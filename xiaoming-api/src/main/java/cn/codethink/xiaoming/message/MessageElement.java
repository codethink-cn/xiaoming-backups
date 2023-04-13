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

import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;

/**
 * <h1>消息元素</h1>
 *
 * <p>消息元素是组成消息的部分。</p>
 *
 * @author Chuanwise
 * @see MessageContent
 * @see MessageMetadata
 */
public interface MessageElement {
    
    /**
     * 添加一个消息内容
     *
     * @param messageContent 消息内容
     * @return 复合消息
     */
    MessageChain plus(MessageContent messageContent);
    
    /**
     * 添加一些消息内容
     *
     * @param messageContents 消息内容
     * @return 复合消息
     */
    MessageChain plus(MessageContent... messageContents);
    
    /**
     * 添加一个消息元数据
     *
     * @param messageMetadata 消息元数据
     * @return 复合消息
     */
    MessageChain plus(MessageMetadata messageMetadata);
    
    /**
     * 添加一些消息元数据
     *
     * @param messageMetadata 消息元数据
     * @return 复合消息
     */
    MessageChain plus(MessageMetadata... messageMetadata);
    
    /**
     * 添加一段文本
     *
     * @param text 文本
     * @return 复合消息
     */
    MessageChain plus(String text);
    
    /**
     * 添加一段复合消息
     *
     * @param messageChain 复合消息
     * @return 复合消息
     */
    MessageChain plus(MessageChain messageChain);
}
