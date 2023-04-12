package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.software.Software;
import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SoftwareImpl
    implements Software {
    
    private static final Map<String, Software> INSTANCES = new ConcurrentHashMap<>();
    private static final Set<Software> INSTANCES_SET = new HashSet<>();
    private static final Set<Software> UNMODIFIABLE_INSTANCES_SET = Collections.unmodifiableSet(INSTANCES_SET);
   
    private final String name;
    
    private SoftwareImpl(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        this.name = name;
    }
    
    public static Software getInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        final Software software = INSTANCES.get(name);
        if (software == null) {
            throw new NoSuchElementException("No such Software called " + name + "!");
        }
        return software;
    }
    
    public static Software createInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        Software software = INSTANCES.get(name);
        if (software == null) {
            synchronized (INSTANCES) {
                software = INSTANCES.get(name);
                if (software == null) {
                    software = new SoftwareImpl(name);
                    INSTANCES.put(name, software);
                    INSTANCES_SET.add(software);
                }
            }
        }
        return software;
    }
    
    public static Set<Software> getInstances() {
        return UNMODIFIABLE_INSTANCES_SET;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
