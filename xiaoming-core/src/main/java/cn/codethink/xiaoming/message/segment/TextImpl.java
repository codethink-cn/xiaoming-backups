package cn.codethink.xiaoming.message.segment;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class TextImpl
    implements Text {
    
    private final String text;
    
    private Integer hashCodeCache;
    
    public TextImpl(String text) {
        Preconditions.checkNotNull(text, "Text is null!");
        Preconditions.checkArgument(!text.isEmpty(), "Text is empty!");
        
        this.text = text;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextImpl text1 = (TextImpl) o;
        return Objects.equals(text, text1.text);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(text);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
