package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.common.Resource;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.InvokeExpressionImpl;
import cn.codethink.xiaoming.expression.annotation.Analyzer;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.format.FormatConfiguration;
import cn.codethink.xiaoming.expression.format.PairedFormatUnit;
import cn.codethink.xiaoming.expression.format.SpacesFormatUnit;
import cn.codethink.xiaoming.expression.format.TextFormatUnit;
import cn.codethink.xiaoming.expression.interpreter.Function;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MultipleMessageContentsMessageChainImpl;
import cn.codethink.xiaoming.message.content.At;
import cn.codethink.xiaoming.message.content.Image;
import cn.codethink.xiaoming.message.content.Text;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.util.Interpreters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class MessageCodeTest {
    
    @Test
    public void analyzeAtAll() {
        final Message deserialize = MessageCode.deserializeFromMessageCode("#{AtAll()}");
        System.out.println(deserialize);
    }
    
    @Test
    public void test() {
        Assertions.assertEquals(Text.of("小明真不辍"), MessageCode.deserializeFromMessageCode("小明真不辍"));
        
        final MultipleMessageContentsMessageChainImpl messageChain = new MultipleMessageContentsMessageChainImpl(Arrays.asList(Text.of("小明真不辍"), At.of(NumericalId.of(1437100907))));
        Assertions.assertEquals(messageChain, MessageCode.deserializeFromMessageCode("小明真不辍#{At(Id(1437100907))}"));
    }
    
    @Test
    public void serializeAt() {
        final At at = At.of(NumericalId.of(1437100907));
        System.out.println(MessageCodeImpl.serialize(at));
    }
    
    @Test
    public void deserializeFile() {
        final Expression expression = Interpreters.getInstance().analyze(new File(".github\\icons\\jetbrains.png"));
        System.out.println(expression);
    }
    
    @Test
    public void serializeImage() {
        final Image image = Image.of(Resource.of(new File(".github/icons/jetbrains.png")));
        
        final SerializingConfiguration configuration = SerializingConfiguration.builder()
            .expressionBounds(PairedFormatUnit.of(TextFormatUnit.of("#{", 1), SpacesFormatUnit.of(1), TextFormatUnit.of(1, "}")))
            .formattingConfiguration(FormatConfiguration.builder()
                .comma(TextFormatUnit.of(",", 1))
                .build())
            .build();
        
        final String messageCode = MessageCodeImpl.serialize(image, configuration);
        System.out.println(messageCode);

        final Message message = MessageCode.deserializeFromMessageCode(messageCode);
        Assertions.assertEquals("#{ Image(Resource(File(\".github\\\\icons\\\\jetbrains.png\")), 0, 0, 0, null) }", messageCode);
        Assertions.assertEquals(image, message);
    }
    
    @Test
    public void deserializeTimeCost() {
        final String image = "#{ Image(Resource(File(\".github\\\\icons\\\\jetbrains.png\")), 0, 0, 0, null) }";
        final int times = 50000;
        final long begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            MessageCode.deserializeFromMessageCode(image);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
    
    private static class IntId
        implements Id {
        
        private static final IntId INSTANCE = new IntId();
    }
    
    private static class IdTypes {
        @Constructor("Id")
        public IntId constructId() {
            return IntId.INSTANCE;
        }
        @Analyzer(IntId.class)
        public Expression analyzeId() {
            final Function function = Interpreters.getInstance().getFunctionOrFail("Id", Collections.emptyList());
            return new InvokeExpressionImpl(function, Collections.emptyList());
        }
    }
    
    @Test
    public void serializeTimeCost() {
        Interpreters.getInstance().registerMethods(new IdTypes());
        
        final Message message = At.of(IntId.INSTANCE);
        final int times = 50000;
        final long begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            MessageCodeImpl.serialize(message);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
    
    @Test
    public void testNearExp() {
        final MessageChain messageChain = MessageChain.of(
            Text.of("咕"),
            Image.of(Resource.of(new File(".github/icons/jetbrains.png"))),
            Text.of("咕"),
            Text.of("咕咕咕")
        );
    
        final SerializingConfiguration configuration = SerializingConfiguration.builder()
            .explicitText(true)
            .build();
    
        final int times = 50000;
        final long begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            messageChain.serializeToMessageCode();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
}
