package cn.codethink.xiaoming.util;

import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.annotation.Analyser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Type;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.message.content.*;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class Interpreters {
    private Interpreters() {
        throw new NoSuchElementException("No " + Interpreters.class.getName() + " instances for you!");
    }
    
    private static final ConfigurableInterpreter INSTANCE = ConfigurableInterpreter.newInstance(Interpreter.getInstance());
    static {
        INSTANCE.registerType(new TextWrapper(), new IdWrapper(), new AtWrapper(),
            new AtAllWrapper(), new ImageWrapper(), new ResourceWrapper());
    }
    
    @Type(Text.class)
    private static class TextWrapper {
        @Constructor
        public Text constructText(String text) {
            return Text.of(text);
        }
    
        @Analyser
        public Expression analyzeText(Text text, Interpreter interpreter) {
            return interpreter.compile("Text(\"" + StringEscapeUtils.escapeJava(text.toString()) + "\")");
        }
    }
    
    @Type(Id.class)
    private static class IdWrapper {
        
        @Constructor
        public Id constructId(String string) {
            return StringId.of(string);
        }
        
        @Constructor
        public Id constructId(Long value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        public Id constructId(Short value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        public Id constructId(Byte value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        public Id constructId(Integer value) {
            return NumericalId.of(value);
        }
    
        @Constructor
        public Id constructId(Double value) {
            return NumericalId.of(value);
        }
        
        @Constructor
        public Id constructId(Float value) {
            return NumericalId.of(value);
        }
        
        @Analyser
        public Expression analyzeId(Id id, Interpreter interpreter) {
            if (id instanceof NumericalId) {
                return interpreter.compile("Id(" + id + ")");
            }
            if (id instanceof StringId) {
                return interpreter.compile("Id(\"" + StringEscapeUtils.escapeJava(id.toString()) + "\")");
            }
            return null;
        }
    }
    
    @Type(At.class)
    private static class AtWrapper {
        @Constructor
        public At constructAt(Id id) {
            return At.of(id);
        }
    
        @Analyser
        public Expression analyzeAt(At at, Interpreter interpreter) {
            return interpreter.compile("At(" + interpreter.format(interpreter.analyze(at.getId())) + ")");
        }
    }
    
    @Type(AtAll.class)
    private static class AtAllWrapper {
        @Constructor
        public AtAll constructAtAll() {
            return AtAll.getInstance();
        }
    }
    
    @Type(Image.class)
    private static class ImageWrapper {
        @Constructor
        public Image construct(Resource resource, int width, int height, int size, ImageType imageType) {
            return new ImageImpl(resource, width, height, size, imageType);
        }
        
        @Constructor
        public Image construct(Resource resource) throws IOException {
            return new ImageImpl(resource);
        }
        
        @Constructor
        public Image construct(byte[] bytes) throws IOException {
            return new ImageImpl(Resource.of(bytes));
        }
        
        @Analyser
        public Expression analyze(Image image, Interpreter interpreter) {
            final String resource = interpreter.format(interpreter.analyze(image.getResource()));
            return interpreter.compile("Image(" + resource + ", " + image.getWidth() + ", " + image.getHeight() + ", " + image.getSize() + ", " + image.getImageType() + ")");
        }
    }
    
    @Type(Resource.class)
    private static class ResourceWrapper {
    
        @Constructor
        public Resource construct(File value) {
            return Resource.of(value);
        }
    
        @Constructor
        public Resource construct(URL value) {
            return Resource.of(value);
        }
    
        @Constructor
        public Resource construct(String value) {
            return Resource.of(value);
        }
    
        @Constructor
        public Resource construct(byte[] value) {
            return Resource.of(value);
        }
        
        @Analyser
        public Expression analyze(FileResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getFile())) + ")");
        }
        
        @Analyser
        public Expression analyze(URLResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getUrl())) + ")");
        }
        
        @Analyser
        public Expression analyze(BytesResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getBytes())) + ")");
        }
    }
    
    public static ConfigurableInterpreter getInstance() {
        return INSTANCE;
    }
}
