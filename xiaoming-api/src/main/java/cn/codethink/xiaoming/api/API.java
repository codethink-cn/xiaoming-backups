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
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.content.*;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Set;

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
    
    Adapter getIM(String name);
    Set<Adapter> getIMs();
    
    Text getText(Object object);
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
    
    MessageChain getMessageChain(MessageContent... messageContents);
    MessageChain getMessageChain(MessageContent messageContent);
    MessageChain.Builder getMessageChainBuilder();
    
    Resource getFileResource(File file);
    Resource getURLResource(URL url);
    Resource getInputStreamResource(InputStream inputStream);
    Resource getBytesResource(byte[] bytes);
    Resource getStaticResource(String path);
    Resource getStaticResource(ClassLoader classLoader, String path);
    
    Image getImage(Resource resource);
    Image getImage(Resource resource, int width, int height, int size, ImageType imageType);
    ImageType getImageType(String imageType);
    Set<ImageType> getImageTypes();
    
    Flash getFlash(Image image);
    
    Interpreter getInterpreter();
    
    LoggerFactory getSlf4jLoggerFactory();
}
