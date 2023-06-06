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

package cn.codethink.xiaoming.logger;

import cn.codethink.xiaoming.util.Levels;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;

public class Slf4jLoggerImpl
    implements cn.codethink.xiaoming.logger.Logger {
    
    private final Logger logger;
    
    public Slf4jLoggerImpl(Logger logger) {
        Preconditions.checkNotNull(logger, "Logger is null!");
        
        this.logger = logger;
    }
    
    @Override
    public boolean isEnabledForLevel(Level level) {
        Preconditions.checkNotNull(level, "Level is null!");

        return logger.isEnabledForLevel(Levels.convert(level));
    }
    
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
    
    @Override
    public void error(String message) {
        Preconditions.checkNotNull(message, "Message is null!");

        logger.error(message);
    }
    
    @Override
    public void error(String format, Object argument) {
        Preconditions.checkNotNull(format, "Format is null!");

        logger.error(format, argument);
    }
    
    @Override
    public void error(String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(format, "Format is null!");
      
        logger.error(format, argument1, argument2);
    }
    
    @Override
    public void error(String format, Object... arguments) {
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");

        logger.error(format, arguments);
    }
    
    @Override
    public void error(Throwable cause) {
        Preconditions.checkNotNull(cause, "Cause is null!");
    
        logger.error(cause.getMessage(), cause);
    }
    
    @Override
    public void error(Throwable cause, String message) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.error(message, cause);
    }
    
    @Override
    public void error(Throwable cause, String format, Object argument) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.error(format, argument);
        logger.error(cause.getMessage(), cause);
    }
    
    @Override
    public void error(Throwable cause, String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
    
        logger.error(format, argument1, argument2);
        logger.error(cause.getMessage(), cause);
    }
    
    @Override
    public void error(Throwable cause, String format, Object... arguments) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
    
        logger.error(format, arguments);
        logger.error(cause.getMessage(), cause);
    }
    
    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }
    
    @Override
    public void warn(String message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.warn(message);
    }
    
    @Override
    public void warn(String format, Object argument) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.warn(format, argument);
    }
    
    @Override
    public void warn(String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.warn(format, argument1, argument2);
    }
    
    @Override
    public void warn(String format, Object... arguments) {
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.warn(format, arguments);
    }
    
    @Override
    public void warn(Throwable cause) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        
        logger.warn(cause.getMessage(), cause);
    }
    
    @Override
    public void warn(Throwable cause, String message) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.warn(message, cause);
    }
    
    @Override
    public void warn(Throwable cause, String format, Object argument) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.warn(format, argument);
        logger.warn(cause.getMessage(), cause);
    }
    
    @Override
    public void warn(Throwable cause, String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.warn(format, argument1, argument2);
        logger.warn(cause.getMessage(), cause);
    }
    
    @Override
    public void warn(Throwable cause, String format, Object... arguments) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.warn(format, arguments);
        logger.warn(cause.getMessage(), cause);
    }
    
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }
    
    @Override
    public void info(String message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.info(message);
    }
    
    @Override
    public void info(String format, Object argument) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.info(format, argument);
    }
    
    @Override
    public void info(String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.info(format, argument1, argument2);
    }
    
    @Override
    public void info(String format, Object... arguments) {
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.info(format, arguments);
    }
    
    @Override
    public void info(Throwable cause) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        
        logger.info(cause.getMessage(), cause);
    }
    
    @Override
    public void info(Throwable cause, String message) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.info(message, cause);
    }
    
    @Override
    public void info(Throwable cause, String format, Object argument) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.info(format, argument);
        logger.info(cause.getMessage(), cause);
    }
    
    @Override
    public void info(Throwable cause, String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.info(format, argument1, argument2);
        logger.info(cause.getMessage(), cause);
    }
    
    @Override
    public void info(Throwable cause, String format, Object... arguments) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.info(format, arguments);
        logger.info(cause.getMessage(), cause);
    }
    
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }
    
    @Override
    public void debug(String message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.debug(message);
    }
    
    @Override
    public void debug(String format, Object argument) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.debug(format, argument);
    }
    
    @Override
    public void debug(String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.debug(format, argument1, argument2);
    }
    
    @Override
    public void debug(String format, Object... arguments) {
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.debug(format, arguments);
    }
    
    @Override
    public void debug(Throwable cause) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        
        logger.debug(cause.getMessage(), cause);
    }
    
    @Override
    public void debug(Throwable cause, String message) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.debug(message, cause);
    }
    
    @Override
    public void debug(Throwable cause, String format, Object argument) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.debug(format, argument);
        logger.debug(cause.getMessage(), cause);
    }
    
    @Override
    public void debug(Throwable cause, String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.debug(format, argument1, argument2);
        logger.debug(cause.getMessage(), cause);
    }
    
    @Override
    public void debug(Throwable cause, String format, Object... arguments) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.debug(format, arguments);
        logger.debug(cause.getMessage(), cause);
    }
    
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }
    
    @Override
    public void trace(String message) {
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.trace(message);
    }
    
    @Override
    public void trace(String format, Object argument) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.trace(format, argument);
    }
    
    @Override
    public void trace(String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.trace(format, argument1, argument2);
    }
    
    @Override
    public void trace(String format, Object... arguments) {
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.trace(format, arguments);
    }
    
    @Override
    public void trace(Throwable cause) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        
        logger.trace(cause.getMessage(), cause);
    }
    
    @Override
    public void trace(Throwable cause, String message) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(message, "Message is null!");
        
        logger.trace(message, cause);
    }
    
    @Override
    public void trace(Throwable cause, String format, Object argument) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.trace(format, argument);
        logger.trace(cause.getMessage(), cause);
    }
    
    @Override
    public void trace(Throwable cause, String format, Object argument1, Object argument2) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        
        logger.trace(format, argument1, argument2);
        logger.trace(cause.getMessage(), cause);
    }
    
    @Override
    public void trace(Throwable cause, String format, Object... arguments) {
        Preconditions.checkNotNull(cause, "Cause is null!");
        Preconditions.checkNotNull(format, "Format is null!");
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        
        logger.trace(format, arguments);
        logger.trace(cause.getMessage(), cause);
    }
}
