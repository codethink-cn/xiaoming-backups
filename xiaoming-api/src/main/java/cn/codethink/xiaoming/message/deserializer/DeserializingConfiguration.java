package cn.codethink.xiaoming.message.deserializer;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.relation.Relation;

/**
 * <h1>反序列化配置</h1>
 *
 * @author Chuanwise
 */
public interface DeserializingConfiguration {
    
    /**
     * 反序列化配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置机器人
         *
         * @param bot 机器人
         * @return 反序列化配置构建器
         */
        Builder bot(Bot bot);
    
        /**
         * 设置关系
         *
         * @param relation 关系
         * @return 反序列化配置构建器
         */
        Builder relation(Relation relation);
    
        /**
         * 设置编译配置
         *
         * @param compilingConfiguration 编译配置
         * @return 反序列化配置构建器
         */
        Builder compilingConfiguration(CompilingConfiguration compilingConfiguration);
    
        /**
         * 构建反序列化配置
         *
         * @return 反序列化配置
         */
        DeserializingConfiguration build();
    }
    
    /**
     * 获取反序列化配置构建器
     *
     * @return 反序列化配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getDeserializingConfigurationBuilder();
    }
    
    /**
     * 获取默认反序列化配置
     *
     * @return 默认反序列化配置
     */
    static DeserializingConfiguration getInstance() {
        return APIFactory.getInstance().getDeserializingConfiguration();
    }
    
    /**
     * 获取机器人
     *
     * @return 机器人
     */
    Bot getBot();
    
    /**
     * 获取关系
     *
     * @return 关系
     */
    Relation getRelation();
    
    /**
     * 获取编译配置
     *
     * @return 编译配置
     */
    CompilingConfiguration getCompilingConfiguration();
}
