package cn.codethink.xiaoming.common;

import cn.codethink.common.util.Preconditions;
import cn.codethink.xiaoming.annotation.BotThreadUnsafeAPI;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;
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
        Preconditions.argument(milliseconds >= 0, "Milliseconds must be greater than or equals to 0!");
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
        Preconditions.objectNonNull(dateFormat, "Date format");
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