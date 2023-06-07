/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.codethink.xiaoming.event;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.common.AbstractBotObject;
import cn.codethink.xiaoming.event.listener.ConsumerListenerImpl;
import cn.codethink.xiaoming.event.listener.Listener;
import cn.codethink.xiaoming.event.listener.ListenerException;
import cn.codethink.xiaoming.event.listener.MethodListenerImpl;
import cn.codethink.xiaoming.event.manager.EventManager;
import cn.codethink.xiaoming.logger.Logger;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventManagerImpl
    extends AbstractBotObject
    implements EventManager {
    
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    private static final Priority[] PRIORITIES = {Priority.LOWEST, Priority.LOW, Priority.NORMAL, Priority.HIGH, Priority.HIGHEST};
    
    private final Map<Priority, List<Listener<?>>> listeners = new HashMap<>();
    private final Logger logger;
    
    public EventManagerImpl(Bot bot) {
        super(bot);
        
        logger = bot.getConfiguration().getLoggerFactory().getLogger(EventManagerImpl.class);
    }
    
    @Override
    public <T extends Event> Listener<T> registerListener(Class<T> eventClass, Priority priority, boolean ignoreCancelled,
                                                          Consumer<T> action) {
        
        Preconditions.checkNotNull(action, "Action is null!");
        Preconditions.checkNotNull(priority, "Priority is null!");
    
        final ConsumerListenerImpl<T> e = new ConsumerListenerImpl<>(action, Collections.singleton(eventClass), ignoreCancelled);
        
        lock.writeLock().lock();
        try {
            listeners.computeIfAbsent(priority, ignored -> new ArrayList<>()).add(e);
        } finally {
            lock.writeLock().unlock();
        }
        
        return e;
    }
    
    @Override
    public <T extends Event> Listener<T> registerListener(Class<T> eventClass, Consumer<T> action) {
        return registerListener(eventClass, Priority.NORMAL, false, action);
    }
    
    @Override
    public void registerListeners(Object listeners) {
        Preconditions.checkNotNull(listeners, "Listeners are null!");
    
        lock.writeLock().lock();
        try {
            registerListenersWithoutLocking(listeners);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public void registerListeners(Object listeners1, Object listeners2) {
        Preconditions.checkNotNull(listeners1, "Listeners 1 are null!");
        Preconditions.checkNotNull(listeners2, "Listeners 2 are null!");
    
        lock.writeLock().lock();
        try {
            registerListenersWithoutLocking(listeners1);
            registerListenersWithoutLocking(listeners2);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public void registerListeners(Object... listeners) {
        Preconditions.checkNotNull(listeners, "Listeners are null!");
        
        lock.writeLock().lock();
        try {
            for (int i = 0; i < listeners.length; i++) {
                Preconditions.checkNotNull(listeners[i], "Listeners " + (i + 1) + " is null!");
                registerListenersWithoutLocking(listeners[i]);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void registerListenersWithoutLocking(Object subject) {
        final Method[] declaredMethods = subject.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            final cn.codethink.xiaoming.annotation.Listener listenerAnnotation = method.getAnnotation(
                cn.codethink.xiaoming.annotation.Listener.class);
            if (listenerAnnotation == null) {
                continue;
            }
    
            final Priority priority = listenerAnnotation.priority();
            final boolean ignoreCancelled = listenerAnnotation.ignoreCancelled();
            final Class<? extends Event>[] eventClasses = listenerAnnotation.value();
            
            final MethodListenerImpl<?> listener;
            if (eventClasses.length == 0) {
                switch (method.getParameterCount()) {
                    case 0:
                        throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": no event class declaration found!");
                    case 1:
                        final Parameter parameter = method.getParameters()[0];
                        final Class<?> parameterType = parameter.getType();
                        
                        if (!Event.class.isAssignableFrom(parameterType)) {
                            throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": unique parameter class must be a subclass of " + Event.class.getName());
                        }
                        
                        listener = new MethodListenerImpl<>(method, subject, Collections.singleton((Class<? extends Event>) parameterType), ignoreCancelled);
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": method can only have 1 or 0 parameter!");
                }
            } else {
                final Set<Class<? extends Event>> eventClassesSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(eventClasses)));
    
                switch (method.getParameterCount()) {
                    case 0:
                        listener = new MethodListenerImpl<>(method, subject, eventClassesSet, ignoreCancelled);
                        break;
                    case 1:
                        final Parameter parameter = method.getParameters()[0];
                        final Class<?> parameterType = parameter.getType();
    
                        if (!Event.class.isAssignableFrom(parameterType)) {
                            throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": unique parameter class must be a subclass of " + Event.class.getName());
                        }
    
                        for (int i = 0; i < eventClasses.length; i++) {
                            if (!parameterType.isAssignableFrom(eventClasses[i])) {
                                throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": " +
                                    "event class " + (i + 1) + " " + eventClasses[i].getName() + " declared in @Listener field 'value' " +
                                    "must be a subclass of parameter class: " + parameterType.getName());
                            }
                        }
                        listener = new MethodListenerImpl<>(method, subject, eventClassesSet, ignoreCancelled);
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected listener method: " + method.getName() + ": method can only have 1 or 0 parameter!");
                }
            }
            
            listeners.computeIfAbsent(priority, ignored -> new ArrayList<>()).add(listener);
        }
    }
    
    @Override
    public boolean unregisterListenersIf(Predicate<Listener<?>> filter) {
        Preconditions.checkNotNull(filter, "Filter is null!");
        
        boolean effected = false;
        
        lock.writeLock().lock();
        try {
            for (List<Listener<?>> samePriorityListeners : listeners.values()) {
                effected = samePriorityListeners.removeIf(filter) || effected;
            }
            listeners.values().removeIf(List::isEmpty);
        } finally {
            lock.writeLock().unlock();
        }
        
        return effected;
    }
    
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void publish(Event event) {
        Preconditions.checkNotNull(event, "Event is null!");
        
        final CancellableEvent cancellableEvent = event instanceof CancellableEvent ? (CancellableEvent) event : null;
        
        lock.readLock().lock();
        try {
            for (Priority priority : PRIORITIES) {
                final List<Listener<?>> samePriorityListeners = listeners.get(priority);
                if (samePriorityListeners == null || samePriorityListeners.isEmpty()) {
                    continue;
                }
    
                for (Listener<?> listener : samePriorityListeners) {
                    if (cancellableEvent != null && !listener.isIgnoreCancelled() && cancellableEvent.isCancelled()) {
                        continue;
                    }
                    
                    boolean listen = false;
                    for (Class<?> eventClass : listener.getEventClasses()) {
                        if (eventClass.isInstance(event)) {
                            listen = true;
                            break;
                        }
                    }
                    if (!listen) {
                        continue;
                    }
    
                    try {
                        ((Listener) listener).listen(event);
                    } catch (ListenerException e) {
                        logger.error(e.getCause(), "Exception thrown in listener");
                    } catch (IllegalAccessException e) {
                        logger.error(e, "Fail to change the accessible of listener!");
                    } catch (Throwable e) {
                        logger.error(e, "Exception thrown in calling listener");
                    }
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}
