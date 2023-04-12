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
