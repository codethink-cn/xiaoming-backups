package cn.codethink.xiaoming.annotation;

import java.lang.annotation.*;

/**
 * <h1>机器人线程不安全 API</h1>
 *
 * <p>机器人线程不安全 API 是虽然线程不安全的，但并不影响功能。并发环境下使用时，造成的影响
 * 最多只是于多创建了几个对象。在此处采用线程不安全的实现是为了提升运行速度。</p>
 *
 * @author Chuanwise
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface BotThreadUnsafeAPI {
}
