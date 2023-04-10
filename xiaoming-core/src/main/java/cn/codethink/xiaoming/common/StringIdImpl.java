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
