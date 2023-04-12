package cn.codethink.xiaoming.common;

import com.google.common.base.Preconditions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class URLResourceImpl
    implements Resource {
    
    private final URL url;
    
    private Integer hashCodeCache;
    private String toStringCache;
    
    public URLResourceImpl(URL url) {
        Preconditions.checkNotNull(url, "URL is null!");
        
        this.url = url;
    }
    
    @Override
    public InputStream open() throws IOException {
        return url.openStream();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final URLResourceImpl that = (URLResourceImpl) o;
        return Objects.equals(url, that.url);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(url);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = url.toString();
        }
        return toStringCache;
    }
    
    public URL getUrl() {
        return url;
    }
}
