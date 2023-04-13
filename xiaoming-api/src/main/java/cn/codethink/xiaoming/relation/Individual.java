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

package cn.codethink.xiaoming.relation;

import cn.codethink.xiaoming.Bot;
import cn.codethink.xiaoming.common.BotObject;

/**
 * <h1>个体</h1>
 *
 * <p>个体指一对一的关系，可能为机器人自己 {@link Bot}，也可能为用户 {@link User}。</p>
 *
 * <p>个体不一定是可知的（如匿名群友），因此没有发送消息相关的 API。</p>
 *
 * @author Chuanwise
 */
public interface Individual
    extends Relation {
}
