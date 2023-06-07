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

/**
 * <h1>可拦截的</h1>
 *
 * @author Chuanwise
 */
public interface InterceptableEvent
    extends Event {
    
    /**
     * 判断事件是否被拦截
     *
     * @return 事件是否被拦截
     */
    boolean isIntercepted();
    
    /**
     * 设置事件是否被拦截
     *
     * @param intercepted 事件是否被拦截
     */
    void setIntercepted(boolean intercepted);
}
