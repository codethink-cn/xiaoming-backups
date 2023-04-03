package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.common.NumericalId;
import cn.codethink.xiaoming.common.StringId;
import cn.codethink.xiaoming.common.Time;

/**
 * <h1>小明内部</h1>
 *
 * <p>小明内部是 xiaoming-api 调用 xiaoming-core 的桥梁。</p>
 *
 * @author Chuanwise
 */
public interface API {
    Time getTimeOfMilliseconds(long milliseconds);
    Time getTimeOfSeconds(long seconds);
    
    NumericalId getZeroNumericalId();
    NumericalId getNumericalId(String value);
    NumericalId getNumericalId(byte value);
    NumericalId getNumericalId(short value);
    NumericalId getNumericalId(int value);
    NumericalId getNumericalId(long value);
    NumericalId getNumericalId(float value);
    NumericalId getNumericalId(double value);
    
    StringId getEmptyStringId();
    StringId getStringId(String value);
}
