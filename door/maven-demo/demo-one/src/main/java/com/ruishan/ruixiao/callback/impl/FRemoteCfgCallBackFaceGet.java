package com.ruishan.ruixiao.callback.impl;

import com.ruishan.ruixiao.hcnet.HCNetSDK;
import com.sun.jna.Pointer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FRemoteCfgCallBackFaceGet implements HCNetSDK.FRemoteConfigCallback {
    @Override
    public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
        switch (dwType){
            case 0:// NET_SDK_CALLBACK_TYPE_STATUS
                HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus  = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                struCfgStatus.write();
                Pointer pCfgStatus = struCfgStatus.getPointer();
                pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0,struCfgStatus.size());
                struCfgStatus.read();

                int iStatus = 0;
                for(int i=0;i<4;i++)
                {
                    int ioffset = i*8;
                    int iByte = struCfgStatus.byStatus[i]&0xff;
                    iStatus = iStatus + (iByte << ioffset);
                }

                switch (iStatus){
                    case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                        System.out.println("查询人脸参数成功,dwStatus:" + iStatus);
                        break;
                    case 1001:
                        System.out.println("正在查询人脸参数中,dwStatus:" + iStatus);
                        break;
                    case 1002:
                        int iErrorCode = 0;
                        for(int i=0;i<4;i++)
                        {
                            int ioffset = i*8;
                            int iByte = struCfgStatus.byErrorCode[i]&0xff;
                            iErrorCode = iErrorCode + (iByte << ioffset);
                        }
                        System.out.println("查询人脸参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                        break;
                }
                break;
            case 2: //NET_SDK_CALLBACK_TYPE_DATA
                HCNetSDK.NET_DVR_FACE_PARAM_CFG m_struFaceInfo = new HCNetSDK.NET_DVR_FACE_PARAM_CFG();
                m_struFaceInfo.write();
                Pointer pInfoV30 = m_struFaceInfo.getPointer();
                pInfoV30.write(0, lpBuffer.getByteArray(0, m_struFaceInfo.size()), 0,m_struFaceInfo.size());
                m_struFaceInfo.read();
                String str = new String(m_struFaceInfo.byCardNo).trim();
                System.out.println("查询到人脸数据关联的卡号,getCardNo:" + str + ",人脸数据类型:" + m_struFaceInfo.byFaceDataType);
                if(m_struFaceInfo.dwFaceLen >0)
                {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        fout = new FileOutputStream(newName +"_Card[" + str + "]_ACSFaceCfg.jpg");
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = m_struFaceInfo.pFaceBuffer.getByteBuffer(offset, m_struFaceInfo.dwFaceLen);
                        byte [] bytes = new byte[m_struFaceInfo.dwFaceLen];
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
                break;
        }
    }
}
