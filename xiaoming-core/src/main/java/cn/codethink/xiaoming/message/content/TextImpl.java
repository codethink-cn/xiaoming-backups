package cn.codethink.xiaoming.message.content;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class TextImpl
    extends AbstractMessageContent
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
        final TextImpl that = (TextImpl) o;
        return Objects.equals(text, that.text);
    }
    
    @Override
    public String getText() {
        return text;
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(text);
        }
        return hashCodeCache;
    }
    
    @Override
    public String summarize() {
        return text;
    }
}
