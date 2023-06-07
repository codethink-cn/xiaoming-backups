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

package cn.codethink.xiaoming.event.manager;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.adapter.Adapter;
import cn.codethink.xiaoming.common.Id;
import cn.codethink.xiaoming.configuration.BotConfiguration;
import cn.codethink.xiaoming.event.AbstractInterceptableEvent;
import cn.codethink.xiaoming.event.EventManagerImpl;
import cn.codethink.xiaoming.logger.factory.LoggerFactory;
import cn.codethink.xiaoming.relation.RelationManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class EventManagerTest {
    
    private static class ConfigurationAvailableBot
        implements Bot {
    
        private final BotConfiguration botConfiguration;
    
        public ConfigurationAvailableBot(BotConfiguration botConfiguration) {
            this.botConfiguration = botConfiguration;
        }
    
        @Override
        public RelationManager getRelationManager() {
            return null;
        }
    
        @Override
        public BotConfiguration getConfiguration() {
            return botConfiguration;
        }
    
        @Override
        public Adapter getAdapter() {
            return null;
        }
    
        @Override
        public Id getId() {
            return null;
        }
    }
    
    private int field = 0;
    
    public static class ChangeStaticFieldEvent
        extends AbstractInterceptableEvent {
    
        private final int changeTo;
    
        public ChangeStaticFieldEvent(int changeTo) {
            this.changeTo = changeTo;
        }
    }
    
    final Bot bot = new ConfigurationAvailableBot(new BotConfiguration() {
        @Override
        public LoggerFactory getLoggerFactory() {
            return LoggerFactory.slf4j();
        }
        
        @Override
        public void setLoggerFactory(LoggerFactory loggerFactory) {
        
        }
    });
    final EventManager eventManager = new EventManagerImpl(bot);
    
    @Test
    public void registerListener() {
        eventManager.registerListener(ChangeStaticFieldEvent.class, event -> field = event.changeTo);
        
        for (int i = 0; i < 5; i++) {
            final int changeTo = ThreadLocalRandom.current().nextInt();
            eventManager.publish(new ChangeStaticFieldEvent(changeTo));
            Assertions.assertEquals(changeTo, field);
        }
        
        eventManager.unregisterListenersIf(listener -> listener.getEventClasses().contains(ChangeStaticFieldEvent.class));
    
        final int beforePublishEvent = this.field;
        eventManager.publish(new ChangeStaticFieldEvent(beforePublishEvent + 5));
        Assertions.assertEquals(beforePublishEvent, field);
    }
}
