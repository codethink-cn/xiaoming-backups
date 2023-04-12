package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;
import javafx.scene.effect.SepiaTone;
import org.apache.commons.collections4.iterators.SingletonIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SingleMessageContentMessageImplChain
    extends AbstractMessageChain
    implements MessageChain {
    
    private final MessageContent messageContent;

    private MessageContent[] toArrayCache;
    
    public SingleMessageContentMessageImplChain(MessageContent messageContent) {
        this(messageContent, Collections.emptySet());
    }
    
    public SingleMessageContentMessageImplChain(MessageContent messageContent, Set<MessageMetadata> messageMetadata) {
        super(messageMetadata);
        
        Preconditions.checkNotNull(messageContent, "Message content is null!");
    
        this.messageContent = messageContent;
    }
    
    @Override
    public int size() {
        return 1;
    }
    
    @Override
    public MessageContent get(int index) {
        Preconditions.checkElementIndex(index, 1);
        return messageContent;
    }
    
    @Override
    public int indexOf(MessageContent messageContent) {
        return Objects.equals(this.messageContent, messageContent) ? 0 : -1;
    }
    
    @Override
    public boolean contains(MessageContent messageContent) {
        return Objects.equals(this.messageContent, messageContent);
    }
    
    @Override
    public boolean containsAll(Iterable<MessageContent> messageContents) {
        Preconditions.checkNotNull(messageContents, "Message contents are null!");
        final Iterator<MessageContent> iterator = messageContents.iterator();
        if (!iterator.hasNext()) {
            return false;
        }
        
        final MessageContent messageContent = iterator.next();
        if (iterator.hasNext()) {
            return false;
        } else {
            return Objects.equals(this.messageContent, messageContent);
        }
    }
    
    @Override
    public MessageChain subChain(int begin, int end) {
        Preconditions.checkArgument(begin == 0 && end == 1, "Unexpected range: [ " + begin + ", " + end + " )");
        return this;
    }
    
    @Override
    public MessageContent[] toArray() {
        if (toArrayCache == null) {
            toArrayCache = new MessageContent[1];
            toArrayCache[0] = messageContent;
        }
        return toArrayCache.clone();
    }
    
    @Override
    public Iterator<MessageContent> iterator() {
        return new SingletonIterator<>(messageContent);
    }
    
    @Override
    public String summarize() {
        return messageContent.summarize();
    }
}
