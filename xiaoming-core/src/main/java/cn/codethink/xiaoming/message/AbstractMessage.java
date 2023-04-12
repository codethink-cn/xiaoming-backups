package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.map.LRUMap;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractMessage
    implements Message {
    
    private volatile Map<SerializingConfiguration, String> messageCodeCache;
    
    @Override
    public String serializeToMessageCode() {
        return serializeToMessageCode(SerializingConfiguration.getInstance());
    }
    
    @Override
    public String serializeToMessageCode(SerializingConfiguration configuration) {
        Preconditions.checkNotNull(configuration, "Serializing configuration is null!");
        
        if (messageCodeCache == null) {
            synchronized (this) {
                if (messageCodeCache == null) {
                    messageCodeCache = Collections.synchronizedMap(new LRUMap<>());
                }
            }
        }
        return messageCodeCache.computeIfAbsent(configuration, conf -> MessageCodeImpl.serialize(this, conf));
    }
}
