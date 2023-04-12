package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.message.chain.MultipleContentsMessageImplChain;
import cn.codethink.xiaoming.message.content.At;
import cn.codethink.xiaoming.message.content.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MessageCodeTest {
    
    @Test
    public void test() {
        Assertions.assertEquals(Text.of("小明真不辍"), MessageCode.deserialize("小明真不辍"));
        
        final MultipleContentsMessageImplChain messageChain = new MultipleContentsMessageImplChain(Arrays.asList(Text.of("小明真不辍"), At.of(NumericalId.of(1437100907))));
        Assertions.assertEquals(messageChain, MessageCode.deserialize("小明真不辍#{At(Id(1437100907))}"));
    }
    
    @Test
    public void serialize() {
        final At at = At.of(NumericalId.of(1437100907));
        System.out.println(MessageCode.serialize(at));
    }
}
