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

package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>文本消息</h1>
 *
 * @author Chuanwise
 */
public interface Text
    extends MessageContent {
    
    /**
     * 构造文本消息
     *
     * @param object 信息
     * @return 文本消息
     */
    static Text of(Object object) {
        return APIFactory.getInstance().getText(object);
    }
    
    /**
     * 获取文本数据
     *
     * @return 文本数据
     */
    String getText();
}
