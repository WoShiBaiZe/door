package com.ruishan.ruixiao.utils;

import com.ruishan.ruixiao.callback.FMSGCallBack_V31;
import com.ruishan.ruixiao.callback.impl.FRemoteCfgCallBackFaceGet;
import com.ruishan.ruixiao.hcnet.HCNetSDK;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import javax.swing.*;
import java.io.*;

/**
 * HCNetSDK工具类
 */
public class HCNetDeviceUtil {

    //加载HCNet.dll文件（HCNetSDK.INSTANCE）
    static HCNetSDK hcNetSDK = HCNetSDK.INSTANCE;

    //用户ID，NET_DVR_Login_V40的返回值，设立初始值为 -1
    static int lUserID = -1;

    //开启长连接，设置长连接句柄的初始值
    static int m_lSetCardCfgHandle = -1;
    //下发卡数据状态
    static int dwState = -1;
    //下发人脸长连接句柄
    static int m_lSetFaceCfgHandle = -1;
    //下发人脸数据状态
    static int dwFaceState = -1;
    //人脸回调
    private static FRemoteCfgCallBackFaceGet fRemoteCfgCallBackFaceGet;
    //回调
    private static FMSGCallBack_V31 fMSFCallBack_V31;
    //宏定义每周时间
    static int MAX_DAYS = 7;
    //设备最大时间段数
    static int MAX_TIMESEGMENT_V30 = 8;
    //设备字符集
    static int iCharEncodeType = 0;
    //卡计划模板
    static int cardTemplateNo = 0;


    /**
     * 登录设备
     *
     * @param ip       设备IP地址
     * @param port     设备端口号
     * @param userName 登录设备用户名
     * @param password 登录设备密码
     * @return
     */
    public static int Login(String ip, short port, String userName, String password) {
        //初始化SDK（NET_DVR_Init()）
        boolean dvrInit = hcNetSDK.NET_DVR_Init();
        //当初始化不等于true，就初始化失败，打印错误码
        if (dvrInit != true) {
            System.out.println("SDK初始化失败，操作错误码是：" + hcNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("SDK初始化成功，成功操作码为：" + hcNetSDK.NET_DVR_GetLastError());
        }
        //注册设备
        //设备信息
        HCNetSDK.NET_DVR_USER_LOGIN_INFO netDvrUserLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();
        //设备登录信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 netDvrDeviceinfoV40 = new HCNetSDK.NET_DVR_DEVICEINFO_V40();

        //设备IP地址
        String m_sDeviceIP = ip;
        netDvrUserLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, netDvrUserLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        //设备用户名
        String m_sUsername = userName;
        netDvrUserLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, netDvrUserLoginInfo.sUserName, 0, m_sUsername.length());

        //设备密码
        String m_sPassword = password;
        netDvrUserLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, netDvrUserLoginInfo.sPassword, 0, m_sPassword.length());

        //设备端口号
        netDvrUserLoginInfo.wPort = port;

        //用户注册（NET_DVR_Login_V40）
        lUserID = hcNetSDK.NET_DVR_Login_V40(netDvrUserLoginInfo, netDvrDeviceinfoV40);

        //设置连接超时时间与重连功能
        hcNetSDK.NET_DVR_SetConnectTime(2000,1);
        hcNetSDK.NET_DVR_SetReconnect(10000, true);

        //判断设备是否注册成功
        if (lUserID == -1) {
            System.out.println("用户注册失败，操作错误码是：" + hcNetSDK.NET_DVR_GetLastError());
        }

