package cn.codethink.xiaoming.message.content;

/**
 * <h1>表情</h1>
 *
 * @author Chuanwise
 */
public interface Face
    extends MessageContent {
    
    /**
     * 获取表情名
     *
     * @return 表情名
     */
    String getName();
}
