package cn.codethink.xiaoming.message;

/**
 * <h1>消息异常</h1>
 *
 * @author Chuanwise
 */
public class MessageException
    extends RuntimeException {
    
    public MessageException() {
    }
    
    public MessageException(String message) {
        super(message);
    }
    
    public MessageException(Throwable cause) {
        super(cause);
    }
    
    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
