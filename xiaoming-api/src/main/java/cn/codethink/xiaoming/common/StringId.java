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

package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>字符串标识符</h1>
 *
 * @author Chuanwise
 */
public interface StringId
    extends Id, Comparable<StringId> {
    
    /**
     * 获取空字符串标识符
     *
     * @return 空字符串标识符
     */
    static StringId empty() {
        return APIFactory.getInstance().getEmptyStringId();
    }
    
    /**
     * 构造字符串标识符
     *
     * @param value 字符串
     * @return 字符串标识符
     */
    static StringId of(String value) {
        return APIFactory.getInstance().getStringId(value);
    }
}
