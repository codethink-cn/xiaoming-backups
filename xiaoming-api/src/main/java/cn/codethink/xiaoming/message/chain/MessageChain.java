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

package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;

import java.util.RandomAccess;
import java.util.Set;

/**
 * <h1>复合消息</h1>
 *
 * @author Chuanwise
 */
public interface MessageChain
    extends Message, Iterable<MessageContent>, RandomAccess {
    
    /**
     * 复合消息构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 添加消息片段
         *
         * @param messageContent 消息片段
         * @return 复合消息构建器
         */
        Builder plus(MessageContent messageContent);
    
        /**
         * 添加若干消息
         *
         * @param messageContents 若干消息
         * @return 复合消息构建器
         */
        Builder plus(MessageContent... messageContents);
    
        /**
         * 添加文本消息
         *
         * @param text 文本信息
         * @return 复合消息构建器
         */
        Builder plus(String text);
        
        /**
         * 添加消息元数据
         *
         * @param messageMetadata 文本信息
         * @return 复合消息构建器
         */
        Builder plus(MessageMetadata messageMetadata);
        
        /**
         * 添加消息元数据
         *
         * @param messageMetadata 文本信息
         * @return 复合消息构建器
         */
        Builder plus(MessageMetadata... messageMetadata);
    
        /**
         * 添加复合消息
         *
         * @param messageChain 复合消息
         * @return 复合消息构建器
         */
        Builder plus(MessageChain messageChain);
    
        /**
         * 构建复合消息
         *
         * @return 复合消息
         */
        MessageChain build();
    }
    
    /**
     * 获取复合消息构建器
     *
     * @return 复合消息构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getMessageChainBuilder();
    }
    
    /**
     * 通过若干消息片段构建复合消息
     *
     * @param messageContents 若干消息片段
     * @return 复合消息
     */
    static MessageChain of(MessageContent... messageContents) {
        return APIFactory.getInstance().getMessageChain(messageContents);
    }
    
    /**
     * 通过消息片段构建复合消息
     *
     * @param messageContent 消息片段
     * @return 复合消息
     */
    static MessageChain of(MessageContent messageContent) {
        return APIFactory.getInstance().getMessageChain(messageContent);
    }
    
    /**
     * 获取消息元数据
     *
     * @param metadataClass 消息元数据
     * @param <T>           消息元数据类型
     * @return 消息元数据或 null
     */
    <T extends MessageMetadata> T get(Class<T> metadataClass);
    
    /**
     * 获取消息元数据
     *
     * @param metadataClass 消息元数据
     * @param <T>           消息元数据类型
     * @return 消息元数据
     */
    <T extends MessageMetadata> T getOrFail(Class<T> metadataClass);
    
    /**
     * 获取复合消息大小
     *
     * @return 复合消息大小
     */
    int size();
    
    /**
     * 获取位于指定位置的内容
     *
     * @param index 索引
     * @return 消息内容
     */
    MessageContent get(int index);
    
    /**
     * 查找消息内容的索引
     *
     * @param messageContent 消息内容
     * @return 索引或 -1
     */
    int indexOf(MessageContent messageContent);
    
    /**
     * 判断是否包含消息内容
     *
     * @param messageContent 消息内容
     * @return 是否包含消息内容
     */
    boolean contains(MessageContent messageContent);
    
    /**
     * 判断是否包含若干消息内容
     *
     * @param messageContents 若干消息内容
     * @return 是否包含若干消息内容
     */
    boolean containsAll(Iterable<MessageContent> messageContents);
    
    /**
     * 获取子复合消息
     *
     * @param begin 起始索引
     * @param end   终止索引
     * @return 子复合消息
     */
    MessageChain subChain(int begin, int end);
    
    /**
     * 获取子复合消息
     *
     * @param begin 起始索引
     * @return 子复合消息
     */
    MessageChain subChain(int begin);
    
    /**
     * 将复合消息转化为数组
     *
     * @return 数组
     */
    MessageContent[] toArray();
}
