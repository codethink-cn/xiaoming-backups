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

package cn.codethink.xiaoming.annotation;

import cn.codethink.xiaoming.event.Event;
import cn.codethink.xiaoming.event.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>监听器注解</h1>
 *
 * @author Chuanwise
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    
    /**
     * 获取监听的事件类型
     *
     * @return 监听的事件类型
     */
    Class<? extends Event>[] value() default {};
    
    /**
     * 获取监听器优先级
     *
     * @return 监听器优先级
     */
    Priority priority() default Priority.NORMAL;
    
    /**
     * 获取监听器是否忽略已经被取消的事件
     *
     * @return 监听器是否忽略已经被取消的事件
     */
    boolean ignoreCancelled() default false;
}
