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

package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.adapter.Adapter;
import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AdapterImpl
    implements Adapter {
    
    private static final Map<String, Adapter> INSTANCES = new ConcurrentHashMap<>();
    private static final Set<Adapter> INSTANCES_SET = new HashSet<>();
    private static final Set<Adapter> UNMODIFIABLE_INSTANCES_SET = Collections.unmodifiableSet(INSTANCES_SET);
   
    private final String name;
    
    private AdapterImpl(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        this.name = name;
    }
    
    public static Adapter getInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        final Adapter adapter = INSTANCES.get(name);
        if (adapter == null) {
            throw new NoSuchElementException("No such Adapter called " + name + "!");
        }
        return adapter;
    }
    
    public static Adapter createInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        Adapter adapter = INSTANCES.get(name);
        if (adapter == null) {
            synchronized (INSTANCES) {
                adapter = INSTANCES.get(name);
                if (adapter == null) {
                    adapter = new AdapterImpl(name);
                    INSTANCES.put(name, adapter);
                    INSTANCES_SET.add(adapter);
                }
            }
        }
        return adapter;
    }
    
    public static Set<Adapter> getInstances() {
        return UNMODIFIABLE_INSTANCES_SET;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
