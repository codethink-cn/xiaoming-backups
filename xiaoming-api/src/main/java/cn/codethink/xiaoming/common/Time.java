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
