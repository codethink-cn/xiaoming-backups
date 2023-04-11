package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.message.chain.MessageChainImpl;
import cn.codethink.xiaoming.message.element.At;
import cn.codethink.xiaoming.message.element.Text;
import cn.codethink.xiaoming.util.Interpreters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MessageCodeTest {
    
    @Test
    public void text() {
        final ConfigurableInterpreter interpreter = Interpreters.getInstance();
    
        Assertions.assertThrows(CompilingException.class, () -> interpreter.compile("Text()"));
    
        final Expression expression = interpreter.compile("Text(\"xiaoming is good!\")");
        final Object result = expression.calculate();
        Assertions.assertEquals(Text.of("xiaoming is good!"), result);
    }
    
    @Test
    public void test() {
        Assertions.assertEquals(Text.of("小明真不辍"), MessageCode.deserialize("小明真不辍"));
        
        final MessageChainImpl messageChain = new MessageChainImpl(Arrays.asList(Text.of("小明真不辍"), At.of(NumericalId.of(1437100907))));
        Assertions.assertEquals(messageChain, MessageCode.deserialize("小明真不辍#{At(Id(1437100907))}"));
    }
}
