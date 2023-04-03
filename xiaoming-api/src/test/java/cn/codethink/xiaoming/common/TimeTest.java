package cn.codethink.xiaoming.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeTest {
    
    @Test
    public void testMillisecondsTime() {
        final int seconds = 162;
        final int milliseconds = seconds * 1000;
        final Time millisecondsTime = Time.ofMilliseconds(milliseconds);
        
        Assertions.assertEquals(milliseconds, millisecondsTime.toMilliseconds());
        Assertions.assertEquals(seconds, millisecondsTime.toSeconds());
    }
}
