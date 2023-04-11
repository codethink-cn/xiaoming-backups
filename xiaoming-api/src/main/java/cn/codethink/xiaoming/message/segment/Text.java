package cn.codethink.xiaoming.message.segment;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>文本消息</h1>
 *
 * @author Chuanwise
 */
public interface Text
    extends MessageSegment {
    
    /**
     * 构造文本消息
     *
     * @param text 文本
     * @return 文本消息
     */
    static Text of(String text) {
        return APIFactory.getInstance().getText(text);
    }
    
    /**
     * 获取文本数据
     *
     * @return 文本数据
     */
    @Override
    String toString();
}