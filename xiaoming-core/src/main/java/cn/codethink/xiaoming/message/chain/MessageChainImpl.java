package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.MessageCode;
import cn.codethink.xiaoming.message.element.MessageElement;
import cn.codethink.xiaoming.message.element.Text;
import com.google.common.base.Preconditions;

import java.util.*;

public class MessageChainImpl
    implements MessageChain {
    
    public static class BuilderImpl
        implements Builder {
    
        private final ArrayList<MessageElement> messageElements = new ArrayList<>();
    
        @Override
        public Builder plus(MessageElement messageElement) {
            Preconditions.checkNotNull(messageElement, "Single Message is null!");
            messageElements.add(messageElement);
            return this;
        }
    
        @Override
        public Builder plus(String text) {
            return plus(Text.of(text));
        }
    
        @Override
        public Builder plus(MessageElement... messageElements) {
            Preconditions.checkNotNull(messageElements, "Single Message is null!");
            for (MessageElement messageElement : messageElements) {
                plus(messageElement);
            }
            return this;
        }
    
        @Override
        public Builder plus(MessageChain messageChain) {
            Preconditions.checkNotNull(messageChain, "Message chain is null!");
            for (MessageElement messageElement : messageChain) {
                plus(messageElement);
            }
            return this;
        }
    
        @Override
        @SuppressWarnings("unchecked")
        public MessageChain build() {
            if (messageElements.isEmpty()) {
                throw new IllegalArgumentException("No message element present!");
            }
            if (messageElements.size() == 1) {
                return new MessageChainImpl(Collections.singletonList(messageElements.get(0)));
            } else {
                return new MessageChainImpl((List<MessageElement>) messageElements.clone());
            }
        }
    }
    
    private static final MessageElement[] EMPTY_MESSAGE_SEGMENT_ARRAY = {};
    
    private final List<MessageElement> segments;
    
    private String toStringCache;
    private Integer hashCodeCache;
    
    public MessageChainImpl(List<MessageElement> segments) {
        Preconditions.checkNotNull(segments, "Message segments are null!");
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
    public Iterator<MessageElement> iterator() {
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
    public boolean add(MessageElement messageElement) {
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
    public boolean addAll(Collection<? extends MessageElement> c) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends MessageElement> c) {
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
    public MessageElement get(int index) {
        return segments.get(index);
    }
    
    @Override
    public MessageElement set(int index, MessageElement element) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public void add(int index, MessageElement element) {
        throw new UnsupportedOperationException("Message chain is unmodifiable!");
    }
    
    @Override
    public MessageElement remove(int index) {
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
    public ListIterator<MessageElement> listIterator() {
        return segments.listIterator();
    }
    
    @Override
    public ListIterator<MessageElement> listIterator(int index) {
        return segments.listIterator(index);
    }
    
    @Override
    public List<MessageElement> subList(int fromIndex, int toIndex) {
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
