package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>闪照</h1>
 *
 * @author Chuanwise
 */
public interface Flash
    extends MessageContent {
    
    /**
     * 构造闪照
     *
     * @param image 图片
     * @return 闪照
     */
    static Flash of(Image image) {
        return APIFactory.getInstance().getFlash(image);
    }
    
    /**
     * 获取闪照内容
     *
     * @return 闪照内容
     */
    Image getImage();
}
