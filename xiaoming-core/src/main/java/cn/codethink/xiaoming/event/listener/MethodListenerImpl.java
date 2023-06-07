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

package cn.codethink.xiaoming.event.listener;

import cn.codethink.xiaoming.event.Event;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Set;

public class MethodListenerImpl<T extends Event>
    implements Listener<T> {
    
    private static final Object[] EMPTY_OBJECT_ARRAY = {};
    
    private final Object subject;
    private final boolean ignoreCancelled;
    private final Set<Class<? extends T>> eventClasses;
    private final Method method;
    
    private Integer hashCodeCache;
    
    public MethodListenerImpl(Method method, Object subject, Set<Class<? extends T>> eventClasses, boolean ignoreCancelled) {
    
        Preconditions.checkNotNull(method, "Method is null!");
    
        final int modifiers = method.getModifiers();
        final Class<?> declaringClass = method.getDeclaringClass();
        if (Modifier.isStatic(modifiers)) {
            this.subject = declaringClass;
        } else {
            Preconditions.checkNotNull(subject, "Subject is null!");
            Preconditions.checkArgument(declaringClass.isInstance(subject), "Subject must be instance of the declaring class of method: " + declaringClass.getName());
    
            this.subject = subject;
        }
        
        this.ignoreCancelled = ignoreCancelled;
        this.eventClasses = eventClasses;
        this.method = method;
    }
    
    @Override
    public void listen(Event event) throws Exception {
        final boolean accessible = method.isAccessible();
        if (!accessible) {
            method.setAccessible(true);
        }
        try {
            if (method.getParameterCount() == 1) {
                method.invoke(subject, event);
            } else {
                method.invoke(subject, EMPTY_OBJECT_ARRAY);
            }
        } catch (InvocationTargetException e) {
            throw new ListenerException(e.getCause());
        } finally {
            if (!accessible) {
                method.setAccessible(false);
            }
        }
    }
    
    @Override
    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }
    
    @Override
    public Set<Class<? extends T>> getEventClasses() {
        return eventClasses;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MethodListenerImpl<?> that = (MethodListenerImpl<?>) o;
        return ignoreCancelled == that.ignoreCancelled
            && Objects.equals(subject, that.subject)
            && Objects.equals(eventClasses, that.eventClasses)
            && Objects.equals(method, that.method);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(subject, ignoreCancelled, eventClasses, method);
        }
        return hashCodeCache;
    }
}
