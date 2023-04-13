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
