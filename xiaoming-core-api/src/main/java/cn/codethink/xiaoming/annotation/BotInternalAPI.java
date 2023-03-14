package cn.codethink.xiaoming.annotation;

import java.lang.annotation.*;

/**
 * <h1>机器人内部 API</h1>
 *
 * <p>机器人内部 API 是不应该被小明核心组件开发者之外的人调用的 API。这些 API 可能在任何时候被改动
 * 而不提前通知。除非你正在开发小明核心组件，否则 <b>不要在你代码中的任何位置调用这些 API</b>。</p>
 *
 * @author Chuanwise
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface BotInternalAPI {
}
