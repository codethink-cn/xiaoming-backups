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
