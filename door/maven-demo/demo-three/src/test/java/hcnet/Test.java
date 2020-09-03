package hcnet;

import com.ruishan.ruixiao.utils.HCNetDeviceUtil;

public class Test {
    public static void main(String[] args) {
        //登录设备
        HCNetDeviceUtil.login("192.168.1.20",(short) 8000,"admin","ab123456");

        //下发人员信息（不包括人脸）
        //HCNetDeviceUtil.setUser(3,"溜溜球3号","3297437336","123456",(short)1,(byte)6,(byte) 1);

        //根据卡号下发人员信息
        //HCNetDeviceUtil.setFaceInfo("3297437333",".\\face\\IMG_1334.JPG");

        //根据卡号删除人脸
        //HCNetDeviceUtil.deleteFace("3297437333");

        //删除卡号
        //HCNetDeviceUtil.deleteCard("3297437333");

        //查询设备上所有信息
        HCNetDeviceUtil.getAllUserInfo();
    }
}
