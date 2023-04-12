package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.MessageCode;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.content.Text;
import com.google.common.base.Preconditions;

import java.util.*;

public class MessageChainImpl
    implements MessageChain {
    
    public static class BuilderImpl
        implements Builder {
    
        private final ArrayList<MessageContent> messageContents = new ArrayList<>();
    
        @Override
        public Builder plus(MessageContent messageContent) {
            Preconditions.checkNotNull(messageContent, "Message content is null!");
            messageContents.add(messageContent);
            return this;
        }
    
        @Override
        public Builder plus(String text) {
            return plus(Text.of(text));
        }
    
        @Override
        public Builder plus(MessageContent... messageContents) {
            Preconditions.checkNotNull(messageContents, "Message content is null!");
            for (MessageContent messageContent : messageContents) {
                plus(messageContent);
            }
            return this;
        }
    
        @Override
        public Builder plus(MessageChain messageChain) {
            Preconditions.checkNotNull(messageChain, "Message chain is null!");
            for (MessageContent messageContent : messageChain) {
                plus(messageContent);
            }
            return this;
        }
    
        @Override
        @SuppressWarnings("unchecked")
        public MessageChain build() {
            if (messageContents.isEmpty()) {
                throw new IllegalArgumentException("No message element present!");
            }
            if (messageContents.size() == 1) {
                return new MessageChainImpl(Collections.singletonList(messageContents.get(0)));
            } else {
                return new MessageChainImpl((List<MessageContent>) messageContents.clone());
            }
        }
    }
    
    private static final MessageContent[] EMPTY_MESSAGE_SEGMENT_ARRAY = {};
    
    private final List<MessageContent> segments;
    
    private String toStringCache;
    private Integer hashCodeCache;
    
    public MessageChainImpl(List<MessageContent> segments) {
        Preconditions.checkNotNull(segments, "Message contents are null!");
        this.segments = Collections.unmodifiableList(segments);
    }
    
    @Override
    public int size() {
        return segments.size();
    }
    
    @Override
    public boolean isEmpty() {
        return segments.isEmpty();
    }
    
    @Override
    public boolean contains(Object o) {
        return segments.contains(o);
    }
    
    @Override
    public Iterator<MessageContent> iterator() {
        return segments.iterator();
    }
    
    @Override
    public Object[] toArray() {
        return toArray(EMPTY_MESSAGE_SEGMENT_ARRAY);
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        return segments.toArray(a);
    }
    
    @Override
    public boolean add(MessageContent messageContent) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return segments.contains(c);
    }
    
    @Override
    public boolean addAll(Collection<? extends MessageContent> c) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends MessageContent> c) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public MessageContent get(int index) {
        return segments.get(index);
    }
    
    @Override
    public MessageContent set(int index, MessageContent element) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public void add(int index, MessageContent element) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public MessageContent remove(int index) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public int indexOf(Object o) {
        return segments.indexOf(o);
    }
    
    @Override
    public int lastIndexOf(Object o) {
        return segments.lastIndexOf(o);
    }
    
    @Override
    public ListIterator<MessageContent> listIterator() {
        return segments.listIterator();
    }
    
    @Override
    public ListIterator<MessageContent> listIterator(int index) {
        return segments.listIterator(index);
    }
    
    @Override
    public List<MessageContent> subList(int fromIndex, int toIndex) {
        return segments.subList(fromIndex, toIndex);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageChainImpl that = (MessageChainImpl) o;
        return Objects.equals(segments, that.segments);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(segments);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = MessageCode.serialize(this);
        }
        return toStringCache;
    }
}
