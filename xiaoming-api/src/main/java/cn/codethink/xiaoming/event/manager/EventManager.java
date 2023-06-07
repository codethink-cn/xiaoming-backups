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

package cn.codethink.xiaoming.event.manager;

import cn.codethink.xiaoming.event.Event;
import cn.codethink.xiaoming.event.Priority;
import cn.codethink.xiaoming.event.listener.Listener;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <h1>事件管理器</h1>
 *
 * <p>事件管理器用于发布事件和注册、注销和管理事件监听器。</p>
 *
 * @author Chuanwise
 */
public interface EventManager {
    
    /**
     * 注册事件监听器
     *
     * @param eventClass      事件类型
     * @param priority        优先级
     * @param ignoreCancelled 是否忽略已经被取消的事件
     * @param action          监听器
     * @param <T>             事件类型
     */
    <T extends Event> Listener<T> registerListener(Class<T> eventClass, Priority priority, boolean ignoreCancelled, Consumer<T> action);
    
    /**
     * 注册事件监听器
     *
     * @param eventClass      事件类型
     * @param action          监听器
     * @param <T>             事件类型
     */
    <T extends Event> Listener<T> registerListener(Class<T> eventClass, Consumer<T> action);
    
    /**
     * 注册方法为监听器
     *
     * @param listeners 对象
     */
    void registerListeners(Object listeners);
    
    /**
     * 注册方法为监听器
     *
     * @param listeners1 对象 1
     * @param listeners2 对象 2
     */
    void registerListeners(Object listeners1, Object listeners2);
    
    /**
     * 注册方法为监听器
     *
     * @param listeners 对象
     */
    void registerListeners(Object... listeners);
    
    /**
     * 注销符合条件的监听器
     *
     * @param filter 筛选条件
     * @return 是否注销任一监听器
     */
    boolean unregisterListenersIf(Predicate<Listener<?>> filter);
    
    /**
     * 发布事件
     *
     * @param event 事件
     */
    void publish(Event event);
}