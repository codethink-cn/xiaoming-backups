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
