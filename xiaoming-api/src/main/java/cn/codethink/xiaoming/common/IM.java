package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

import java.util.Set;

/**
 * <h1>即时通讯软件</h1>
 *
 * @author Chuanwise
 */
public interface IM {
    
    /**
     * 获取即时通讯软件
     *
     * @param name 即时通讯软件名
     * @return 即时通讯软件
     */
    static IM of(String name) {
        return APIFactory.getInstance().getIM(name);
    }
    
    /**
     * 获取目前的所有即时通讯软件
     *
     * @return 目前的所有即时通讯软件
     */
    static Set<IM> getInstances() {
        return APIFactory.getInstance().getIMs();
    }
}