        return lUserID;
    }

    /**
     * 获取设备上全部用户信息
     */
    public static void getAllCardInfo() {
        //配置卡的参数条件
        HCNetSDK.NET_DVR_CARD_COND netDvrCardCond = new HCNetSDK.NET_DVR_CARD_COND();
        //获取卡的大小
        netDvrCardCond.read();
        netDvrCardCond.dwSize = netDvrCardCond.size();
        //设置或获取卡数量，获取时置为0xffffffff表示获取所有卡信息
        netDvrCardCond.dwCardNum = 0xffffffff;
        //设备是否进行卡号校验：0- 不校验，1- 校验
        //netDvrCardCond.byCheckCardNo = 1;
        //设置卡参数体
        netDvrCardCond.write();
        Pointer ptrStruCond = netDvrCardCond.getPointer();

        m_lSetCardCfgHandle = hcNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_CARD, ptrStruCond, netDvrCardCond.size(), null, null);

        if (m_lSetCardCfgHandle == -1) {
            System.out.println("建立下发卡长连接失败，错误码为：" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("建立下发卡长连接成功！");
        }

        HCNetSDK.NET_DVR_CARD_RECORD struCardRecord = new HCNetSDK.NET_DVR_CARD_RECORD();
        struCardRecord.read();
        struCardRecord.dwSize = struCardRecord.size();
        struCardRecord.write();

        IntByReference pInt = new IntByReference(0);

        //设置下发卡数据状态初始值
        int dwState = -1;


        //循环获取卡（NET_DVR_GetNextRemoteConfig）

            dwState = hcNetSDK.NET_DVR_GetNextRemoteConfig(m_lSetCardCfgHandle, struCardRecord.getPointer(), struCardRecord.size());
            struCardRecord.read();
            if (dwState == -1) {
                System.out.println("NET_DVR_SendWithRecvRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
//                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_NEEDWAIT) {
                System.out.println("配置等待");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FAILED) {
                System.out.println("获取卡参数失败");
//                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_EXCEPTION) {
                System.out.println("获取卡参数异常");
//                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_SUCCESS) {
                try {
                    System.out.println();
                    System.out.println("获取卡参数成功, 卡号: " + new String(struCardRecord.byCardNo).trim()
                            + ", 工号：" + struCardRecord.dwEmployeeNo
                            + ", 卡类型：" + struCardRecord.byCardType
                            + ", 姓名：" + new String(struCardRecord.byName, "GBK").trim()
                            + ", 密码：" + new String(struCardRecord.byCardPassword, "GBK").trim()
                            + ", 用户权限：" + struCardRecord.byUserType
                            + ", 卡计划模板：" + struCardRecord.wCardRightPlan[cardTemplateNo]
                            + ", 已刷卡次数：" + struCardRecord.dwSwipeTimes
                            + ", 最大刷卡次数：" + struCardRecord.dwMaxSwipeTimes
                            + ", 卡权限：" + struCardRecord.dwCardRight);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FINISH) {
                System.out.println("获取卡参数完成");
//                break;

        }

        if (!hcNetSDK.NET_DVR_StopRemoteConfig(m_lSetCardCfgHandle)) {
            System.out.println("NET_DVR_StopRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_StopRemoteConfig接口成功");
        }
    }

    /**
     * 设置人员
     *
     * @param empNo     工号（可以为空）
     * @param strCardNo 卡号
     * @param empName   姓名
     * @param password  密码
     * @param userType  用户类型
     * @param cardType  卡类型
     */
    public static void setEmployee(int empNo, String strCardNo, String empName, String password, byte userType, byte cardType,short templateNo) {

        //卡参数配置条件
        HCNetSDK.NET_DVR_CARD_COND struCardCond = new HCNetSDK.NET_DVR_CARD_COND();
        struCardCond.read();
        //卡结构体大小
        struCardCond.dwSize = struCardCond.size();
        //下发一张
        struCardCond.dwCardNum = 1;
        struCardCond.write();
        Pointer ptrStruCond = struCardCond.getPointer();

        //配置长连接
        m_lSetCardCfgHandle = hcNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_SET_CARD, ptrStruCond, struCardCond.size(), null, null);
        if (m_lSetCardCfgHandle == -1) {
            System.out.println("建立下发卡长连接失败，错误码为" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("建立下发卡长连接成功！");
        }

        HCNetSDK.NET_DVR_CARD_RECORD struCardRecord = new HCNetSDK.NET_DVR_CARD_RECORD();
        struCardRecord.read();
        struCardRecord.dwSize = struCardRecord.size();

        for (int i = 0; i < HCNetSDK.ACS_CARD_NO_LEN; i++) {
            struCardRecord.byCardNo[i] = 0;
        }
        for (int i = 0; i < strCardNo.length(); i++) {
            struCardRecord.byCardNo[i] = strCardNo.getBytes()[i];
        }

        //普通卡 1- 普通卡（默认），2- 残疾人卡，3- 黑名单卡，4- 巡更卡，
        // 5- 胁迫卡，6- 超级卡，7- 来宾卡，8- 解除卡，9- 员工卡，10- 应急卡，
        // 11- 应急管理卡（用于授权临时卡权限，本身不能开门），默认普通卡
        struCardRecord.byCardType = cardType;
        //是否为首卡，0-否，1-是
        struCardRecord.byLeaderCard = 0;
        //用户类型 0-普通 1-管理员
        struCardRecord.byUserType = userType;
        //门权限（梯控的楼层权限、锁权限），按字节表示，1-为有权限，0-为无权限，从低位到高位依次表示对门（或者梯控楼层、锁）1-N是否有权限
        struCardRecord.byDoorRight[0] = 1;

        //卡有效期使能，下面是卡有效期从2000-1-1 11:11:11到2030-1-1 11:11:11
        struCardRecord.struValid.byEnable = 1;
        struCardRecord.struValid.struBeginTime.wYear = 2000;
        struCardRecord.struValid.struBeginTime.byMonth = 1;
        struCardRecord.struValid.struBeginTime.byDay = 1;
        struCardRecord.struValid.struBeginTime.byHour = 11;
        struCardRecord.struValid.struBeginTime.byMinute = 11;
        struCardRecord.struValid.struBeginTime.bySecond = 11;
        struCardRecord.struValid.struEndTime.wYear = 2030;
        struCardRecord.struValid.struEndTime.byMonth = 1;
        struCardRecord.struValid.struEndTime.byDay = 1;
        struCardRecord.struValid.struEndTime.byHour = 11;
        struCardRecord.struValid.struEndTime.byMinute = 11;
        struCardRecord.struValid.struEndTime.bySecond = 11;

        //卡计划模板1有效，1代表24小时全天有效
        struCardRecord.wCardRightPlan[0] = templateNo;
        //工号
        struCardRecord.dwEmployeeNo = empNo;
        //卡密码
        struCardRecord.byCardPassword = password.getBytes();

        //姓名
        try {
            byte[] strCardName = empName.getBytes("GBK");
            for (int i = 0; i < HCNetSDK.NAME_LEN; i++) {
                struCardRecord.byName[i] = 0;
            }
            for (int i = 0; i < strCardName.length; i++) {
                struCardRecord.byName[i] = strCardName[i];
            }
            struCardRecord.write();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持编译异常");
        }

        HCNetSDK.NET_DVR_CARD_STATUS struCardStatus = new HCNetSDK.NET_DVR_CARD_STATUS();
        struCardStatus.read();
        struCardStatus.dwSize = struCardStatus.size();
        struCardStatus.write();

        IntByReference pInt = new IntByReference(0);

        while (true) {
            dwState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(m_lSetCardCfgHandle, struCardRecord.getPointer(), struCardRecord.size(), struCardStatus.getPointer(), struCardStatus.size(), pInt);
            struCardStatus.read();
            if (dwState == -1) {
                System.out.println("NET_DVR_SendWithRecvRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_NEEDWAIT) {
                System.out.println("配置等待");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FAILED) {
                System.out.println("下发卡失败, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 错误码：" + struCardStatus.dwErrorCode);
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_EXCEPTION) {
                System.out.println("下发卡异常, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 错误码：" + struCardStatus.dwErrorCode);
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_SUCCESS) {
                if (struCardStatus.dwErrorCode != 0) {
                    System.out.println("下发卡成功,但是错误码" + struCardStatus.dwErrorCode + ", 卡号：" + new String(struCardStatus.byCardNo).trim());
                } else {
                    System.out.println("下发卡成功, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 状态：" + struCardStatus.byStatus);
                }
                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FINISH) {
                System.out.println("下发卡完成");
                break;
            }

        }

        //判断长连接是否停止
        if (!hcNetSDK.NET_DVR_StopRemoteConfig(m_lSetCardCfgHandle)) {
            System.out.println("NET_DVR_StopRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_StopRemoteConfig接口成功");
        }
    }

    /**
     * 根据卡号下发人脸
     *
     * @param strCardNo 卡号
     * @param filePath  人脸路径
     */
    public static void setFaceInfo(String strCardNo, String filePath) {
        HCNetSDK.NET_DVR_FACE_COND struFaceCond = new HCNetSDK.NET_DVR_FACE_COND();
        struFaceCond.read();
        struFaceCond.dwSize = struFaceCond.size();
        //下发一张
        struFaceCond.dwFaceNum = 1;
        //人脸读卡器编号
        struFaceCond.dwEnableReaderNo = 1;
        struFaceCond.write();
        Pointer ptrStruFaceCond = struFaceCond.getPointer();

        m_lSetFaceCfgHandle = hcNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_SET_FACE, ptrStruFaceCond, struFaceCond.size(), null, null);
        if (m_lSetFaceCfgHandle == -1) {
            System.out.println("建立下发人脸长连接失败，错误码为" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("建立下发人脸长连接成功！");
        }

        HCNetSDK.NET_DVR_FACE_RECORD struFaceRecord = new HCNetSDK.NET_DVR_FACE_RECORD();
        struFaceRecord.read();
        struFaceRecord.dwSize = struFaceRecord.size();

        for (int i = 0; i < HCNetSDK.ACS_CARD_NO_LEN; i++) {
            struFaceRecord.byCardNo[i] = 0;
        }
        for (int i = 0; i < strCardNo.length(); i++) {
            struFaceRecord.byCardNo[i] = strCardNo.getBytes()[i];
        }

        //从本地文件里面读取JPEG图片二进制数据
        FileInputStream picfile = null;
        int picdataLength = 0;
        try {
            picfile = new FileInputStream(new File(System.getProperty("user.dir") + filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            picdataLength = picfile.available();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (picdataLength < 0) {
            System.out.println("input file dataSize < 0");
            return;
        }

        HCNetSDK.BYTE_ARRAY ptrpicByte = new HCNetSDK.BYTE_ARRAY(picdataLength);
        try {
            picfile.read(ptrpicByte.byValue);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        ptrpicByte.write();
        struFaceRecord.dwFaceLen = picdataLength;
        struFaceRecord.pFaceBuffer = ptrpicByte.getPointer();

        struFaceRecord.write();


        HCNetSDK.NET_DVR_FACE_STATUS struFaceStatus = new HCNetSDK.NET_DVR_FACE_STATUS();
        struFaceStatus.read();
        struFaceStatus.dwSize = struFaceStatus.size();
        struFaceStatus.write();

        IntByReference pInt = new IntByReference(0);

        while (true) {
            dwFaceState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(m_lSetFaceCfgHandle, struFaceRecord.getPointer(), struFaceRecord.size(), struFaceStatus.getPointer(), struFaceStatus.size(), pInt);
            struFaceStatus.read();
            if (dwFaceState == -1) {
                System.out.println("NET_DVR_SendWithRecvRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
                break;
            } else if (dwFaceState == HCNetSDK.NET_SDK_CONFIG_STATUS_NEEDWAIT) {
                System.out.println("配置等待");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (dwFaceState == HCNetSDK.NET_SDK_CONFIG_STATUS_FAILED) {
                System.out.println("下发人脸失败, 卡号: " + new String(struFaceStatus.byCardNo).trim() + ", 错误码：" + hcNetSDK.NET_DVR_GetLastError());
                break;
            } else if (dwFaceState == HCNetSDK.NET_SDK_CONFIG_STATUS_EXCEPTION) {
                System.out.println("下发卡异常, 卡号: " + new String(struFaceStatus.byCardNo).trim() + ", 错误码：" + hcNetSDK.NET_DVR_GetLastError());
                break;
            } else if (dwFaceState == HCNetSDK.NET_SDK_CONFIG_STATUS_SUCCESS) {
                if (struFaceStatus.byRecvStatus != 1) {
                    System.out.println("下发卡失败，人脸读卡器状态" + struFaceStatus.byRecvStatus + ", 卡号：" + new String(struFaceStatus.byCardNo).trim());
                    break;
                } else {
                    System.out.println("下发卡成功, 卡号: " + new String(struFaceStatus.byCardNo).trim() + ", 状态：" + struFaceStatus.byRecvStatus);
                }
                continue;
            } else if (dwFaceState == HCNetSDK.NET_SDK_CONFIG_STATUS_FINISH) {
                System.out.println("下发人脸完成");
                break;
            }

        }

        if (!hcNetSDK.NET_DVR_StopRemoteConfig(m_lSetFaceCfgHandle)) {
            System.out.println("NET_DVR_StopRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_StopRemoteConfig接口成功");
        }
    }

    /**
     * 根据卡号删除人脸
     *
     * @param strCardNo 卡号
     */
    public static void deleteFace(String strCardNo) {
        HCNetSDK.NET_DVR_FACE_PARAM_CTRL struFaceDelCond = new HCNetSDK.NET_DVR_FACE_PARAM_CTRL();
        struFaceDelCond.dwSize = struFaceDelCond.size();
        //删除方式：0- 按卡号方式删除，1- 按读卡器删除
        struFaceDelCond.byMode = 0;

        struFaceDelCond.struProcessMode.setType(HCNetSDK.NET_DVR_FACE_PARAM_BYCARD.class);

        //需要删除人脸关联的卡号
        for (int i = 0; i < HCNetSDK.ACS_CARD_NO_LEN; i++) {
            struFaceDelCond.struProcessMode.struByCard.byCardNo[i] = 0;
        }
        System.arraycopy(strCardNo.getBytes(), 0, struFaceDelCond.struProcessMode.struByCard.byCardNo, 0, strCardNo.length());

        //读卡器
        struFaceDelCond.struProcessMode.struByCard.byEnableCardReader[0] = 1;
        //人脸ID
        struFaceDelCond.struProcessMode.struByCard.byFaceID[0] = 1;
        struFaceDelCond.write();

        Pointer ptrFaceDelCond = struFaceDelCond.getPointer();

        boolean bRet = hcNetSDK.NET_DVR_RemoteControl(lUserID, HCNetSDK.NET_DVR_DEL_FACE_PARAM_CFG, ptrFaceDelCond, struFaceDelCond.size());
        if (!bRet) {
            System.out.println("删除人脸失败，错误码为" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("删除人脸成功！");
        }
    }

    /**
     * 删除卡
     * @param strCardNo 卡号
     */
    public static void deleteCard(String strCardNo) {
        HCNetSDK.NET_DVR_CARD_COND struCardCond = new HCNetSDK.NET_DVR_CARD_COND();
        struCardCond.read();
        struCardCond.dwSize = struCardCond.size();
        struCardCond.dwCardNum = 1;  //下发一张
        struCardCond.write();
        Pointer ptrStruCond = struCardCond.getPointer();

        m_lSetCardCfgHandle = hcNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_DEL_CARD, ptrStruCond, struCardCond.size(), null, null);
        if (m_lSetCardCfgHandle == -1) {
            System.out.println("建立删除卡长连接失败，错误码为" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("建立删除卡长连接成功！");
        }

        HCNetSDK.NET_DVR_CARD_SEND_DATA struCardData = new HCNetSDK.NET_DVR_CARD_SEND_DATA();
        struCardData.read();
        struCardData.dwSize = struCardData.size();

        for (int i = 0; i < HCNetSDK.ACS_CARD_NO_LEN; i++) {
            struCardData.byCardNo[i] = 0;
        }
        for (int i = 0; i < strCardNo.length(); i++) {
            struCardData.byCardNo[i] = strCardNo.getBytes()[i];
        }
        struCardData.write();

        HCNetSDK.NET_DVR_CARD_STATUS struCardStatus = new HCNetSDK.NET_DVR_CARD_STATUS();
        //读取卡
        struCardStatus.read();
        //设置卡的大小
        struCardStatus.dwSize = struCardStatus.size();
        //写入卡
        struCardStatus.write();

        IntByReference pInt = new IntByReference(0);

        while (true) {
            //发送和接收远程配置
            dwState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(m_lSetCardCfgHandle, struCardData.getPointer(), struCardData.size(), struCardStatus.getPointer(), struCardStatus.size(), pInt);

            struCardStatus.read();
            if (dwState == -1) {
                System.out.println("NET_DVR_SendWithRecvRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_NEEDWAIT) {
                System.out.println("配置等待");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FAILED) {
                System.out.println("删除卡失败, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 错误码：" + struCardStatus.dwErrorCode);
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_EXCEPTION) {
                System.out.println("删除卡异常, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 错误码：" + struCardStatus.dwErrorCode);
                break;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_SUCCESS) {
                if (struCardStatus.dwErrorCode != 0) {
                    System.out.println("删除卡成功,但是错误码" + struCardStatus.dwErrorCode + ", 卡号：" + new String(struCardStatus.byCardNo).trim());
                } else {
                    System.out.println("删除卡成功, 卡号: " + new String(struCardStatus.byCardNo).trim() + ", 状态：" + struCardStatus.byStatus);
                }
                continue;
            } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FINISH) {
                System.out.println("删除卡完成");
                break;
            }

        }

        //停止长连接
        if (!hcNetSDK.NET_DVR_StopRemoteConfig(m_lSetCardCfgHandle)) {
            System.out.println("NET_DVR_StopRemoteConfig接口调用失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_StopRemoteConfig接口成功");
        }
    }


    /**
     * 根据卡号获取人脸
     * @param cardNo 卡号
     */
    public static void getFace(String cardNo) {
        int iErr = 0;
        HCNetSDK.NET_DVR_FACE_PARAM_COND m_struFaceInputParam = new HCNetSDK.NET_DVR_FACE_PARAM_COND();
        m_struFaceInputParam.dwSize = m_struFaceInputParam.size();
        //人脸关联的卡号
        m_struFaceInputParam.byCardNo = cardNo.getBytes();
        m_struFaceInputParam.byEnableCardReader[0] = 1;
        m_struFaceInputParam.dwFaceNum = 1;
        m_struFaceInputParam.byFaceID = 1;
        m_struFaceInputParam.write();

        Pointer lpInBuffer = m_struFaceInputParam.getPointer();
        Pointer pUserData = null;
        fRemoteCfgCallBackFaceGet = new FRemoteCfgCallBackFaceGet();

        int lHandle = hcNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_FACE_PARAM_CFG, lpInBuffer, m_struFaceInputParam.size(), fRemoteCfgCallBackFaceGet, pUserData);
        if (lHandle < 0) {
            iErr = hcNetSDK.NET_DVR_GetLastError();
            JOptionPane.showMessageDialog(null, "建立长连接失败，错误号：" + iErr);
            return;
        }
        JOptionPane.showMessageDialog(null, "建立获取卡参数长连接成功!");

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


        if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hcNetSDK.NET_DVR_GetLastError();
            JOptionPane.showMessageDialog(null, "断开长连接失败，错误号：" + iErr);
            return;
        }
        JOptionPane.showMessageDialog(null, "断开长连接成功!");
    }

    /**
     * 设置布防
     */
    public static void setAlarm() {
        //设置报警回调函数
        FMSGCallBack_V31 fmsgCallBack_v31 = new FMSGCallBack_V31();
        //FRemoteCfgCallBackCardGet fRemoteCfgCallBackCardGet = new FRemoteCfgCallBackCardGet();
        boolean b = hcNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fmsgCallBack_v31, null);
        //如果设置报警回调失败，获取错误码
        if (!b) {
            System.out.println("SetDVRMessageCallBack failed, error code=" + hcNetSDK.NET_DVR_GetLastError());
        }
        //建立报警上传通道（布防）
        //布防参数
        HCNetSDK.NET_DVR_SETUPALARM_PARAM net_dvr_setupalarm_param = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
        int nativeLong = hcNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, net_dvr_setupalarm_param);
        //如果布防失败返回-1
        if (nativeLong < 0) {
            System.out.println("SetupAlarmChan failed, error code=" + hcNetSDK.NET_DVR_GetLastError());
            //注销
            hcNetSDK.NET_DVR_Logout(lUserID);
            //释放SDK资源
            hcNetSDK.NET_DVR_Cleanup();
        }
        try {
            //等待设备上传报警信息
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //撤销布防上传通道
        if (!hcNetSDK.NET_DVR_CloseAlarmChan_V30(nativeLong)) {
            //System.out.println("NET_DVR_CloseAlarmChan_V30 failed, error code="+hCNetSDK.NET_DVR_GetLastError());
            //注销
            hcNetSDK.NET_DVR_Logout(lUserID);
            //释放SDK资源
            hcNetSDK.NET_DVR_Cleanup();
        }
        //注销用户
        hcNetSDK.NET_DVR_Logout(lUserID);
        //释放SDK资源
        hcNetSDK.NET_DVR_Cleanup();
    }

    /**
     * 设置卡权限（卡计划模板）
     * @param templateNo 模板编号
     */
    public static void setCardPower(int templateNo) {

        //设置卡计划模板步骤
        /*
        步骤：
            NET_DVR_Init
            NET_DVR_Login_V40
            NET_DVR_SetDVRConfig
            NET_DVR_SET_CARD_RIGHT_PLAN_TEMPLATE
                NET_DVR_SET_CARD_RIGHT_WEEK_PLAN（周计划模板）
                NET_DVR_SET_CARD_RIGHT_HOLIDAY_GROUP（假日计划模板）设置假日计划模板
            NET_DVR_Logout
            NET_DVR_Cleanup

            NET_DVR_PLAN_TEMPLATE_COND
            卡权限计划模板配置条件结构体。

            NET_DVR_PLAN_TEMPLATE
            计划模板配置结构体。
         */
        //设置卡权限计划模板参数
        //卡权限计划模板配置条件结构体
        HCNetSDK.NET_DVR_PLAN_TEMPLATE_COND netDvrPlanTemplateCond = new HCNetSDK.NET_DVR_PLAN_TEMPLATE_COND();
        netDvrPlanTemplateCond.dwSize = netDvrPlanTemplateCond.size();
        //计划模板编号，从1开始，最大值从门禁能力集获取
        netDvrPlanTemplateCond.dwPlanTemplateNumber = templateNo;
        //就地控制器序号[1,64]，0表示门禁主机
        netDvrPlanTemplateCond.wLocalControllerID = 0;
        netDvrPlanTemplateCond.write();

        HCNetSDK.NET_DVR_PLAN_TEMPLATE struPlanTemCfg = new HCNetSDK.NET_DVR_PLAN_TEMPLATE();
        struPlanTemCfg.dwSize = struPlanTemCfg.size();
        //是否使能：0- 否，1- 是
        struPlanTemCfg.byEnable = 1;
        //周计划编号，0表示无效
        struPlanTemCfg.dwWeekPlanNo = 2;
        //假日组编号，按值表示，采用紧凑型排列，中间遇到0则后续无效
        struPlanTemCfg.dwHolidayGroupNo[0] = 0;

        //卡模板名称
        byte[] byTemplateName;
        try {
            byTemplateName = "计划模板名称测试".getBytes("GBK");
            //计划模板名称
            for (int i = 0; i < HCNetSDK.TEMPLATE_NAME_LEN; i++) {
                struPlanTemCfg.byTemplateName[i] = 0;
            }
            System.arraycopy(byTemplateName, 0, struPlanTemCfg.byTemplateName, 0, byTemplateName.length);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        struPlanTemCfg.write();

        IntByReference pInt = new IntByReference(0);
        Pointer lpStatusList = pInt.getPointer();

        if (false == hcNetSDK.NET_DVR_SetDeviceConfig(lUserID, HCNetSDK.NET_DVR_SET_CARD_RIGHT_WEEK_PLAN_V50, 1, netDvrPlanTemplateCond.getPointer(), netDvrPlanTemplateCond.size(), lpStatusList, struPlanTemCfg.getPointer(), struPlanTemCfg.size())) {
            System.out.println("NET_DVR_SET_CARD_RIGHT_PLAN_TEMPLATE_V50失败，错误号：" + hcNetSDK.NET_DVR_GetLastError());
            return;
        }
        System.out.println("NET_DVR_SET_CARD_RIGHT_PLAN_TEMPLATE_V50成功！");

        //卡权限周计划参数
        HCNetSDK.NET_DVR_WEEK_PLAN_COND struWeekPlanCond = new HCNetSDK.NET_DVR_WEEK_PLAN_COND();
        struWeekPlanCond.dwSize = struWeekPlanCond.size();
        struWeekPlanCond.dwWeekPlanNumber = 1;
        struWeekPlanCond.wLocalControllerID = 0;

        //周计划模板
        HCNetSDK.NET_DVR_WEEK_PLAN_CFG struWeekPlanCfg = new HCNetSDK.NET_DVR_WEEK_PLAN_CFG();

        struWeekPlanCond.write();
        struWeekPlanCfg.write();

        Pointer lpCond = struWeekPlanCond.getPointer();
        Pointer lpInbuferCfg = struWeekPlanCfg.getPointer();

        if (false == hcNetSDK.NET_DVR_GetDeviceConfig(lUserID, HCNetSDK.NET_DVR_GET_CARD_RIGHT_WEEK_PLAN_V50, 1, lpCond, struWeekPlanCond.size(), lpStatusList, lpInbuferCfg, struWeekPlanCfg.size())) {
            System.out.println("NET_DVR_GET_CARD_RIGHT_WEEK_PLAN_V50失败，错误号：" + hcNetSDK.NET_DVR_GetLastError());
            return;
        }
        struWeekPlanCfg.read();
        //模板是否使能：0- 否，1- 是
        struWeekPlanCfg.byEnable = 1;

        //避免时间段交叉，初始化时间段
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                //struPlanCfgDay = 那一天【周一到周五】
                //struBeginTime = 开始时间点（时分秒）
                //struEndTime = 结束时间点（时分秒）
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].byEnable = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struBeginTime.byHour = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struBeginTime.byMinute = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struBeginTime.bySecond = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struEndTime.byHour = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struEndTime.byMinute = 0;
                struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[j].struTimeSegment.struEndTime.bySecond = 0;
            }
        }

        //一周7天，全天24小时
        for (int i = 0; i < 7; i++) {
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].byEnable = 1;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.byHour = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.byMinute = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.bySecond = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.byHour = 24;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.byMinute = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.bySecond = 0;
        }

        //一周7天，每天设置2个时间段
        for (int i = 0; i < 7; i++) {
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].byEnable = 1;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.byHour = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.byMinute = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struBeginTime.bySecond = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.byHour = 11;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.byMinute = 59;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[0].struTimeSegment.struEndTime.bySecond = 59;

            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].byEnable = 1;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struBeginTime.byHour = 13;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struBeginTime.byMinute = 30;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struBeginTime.bySecond = 0;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struEndTime.byHour = 19;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struEndTime.byMinute = 59;
            struWeekPlanCfg.struPlanCfg[i].struPlanCfgDay[1].struTimeSegment.struEndTime.bySecond = 59;
        }
        struWeekPlanCfg.write();

        //设置卡权限周计划参数
        if (false == hcNetSDK.NET_DVR_SetDeviceConfig(lUserID, HCNetSDK.NET_DVR_SET_CARD_RIGHT_WEEK_PLAN_V50, 1, lpCond, struWeekPlanCond.size(), lpStatusList, lpInbuferCfg, struWeekPlanCfg.size())) {
            System.out.println("NET_DVR_SET_CARD_RIGHT_WEEK_PLAN_V50失败，错误号：" + hcNetSDK.NET_DVR_GetLastError());
            return;
        }
        System.out.println("NET_DVR_SET_CARD_RIGHT_WEEK_PLAN_V50成功！");
    }

    /**
     * 远程控门
     */
    public static void openDoor() {
        /**
         * lGatewayIndex：
         *  门禁序号（楼层编号、锁ID），从1开始，-1表示对所有门（或者梯控的所有楼层）进行操作
         * dwStaic：
         *  0- 关闭（对于梯控，表示受控）
         *  1- 打开（对于梯控，表示开门）
         *  2- 常开（对于梯控，表示自由、通道状态）
         *  3- 常关（对于梯控，表示禁用）
         *  4- 恢复（梯控，普通状态）
         *  5- 访客呼梯（梯控）
         *  6- 住户呼梯（梯控）
         * NET_DVR_ControlGateway(设备登录状态,设备对应的门(几楼，那个门),dwStaic);
         */
        boolean flag = hcNetSDK.NET_DVR_ControlGateway(lUserID,1,1);

        if (flag != true) {
            System.out.println("开门失败，错误码：" + hcNetSDK.NET_DVR_GetLastError());
        }else {
            System.out.println("开门成功。");
        }
    }
}
