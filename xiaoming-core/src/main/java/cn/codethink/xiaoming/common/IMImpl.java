package cn.codethink.xiaoming.common;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class IMImpl
    implements IM {
    
    private static final Map<String, IM> INSTANCES = new ConcurrentHashMap<>();
    private static final Set<IM> INSTANCES_SET = new HashSet<>();
    private static final Set<IM> UNMODIFIABLE_INSTANCES_SET = Collections.unmodifiableSet(INSTANCES_SET);
   
    private final String name;
    
    private IMImpl(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        this.name = name;
    }
    
    public static IM getInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        final IM im = INSTANCES.get(name);
        if (im == null) {
            throw new NoSuchElementException("No such IM called " + name + "!");
        }
        return im;
    }
    
    public static IM createInstance(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        IM im = INSTANCES.get(name);
        if (im == null) {
            synchronized (INSTANCES) {
                im = INSTANCES.get(name);
                if (im == null) {
                    im = new IMImpl(name);
                    INSTANCES.put(name, im);
                    INSTANCES_SET.add(im);
                }
            }
        }
        return im;
    }
    
    public static Set<IM> getInstances() {
        return UNMODIFIABLE_INSTANCES_SET;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
