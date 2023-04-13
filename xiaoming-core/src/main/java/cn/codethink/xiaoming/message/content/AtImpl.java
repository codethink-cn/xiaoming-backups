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

package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.message.AbstractMessage;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class AtImpl
    extends AbstractMessageContent
    implements At {
    
    private final Id id;
    
    private String summarizeCache;
    private Integer hashCodeCache;
    
    public AtImpl(Id id) {
        Preconditions.checkNotNull(id, "Id is null!");
        
        this.id = id;
    }
    
    @Override
    public Id getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AtImpl at = (AtImpl) o;
        return Objects.equals(id, at.id);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(id);
        }
        return hashCodeCache;
    }
    
    @Override
    public String summarize() {
        if (summarizeCache == null) {
            summarizeCache = "@" + id;
        }
        return summarizeCache;
    }
}
