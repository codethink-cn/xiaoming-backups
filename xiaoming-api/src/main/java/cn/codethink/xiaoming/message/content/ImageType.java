package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;

import java.util.Set;

/**
 * <h1>图片类型</h1>
 *
 * @author Chuanwise
 */
public interface ImageType {
    
    /**
     * PNG
     */
    ImageType PNG = of("PNG");
    
    /**
     * BMP
     */
    ImageType BMP = of("BMP");
    
    /**
     * JPG
     */
    ImageType JPG = of("JPG");
    
    /**
     * GIF
     */
    ImageType GIF = of("GIF");
    
    /**
     * WEBP
     */
    ImageType WEBP = of("WEBP");
    
    /**
     * 获取图片类型
     *
     * @param imageType 图片类型
     * @return 图片类型
     */
    static ImageType of(String imageType) {
        return APIFactory.getInstance().getImageType(imageType);
    }
    
    /**
     * 获取支持的所有图片类型
     *
     * @return 图片类型
     */
    static Set<ImageType> getInstances() {
        return APIFactory.getInstance().getImageTypes();
    }
}
