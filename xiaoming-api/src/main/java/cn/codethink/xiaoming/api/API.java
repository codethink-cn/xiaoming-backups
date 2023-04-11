package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.element.*;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * <h1>小明内部</h1>
 *
 * <p>小明内部是 xiaoming-api 调用 xiaoming-core 的桥梁。</p>
 *
 * @author Chuanwise
 */
public interface API {
    Time getTimeOfMilliseconds(long milliseconds);
    Time getTimeOfSeconds(long seconds);
    
    NumericalId getZeroNumericalId();
    NumericalId getNumericalId(String value);
    NumericalId getNumericalId(byte value);
    NumericalId getNumericalId(short value);
    NumericalId getNumericalId(int value);
    NumericalId getNumericalId(long value);
    NumericalId getNumericalId(float value);
    NumericalId getNumericalId(double value);
    
    StringId getEmptyStringId();
    StringId getStringId(String value);
    
    Text getText(String text);
    At getAt(Id id);
    AtAll getAtAll();
    
    SerializingConfiguration getSerializingConfiguration();
    SerializingConfiguration.Builder getSerializingConfigurationBuilder();
    
    DeserializingConfiguration getDeserializingConfiguration();
    DeserializingConfiguration.Builder getDeserializingConfigurationBuilder();
    
    String serialize(Message message);
    String serialize(Message message, SerializingConfiguration configuration);
    
    Message deserialize(Reader reader);
    Message deserialize(Reader reader, DeserializingConfiguration configuration);
    
    MessageChain getMessageChain(MessageElement... messageElements);
    MessageChain getMessageChain(MessageElement messageElement);
    MessageChain.Builder getMessageChainBuilder();
    
    Resource getFileResource(File file);
    Resource getURLResource(URL url);
    Resource getInputStreamResource(InputStream inputStream);
    Resource getBytesResource(byte[] bytes);
    Resource getStaticResource(String path);
    Resource getStaticResource(ClassLoader classLoader, String path);
    
    Image getImage(Resource resource);
}
