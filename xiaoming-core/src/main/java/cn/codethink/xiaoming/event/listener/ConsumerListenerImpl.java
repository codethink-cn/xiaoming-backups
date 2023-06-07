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

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class ConsumerListenerImpl<T extends Event>
    implements Listener<T> {
    
    private final Consumer<T> action;
    private final Set<Class<? extends T>> eventClasses;
    private final boolean ignoreCancelled;
    
    private Integer hashCodeCache;
    
    public ConsumerListenerImpl(Consumer<T> action, Set<Class<? extends T>> eventClasses, boolean ignoreCancelled) {
        Preconditions.checkNotNull(action, "Action is null!");
        Preconditions.checkNotNull(eventClasses, "Event classes is null!");
        
        this.action = action;
        this.eventClasses = eventClasses;
        this.ignoreCancelled = ignoreCancelled;
    }
    
    @Override
    public void listen(T event) throws Exception {
        Preconditions.checkNotNull(event, "Event is null!");
    
        try {
            action.accept(event);
        } catch (Throwable e) {
            throw new ListenerException(e);
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
        final ConsumerListenerImpl<?> that = (ConsumerListenerImpl<?>) o;
        return ignoreCancelled == that.ignoreCancelled
            && Objects.equals(action, that.action)
            && Objects.equals(eventClasses, that.eventClasses);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(action, eventClasses, ignoreCancelled);
        }
        return hashCodeCache;
    }
}
