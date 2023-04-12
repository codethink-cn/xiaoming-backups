package cn.codethink.xiaoming.util;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.expression.lang.Interpreter;

import java.util.NoSuchElementException;

/**
 * <h1>解释器工具</h1>
 *
 * @author Chuanwise
 */
public class Interpreters {
    private Interpreters() {
        throw new NoSuchElementException("No " + Interpreters.class.getName() + " instances for you!");
    }
    
    /**
     * 消息码内插表达式解释器
     */
    private static final Interpreter INSTANCE = APIFactory.getInstance().getInterpreter();
    
    /**
     * 获取消息码内插表达式解释器
     *
     * @return 消息码内插表达式解释器
     */
    public static Interpreter getInstance() {
        return INSTANCE;
    }
}
