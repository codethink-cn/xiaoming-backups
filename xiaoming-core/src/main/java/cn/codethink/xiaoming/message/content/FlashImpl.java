package cn.codethink.xiaoming.message.content;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class FlashImpl
    extends AbstractMessageContent
    implements Flash {
    
    private static final String DEFAULT_SUMMARY = "[闪照]";
    private final Image image;
    
    private Integer hashCodeCache;
    
    public FlashImpl(Image image) {
        Preconditions.checkNotNull(image, "Image is null!");
        
        this.image = image;
    }
    
    @Override
    public String summarize() {
        return DEFAULT_SUMMARY;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FlashImpl flash = (FlashImpl) o;
        return Objects.equals(image, flash.image);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(image);
        }
        return hashCodeCache;
    }
}
