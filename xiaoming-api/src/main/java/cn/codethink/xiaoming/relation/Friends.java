package cn.codethink.xiaoming.relation;

import cn.codethink.xiaoming.common.BotObject;
import cn.codethink.xiaoming.common.Id;

/**
 * <h1>好友们</h1>
 *
 * <p>用于获取具体好友的组件</p>
 *
 * @author Chuanwise
 */
public interface Friends
    extends Iterable<Friend>, BotObject {
    
    /**
     * 获取好友
     *
     * @param id 好友 ID
     * @return 好友
     */
    Friend get(Id id);
    
    
}
