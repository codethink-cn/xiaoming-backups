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

package cn.codethink.xiaoming.logger;

/**
 * <h1>日志</h1>
 *
 * @author Chuanwise
 */
public interface Logger {
    
    /**
     * 获取是否启用某一级别日志
     *
     * @param level 日志级别
     * @return 日志级别是否启用
     */
    boolean isEnabledForLevel(Level level);
    
    /**
     * 获取错误级别是否启用
     *
     * @return 错误级别是否启用
     */
    boolean isErrorEnabled();
    
    /**
     * 打印一条错误日志
     *
     * @param message 消息
     */
    void error(String message);
    
    /**
     * 打印一条错误日志
     *
     * @param format   格式
     * @param argument 参数
     */
    void error(String format, Object argument);
    
    /**
     * 打印一条错误日志
     *
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void error(String format, Object argument1, Object argument2);
    
    /**
     * 打印一条错误日志
     *
     * @param format    格式
     * @param arguments 参数
     */
    void error(String format, Object... arguments);
    
    /**
     * 打印一条错误日志
     *
     * @param cause 异常
     */
    void error(Throwable cause);
    
    /**
     * 打印一条错误日志
     *
     * @param cause   异常
     * @param message 消息
     */
    void error(Throwable cause, String message);
    
    /**
     * 打印一条错误日志
     *
     * @param cause    异常
     * @param format   格式
     * @param argument 参数
     */
    void error(Throwable cause, String format, Object argument);
    
    /**
     * 打印一条错误日志
     *
     * @param cause     异常
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void error(Throwable cause, String format, Object argument1, Object argument2);
    
    /**
     * 打印一条错误日志
     *
     * @param cause     异常
     * @param format    格式
     * @param arguments 参数
     */
    void error(Throwable cause, String format, Object... arguments);
    
    /**
     * 获取警告级别是否启用
     *
     * @return 警告级别是否启用
     */
    boolean isWarnEnabled();
    
    /**
     * 打印一条警告日志
     *
     * @param message 消息
     */
    void warn(String message);
    
    /**
     * 打印一条警告日志
     *
     * @param format   格式
     * @param argument 参数
     */
    void warn(String format, Object argument);
    
    /**
     * 打印一条警告日志
     *
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void warn(String format, Object argument1, Object argument2);
    
    /**
     * 打印一条警告日志
     *
     * @param format    格式
     * @param arguments 参数
     */
    void warn(String format, Object... arguments);
    
    /**
     * 打印一条警告日志
     *
     * @param cause 异常
     */
    void warn(Throwable cause);
    
    /**
     * 打印一条警告日志
     *
     * @param cause   异常
     * @param message 消息
     */
    void warn(Throwable cause, String message);
    
    /**
     * 打印一条警告日志
     *
     * @param cause    异常
     * @param format   格式
     * @param argument 参数
     */
    void warn(Throwable cause, String format, Object argument);
    
    /**
     * 打印一条警告日志
     *
     * @param cause     异常
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void warn(Throwable cause, String format, Object argument1, Object argument2);
    
    /**
     * 打印一条警告日志
     *
     * @param cause     异常
     * @param format    格式
     * @param arguments 参数
     */
    void warn(Throwable cause, String format, Object... arguments);
    
    /**
     * 获取信息级别是否启用
     *
     * @return 信息级别是否启用
     */
    boolean isInfoEnabled();
    
    /**
     * 打印一条信息日志
     *
     * @param message 消息
     */
    void info(String message);
    
    /**
     * 打印一条信息日志
     *
     * @param format   格式
     * @param argument 参数
     */
    void info(String format, Object argument);
    
    /**
     * 打印一条信息日志
     *
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void info(String format, Object argument1, Object argument2);
    
    /**
     * 打印一条信息日志
     *
     * @param format    格式
     * @param arguments 参数
     */
    void info(String format, Object... arguments);
    
    /**
     * 打印一条信息日志
     *
     * @param cause 异常
     */
    void info(Throwable cause);
    
    /**
     * 打印一条信息日志
     *
     * @param cause   异常
     * @param message 消息
     */
    void info(Throwable cause, String message);
    
    /**
     * 打印一条信息日志
     *
     * @param cause    异常
     * @param format   格式
     * @param argument 参数
     */
    void info(Throwable cause, String format, Object argument);
    
    /**
     * 打印一条信息日志
     *
     * @param cause     异常
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void info(Throwable cause, String format, Object argument1, Object argument2);
    
    /**
     * 打印一条信息日志
     *
     * @param cause     异常
     * @param format    格式
     * @param arguments 参数
     */
    void info(Throwable cause, String format, Object... arguments);
    
    /**
     * 获取调试级别是否启用
     *
     * @return 调试级别是否启用
     */
    boolean isDebugEnabled();
    
    /**
     * 打印一条调试日志
     *
     * @param message 消息
     */
    void debug(String message);
    
    /**
     * 打印一条调试日志
     *
     * @param format   格式
     * @param argument 参数
     */
    void debug(String format, Object argument);
    
    /**
     * 打印一条调试日志
     *
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void debug(String format, Object argument1, Object argument2);
    
    /**
     * 打印一条调试日志
     *
     * @param format    格式
     * @param arguments 参数
     */
    void debug(String format, Object... arguments);
    
    /**
     * 打印一条调试日志
     *
     * @param cause 异常
     */
    void debug(Throwable cause);
    
    /**
     * 打印一条调试日志
     *
     * @param cause   异常
     * @param message 消息
     */
    void debug(Throwable cause, String message);
    
    /**
     * 打印一条调试日志
     *
     * @param cause    异常
     * @param format   格式
     * @param argument 参数
     */
    void debug(Throwable cause, String format, Object argument);
    
    /**
     * 打印一条调试日志
     *
     * @param cause     异常
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void debug(Throwable cause, String format, Object argument1, Object argument2);
    
    /**
     * 打印一条调试日志
     *
     * @param cause     异常
     * @param format    格式
     * @param arguments 参数
     */
    void debug(Throwable cause, String format, Object... arguments);
    
    /**
     * 获取轨迹级别是否启用
     *
     * @return 轨迹级别是否启用
     */
    boolean isTraceEnabled();
    
    /**
     * 打印一条轨迹日志
     *
     * @param message 消息
     */
    void trace(String message);
    
    /**
     * 打印一条轨迹日志
     *
     * @param format   格式
     * @param argument 参数
     */
    void trace(String format, Object argument);
    
    /**
     * 打印一条轨迹日志
     *
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void trace(String format, Object argument1, Object argument2);
    
    /**
     * 打印一条轨迹日志
     *
     * @param format    格式
     * @param arguments 参数
     */
    void trace(String format, Object... arguments);
    
    /**
     * 打印一条轨迹日志
     *
     * @param cause 异常
     */
    void trace(Throwable cause);
    
    /**
     * 打印一条轨迹日志
     *
     * @param cause   异常
     * @param message 消息
     */
    void trace(Throwable cause, String message);
    
    /**
     * 打印一条轨迹日志
     *
     * @param cause    异常
     * @param format   格式
     * @param argument 参数
     */
    void trace(Throwable cause, String format, Object argument);
    
    /**
     * 打印一条轨迹日志
     *
     * @param cause     异常
     * @param format    格式
     * @param argument1 参数 1
     * @param argument2 参数 2
     */
    void trace(Throwable cause, String format, Object argument1, Object argument2);
    
    /**
     * 打印一条轨迹日志
     *
     * @param cause     异常
     * @param format    格式
     * @param arguments 参数
     */
    void trace(Throwable cause, String format, Object... arguments);
}