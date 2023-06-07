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

import java.util.Objects;

public class AbstractCancellableEvent
    implements CancellableEvent {
    
    private volatile boolean cancelled;
    
    private Integer hashCodeCache;
    
    public AbstractCancellableEvent() {
    }
    
    public AbstractCancellableEvent(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CancellableEvent)) {
            return false;
        }
        return ((CancellableEvent) o).isCancelled() == cancelled;
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(cancelled);
        }
        return hashCodeCache;
    }
}
