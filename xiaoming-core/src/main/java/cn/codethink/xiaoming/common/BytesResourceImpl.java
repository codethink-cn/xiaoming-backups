package cn.codethink.xiaoming.common;

import com.google.common.base.Preconditions;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class BytesResourceImpl
    implements Resource {
    
    private final byte[] bytes;
    
    private Integer hashCodeCache;
    private String toStringCache;
    
    public BytesResourceImpl(byte[] bytes) {
        Preconditions.checkNotNull(bytes, "Bytes are null!");
        
        this.bytes = bytes;
    }
    
    @Override
    public InputStream open() {
        return new ByteArrayInputStream(bytes);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BytesResourceImpl that = (BytesResourceImpl) o;
        return Arrays.equals(bytes, that.bytes);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Arrays.hashCode(bytes);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = Base64.getEncoder().encodeToString(bytes);
        }
        return toStringCache;
    }
    
    public byte[] getBytes() {
        return bytes;
    }
}
