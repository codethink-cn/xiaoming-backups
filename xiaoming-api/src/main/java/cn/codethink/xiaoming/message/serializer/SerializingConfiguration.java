package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.expression.format.FormatConfiguration;
import cn.codethink.xiaoming.expression.format.PairedFormatUnit;

/**
 * <h1>序列化配置</h1>
 *
 * @author Chuanwise
 */
public interface SerializingConfiguration {
    
    /**
     * 序列化配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置是否序列化为离线消息
         *
         * @param offline 序列化为离线消息
         * @return 序列化配置构建器
         */
        Builder offline(boolean offline);
    
        /**
         * 设置是否存储资源字节信息
         *
         * @param storageResourcesBytes 存储资源字节信息
         * @return 序列化配置构建器
         */
        Builder storageResourcesBytes(boolean storageResourcesBytes);
    
        /**
         * 设置是否明确文本
         *
         * @param explicitText 明确文本
         * @return 序列化配置构建器
         */
        Builder explicitText(boolean explicitText);
    
        /**
         * 设置格式化配置
         *
         * @param formattingConfiguration 格式化配置
         * @return 序列化配置构建器
         */
        Builder formattingConfiguration(FormatConfiguration formattingConfiguration);
    
        /**
         * 设置表达式括号
         *
         * @param expressionBounds 表达式括号
         * @return 序列化配置构建器
         */
        Builder expressionBounds(PairedFormatUnit expressionBounds);
    
        /**
         * 构建序列化配置
         *
         * @return 序列化配置
         */
        SerializingConfiguration build();
    }
    
    /**
     * 获取默认序列化配置构建器
     *
     * @return 默认序列化配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getSerializingConfigurationBuilder();
    }
    
    /**
     * 获取默认序列化配置
     *
     * @return 默认序列化配置
     */
    static SerializingConfiguration getInstance() {
        return APIFactory.getInstance().getSerializingConfiguration();
    }
    
    /**
     * 获取表达式括号
     *
     * @return 表达式括号
     */
    PairedFormatUnit getExpressionBounds();
    
    /**
     * 是否序列化为离线消息
     *
     * @return 序列化为离线消息
     */
    boolean isOffline();
    
    /**
     * 是否存储资源字节信息
     *
     * @return 存储资源字节信息
     */
    boolean isStorageResourcesBytes();
    
    /**
     * 是否明确文本
     *
     * @return 明确文本
     */
    boolean isExplicitText();
    
    /**
     * 获取格式化配置
     *
     * @return 格式化配置
     */
    FormatConfiguration getFormatConfiguration();
}
