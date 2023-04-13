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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class InputStreamResourceImpl
    implements Resource {
    
    private final InputStream inputStream;
    private volatile boolean opened = false;
    
    private Integer hashCodeCache;
    private String toStringCache;
    
    public InputStreamResourceImpl(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "Input stream is null!");
        
        this.inputStream = inputStream;
    }
    
    @Override
    public InputStream open() throws IOException {
        if (opened) {
            throw new UnsupportedOperationException("Resource can only be open once!");
        }
        opened = true;
        return inputStream;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InputStreamResourceImpl that = (InputStreamResourceImpl) o;
        return Objects.equals(inputStream, that.inputStream);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(inputStream);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = inputStream.toString();
        }
        return toStringCache;
    }
}
