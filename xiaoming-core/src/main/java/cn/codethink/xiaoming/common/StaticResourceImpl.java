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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StaticResourceImpl
    implements Resource {
    
    private final String path;
    private final ClassLoader classLoader;
    
    private Integer hashCodeCache;
    
    public StaticResourceImpl(ClassLoader classLoader, String path) {
        Preconditions.checkNotNull(path, "URL is null!");
        Preconditions.checkNotNull(classLoader, "Class loader is null!");
        
        this.path = path;
        this.classLoader = classLoader;
    }
    
    @Override
    public InputStream open() throws IOException {
        final InputStream stream = classLoader.getResourceAsStream(path);
        if (stream == null) {
            throw new IOException("Resource located in " + path + " doesn't exists!");
        }
        return stream;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StaticResourceImpl that = (StaticResourceImpl) o;
        return Objects.equals(path, that.path);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(path);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        return path;
    }
}
