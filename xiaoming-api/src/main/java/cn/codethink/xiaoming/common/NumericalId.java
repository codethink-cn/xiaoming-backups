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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <h1>数字标识符</h1>
 *
 * @author Chuanwise
 */
public interface NumericalId
    extends Id, Comparable<NumericalId> {
    
    /**
     * 构造零数字标识符
     *
     * @return 零数字标识符
     */
    static NumericalId zero() {
        return APIFactory.getInstance().getZeroNumericalId();
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(byte value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(short value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(int value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(long value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(float value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(double value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 构造数字标识符
     *
     * @param value 数字
     * @return 数字标识符
     */
    static NumericalId of(String value) {
        return APIFactory.getInstance().getNumericalId(value);
    }
    
    /**
     * 转化为字节
     *
     * @return 字节
     */
    Byte toByte();
    
    /**
     * 转化为精确字节
     *
     * @return 精确字节
     */
    Byte toExactByte();
    
    /**
     * 转化为字节数值
     *
     * @return 字节数值
     */
    byte toByteValue();
    
    /**
     * 转化为精确字节数值
     *
     * @return 精确字节数值
     */
    byte toExactByteValue();
    
    /**
     * 转化为短整数
     *
     * @return 短整数
     */
    Short toShort();
    
    /**
     * 转化为精确短整数
     *
     * @return 精确短整数
     */
    Short toExactShort();
    
    /**
     * 转化为短整数数值
     *
     * @return 短整数数值
     */
    short toShortValue();
    
    /**
     * 转化为精确短整数数值
     *
     * @return 精确短整数数值
     */
    short toExactShortValue();
    
    /**
     * 转化为整数
     *
     * @return 整数
     */
    Integer toInt();
    
    /**
     * 转化为精确整数
     *
     * @return 精确整数
     */
    Integer toExactInt();
    
    /**
     * 转化为整数数值
     *
     * @return 整数数值
     */
    int toIntValue();
    
    /**
     * 转化为精确整数数值
     *
     * @return 精确整数数值
     */
    int toExactIntValue();
    
    /**
     * 转化为长整数
     *
     * @return 长整数
     */
    Long toLong();
    
    /**
     * 转化为精确长整数
     *
     * @return 精确长整数
     */
    Long toExactLong();
    
    /**
     * 转化为长整数数值
     *
     * @return 长整数数值
     */
    long toLongValue();
    
    /**
     * 转化为精确长整数数值
     *
     * @return 精确长整数数值
     */
    long toExactLongValue();
    
    /**
     * 转化为浮点数
     *
     * @return 浮点数
     */
    Float toFloat();
    
    /**
     * 转化为浮点数数值
     *
     * @return 浮点数数值
     */
    float toFloatValue();
    
    /**
     * 转化为双精度浮点数
     *
     * @return 双精度浮点数
     */
    Double toDouble();
    
    /**
     * 转化为浮点数数值
     *
     * @return 双精度浮点数数值
     */
    double toDoubleValue();
    
    /**
     * 转化为大整数
     *
     * @return 大整数
     */
    BigInteger toBigInteger();
    
    /**
     * 转化为精确大整数
     *
     * @return 精确大整数
     */
    BigInteger toExactBigInteger();
    
    /**
     * 转化为大小数
     *
     * @return 大小数
     */
    BigDecimal toBigDecimal();
}