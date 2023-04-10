package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.Bot;
import com.google.common.base.Preconditions;

public class AbstractBotObject
    implements BotObject {
    
    private final Bot bot;
    
    public AbstractBotObject(Bot bot) {
        Preconditions.checkNotNull(bot, "Bot is null!");
        
        this.bot = bot;
    }
    
    @Override
    public final Bot getBot() {
        return bot;
    }
}
