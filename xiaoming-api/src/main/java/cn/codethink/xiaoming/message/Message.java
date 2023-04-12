package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.metadata.MessageMetadata;
import cn.codethink.xiaoming.relation.Relation;

/**
 * <h1>消息</h1>
 *
 * <p>消息是在即时通讯软件机器人中一切可发送和可接收的消息的总接口。</p>
 *
 * @author Chuanwise
 * @see MessageChain
 * @see MessageContent
 */
public interface Message {
    
    /**
     * 概括消息
     *
     * @return 消息概要
     */
    String summarize();
    
    /**
     * 概括消息
     *
     * @param relation 关系
     * @return 消息概要
     */
    String summarize(Relation relation);
    
    /**
     * 获取消息元数据
     *
     * @param metadataClass 消息元数据
     * @param <T>           消息元数据类型
     * @return 消息元数据或 null
     */
    <T extends MessageMetadata> T getMetadata(Class<T> metadataClass);
    
    /**
     * 获取消息元数据
     *
     * @param metadataClass 消息元数据
     * @param <T>           消息元数据类型
     * @return 消息元数据
     */
    <T extends MessageMetadata> T getMetadataOrFail(Class<T> metadataClass);
}
