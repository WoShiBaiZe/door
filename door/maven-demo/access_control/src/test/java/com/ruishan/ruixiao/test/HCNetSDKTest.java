package com.ruishan.ruixiao.test;

import com.ruishan.ruixiao.utils.HCNetDeviceUtil;
import org.junit.Test;

public class HCNetSDKTest {

    /**
     * 测试设备登录
     */
    @Test
    public void testLogin() {
        int login = HCNetDeviceUtil.login("192.168.1.20", "admin", "ab123456", (short) 8000);
        System.out.println(login);
    }
}
