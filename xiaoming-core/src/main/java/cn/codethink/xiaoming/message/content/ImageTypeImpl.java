package cn.codethink.xiaoming.message.content;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImageTypeImpl
    implements ImageType {
    
    private static final Map<String, ImageType> INSTANCES = new ConcurrentHashMap<>();
    private static final Set<ImageType> INSTANCES_SET = new HashSet<>();
    private static final Set<ImageType> UNMODIFIABLE_INSTANCES_SET = Collections.unmodifiableSet(INSTANCES_SET);
    
    public static ImageType getInstance(String imageType) {
        Preconditions.checkNotNull(imageType, "Image type is null!");
        
        final ImageType type = INSTANCES.get(imageType);
        if (type == null) {
            throw new NoSuchElementException("No such image type: " + imageType);
        }
        return type;
    }
    
    public static Set<ImageType> getInstances() {
        return UNMODIFIABLE_INSTANCES_SET;
    }
    
    public static ImageType createInstance(String imageType) {
        Preconditions.checkNotNull(imageType, "Image type is null!");
        
        ImageType type = INSTANCES.get(imageType);
        if (type == null) {
            synchronized (INSTANCES) {
                type = INSTANCES.get(imageType);
                if (type == null) {
                    type = new ImageTypeImpl(imageType);
                    INSTANCES.put(imageType, type);
                    INSTANCES_SET.add(type);
                }
            }
        }
        return type;
    }
    
    private final String name;
    
    private ImageTypeImpl(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
        
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
