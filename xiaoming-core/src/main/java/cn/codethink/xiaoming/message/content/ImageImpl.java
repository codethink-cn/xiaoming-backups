package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.common.Resource;
import com.google.common.base.Preconditions;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class ImageImpl
    implements Image {
    
    private final Resource resource;
    private final int width;
    private final int height;
    private final int size;
    private final ImageType imageType;
    
    private Integer hashCodeCache;
    
    public ImageImpl(Resource resource, int width, int height, int size, ImageType imageType) {
        Preconditions.checkNotNull(resource, "Resource is null!");
        Preconditions.checkNotNull(imageType, "Image type is null!");
        Preconditions.checkArgument(width < 0, "Width must be greater than or equals to 0!");
        Preconditions.checkArgument(size < 0, "Size must be greater than or equals to 0!");
        Preconditions.checkArgument(height < 0, "Height must be greater than or equals to 0!");
        
        this.resource = resource;
        this.width = width;
        this.height = height;
        this.size = size;
        this.imageType = imageType;
    }
    
    public ImageImpl(Resource resource) throws IOException {
        final ImageInputStream inputStream = ImageIO.createImageInputStream(resource.open());
        final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(inputStream);
    
        ImageType imageType = null;
        int width = 0;
        int height = 0;
        int size = 0;
        while (imageReaders.hasNext()) {
            final ImageReader imageReader = imageReaders.next();
            final String type = imageReader.getFormatName().toUpperCase();
            if (imageType == null) {
                imageType = ImageTypeImpl.createInstance(type);
                width = imageReader.getWidth(0);
                height = imageReader.getHeight(0);
                size = 0;
            } else {
                throw new IllegalArgumentException("Multiple images present!");
            }
        }
        
        this.imageType = imageType;
        this.resource = resource;
        this.width = width;
        this.height = height;
        this.size = size;
    }
    
    @Override
    public Resource getResource() {
        return resource;
    }
    
    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public ImageType getImageType() {
        return imageType;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImageImpl image = (ImageImpl) o;
        return width == image.width
            && height == image.height
            && size == image.size
            && Objects.equals(resource, image.resource)
            && Objects.equals(imageType, image.imageType);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(resource, width, height, size, imageType);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        return "ImageImpl{" +
            "resource=" + resource +
            ", width=" + width +
            ", height=" + height +
            ", size=" + size +
            ", imageType=" + imageType +
            ", hashCodeCache=" + hashCodeCache +
            '}';
    }
}
