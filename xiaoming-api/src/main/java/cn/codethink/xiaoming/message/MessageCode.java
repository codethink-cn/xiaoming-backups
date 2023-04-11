package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
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
    
    public static String serialize(Message message) {
        return APIFactory.getInstance().serialize(message);
    }
    
    public static String serialize(Message message, SerializingConfiguration configuration) {
        return APIFactory.getInstance().serialize(message, configuration);
    }
    
    public static Message deserialize(String string) {
        Preconditions.checkNotNull(string, "String is null!");
        Preconditions.checkArgument(!string.isEmpty(), "String is empty!");
        
        return deserialize(new StringReader(string));
    }
    
    public static Message deserialize(String string, DeserializingConfiguration configuration) {
        Preconditions.checkNotNull(string, "String is null!");
        Preconditions.checkArgument(!string.isEmpty(), "String is empty!");
        Preconditions.checkNotNull(configuration, "Deserializing configuration is empty!");
        
        return deserialize(new StringReader(string), configuration);
    }
    
    public static Message deserialize(Reader reader) {
        return APIFactory.getInstance().deserialize(reader);
    }
    
    // TODO comments
    public static Message deserialize(Reader reader, DeserializingConfiguration configuration) {
        return APIFactory.getInstance().deserialize(reader, configuration);
    }
}
