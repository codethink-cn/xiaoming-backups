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

package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.expression.format.FormatConfiguration;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;

/**
 * <h1>消息</h1>
 *
 * <p>消息是在即时通讯软件机器人中一切可发送和可接收的消息的总接口。</p>
 *
 * @author Chuanwise
 * @see MessageChain
 * @see MessageContent
 */
public interface Message
    extends MessageElement {
    
    /**
     * 概括消息
     *
     * @return 消息概要
     */
    String summarize();
    
    /**
     * 序列化为消息码
     *
     * @return 消息码
     */
    String serializeToMessageCode();
    
    /**
     * 序列化为消息码
     *
     * @param configuration 序列化配置
     * @return 消息码
     */
    String serializeToMessageCode(SerializingConfiguration configuration);
}
