package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.content.MessageContent;

/**
 * <h1>消息</h1>
 *
 * <p>消息是在即时通讯软件机器人中一切可发送和可接收的消息的总接口。</p>
 *
 * @author Chuanwise
 * @see MessageChain
 * @see MessageContent
 */
public interface Message
    extends MessageElement {
    
    /**
     * 概括消息
     *
     * @return 消息概要
     */
    String summarize();
}
