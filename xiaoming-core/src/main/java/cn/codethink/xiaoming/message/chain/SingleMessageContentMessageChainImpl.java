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
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.iterators.SingletonIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SingleMessageContentMessageChainImpl
    extends AbstractMessageChain
    implements MessageChain {
    
    private final MessageContent messageContent;

    private MessageContent[] toArrayCache;
    
    public SingleMessageContentMessageChainImpl(MessageContent messageContent) {
        this(messageContent, Collections.emptySet());
    }
    
    public SingleMessageContentMessageChainImpl(MessageContent messageContent, Set<MessageMetadata> messageMetadata) {
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
