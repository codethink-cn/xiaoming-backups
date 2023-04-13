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

package cn.codethink.xiaoming.util;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;

import java.util.NoSuchElementException;

/**
 * <h1>解释器工具</h1>
 *
 * @author Chuanwise
 */
public class Interpreters {
    private Interpreters() {
        throw new NoSuchElementException("No " + Interpreters.class.getName() + " instances for you!");
    }
    
    /**
     * 消息码内插表达式解释器
     */
    private static final Interpreter INSTANCE = APIFactory.getInstance().getInterpreter();
    
    /**
     * 获取消息码内插表达式解释器
     *
     * @return 消息码内插表达式解释器
     */
    public static Interpreter getInstance() {
        return INSTANCE;
    }
}
