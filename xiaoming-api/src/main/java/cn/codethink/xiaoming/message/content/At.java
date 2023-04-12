package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.common.Id;

/**
 * <h1>提及</h1>
 *
 * @author Chuanwise
 */
public interface At
    extends MessageContent {
    
    /**
     * 构造提及消息
     *
     * @param id 提及目标
     * @return 提及消息
     */
    static At of(Id id) {
        return APIFactory.getInstance().getAt(id);
    }
    
    /**
     * 获取提及目标
     *
     * @return 提及目标
     */
    Id getId();
}
