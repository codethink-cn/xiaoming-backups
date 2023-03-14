package cn.codethink.xiaoming.common;

import cn.codethink.common.util.Preconditions;

import java.util.Objects;

public class StringIdImpl
    implements StringId {
    
    public static final StringIdImpl EMPTY = new StringIdImpl("");
    
    private final String value;
    
    private StringIdImpl(String value) {
        this.value = value;
    }
    
    public static StringId of(String value) {
        Preconditions.objectNonNull(value, "Value");
        if (value.isEmpty()) {
            return EMPTY;
        } else {
            return new StringIdImpl(value);
        }
    }
    
    @Override
    public int compareTo(StringId o) {
        Preconditions.objectNonNull(o, "String id");
        return value.compareTo(o.toString());
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
