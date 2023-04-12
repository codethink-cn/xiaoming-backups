package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.common.Time;
import cn.codethink.xiaoming.message.chain.MessageChain;

import java.util.List;

/**
 * <h1>转发消息</h1>
 *
 * @author Chuanwise
 */
public interface Forward
    extends MessageContent {
    
    /**
     * 转发消息内容
     *
     * @author Chuanwise
     */
    interface Content {
        
        /**
         * 获取发送人 Id
         *
         * @return 发送人 Id
         */
        Id getSenderId();
        
        /**
         * 获取发送时间
         *
         * @return 发送时间
         */
        Time getTime();
        
        /**
         * 获取发送人名称
         *
         * @return 发送人名称
         */
        String getSenderName();
        
        /**
         * 获取消息链
         *
         * @return 消息链
         */
        MessageChain getMessageChain();
    }
    
    /**
     * 获取预览
     *
     * @return 预览
     */
    List<String> getPreview();
    
    /**
     * 获取标题
     *
     * @return 标题
     */
    List<String> getTitle();
    
    /**
     * 获取描述
     *
     * @return 描述
     */
    List<String> getDescription();
    
    /**
     * 获取内容
     *
     * @return 内容
     */
    List<Content> getContents();
}
