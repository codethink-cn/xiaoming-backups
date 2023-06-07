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

import java.util.Set;

/**
 * <h1>事件监听器</h1>
 *
 * @author Chuanwise
 */
public interface Listener<T extends Event> {
    
    /**
     * 监听事件
     *
     * @param event 事件
     */
    void listen(T event) throws Exception;
    
    /**
     * 获取是否监听已经被取消的事件
     *
     * @return 是否监听已经被取消的事件
     */
    boolean isIgnoreCancelled();
    
    /**
     * 获取事件类型
     *
     * @return 事件类型
     */
    Set<Class<? extends T>> getEventClasses();
}
