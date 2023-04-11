package cn.codethink.xiaoming.util;

import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.common.StringId;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.annotation.Analyser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Type;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.message.element.At;
import cn.codethink.xiaoming.message.element.AtAll;
import cn.codethink.xiaoming.message.element.Text;
import org.apache.commons.text.StringEscapeUtils;

import java.util.NoSuchElementException;

public class Interpreters {
    private Interpreters() {
        throw new NoSuchElementException("No " + Interpreters.class.getName() + " instances for you!");
    }
    
    private static final ConfigurableInterpreter INSTANCE = ConfigurableInterpreter.newInstance(Interpreter.getInstance());
    static {
        INSTANCE.registerTypes(new MessageCodeTypes());
    }
    
    private static class MessageCodeTypes {
    
        @Constructor
        @Type(Text.class)
        public Text constructText(String text) {
            return Text.of(text);
        }
        
        @Analyser
        @Type(Text.class)
        public Expression analyzeText(Text text, Interpreter interpreter) {
            return interpreter.compile("Text(\"" + StringEscapeUtils.escapeJava(text.toString()) + "\")");
        }
        
        @Constructor
        @Type(Id.class)
        public Id constructId(String string) {
            return StringId.of(string);
        }
        
        @Constructor
        @Type(Id.class)
        public Id constructId(Long value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        @Type(Id.class)
        public Id constructId(Short value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        @Type(Id.class)
        public Id constructId(Byte value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        @Type(Id.class)
        public Id constructId(Integer value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        @Type(Id.class)
        public Id constructId(Double value) {
            return NumericalId.of(value);
        }
        
        @Constructor
        @Type(Id.class)
        public Id constructId(Float value) {
            return NumericalId.of(value);
        }
        
        @Analyser
        @Type(Id.class)
        public Expression analyzeId(Id id, Interpreter interpreter) {
            if (id instanceof NumericalId) {
                return interpreter.compile("Id(" + id + ")");
            }
            if (id instanceof StringId) {
                return interpreter.compile("Id(\"" + StringEscapeUtils.escapeJava(id.toString()) + "\")");
            }
            return null;
        }
        
        @Constructor
        @Type(At.class)
        public At constructAt(Id id) {
            return At.of(id);
        }
        
        @Analyser
        @Type(At.class)
        public Expression analyzeAt(At at, Interpreter interpreter) {
            return interpreter.compile("At(" + interpreter.format(interpreter.analyze(at.getId())) + ")");
        }
        
        @Constructor
        @Type(AtAll.class)
        public AtAll constructAtAll() {
            return AtAll.getInstance();
        }
    }
    
    public static ConfigurableInterpreter getInstance() {
        return INSTANCE;
    }
}
