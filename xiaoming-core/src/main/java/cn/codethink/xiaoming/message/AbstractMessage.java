package cn.codethink.xiaoming.message;

public abstract class AbstractMessage
    implements Message {
    
    private String toStringCache;
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = MessageCode.serialize(this);
        }
        return toStringCache;
    }
}
