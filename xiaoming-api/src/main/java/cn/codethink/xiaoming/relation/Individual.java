package cn.codethink.xiaoming.relation;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.common.BotObject;

/**
 * <h1>个体</h1>
 *
 * <p>个体指一对一的关系，可能为机器人自己 {@link Bot}，也可能为用户 {@link User}。</p>
 *
 * <p>个体不一定是可知的（如匿名群友），因此没有发送消息相关的 API。</p>
 *
 * @author Chuanwise
 */
public interface Individual
    extends Relation, BotObject {
}
