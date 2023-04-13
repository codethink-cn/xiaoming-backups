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

package cn.codethink.xiaoming.relation;

import cn.codethink.xiaoming.common.BotObject;
import cn.codethink.xiaoming.common.Id;

/**
 * <h1>好友们</h1>
 *
 * <p>用于获取具体好友的组件</p>
 *
 * @author Chuanwise
 */
public interface Friends
    extends Iterable<Friend>, BotObject {
    
    /**
     * 获取好友
     *
     * @param id 好友 ID
     * @return 好友
     */
    Friend get(Id id);
    
    
}
