package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

import java.text.DateFormat;
import java.util.Date;

/**
 * <h1>时间</h1>
 *
 * @author Chuanwise
 */
public interface Time {
    
    static Time ofSeconds(long seconds) {
        return APIFactory.getInstance().getTimeOfSeconds(seconds);
    }
    
    static Time ofMilliseconds(long milliseconds) {
        return APIFactory.getInstance().getTimeOfSeconds(milliseconds);
    }
    
    long toSeconds();
    
    long toMilliseconds();
    
    Date toDate();
    
    String format(DateFormat dateFormat);
}
