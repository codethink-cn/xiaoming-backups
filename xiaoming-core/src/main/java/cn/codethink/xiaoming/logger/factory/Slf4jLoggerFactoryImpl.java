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

package cn.codethink.xiaoming.logger.factory;

import cn.codethink.xiaoming.logger.Logger;
import cn.codethink.xiaoming.logger.Slf4jLoggerImpl;

public final class Slf4jLoggerFactoryImpl
    implements LoggerFactory {
    
    private static final Slf4jLoggerFactoryImpl INSTANCE = new Slf4jLoggerFactoryImpl();
    
    private Slf4jLoggerFactoryImpl() {
    }
    
    public static Slf4jLoggerFactoryImpl getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Logger getLogger(String name) {
        return new Slf4jLoggerImpl(org.slf4j.LoggerFactory.getLogger(name));
    }
    
    @Override
    public Logger getLogger(Class<?> clazz) {
        return new Slf4jLoggerImpl(org.slf4j.LoggerFactory.getLogger(clazz));
    }
}