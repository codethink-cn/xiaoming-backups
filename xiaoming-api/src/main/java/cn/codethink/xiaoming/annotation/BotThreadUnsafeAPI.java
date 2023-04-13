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
