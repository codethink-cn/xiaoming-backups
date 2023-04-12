package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.common.Resource;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MultipleContentsMessageImplChain;
import cn.codethink.xiaoming.message.content.At;
import cn.codethink.xiaoming.message.content.Image;
import cn.codethink.xiaoming.message.content.Text;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.util.Interpreters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class MessageCodeTest {
    
    @Test
    public void test() {
        Assertions.assertEquals(Text.of("小明真不辍"), MessageCode.deserialize("小明真不辍"));
        
        final MultipleContentsMessageImplChain messageChain = new MultipleContentsMessageImplChain(Arrays.asList(Text.of("小明真不辍"), At.of(NumericalId.of(1437100907))));
        Assertions.assertEquals(messageChain, MessageCode.deserialize("小明真不辍#{At(Id(1437100907))}"));
    }
    
    @Test
    public void serializeAt() {
        final At at = At.of(NumericalId.of(1437100907));
        System.out.println(MessageCode.serialize(at));
    }
    
    @Test
    public void serializeImage() {
        final Image image = Image.of(Resource.of(new File(".github/icons/jetbrains.png")));
        
        final SerializingConfiguration configuration = SerializingConfiguration.builder()
            .countOfSpacesBeforeExpression(1)
            .countOfSpacesAfterExpression(1)
            .formattingConfiguration(FormattingConfiguration.builder()
                .countOfSpacesAfterComma(1)
                .build())
            .build();
        
        final String messageCode = MessageCode.serialize(image, configuration);
        System.out.println(messageCode);

        final Message message = MessageCode.deserialize(messageCode);
        Assertions.assertEquals("#{ Image(Resource(File(\".github\\\\icons\\\\jetbrains.png\")), 0, 0, 0, null) }", messageCode);
        Assertions.assertEquals(image, message);
    }
}
