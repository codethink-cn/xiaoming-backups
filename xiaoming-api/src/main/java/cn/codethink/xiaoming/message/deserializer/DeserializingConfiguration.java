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

package cn.codethink.xiaoming.message.deserializer;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.relation.Relation;

/**
 * <h1>反序列化配置</h1>
 *
 * @author Chuanwise
 */
public interface DeserializingConfiguration {
    
    /**
     * 反序列化配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置机器人
         *
         * @param bot 机器人
         * @return 反序列化配置构建器
         */
        Builder bot(Bot bot);
    
        /**
         * 设置关系
         *
         * @param relation 关系
         * @return 反序列化配置构建器
         */
        Builder relation(Relation relation);
    
        /**
         * 构建反序列化配置
         *
         * @return 反序列化配置
         */
        DeserializingConfiguration build();
    }
    
    /**
     * 获取反序列化配置构建器
     *
     * @return 反序列化配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getDeserializingConfigurationBuilder();
    }
    
    /**
     * 获取默认反序列化配置
     *
     * @return 默认反序列化配置
     */
    static DeserializingConfiguration getInstance() {
        return APIFactory.getInstance().getDeserializingConfiguration();
    }
    
    /**
     * 获取机器人
     *
     * @return 机器人
     */
    Bot getBot();
    
    /**
     * 获取关系
     *
     * @return 关系
     */
    Relation getRelation();
}
