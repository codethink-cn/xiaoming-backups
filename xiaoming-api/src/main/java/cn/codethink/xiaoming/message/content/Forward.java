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

import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.common.Time;
import cn.codethink.xiaoming.message.chain.MessageChain;

import java.util.List;

/**
 * <h1>转发消息</h1>
 *
 * @author Chuanwise
 */
public interface Forward
    extends MessageContent {
    
    /**
     * 转发消息内容
     *
     * @author Chuanwise
     */
    interface Content {
        
        /**
         * 获取发送人 Id
         *
         * @return 发送人 Id
         */
        Id getSenderId();
        
        /**
         * 获取发送时间
         *
         * @return 发送时间
         */
        Time getTime();
        
        /**
         * 获取发送人名称
         *
         * @return 发送人名称
         */
        String getSenderName();
        
        /**
         * 获取消息链
         *
         * @return 消息链
         */
        MessageChain getMessageChain();
    }
    
    /**
     * 获取预览
     *
     * @return 预览
     */
    List<String> getPreview();
    
    /**
     * 获取标题
     *
     * @return 标题
     */
    List<String> getTitle();
    
    /**
     * 获取描述
     *
     * @return 描述
     */
    List<String> getDescription();
    
    /**
     * 获取内容
     *
     * @return 内容
     */
    List<Content> getContents();
}
