package org.fh.util.HKSDK;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fh.util.HKSDK.HCNetSDK.FColGlobalDataCallBack;
import org.fh.util.HKSDK.HCNetSDK.FMSGCallBack;

import com.sun.jna.Pointer;

public class AlarmJavaTest {

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
    HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息

    FMSGCallBack fMSFCallBack;//报警回调函数实现
    FMSGCallBack_V31 fMSFCallBack_V31;//报警回调函数实现

    FColGlobalDataCallBack fGpsCallBack;//GPS信息查询回调函数实现

    public static void main(String[] args) {
        if (!hCNetSDK.NET_DVR_Init()) {
            System.out.println("SDK初始化失败");
            return;
        }
        System.out.println("SDK初始化成功");
        //设置连接超时时间与重连功能
        hCNetSDK.NET_DVR_SetConnectTime(2000, 1);
        hCNetSDK.NET_DVR_SetReconnect(10000, true);
        int lUserID;//用户ID
        HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
        lUserID = -1;

        //读取配置文件里的IP、端口、用户名、密码
        String ip = "192.168.1.20";
        short port = 8000;
        String name = "admin";
        String password = "ab123456";

        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();

        //注册设备
        lUserID = hCNetSDK.NET_DVR_Login_V30(ip, (short) port, name, password, m_strDeviceInfo);
        //如果注册失败返回-1，获取错误码
        if (lUserID < 0) {
            System.out.println("Login failed, error code=" + hCNetSDK.NET_DVR_GetLastError());
        }
        System.out.println("注册成功");
        //设置报警回调函数
        FMSGCallBack_V31 fmsgCallBack_v31 = new FMSGCallBack_V31();
        FRemoteCfgCallBackCardGet fRemoteCfgCallBackCardGet = new FRemoteCfgCallBackCardGet();
        boolean b = hCNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fmsgCallBack_v31, null);
        //如果设置报警回调失败，获取错误码
        if (!b) {
            System.out.println("SetDVRMessageCallBack failed, error code=" + hCNetSDK.NET_DVR_GetLastError());
        }
        //建立报警上传通道（布防）
        //布防参数
        HCNetSDK.NET_DVR_SETUPALARM_PARAM net_dvr_setupalarm_param = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
        int nativeLong = hCNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, net_dvr_setupalarm_param);
        //如果布防失败返回-1
        if (nativeLong < 0) {
            System.out.println("SetupAlarmChan failed, error code=" + hCNetSDK.NET_DVR_GetLastError());
            //注销
            hCNetSDK.NET_DVR_Logout(lUserID);
            //释放SDK资源
            hCNetSDK.NET_DVR_Cleanup();
        }
        try {
            //等待设备上传报警信息
            Thread.sleep(600001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //撤销布防上传通道
        if (!hCNetSDK.NET_DVR_CloseAlarmChan_V30(nativeLong)) {
            //System.out.println("NET_DVR_CloseAlarmChan_V30 failed, error code="+hCNetSDK.NET_DVR_GetLastError());
            //注销
            hCNetSDK.NET_DVR_Logout(lUserID);
            //释放SDK资源
            hCNetSDK.NET_DVR_Cleanup();
        }
        //注销用户
        hCNetSDK.NET_DVR_Logout(lUserID);
        //释放SDK资源
        hCNetSDK.NET_DVR_Cleanup();
    }

    public static class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31 {

        //lCommand 上传消息类型  pAlarmer 报警设备信息  pAlarmInfo  报警信息   dwBufLen 报警信息缓存大小   pUser  用户数据
        public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
            try {
                //报警类型
                String sAlarmType = new String();
                //报警时间
                Date today = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String format = dateFormat.format(today);
                String[] sIP = new String[2];

                sAlarmType = new String("lCommand=") + lCommand;
                //lCommand是传的报警类型
                switch (lCommand) {
                    case HCNetSDK.COMM_ALARM_V40:
                        HCNetSDK.NET_DVR_ALARMINFO_V40 struAlarmInfoV40 = new HCNetSDK.NET_DVR_ALARMINFO_V40();
                        struAlarmInfoV40.write();
                        Pointer pInfoV40 = struAlarmInfoV40.getPointer();
                        pInfoV40.write(0, pAlarmInfo.getByteArray(0, struAlarmInfoV40.size()), 0, struAlarmInfoV40.size());
                        struAlarmInfoV40.read();

                        switch (struAlarmInfoV40.struAlarmFixedHeader.dwAlarmType) {
                            case 0:
                                struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struIOAlarm.class);
                                struAlarmInfoV40.read();
                                sAlarmType = sAlarmType + new String("：信号量报警") + "，" + "报警输入口：" + struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.struioAlarm.dwAlarmInputNo;
                                break;
                            case 1:
                                sAlarmType = sAlarmType + new String("：硬盘满");
                                break;
                            case 2:
                                sAlarmType = sAlarmType + new String("：信号丢失");
                                break;
                            case 3:
                                struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struAlarmChannel.class);
                                struAlarmInfoV40.read();
                                int iChanNum = struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.sstrualarmChannel.dwAlarmChanNum;
                                sAlarmType = sAlarmType + new String("：移动侦测") + "，" + "报警通道个数：" + iChanNum + "，" + "报警通道号：";

                                for (int i = 0; i < iChanNum; i++) {
                                    byte[] byChannel = struAlarmInfoV40.pAlarmData.getByteArray(i * 4, 4);

                                    int iChanneNo = 0;
                                    for (int j = 0; j < 4; j++) {
                                        int ioffset = j * 8;
                                        int iByte = byChannel[j] & 0xff;
                                        iChanneNo = iChanneNo + (iByte << ioffset);
                                    }

                                    sAlarmType = sAlarmType + "+ch[" + iChanneNo + "]";
                                }

                                break;
                            case 4:
                                sAlarmType = sAlarmType + new String("：硬盘未格式化");
                                break;
                            case 5:
                                sAlarmType = sAlarmType + new String("：读写硬盘出错");
                                break;
                            case 6:
                                sAlarmType = sAlarmType + new String("：遮挡报警");
                                break;
                            case 7:
                                sAlarmType = sAlarmType + new String("：制式不匹配");
                                break;
                            case 8:
                                sAlarmType = sAlarmType + new String("：非法访问");
                                break;
                        }

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                        break;
                    //9000报警信息主动上传
                    case HCNetSDK.COMM_ALARM_V30:
                        HCNetSDK.NET_DVR_ALARMINFO_V30 strAlarmInfoV30 = new HCNetSDK.NET_DVR_ALARMINFO_V30();
                        strAlarmInfoV30.write();
                        Pointer pInfoV30 = strAlarmInfoV30.getPointer();
                        pInfoV30.write(0, pAlarmInfo.getByteArray(0, strAlarmInfoV30.size()), 0, strAlarmInfoV30.size());
                        strAlarmInfoV30.read();
                        switch (strAlarmInfoV30.dwAlarmType) {
                            case 0:
                                sAlarmType = sAlarmType + new String("：信号量报警") + "，" + "报警输入口：" + (strAlarmInfoV30.dwAlarmInputNumber + 1);
                                break;
                            case 1:
                                sAlarmType = sAlarmType + new String("：硬盘满");
                                break;
                            case 2:
                                sAlarmType = sAlarmType + new String("：信号丢失");
                                break;
                            case 3:
                                sAlarmType = sAlarmType + new String("：移动侦测") + "，" + "报警通道：";
                                for (int i = 0; i < 64; i++) {
                                    if (strAlarmInfoV30.byChannel[i] == 1) {
                                        sAlarmType = sAlarmType + "ch" + (i + 1) + " ";
                                    }
                                }
                                break;
                            case 4:
                                sAlarmType = sAlarmType + new String("：硬盘未格式化");
                                break;
                            case 5:
                                sAlarmType = sAlarmType + new String("：读写硬盘出错");
                                break;
                            case 6:
                                sAlarmType = sAlarmType + new String("：遮挡报警");
                                break;
                            case 7:
                                sAlarmType = sAlarmType + new String("：制式不匹配");
                                break;
                            case 8:
                                sAlarmType = sAlarmType + new String("：非法访问");
                                break;
                        }
                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                        break;
                    //行为分析信息上传
                    case HCNetSDK.COMM_ALARM_RULE:
                        HCNetSDK.NET_VCA_RULE_ALARM strVcaAlarm = new HCNetSDK.NET_VCA_RULE_ALARM();
                        strVcaAlarm.write();
                        Pointer pVcaInfo = strVcaAlarm.getPointer();
                        pVcaInfo.write(0, pAlarmInfo.getByteArray(0, strVcaAlarm.size()), 0, strVcaAlarm.size());
                        strVcaAlarm.read();

                        switch (strVcaAlarm.struRuleInfo.wEventTypeEx) {
                            case 1:
                                sAlarmType = sAlarmType + new String("：穿越警戒面") + "，" +
                                        "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                        "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                        "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                        "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                                break;
                            case 2:
                                sAlarmType = sAlarmType + new String("：目标进入区域") + "，" +
                                        "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                        "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                        "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                        "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                                break;
                            case 3:
                                sAlarmType = sAlarmType + new String("：目标离开区域") + "，" +
                                        "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                        "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                        "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                        "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                                break;
                            default:
                                sAlarmType = sAlarmType + new String("：其他行为分析报警，事件类型：")
                                        + strVcaAlarm.struRuleInfo.wEventTypeEx +
                                        "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                        "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                        "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                        "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                                break;
                        }
                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        if (strVcaAlarm.dwPicDataLen > 0) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                fout = new FileOutputStream(".\\pic\\" + new String(pAlarmer.sDeviceIP).trim()
                                        + "wEventTypeEx[" + strVcaAlarm.struRuleInfo.wEventTypeEx + "]_" + newName + "_vca.jpg");
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strVcaAlarm.pImage.getByteBuffer(offset, strVcaAlarm.dwPicDataLen);
                                byte[] bytes = new byte[strVcaAlarm.dwPicDataLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    //交通抓拍结果上传
                    case HCNetSDK.COMM_UPLOAD_PLATE_RESULT:
                        HCNetSDK.NET_DVR_PLATE_RESULT strPlateResult = new HCNetSDK.NET_DVR_PLATE_RESULT();
                        strPlateResult.write();
                        Pointer pPlateInfo = strPlateResult.getPointer();
                        pPlateInfo.write(0, pAlarmInfo.getByteArray(0, strPlateResult.size()), 0, strPlateResult.size());
                        strPlateResult.read();
                        try {
                            String srt3 = new String(strPlateResult.struPlateInfo.sLicense, "GBK");
                            sAlarmType = sAlarmType + "：交通抓拍上传，车牌：" + srt3;
                        } catch (UnsupportedEncodingException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        if (strPlateResult.dwPicLen > 0) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                fout = new FileOutputStream(".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + "_"
                                        + newName + "_plateResult.jpg");
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strPlateResult.pBuffer1.getByteBuffer(offset, strPlateResult.dwPicLen);
                                byte[] bytes = new byte[strPlateResult.dwPicLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    case HCNetSDK.COMM_ITS_PLATE_RESULT://交通抓拍的终端图片上传
                        HCNetSDK.NET_ITS_PLATE_RESULT strItsPlateResult = new HCNetSDK.NET_ITS_PLATE_RESULT();
                        strItsPlateResult.write();
                        Pointer pItsPlateInfo = strItsPlateResult.getPointer();
                        pItsPlateInfo.write(0, pAlarmInfo.getByteArray(0, strItsPlateResult.size()), 0, strItsPlateResult.size());
                        strItsPlateResult.read();
                        try {
                            String srt3 = new String(strItsPlateResult.struPlateInfo.sLicense, "GBK");
                            sAlarmType = sAlarmType + ",车辆类型：" + strItsPlateResult.byVehicleType + ",交通抓拍上传，车牌：" + srt3;
                        } catch (UnsupportedEncodingException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        for (int i = 0; i < strItsPlateResult.dwPicNum; i++) {
                            if (strItsPlateResult.struPicInfo[i].dwDataLen > 0) {
                                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                                String newName = sf.format(new Date());
                                FileOutputStream fout;
                                try {
                                    String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + "_"
                                            + newName + "_type[" + strItsPlateResult.struPicInfo[i].byType + "]_ItsPlate.jpg";
                                    fout = new FileOutputStream(filename);
                                    //将字节写入文件
                                    long offset = 0;
                                    ByteBuffer buffers = strItsPlateResult.struPicInfo[i].pBuffer.getByteBuffer(offset, strItsPlateResult.struPicInfo[i].dwDataLen);
                                    byte[] bytes = new byte[strItsPlateResult.struPicInfo[i].dwDataLen];
                                    buffers.rewind();
                                    buffers.get(bytes);
                                    fout.write(bytes);
                                    fout.close();
                                } catch (FileNotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    case HCNetSDK.COMM_ALARM_PDC://客流量统计报警上传
                        HCNetSDK.NET_DVR_PDC_ALRAM_INFO strPDCResult = new HCNetSDK.NET_DVR_PDC_ALRAM_INFO();
                        strPDCResult.write();
                        Pointer pPDCInfo = strPDCResult.getPointer();
                        pPDCInfo.write(0, pAlarmInfo.getByteArray(0, strPDCResult.size()), 0, strPDCResult.size());
                        strPDCResult.read();

                        if (strPDCResult.byMode == 0) {
                            strPDCResult.uStatModeParam.setType(HCNetSDK.NET_DVR_STATFRAME.class);
                            sAlarmType = sAlarmType + "：客流量统计，进入人数：" + strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum +
                                    ", byMode:" + strPDCResult.byMode + ", dwRelativeTime:" + strPDCResult.uStatModeParam.struStatFrame.dwRelativeTime +
                                    ", dwAbsTime:" + strPDCResult.uStatModeParam.struStatFrame.dwAbsTime;
                        }
                        if (strPDCResult.byMode == 1) {
                            strPDCResult.uStatModeParam.setType(HCNetSDK.NET_DVR_STATTIME.class);
                            String strtmStart = "" + String.format("%04d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwYear) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwMonth) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwDay) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwHour) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwMinute) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwSecond);
                            String strtmEnd = "" + String.format("%04d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwYear) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwMonth) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwDay) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwHour) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwMinute) +
                                    String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwSecond);
                            sAlarmType = sAlarmType + "：客流量统计，进入人数：" + strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum +
                                    ", byMode:" + strPDCResult.byMode + ", tmStart:" + strtmStart + ",tmEnd :" + strtmEnd;
                        }

                        //报警设备IP地址
                        sIP = new String(strPDCResult.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                        break;

                    case HCNetSDK.COMM_ITS_PARK_VEHICLE://停车场数据上传
                        HCNetSDK.NET_ITS_PARK_VEHICLE strItsParkVehicle = new HCNetSDK.NET_ITS_PARK_VEHICLE();
                        strItsParkVehicle.write();
                        Pointer pItsParkVehicle = strItsParkVehicle.getPointer();
                        pItsParkVehicle.write(0, pAlarmInfo.getByteArray(0, strItsParkVehicle.size()), 0, strItsParkVehicle.size());
                        strItsParkVehicle.read();
                        try {
                            String srtParkingNo = new String(strItsParkVehicle.byParkingNo).trim(); //车位编号
                            String srtPlate = new String(strItsParkVehicle.struPlateInfo.sLicense, "GBK").trim(); //车牌号码
                            sAlarmType = sAlarmType + ",停产场数据,车位编号：" + srtParkingNo + ",车位状态："
                                    + strItsParkVehicle.byLocationStatus + ",车牌：" + srtPlate;
                        } catch (UnsupportedEncodingException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        for (int i = 0; i < strItsParkVehicle.dwPicNum; i++) {
                            if (strItsParkVehicle.struPicInfo[i].dwDataLen > 0) {
                                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                                String newName = sf.format(new Date());
                                FileOutputStream fout;
                                try {
                                    String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + "_"
                                            + newName + "_type[" + strItsParkVehicle.struPicInfo[i].byType + "]_ParkVehicle.jpg";
                                    fout = new FileOutputStream(filename);
                                    //将字节写入文件
                                    long offset = 0;
                                    ByteBuffer buffers = strItsParkVehicle.struPicInfo[i].pBuffer.getByteBuffer(offset, strItsParkVehicle.struPicInfo[i].dwDataLen);
                                    byte[] bytes = new byte[strItsParkVehicle.struPicInfo[i].dwDataLen];
                                    buffers.rewind();
                                    buffers.get(bytes);
                                    fout.write(bytes);
                                    fout.close();
                                } catch (FileNotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    case HCNetSDK.COMM_ALARM_TFS: //交通取证报警信息
                        HCNetSDK.NET_DVR_TFS_ALARM strTFSAlarmInfo = new HCNetSDK.NET_DVR_TFS_ALARM();
                        strTFSAlarmInfo.write();
                        Pointer pTFSInfo = strTFSAlarmInfo.getPointer();
                        pTFSInfo.write(0, pAlarmInfo.getByteArray(0, strTFSAlarmInfo.size()), 0, strTFSAlarmInfo.size());
                        strTFSAlarmInfo.read();

                        try {
                            String srtPlate = new String(strTFSAlarmInfo.struPlateInfo.sLicense, "GBK").trim(); //车牌号码
                            sAlarmType = sAlarmType + "：交通取证报警信息，违章类型：" + strTFSAlarmInfo.dwIllegalType + "，车牌号码：" + srtPlate
                                    + "，车辆出入状态：" + strTFSAlarmInfo.struAIDInfo.byVehicleEnterState;
                        } catch (UnsupportedEncodingException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        //报警设备IP地址
                        sIP = new String(strTFSAlarmInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                        break;
                    case HCNetSDK.COMM_ALARM_AID_V41://交通事件报警信息扩展
                        HCNetSDK.NET_DVR_AID_ALARM_V41 struAIDAlarmInfo = new HCNetSDK.NET_DVR_AID_ALARM_V41();
                        struAIDAlarmInfo.write();
                        Pointer pAIDInfo = struAIDAlarmInfo.getPointer();
                        pAIDInfo.write(0, pAlarmInfo.getByteArray(0, struAIDAlarmInfo.size()), 0, struAIDAlarmInfo.size());
                        struAIDAlarmInfo.read();
                        sAlarmType = sAlarmType + "：交通事件报警信息，交通事件类型：" + struAIDAlarmInfo.struAIDInfo.dwAIDType + "，规则ID："
                                + struAIDAlarmInfo.struAIDInfo.byRuleID + "，车辆出入状态：" + struAIDAlarmInfo.struAIDInfo.byVehicleEnterState;

                        //报警设备IP地址
                        sIP = new String(struAIDAlarmInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                        break;
                    case HCNetSDK.COMM_ALARM_TPS_V41://交通事件报警信息扩展
                        HCNetSDK.NET_DVR_TPS_ALARM_V41 struTPSAlarmInfo = new HCNetSDK.NET_DVR_TPS_ALARM_V41();
                        struTPSAlarmInfo.write();
                        Pointer pTPSInfo = struTPSAlarmInfo.getPointer();
                        pTPSInfo.write(0, pAlarmInfo.getByteArray(0, struTPSAlarmInfo.size()), 0, struTPSAlarmInfo.size());
                        struTPSAlarmInfo.read();

                        sAlarmType = sAlarmType + "：交通统计报警信息，绝对时标：" + struTPSAlarmInfo.dwAbsTime
                                + "，能见度:" + struTPSAlarmInfo.struDevInfo.byIvmsChannel
                                + "，车道1交通状态:" + struTPSAlarmInfo.struTPSInfo.struLaneParam[0].byTrafficState
                                + "，监测点编号：" + new String(struTPSAlarmInfo.byMonitoringSiteID).trim()
                                + "，设备编号：" + new String(struTPSAlarmInfo.byDeviceID).trim()
                                + "，开始统计时间：" + struTPSAlarmInfo.dwStartTime
                                + "，结束统计时间：" + struTPSAlarmInfo.dwStopTime;

                        //报警设备IP地址
                        sIP = new String(struTPSAlarmInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                        break;
                    case HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT: //人脸识别结果上传
                        System.out.println("开始人脸抓拍:" + HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT);
                        //实时人脸抓拍上传
                        HCNetSDK.NET_VCA_FACESNAP_RESULT strFaceSnapInfo = new HCNetSDK.NET_VCA_FACESNAP_RESULT();
                        strFaceSnapInfo.write();
                        Pointer pFaceSnapInfo = strFaceSnapInfo.getPointer();
                        System.out.println("pFaceSnapInfo:::" + pFaceSnapInfo + "\n");
                        System.out.println("strFaceSnapInfo" + strFaceSnapInfo + "\n");
                        pFaceSnapInfo.write(0, pAlarmInfo.getByteArray(0, strFaceSnapInfo.size()), 0, strFaceSnapInfo.size());
                        strFaceSnapInfo.read();
                        sAlarmType = sAlarmType + "：人脸抓拍上传，人脸评分：" + strFaceSnapInfo.dwFaceScore + "，年龄段：" + strFaceSnapInfo.struFeature.byAgeGroup + "，性别：" + strFaceSnapInfo.struFeature.bySex;

                        System.out.println("" + sAlarmType + "");
                        //报警设备IP地址
                        sIP = new String(strFaceSnapInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                        System.out.println(sIP);
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); //设置日期格式
                        String time = df.format(new Date()); // new Date()为获取当前系统时间
                        //人脸图片写文件
                        try {
                            FileOutputStream small = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "small.jpg");
                            FileOutputStream big = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "big.jpg");

                            if (strFaceSnapInfo.dwFacePicLen > 0) {
                                try {
                                    small.write(strFaceSnapInfo.pBuffer1.getByteArray(0, strFaceSnapInfo.dwFacePicLen), 0, strFaceSnapInfo.dwFacePicLen);
                                    small.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(AlarmJavaTest.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            if (strFaceSnapInfo.dwFacePicLen > 0) {
                                try {
                                    big.write(strFaceSnapInfo.pBuffer2.getByteArray(0, strFaceSnapInfo.dwBackgroundPicLen), 0, strFaceSnapInfo.dwBackgroundPicLen);
                                    big.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(AlarmJavaTest.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(AlarmJavaTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    //黑名单比对结果上传
                    case HCNetSDK.COMM_SNAP_MATCH_ALARM:
                        //人脸黑名单比对报警
                        HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM strFaceSnapMatch = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                        strFaceSnapMatch.write();
                        Pointer pFaceSnapMatch = strFaceSnapMatch.getPointer();
                        pFaceSnapMatch.write(0, pAlarmInfo.getByteArray(0, strFaceSnapMatch.size()), 0, strFaceSnapMatch.size());
                        strFaceSnapMatch.read();

                        if ((strFaceSnapMatch.dwSnapPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_pSnapPicBuffer" + ".jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strFaceSnapMatch.pSnapPicBuffer.getByteBuffer(offset, strFaceSnapMatch.dwSnapPicLen);
                                byte[] bytes = new byte[strFaceSnapMatch.dwSnapPicLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if ((strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_struSnapInfo_pBuffer1" + ".jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strFaceSnapMatch.struSnapInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen);
                                byte[] bytes = new byte[strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if ((strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_fSimilarity_" + strFaceSnapMatch.fSimilarity + "_struBlackListInfo_pBuffer1" + ".jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strFaceSnapMatch.struBlackListInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen);
                                byte[] bytes = new byte[strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        sAlarmType = sAlarmType + "：人脸黑名单比对报警，相识度：" + strFaceSnapMatch.fSimilarity + "，黑名单姓名：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byName, "GBK").trim() + "，\n黑名单证件信息：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byCertificateNumber).trim();

                        //获取人脸库ID
                        byte[] FDIDbytes;
                        if ((strFaceSnapMatch.struBlackListInfo.dwFDIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pFDID != null)) {
                            ByteBuffer FDIDbuffers = strFaceSnapMatch.struBlackListInfo.pFDID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwFDIDLen);
                            FDIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwFDIDLen];
                            FDIDbuffers.rewind();
                            FDIDbuffers.get(FDIDbytes);
                            sAlarmType = sAlarmType + "，人脸库ID:" + new String(FDIDbytes).trim();
                        }
                        //获取人脸图片ID
                        byte[] PIDbytes;
                        if ((strFaceSnapMatch.struBlackListInfo.dwPIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pPID != null)) {
                            ByteBuffer PIDbuffers = strFaceSnapMatch.struBlackListInfo.pPID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwPIDLen);
                            PIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwPIDLen];
                            PIDbuffers.rewind();
                            PIDbuffers.get(PIDbytes);
                            sAlarmType = sAlarmType + "，人脸图片ID:" + new String(PIDbytes).trim();
                        }
                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                        break;
                    case HCNetSDK.COMM_ALARM_ACS: //门禁主机报警信息
                        HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
                        strACSInfo.write();
                        Pointer pACSInfo = strACSInfo.getPointer();
                        pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
                        strACSInfo.read();

                        sAlarmType = sAlarmType + "：门禁主机报警信息，卡号：" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + "，卡类型：" +
                                strACSInfo.struAcsEventInfo.byCardType + "，报警主类型：" + strACSInfo.dwMajor + "，报警次类型：" + strACSInfo.dwMinor;

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        if (strACSInfo.dwPicDataLen > 0) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                        "_byCardNo[" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() +
                                        "_" + newName + "_Acs.jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
                                byte[] bytes = new byte[strACSInfo.dwPicDataLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    case HCNetSDK.COMM_ID_INFO_ALARM: //身份证信息
                        HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM();
                        strIDCardInfo.write();
                        Pointer pIDCardInfo = strIDCardInfo.getPointer();
                        pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                        strIDCardInfo.read();

                        sAlarmType = sAlarmType + "：门禁身份证刷卡信息，身份证号码：" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() + "，姓名：" +
                                new String(strIDCardInfo.struIDCardCfg.byName).trim() + "，报警主类型：" + strIDCardInfo.dwMajor + "，报警次类型：" + strIDCardInfo.dwMinor;

                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        //身份证图片
                        if (strIDCardInfo.dwPicDataLen > 0) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                        "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                        "_" + newName + "_IDInfoPic.jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strIDCardInfo.pPicData.getByteBuffer(offset, strIDCardInfo.dwPicDataLen);
                                byte[] bytes = new byte[strIDCardInfo.dwPicDataLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        //抓拍图片
                        if (strIDCardInfo.dwCapturePicDataLen > 0) {
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String newName = sf.format(new Date());
                            FileOutputStream fout;
                            try {
                                String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                        "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                        "_" + newName + "_IDInfoCapturePic.jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = strIDCardInfo.pCapturePicData.getByteBuffer(offset, strIDCardInfo.dwCapturePicDataLen);
                                byte[] bytes = new byte[strIDCardInfo.dwCapturePicDataLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    case HCNetSDK.COMM_ISAPI_ALARM: //ISAPI协议报警信息
                        HCNetSDK.NET_DVR_ALARM_ISAPI_INFO struEventISAPI = new HCNetSDK.NET_DVR_ALARM_ISAPI_INFO();
                        struEventISAPI.write();
                        Pointer pEventISAPI = struEventISAPI.getPointer();
                        pEventISAPI.write(0, pAlarmInfo.getByteArray(0, struEventISAPI.size()), 0, struEventISAPI.size());
                        struEventISAPI.read();
                        sAlarmType = sAlarmType + "：ISAPI协议报警信息, 数据格式:" + struEventISAPI.byDataType +
                                ", 图片个数:" + struEventISAPI.byPicturesNumber;

                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                        String curTime = sf1.format(new Date());
                        FileOutputStream foutdata;
                        try {
                            String jsonfilename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime + "_ISAPI_Alarm_" + ".json";
                            foutdata = new FileOutputStream(jsonfilename);
                            //将字节写入文件
                            ByteBuffer jsonbuffers = struEventISAPI.pAlarmData.getByteBuffer(0, struEventISAPI.dwAlarmDataLen);
                            byte[] jsonbytes = new byte[struEventISAPI.dwAlarmDataLen];
                            jsonbuffers.rewind();
                            jsonbuffers.get(jsonbytes);
                            foutdata.write(jsonbytes);
                            foutdata.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        for (int i = 0; i < struEventISAPI.byPicturesNumber; i++) {
                            HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA struPicData = new HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA();
                            struPicData.write();
                            Pointer pPicData = struPicData.getPointer();
                            pPicData.write(0, struEventISAPI.pPicPackData.getByteArray(i * struPicData.size(), struPicData.size()), 0, struPicData.size());
                            struPicData.read();

                            FileOutputStream fout;
                            try {
                                String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime +
                                        "_ISAPIPic_" + i + "_" + new String(struPicData.szFilename).trim() + ".jpg";
                                fout = new FileOutputStream(filename);
                                //将字节写入文件
                                long offset = 0;
                                ByteBuffer buffers = struPicData.pPicData.getByteBuffer(offset, struPicData.dwPicLen);
                                byte[] bytes = new byte[struPicData.dwPicLen];
                                buffers.rewind();
                                buffers.get(bytes);
                                fout.write(bytes);
                                fout.close();
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        //报警设备IP地址
                        sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                        break;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(AlarmJavaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public static class FRemoteCfgCallBackCardGet implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData){
            HCNetSDK.MY_USER_DATA m_userData = new HCNetSDK.MY_USER_DATA();
            m_userData.write();
            Pointer pUserVData = m_userData.getPointer();
            pUserVData.write(0, pUserData.getByteArray(0, m_userData.size()), 0, m_userData.size());
            m_userData.read();
            switch (dwType){
                case 0: //NET_SDK_CALLBACK_TYPE_STATUS
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus  = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCfgStatus.write();
                    Pointer pCfgStatus = struCfgStatus.getPointer();
                    pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0,struCfgStatus.size());
                    struCfgStatus.read();

                    int iStatus = 0;
                    for(int i=0;i<4;i++){
                        int ioffset = i*8;
                        int iByte = struCfgStatus.byStatus[i]&0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }
                    switch (iStatus){
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("查询卡参数成功,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            int iErrorCode = 0;
                            for(int i=0;i<4;i++){
                                int ioffset = i*8;
                                int iByte = struCfgStatus.byErrorCode[i]&0xff;
                                iErrorCode = iErrorCode + (iByte << ioffset);
                            }
                            System.out.println("查询卡参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                            break;
                    }
                    break;
                case 2: //NET_SDK_CALLBACK_TYPE_DATA
                    HCNetSDK.NET_DVR_CARD_CFG_V50 m_struCardInfo = new HCNetSDK.NET_DVR_CARD_CFG_V50();
                    m_struCardInfo.write();
                    Pointer pInfoV30 = m_struCardInfo.getPointer();
                    pInfoV30.write(0, lpBuffer.getByteArray(0, m_struCardInfo.size()), 0,m_struCardInfo.size());
                    m_struCardInfo.read();
                    String str = new String(m_struCardInfo.byCardNo).trim();
                    try {
                        String srtName=new String(m_struCardInfo.byName,"GBK").trim(); //姓名
                        System.out.println("查询到的卡号,getCardNo:" + str + "姓名:" + srtName);
                    }
                    catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

//    public static void AlarmDataHandle(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo,
//                                       int dwBufLen, Pointer pUser) {
//
//        String sAlarmType = new String();
//        // 报警时间
//        Date today = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        // lCommand是传的报警类型
//        // 报警设备IP地址
//        String[] sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
//        //logger.info("报警时间 alarmTime = ", dateFormat.format(today));
//        //logger.info("报警设备IP地址-----------------:ip=" + sIP[0]);
//        switch (lCommand) {
//            case HCNetSDK.COMM_ALARM_ACS: // 门禁主机报警信息
//                HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
//                strACSInfo.write();
//                Pointer pACSInfo = strACSInfo.getPointer();
//                pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
//                strACSInfo.read();
//                switch (strACSInfo.dwMajor) {
//                    case HKConstants.ALARM_TYPE_MAJOR.ALARM:
//                        //logger.info("报警主类型---【" + "报警" + "】");
//                        switch (strACSInfo.dwMinor) {
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_SHORT_CIRCUIT:
//                                //logger.info("----->防区短路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_BROKEN_CIRCUIT:
//                                //logger.info("----->防区断路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_EXCEPTION:
//                                //logger.info("----->防区异常报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_RESUME:
//                                //logger.info("----->防区报警恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_HOST_DESMANTLE_ALARM:
//                                //logger.info("----->设备防拆报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_HOST_DESMANTLE_RESUME:
//                                //logger.info("----->设备防拆恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_READER_DESMANTLE_ALARM:
//                                //logger.info("----->读卡器防拆报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_READER_DESMANTLE_RESUME:
//                                //logger.info("----->读卡器防拆恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CASE_SENSOR_ALARM:
//                                //logger.info("----->事件输入报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CASE_SENSOR_RESUME:
//                                //logger.info("----->事件输入恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_STRESS_ALARM:
//                                //logger.info("----->胁迫报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_OFFLINE_ECENT_NEARLY_FULL:
//                                logger.info("----->离线事件满90%报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_MAX_AUTHENTICATE_FAIL:
//                                //logger.info("----->卡号认证失败超次报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SD_CARD_FULL:
//                                logger.info("----->SD卡存储满报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LINKAGE_CAPTURE_PIC:
//                                //logger.info("----->联动抓拍事件报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SECURITY_MODULE_DESMANTLE_ALARM:
//                                //logger.info("----->门控安全模块防拆报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SECURITY_MODULE_DESMANTLE_RESUME:
//                                //logger.info("----->门控安全模块防拆恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_POS_START_ALARM:
//                                //logger.info("----->POS开启");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_POS_END_ALARM:
//                                //logger.info("----->POS结束");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_IMAGE_QUALITY_LOW:
//                                //logger.info("----->人脸图像画质低");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGE_RPRINT_QUALITY_LOW:
//                                //logger.info("----->指纹图像画质低");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_IMPORT_SHORT_CIRCUIT:
//                                //logger.info("----->消防输入短路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_IMPORT_BROKEN_CIRCUIT:
//                                //logger.info("----->消防输入断路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_IMPORT_RESUME:
//                                //logger.info("----->消防输入恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_BUTTON_TRIGGER:
//                                //	logger.info("----->消防按钮触发");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_BUTTON_RESUME:
//                                //	logger.info("----->消防按钮恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MAINTENANCE_BUTTON_TRIGGER:
//                                //	logger.info("----->维护按钮触发");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MAINTENANCE_BUTTON_RESUME:
//                                //	logger.info("----->维护按钮恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMERGENCY_BUTTON_TRIGGER:
//                                logger.info("----->紧急按钮触发");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMERGENCY_BUTTON_RESUME:
//                                //	logger.info("----->紧急按钮恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DISTRACT_CONTROLLER_ALARM:
//                                //	logger.info("----->分控器防拆报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DISTRACT_CONTROLLER_RESUME:
//                                //	logger.info("----->分控器防拆报警恢复");
//                                break;
//                            default:
//                                break;
//
//                        }
//                    case HKConstants.ALARM_TYPE_MAJOR.ABNORMAL:
//                        //	logger.info("报警主类型---【" + "异常" + "】");
//                        switch (strACSInfo.dwMinor) {
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NET_BROKEN:
//                                //	logger.info("----->网络断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_RS485_DEVICE_ABNORMAL:
//                                //	logger.info("----->RS485连接状态异常");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_RS485_DEVICE_REVERT:
//                                //	logger.info("----->RS485连接状态异常恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DEV_POWER_ON:
//                                //	logger.info("----->设备上电启动");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DEV_POWER_OFF:
//                                //	logger.info("----->设备掉电关闭");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_WATCH_DOG_RESET:
//                                //	logger.info("----->看门狗复位");
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOW_BATTERY:
//                                //	logger.info("----->蓄电池电压低");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_BATTERY_RESUME:
//                                //	logger.info("----->蓄电池电压恢复正常");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AC_OFF:
//                                //	logger.info("----->交流电断电");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AC_RESUME:
//                                //	logger.info("----->交流电恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NET_RESUME:
//                                //	logger.info("----->网络恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FLASH_ABNORMAL:
//                                //	logger.info("----->FLASH读写异常");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_READER_OFFLINE:
//                                //	logger.info("----->读卡器掉线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_READER_RESUME:
//                                //	logger.info("----->读卡器掉线恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_INDICATOR_LIGHT_OFF:
//                                //	logger.info("----->指示灯关闭");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_INDICATOR_LIGHT_RESUME:
//                                //	logger.info("----->指示灯恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CHANNEL_CONTROLLER_OFF:
//                                //	logger.info("----->通道控制器掉线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CHANNEL_CONTROLLER_RESUME:
//                                //logger.info("----->通道控制器恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SECURITY_MODULE_OFF:
//                                //logger.info("----->门控安全模块掉线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SECURITY_MODULE_RESUME:
//                                //	logger.info("----->门控安全模块掉线恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_CONTROL_NET_BROKEN:
//                                //	logger.info("----->就地控制器网络断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_CONTROL_NET_RSUME:
//                                //	logger.info("----->就地控制器网络恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MASTER_RS485_LOOPNODE_BROKEN:
//                                //logger.info("----->主控RS485环路节点断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MASTER_RS485_LOOPNODE_RESUME:
//                                //	logger.info("----->主控RS485环路节点恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_CONTROL_OFFLINE:
//                                //	logger.info("----->就地控制器掉线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_CONTROL_RESUME:
//                                //	logger.info("----->就地控制器掉线恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_DOWNSIDE_RS485_LOOPNODE_BROKEN:
//                                //logger.info("----->就地下行RS485环路断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_DOWNSIDE_RS485_LOOPNODE_RESUME:
//                                //logger.info("----->就地下行RS485环路恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DISTRACT_CONTROLLER_ONLINE:
//                                //	logger.info("----->分控器在线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DISTRACT_CONTROLLER_OFFLINE:
//                                //	logger.info("----->分控器离线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ID_CARD_READER_NOT_CONNECT:
//                                //logger.info("----->身份证阅读器未连接（智能专用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ID_CARD_READER_RESUME:
//                                //	logger.info("----->身份证阅读器连接恢复（智能专用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGER_PRINT_MODULE_NOT_CONNECT:
//                                //	logger.info("----->分控器防拆报警恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGER_PRINT_MODULE_RESUME:
//                                //logger.info("----->指纹模组连接恢复（智能专用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CAMERA_NOT_CONNECT:
//                                //	logger.info("----->摄像头未连接");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CAMERA_RESUME:
//                                //	logger.info("----->摄像头连接恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_COM_NOT_CONNECT:
//                                //	logger.info("----->COM口未连接");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_COM_RESUME:
//                                //	logger.info("----->COM口连接恢复");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DEVICE_NOT_AUTHORIZE:
//                                //	logger.info("----->设备未授权");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_PEOPLE_AND_ID_CARD_DEVICE_ONLINE:
//                                //	logger.info("----->人证设备在线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_PEOPLE_AND_ID_CARD_DEVICE_OFFLINE:
//                                //	logger.info("----->人证设备离线");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_BATTERY_ELECTRIC_LOW:
//                                //	logger.info("----->电池电压低（仅人脸设备使用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_BATTERY_ELECTRIC_RESUME:
//                                //	logger.info("----->电池电压恢复正常（仅人脸设备使用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SUBMARINEBACK_COMM_BREAK:
//                                //	logger.info("----->与反潜回服务器通信断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SUBMARINEBACK_COMM_RESUME:
//                                //	logger.info("----->与反潜回服务器通信恢复");
//                                break;
//                            default:
//                                break;
//
//                        }
//                    case HKConstants.ALARM_TYPE_MAJOR.OPERATION:
//                        //	logger.info("报警主类型---【" + "操作" + "】");
//                        switch (strACSInfo.dwMinor) {
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_LOGIN:
//                                //	logger.info("----->本地登陆");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_UPGRADE:
//                                //	logger.info("----->本地升级");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_LOGIN:
//                                //	logger.info("----->远程登录");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_LOGOUT:
//                                //	logger.info("----->远程注销登陆");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ARM:
//                                //	logger.info("----->远程布防");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_DISARM:
//                                //	logger.info("----->远程撤防");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_REBOOT:
//                                //	logger.info("----->远程重启");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_UPGRADE:
//                                //	logger.info("----->远程升级");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CFGFILE_OUTPUT:
//                                //	logger.info("----->远程导出配置文件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CFGFILE_INTPUT:
//                                //	logger.info("----->远程导入配置文件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ALARMOUT_OPEN_MAN:
//                                //	logger.info("----->远程手动开启报警输出");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ALARMOUT_CLOSE_MAN:
//                                //	logger.info("----->远程手动关闭报警输出");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_OPEN_DOOR:
//                                //	logger.info("----->远程开门");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CLOSE_DOOR:
//                                //	logger.info("----->远程关门（对于梯控，表示受控）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ALWAYS_OPEN:
//                                //	logger.info("----->远程常开（对于梯控，表示自由）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ALWAYS_CLOSE:
//                                //	logger.info("----->远程常关（对于梯控，表示禁用）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CHECK_TIME:
//                                //	logger.info("----->远程手动校时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NTP_CHECK_TIME:
//                                //	logger.info("----->NTP自动校时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CLEAR_CARD:
//                                //	logger.info("----->远程清空卡号");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_RESTORE_CFG:
//                                //	logger.info("----->远程恢复默认参数");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_ARM:
//                                //	logger.info("----->防区布防");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMIN_DISARM:
//                                //	logger.info("----->防区撤防");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_RESTORE_CFG:
//                                //	logger.info("----->本地恢复默认参数");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_CAPTURE_PIC:
//                                //	logger.info("----->远程抓拍");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MOD_NET_REPORT_CFG:
//                                //	logger.info("----->修改网络中心参数配置");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MOD_GPRS_REPORT_PARAM:
//                                //logger.info("----->修改GPRS中心参数配置");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MOD_REPORT_GROUP_PARAM:
//                                //	logger.info("----->修改中心组参数配置");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_UNLOCK_PASSWORD_OPEN_DOOR:
//                                //logger.info("----->解除码输入");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AUTO_RENUMBER:
//                                //logger.info("----->自动重新编号");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AUTO_COMPLEMENT_NUMBER:
//                                //logger.info("----->自动补充编号");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NORMAL_CFGFILE_INPUT:
//                                //	logger.info("----->导入普通配置文件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NORMAL_CFGFILE_OUTTPUT:
//                                //	logger.info("----->导出普通配置文件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_RIGHT_INPUT:
//                                //	logger.info("----->导入卡权限参数");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_RIGHT_OUTTPUT:
//                                //	logger.info("----->导出卡权限参数");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCAL_USB_UPGRADE:
//                                //	logger.info("----->本地U盘升级b");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_VISITOR_CALL_LADDER:
//                                //	logger.info("----->访客呼梯");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_HOUSEHOLD_CALL_LADDER:
//                                //	logger.info("----->住户呼梯");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ACTUAL_GUARD:
//                                //	logger.info("----->远程实时布防");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_REMOTE_ACTUAL_UNGUARD:
//                                //logger.info("----->远程实时撤防");
//                                break;
//                            default:
//                                break;
//                        }
//                    case HKConstants.ALARM_TYPE_MAJOR.EVENT:
//                        //logger.info("报警主类型---【" + "事件" + "】");
//                        switch (strACSInfo.dwMinor) {
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LEGAL_CARD_PASS:// 01
//                                // ;//
//                                //logger.info("----->合法卡认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_AND_PSW_PASS:// 02
//                                // ;//
//                                //logger.info("----->刷卡加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_AND_PSW_FAIL:// 03
//                                // ;//
//                                //logger.info("----->刷卡加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_AND_PSW_TIMEOUT:// 04
//                                // ;//
//                                //logger.info("----->数卡加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_AND_PSW_OVER_TIME:// 05
//                                // ;//
//                                //logger.info("----->刷卡加密码超次");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_NO_RIGHT:// 06 ;//
//                                //logger.info("----->未分配权限");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_INVALID_PERIOD:// 07
//                                // ;//
//                                //logger.info("----->无效时段");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_OUT_OF_DATE:// 08
//                                // ;//
//                                //logger.info("----->卡号过期");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_INVALID_CARD:// 09 ;//
//                                //logger.info("----->无此卡号");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ANTI_SNEAK_FAIL:// 0a
//                                // ;//
//                                //logger.info("----->反潜回认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_INTERLOCK_DOOR_NOT_CLOSE:// 0b
//                                // ;//
//                                //logger.info("----->互锁门未关闭");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_NOT_BELONG_MULTI_GROUP:// 0c
//                                // ;//
//                                //logger.info("----->卡不属于多重认证群组");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_INVALID_MULTI_VERIFY_PERIOD:// 0d
//                                // ;//
//                                //logger.info("----->卡不在多重认证时间段内");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_SUPER_RIGHT_FAIL:// 0e
//                                // ;//
//                                //logger.info("----->多重认证模式超级权限认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_REMOTE_RIGHT_FAIL:// 0f
//                                // ;//
//                                //logger.info("----->多重认证模式远程认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_SUCCESS:// 10
//                                // ;//
//                                //logger.info("----->多重认证成功");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LEADER_CARD_OPEN_BEGIN:// 11
//                                // ;//
//                                //logger.info("----->首卡开门开始");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LEADER_CARD_OPEN_END:// 12
//                                // ;//
//                                //logger.info("----->首卡开门结束");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALWAYS_OPEN_BEGIN:// 13
//                                // ;//
//                                //logger.info("----->常开状态开始");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALWAYS_OPEN_END:// 14
//                                // ;//
//                                //logger.info("----->常开状态结束");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCK_OPEN:// 15 ;//
//                                //logger.info("----->门锁打开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_LOCK_CLOSE:// 16 ;//
//                                //logger.info("----->门锁关闭");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_BUTTON_PRESS:// 17
//                                // ;//
//                                //logger.info("----->开门按钮打开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_BUTTON_RELEASE:// 18
//                                // ;//
//                                //logger.info("----->开门按钮放开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_OPEN_NORMAL:// 19
//                                // ;//
//                                //logger.info("----->正常开门（门磁）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_CLOSE_NORMAL:// 1a
//                                // ;//
//                                //logger.info("----->正常关门（门磁）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_OPEN_ABNORMAL:// 1b
//                                // ;//
//                                //logger.info("----->门异常打开（门磁）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_OPEN_TIMEOUT:// 1c
//                                // ;//
//                                //logger.info("----->门打开超时（门磁）");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMOUT_ON:// 1d ;//
//                                //logger.info("----->报警输出打开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALARMOUT_OFF:// 1e ;//
//                                //logger.info("----->报警输出关闭");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALWAYS_CLOSE_BEGIN:// 1f
//                                // ;//
//                                //logger.info("----->常关状态开始");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_ALWAYS_CLOSE_END:// 20
//                                // ;//
//                                //logger.info("----->常关状态结束");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_NEED_REMOTE_OPEN:// 21
//                                // ;//
//                                //logger.info("----->多重多重认证需要远程开门");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_SUPERPASSWD_VERIFY_SUCCESS:// 22
//                                // ;//
//                                //logger.info("----->多重认证超级密码认证成功事件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_REPEAT_VERIFY:// 23
//                                // ;//
//                                //logger.info("----->多重认证重复认证事件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_MULTI_VERIFY_TIMEOUT:// 24
//                                // ;//
//                                //logger.info("----->多重认证重复认证事件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORBELL_RINGING:// 25
//                                // ;//
//                                //logger.info("----->门铃响");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_COMPARE_PASS:// 26
//                                // ;//
//                                //logger.info("----->指纹比对通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_COMPARE_FAIL:// 27
//                                // ;//
//                                //logger.info("----->指纹比对失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_VERIFY_PASS:// 28
//                                // ;//
//                                //logger.info("----->刷卡加指纹认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_VERIFY_FAIL:// 29
//                                // ;//
//                                //logger.info("----->刷卡加指纹认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_VERIFY_TIMEOUT:// 2a
//                                // ;//
//                                //logger.info("----->刷卡加指纹认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_PASS:// 2b
//                                // ;//
//                                //logger.info("----->刷卡加指纹加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_FAIL:// 2c
//                                // ;//
//                                //logger.info("----->刷卡加指纹加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_TIMEOUT:// 2d
//                                // ;//
//                                //logger.info("----->刷卡加指纹加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_PASSWD_VERIFY_PASS:// 2e
//                                // ;//
//                                //logger.info("----->指纹加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_PASSWD_VERIFY_FAIL:// 2f
//                                // ;//
//                                //logger.info("----->指纹加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_PASSWD_VERIFY_TIMEOUT:// 30
//                                // ;//
//                                //logger.info("----->指纹加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FINGERPRINT_INEXISTENCE:// 31
//                                // ;//
//                                //logger.info("----->指纹不存在");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_PLATFORM_VERIFY:// 32
//                                // ;//
//                                //logger.info("----->刷卡平台认证");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CALL_CENTER:// 33 ;//
//                                //logger.info("----->呼叫中心事件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_RELAY_TURN_ON_DOOR_ALWAYS_OPEN:// 34
//                                // ;//
//                                //	logger.info("----->消防继电器导通触发门常开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRE_RELAY_RECOVER_DOOR_RECOVER_NORMAL:// 35
//                                // ;//
//                                //	logger.info("----->消防继电器恢复门恢复正常");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_FP_VERIFY_PASS:// 36
//                                // ;//
//                                //	logger.info("----->人脸加指纹认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_FP_VERIFY_FAIL:// 37
//                                // ;//
//                                //	logger.info("----->人脸加指纹认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_FP_VERIFY_TIMEOUT:// 38
//                                // ;//
//                                //	logger.info("----->人脸加指纹认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_VERIFY_PASS:// 39
//                                // ;//
//                                //	logger.info("----->人脸加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_VERIFY_FAIL:// 3a
//                                // ;//
//                                //	logger.info("----->人脸加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_VERIFY_TIMEOUT:// 3b;//
//                                //	logger.info("----->人脸加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_CARD_VERIFY_PASS:// 3c
//                                // ;//
//                                //	logger.info("----->人脸加刷卡认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_CARD_VERIFY_FAIL:// 3d
//                                // ;//
//                                //	logger.info("----->人脸加刷卡认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_CARD_VERIFY_TIMEOUT:// 3e
//                                // ;//
//                                //	logger.info("----->人脸加刷卡认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_AND_FP_VERIFY_PASS:// 3f
//                                // ;//
//                                //	logger.info("----->人脸加密码加指纹认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_AND_FP_VERIFY_FAIL:// 40
//                                // ;//
//                                //	logger.info("----->人脸加密码加指纹认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_AND_PW_AND_FP_VERIFY_TIMEOUT:// 41
//                                // ;//
//                                //	logger.info("----->人脸加密码加指纹认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_CARD_AND_FP_VERIFY_PASS:// 42
//                                // ;//
//                                //	logger.info("----->人脸加刷卡加指纹认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_CARD_AND_FP_VERIFY_FAIL:// 43
//                                // ;//
//                                //	logger.info("----->人脸加刷卡加指纹认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_CARD_AND_FP_VERIFY_TIMEOUT:// 44
//                                // ;//
//                                //logger.info("----->人脸加刷卡加指纹认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_VERIFY_PASS:// 45
//                                // ;//
//                                //	logger.info("----->工号加指纹认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_VERIFY_FAIL:// 46
//                                // ;//
//                                //logger.info("----->工号加指纹认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_VERIFY_TIMEOUT:// 47
//                                // ;//
//                                //	logger.info("----->工号加指纹认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_PASS:// 48
//                                // ;//
//                                //	logger.info("----->工号加指纹加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_FAIL:// 49
//                                // ;//
//                                //	logger.info("----->工号加指纹加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_TIMEOUT:// 4a
//                                // ;//
//                                //	logger.info("----->工号加指纹加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_VERIFY_PASS:// 4b
//                                // ;//
//                                logger.info("----->人脸认证通过");
//                                // 记录人脸通过日志
//                                String cardNO = new String(strACSInfo.struAcsEventInfo.byCardNo).trim();
//                                if(StringUtils.isNoneBlank(cardNO)&&StringUtils.isNoneBlank(sIP[0])){
//                                    HKwsStatusAndLogUtil.setRecord2Redis(new HKDeviceLogVo(cardNO,sIP[0],today.getTime()));
//                                }
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_VERIFY_FAIL:// 4c
//                                // ;//
//                                //	logger.info("----->人脸认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FACE_VERIFY_PASS:// 4d
//                                // ;//
//                                //	logger.info("----->工号加人脸认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FACE_VERIFY_FAIL:// 4e
//                                // ;//
//                                //	logger.info("----->工号加人脸认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_FACE_VERIFY_TIMEOUT:// 4f
//                                // ;//
//                                //	logger.info("----->工号加人脸认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FACE_RECOGNIZE_FAIL:// 50
//                                // ;//
//                                //	logger.info("----->人脸识别失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRSTCARD_AUTHORIZE_BEGIN:// 51
//                                // ;//
//                                //	logger.info("----->首卡授权开始");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRSTCARD_AUTHORIZE_END:// 52
//                                // ;//
//                                //	logger.info("----->首卡授权结束");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORLOCK_INPUT_SHORT_CIRCUIT:// 53
//                                // ;//
//                                //	logger.info("----->门锁输入短路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORLOCK_INPUT_BROKEN_CIRCUIT:// 54
//                                // ;//
//                                //	logger.info("----->门锁输入断路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORLOCK_INPUT_EXCEPTION:// 55
//                                // ;//
//                                //logger.info("----->门锁输入异常报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORCONTACT_INPUT_SHORT_CIRCUIT:// 56
//                                // ;//
//                                //	logger.info("----->门磁输入短路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORCONTACT_INPUT_BROKEN_CIRCUIT:// 57
//                                // ;//
//                                //logger.info("----->门磁输入断路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORCONTACT_INPUT_EXCEPTION:// 58
//                                // ;//
//                                //logger.info("----->门磁输入异常报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_OPENBUTTON_INPUT_SHORT_CIRCUIT:// 59
//                                // ;//
//                                //logger.info("----->开门按钮输入短路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_OPENBUTTON_INPUT_BROKEN_CIRCUIT:// 5a
//                                // ;//
//                                //logger.info("----->开门按钮输入断路报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_OPENBUTTON_INPUT_EXCEPTION:// 5b
//                                // ;//
//                                //logger.info("----->开门按钮输入异常报警");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORLOCK_OPEN_EXCEPTION:// 5c
//                                // ;//
//                                //logger.info("----->门锁异常打开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOORLOCK_OPEN_TIMEOUT:// 5d
//                                // ;//
//                                //logger.info("----->门锁打开超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_FIRSTCARD_OPEN_WITHOUT_AUTHORIZE:// 5e
//                                // ;//
//                                //logger.info("----->首卡未授权开门失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CALL_LADDER_RELAY_BREAK:// 5f
//                                // ;//
//                                //logger.info("----->呼梯继电器断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CALL_LADDER_RELAY_CLOSE:// 60
//                                // ;//
//                                //logger.info("----->呼梯继电器闭合");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AUTO_KEY_RELAY_BREAK:// 61
//                                // ;//
//                                //logger.info("----->自动按键继电器断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AUTO_KEY_RELAY_CLOSE:// 62
//                                // ;//
//                                //logger.info("----->自动按键继电器闭合");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_KEY_CONTROL_RELAY_BREAK:// 63
//                                // ;//
//                                //logger.info("----->按键梯控继电器断开");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_KEY_CONTROL_RELAY_CLOSE:// 64
//                                // ;//
//                                //logger.info("----->按键梯控继电器闭合");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_PW_PASS:// 65
//                                // ;//
//                                //logger.info("----->工号加密码认证通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_PW_FAIL:// 66
//                                // ;//
//                                //logger.info("----->工号加密码认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_EMPLOYEENO_AND_PW_TIMEOUT:// 67
//                                // ;//
//                                //logger.info("----->工号加密码认证超时");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_HUMAN_DETECT_FAIL:// 68
//                                // ;//
//                                //logger.info("----->真人检测失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_PEOPLE_AND_ID_CARD_COMPARE_PASS:// 69
//                                // ;//
//                                //logger.info("----->人证比对通过");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_PEOPLE_AND_ID_CARD_COMPARE_FAIL:// 70
//                                // ;//
//                                //logger.info("----->人证比对失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CERTIFICATE_BLACK_LIST:// 71
//                                // ;//
//                                //logger.info("----->黑名单事件");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_DOOR_OPEN_OR_DORMANT_FAIL:// 75
//                                // ;//
//                                //logger.info("----->门状态常闭或休眠状态认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_AUTH_PLAN_DORMANT_FAIL:// 76
//                                // ;//
//                                //logger.info("----->认证计划休眠模式认证失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_CARD_ENCRYPT_VERIFY_FAIL:// 77
//                                // ;//
//                                //logger.info("----->卡加密校验失败");
//                                break;
//                            case HKConstants.ALARM_TYPE_MINOR.MINOR_SUBMARINEBACK_REPLY_FAIL:// 78
//                                // ;//
//                                //logger.info("----->反潜回服务器应答失败");
//                                break;
//                            default:
//                                break;
//                        }
//                }
//                String cardNo = new String(strACSInfo.struAcsEventInfo.byCardNo).trim();
//                if (ValidateHelper.isNotEmptyString(cardNo)) {
//                    //logger.info("报警卡号cardNo = {}", cardNo);
//                }
//
//                Byte cardType = strACSInfo.struAcsEventInfo.byCardType;
//                if (cardType != null) {
//                    //logger.info("报警卡号类型cardType = {}", cardType);
//                }
//                logger.info(sAlarmType);
//                //logger.info("抓拍数据指针---->strACSInfo.dwPicDataLen=" + strACSInfo.dwPicDataLen);
//                if (strACSInfo.dwPicDataLen != 0) {
//                    //logger.info("报警抓拍-------------------------->开始");
//                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//                    String newName = sf.format(new Date());
//                    FileOutputStream fout;
//                    try {
//                        String filename = "E:/" + newName + "_ACS_card_"
//                                + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + ".jpg";
//                        fout = new FileOutputStream(filename);
//                        // 将字节写入文件
//                        long offset = 0;
//                        ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
//                        byte[] bytes = new byte[strACSInfo.dwPicDataLen];
//                        buffers.rewind();
//                        buffers.get(bytes);
//                        fout.write(bytes);
//                        fout.close();
//                    } catch (FileNotFoundException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            case HCNetSDK.COMM_ALARM_PDC:
//                HCNetSDK.NET_DVR_PDC_ALRAM_INFO strPDCResult = new HCNetSDK.NET_DVR_PDC_ALRAM_INFO();
//                strPDCResult.write();
//                Pointer pPDCInfo = strPDCResult.getPointer();
//                pPDCInfo.write(0, pAlarmInfo.getByteArray(0, strPDCResult.size()), 0, strPDCResult.size());
//                strPDCResult.read();
//                //sAlarmType = sAlarmType + "：客流量统计，进入人数：" + strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum;
//                //logger.info(sAlarmType);
//            default:
//                break;
//        }
//
//    }
}

