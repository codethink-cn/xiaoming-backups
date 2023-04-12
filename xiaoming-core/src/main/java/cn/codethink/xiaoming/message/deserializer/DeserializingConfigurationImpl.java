package cn.codethink.xiaoming.message.deserializer;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.common.BotObject;
import cn.codethink.xiaoming.relation.Relation;
import com.google.common.base.Preconditions;

public class DeserializingConfigurationImpl
    implements DeserializingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private Bot bot;
        private Relation relation;
    
    
        @Override
        public Builder bot(Bot bot) {
            this.bot = bot;
            return this;
        }
    
        @Override
        public Builder relation(Relation relation) {
            this.relation = relation;
            if (bot == null) {
                if (relation instanceof Bot) {
                    bot = (Bot) relation;
                } else if (relation instanceof BotObject) {
                    bot = ((BotObject) relation).getBot();
                } else {
                    throw new IllegalArgumentException("Unexpected relation type: " + relation.getClass().getName());
                }
            }
            return this;
        }
    
        @Override
        public DeserializingConfiguration build() {
            return new DeserializingConfigurationImpl(bot, relation);
        }
    }
    
    private static final DeserializingConfiguration INSTANCE = DeserializingConfiguration.builder().build();
    
    public static DeserializingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final Bot bot;
    private final Relation relation;
    
    private DeserializingConfigurationImpl(Bot bot, Relation relation) {
        this.bot = bot;
        this.relation = relation;
    }
    
    @Override
    public Bot getBot() {
        return bot;
    }
    
    @Override
    public Relation getRelation() {
        return relation;
    }
}
