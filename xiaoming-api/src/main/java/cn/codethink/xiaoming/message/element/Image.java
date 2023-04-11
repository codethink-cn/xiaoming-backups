package cn.codethink.xiaoming.message.element;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.common.Resource;

/**
 * <h1>图像</h1>
 *
 * @author Chuanwise
 */
public interface Image {
    
    /**
     * 构造图像
     *
     * @param resource 资源
     * @return 图像
     */
    static Image of(Resource resource) {
        return APIFactory.getInstance().getImage(resource);
    }
    
    /**
     * 获取图像资源
     *
     * @return 图像资源
     */
    Resource getResource();
}
