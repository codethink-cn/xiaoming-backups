package cn.codethink.xiaoming.message.element;

import cn.codethink.xiaoming.common.Id;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class AtImpl
    implements At {
    
    private final Id id;
    
    private String toStringCache;
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
        AtImpl at = (AtImpl) o;
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
    public String toString() {
        if (toStringCache == null) {
            toStringCache = "@" + id;
        }
        return toStringCache;
    }
}
