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
