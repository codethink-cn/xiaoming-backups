package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.MessageCodeImpl;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MessageChainImpl;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfigurationImpl;
import cn.codethink.xiaoming.message.element.*;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.message.serializer.SerializingConfigurationImpl;
import com.google.common.base.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
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
    public Text getText(String text) {
        return new TextImpl(text);
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
    public MessageChain getMessageChain(MessageElement... messageElements) {
        Preconditions.checkNotNull(messageElements, "Message elements are null!");
        if (messageElements.length == 0) {
            throw new IllegalArgumentException("No message element present!");
        }
        
        for (int i = 0; i < messageElements.length; i++) {
            if (messageElements[i] == null) {
                throw new NullPointerException("Null message element at position " + (i + 1));
            }
        }
        if (messageElements.length == 1) {
            return new MessageChainImpl(Collections.singletonList(messageElements[0]));
        } else {
            return new MessageChainImpl(Arrays.asList(messageElements));
        }
    }
    
    @Override
    public MessageChain getMessageChain(MessageElement messageElement) {
        Preconditions.checkNotNull(messageElement, "Message element is null!");
        return new MessageChainImpl(Collections.singletonList(messageElement));
    }
    
    @Override
    public MessageChain.Builder getMessageChainBuilder() {
        return new MessageChainImpl.BuilderImpl();
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
        // TODO
        return null;
    }
}