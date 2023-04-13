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

import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.content.Text;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;

import java.util.*;

public class MultipleMessageContentsMessageChainImpl
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
            if (stringBuilder != null && stringBuilder.length() > 0) {
                messageContents.add(Text.of(stringBuilder.toString()));
                stringBuilder.setLength(0);
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
                plus(((Text) messageContent).getText());
            } else {
                prepareMessageContents();
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
            if (messageContents == null || messageContents.isEmpty()) {
                throw new IllegalArgumentException("No message content present!");
            }
            final Set<MessageMetadata> messageMetadata;
            if (this.messageMetadata == null) {
                messageMetadata = Collections.emptySet();
            } else if (this.messageMetadata.size() == 1) {
                messageMetadata = Collections.singleton(this.messageMetadata.iterator().next());
            } else {
                messageMetadata = new HashSet<>(this.messageMetadata);
            }
            if (messageContents.size() == 1) {
                return new SingleMessageContentMessageChainImpl(messageContents.get(0), messageMetadata);
            } else {
                return new MultipleMessageContentsMessageChainImpl(new ArrayList<>(messageContents), messageMetadata);
            }
        }
    }
    
    private static final MessageContent[] EMPTY_MESSAGE_CONTENT_ARRAY = {};
    
    private final List<MessageContent> messageContents;
    private MessageContent[] toArrayCache;
    
    private Integer hashCodeCache;
    
    public MultipleMessageContentsMessageChainImpl(List<MessageContent> messageContents, Set<MessageMetadata> messageMetadata) {
        super(messageMetadata);
        
        Preconditions.checkNotNull(messageContents, "Message contents are null!");
        Preconditions.checkArgument(!messageContents.isEmpty(), "Message contents are empty!");
    
        this.messageContents = Collections.unmodifiableList(messageContents);
    }
    
    public MultipleMessageContentsMessageChainImpl(List<MessageContent> messageContents) {
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
            return new SingleMessageContentMessageChainImpl(messageContents.get(begin));
        } else {
            return new MultipleMessageContentsMessageChainImpl(messageContents.subList(begin, end));
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
