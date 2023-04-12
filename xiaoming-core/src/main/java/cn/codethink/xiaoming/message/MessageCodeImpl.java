package cn.codethink.xiaoming.message;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.format.FormatUnit;
import cn.codethink.xiaoming.expression.format.Formatter;
import cn.codethink.xiaoming.expression.format.PairedFormatUnit;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.interpreter.InterpreterImpl;
import cn.codethink.xiaoming.message.chain.MessageChain;
import cn.codethink.xiaoming.message.chain.MultipleMessageContentsMessageChainImpl;
import cn.codethink.xiaoming.message.deserializer.DeserializingConfiguration;
import cn.codethink.xiaoming.message.deserializer.DeserializingException;
import cn.codethink.xiaoming.message.content.MessageContent;
import cn.codethink.xiaoming.message.content.Text;
import cn.codethink.xiaoming.message.serializer.SerializingConfiguration;
import cn.codethink.xiaoming.util.InterpretersImpl;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class MessageCodeImpl {
    private MessageCodeImpl() {
        throw new NoSuchElementException("No " + MessageCodeImpl.class.getName() + " instances for you!");
    }
    
    private static void plusFormatUnits(Formatter formatter, MessageContent messageContent, SerializingConfiguration configuration) {
        if (messageContent instanceof Text && !configuration.isExplicitText()) {
            formatter.plus(StringEscapeUtils.unescapeJava(((Text) messageContent).getText()));
            return;
        }
    
        final Interpreter interpreter = InterpretersImpl.getInstance();
        final Expression expression = interpreter.analyze(messageContent);
        ((InterpreterImpl) interpreter).plusFormatUnits(formatter, expression);
    }
    
    public static String serialize(Message message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        return serialize(message, SerializingConfiguration.getInstance());
    }
    
    public static String serialize(Message message, SerializingConfiguration configuration) {
        Preconditions.checkNotNull(message, "Message is null!");
        Preconditions.checkNotNull(configuration, "Serializing configuration is null!");
    
        final Formatter formatter = Formatter.newInstance(configuration.getFormatConfiguration());
        final PairedFormatUnit expressionBraces = configuration.getExpressionBounds();
        if (message instanceof MessageContent) {
    
            formatter.plus(expressionBraces.getLeftUnit());
            plusFormatUnits(formatter, (MessageContent) message, configuration);
            formatter.plus(expressionBraces.getRightUnit());
            
            return formatter.toString();
        }
        if (message instanceof MessageChain) {
            final MessageChain messageChain = (MessageChain) message;
            final int size = messageChain.size();
            if (size == 0) {
                throw new IllegalArgumentException("Unexpected empty message chain");
            }
    
            final FormatUnit comma = configuration.getFormatConfiguration().getComma();
            if (configuration.isExplicitText()) {
                formatter.plus(expressionBraces.getLeftUnit());
                plusFormatUnits(formatter, messageChain.get(0), configuration);
                for (int i = 1; i < size; i++) {
                    formatter.plus(comma);
                    plusFormatUnits(formatter, messageChain.get(i), configuration);
                }
                formatter.plus(expressionBraces.getRightUnit());
            } else {
                boolean expression = false;
                for (MessageContent messageContent : messageChain) {
                    if (expression) {
                        if (messageContent instanceof Text) {
                            formatter.plus(expressionBraces.getRightUnit());
                            expression = false;
                        } else {
                            formatter.plus(comma);
                        }
                        plusFormatUnits(formatter, messageContent, configuration);
                    } else {
                        if (!(messageContent instanceof Text)) {
                            expression = true;
                            formatter.plus(expressionBraces.getLeftUnit());
                        }
                        plusFormatUnits(formatter, messageContent, configuration);
                    }
                }
                
                if (expression) {
                    formatter.plus(expressionBraces.getRightUnit());
                }
            }
            return formatter.toString();
        }

        throw new IllegalArgumentException("Unexpected message type: " + message.getClass().getName());
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
    
            final List<MessageContent> messageContents = new ArrayList<>();
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
                                    messageContents.add(Text.of(StringEscapeUtils.unescapeJava(content)));
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
                        stringBuilder.append(ch);
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
                                    final Expression expression = InterpretersImpl.getInstance().compile(stringBuilder.toString());
                                    final Object result = expression.interpret();
                                    if (result instanceof MessageContent) {
                                        messageContents.add((MessageContent) result);
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
                                final Expression expression = InterpretersImpl.getInstance().compile(stringBuilder.toString());
                                final Object result = expression.interpret();
                                if (result instanceof MessageContent) {
                                    messageContents.add((MessageContent) result);
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
                messageContents.add(Text.of(StringEscapeUtils.unescapeJava(content)));
                stringBuilder.setLength(0);
            }
            
            if (messageContents.isEmpty()) {
                throw new IllegalArgumentException("Empty message chain!");
            }
            if (messageContents.size() == 1) {
                return messageContents.get(0);
            }
            return new MultipleMessageContentsMessageChainImpl(messageContents);
        } catch (IOException e) {
            throw new DeserializingException("Exception thrown while checking if reader ready", e);
        }
    }
}