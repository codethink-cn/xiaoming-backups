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

import cn.codethink.xiaoming.annotation.BotThreadUnsafeAPI;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;

public class NumericalIdImpl
    implements NumericalId {
    
    public static final NumericalIdImpl ZERO = new NumericalIdImpl(BigDecimal.ZERO, "0");
    
    private final BigDecimal bigDecimal;
    
    private final String string;
    
    @BotThreadUnsafeAPI
    private BigInteger inexactBigInteger, exactBigInteger;
    
    @BotThreadUnsafeAPI
    private Byte inexactByte, exactByte;
    
    @BotThreadUnsafeAPI
    private Short inexactShort, exactShort;
    
    @BotThreadUnsafeAPI
    private Integer inexactInteger, exactInteger;
    
    @BotThreadUnsafeAPI
    private Long inexactLong, exactLong;
    
    @BotThreadUnsafeAPI
    private Float inexactFloat;
    
    @BotThreadUnsafeAPI
    private Double inexactDouble;
    
    @BotThreadUnsafeAPI
    private Integer hashCode;
    
    public static NumericalId of(String string) {
        Preconditions.checkNotNull(string, "String is null!");
        
        final BigDecimal bigDecimal = new BigDecimal(string);
        if (bigDecimal.equals(BigDecimal.ZERO)) {
            return ZERO;
        } else {
            return new NumericalIdImpl(bigDecimal, string);
        }
    }
    
    private NumericalIdImpl(BigDecimal bigDecimal, String string) {
        this.bigDecimal = bigDecimal;
        this.string = string;
    }
    
    @Override
    public Byte toByte() {
        if (inexactByte == null) {
            inexactByte = bigDecimal.byteValue();
        }
        return inexactByte;
    }
    
    @Override
    public Byte toExactByte() {
        if (exactByte == null) {
            exactByte = bigDecimal.byteValueExact();
        }
        return exactByte;
    }
    
    @Override
    public byte toByteValue() {
        return toByte();
    }
    
    @Override
    public byte toExactByteValue() {
        return toExactByte();
    }
    
    @Override
    public Short toShort() {
        if (inexactShort == null) {
            inexactShort = bigDecimal.shortValue();
        }
        return inexactShort;
    }
    
    @Override
    public Short toExactShort() {
        if (exactShort == null) {
            exactShort = bigDecimal.shortValueExact();
        }
        return exactShort;
    }
    
    @Override
    public short toShortValue() {
        return toShort();
    }
    
    @Override
    public short toExactShortValue() {
        return toExactShort();
    }
    
    @Override
    public Integer toInt() {
        if (inexactInteger == null) {
            inexactInteger = bigDecimal.intValue();
        }
        return inexactInteger;
    }
    
    @Override
    public Integer toExactInt() {
        if (exactInteger == null) {
            exactInteger = bigDecimal.intValueExact();
        }
        return exactInteger;
    }
    
    @Override
    public int toIntValue() {
        return toInt();
    }
    
    @Override
    public int toExactIntValue() {
        return toExactInt();
    }
    
    @Override
    public Long toLong() {
        if (inexactLong == null) {
            inexactLong = bigDecimal.longValue();
        }
        return inexactLong;
    }
    
    @Override
    public Long toExactLong() {
        if (exactLong == null) {
            exactLong = bigDecimal.longValueExact();
        }
        return exactLong;
    }
    
    @Override
    public long toLongValue() {
        return toLong();
    }
    
    @Override
    public long toExactLongValue() {
        return toExactLong();
    }
    
    @Override
    public Float toFloat() {
        if (inexactFloat == null) {
            inexactFloat = bigDecimal.floatValue();
        }
        return inexactFloat;
    }
    
    @Override
    public float toFloatValue() {
        return toFloat();
    }
    
    @Override
    public Double toDouble() {
        if (inexactDouble == null) {
            inexactDouble = bigDecimal.doubleValue();
        }
        return inexactDouble;
    }
    
    @Override
    public double toDoubleValue() {
        return toDouble();
    }
    
    @Override
    public BigInteger toBigInteger() {
        if (inexactBigInteger == null) {
            inexactBigInteger = bigDecimal.toBigInteger();
        }
        return inexactBigInteger;
    }
    
    @Override
    public BigInteger toExactBigInteger() {
        if (exactBigInteger == null) {
            exactBigInteger = bigDecimal.toBigIntegerExact();
        }
        return exactBigInteger;
    }
    
    @Override
    public BigDecimal toBigDecimal() {
        return bigDecimal;
    }
    
    @Override
    public int compareTo(@Nonnull NumericalId id) {
        Preconditions.checkNotNull(id, "Numerical id is null!");
        return bigDecimal.compareTo(id.toBigDecimal());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NumericalIdImpl that = (NumericalIdImpl) o;
        return bigDecimal.equals(that.bigDecimal);
    }
    
    @Override
    public int hashCode() {
        if (hashCode == null) {
            hashCode = bigDecimal.hashCode();
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        return string;
    }
}