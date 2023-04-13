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

package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.annotation.BotThreadUnsafeAPI;
import com.google.common.base.Preconditions;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeImpl
    implements Time {
    
    private final long milliseconds;
    private final long seconds;
    
    @BotThreadUnsafeAPI
    private Date date;
    
    @BotThreadUnsafeAPI
    private String toString;
    
    @BotThreadUnsafeAPI
    private Integer hashCode;
    
    public TimeImpl(long milliseconds) {
        Preconditions.checkArgument(milliseconds >= 0, "Milliseconds must be greater than or equals to 0!");
        this.milliseconds = milliseconds;
        this.seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
    }
    
    @Override
    public long toSeconds() {
        return seconds;
    }
    
    @Override
    public long toMilliseconds() {
        return milliseconds;
    }
    
    @Override
    public Date toDate() {
        if (date == null) {
            date = new Date(milliseconds);
        }
        return date;
    }
    
    @Override
    public String format(DateFormat dateFormat) {
        Preconditions.checkNotNull(dateFormat, "Date format is null!");
        return dateFormat.format(toDate());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TimeImpl time = (TimeImpl) o;
        return milliseconds == time.milliseconds;
    }
    
    @Override
    public int hashCode() {
        if (hashCode == null) {
            hashCode = Long.hashCode(milliseconds);
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        if (toString == null) {
            toString = format(DateFormat.getInstance());
        }
        return toString;
    }
}