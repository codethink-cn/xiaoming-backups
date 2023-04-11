package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingItem;
import cn.codethink.xiaoming.expression.formatter.FormattingTextItem;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MessageChainImpl;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.deserializer.DeserializingException;
import cn.codethink.xiaoming.message.element.MessageElement;
import cn.codethink.xiaoming.message.element.Text;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.util.Interpreters;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MessageCodeImpl {
    private MessageCodeImpl() {
        throw new NoSuchElementException("No " + MessageCodeImpl.class.getName() + " instances for you!");
    }
    
    private static FormattingItem serializeSingleMessage(MessageElement messageElement, SerializingConfiguration configuration) {
        if (messageElement instanceof Text && !configuration.isExplicitText()) {
            return new FormattingTextItem(StringEscapeUtils.escapeJava(messageElement.toString()));
        }
    
        final ConfigurableInterpreter interpreter = Interpreters.getInstance();
        final Expression expression = interpreter.analyze(messageElement, configuration.getAnalyzingConfiguration());
        final FormattingConfiguration formattingConfiguration = configuration.getFormattingConfiguration();
        
        return new FormattingTextItem(interpreter.format(expression, formattingConfiguration));
    }
    
    public static String serialize(Message message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        return serialize(message, SerializingConfiguration.getInstance());
    }
    
    public static String serialize(Message message, SerializingConfiguration configuration) {
        Preconditions.checkNotNull(message, "Message is null!");
        Preconditions.checkNotNull(configuration, "Serializing configuration is null!");
    
        if (message instanceof MessageElement) {
            return FormattingItem.toString(Collections.singletonList(serializeSingleMessage((MessageElement) message, configuration)),
                configuration.getFormattingConfiguration().isMinimizeSpaces());
        }
        if (message instanceof MessageChain) {
            final MessageChain messageChain = (MessageChain) message;
            final int size = messageChain.size();
            if (size == 0) {
                throw new IllegalArgumentException("Unexpected empty message chain");
            }
    
            final FormattingTextItem itemBeforeExpression =
                new FormattingTextItem(0, "#{", configuration.getCountOfSpacesBeforeExpression());
            final FormattingTextItem itemAfterExpression =
                new FormattingTextItem(configuration.getCountOfSpacesAfterExpression(), "}", 0);
            final FormattingItem comma = new FormattingTextItem(configuration.getFormattingConfiguration().getCountOfSpacesBeforeComma(),
                ",", configuration.getFormattingConfiguration().getCountOfSpacesAfterComma());
    
            final List<FormattingItem> items = new ArrayList<>();
            if (configuration.isExplicitText()) {
                items.add(itemBeforeExpression);
                items.add(serializeSingleMessage(messageChain.get(0), configuration));
                for (int i = 1; i < size; i++) {
                    items.add(comma);
                    items.add(serializeSingleMessage(messageChain.get(i), configuration));
                }
                items.add(itemAfterExpression);
            } else {
                boolean expression = false;
                for (MessageElement messageElement : messageChain) {
                    if (expression) {
                        if (messageElement instanceof Text) {
                            items.add(itemAfterExpression);
                            expression = false;
                        }
                        items.add(serializeSingleMessage(messageElement, configuration));
                    } else {
                        if (!(messageElement instanceof Text)) {
                            expression = true;
                            items.add(itemBeforeExpression);
                        }
                        items.add(serializeSingleMessage(messageElement, configuration));
                    }
                }
                
                if (expression) {
                    items.add(itemAfterExpression);
                }
            }
            return FormattingItem.toString(items, configuration.getFormattingConfiguration().isMinimizeSpaces());
        }
//        if (message instanceof )
        
        return null;
    }
    
    public static Message deserialize(Reader reader) {
        Preconditions.checkNotNull(reader, "Reader is null!");
    
        return deserialize(reader, DeserializingConfiguration.getInstance());
    }
    
    public static Message deserialize(Reader reader, DeserializingConfiguration configuration) {
        Preconditions.checkNotNull(reader, "Reader is null!");
        Preconditions.checkNotNull(configuration, "Deserializing configuration is null!");
    
        try {
            // 深度，用于匹配内插表达式括号
            int depth = 0;
            
            boolean escaped = false;
            boolean text = true;
            
            // 非 text 状态时，inString 表示当前是否正在表达式字符串中
            boolean inString = false;
            
            // text 状态时，获得 # 后接受 { 的状态
            boolean acceptingLeftBrace = false;
    
            final List<MessageElement> segments = new ArrayList<>();
            final StringBuilder stringBuilder = new StringBuilder();
            
            while (reader.ready()) {
                final int read = reader.read();
                if (read == -1) {
                    break;
                }
                
                final char ch = (char) read;
    
                if (text) {
                    if (escaped) {
                        stringBuilder.append(ch);
                        escaped = false;
                        continue;
                    }
                    
                    switch (ch) {
                        case '\\':
                            stringBuilder.append(ch);
                            escaped = true;
                            continue;
                        case '{':
                            if (acceptingLeftBrace) {
                                // 进入表达式解析状态
                                if (stringBuilder.length() > 0) {
                                    final String content = stringBuilder.toString();
                                    segments.add(Text.of(StringEscapeUtils.unescapeJava(content)));
                                    stringBuilder.setLength(0);
                                }
                                text = false;
                                acceptingLeftBrace = false;
                                depth = 1;
                            } else {
                                stringBuilder.append('{');
                            }
                            continue;
                        case '#':
                            if (acceptingLeftBrace) {
                                // 如果 acceptingLeftBrace，说明前面的 # 只是文本的 #，将其加入缓存
                                stringBuilder.append("##");
                                acceptingLeftBrace = false;
                            } else {
                                // 否则等待 {
                                acceptingLeftBrace = true;
                            }
                            continue;
                        default:
                            if (acceptingLeftBrace) {
                                stringBuilder.append("#");
                                acceptingLeftBrace = false;
                            }
                            stringBuilder.append(ch);
                    }
                } else {
                    // 处理表达式中的字符串中的 \"
                    if (escaped) {
                        escaped = false;
                        continue;
                    }
                    
                    switch (ch) {
                        case '\\':
                            stringBuilder.append(ch);
                            escaped = true;
                            continue;
                        case '"':
                            stringBuilder.append(ch);
                            inString = !inString;
                            continue;
                            
                            // 遇到表达式内的括号，都将其放入缓存中
                            // 如果括号不是字符串里的括号，则增加深度
                        case '{':
                        case '[':
                        case '(':
                            if (!inString) {
                                depth++;
                            }
                            stringBuilder.append(ch);
                            continue;
                            
                        case ']':
                        case ')':
                            if (!inString) {
                                depth--;
                            }
                            stringBuilder.append(ch);
                            continue;
                        case '}':
                            if (!inString) {
                                depth--;
                                
                                if (depth == 0) {
                                    final Expression expression = Interpreters.getInstance().compile(stringBuilder.toString(), configuration.getCompilingConfiguration());
                                    final Object result = expression.calculate();
                                    if (result instanceof MessageElement) {
                                        segments.add((MessageElement) result);
                                    } else {
                                        throw new IllegalArgumentException("Unexpected result of internal expression: " + result + ", expected instances of " + Message.class.getName());
                                    }
                                    text = true;
                                    stringBuilder.setLength(0);
                                } else {
                                    stringBuilder.append(ch);
                                }
                            } else {
                                stringBuilder.append(ch);
                            }
                            continue;
                        case ',':
                            if (inString) {
                                stringBuilder.append(ch);
                                continue;
                            }
    
                            // depth == 0 时表示多个表达式分割
                            // 例如 #{Image(...), At(...)}
                            if (depth == 0) {
                                
                                // 编译前面的表达式
                                final Expression expression = Interpreters.getInstance().compile(stringBuilder.toString(), configuration.getCompilingConfiguration());
                                final Object result = expression.calculate();
                                if (result instanceof MessageElement) {
                                    segments.add((MessageElement) result);
                                } else {
                                    throw new IllegalArgumentException("Unexpected result of internal expression: " + result + ", expected instances of " + Message.class.getName());
                                }
                                
                                // 清空缓存
                                stringBuilder.setLength(0);
                            } else {
                                stringBuilder.append(ch);
                            }
                            continue;
                        default:
                            stringBuilder.append(ch);
                    }
                }
            }
            
            // 最终还是 text 状态说明最后一个表达式没有结束
            if (!text) {
                throw new IllegalArgumentException("Uncompleted expression at final position");
            }
    
            // 如果结尾有文本，则将其添加到列表中
            if (stringBuilder.length() > 0) {
                final String content = stringBuilder.toString();
                segments.add(Text.of(StringEscapeUtils.unescapeJava(content)));
                stringBuilder.setLength(0);
            }
            
            if (segments.isEmpty()) {
                throw new IllegalArgumentException("Empty message chain!");
            }
            if (segments.size() == 1) {
                return segments.get(0);
            }
            return new MessageChainImpl(segments);
        } catch (IOException e) {
            throw new DeserializingException("Exception thrown while checking if reader ready", e);
        }
    }
}