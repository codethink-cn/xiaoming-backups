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

import cn.codethink.xiaoming.logger.Level;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.NoSuchElementException;

public class Levels {
    private Levels() {
        throw new NoSuchElementException("No " + Levels.class.getName() + " instances for you!");
    }
    
    private static final DualHashBidiMap<Level, org.slf4j.event.Level> LEVELS = new DualHashBidiMap<>();
    static {
        LEVELS.put(Level.ERROR, org.slf4j.event.Level.ERROR);
        LEVELS.put(Level.WARN, org.slf4j.event.Level.WARN);
        LEVELS.put(Level.INFO, org.slf4j.event.Level.INFO);
        LEVELS.put(Level.DEBUG, org.slf4j.event.Level.DEBUG);
        LEVELS.put(Level.TRACE, org.slf4j.event.Level.TRACE);
    }
    
    public static Level convert(org.slf4j.event.Level level) {
        return LEVELS.getKey(level);
    }
    
    public static org.slf4j.event.Level convert(Level level) {
        return LEVELS.get(level);
    }
}
