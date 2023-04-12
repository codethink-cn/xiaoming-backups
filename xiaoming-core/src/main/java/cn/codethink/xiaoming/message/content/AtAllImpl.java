package cn.codethink.xiaoming.message.content;

public class AtAllImpl
    extends AbstractMessageContent
    implements AtAll {
    
    private static final AtAllImpl INSTANCE = new AtAllImpl();
    private static final String DEFAULT_SUMMARY = "@全体成员";

    private AtAllImpl() {
    }
    
    public static AtAllImpl getInstance() {
        return INSTANCE;
    }
    
    @Override
    public String summarize() {
        return DEFAULT_SUMMARY;
    }
}
