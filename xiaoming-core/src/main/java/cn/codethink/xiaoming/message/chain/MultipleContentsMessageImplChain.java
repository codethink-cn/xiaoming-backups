package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.content.Text;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;

import java.util.*;

public class MultipleContentsMessageImplChain
    extends AbstractMessageChain
    implements MessageChain {
    
    public static class BuilderImpl
        implements Builder {
    
        private StringBuilder stringBuilder = null;
        private ArrayList<MessageContent> messageContents;
        private Set<MessageMetadata> messageMetadata;
    
        private void prepareMessageContents(int increaseSize) {
            if (messageContents == null) {
                messageContents = new ArrayList<>(increaseSize);
            } else {
                messageContents.ensureCapacity(messageContents.size() + increaseSize);
            }
        }
        
        private void prepareMessageContents() {
            if (messageContents == null) {
                messageContents = new ArrayList<>();
            }
        }
    
        public void prepareMessageMetadata() {
            if (messageMetadata == null) {
                messageMetadata = new HashSet<>();
            }
        }
    
        @Override
        public Builder plus(MessageContent messageContent) {
            Preconditions.checkNotNull(messageContent, "Message content is null!");
            if (messageContent instanceof Text) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(messageContent);
            } else {
                if (stringBuilder != null && stringBuilder.length() > 1) {
                    messageContents.add(Text.of(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                }
                messageContents.add(messageContent);
            }
            return this;
        }
    
        @Override
        public Builder plus(String text) {
            Preconditions.checkNotNull(text, "Text is null!");
            Preconditions.checkArgument(!text.isEmpty(), "Text is empty!");
            
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(text);
            
            return this;
        }
    
        @Override
        public Builder plus(MessageMetadata messageMetadata) {
            Preconditions.checkNotNull(messageMetadata, "Message metadata is null!");
            prepareMessageMetadata();
            this.messageMetadata.add(messageMetadata);
            return this;
        }
    
        @Override
        public Builder plus(MessageMetadata... messageMetadata) {
            Preconditions.checkNotNull(messageMetadata, "Message metadata is null!");
            for (MessageMetadata metadata : messageMetadata) {
                plus(metadata);
            }
            return this;
        }
    
        @Override
        public Builder plus(MessageContent... messageContents) {
            Preconditions.checkNotNull(messageContents, "Message content is null!");
            prepareMessageContents(messageContents.length);
            for (MessageContent messageContent : messageContents) {
                plus(messageContent);
            }
            return this;
        }
    
        @Override
        public Builder plus(MessageChain messageChain) {
            Preconditions.checkNotNull(messageChain, "Message chain is null!");
            prepareMessageContents(messageChain.size());
            for (MessageContent messageContent : messageChain) {
                plus(messageContent);
            }
            return this;
        }
    
        @Override
        public MessageChain build() {
            if (stringBuilder != null && stringBuilder.length() > 0) {
                messageContents.add(Text.of(stringBuilder.toString()));
                stringBuilder.setLength(0);
            }
            if (messageContents.isEmpty()) {
                throw new IllegalArgumentException("No message content present!");
            }
            if (messageContents.size() == 1) {
                return new SingleMessageContentMessageImplChain(messageContents.get(0));
            } else {
                return new MultipleContentsMessageImplChain(new ArrayList<>(messageContents));
            }
        }
    }
    
    private static final MessageContent[] EMPTY_MESSAGE_CONTENT_ARRAY = {};
    
    private final List<MessageContent> messageContents;
    private MessageContent[] toArrayCache;
    
    private Integer hashCodeCache;
    
    public MultipleContentsMessageImplChain(List<MessageContent> messageContents, Set<MessageMetadata> messageMetadata) {
        super(messageMetadata);
        
        Preconditions.checkNotNull(messageContents, "Message contents are null!");
        Preconditions.checkArgument(!messageContents.isEmpty(), "Message contents are empty!");
        
        int previousTextIndex = -1;
        List<MessageContent> contents = null;
        StringBuilder stringBuilder = null;
        
        // 合并相邻的 Text
        final int size = messageContents.size();
        for (int i = 0; i < size; i++) {
            final MessageContent messageContent = messageContents.get(i);
            if (messageContent instanceof Text) {
                if (previousTextIndex == -1) {
                    previousTextIndex = i;
                }
            } else {
                if (previousTextIndex != -1) {
                    if (contents == null) {
                        contents = new ArrayList<>(messageContents.size() - (i - previousTextIndex));
                        for (int j = 0; j < previousTextIndex; j++) {
                            contents.add(messageContents.get(j));
                        }
                    }
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder();
                    }
                    for (int j = previousTextIndex; j < i; j++) {
                        stringBuilder.append(messageContents.get(j).toString());
                    }
                    contents.add(Text.of(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                    previousTextIndex = -1;
                }
            }
        }
        if (previousTextIndex != -1) {
            if (contents == null) {
                contents = new ArrayList<>(messageContents.size() - (size - previousTextIndex));
                for (int j = 0; j < previousTextIndex; j++) {
                    contents.add(messageContents.get(j));
                }
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            }
            for (int j = previousTextIndex; j < size; j++) {
                stringBuilder.append(messageContents.get(j).toString());
            }
            contents.add(Text.of(stringBuilder.toString()));
        }
        
        if (contents == null) {
            this.messageContents = Collections.unmodifiableList(messageContents);
        } else {
            this.messageContents = Collections.unmodifiableList(contents);
        }
    }
    
    public MultipleContentsMessageImplChain(List<MessageContent> messageContents) {
        this(messageContents, Collections.emptySet());
    }
    
    @Override
    public int size() {
        return messageContents.size();
    }
    
    @Override
    public Iterator<MessageContent> iterator() {
        return messageContents.iterator();
    }
    
    @Override
    public MessageContent get(int index) {
        return messageContents.get(index);
    }
    
    @Override
    public int indexOf(MessageContent messageContent) {
        return messageContents.indexOf(messageContent);
    }
    
    @Override
    public boolean contains(MessageContent messageContent) {
        return messageContents.contains(messageContent);
    }
    
    @Override
    public boolean containsAll(Iterable<MessageContent> messageContents) {
        Preconditions.checkNotNull(messageContents, "Message contents are null!");
        for (MessageContent messageContent : messageContents) {
            if (!this.messageContents.contains(messageContent)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public MessageChain subChain(int begin, int end) {
        final int size = end - begin;
        Preconditions.checkArgument(size > 0, "Unexpected size: " + size);
        if (size == 1) {
            return new SingleMessageContentMessageImplChain(messageContents.get(begin));
        } else {
            return new MultipleContentsMessageImplChain(messageContents.subList(begin, end));
        }
    }
    
    @Override
    public MessageContent[] toArray() {
        if (toArrayCache == null) {
            toArrayCache = messageContents.toArray(EMPTY_MESSAGE_CONTENT_ARRAY);
        }
        return toArrayCache.clone();
    }
    
    @Override
    public String summarize() {
        final int size = messageContents.size();
        if (size == 1) {
            return messageContents.get(0).summarize();
        } else {
            final StringBuilder stringBuilder = new StringBuilder();
            for (MessageContent messageContent : messageContents) {
                stringBuilder.append(messageContent.summarize());
            }
            return stringBuilder.toString();
        }
    }
}
