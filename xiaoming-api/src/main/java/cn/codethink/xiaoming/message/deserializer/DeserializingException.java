package cn.codethink.xiaoming.message.deserializer;

import cn.codethink.xiaoming.expression.ExpressionException;
import cn.codethink.xiaoming.message.MessageException;

/**
 * <h1>反序列化异常</h1>
 *
 * @author Chuanwise
 */
public class DeserializingException
    extends MessageException {
    
    public DeserializingException() {
    }
    
    public DeserializingException(String message) {
        super(message);
    }
    
    public DeserializingException(Throwable cause) {
        super(cause);
    }
    
    public DeserializingException(String message, Throwable cause) {
        super(message, cause);
    }
}
