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

import cn.codethink.xiaoming.message.AbstractMessage;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.SingleMessageContentMessageChainImpl;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;

public abstract class AbstractMessageContent
    extends AbstractMessage
    implements MessageContent {
    
    @Override
    public MessageChain plus(MessageContent messageContent) {
        return MessageChain.builder()
            .plus(this)
            .plus(messageContent)
            .build();
    }
    
    @Override
    public MessageChain plus(MessageContent... messageContents) {
        Preconditions.checkNotNull(messageContents, "Message contents is null!");
        if (messageContents.length == 0) {
            return new SingleMessageContentMessageChainImpl(this);
        } else {
            return MessageChain.builder()
                .plus(this)
                .plus(messageContents)
                .build();
        }
    }
    
    @Override
    public MessageChain plus(MessageMetadata messageMetadata) {
        return MessageChain.builder()
            .plus(this)
            .plus(messageMetadata)
            .build();
    }
    
    @Override
    public MessageChain plus(MessageMetadata... messageMetadata) {
        Preconditions.checkNotNull(messageMetadata, "Message metadata is null!");
        if (messageMetadata.length == 0) {
            return new SingleMessageContentMessageChainImpl(this);
        } else {
            return MessageChain.builder()
                .plus(this)
                .plus(messageMetadata)
                .build();
        }
    }
    
    @Override
    public MessageChain plus(String text) {
        return MessageChain.builder()
            .plus(this)
            .plus(text)
            .build();
    }
    
    @Override
    public MessageChain plus(MessageChain messageChain) {
        return MessageChain.builder()
            .plus(this)
            .plus(messageChain)
            .build();
    }
}
