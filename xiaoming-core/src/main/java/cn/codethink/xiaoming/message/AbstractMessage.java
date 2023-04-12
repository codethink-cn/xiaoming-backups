package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import com.google.common.base.Preconditions;

public abstract class AbstractMessage
    implements Message {
    
    private volatile String messageCodeCache;
    
    @Override
    public String serializeToMessageCode() {
        return serializeToMessageCode(SerializingConfiguration.getInstance());
    }
    
    @Override
    public String serializeToMessageCode(SerializingConfiguration configuration) {
        Preconditions.checkNotNull(configuration, "Serializing configuration is null!");
        
        if (configuration.equals(SerializingConfiguration.getInstance())) {
            if (messageCodeCache == null) {
                synchronized (this) {
                    if (messageCodeCache == null) {
                        messageCodeCache = MessageCodeImpl.serialize(this);
                    }
                }
            }
            return messageCodeCache;
        } else {
            return MessageCodeImpl.serialize(this, configuration);
        }
    }
}
