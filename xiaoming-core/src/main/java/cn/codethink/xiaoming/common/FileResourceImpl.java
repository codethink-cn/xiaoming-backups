package cn.codethink.xiaoming.common;

import com.google.common.base.Preconditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FileResourceImpl
    implements Resource {
    
    private final File file;
    
    private Integer hashCodeCache;
    private String toStringCache;
    
    public FileResourceImpl(File file) {
        Preconditions.checkNotNull(file, "File is null!");
        
        this.file = file;
    }
    
    @Override
    public InputStream open() throws IOException {
        return new FileInputStream(file);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FileResourceImpl that = (FileResourceImpl) o;
        return Objects.equals(file, that.file);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(file);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = file.toString();
        }
        return toStringCache;
    }
}
