/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                        throw new NoSuchElementException("No xiaoming-core present!");
                    }
                }
            }
        }
        return api;
    }
}
