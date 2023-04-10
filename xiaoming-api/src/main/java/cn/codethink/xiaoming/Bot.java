package cn.codethink.xiaoming;

import cn.codethink.xiaoming.common.IdObject;
import cn.codethink.xiaoming.relation.Individual;
import cn.codethink.xiaoming.relation.RelationManager;

/**
 * <h1>机器人</h1>
 *
 * @author Chuanwise
 */
public interface Bot
    extends Individual, IdObject {
    
    /**
     * 获取关系管理器
     *
     * @return 关系管理器
     */
    RelationManager getRelationManager();
}
