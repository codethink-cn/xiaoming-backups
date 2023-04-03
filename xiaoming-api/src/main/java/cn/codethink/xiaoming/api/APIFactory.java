package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.annotation.BotInternalAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * <h1>API 工厂</h1>
 *
 * <p>API 工厂是获取 {@link API} 实例的类。</p>
 *
 * @author Chuanwise
 */
@BotInternalAPI
public class APIFactory {
    private APIFactory() {
    }
    
    /**
     * API 的全局唯一实例
     */
    private static volatile API api;
    
    public static API getInstance() {
        if (api == null) {
            synchronized (APIFactory.class) {
                if (api == null) {
                    final ServiceLoader<API> serviceLoader = ServiceLoader.load(API.class);
                    final Iterator<API> iterator = serviceLoader.iterator();
    
                    if (iterator.hasNext()) {
                        api = iterator.next();
                    } else {
                        throw new NoSuchElementException("Could not load xiaoming-core!");
                    }
                }
            }
        }
        return api;
    }
}
