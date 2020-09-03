package com.ruishan.ruixiao.test;

import com.ruishan.ruixiao.callback.FMSGCallBack_V31;
import com.ruishan.ruixiao.utils.HCNetDeviceUtil;

public class Test {
    public static void main(String[] args) {
        //登录设备
        int login = HCNetDeviceUtil.Login("192.168.1.20", (short) 8000, "admin", "ab123456");

        System.out.println(login);
        //获取设备上所有人员信息
        HCNetDeviceUtil.getAllCardInfo();

        //设立布防
        //HCNetDeviceUtil.setAlarm();

        //设立卡计划模板
        //HCNetDeviceUtil.setCardPower(2);


        //权限计划，取值为计划模板编号，同个门（锁）不同计划模板采用权限或的方式处理
        //HCNetDeviceUtil.setEmployee(3,"3297437333","溜溜球3号","99764360",(byte) 1,(byte) 6,(short) 2);

        //根据卡号下发人脸
        //HCNetDeviceUtil.setFaceInfo("3297437333",".\\face\\IMG_1334.JPG");

        //根据卡号删除人脸
        //HCNetDeviceUtil.deleteFace("3297437333");

        //删除卡号
        //HCNetDeviceUtil.deleteCard("3297437333");

        //获取人脸
        //HCNetDeviceUtil.getFace("3297437333");

        //远程开门
        //HCNetDeviceUtil.openDoor();
    }
}
