package com.example.consumerTest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class Base64UtilTest {

    @Test
    public void testBase64() {
        String str = "{openId:\"8a70f4636ce24f18016d43ea0cc24dc5\",nickName:\"不三不四\",sex:\"男\",headImg:\"http://wx.qlogo.cn/mmopen/wprMnqDUJH7w4OLRMH4fPFwrF5AeGFZKU1FkezVthLtlzrbpc7CBiaDibh35YRUk5hbjWPITNlRHBzxLOMjhQYlg/0\"}";
        String result = Base64.encodeBase64String(str.getBytes(StandardCharsets.UTF_8));
        Assert.assertNotNull(result);
        byte[] decodeByte = Base64.decodeBase64(result);
        String decodeStr = new String(decodeByte, StandardCharsets.UTF_8);
        Assert.assertArrayEquals(str.getBytes(), decodeByte);
        Assert.assertNotNull(decodeStr);

    }
}
