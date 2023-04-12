package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>提及所有</h1>
 *
 * @author Chuanwise
 */
public interface AtAll
    extends MessageContent {
    
    /**
     * 获取提及所有消息
     *
     * @return 提及所有消息
     */
    static AtAll getInstance() {
        return APIFactory.getInstance().getAtAll();
    }
}
