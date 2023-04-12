package cn.codethink.xiaoming.util;

import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.annotation.Analyzer;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Function;
import cn.codethink.xiaoming.expression.lang.Interpreter;
import cn.codethink.xiaoming.message.content.*;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class InterpretersImpl {
    private InterpretersImpl() {
        throw new NoSuchElementException("No " + InterpretersImpl.class.getName() + " instances for you!");
    }
    
    private static final Interpreter INSTANCE = Interpreter.newInstance();
    static {
        INSTANCE.registerMethods(
            new JdkTypes(),
            new ResourceTypes(),
            new CommonTypes(),
            new MessageContentTypes()
        );
    }
    
    private static class JdkTypes {
        
        @Constructor
        public File constructFile(String path) {
            return new File(path);
        }
        
        @Constructor
        public File constructFile(File file, String path) {
            return new File(file, path);
        }
        
        @Analyzer(File.class)
        public Expression analyzeFile(File file, Interpreter interpreter) {
            return interpreter.compile("File(\"" + StringEscapeUtils.escapeJava(file.getPath()) + "\")");
        }
        
        @Function
        public String property(String property) {
            return System.getProperty(property);
        }
    }
    
    private static class ResourceTypes {
        
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
        
        @Analyzer(Resource.class)
        public Expression analyze(FileResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getFile())) + ")");
        }
        
        @Analyzer(Resource.class)
        public Expression analyze(URLResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getUrl())) + ")");
        }
        
        @Analyzer(Resource.class)
        public Expression analyze(BytesResourceImpl resource, Interpreter interpreter) {
            return interpreter.compile("Resource(" + interpreter.format(interpreter.analyze(resource.getBytes())) + ")");
        }
    }
    
    private static class CommonTypes {
        
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
    
        @Analyzer(Id.class)
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
    
    private static class MessageContentTypes {
        
        @Constructor
        public Text constructText(String text) {
            return Text.of(text);
        }
    
        @Analyzer(Text.class)
        public Expression analyzeText(Text text, Interpreter interpreter) {
            return interpreter.compile("Text(\"" + StringEscapeUtils.escapeJava(text.toString()) + "\")");
        }
    
        @Constructor
        public At constructAt(Id id) {
            return At.of(id);
        }
    
        @Analyzer(At.class)
        public Expression analyzeAt(At at, Interpreter interpreter) {
            return interpreter.compile("At(" + interpreter.format(interpreter.analyze(at.getId())) + ")");
        }
    
        @Constructor
        public AtAll constructAtAll() {
            return AtAll.getInstance();
        }
        
        @Analyzer(AtAll.class)
        public Expression analyzeAtAll(Interpreter interpreter) {
            return interpreter.compile("AtAll()");
        }
        
        @Constructor
        public Image constructImage(Resource resource, Integer width, Integer height, Integer size, ImageType imageType) {
            return new ImageImpl(resource, width, height, size, imageType);
        }
        
        @Constructor
        public Image constructImage(Resource resource) throws IOException {
            return new ImageImpl(resource);
        }
        
        @Constructor
        public Image constructImage(byte[] bytes) throws IOException {
            return new ImageImpl(Resource.of(bytes));
        }
        
        @Analyzer(Image.class)
        public Expression analyze(Image image, Interpreter interpreter) {
            final String resource = interpreter.format(interpreter.analyze(image.getResource()));
            return interpreter.compile("Image(" + resource + ", " + image.getWidth() + ", " + image.getHeight() + ", " + image.getSize() + ", " + image.getImageType() + ")");
        }
    }
    
    public static Interpreter getInstance() {
        return INSTANCE;
    }
}
