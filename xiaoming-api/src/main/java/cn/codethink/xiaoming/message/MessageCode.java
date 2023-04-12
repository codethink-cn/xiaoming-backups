package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import com.google.common.base.Preconditions;

import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;

/**
 * <h1>消息码</h1>
 *
 * @author Chuanwise
 */
public final class MessageCode {
    private MessageCode() {
        throw new NoSuchElementException("No " + MessageCode.class.getName() + " instances for you!");
    }
    
    /**
     * 反序列化消息码
     *
     * @param messageCode 消息码
     * @return 消息
     */
    public static Message deserializeFromMessageCode(String messageCode) {
        Preconditions.checkNotNull(messageCode, "String is null!");
        Preconditions.checkArgument(!messageCode.isEmpty(), "String is empty!");
        
        return deserializeFromMessageCode(new StringReader(messageCode));
    }
    
    /**
     * 反序列化消息码
     *
     * @param messageCode   消息码
     * @param configuration 反序列化配置
     * @return 消息
     */
    public static Message deserializeFromMessageCode(String messageCode, DeserializingConfiguration configuration) {
        Preconditions.checkNotNull(messageCode, "String is null!");
        Preconditions.checkArgument(!messageCode.isEmpty(), "String is empty!");
        Preconditions.checkNotNull(configuration, "Deserializing configuration is empty!");
        
        return deserializeFromMessageCode(new StringReader(messageCode), configuration);
    }
    
    /**
     * 反序列化消息码
     *
     * @param messageCodeReader 消息码流
     * @return 消息
     */
    public static Message deserializeFromMessageCode(Reader messageCodeReader) {
        return APIFactory.getInstance().deserialize(messageCodeReader);
    }
    
    /**
     * 反序列化消息码
     *
     * @param messageCodeReader 消息码流
     * @param configuration     反序列化配置
     * @return 消息
     */
    public static Message deserializeFromMessageCode(Reader messageCodeReader, DeserializingConfiguration configuration) {
        return APIFactory.getInstance().deserialize(messageCodeReader, configuration);
    }
}
