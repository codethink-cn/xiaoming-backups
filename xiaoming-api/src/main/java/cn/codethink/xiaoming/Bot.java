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

package cn.codethink.xiaoming;

import cn.codethink.xiaoming.adapter.Adapter;
import cn.codethink.xiaoming.common.IdObject;
import cn.codethink.xiaoming.relation.Individual;
import cn.codethink.xiaoming.relation.RelationManager;

/**
 * <h1>机器人</h1>
 *
 * @author Chuanwise
 */
public interface Bot
    extends Individual, IdObject {
    
    /**
     * 获取关系管理器
     *
     * @return 关系管理器
     */
    RelationManager getRelationManager();
    
    /**
     * 获取适配器
     *
     * @return 适配器
     */
    Adapter getAdapter();
}
