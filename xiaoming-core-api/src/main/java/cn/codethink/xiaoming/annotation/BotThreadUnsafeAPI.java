package cn.codethink.xiaoming.annotation;

import java.lang.annotation.*;

/**
 * <h1>机器人线程不安全 API</h1>
 *
 * <p>机器人线程不安全 API 是虽然线程不安全的，但并不影响功能。并发环境下使用时，造成的影响
 * 最多只是于多创建了几个对象。在此处采用线程不安全的实现是为了提升运行速度。</p>
 *
 * <ui>
 *     <li>当一个方法被该注解修饰，意味着该方法是线程不安全 API。</li>
 *     <li>当一个类或接口被该注解修饰，意味着该类或接口及其所有子类都是线程不安全 API。</li>
 *     <li>当一个属性被该注解修饰，意味着该属性相关的方法都是线程不安全 API。</li>
 * </ui>
 *
 * @author Chuanwise
 */
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface BotThreadUnsafeAPI {
}
