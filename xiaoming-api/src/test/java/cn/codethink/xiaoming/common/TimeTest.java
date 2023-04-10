package cn.codethink.xiaoming.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class TimeTest {
    
    @Test
    public void testMillisecondsTime() {
        final int seconds = 162;
        final int milliseconds = seconds * 1000;
        final Time millisecondsTime = Time.ofMilliseconds(milliseconds);
        
        Assertions.assertEquals(milliseconds, millisecondsTime.toMilliseconds());
        Assertions.assertEquals(seconds, millisecondsTime.toSeconds());
    }
    
    @Test
    public void md5() throws NoSuchAlgorithmException {
        final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        final byte[] digest = sha256.digest("qadasda".getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(digest));
        System.out.println(digest.length);
        final String base64 = new String(Base64.getEncoder().encode(digest));
        System.out.println(base64);
        System.out.println(base64.length());
    }
}
