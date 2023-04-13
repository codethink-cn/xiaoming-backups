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

package cn.codethink.xiaoming.adapter;

import cn.codethink.xiaoming.api.APIFactory;

import java.util.Set;

/**
 * <h1>适配器</h1>
 *
 * @author Chuanwise
 */
public interface Adapter {
    
    /**
     * 获取适配器
     *
     * @param name 适配器名
     * @return 适配器
     */
    static Adapter of(String name) {
        return APIFactory.getInstance().getIM(name);
    }
    
    /**
     * 获取目前的所有适配器
     *
     * @return 目前的所有适配器
     */
    static Set<Adapter> getInstances() {
        return APIFactory.getInstance().getIMs();
    }
}
