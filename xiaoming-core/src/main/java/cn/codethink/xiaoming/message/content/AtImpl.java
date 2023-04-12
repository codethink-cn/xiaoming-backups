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
