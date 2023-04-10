package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.segment.MessageSegment;

/**
 * <h1>消息链</h1>
 *
 * <p>消息链是</p>
 *
 * @author Chuanwise
 */
public interface MessageChain
    extends Message, Iterable<MessageSegment> {
}
