package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;

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
         * 设置是否存储图像字节信息
         *
         * @param storageImageBytes 存储图像字节信息
         * @return 序列化配置构建器
         */
        Builder storageImageBytes(boolean storageImageBytes);
    
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
        Builder formattingConfiguration(FormattingConfiguration formattingConfiguration);
    
        /**
         * 设置分析配置
         *
         * @param analyzingConfiguration 分析配置
         * @return 序列化配置构建器
         */
        Builder analyzingConfiguration(AnalyzingConfiguration analyzingConfiguration);
    
        /**
         * 设置表达式开头的空格数
         *
         * @param countOfSpacesBeforeExpression 表达式开头的空格数
         * @return 序列化配置构建器
         */
        Builder countOfSpacesBeforeExpression(int countOfSpacesBeforeExpression);
    
        /**
         * 设置表达式结尾的空格数
         *
         * @param countOfSpacesAfterExpression 表达式结尾的空格数
         * @return 序列化配置构建器
         */
        Builder countOfSpacesAfterExpression(int countOfSpacesAfterExpression);
    
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
     * 获取表达式开头的空格数
     *
     * @return 表达式开头的空格数
     */
    int getCountOfSpacesBeforeExpression();
    
    /**
     * 获取表达式结尾的空格数
     *
     * @return 表达式结尾的空格数
     */
    int getCountOfSpacesAfterExpression();
    
    /**
     * 是否序列化为离线消息
     *
     * @return 序列化为离线消息
     */
    boolean isOffline();
    
    /**
     * 是否存储图像字节信息
     *
     * @return 存储图像字节信息
     */
    Boolean isStorageImageBytes();
    
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
    FormattingConfiguration getFormattingConfiguration();
    
    /**
     * 获取分析配置
     *
     * @return 分析配置
     */
    AnalyzingConfiguration getAnalyzingConfiguration();
}
