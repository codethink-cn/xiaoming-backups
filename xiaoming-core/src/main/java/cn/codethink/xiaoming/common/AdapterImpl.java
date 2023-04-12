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
