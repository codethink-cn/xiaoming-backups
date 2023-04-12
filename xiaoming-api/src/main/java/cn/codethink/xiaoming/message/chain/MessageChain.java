package cn.codethink.xiaoming.message.chain;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.message.Message;
import cn.codethink.xiaoming.message.content.MessageContent;

import java.util.List;

/**
 * <h1>消息链</h1>
 *
 * <p>消息链是</p>
 *
 * @author Chuanwise
 */
public interface MessageChain
    extends Message, List<MessageContent> {
    
    /**
     * 消息链构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 添加消息片段
         *
         * @param messageContent 消息片段
         * @return 消息链构建器
         */
        Builder plus(MessageContent messageContent);
    
        /**
         * 添加文本消息
         *
         * @param text 文本信息
         * @return 消息链构建器
         */
        Builder plus(String text);
    
        /**
         * 添加若干消息
         *
         * @param messageContents 若干消息
         * @return 消息链构建器
         */
        Builder plus(MessageContent... messageContents);
    
        /**
         * 添加消息链
         *
         * @param messageChain 消息链
         * @return 消息链构建器
         */
        Builder plus(MessageChain messageChain);
    
        /**
         * 构建消息链
         *
         * @return 消息链
         */
        MessageChain build();
    }
    
    /**
     * 获取消息链构建器
     *
     * @return 消息链构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getMessageChainBuilder();
    }
    
    /**
     * 通过若干消息片段构建消息链
     *
     * @param messageContents 若干消息片段
     * @return 消息链
     */
    static MessageChain of(MessageContent... messageContents) {
        return APIFactory.getInstance().getMessageChain(messageContents);
    }
    
    /**
     * 通过消息片段构建消息链
     *
     * @param messageContent 消息片段
     * @return 消息链
     */
    static MessageChain of(MessageContent messageContent) {
        return APIFactory.getInstance().getMessageChain(messageContent);
    }
}
