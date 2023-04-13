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

package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.message.Message;

/**
 * <h1>消息片段</h1>
 *
 * <p>消息片段是消息中可包含的最小元素，如一段文本 {@link Text}、一张图片 {@link Image}</p>
 *
 * @author Chuanwise
 */
public interface MessageContent
    extends Message {
}
