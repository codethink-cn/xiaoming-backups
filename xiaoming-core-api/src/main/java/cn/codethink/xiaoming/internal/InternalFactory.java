package cn.codethink.xiaoming.internal;

import cn.codethink.xiaoming.annotation.BotInternalAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * <h1>Internal 工厂</h1>
 *
 * <p>Internal 工厂是获取 {@link Internal} 实例的类。</p>
 *
 * @author Chuanwise
 */
@BotInternalAPI
public class InternalFactory {
    private InternalFactory() {
    }
    
    /**
     * Internal 的全局唯一实例
     */
    private static volatile Internal instance;
    
    public static Internal getInstance() {
        if (instance == null) {
            synchronized (InternalFactory.class) {
                if (instance == null) {
                    final ServiceLoader<Internal> serviceLoader = ServiceLoader.load(Internal.class);
                    final Iterator<Internal> iterator = serviceLoader.iterator();
    
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                    } else {
                        throw new NoSuchElementException("Could not load xiaoming-core!");
                    }
                }
            }
        }
        return instance;
    }
}
