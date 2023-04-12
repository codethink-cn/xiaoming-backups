package cn.codethink.xiaoming.adapter;

import cn.codethink.xiaoming.api.APIFactory;

import java.util.Set;

/**
 * <h1>适配器</h1>
 *
 * @author Chuanwise
 */
public interface Adapter {
    
    /**
     * 获取适配器
     *
     * @param name 适配器名
     * @return 适配器
     */
    static Adapter of(String name) {
        return APIFactory.getInstance().getIM(name);
    }
    
    /**
     * 获取目前的所有适配器
     *
     * @return 目前的所有适配器
     */
    static Set<Adapter> getInstances() {
        return APIFactory.getInstance().getIMs();
    }
}
