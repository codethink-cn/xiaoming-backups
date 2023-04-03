package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

import java.text.DateFormat;
import java.util.Date;

/**
 * <h1>时间</h1>
 *
 * <p>表示某件事发生的具体时间</p>
 *
 * @author Chuanwise
 */
public interface Time {
    
    /**
     * 构造秒时间
     *
     * @param seconds 秒数
     * @return 时间
     */
    static Time ofSeconds(long seconds) {
        return APIFactory.getInstance().getTimeOfSeconds(seconds);
    }
    
    /**
     * 构造毫秒时间
     *
     * @param milliseconds 毫秒数
     * @return 时间
     */
    static Time ofMilliseconds(long milliseconds) {
        return APIFactory.getInstance().getTimeOfSeconds(milliseconds);
    }
    
    /**
     * 转化为秒
     *
     * @return 秒
     */
    long toSeconds();
    
    /**
     * 转化为毫秒
     *
     * @return 毫秒
     */
    long toMilliseconds();
    
    /**
     * 转化为日期
     *
     * @return 日期
     */
    Date toDate();
    
    /**
     * 格式化
     *
     * @param dateFormat 日期格式
     * @return 格式化后的字符串
     */
    String format(DateFormat dateFormat);
}
