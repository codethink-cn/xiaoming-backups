package cn.codethink.xiaoming.message.content;

public class AtAllImpl
    implements AtAll {
    
    private static final AtAllImpl INSTANCE = new AtAllImpl();

    private AtAllImpl() {
    }
    
    public static AtAllImpl getInstance() {
        return INSTANCE;
    }
}
