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

package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.adapter.Adapter;
import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.logger.factory.LoggerFactory;
import cn.codethink.xiaoming.logger.factory.Slf4jLoggerFactoryImpl;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.MessageCodeImpl;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MultipleMessageContentsMessageChainImpl;
import cn.codethink.xiaoming.message.chain.SingleMessageContentMessageChainImpl;
import cn.codethink.xiaoming.message.content.*;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfigurationImpl;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.message.serializer.SerializingConfigurationImpl;
import cn.codethink.xiaoming.util.InterpretersImpl;
import com.google.common.base.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class APIImpl
    implements API {
    
    @Override
    public Time getTimeOfMilliseconds(long milliseconds) {
        return new TimeImpl(milliseconds);
    }
    
    @Override
    public Time getTimeOfSeconds(long seconds) {
        return new TimeImpl(TimeUnit.MILLISECONDS.toSeconds(seconds));
    }
    
    @Override
    public NumericalId getZeroNumericalId() {
        return NumericalIdImpl.ZERO;
    }
    
    @Override
    public NumericalId getNumericalId(String value) {
        return NumericalIdImpl.of(value);
    }
    
    @Override
    public NumericalId getNumericalId(byte value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(short value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(int value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(long value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(float value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(double value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public StringId getEmptyStringId() {
        return StringIdImpl.EMPTY;
    }
    
    @Override
    public StringId getStringId(String value) {
        return StringIdImpl.of(value);
    }
    
    @Override
    public Adapter getIM(String name) {
        return AdapterImpl.getInstance(name);
    }
    
    @Override
    public Set<Adapter> getIMs() {
        return AdapterImpl.getInstances();
    }
    
    @Override
    public Text getText(Object object) {
        final String string;
        if (object == null) {
            string = "null";
        } else if (object instanceof String) {
            string = (String) object;
        } else {
            string = Objects.toString(object);
        }
        return TextImpl.of(string);
    }
    
    @Override
    public At getAt(Id id) {
        return new AtImpl(id);
    }
    
    @Override
    public AtAll getAtAll() {
        return AtAllImpl.getInstance();
    }
    
    @Override
    public SerializingConfiguration getSerializingConfiguration() {
        return SerializingConfigurationImpl.getInstance();
    }
    
    @Override
    public SerializingConfiguration.Builder getSerializingConfigurationBuilder() {
        return new SerializingConfigurationImpl.BuilderImpl();
    }
    
    @Override
    public DeserializingConfiguration getDeserializingConfiguration() {
        return DeserializingConfigurationImpl.getInstance();
    }
    
    @Override
    public DeserializingConfiguration.Builder getDeserializingConfigurationBuilder() {
        return new DeserializingConfigurationImpl.BuilderImpl();
    }
    
    @Override
    public String serialize(Message message) {
        return MessageCodeImpl.serialize(message);
    }
    
    @Override
    public String serialize(Message message, SerializingConfiguration configuration) {
        return MessageCodeImpl.serialize(message, configuration);
    }
    
    @Override
    public Message deserialize(Reader reader) {
        return MessageCodeImpl.deserialize(reader);
    }
    
    @Override
    public Message deserialize(Reader reader, DeserializingConfiguration configuration) {
        return MessageCodeImpl.deserialize(reader, configuration);
    }
    
    
    @Override
    public MessageChain getMessageChain(MessageContent... messageContents) {
        Preconditions.checkNotNull(messageContents, "Message contents are null!");
        if (messageContents.length == 0) {
            throw new IllegalArgumentException("No message content present!");
        }
        
        for (int i = 0; i < messageContents.length; i++) {
            if (messageContents[i] == null) {
                throw new NullPointerException("Null message content at position " + (i + 1));
            }
        }
        if (messageContents.length == 1) {
            return new SingleMessageContentMessageChainImpl(messageContents[0]);
        } else {
            return MessageChain.builder()
                .plus(messageContents)
                .build();
        }
    }
    
    @Override
    public MessageChain getMessageChain(MessageContent messageContent) {
        Preconditions.checkNotNull(messageContent, "Message content is null!");
        return new SingleMessageContentMessageChainImpl(messageContent);
    }
    
    @Override
    public MessageChain.Builder getMessageChainBuilder() {
        return new MultipleMessageContentsMessageChainImpl.BuilderImpl();
    }
    
    @Override
    public Resource getFileResource(File file) {
        return new FileResourceImpl(file);
    }
    
    @Override
    public Resource getURLResource(URL url) {
        return new URLResourceImpl(url);
    }
    
    @Override
    public Resource getInputStreamResource(InputStream inputStream) {
        return new InputStreamResourceImpl(inputStream);
    }
    
    @Override
    public Resource getBytesResource(byte[] bytes) {
        return new BytesResourceImpl(bytes);
    }
    
    @Override
    public Resource getStaticResource(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = APIImpl.class.getClassLoader();
        }
        return new StaticResourceImpl(classLoader, path);
    }
    
    @Override
    public Resource getStaticResource(ClassLoader classLoader, String path) {
        return new StaticResourceImpl(classLoader, path);
    }
    
    @Override
    public Image getImage(Resource resource) {
        return new ImageImpl(resource, 0, 0, 0, null);
    }
    
    @Override
    public Image getImage(Resource resource, int width, int height, int size, ImageType imageType) {
        return new ImageImpl(resource, width, height, size, imageType);
    }
    
    @Override
    public ImageType getImageType(String imageType) {
        return ImageTypeImpl.getInstance(imageType);
    }
    
    @Override
    public Set<ImageType> getImageTypes() {
        return ImageTypeImpl.getInstances();
    }
    
    @Override
    public Flash getFlash(Image image) {
        return new FlashImpl(image);
    }
    
    @Override
    public Interpreter getInterpreter() {
        return InterpretersImpl.getInstance();
    }
    
    @Override
    public LoggerFactory getSlf4jLoggerFactory() {
        return Slf4jLoggerFactoryImpl.getInstance();
    }
}