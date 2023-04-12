package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.AbstractMessage;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public abstract class AbstractMessageChain
    extends AbstractMessage
    implements MessageChain {
    
    private Integer hashCodeCache;
    
    private final Set<MessageMetadata> metadata;
    
    public AbstractMessageChain() {
        this.metadata = Collections.emptySet();
    }
    
    public AbstractMessageChain(Set<MessageMetadata> metadata) {
        Preconditions.checkNotNull(metadata, "Message metadata is null!");
        this.metadata = metadata;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends MessageMetadata> T get(Class<T> metadataClass) {
        Preconditions.checkNotNull(metadataClass, "MessageMetadata class is null!");
        
        final List<MessageMetadata> metadata = ListUtils.select(this.metadata,
            messageMetadata -> metadataClass.isAssignableFrom(messageMetadata.getClass()));
        if (metadata.isEmpty()) {
            return null;
        }
        if (metadata.size() == 1) {
            return (T) metadata.get(0);
        } else {
            throw new IllegalArgumentException("Ambiguous metadata class: " + metadataClass.getName() + ", " + metadata.size() + " metadata found!");
        }
    }
    
    @Override
    public <T extends MessageMetadata> T getOrFail(Class<T> metadataClass) {
        final T metadata = get(metadataClass);
        if (metadata == null) {
            throw new NoSuchElementException("No such metadata that is an instance of " + metadataClass.getName());
        }
        return metadata;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MessageChain) || super.equals(o)) {
            return false;
        }
    
        final MessageChain messageChain = (MessageChain) o;
        
        final Iterator<MessageContent> iterator = iterator();
        final Iterator<MessageContent> thatIterator = messageChain.iterator();
        
        while (iterator.hasNext() && thatIterator.hasNext()) {
            if (!Objects.equals(iterator.next(), thatIterator.next())) {
                return false;
            }
        }
        return iterator.hasNext() == thatIterator.hasNext();
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            int result = 0;
            for (MessageContent messageContent : this) {
                result = 31 * result + messageContent.hashCode();
            }
            hashCodeCache = result;
        }
        return hashCodeCache;
    }
    
    @Override
    public MessageChain subChain(int begin) {
        return subChain(begin, size());
    }
    
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
            return this;
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
            return this;
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
