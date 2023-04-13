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

package cn.codethink.xiaoming.common;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

public class StringIdImpl
    implements StringId {
    
    public static final StringIdImpl EMPTY = new StringIdImpl("");
    
    private final String value;
    
    private StringIdImpl(String value) {
        this.value = value;
    }
    
    public static StringId of(String value) {
        Preconditions.checkNotNull(value, "Value is null!");
        
        if (value.isEmpty()) {
            return EMPTY;
        } else {
            return new StringIdImpl(value);
        }
    }
    
    @Override
    public int compareTo(@Nonnull StringId id) {
        Preconditions.checkNotNull(id, "String id is null!");
        return value.compareTo(id.toString());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StringIdImpl stringId = (StringIdImpl) o;
        return value.equals(stringId.toString());
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public String toString() {
        return value;
    }
}
