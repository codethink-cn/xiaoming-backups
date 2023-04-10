package cn.codethink.xiaoming.api;

import cn.codethink.xiaoming.common.*;
import cn.codethink.xiaoming.message.segment.*;

import java.util.concurrent.TimeUnit;

public class APIImpl
    implements API {
    
    @Override
    public Time getTimeOfMilliseconds(long milliseconds) {
        return new TimeImpl(milliseconds);
    }
    
    @Override
    public Time getTimeOfSeconds(long seconds) {
        return new TimeImpl(TimeUnit.MILLISECONDS.toSeconds(seconds));
    }
    
    @Override
    public NumericalId getZeroNumericalId() {
        return NumericalIdImpl.ZERO;
    }
    
    @Override
    public NumericalId getNumericalId(String value) {
        return NumericalIdImpl.of(value);
    }
    
    @Override
    public NumericalId getNumericalId(byte value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(short value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(int value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(long value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(float value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public NumericalId getNumericalId(double value) {
        return NumericalIdImpl.of(String.valueOf(value));
    }
    
    @Override
    public StringId getEmptyStringId() {
        return StringIdImpl.EMPTY;
    }
    
    @Override
    public StringId getStringId(String value) {
        return StringIdImpl.of(value);
    }
    
    @Override
    public Text getText(String text) {
        return new TextImpl(text);
    }
    
    @Override
    public At getAt(Id id) {
        return new AtImpl(id);
    }
    
    @Override
    public AtAll getAtAll() {
        return AtAllImpl.getInstance();
    }
}
