package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.expression.ExpressionException;
import cn.codethink.xiaoming.message.MessageException;

/**
 * <h1>序列化异常</h1>
 *
 * @author Chuanwise
 */
public class SerializingException
    extends MessageException {
    
    public SerializingException() {
    }
    
    public SerializingException(String message) {
        super(message);
    }
    
    public SerializingException(Throwable cause) {
        super(cause);
    }
    
    public SerializingException(String message, Throwable cause) {
        super(message, cause);
    }
}
