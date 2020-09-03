/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HCNetSDK.java
 *
 * Created on 2009-9-14, 19:31:34
 */



package Test1;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.examples.win32.GDI32.RECT;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.ptr.ShortByReference;

//SDK鎺ュ彛璇存槑,HCNetSDK.dll
public interface HCNetSDK extends StdCallLibrary {

    HCNetSDK INSTANCE = (HCNetSDK) Native.loadLibrary("E:\\win64 V5.3.6.30eclipse实现人脸抓拍获取人脸评分\\Test1\\lib\\HCNetSDK",
            HCNetSDK.class);
    /***瀹忓畾涔�***/
    //甯搁噺

    public static final int MAX_NAMELEN = 16;	//DVR鏈湴鐧婚檰鍚�
    public static final int MAX_RIGHT = 32;	//璁惧鏀寔鐨勬潈闄愶紙1-12琛ㄧず鏈湴鏉冮檺锛�13-32琛ㄧず杩滅▼鏉冮檺锛�
    public static final int NAME_LEN = 32;    //鐢ㄦ埛鍚嶉暱搴�
    public static final int PASSWD_LEN = 16;    //瀵嗙爜闀垮害
    public static final int SERIALNO_LEN = 48;   //搴忓垪鍙烽暱搴�
    public static final int MACADDR_LEN = 6;      //mac鍦板潃闀垮害
    public static final int MAX_ETHERNET = 2;   //璁惧鍙厤浠ュお缃戠粶
    public static final int PATHNAME_LEN = 128;   //璺緞闀垮害
    public static final int MAX_TIMESEGMENT_V30 = 8;    //9000璁惧鏈�澶ф椂闂存鏁�
    public static final int MAX_TIMESEGMENT = 4;   //8000璁惧鏈�澶ф椂闂存鏁�
    public static final int MAX_SHELTERNUM = 4;   //8000璁惧鏈�澶ч伄鎸″尯鍩熸暟
    public static final int MAX_DAYS = 7;      //姣忓懆澶╂暟
    public static final int PHONENUMBER_LEN = 32;   //pppoe鎷ㄥ彿鍙风爜鏈�澶ч暱搴�
    public static final int MAX_DISKNUM_V30 = 33;		//9000璁惧鏈�澶х‖鐩樻暟/* 鏈�澶�33涓‖鐩�(鍖呮嫭16涓唴缃甋ATA纭洏銆�1涓猠SATA纭洏鍜�16涓狽FS鐩�) */
    public static final int MAX_DISKNUM = 16;     //8000璁惧鏈�澶х‖鐩樻暟
    public static final int MAX_DISKNUM_V10 = 8;   //1.2鐗堟湰涔嬪墠鐗堟湰
    public static final int MAX_WINDOW_V30 = 32; //9000璁惧鏈湴鏄剧ず鏈�澶ф挱鏀剧獥鍙ｆ暟
    public static final int MAX_WINDOW = 16;    //8000璁惧鏈�澶х‖鐩樻暟
    public static final int MAX_VGA_V30 = 4;     //9000璁惧鏈�澶у彲鎺GA鏁�
    public static final int MAX_VGA = 1;    //8000璁惧鏈�澶у彲鎺GA鏁�
    public static final int MAX_USERNUM_V30 = 32;  //9000璁惧鏈�澶х敤鎴锋暟
    public static final int MAX_USERNUM = 16;  //8000璁惧鏈�澶х敤鎴锋暟
    public static final int MAX_EXCEPTIONNUM_V30 = 32;  //9000璁惧鏈�澶у紓甯稿鐞嗘暟
    public static final int MAX_EXCEPTIONNUM = 16;   //8000璁惧鏈�澶у紓甯稿鐞嗘暟
    public static final int MAX_LINK = 6;    //8000璁惧鍗曢�氶亾鏈�澶ц棰戞祦杩炴帴鏁�
    public static final int MAX_DECPOOLNUM = 4;   //鍗曡矾瑙ｇ爜鍣ㄦ瘡涓В鐮侀�氶亾鏈�澶у彲寰幆瑙ｇ爜鏁�
    public static final int MAX_DECNUM = 4;    //鍗曡矾瑙ｇ爜鍣ㄧ殑鏈�澶цВ鐮侀�氶亾鏁帮紙瀹為檯鍙湁涓�涓紝鍏朵粬涓変釜淇濈暀锛�
    public static final int MAX_TRANSPARENTNUM = 2;   //鍗曡矾瑙ｇ爜鍣ㄥ彲閰嶇疆鏈�澶ч�忔槑閫氶亾鏁�
    public static final int MAX_CYCLE_CHAN = 16;   //鍗曡矾瑙ｇ爜鍣ㄦ渶澶ц疆寰�氶亾鏁�
    public static final int MAX_DIRNAME_LENGTH = 80;   //鏈�澶х洰褰曢暱搴�
    public static final int MAX_STRINGNUM_V30 = 8;		//9000璁惧鏈�澶SD瀛楃琛屾暟鏁�
    public static final int MAX_STRINGNUM = 4;   //8000璁惧鏈�澶SD瀛楃琛屾暟鏁�
    public static final int MAX_STRINGNUM_EX = 8;   //8000瀹氬埗鎵╁睍
    public static final int MAX_AUXOUT_V30 = 16;   //9000璁惧鏈�澶ц緟鍔╄緭鍑烘暟
    public static final int MAX_AUXOUT = 4;      //8000璁惧鏈�澶ц緟鍔╄緭鍑烘暟
    public static final int MAX_HD_GROUP = 16;   //9000璁惧鏈�澶х‖鐩樼粍鏁�
    public static final int MAX_NFS_DISK = 8;    //8000璁惧鏈�澶FS纭洏鏁�
    public static final int IW_ESSID_MAX_SIZE = 32;    //WIFI鐨凷SID鍙烽暱搴�
    public static final int IW_ENCODING_TOKEN_MAX = 32;   //WIFI瀵嗛攣鏈�澶у瓧鑺傛暟
    public static final int MAX_SERIAL_NUM = 64;    //鏈�澶氭敮鎸佺殑閫忔槑閫氶亾璺暟
    public static final int MAX_DDNS_NUMS = 10;   //9000璁惧鏈�澶у彲閰峝dns鏁�
    public static final int MAX_DOMAIN_NAME = 64;	/* 鏈�澶у煙鍚嶉暱搴� */

    public static final int MAX_EMAIL_ADDR_LEN = 48;  //鏈�澶mail鍦板潃闀垮害
    public static final int MAX_EMAIL_PWD_LEN = 32;     //鏈�澶mail瀵嗙爜闀垮害
    public static final int MAXPROGRESS = 100;  //鍥炴斁鏃剁殑鏈�澶х櫨鍒嗙巼
    public static final int MAX_SERIALNUM = 2;    //8000璁惧鏀寔鐨勪覆鍙ｆ暟 1-232锛� 2-485
    public static final int CARDNUM_LEN = 20;    //鍗″彿闀垮害
    public static final int MAX_VIDEOOUT_V30 = 4;      //9000璁惧鐨勮棰戣緭鍑烘暟
    public static final int MAX_VIDEOOUT = 2;      //8000璁惧鐨勮棰戣緭鍑烘暟
    public static final int MAX_PRESET_V30 = 256;	/* 9000璁惧鏀寔鐨勪簯鍙伴缃偣鏁� */
    public static final int MAX_TRACK_V30 = 256;	/* 9000璁惧鏀寔鐨勪簯鍙拌建杩规暟 */
    public static final int MAX_CRUISE_V30 = 256;	/* 9000璁惧鏀寔鐨勪簯鍙板贰鑸暟 */
    public static final int MAX_PRESET = 128;	/* 8000璁惧鏀寔鐨勪簯鍙伴缃偣鏁� */
    public static final int MAX_TRACK = 128;	/* 8000璁惧鏀寔鐨勪簯鍙拌建杩规暟 */
    public static final int MAX_CRUISE = 128;	/* 8000璁惧鏀寔鐨勪簯鍙板贰鑸暟 */
    public static final int CRUISE_MAX_PRESET_NUMS = 32;    /* 涓�鏉″贰鑸渶澶氱殑宸¤埅鐐� */
    public static final int MAX_SERIAL_PORT = 8;    //9000璁惧鏀寔232涓插彛鏁�
    public static final int MAX_PREVIEW_MODE = 8;    /* 璁惧鏀寔鏈�澶ч瑙堟ā寮忔暟鐩� 1鐢婚潰,4鐢婚潰,9鐢婚潰,16鐢婚潰.... */
    public static final int MAX_MATRIXOUT = 16;  /* 鏈�澶фā鎷熺煩闃佃緭鍑轰釜鏁� */
    public static final int LOG_INFO_LEN = 11840; /* 鏃ュ織闄勫姞淇℃伅 */
    public static final int DESC_LEN = 16;    /* 浜戝彴鎻忚堪瀛楃涓查暱搴� */
    public static final int PTZ_PROTOCOL_NUM = 200;   /* 9000鏈�澶ф敮鎸佺殑浜戝彴鍗忚鏁� */
    public static final int MAX_AUDIO = 1;    //8000璇煶瀵硅閫氶亾鏁�
    public static final int MAX_AUDIO_V30 = 2;   //9000璇煶瀵硅閫氶亾鏁�
    public static final int MAX_CHANNUM = 16;   //8000璁惧鏈�澶ч�氶亾鏁�
    public static final int MAX_ALARMIN = 16;  //8000璁惧鏈�澶ф姤璀﹁緭鍏ユ暟
    public static final int MAX_ALARMOUT = 4;    //8000璁惧鏈�澶ф姤璀﹁緭鍑烘暟
//9000 IPC鎺ュ叆
    public static final int MAX_ANALOG_CHANNUM = 32;    //鏈�澶�32涓ā鎷熼�氶亾
    public static final int MAX_ANALOG_ALARMOUT = 32;    //鏈�澶�32璺ā鎷熸姤璀﹁緭鍑�
    public static final int MAX_ANALOG_ALARMIN = 32;    //鏈�澶�32璺ā鎷熸姤璀﹁緭鍏�
    public static final int MAX_IP_DEVICE = 32;    //鍏佽鎺ュ叆鐨勬渶澶P璁惧鏁�
    public static final int MAX_IP_CHANNEL = 32;   //鍏佽鍔犲叆鐨勬渶澶欼P閫氶亾鏁�
    public static final int MAX_IP_ALARMIN = 128;   //鍏佽鍔犲叆鐨勬渶澶氭姤璀﹁緭鍏ユ暟
    public static final int MAX_IP_ALARMOUT = 64; //鍏佽鍔犲叆鐨勬渶澶氭姤璀﹁緭鍑烘暟

    /* 鏈�澶ф敮鎸佺殑閫氶亾鏁� 鏈�澶фā鎷熷姞涓婃渶澶P鏀寔 */
    public static final int MAX_CHANNUM_V30 = (MAX_ANALOG_CHANNUM + MAX_IP_CHANNEL);//64
    public static final int MAX_ALARMOUT_V30 = (MAX_ANALOG_ALARMOUT + MAX_IP_ALARMOUT);//96
    public static final int MAX_ALARMIN_V30 = (MAX_ANALOG_ALARMIN + MAX_IP_ALARMIN);//160

    public static final int MAX_LICENSE_LEN = 16;
    public static final int VCA_MAX_POLYGON_POINT_NUM = 10;

    public static final int MAX_ID_NUM_LEN                =  32;  //鏈�澶ц韩浠借瘉鍙烽暱搴�
    public static final int MAX_ID_NAME_LEN               = 128;   //鏈�澶у鍚嶉暱搴�
    public static final int MAX_ID_ADDR_LEN               = 280;   //鏈�澶т綇鍧�闀垮害
    public static final int MAX_ID_ISSUING_AUTHORITY_LEN  = 128; //鏈�澶х鍙戞満鍏抽暱搴�
    /*******************鍏ㄥ眬閿欒鐮� begin**********************/
    public static final int NET_DVR_NOERROR = 0;	//娌℃湁閿欒
    public static final int NET_DVR_PASSWORD_ERROR = 1;	//鐢ㄦ埛鍚嶅瘑鐮侀敊璇�
    public static final int NET_DVR_NOENOUGHPRI = 2;//鏉冮檺涓嶈冻
    public static final int NET_DVR_NOINIT = 3;//娌℃湁鍒濆鍖�
    public static final int NET_DVR_CHANNEL_ERROR = 4;	//閫氶亾鍙烽敊璇�
    public static final int NET_DVR_OVER_MAXLINK = 5;	//杩炴帴鍒癉VR鐨勫鎴风涓暟瓒呰繃鏈�澶�
    public static final int NET_DVR_VERSIONNOMATCH = 6;	//鐗堟湰涓嶅尮閰�
    public static final int NET_DVR_NETWORK_FAIL_CONNECT = 7;//杩炴帴鏈嶅姟鍣ㄥけ璐�
    public static final int NET_DVR_NETWORK_SEND_ERROR = 8;	//鍚戞湇鍔″櫒鍙戦�佸け璐�
    public static final int NET_DVR_NETWORK_RECV_ERROR = 9;	//浠庢湇鍔″櫒鎺ユ敹鏁版嵁澶辫触
    public static final int NET_DVR_NETWORK_RECV_TIMEOUT = 10;	//浠庢湇鍔″櫒鎺ユ敹鏁版嵁瓒呮椂
    public static final int NET_DVR_NETWORK_ERRORDATA = 11;	//浼犻�佺殑鏁版嵁鏈夎
    public static final int NET_DVR_ORDER_ERROR = 12;	//璋冪敤娆″簭閿欒
    public static final int NET_DVR_OPERNOPERMIT = 13;	//鏃犳鏉冮檺
    public static final int NET_DVR_COMMANDTIMEOUT = 14;	//DVR鍛戒护鎵ц瓒呮椂
    public static final int NET_DVR_ERRORSERIALPORT = 15;	//涓插彛鍙烽敊璇�
    public static final int NET_DVR_ERRORALARMPORT = 16;	//鎶ヨ绔彛閿欒
    public static final int NET_DVR_PARAMETER_ERROR = 17;//鍙傛暟閿欒
    public static final int NET_DVR_CHAN_EXCEPTION = 18;	//鏈嶅姟鍣ㄩ�氶亾澶勪簬閿欒鐘舵��
    public static final int NET_DVR_NODISK = 19;	//娌℃湁纭洏
    public static final int NET_DVR_ERRORDISKNUM = 20;	//纭洏鍙烽敊璇�
    public static final int NET_DVR_DISK_FULL = 21;	//鏈嶅姟鍣ㄧ‖鐩樻弧
    public static final int NET_DVR_DISK_ERROR = 22;//鏈嶅姟鍣ㄧ‖鐩樺嚭閿�
    public static final int NET_DVR_NOSUPPORT = 23;//鏈嶅姟鍣ㄤ笉鏀寔
    public static final int NET_DVR_BUSY = 24;//鏈嶅姟鍣ㄥ繖
    public static final int NET_DVR_MODIFY_FAIL = 25;//鏈嶅姟鍣ㄤ慨鏀逛笉鎴愬姛
    public static final int NET_DVR_PASSWORD_FORMAT_ERROR = 26;//瀵嗙爜杈撳叆鏍煎紡涓嶆纭�
    public static final int NET_DVR_DISK_FORMATING = 27;	//纭洏姝ｅ湪鏍煎紡鍖栵紝涓嶈兘鍚姩鎿嶄綔
    public static final int NET_DVR_DVRNORESOURCE = 28;	//DVR璧勬簮涓嶈冻
    public static final int NET_DVR_DVROPRATEFAILED = 29; //DVR鎿嶄綔澶辫触
    public static final int NET_DVR_OPENHOSTSOUND_FAIL = 30; //鎵撳紑PC澹伴煶澶辫触
    public static final int NET_DVR_DVRVOICEOPENED = 31; //鏈嶅姟鍣ㄨ闊冲璁茶鍗犵敤
    public static final int NET_DVR_TIMEINPUTERROR = 32; //鏃堕棿杈撳叆涓嶆纭�
    public static final int NET_DVR_NOSPECFILE = 33;  //鍥炴斁鏃舵湇鍔″櫒娌℃湁鎸囧畾鐨勬枃浠�
    public static final int NET_DVR_CREATEFILE_ERROR = 34;	//鍒涘缓鏂囦欢鍑洪敊
    public static final int NET_DVR_FILEOPENFAIL = 35; //鎵撳紑鏂囦欢鍑洪敊
    public static final int NET_DVR_OPERNOTFINISH = 36; //涓婃鐨勬搷浣滆繕娌℃湁瀹屾垚
    public static final int NET_DVR_GETPLAYTIMEFAIL = 37; //鑾峰彇褰撳墠鎾斁鐨勬椂闂村嚭閿�
    public static final int NET_DVR_PLAYFAIL = 38; //鎾斁鍑洪敊
    public static final int NET_DVR_FILEFORMAT_ERROR = 39;//鏂囦欢鏍煎紡涓嶆纭�
    public static final int NET_DVR_DIR_ERROR = 40;	//璺緞閿欒
    public static final int NET_DVR_ALLOC_RESOURCE_ERROR = 41;//璧勬簮鍒嗛厤閿欒
    public static final int NET_DVR_AUDIO_MODE_ERROR = 42;	//澹板崱妯″紡閿欒
    public static final int NET_DVR_NOENOUGH_BUF = 43;	//缂撳啿鍖哄お灏�
    public static final int NET_DVR_CREATESOCKET_ERROR = 44;	//鍒涘缓SOCKET鍑洪敊
    public static final int NET_DVR_SETSOCKET_ERROR = 45;	//璁剧疆SOCKET鍑洪敊
    public static final int NET_DVR_MAX_NUM = 46;	//涓暟杈惧埌鏈�澶�
    public static final int NET_DVR_USERNOTEXIST = 47;	//鐢ㄦ埛涓嶅瓨鍦�
    public static final int NET_DVR_WRITEFLASHERROR = 48;//鍐橣LASH鍑洪敊
    public static final int NET_DVR_UPGRADEFAIL = 49;//DVR鍗囩骇澶辫触
    public static final int NET_DVR_CARDHAVEINIT = 50; //瑙ｇ爜鍗″凡缁忓垵濮嬪寲杩�
    public static final int NET_DVR_PLAYERFAILED = 51;	//璋冪敤鎾斁搴撲腑鏌愪釜鍑芥暟澶辫触
    public static final int NET_DVR_MAX_USERNUM = 52; //璁惧绔敤鎴锋暟杈惧埌鏈�澶�
    public static final int NET_DVR_GETLOCALIPANDMACFAIL = 53;//鑾峰緱瀹㈡埛绔殑IP鍦板潃鎴栫墿鐞嗗湴鍧�澶辫触
    public static final int NET_DVR_NOENCODEING = 54;	//璇ラ�氶亾娌℃湁缂栫爜
    public static final int NET_DVR_IPMISMATCH = 55;	//IP鍦板潃涓嶅尮閰�
    public static final int NET_DVR_MACMISMATCH = 56;//MAC鍦板潃涓嶅尮閰�
    public static final int NET_DVR_UPGRADELANGMISMATCH = 57;//鍗囩骇鏂囦欢璇█涓嶅尮閰�
    public static final int NET_DVR_MAX_PLAYERPORT = 58;//鎾斁鍣ㄨ矾鏁拌揪鍒版渶澶�
    public static final int NET_DVR_NOSPACEBACKUP = 59;//澶囦唤璁惧涓病鏈夎冻澶熺┖闂磋繘琛屽浠�
    public static final int NET_DVR_NODEVICEBACKUP = 60;	//娌℃湁鎵惧埌鎸囧畾鐨勫浠借澶�
    public static final int NET_DVR_PICTURE_BITS_ERROR = 61;	//鍥惧儚绱犱綅鏁颁笉绗︼紝闄�24鑹�
    public static final int NET_DVR_PICTURE_DIMENSION_ERROR = 62;//鍥剧墖楂�*瀹借秴闄愶紝 闄�128*256
    public static final int NET_DVR_PICTURE_SIZ_ERROR = 63;	//鍥剧墖澶у皬瓒呴檺锛岄檺100K
    public static final int NET_DVR_LOADPLAYERSDKFAILED = 64;	//杞藉叆褰撳墠鐩綍涓婸layer Sdk鍑洪敊
    public static final int NET_DVR_LOADPLAYERSDKPROC_ERROR = 65;	//鎵句笉鍒癙layer Sdk涓煇涓嚱鏁板叆鍙�
    public static final int NET_DVR_LOADDSSDKFAILED = 66;	//杞藉叆褰撳墠鐩綍涓婦Ssdk鍑洪敊
    public static final int NET_DVR_LOADDSSDKPROC_ERROR = 67;	//鎵句笉鍒癉sSdk涓煇涓嚱鏁板叆鍙�
    public static final int NET_DVR_DSSDK_ERROR = 68;	//璋冪敤纭В鐮佸簱DsSdk涓煇涓嚱鏁板け璐�
    public static final int NET_DVR_VOICEMONOPOLIZE = 69;	//澹板崱琚嫭鍗�
    public static final int NET_DVR_JOINMULTICASTFAILED = 70;	//鍔犲叆澶氭挱缁勫け璐�
    public static final int NET_DVR_CREATEDIR_ERROR = 71;	//寤虹珛鏃ュ織鏂囦欢鐩綍澶辫触
    public static final int NET_DVR_BINDSOCKET_ERROR = 72;	//缁戝畾濂楁帴瀛楀け璐�
    public static final int NET_DVR_SOCKETCLOSE_ERROR = 73;	//socket杩炴帴涓柇锛屾閿欒閫氬父鏄敱浜庤繛鎺ヤ腑鏂垨鐩殑鍦颁笉鍙揪
    public static final int NET_DVR_USERID_ISUSING = 74;	//娉ㄩ攢鏃剁敤鎴稩D姝ｅ湪杩涜鏌愭搷浣�
    public static final int NET_DVR_SOCKETLISTEN_ERROR = 75;	//鐩戝惉澶辫触
    public static final int NET_DVR_PROGRAM_EXCEPTION = 76;	//绋嬪簭寮傚父
    public static final int NET_DVR_WRITEFILE_FAILED = 77;	//鍐欐枃浠跺け璐�
    public static final int NET_DVR_FORMAT_READONLY = 78;//绂佹鏍煎紡鍖栧彧璇荤‖鐩�
    public static final int NET_DVR_WITHSAMEUSERNAME = 79;//鐢ㄦ埛閰嶇疆缁撴瀯涓瓨鍦ㄧ浉鍚岀殑鐢ㄦ埛鍚�
    public static final int NET_DVR_DEVICETYPE_ERROR = 80; /*瀵煎叆鍙傛暟鏃惰澶囧瀷鍙蜂笉鍖归厤*/
    public static final int NET_DVR_LANGUAGE_ERROR = 81; /*瀵煎叆鍙傛暟鏃惰瑷�涓嶅尮閰�*/
    public static final int NET_DVR_PARAVERSION_ERROR = 82; /*瀵煎叆鍙傛暟鏃惰蒋浠剁増鏈笉鍖归厤*/
    public static final int NET_DVR_IPCHAN_NOTALIVE = 83; /*棰勮鏃跺鎺P閫氶亾涓嶅湪绾�*/
    public static final int NET_DVR_RTSP_SDK_ERROR = 84;	/*鍔犺浇楂樻竻IPC閫氳搴揝treamTransClient.dll澶辫触*/
    public static final int NET_DVR_CONVERT_SDK_ERROR = 85;	/*鍔犺浇杞爜搴撳け璐�*/
    public static final int NET_DVR_IPC_COUNT_OVERFLOW = 86; /*瓒呭嚭鏈�澶х殑ip鎺ュ叆閫氶亾鏁�*/
    public static final int NET_PLAYM4_NOERROR = 500;	//no error
    public static final int NET_PLAYM4_PARA_OVER = 501;//input parameter is invalid;
    public static final int NET_PLAYM4_ORDER_ERROR = 502;//The order of the function to be called is error.
    public static final int NET_PLAYM4_TIMER_ERROR = 503;//Create multimedia clock failed;
    public static final int NET_PLAYM4_DEC_VIDEO_ERROR = 504;//Decode video data failed.
    public static final int NET_PLAYM4_DEC_AUDIO_ERROR = 505;//Decode audio data failed.
    public static final int NET_PLAYM4_ALLOC_MEMORY_ERROR = 506;	//Allocate memory failed.
    public static final int NET_PLAYM4_OPEN_FILE_ERROR = 507;	//Open the file failed.
    public static final int NET_PLAYM4_CREATE_OBJ_ERROR = 508;//Create thread or event failed
    public static final int NET_PLAYM4_CREATE_DDRAW_ERROR = 509;//Create DirectDraw object failed.
    public static final int NET_PLAYM4_CREATE_OFFSCREEN_ERROR = 510;//failed when creating off-screen surface.
    public static final int NET_PLAYM4_BUF_OVER = 511;	//buffer is overflow
    public static final int NET_PLAYM4_CREATE_SOUND_ERROR = 512;	//failed when creating audio device.
    public static final int NET_PLAYM4_SET_VOLUME_ERROR = 513;//Set volume failed
    public static final int NET_PLAYM4_SUPPORT_FILE_ONLY = 514;//The function only support play file.
    public static final int NET_PLAYM4_SUPPORT_STREAM_ONLY = 515;//The function only support play stream.
    public static final int NET_PLAYM4_SYS_NOT_SUPPORT = 516;//System not support.
    public static final int NET_PLAYM4_FILEHEADER_UNKNOWN = 517;	//No file header.
    public static final int NET_PLAYM4_VERSION_INCORRECT = 518;	//The version of decoder and encoder is not adapted.
    public static final int NET_PALYM4_INIT_DECODER_ERROR = 519;	//Initialize decoder failed.
    public static final int NET_PLAYM4_CHECK_FILE_ERROR = 520;	//The file data is unknown.
    public static final int NET_PLAYM4_INIT_TIMER_ERROR = 521;	//Initialize multimedia clock failed.
    public static final int NET_PLAYM4_BLT_ERROR = 522;//Blt failed.
    public static final int NET_PLAYM4_UPDATE_ERROR = 523;//Update failed.
    public static final int NET_PLAYM4_OPEN_FILE_ERROR_MULTI = 524; //openfile error, streamtype is multi
    public static final int NET_PLAYM4_OPEN_FILE_ERROR_VIDEO = 525; //openfile error, streamtype is video
    public static final int NET_PLAYM4_JPEG_COMPRESS_ERROR = 526; //JPEG compress error
    public static final int NET_PLAYM4_EXTRACT_NOT_SUPPORT = 527;	//Don't support the version of this file.
    public static final int NET_PLAYM4_EXTRACT_DATA_ERROR = 528;	//extract video data failed.
    /*******************鍏ㄥ眬閿欒鐮� end**********************/
    /*************************************************
    NET_DVR_IsSupport()杩斿洖鍊�
    1锛�9浣嶅垎鍒〃绀轰互涓嬩俊鎭紙浣嶄笌鏄疶RUE)琛ㄧず鏀寔锛�
     **************************************************/
    public static final int NET_DVR_SUPPORT_DDRAW = 0x01;//鏀寔DIRECTDRAW锛屽鏋滀笉鏀寔锛屽垯鎾斁鍣ㄤ笉鑳藉伐浣滐紱
    public static final int NET_DVR_SUPPORT_BLT = 0x02;//鏄惧崱鏀寔BLT鎿嶄綔锛屽鏋滀笉鏀寔锛屽垯鎾斁鍣ㄤ笉鑳藉伐浣滐紱
    public static final int NET_DVR_SUPPORT_BLTFOURCC = 0x04;//鏄惧崱BLT鏀寔棰滆壊杞崲锛屽鏋滀笉鏀寔锛屾挱鏀惧櫒浼氱敤杞欢鏂规硶浣淩GB杞崲锛�
    public static final int NET_DVR_SUPPORT_BLTSHRINKX = 0x08;//鏄惧崱BLT鏀寔X杞寸缉灏忥紱濡傛灉涓嶆敮鎸侊紝绯荤粺浼氱敤杞欢鏂规硶杞崲锛�
    public static final int NET_DVR_SUPPORT_BLTSHRINKY = 0x10;//鏄惧崱BLT鏀寔Y杞寸缉灏忥紱濡傛灉涓嶆敮鎸侊紝绯荤粺浼氱敤杞欢鏂规硶杞崲锛�
    public static final int NET_DVR_SUPPORT_BLTSTRETCHX = 0x20;//鏄惧崱BLT鏀寔X杞存斁澶э紱濡傛灉涓嶆敮鎸侊紝绯荤粺浼氱敤杞欢鏂规硶杞崲锛�
    public static final int NET_DVR_SUPPORT_BLTSTRETCHY = 0x40;//鏄惧崱BLT鏀寔Y杞存斁澶э紱濡傛灉涓嶆敮鎸侊紝绯荤粺浼氱敤杞欢鏂规硶杞崲锛�
    public static final int NET_DVR_SUPPORT_SSE = 0x80;//CPU鏀寔SSE鎸囦护锛孖ntel Pentium3浠ヤ笂鏀寔SSE鎸囦护锛�
    public static final int NET_DVR_SUPPORT_MMX = 0x100;//CPU鏀寔MMX鎸囦护闆嗭紝Intel Pentium3浠ヤ笂鏀寔SSE鎸囦护锛�
    /**********************浜戝彴鎺у埗鍛戒护 begin*************************/
    public static final int LIGHT_PWRON = 2;	/* 鎺ラ�氱伅鍏夌數婧� */
    public static final int WIPER_PWRON = 3;	/* 鎺ラ�氶洦鍒峰紑鍏� */
    public static final int FAN_PWRON = 4;	/* 鎺ラ�氶鎵囧紑鍏� */
    public static final int HEATER_PWRON = 5;	/* 鎺ラ�氬姞鐑櫒寮�鍏� */
    public static final int AUX_PWRON1 = 6;	/* 鎺ラ�氳緟鍔╄澶囧紑鍏� */
    public static final int AUX_PWRON2 = 7;	/* 鎺ラ�氳緟鍔╄澶囧紑鍏� */
    public static final int SET_PRESET = 8;	/* 璁剧疆棰勭疆鐐� */
    public static final int CLE_PRESET = 9;	/* 娓呴櫎棰勭疆鐐� */
    public static final int ZOOM_IN = 11;	/* 鐒﹁窛浠ラ�熷害SS鍙樺ぇ(鍊嶇巼鍙樺ぇ) */
    public static final int ZOOM_OUT = 12;	/* 鐒﹁窛浠ラ�熷害SS鍙樺皬(鍊嶇巼鍙樺皬) */
    public static final int FOCUS_NEAR = 13; /* 鐒︾偣浠ラ�熷害SS鍓嶈皟 */
    public static final int FOCUS_FAR = 14; /* 鐒︾偣浠ラ�熷害SS鍚庤皟 */
    public static final int IRIS_OPEN = 15; /* 鍏夊湀浠ラ�熷害SS鎵╁ぇ */
    public static final int IRIS_CLOSE = 16; /* 鍏夊湀浠ラ�熷害SS缂╁皬 */
    public static final int TILT_UP = 21;	/* 浜戝彴浠S鐨勯�熷害涓婁话 */
    public static final int TILT_DOWN = 22;	/* 浜戝彴浠S鐨勯�熷害涓嬩刊 */
    public static final int PAN_LEFT = 23;	/* 浜戝彴浠S鐨勯�熷害宸﹁浆 */
    public static final int PAN_RIGHT = 24;	/* 浜戝彴浠S鐨勯�熷害鍙宠浆 */
    public static final int UP_LEFT = 25;	/* 浜戝彴浠S鐨勯�熷害涓婁话鍜屽乏杞� */
    public static final int UP_RIGHT = 26;	/* 浜戝彴浠S鐨勯�熷害涓婁话鍜屽彸杞� */
    public static final int DOWN_LEFT = 27;	/* 浜戝彴浠S鐨勯�熷害涓嬩刊鍜屽乏杞� */
    public static final int DOWN_RIGHT = 28;	/* 浜戝彴浠S鐨勯�熷害涓嬩刊鍜屽彸杞� */
    public static final int PAN_AUTO = 29;	/* 浜戝彴浠S鐨勯�熷害宸﹀彸鑷姩鎵弿 */
    public static final int FILL_PRE_SEQ = 30;	/* 灏嗛缃偣鍔犲叆宸¤埅搴忓垪 */
    public static final int SET_SEQ_DWELL = 31;	/* 璁剧疆宸¤埅鐐瑰仠椤挎椂闂� */
    public static final int SET_SEQ_SPEED = 32;	/* 璁剧疆宸¤埅閫熷害 */
    public static final int CLE_PRE_SEQ = 33;/* 灏嗛缃偣浠庡贰鑸簭鍒椾腑鍒犻櫎 */
    public static final int STA_MEM_CRUISE = 34;/* 寮�濮嬭褰曡建杩� */
    public static final int STO_MEM_CRUISE = 35;/* 鍋滄璁板綍杞ㄨ抗 */
    public static final int RUN_CRUISE = 36;	/* 寮�濮嬭建杩� */
    public static final int RUN_SEQ = 37;	/* 寮�濮嬪贰鑸� */
    public static final int STOP_SEQ = 38;	/* 鍋滄宸¤埅 */
    public static final int GOTO_PRESET = 39;	/* 蹇悆杞埌棰勭疆鐐� */

    /**********************浜戝彴鎺у埗鍛戒护 end*************************/
    /*************************************************
    鍥炴斁鏃舵挱鏀炬帶鍒跺懡浠ゅ畯瀹氫箟
    NET_DVR_PlayBackControl
    NET_DVR_PlayControlLocDisplay
    NET_DVR_DecPlayBackCtrl鐨勫畯瀹氫箟
    鍏蜂綋鏀寔鏌ョ湅鍑芥暟璇存槑鍜屼唬鐮�
     **************************************************/
    public static final int NET_DVR_PLAYSTART = 1;//寮�濮嬫挱鏀�
    public static final int NET_DVR_PLAYSTOP = 2;//鍋滄鎾斁
    public static final int NET_DVR_PLAYPAUSE = 3;//鏆傚仠鎾斁
    public static final int NET_DVR_PLAYRESTART = 4;//鎭㈠鎾斁
    public static final int NET_DVR_PLAYFAST = 5;//蹇斁
    public static final int NET_DVR_PLAYSLOW = 6;//鎱㈡斁
    public static final int NET_DVR_PLAYNORMAL = 7;//姝ｅ父閫熷害
    public static final int NET_DVR_PLAYFRAME = 8;//鍗曞抚鏀�
    public static final int NET_DVR_PLAYSTARTAUDIO = 9;//鎵撳紑澹伴煶
    public static final int NET_DVR_PLAYSTOPAUDIO = 10;//鍏抽棴澹伴煶
    public static final int NET_DVR_PLAYAUDIOVOLUME = 11;//璋冭妭闊抽噺
    public static final int NET_DVR_PLAYSETPOS = 12;//鏀瑰彉鏂囦欢鍥炴斁鐨勮繘搴�
    public static final int NET_DVR_PLAYGETPOS = 13;//鑾峰彇鏂囦欢鍥炴斁鐨勮繘搴�
    public static final int NET_DVR_PLAYGETTIME = 14;//鑾峰彇褰撳墠宸茬粡鎾斁鐨勬椂闂�(鎸夋枃浠跺洖鏀剧殑鏃跺�欐湁鏁�)
    public static final int NET_DVR_PLAYGETFRAME = 15;//鑾峰彇褰撳墠宸茬粡鎾斁鐨勫抚鏁�(鎸夋枃浠跺洖鏀剧殑鏃跺�欐湁鏁�)
    public static final int NET_DVR_GETTOTALFRAMES = 16;//鑾峰彇褰撳墠鎾斁鏂囦欢鎬荤殑甯ф暟(鎸夋枃浠跺洖鏀剧殑鏃跺�欐湁鏁�)
    public static final int NET_DVR_GETTOTALTIME = 17;//鑾峰彇褰撳墠鎾斁鏂囦欢鎬荤殑鏃堕棿(鎸夋枃浠跺洖鏀剧殑鏃跺�欐湁鏁�)
    public static final int NET_DVR_THROWBFRAME = 20;//涓甯�
    public static final int NET_DVR_SETSPEED = 24;//璁剧疆鐮佹祦閫熷害
    public static final int NET_DVR_KEEPALIVE = 25;//淇濇寔涓庤澶囩殑蹇冭烦(濡傛灉鍥炶皟闃诲锛屽缓璁�2绉掑彂閫佷竴娆�)
//杩滅▼鎸夐敭瀹氫箟濡備笅锛�
/* key value send to CONFIG program */
    public static final int KEY_CODE_1 = 1;
    public static final int KEY_CODE_2 = 2;
    public static final int KEY_CODE_3 = 3;
    public static final int KEY_CODE_4 = 4;
    public static final int KEY_CODE_5 = 5;
    public static final int KEY_CODE_6 = 6;
    public static final int KEY_CODE_7 = 7;
    public static final int KEY_CODE_8 = 8;
    public static final int KEY_CODE_9 = 9;
    public static final int KEY_CODE_0 = 10;
    public static final int KEY_CODE_POWER = 11;
    public static final int KEY_CODE_MENU = 12;
    public static final int KEY_CODE_ENTER = 13;
    public static final int KEY_CODE_CANCEL = 14;
    public static final int KEY_CODE_UP = 15;
    public static final int KEY_CODE_DOWN = 16;
    public static final int KEY_CODE_LEFT = 17;
    public static final int KEY_CODE_RIGHT = 18;
    public static final int KEY_CODE_EDIT = 19;
    public static final int KEY_CODE_ADD = 20;
    public static final int KEY_CODE_MINUS = 21;
    public static final int KEY_CODE_PLAY = 22;
    public static final int KEY_CODE_REC = 23;
    public static final int KEY_CODE_PAN = 24;
    public static final int KEY_CODE_M = 25;
    public static final int KEY_CODE_A = 26;
    public static final int KEY_CODE_F1 = 27;
    public static final int KEY_CODE_F2 = 28;

    /* for PTZ control */
    public static final int KEY_PTZ_UP_START = KEY_CODE_UP;
    public static final int KEY_PTZ_UP_STO = 32;
    public static final int KEY_PTZ_DOWN_START = KEY_CODE_DOWN;
    public static final int KEY_PTZ_DOWN_STOP = 33;
    public static final int KEY_PTZ_LEFT_START = KEY_CODE_LEFT;
    public static final int KEY_PTZ_LEFT_STOP = 34;
    public static final int KEY_PTZ_RIGHT_START = KEY_CODE_RIGHT;
    public static final int KEY_PTZ_RIGHT_STOP = 35;
    public static final int KEY_PTZ_AP1_START = KEY_CODE_EDIT;/* 鍏夊湀+ */
    public static final int KEY_PTZ_AP1_STOP = 36;
    public static final int KEY_PTZ_AP2_START = KEY_CODE_PAN;/* 鍏夊湀- */
    public static final int KEY_PTZ_AP2_STOP = 37;
    public static final int KEY_PTZ_FOCUS1_START = KEY_CODE_A;/* 鑱氱劍+ */
    public static final int KEY_PTZ_FOCUS1_STOP = 38;
    public static final int KEY_PTZ_FOCUS2_START = KEY_CODE_M ;/* 鑱氱劍- */
    public static final int KEY_PTZ_FOCUS2_STOP = 39;
    public static final int KEY_PTZ_B1_START = 40;/* 鍙樺��+ */
    public static final int KEY_PTZ_B1_STOP = 41;
    public static final int KEY_PTZ_B2_START = 42;/* 鍙樺��- */
    public static final int KEY_PTZ_B2_STOP = 43;
//9000鏂板
    public static final int KEY_CODE_11 = 44;
    public static final int KEY_CODE_12 = 45;
    public static final int KEY_CODE_13 = 46;
    public static final int KEY_CODE_14 = 47;
    public static final int KEY_CODE_15 = 48;
    public static final int KEY_CODE_16 = 49;
    /*************************鍙傛暟閰嶇疆鍛戒护 begin*******************************/
//鐢ㄤ簬NET_DVR_SetDVRConfig鍜孨ET_DVR_GetDVRConfig,娉ㄦ剰鍏跺搴旂殑閰嶇疆缁撴瀯
    public static final int NET_DVR_GET_DEVICECFG = 100;	//鑾峰彇璁惧鍙傛暟
    public static final int NET_DVR_SET_DEVICECFG = 101;	//璁剧疆璁惧鍙傛暟
    public static final int NET_DVR_GET_NETCFG = 102;	//鑾峰彇缃戠粶鍙傛暟
    public static final int NET_DVR_SET_NETCFG = 103;	//璁剧疆缃戠粶鍙傛暟
    public static final int NET_DVR_GET_PICCFG = 104;	//鑾峰彇鍥捐薄鍙傛暟
    public static final int NET_DVR_SET_PICCFG = 105;	//璁剧疆鍥捐薄鍙傛暟
    public static final int NET_DVR_GET_COMPRESSCFG = 106;	//鑾峰彇鍘嬬缉鍙傛暟
    public static final int NET_DVR_SET_COMPRESSCFG = 107;	//璁剧疆鍘嬬缉鍙傛暟
    public static final int NET_DVR_GET_RECORDCFG = 108;	//鑾峰彇褰曞儚鏃堕棿鍙傛暟
    public static final int NET_DVR_SET_RECORDCFG = 109;	//璁剧疆褰曞儚鏃堕棿鍙傛暟
    public static final int NET_DVR_GET_DECODERCFG = 110;	//鑾峰彇瑙ｇ爜鍣ㄥ弬鏁�
    public static final int NET_DVR_SET_DECODERCFG = 111;	//璁剧疆瑙ｇ爜鍣ㄥ弬鏁�
    public static final int NET_DVR_GET_RS232CFG = 112;	//鑾峰彇232涓插彛鍙傛暟
    public static final int NET_DVR_SET_RS232CFG = 113;	//璁剧疆232涓插彛鍙傛暟
    public static final int NET_DVR_GET_ALARMINCFG = 114;	//鑾峰彇鎶ヨ杈撳叆鍙傛暟
    public static final int NET_DVR_SET_ALARMINCFG = 115;	//璁剧疆鎶ヨ杈撳叆鍙傛暟
    public static final int NET_DVR_GET_ALARMOUTCFG = 116;	//鑾峰彇鎶ヨ杈撳嚭鍙傛暟
    public static final int NET_DVR_SET_ALARMOUTCFG = 117;	//璁剧疆鎶ヨ杈撳嚭鍙傛暟
    public static final int NET_DVR_GET_TIMECFG = 118;	//鑾峰彇DVR鏃堕棿
    public static final int NET_DVR_SET_TIMECFG = 119;		//璁剧疆DVR鏃堕棿
    public static final int NET_DVR_GET_PREVIEWCFG = 120;	//鑾峰彇棰勮鍙傛暟
    public static final int NET_DVR_SET_PREVIEWCFG = 121;	//璁剧疆棰勮鍙傛暟
    public static final int NET_DVR_GET_VIDEOOUTCFG = 122;	//鑾峰彇瑙嗛杈撳嚭鍙傛暟
    public static final int NET_DVR_SET_VIDEOOUTCFG = 123;	//璁剧疆瑙嗛杈撳嚭鍙傛暟
    public static final int NET_DVR_GET_USERCFG = 124;	//鑾峰彇鐢ㄦ埛鍙傛暟
    public static final int NET_DVR_SET_USERCFG = 125;	//璁剧疆鐢ㄦ埛鍙傛暟
    public static final int NET_DVR_GET_EXCEPTIONCFG = 126;	//鑾峰彇寮傚父鍙傛暟
    public static final int NET_DVR_SET_EXCEPTIONCFG = 127;	//璁剧疆寮傚父鍙傛暟
    public static final int NET_DVR_GET_ZONEANDDST = 128;	//鑾峰彇鏃跺尯鍜屽鏃跺埗鍙傛暟
    public static final int NET_DVR_SET_ZONEANDDST = 129;	//璁剧疆鏃跺尯鍜屽鏃跺埗鍙傛暟
    public static final int NET_DVR_GET_SHOWSTRING = 130;	//鑾峰彇鍙犲姞瀛楃鍙傛暟
    public static final int NET_DVR_SET_SHOWSTRING = 131;	//璁剧疆鍙犲姞瀛楃鍙傛暟
    public static final int NET_DVR_GET_EVENTCOMPCFG = 132;	//鑾峰彇浜嬩欢瑙﹀彂褰曞儚鍙傛暟
    public static final int NET_DVR_SET_EVENTCOMPCFG = 133;	//璁剧疆浜嬩欢瑙﹀彂褰曞儚鍙傛暟
    public static final int NET_DVR_GET_AUXOUTCFG = 140;	//鑾峰彇鎶ヨ瑙﹀彂杈呭姪杈撳嚭璁剧疆(HS璁惧杈呭姪杈撳嚭2006-02-28)
    public static final int NET_DVR_SET_AUXOUTCFG = 141;	//璁剧疆鎶ヨ瑙﹀彂杈呭姪杈撳嚭璁剧疆(HS璁惧杈呭姪杈撳嚭2006-02-28)
    public static final int NET_DVR_GET_PREVIEWCFG_AUX = 142;	//鑾峰彇-s绯诲垪鍙岃緭鍑洪瑙堝弬鏁�(-s绯诲垪鍙岃緭鍑�2006-04-13)
    public static final int NET_DVR_SET_PREVIEWCFG_AUX = 143;	//璁剧疆-s绯诲垪鍙岃緭鍑洪瑙堝弬鏁�(-s绯诲垪鍙岃緭鍑�2006-04-13)
    public static final int NET_DVR_GET_PICCFG_EX = 200;	//鑾峰彇鍥捐薄鍙傛暟(SDK_V14鎵╁睍鍛戒护)
    public static final int NET_DVR_SET_PICCFG_EX = 201;	//璁剧疆鍥捐薄鍙傛暟(SDK_V14鎵╁睍鍛戒护)
    public static final int NET_DVR_GET_USERCFG_EX = 202;	//鑾峰彇鐢ㄦ埛鍙傛暟(SDK_V15鎵╁睍鍛戒护)
    public static final int NET_DVR_SET_USERCFG_EX = 203;	//璁剧疆鐢ㄦ埛鍙傛暟(SDK_V15鎵╁睍鍛戒护)
    public static final int NET_DVR_GET_COMPRESSCFG_EX = 204;	//鑾峰彇鍘嬬缉鍙傛暟(SDK_V15鎵╁睍鍛戒护2006-05-15)
    public static final int NET_DVR_SET_COMPRESSCFG_EX = 205;	//璁剧疆鍘嬬缉鍙傛暟(SDK_V15鎵╁睍鍛戒护2006-05-15)
    public static final int NET_DVR_GET_NETAPPCFG = 222;	//鑾峰彇缃戠粶搴旂敤鍙傛暟 NTP/DDNS/EMAIL
    public static final int NET_DVR_SET_NETAPPCFG = 223;	//璁剧疆缃戠粶搴旂敤鍙傛暟 NTP/DDNS/EMAIL
    public static final int NET_DVR_GET_NTPCFG = 224;	//鑾峰彇缃戠粶搴旂敤鍙傛暟 NTP
    public static final int NET_DVR_SET_NTPCFG = 225;	//璁剧疆缃戠粶搴旂敤鍙傛暟 NTP
    public static final int NET_DVR_GET_DDNSCFG = 226;	//鑾峰彇缃戠粶搴旂敤鍙傛暟 DDNS
    public static final int NET_DVR_SET_DDNSCFG = 227;		//璁剧疆缃戠粶搴旂敤鍙傛暟 DDNS
//瀵瑰簲NET_DVR_EMAILPARA
    public static final int NET_DVR_GET_EMAILCFG = 228;	//鑾峰彇缃戠粶搴旂敤鍙傛暟 EMAIL
    public static final int NET_DVR_SET_EMAILCFG = 229;	//璁剧疆缃戠粶搴旂敤鍙傛暟 EMAIL
    public static final int NET_DVR_GET_NFSCFG = 230;	/* NFS disk config */
    public static final int NET_DVR_SET_NFSCFG = 231;	/* NFS disk config */
    public static final int NET_DVR_GET_SHOWSTRING_EX = 238;	//鑾峰彇鍙犲姞瀛楃鍙傛暟鎵╁睍(鏀寔8鏉″瓧绗�)
    public static final int NET_DVR_SET_SHOWSTRING_EX = 239;	//璁剧疆鍙犲姞瀛楃鍙傛暟鎵╁睍(鏀寔8鏉″瓧绗�)
    public static final int NET_DVR_GET_NETCFG_OTHER = 244;	//鑾峰彇缃戠粶鍙傛暟
    public static final int NET_DVR_SET_NETCFG_OTHER = 245;	//璁剧疆缃戠粶鍙傛暟
//瀵瑰簲NET_DVR_EMAILCFG缁撴瀯
    public static final int NET_DVR_GET_EMAILPARACFG = 250;	//Get EMAIL parameters
    public static final int NET_DVR_SET_EMAILPARACFG = 251;	//Setup EMAIL parameters
    public static final int NET_DVR_GET_DDNSCFG_EX = 274;//鑾峰彇鎵╁睍DDNS鍙傛暟
    public static final int NET_DVR_SET_DDNSCFG_EX = 275;//璁剧疆鎵╁睍DDNS鍙傛暟
    public static final int NET_DVR_SET_PTZPOS = 292;	//浜戝彴璁剧疆PTZ浣嶇疆
    public static final int NET_DVR_GET_PTZPOS = 293;		//浜戝彴鑾峰彇PTZ浣嶇疆
    public static final int NET_DVR_GET_PTZSCOPE = 294;//浜戝彴鑾峰彇PTZ鑼冨洿
    /***************************DS9000鏂板鍛戒护(_V30) begin *****************************/
//缃戠粶(NET_DVR_NETCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_NETCFG_V30 = 1000;	//鑾峰彇缃戠粶鍙傛暟
    public static final int NET_DVR_SET_NETCFG_V30 = 1001;	//璁剧疆缃戠粶鍙傛暟
//鍥捐薄(NET_DVR_PICCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_PICCFG_V30 = 1002;	//鑾峰彇鍥捐薄鍙傛暟
    public static final int NET_DVR_SET_PICCFG_V30 = 1003;	//璁剧疆鍥捐薄鍙傛暟
//褰曞儚鏃堕棿(NET_DVR_RECORD_V30缁撴瀯)
    public static final int NET_DVR_GET_RECORDCFG_V30 = 1004;	//鑾峰彇褰曞儚鍙傛暟
    public static final int NET_DVR_SET_RECORDCFG_V30 = 1005;	//璁剧疆褰曞儚鍙傛暟
//鐢ㄦ埛(NET_DVR_USER_V30缁撴瀯)
    public static final int NET_DVR_GET_USERCFG_V30 = 1006;	//鑾峰彇鐢ㄦ埛鍙傛暟
    public static final int NET_DVR_SET_USERCFG_V30 = 1007;	//璁剧疆鐢ㄦ埛鍙傛暟
//9000DDNS鍙傛暟閰嶇疆(NET_DVR_DDNSPARA_V30缁撴瀯)
    public static final int NET_DVR_GET_DDNSCFG_V30 = 1010;	//鑾峰彇DDNS(9000鎵╁睍)
    public static final int NET_DVR_SET_DDNSCFG_V30 = 1011;	//璁剧疆DDNS(9000鎵╁睍)
//EMAIL鍔熻兘(NET_DVR_EMAILCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_EMAILCFG_V30 = 1012;//鑾峰彇EMAIL鍙傛暟
    public static final int NET_DVR_SET_EMAILCFG_V30 = 1013;//璁剧疆EMAIL鍙傛暟
//宸¤埅鍙傛暟 (NET_DVR_CRUISE_PARA缁撴瀯)
    public static final int NET_DVR_GET_CRUISE = 1020;
    public static final int NET_DVR_SET_CRUISE = 1021;
//鎶ヨ杈撳叆缁撴瀯鍙傛暟 (NET_DVR_ALARMINCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_ALARMINCFG_V30 = 1024;
    public static final int NET_DVR_SET_ALARMINCFG_V30 = 1025;
//鎶ヨ杈撳嚭缁撴瀯鍙傛暟 (NET_DVR_ALARMOUTCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_ALARMOUTCFG_V30 = 1026;
    public static final int NET_DVR_SET_ALARMOUTCFG_V30 = 1027;
//瑙嗛杈撳嚭缁撴瀯鍙傛暟 (NET_DVR_VIDEOOUT_V30缁撴瀯)
    public static final int NET_DVR_GET_VIDEOOUTCFG_V30 = 1028;
    public static final int NET_DVR_SET_VIDEOOUTCFG_V30 = 1029;
//鍙犲姞瀛楃缁撴瀯鍙傛暟 (NET_DVR_SHOWSTRING_V30缁撴瀯)
    public static final int NET_DVR_GET_SHOWSTRING_V30 = 1030;
    public static final int NET_DVR_SET_SHOWSTRING_V30 = 1031;
//寮傚父缁撴瀯鍙傛暟 (NET_DVR_EXCEPTION_V30缁撴瀯)
    public static final int NET_DVR_GET_EXCEPTIONCFG_V30 = 1034;
    public static final int NET_DVR_SET_EXCEPTIONCFG_V30 = 1035;
//涓插彛232缁撴瀯鍙傛暟 (NET_DVR_RS232CFG_V30缁撴瀯)
    public static final int NET_DVR_GET_RS232CFG_V30 = 1036;
    public static final int NET_DVR_SET_RS232CFG_V30 = 1037;
//鍘嬬缉鍙傛暟 (NET_DVR_COMPRESSIONCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_COMPRESSCFG_V30 = 1040;
    public static final int NET_DVR_SET_COMPRESSCFG_V30 = 1041;
//鑾峰彇485瑙ｇ爜鍣ㄥ弬鏁� (NET_DVR_DECODERCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_DECODERCFG_V30 = 1042;	//鑾峰彇瑙ｇ爜鍣ㄥ弬鏁�
    public static final int NET_DVR_SET_DECODERCFG_V30 = 1043;	//璁剧疆瑙ｇ爜鍣ㄥ弬鏁�
//鑾峰彇棰勮鍙傛暟 (NET_DVR_PREVIEWCFG_V30缁撴瀯)
    public static final int NET_DVR_GET_PREVIEWCFG_V30 = 1044;	//鑾峰彇棰勮鍙傛暟
    public static final int NET_DVR_SET_PREVIEWCFG_V30 = 1045;	//璁剧疆棰勮鍙傛暟
//杈呭姪棰勮鍙傛暟 (NET_DVR_PREVIEWCFG_AUX_V30缁撴瀯)
    public static final int NET_DVR_GET_PREVIEWCFG_AUX_V30 = 1046;	//鑾峰彇杈呭姪棰勮鍙傛暟
    public static final int NET_DVR_SET_PREVIEWCFG_AUX_V30 = 1047;	//璁剧疆杈呭姪棰勮鍙傛暟
//IP鎺ュ叆閰嶇疆鍙傛暟 锛圢ET_DVR_IPPARACFG缁撴瀯锛�
    public static final int NET_DVR_GET_IPPARACFG = 1048;    //鑾峰彇IP鎺ュ叆閰嶇疆淇℃伅
    public static final int NET_DVR_SET_IPPARACFG = 1049;    //璁剧疆IP鎺ュ叆閰嶇疆淇℃伅
//IP鎶ヨ杈撳叆鎺ュ叆閰嶇疆鍙傛暟 锛圢ET_DVR_IPALARMINCFG缁撴瀯锛�
    public static final int NET_DVR_GET_IPALARMINCFG = 1050;    //鑾峰彇IP鎶ヨ杈撳叆鎺ュ叆閰嶇疆淇℃伅
    public static final int NET_DVR_SET_IPALARMINCFG = 1051;   //璁剧疆IP鎶ヨ杈撳叆鎺ュ叆閰嶇疆淇℃伅
//IP鎶ヨ杈撳嚭鎺ュ叆閰嶇疆鍙傛暟 锛圢ET_DVR_IPALARMOUTCFG缁撴瀯锛�
    public static final int NET_DVR_GET_IPALARMOUTCFG = 1052;   //鑾峰彇IP鎶ヨ杈撳嚭鎺ュ叆閰嶇疆淇℃伅
    public static final int NET_DVR_SET_IPALARMOUTCFG = 1053;  //璁剧疆IP鎶ヨ杈撳嚭鎺ュ叆閰嶇疆淇℃伅
//纭洏绠＄悊鐨勫弬鏁拌幏鍙� (NET_DVR_HDCFG缁撴瀯)
    public static final int NET_DVR_GET_HDCFG = 1054;    //鑾峰彇纭洏绠＄悊閰嶇疆鍙傛暟
    public static final int NET_DVR_SET_HDCFG = 1055;    //璁剧疆纭洏绠＄悊閰嶇疆鍙傛暟
//鐩樼粍绠＄悊鐨勫弬鏁拌幏鍙� (NET_DVR_HDGROUP_CFG缁撴瀯)
    public static final int NET_DVR_GET_HDGROUP_CFG = 1056;    //鑾峰彇鐩樼粍绠＄悊閰嶇疆鍙傛暟
    public static final int NET_DVR_SET_HDGROUP_CFG = 1057;    //璁剧疆鐩樼粍绠＄悊閰嶇疆鍙傛暟
//璁惧缂栫爜绫诲瀷閰嶇疆(NET_DVR_COMPRESSION_AUDIO缁撴瀯)
    public static final int NET_DVR_GET_COMPRESSCFG_AUD = 1058;     //鑾峰彇璁惧璇煶瀵硅缂栫爜鍙傛暟
    public static final int NET_DVR_SET_COMPRESSCFG_AUD = 1059;     //璁剧疆璁惧璇煶瀵硅缂栫爜鍙傛暟
    /***************************DS9000鏂板鍛戒护(_V30) end *****************************/
    /*************************鍙傛暟閰嶇疆鍛戒护 end*******************************/
    /*******************鏌ユ壘鏂囦欢鍜屾棩蹇楀嚱鏁拌繑鍥炲��*************************/
    public static final int NET_DVR_FILE_SUCCESS = 1000;	//鑾峰緱鏂囦欢淇℃伅
    public static final int NET_DVR_FILE_NOFIND = 1001;	//娌℃湁鏂囦欢
    public static final int NET_DVR_ISFINDING = 1002;//姝ｅ湪鏌ユ壘鏂囦欢
    public static final int NET_DVR_NOMOREFILE = 1003;//鏌ユ壘鏂囦欢鏃舵病鏈夋洿澶氱殑鏂囦欢
    public static final int NET_DVR_FILE_EXCEPTION = 1004;//鏌ユ壘鏂囦欢鏃跺紓甯�
    /*********************鍥炶皟鍑芥暟绫诲瀷 begin************************/
    public static final int COMM_ALARM = 0x1100;	//8000鎶ヨ淇℃伅涓诲姩涓婁紶
    public static final int COMM_TRADEINFO = 0x1500;  //ATMDVR涓诲姩涓婁紶浜ゆ槗淇℃伅
    public static final int COMM_ALARM_V30 = 0x4000;//9000鎶ヨ淇℃伅涓诲姩涓婁紶
    public static final int COMM_ALARM_RULE = 0x1102;//琛屼负鍒嗘瀽淇℃伅涓婁紶
    public static final int COMM_ALARM_PDC = 0x1103;//瀹㈡祦閲忕粺璁℃姤璀︿笂浼�
    public static final int COMM_UPLOAD_PLATE_RESULT = 0x2800;//浜ら�氭姄鎷嶇粨鏋滀笂浼�
    public static final int COMM_ITS_PLATE_RESULT = 0x3050;//浜ら�氭姄鎷嶇殑缁堢鍥剧墖涓婁紶
    public static final int COMM_IPCCFG = 0x4001;//9000璁惧IPC鎺ュ叆閰嶇疆鏀瑰彉鎶ヨ淇℃伅涓诲姩涓婁紶
    public static final int COMM_ITS_PARK_VEHICLE = 0x3056;//鍋滆溅鍦烘暟鎹笂浼�
    public static final int COMM_ALARM_TFS = 0x1113; //浜ら�氬彇璇佹姤璀︿俊鎭�
    public static final int COMM_ALARM_TPS_V41 = 0x1114; //浜ら�氫簨浠舵姤璀︿俊鎭墿灞�
    public static final int COMM_ALARM_AID_V41 = 0x1115; //浜ら�氫簨浠舵姤璀︿俊鎭墿灞�
    public static final int COMM_UPLOAD_FACESNAP_RESULT = 0x1112;  //浜鸿劯璇嗗埆缁撴灉涓婁紶
    public static final int COMM_SNAP_MATCH_ALARM = 0x2902;  //榛戝悕鍗曟瘮瀵圭粨鏋滀笂浼�
    public static final int COMM_ALARM_ACS = 0x5002; //闂ㄧ涓绘満鎶ヨ淇℃伅
    public static final int COMM_ID_INFO_ALARM = 0x5200; //闂ㄧ韬唤璇佸埛鍗′俊鎭�
    public static final int COMM_VCA_ALARM = 0x4993; //鏅鸿兘妫�娴嬮�氱敤鎶ヨ
    /*************鎿嶄綔寮傚父绫诲瀷(娑堟伅鏂瑰紡, 鍥炶皟鏂瑰紡(淇濈暀))****************/
    public static final int EXCEPTION_EXCHANGE = 0x8000;//鐢ㄦ埛浜や簰鏃跺紓甯�
    public static final int EXCEPTION_AUDIOEXCHANGE = 0x8001;//璇煶瀵硅寮傚父
    public static final int EXCEPTION_ALARM = 0x8002;//鎶ヨ寮傚父
    public static final int EXCEPTION_PREVIEW = 0x8003;//缃戠粶棰勮寮傚父
    public static final int EXCEPTION_SERIAL = 0x8004;//閫忔槑閫氶亾寮傚父
    public static final int EXCEPTION_RECONNECT = 0x8005;	//棰勮鏃堕噸杩�
    public static final int EXCEPTION_ALARMRECONNECT = 0x8006;//鎶ヨ鏃堕噸杩�
    public static final int EXCEPTION_SERIALRECONNECT = 0x8007;//閫忔槑閫氶亾閲嶈繛
    public static final int EXCEPTION_PLAYBACK = 0x8010;//鍥炴斁寮傚父
    public static final int EXCEPTION_DISKFMT = 0x8011;//纭洏鏍煎紡鍖�
    /********************棰勮鍥炶皟鍑芥暟*********************/
    public static final int NET_DVR_SYSHEAD = 1;//绯荤粺澶存暟鎹�
    public static final int NET_DVR_STREAMDATA = 2;//瑙嗛娴佹暟鎹紙鍖呮嫭澶嶅悎娴佸拰闊宠棰戝垎寮�鐨勮棰戞祦鏁版嵁锛�
    public static final int NET_DVR_AUDIOSTREAMDATA = 3;//闊抽娴佹暟鎹�
    public static final int NET_DVR_STD_VIDEODATA = 4;//鏍囧噯瑙嗛娴佹暟鎹�
    public static final int NET_DVR_STD_AUDIODATA = 5;//鏍囧噯闊抽娴佹暟鎹�
//鍥炶皟棰勮涓殑鐘舵�佸拰娑堟伅
    public static final int NET_DVR_REALPLAYEXCEPTION = 111;//棰勮寮傚父
    public static final int NET_DVR_REALPLAYNETCLOSE = 112;//棰勮鏃惰繛鎺ユ柇寮�
    public static final int NET_DVR_REALPLAY5SNODATA = 113;//棰勮5s娌℃湁鏀跺埌鏁版嵁
    public static final int NET_DVR_REALPLAYRECONNECT = 114;//棰勮閲嶈繛
    /********************鍥炴斁鍥炶皟鍑芥暟*********************/
    public static final int NET_DVR_PLAYBACKOVER = 101;//鍥炴斁鏁版嵁鎾斁瀹屾瘯
    public static final int NET_DVR_PLAYBACKEXCEPTION = 102;//鍥炴斁寮傚父
    public static final int NET_DVR_PLAYBACKNETCLOSE = 103;//鍥炴斁鏃跺�欒繛鎺ユ柇寮�
    public static final int NET_DVR_PLAYBACK5SNODATA = 104;	//鍥炴斁5s娌℃湁鏀跺埌鏁版嵁
    /*********************鍥炶皟鍑芥暟绫诲瀷 end************************/
//璁惧鍨嬪彿(DVR绫诲瀷)
/* 璁惧绫诲瀷 */
    public static final int DVR = 1;			/*瀵瑰皻鏈畾涔夌殑dvr绫诲瀷杩斿洖NETRET_DVR*/
    public static final int ATMDVR = 2;		/*atm dvr*/
    public static final int DVS = 3;			/*DVS*/
    public static final int DEC = 4;			/* 6001D */
    public static final int ENC_DEC = 5;			/* 6001F */
    public static final int DVR_HC = 6;			/*8000HC*/
    public static final int DVR_HT = 7;			/*8000HT*/
    public static final int DVR_HF = 8;			/*8000HF*/
    public static final int DVR_HS = 9;			/* 8000HS DVR(no audio) */
    public static final int DVR_HTS = 10;         /* 8016HTS DVR(no audio) */
    public static final int DVR_HB = 11;         /* HB DVR(SATA HD) */
    public static final int DVR_HCS = 12;         /* 8000HCS DVR */
    public static final int DVS_A = 13;         /* 甯TA纭洏鐨凞VS */
    public static final int DVR_HC_S = 14;         /* 8000HC-S */
    public static final int DVR_HT_S = 15;         /* 8000HT-S */
    public static final int DVR_HF_S = 16;         /* 8000HF-S */
    public static final int DVR_HS_S = 17;         /* 8000HS-S */
    public static final int ATMDVR_S = 18;         /* ATM-S */
    public static final int LOWCOST_DVR = 19;			/*7000H绯诲垪*/
    public static final int DEC_MAT = 20;         /*澶氳矾瑙ｇ爜鍣�*/
    public static final int DVR_MOBILE = 21;			/* mobile DVR */
    public static final int DVR_HD_S = 22;        /* 8000HD-S */
    public static final int DVR_HD_SL = 23;			/* 8000HD-SL */
    public static final int DVR_HC_SL = 24;			/* 8000HC-SL */
    public static final int DVR_HS_ST = 25;			/* 8000HS_ST */
    public static final int DVS_HW = 26;         /* 6000HW */
    public static final int IPCAM = 30;			/*IP 鎽勫儚鏈�*/
    public static final int MEGA_IPCAM = 31;			/*X52MF绯诲垪,752MF,852MF*/
    public static final int IPCAM_X62MF = 32;			/*X62MF绯诲垪鍙帴鍏�9000璁惧,762MF,862MF*/
    public static final int IPDOME = 40;			/*IP鏍囨竻蹇悆*/
    public static final int MEGA_IPDOME = 41;     /*IP楂樻竻蹇悆*/
    public static final int IPMOD = 50;			/*IP 妯″潡*/
    public static final int DS71XX_H = 71;			/* DS71XXH_S */
    public static final int DS72XX_H_S = 72;			/* DS72XXH_S */
    public static final int DS73XX_H_S = 73;			/* DS73XXH_S */
    public static final int DS81XX_HS_S = 81;			/* DS81XX_HS_S */
    public static final int DS81XX_HL_S = 82;			/* DS81XX_HL_S */
    public static final int DS81XX_HC_S = 83;			/* DS81XX_HC_S */
    public static final int DS81XX_HD_S = 84;			/* DS81XX_HD_S */
    public static final int DS81XX_HE_S = 85;			/* DS81XX_HE_S */
    public static final int DS81XX_HF_S = 86;			/* DS81XX_HF_S */
    public static final int DS81XX_AH_S = 87;			/* DS81XX_AH_S */
    public static final int DS81XX_AHF_S = 88;			/* DS81XX_AHF_S */
    public static final int DS90XX_HF_S = 90;       /*DS90XX_HF_S*/
    public static final int DS91XX_HF_S = 91;             /*DS91XX_HF_S*/
    public static final int DS91XX_HD_S = 92;            /*91XXHD-S(MD)*/

    /* 鎿嶄綔 */
//涓荤被鍨�
    public static final int MAJOR_OPERATION = 0x3;
//娆＄被鍨�
    public static final int MINOR_START_DVR = 0x41; /* 寮�鏈� */
    public static final int MINOR_STOP_DVR = 0x42;/* 鍏虫満 */
    public static final int MINOR_STOP_ABNORMAL = 0x43;/* 寮傚父鍏虫満 */
    public static final int MINOR_REBOOT_DVR = 0x44;   /*鏈湴閲嶅惎璁惧*/
    public static final int MINOR_LOCAL_LOGIN = 0x50; /* 鏈湴鐧婚檰 */
    public static final int MINOR_LOCAL_LOGOUT = 0x51; /* 鏈湴娉ㄩ攢鐧婚檰 */
    public static final int MINOR_LOCAL_CFG_PARM = 0x52; /* 鏈湴閰嶇疆鍙傛暟 */
    public static final int MINOR_LOCAL_PLAYBYFILE = 0x53; /* 鏈湴鎸夋枃浠跺洖鏀炬垨涓嬭浇 */
    public static final int MINOR_LOCAL_PLAYBYTIME = 0x54; /* 鏈湴鎸夋椂闂村洖鏀炬垨涓嬭浇*/
    public static final int MINOR_LOCAL_START_REC = 0x55; /* 鏈湴寮�濮嬪綍鍍� */
    public static final int MINOR_LOCAL_STOP_REC = 0x56; /* 鏈湴鍋滄褰曞儚 */
    public static final int MINOR_LOCAL_PTZCTRL = 0x57; /* 鏈湴浜戝彴鎺у埗 */
    public static final int MINOR_LOCAL_PREVIEW = 0x58;/* 鏈湴棰勮 (淇濈暀涓嶄娇鐢�)*/
    public static final int MINOR_LOCAL_MODIFY_TIME = 0x59;/* 鏈湴淇敼鏃堕棿(淇濈暀涓嶄娇鐢�) */
    public static final int MINOR_LOCAL_UPGRADE = 0x5a;/* 鏈湴鍗囩骇 */
    public static final int MINOR_LOCAL_RECFILE_OUTPUT = 0x5b;   /* 鏈湴澶囦唤褰曡薄鏂囦欢 */
    public static final int MINOR_LOCAL_FORMAT_HDD = 0x5c;  /* 鏈湴鍒濆鍖栫‖鐩� */
    public static final int MINOR_LOCAL_CFGFILE_OUTPUT = 0x5d;  /* 瀵煎嚭鏈湴閰嶇疆鏂囦欢 */
    public static final int MINOR_LOCAL_CFGFILE_INPUT = 0x5e;  /* 瀵煎叆鏈湴閰嶇疆鏂囦欢 */
    public static final int MINOR_LOCAL_COPYFILE = 0x5f;  /* 鏈湴澶囦唤鏂囦欢 */
    public static final int MINOR_LOCAL_LOCKFILE = 0x60;  /* 鏈湴閿佸畾褰曞儚鏂囦欢 */
    public static final int MINOR_LOCAL_UNLOCKFILE = 0x61;   /* 鏈湴瑙ｉ攣褰曞儚鏂囦欢 */
    public static final int MINOR_LOCAL_DVR_ALARM = 0x62;  /* 鏈湴鎵嬪姩娓呴櫎鍜岃Е鍙戞姤璀�*/
    public static final int MINOR_IPC_ADD = 0x63;  /* 鏈湴娣诲姞IPC */
    public static final int MINOR_IPC_DEL = 0x64;  /* 鏈湴鍒犻櫎IPC */
    public static final int MINOR_IPC_SET = 0x65;  /* 鏈湴璁剧疆IPC */
    public static final int MINOR_LOCAL_START_BACKUP = 0x66;	/* 鏈湴寮�濮嬪浠� */
    public static final int MINOR_LOCAL_STOP_BACKUP = 0x67;/* 鏈湴鍋滄澶囦唤*/
    public static final int MINOR_LOCAL_COPYFILE_START_TIME = 0x68;/* 鏈湴澶囦唤寮�濮嬫椂闂�*/
    public static final int MINOR_LOCAL_COPYFILE_END_TIME = 0x69;	/* 鏈湴澶囦唤缁撴潫鏃堕棿*/
    public static final int MINOR_REMOTE_LOGIN = 0x70;/* 杩滅▼鐧诲綍 */
    public static final int MINOR_REMOTE_LOGOUT = 0x71;/* 杩滅▼娉ㄩ攢鐧婚檰 */
    public static final int MINOR_REMOTE_START_REC = 0x72;/* 杩滅▼寮�濮嬪綍鍍� */
    public static final int MINOR_REMOTE_STOP_REC = 0x73;/* 杩滅▼鍋滄褰曞儚 */
    public static final int MINOR_START_TRANS_CHAN = 0x74;/* 寮�濮嬮�忔槑浼犺緭 */
    public static final int MINOR_STOP_TRANS_CHAN = 0x75; /* 鍋滄閫忔槑浼犺緭 */
    public static final int MINOR_REMOTE_GET_PARM = 0x76;/* 杩滅▼鑾峰彇鍙傛暟 */
    public static final int MINOR_REMOTE_CFG_PARM = 0x77;/* 杩滅▼閰嶇疆鍙傛暟 */
    public static final int MINOR_REMOTE_GET_STATUS = 0x78;/* 杩滅▼鑾峰彇鐘舵�� */
    public static final int MINOR_REMOTE_ARM = 0x79; /* 杩滅▼甯冮槻 */
    public static final int MINOR_REMOTE_DISARM = 0x7a;/* 杩滅▼鎾ら槻 */
    public static final int MINOR_REMOTE_REBOOT = 0x7b; /* 杩滅▼閲嶅惎 */
    public static final int MINOR_START_VT = 0x7c;/* 寮�濮嬭闊冲璁� */
    public static final int MINOR_STOP_VT = 0x7d;/* 鍋滄璇煶瀵硅 */
    public static final int MINOR_REMOTE_UPGRADE = 0x7e; /* 杩滅▼鍗囩骇 */
    public static final int MINOR_REMOTE_PLAYBYFILE = 0x7f; /* 杩滅▼鎸夋枃浠跺洖鏀� */
    public static final int MINOR_REMOTE_PLAYBYTIME = 0x80; /* 杩滅▼鎸夋椂闂村洖鏀� */
    public static final int MINOR_REMOTE_PTZCTRL = 0x81; /* 杩滅▼浜戝彴鎺у埗 */
    public static final int MINOR_REMOTE_FORMAT_HDD = 0x82;  /* 杩滅▼鏍煎紡鍖栫‖鐩� */
    public static final int MINOR_REMOTE_STOP = 0x83;  /* 杩滅▼鍏虫満 */
    public static final int MINOR_REMOTE_LOCKFILE = 0x84;/* 杩滅▼閿佸畾鏂囦欢 */
    public static final int MINOR_REMOTE_UNLOCKFILE = 0x85;/* 杩滅▼瑙ｉ攣鏂囦欢 */
    public static final int MINOR_REMOTE_CFGFILE_OUTPUT = 0x86;   /* 杩滅▼瀵煎嚭閰嶇疆鏂囦欢 */
    public static final int MINOR_REMOTE_CFGFILE_INTPUT = 0x87;   /* 杩滅▼瀵煎叆閰嶇疆鏂囦欢 */
    public static final int MINOR_REMOTE_RECFILE_OUTPUT = 0x88;   /* 杩滅▼瀵煎嚭褰曡薄鏂囦欢 */
    public static final int MINOR_REMOTE_DVR_ALARM = 0x89;    /* 杩滅▼鎵嬪姩娓呴櫎鍜岃Е鍙戞姤璀�*/
    public static final int MINOR_REMOTE_IPC_ADD = 0x8a;  /* 杩滅▼娣诲姞IPC */
    public static final int MINOR_REMOTE_IPC_DEL = 0x8b;/* 杩滅▼鍒犻櫎IPC */
    public static final int MINOR_REMOTE_IPC_SET = 0x8c; /* 杩滅▼璁剧疆IPC */
    public static final int MINOR_REBOOT_VCA_LIB = 0x8d;		/*閲嶅惎鏅鸿兘搴�*/

    /*鏃ュ織闄勫姞淇℃伅*/
//涓荤被鍨�
    public static final int MAJOR_INFORMATION = 0x4;   /*闄勫姞淇℃伅*/
//娆＄被鍨�
    public static final int MINOR_HDD_INFO = 0xa1;/*纭洏淇℃伅*/
    public static final int MINOR_SMART_INFO = 0xa2;   /*SMART淇℃伅*/
    public static final int MINOR_REC_START = 0xa3;   /*寮�濮嬪綍鍍�*/
    public static final int MINOR_REC_STOP = 0xa4;/*鍋滄褰曞儚*/
    public static final int MINOR_REC_OVERDUE = 0xa5;/*杩囨湡褰曞儚鍒犻櫎*/
    public static final int MINOR_LINK_START = 0xa6; // ivms锛屽璺В鐮佸櫒绛夎繛鎺ュ墠绔澶�
    public static final int MINOR_LINK_STOP = 0xa7;// ivms锛屽璺В鐮佸櫒绛夋柇寮�鍓嶇璁惧銆�
//褰撴棩蹇楃殑涓荤被鍨嬩负MAJOR_OPERATION=03锛屾绫诲瀷涓篗INOR_LOCAL_CFG_PARM=0x52鎴栬�匨INOR_REMOTE_GET_PARM=0x76鎴栬�匨INOR_REMOTE_CFG_PARM=0x77鏃讹紝dwParaType:鍙傛暟绫诲瀷鏈夋晥锛屽叾鍚箟濡備笅锛�
    public static final int PARA_VIDEOOUT = 0x1;
    public static final int PARA_IMAGE = 0x2;
    public static final int PARA_ENCODE = 0x4;
    public static final int PARA_NETWORK = 0x8;
    public static final int PARA_ALARM = 0x10;
    public static final int PARA_EXCEPTION = 0x20;
    public static final int PARA_DECODER = 0x40; /*瑙ｇ爜鍣�*/
    public static final int PARA_RS232 = 0x80;
    public static final int PARA_PREVIEW = 0x100;
    public static final int PARA_SECURITY = 0x200;
    public static final int PARA_DATETIME = 0x400;
    public static final int PARA_FRAMETYPE = 0x800;  /*甯ф牸寮�*/
    public static final int PARA_VCA_RULE = 0x1000;    //琛屼负瑙勫垯
//SDK_V222
//鏅鸿兘璁惧绫诲瀷
    public static final int DS6001_HF_B = 60;//琛屼负鍒嗘瀽锛欴S6001-HF/B
    public static final int DS6001_HF_P = 61;//杞︾墝璇嗗埆锛欴S6001-HF/P
    public static final int DS6002_HF_B = 62;//鍙屾満璺熻釜锛欴S6002-HF/B
    public static final int DS6101_HF_B = 63;//琛屼负鍒嗘瀽锛欴S6101-HF/B
    public static final int IVMS_2000 = 64;//鏅鸿兘鍒嗘瀽浠�
    public static final int DS9000_IVS = 65;//9000绯诲垪鏅鸿兘DVR
    public static final int DS8004_AHL_A = 66;//鏅鸿兘ATM, DS8004AHL-S/A
    public static final int DS6101_HF_P = 67;//杞︾墝璇嗗埆锛欴S6101-HF/P
//鑳藉姏鑾峰彇鍛戒护
    public static final int VCA_DEV_ABILITY = 0x100;//璁惧鏅鸿兘鍒嗘瀽鐨勬�昏兘鍔�
    public static final int VCA_CHAN_ABILITY = 0x110;//琛屼负鍒嗘瀽鑳藉姏
//鑾峰彇/璁剧疆澶ф帴鍙ｅ弬鏁伴厤缃懡浠�
//杞︾墝璇嗗埆锛圢ET_VCA_PLATE_CFG锛�;
    public static final int NET_DVR_SET_PLATECFG = 150 ;//璁剧疆杞︾墝璇嗗埆鍙傛暟

    public static final int NET_DVR_GET_PLATECFG = 151;	//鑾峰彇杞︾墝璇嗗埆鍙傛暟
//琛屼负瀵瑰簲锛圢ET_VCA_RULECFG锛�
    public static final int NET_DVR_SET_RULECFG = 152;	//璁剧疆琛屼负鍒嗘瀽瑙勫垯
    public static final int NET_DVR_GET_RULECFG = 153;//鑾峰彇琛屼负鍒嗘瀽瑙勫垯,
//鍙屾憚鍍忔満鏍囧畾鍙傛暟锛圢ET_DVR_LF_CFG锛�
    public static final int NET_DVR_SET_LF_CFG = 160;//璁剧疆鍙屾憚鍍忔満鐨勯厤缃弬鏁�
    public static final int NET_DVR_GET_LF_CFG = 161;//鑾峰彇鍙屾憚鍍忔満鐨勯厤缃弬鏁�
//鏅鸿兘鍒嗘瀽浠彇娴侀厤缃粨鏋�
    public static final int NET_DVR_SET_IVMS_STREAMCFG = 162;	//璁剧疆鏅鸿兘鍒嗘瀽浠彇娴佸弬鏁�
    public static final int NET_DVR_GET_IVMS_STREAMCFG = 163;	//鑾峰彇鏅鸿兘鍒嗘瀽浠彇娴佸弬鏁�
//鏅鸿兘鎺у埗鍙傛暟缁撴瀯
    public static final int NET_DVR_SET_VCA_CTRLCFG = 164; //璁剧疆鏅鸿兘鎺у埗鍙傛暟
    public static final int NET_DVR_GET_VCA_CTRLCFG = 165;	 //鑾峰彇鏅鸿兘鎺у埗鍙傛暟
//灞忚斀鍖哄煙NET_VCA_MASK_REGION_LIST
    public static final int NET_DVR_SET_VCA_MASK_REGION = 166;	 //璁剧疆灞忚斀鍖哄煙鍙傛暟
    public static final int NET_DVR_GET_VCA_MASK_REGION = 167;	 //鑾峰彇灞忚斀鍖哄煙鍙傛暟
//ATM杩涘叆鍖哄煙 NET_VCA_ENTER_REGION
    public static final int NET_DVR_SET_VCA_ENTER_REGION = 168; //璁剧疆杩涘叆鍖哄煙鍙傛暟
    public static final int NET_DVR_GET_VCA_ENTER_REGION = 169;	 //鑾峰彇杩涘叆鍖哄煙鍙傛暟
//鏍囧畾绾块厤缃甆ET_VCA_LINE_SEGMENT_LIST
    public static final int NET_DVR_SET_VCA_LINE_SEGMENT = 170;	 //璁剧疆鏍囧畾绾�
    public static final int NET_DVR_GET_VCA_LINE_SEGMENT = 171;	 //鑾峰彇鏍囧畾绾�
// ivms灞忚斀鍖哄煙NET_IVMS_MASK_REGION_LIST
    public static final int NET_DVR_SET_IVMS_MASK_REGION = 172;	 //璁剧疆IVMS灞忚斀鍖哄煙鍙傛暟
    public static final int NET_DVR_GET_IVMS_MASK_REGION = 173;	 //鑾峰彇IVMS灞忚斀鍖哄煙鍙傛暟
// ivms杩涘叆妫�娴嬪尯鍩烴ET_IVMS_ENTER_REGION
    public static final int NET_DVR_SET_IVMS_ENTER_REGION = 174; //璁剧疆IVMS杩涘叆鍖哄煙鍙傛暟
    public static final int NET_DVR_GET_IVMS_ENTER_REGION = 175; //鑾峰彇IVMS杩涘叆鍖哄煙鍙傛暟
    public static final int NET_DVR_SET_IVMS_BEHAVIORCFG = 176;//璁剧疆鏅鸿兘鍒嗘瀽浠涓鸿鍒欏弬鏁�
    public static final int NET_DVR_GET_IVMS_BEHAVIORCFG = 177;	//鑾峰彇鏅鸿兘鍒嗘瀽浠涓鸿鍒欏弬鏁�
    public static final int NET_DVR_GET_TRAVERSE_PLANE_DETECTION = 3360; //鑾峰彇瓒婄晫渚︽祴閰嶇疆

    public static final int NET_DVR_GET_CARD_CFG = 2116; //鑾峰彇鍗″弬鏁�
    public static final int NET_DVR_SET_CARD_CFG = 2117; //璁剧疆鍗″弬鏁�
    /**********************璁惧绫诲瀷 end***********************/

/*************************************************
鍙傛暟閰嶇疆缁撴瀯銆佸弬鏁�(鍏朵腑_V30涓�9000鏂板)
**************************************************/

/////////////////////////////////////////////////////////////////////////
//鏍℃椂缁撴瀯鍙傛暟
    public static class NET_DVR_TIME extends Structure {//鏍℃椂缁撴瀯鍙傛暟
        public int dwYear;		//骞�
        public int dwMonth;		//鏈�
        public int dwDay;		//鏃�
        public int dwHour;		//鏃�
        public int dwMinute;		//鍒�
        public int dwSecond;		//绉�

      public String toString() {
            return "NET_DVR_TIME.dwYear: " + dwYear + "\n" + "NET_DVR_TIME.dwMonth: \n" + dwMonth + "\n" + "NET_DVR_TIME.dwDay: \n" + dwDay + "\n" + "NET_DVR_TIME.dwHour: \n" + dwHour + "\n" + "NET_DVR_TIME.dwMinute: \n" + dwMinute + "\n" + "NET_DVR_TIME.dwSecond: \n" + dwSecond;
        }

        //鐢ㄤ簬鍒楄〃涓樉绀�
        public String toStringTime()
        {
            return  String.format("%02d/%02d/%02d%02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        //瀛樺偍鏂囦欢鍚嶄娇鐢�
         public String toStringTitle()
        {
            return  String.format("Time%02d%02d%02d%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    public static class NET_DVR_SCHEDTIME extends Structure {
        public byte byStartHour;	//寮�濮嬫椂闂�
        public byte byStartMin;
        public byte byStopHour;	        //缁撴潫鏃堕棿
        public byte byStopMin;
    }

  public static class NET_DVR_HANDLEEXCEPTION_V30 extends Structure {
	public int dwHandleType;	/*澶勭悊鏂瑰紡,澶勭悊鏂瑰紡鐨�"鎴�"缁撴灉*//*0x00: 鏃犲搷搴�*//*0x01: 鐩戣鍣ㄤ笂璀﹀憡*//*0x02: 澹伴煶璀﹀憡*//*0x04: 涓婁紶涓績*/	/*0x08: 瑙﹀彂鎶ヨ杈撳嚭*//*0x20: 瑙﹀彂鎶撳浘*/  //(JPEG瀹氬埗)
	public byte[] byRelAlarmOut = new byte[MAX_ALARMOUT_V30];  //鎶ヨ瑙﹀彂鐨勮緭鍑洪�氶亾,鎶ヨ瑙﹀彂鐨勮緭鍑�,涓�1琛ㄧず瑙﹀彂璇ヨ緭鍑�
}

//鎶ヨ鍜屽紓甯稿鐞嗙粨鏋�(瀛愮粨鏋�)(澶氬浣跨敤)
  public static class NET_DVR_HANDLEEXCEPTION extends Structure {
	public int	dwHandleType;			/*澶勭悊鏂瑰紡,澶勭悊鏂瑰紡鐨�"鎴�"缁撴灉*//*0x00: 鏃犲搷搴�*//*0x01: 鐩戣鍣ㄤ笂璀﹀憡*//*0x02: 澹伴煶璀﹀憡*//*0x04: 涓婁紶涓績*/	/*0x08: 瑙﹀彂鎶ヨ杈撳嚭*//*0x20: 瑙﹀彂鎶撳浘*/  //(JPEG瀹氬埗)
	public byte[]  byRelAlarmOut = new byte[MAX_ALARMOUT];  //鎶ヨ瑙﹀彂鐨勮緭鍑洪�氶亾,鎶ヨ瑙﹀彂鐨勮緭鍑�,涓�1琛ㄧず瑙﹀彂璇ヨ緭鍑�
}

//DVR璁惧鍙傛暟
  public static class NET_DVR_DEVICECFG extends Structure {
        public int dwSize;
        public byte[] sDVRName = new byte[NAME_LEN];     //DVR鍚嶇О
        public int dwDVRID;				 //DVR ID,鐢ㄤ簬閬ユ帶鍣� //V1.4(0-99), V1.5(0-255)
        public int dwRecycleRecord;		         //鏄惁寰幆褰曞儚,0:涓嶆槸; 1:鏄�
        //浠ヤ笅涓嶅彲鏇存敼
        public byte[] sSerialNumber = new byte[SERIALNO_LEN];  //搴忓垪鍙�
        public int dwSoftwareVersion;			       //杞欢鐗堟湰鍙�,楂�16浣嶆槸涓荤増鏈�,浣�16浣嶆槸娆＄増鏈�
        public int dwSoftwareBuildDate;			        //杞欢鐢熸垚鏃ユ湡,0xYYYYMMDD
        public int dwDSPSoftwareVersion;		        //DSP杞欢鐗堟湰,楂�16浣嶆槸涓荤増鏈�,浣�16浣嶆槸娆＄増鏈�
        public int dwDSPSoftwareBuildDate;		        // DSP杞欢鐢熸垚鏃ユ湡,0xYYYYMMDD
        public int dwPanelVersion;				// 鍓嶉潰鏉跨増鏈�,楂�16浣嶆槸涓荤増鏈�,浣�16浣嶆槸娆＄増鏈�
        public int dwHardwareVersion;	        // 纭欢鐗堟湰,楂�16浣嶆槸涓荤増鏈�,浣�16浣嶆槸娆＄増鏈�
        public byte byAlarmInPortNum;		//DVR鎶ヨ杈撳叆涓暟
        public byte byAlarmOutPortNum;		//DVR鎶ヨ杈撳嚭涓暟
        public byte byRS232Num;			//DVR 232涓插彛涓暟
        public byte byRS485Num;			//DVR 485涓插彛涓暟
        public byte byNetworkPortNum;		//缃戠粶鍙ｄ釜鏁�
        public byte byDiskCtrlNum;			//DVR 纭洏鎺у埗鍣ㄤ釜鏁�
        public byte byDiskNum;				//DVR 纭洏涓暟
        public byte byDVRType;				//DVR绫诲瀷, 1:DVR 2:ATM DVR 3:DVS ......
        public byte byChanNum;				//DVR 閫氶亾涓暟
        public byte byStartChan;			//璧峰閫氶亾鍙�,渚嬪DVS-1,DVR - 1
        public byte byDecordChans;			//DVR 瑙ｇ爜璺暟
        public byte byVGANum;				//VGA鍙ｇ殑涓暟
        public byte byUSBNum;				//USB鍙ｇ殑涓暟
        public byte byAuxoutNum;			//杈呭彛鐨勪釜鏁�
        public byte byAudioNum;			        //璇煶鍙ｇ殑涓暟
        public byte byIPChanNum;			//鏈�澶ф暟瀛楅�氶亾鏁�
    }

public static class NET_DVR_IPADDR extends Structure {
        public byte[] sIpV4 = new byte[16];
        public byte[] byRes = new byte[128];

        public String toString() {
            return "NET_DVR_IPADDR.sIpV4: " + new String(sIpV4) + "\n" + "NET_DVR_IPADDR.byRes: " + new String(byRes) + "\n";
        }
    }


//缃戠粶鏁版嵁缁撴瀯(瀛愮粨鏋�)(9000鎵╁睍)
    public static class NET_DVR_ETHERNET_V30 extends Structure {
        public NET_DVR_IPADDR struDVRIP;
        public NET_DVR_IPADDR struDVRIPMask;
        public int dwNetInterface;
        public short wDVRPort;
        public short wMTU;
        public byte[] byMACAddr = new byte[6];

        public String toString() {
            return "NET_DVR_ETHERNET_V30.struDVRIP: \n" + struDVRIP + "\n" + "NET_DVR_ETHERNET_V30.struDVRIPMask: \n" + struDVRIPMask + "\n" + "NET_DVR_ETHERNET_V30.dwNetInterface: " + dwNetInterface + "\n" + "NET_DVR_ETHERNET_V30.wDVRPort: " + wDVRPort + "\n" + "NET_DVR_ETHERNET_V30.wMTU: " + wMTU + "\n" + "NET_DVR_ETHERNET_V30.byMACAddr: " + new String(byMACAddr) + "\n";
        }
    }

    public static class NET_DVR_ETHERNET extends Structure {//缃戠粶鏁版嵁缁撴瀯(瀛愮粨鏋�)
	public byte[]  sDVRIP = new byte[16];                    //DVR IP鍦板潃
	public byte[]  sDVRIPMask = new byte[16];                //DVR IP鍦板潃鎺╃爜
	public int dwNetInterface;               //缃戠粶鎺ュ彛 1-10MBase-T 2-10MBase-T鍏ㄥ弻宸� 3-100MBase-TX 4-100M鍏ㄥ弻宸� 5-10M/100M鑷�傚簲
	public short wDVRPort;		                //绔彛鍙�
	public byte[]  byMACAddr = new byte[MACADDR_LEN];		//鏈嶅姟鍣ㄧ殑鐗╃悊鍦板潃
}

    public static class NET_DVR_PPPOECFG extends Structure {//PPPoe
        public int dwPPPoE;
        public byte[] sPPPoEUser = new byte[32];
        public byte[] sPPPoEPassword = new byte[16];
        public NET_DVR_IPADDR struPPPoEIP;
    }

 public static class NET_DVR_NETCFG_V30 extends Structure {
        public int dwSize;
        public NET_DVR_ETHERNET_V30[] struEtherNet = new NET_DVR_ETHERNET_V30[2];
        public NET_DVR_IPADDR[] struRes1 = new NET_DVR_IPADDR[2];
        public NET_DVR_IPADDR struAlarmHostIpAddr;
        public short[] wRes2 = new short[2];
        public short wAlarmHostIpPort;
        public byte byUseDhcp;
        public byte byRes3;
        public NET_DVR_IPADDR struDnsServer1IpAddr;
        public NET_DVR_IPADDR struDnsServer2IpAddr;
        public byte[] byIpResolver = new byte[64];
        public short wIpResolverPort;
        public short wHttpPortNo;
        public NET_DVR_IPADDR struMulticastIpAddr;
        public NET_DVR_IPADDR struGatewayIpAddr;
        public NET_DVR_PPPOECFG struPPPoE;
        public byte[] byRes = new byte[64];

        public String toString() {
            return "NET_DVR_NETCFG_V30.dwSize: " + dwSize + "\n" + "NET_DVR_NETCFG_V30.struEtherNet[0]: \n" + struEtherNet[0] + "\n" + "NET_DVR_NETCFG_V30.struAlarmHostIpAddr: \n" + struAlarmHostIpAddr + "\n" + "NET_DVR_NETCFG_V30.wAlarmHostIpPort: " + wAlarmHostIpPort + "\n" + "NET_DVR_NETCFG_V30.wHttpPortNo: " + wHttpPortNo + "\n" + "NET_DVR_NETCFG_V30.struGatewayIpAddr: \n" + struGatewayIpAddr + "\n";
        }
    }


 public static class NET_DVR_NETCFG extends Structure {//缃戠粶閰嶇疆缁撴瀯
	public int dwSize;
	public NET_DVR_ETHERNET[] struEtherNet = new NET_DVR_ETHERNET[MAX_ETHERNET];		/* 浠ュお缃戝彛 */
	public byte[] sManageHostIP = new byte[16];		    //杩滅▼绠＄悊涓绘満鍦板潃
	public short wManageHostPort;		    //杩滅▼绠＄悊涓绘満绔彛鍙�
	public byte[] sIPServerIP = new byte[16];           //IPServer鏈嶅姟鍣ㄥ湴鍧�
	public byte[] sMultiCastIP = new byte[16];          //澶氭挱缁勫湴鍧�
	public byte[] sGatewayIP = new byte[16];       	    //缃戝叧鍦板潃
	public byte[] sNFSIP = new byte[16];			    //NFS涓绘満IP鍦板潃
	public byte[] sNFSDirectory = new byte[PATHNAME_LEN];//NFS鐩綍
	public int dwPPPOE;				    //0-涓嶅惎鐢�,1-鍚敤
	public byte[] sPPPoEUser = new byte[NAME_LEN];	    //PPPoE鐢ㄦ埛鍚�
	public byte[] sPPPoEPassword = new byte[PASSWD_LEN];// PPPoE瀵嗙爜
	public byte[] sPPPoEIP = new byte[16];			    //PPPoE IP鍦板潃(鍙)
}

//閫氶亾鍥捐薄缁撴瀯
    public static class NET_DVR_SCHEDTIMEWEEK extends Structure {
        public NET_DVR_SCHEDTIME[] struAlarmTime = new NET_DVR_SCHEDTIME[8];
    }

     public static class byte96 extends Structure {
        public byte[] byMotionScope = new byte[96];
    }
     
  public static class NET_DVR_MOTION_V30 extends Structure {//绉诲姩渚︽祴(瀛愮粨鏋�)(9000鎵╁睍)
        public byte96[] byMotionScope = new byte96[64];						/*渚︽祴鍖哄煙,0-96浣�,琛ㄧず64琛�,鍏辨湁96*64涓皬瀹忓潡,涓�1琛ㄧず鏄Щ鍔ㄤ睛娴嬪尯鍩�,0-琛ㄧず涓嶆槸*/
        public byte byMotionSensitive;							/*绉诲姩渚︽祴鐏垫晱搴�, 0 - 5,瓒婇珮瓒婄伒鏁�,oxff鍏抽棴*/
        public byte byEnableHandleMotion;						/* 鏄惁澶勭悊绉诲姩渚︽祴 0锛嶅惁 1锛嶆槸*/
        public byte byPrecision;							/* 绉诲姩渚︽祴绠楁硶鐨勮繘搴�: 0--16*16, 1--32*32, 2--64*64 ... */
        public byte reservedData;
        public NET_DVR_HANDLEEXCEPTION_V30 struMotionHandleType;			/* 澶勭悊鏂瑰紡 */
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS]; /*甯冮槻鏃堕棿*/
        public byte[] byRelRecordChan = new byte[64];					/* 鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾*/
    }

  public static class NET_DVR_MOTION extends Structure {//绉诲姩渚︽祴(瀛愮粨鏋�)
	byte[][] byMotionScope = new byte[18][22];	/*渚︽祴鍖哄煙,鍏辨湁22*18涓皬瀹忓潡,涓�1琛ㄧず鏀瑰畯鍧楁槸绉诲姩渚︽祴鍖哄煙,0-琛ㄧず涓嶆槸*/
	byte byMotionSensitive;		/*绉诲姩渚︽祴鐏垫晱搴�, 0 - 5,瓒婇珮瓒婄伒鏁�,0xff鍏抽棴*/
	byte byEnableHandleMotion;	/* 鏄惁澶勭悊绉诲姩渚︽祴 */
        byte[]  reservedData = new byte[2];
        NET_DVR_HANDLEEXCEPTION strMotionHandleType;	/* 澶勭悊鏂瑰紡 */
	byte[] byRelRecordChan = new byte[MAX_CHANNUM]; //鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾,涓�1琛ㄧず瑙﹀彂璇ラ�氶亾
}

  public static class NET_DVR_HIDEALARM_V30 extends Structure {//閬尅鎶ヨ
        public int dwEnableHideAlarm;				/* 鏄惁鍚姩閬尅鎶ヨ ,0-鍚�,1-浣庣伒鏁忓害 2-涓伒鏁忓害 3-楂樼伒鏁忓害*/
        public short wHideAlarmAreaTopLeftX;			/* 閬尅鍖哄煙鐨剎鍧愭爣 */
        public short wHideAlarmAreaTopLeftY;			/* 閬尅鍖哄煙鐨剏鍧愭爣 */
        public short wHideAlarmAreaWidth;				/* 閬尅鍖哄煙鐨勫 */
        public short wHideAlarmAreaHeight;				/*閬尅鍖哄煙鐨勯珮*/
        public NET_DVR_HANDLEEXCEPTION_V30 strHideAlarmHandleType;	/* 澶勭悊鏂瑰紡 */
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];//甯冮槻鏃堕棿
    }

  public static class NET_DVR_HIDEALARM extends Structure {//閬尅鎶ヨ(瀛愮粨鏋�)  鍖哄煙澶у皬704*576
	public int dwEnableHideAlarm;				/* 鏄惁鍚姩閬尅鎶ヨ ,0-鍚�,1-浣庣伒鏁忓害 2-涓伒鏁忓害 3-楂樼伒鏁忓害*/
	public short wHideAlarmAreaTopLeftX;			/* 閬尅鍖哄煙鐨剎鍧愭爣 */
	public short wHideAlarmAreaTopLeftY;			/* 閬尅鍖哄煙鐨剏鍧愭爣 */
	public short wHideAlarmAreaWidth;				/* 閬尅鍖哄煙鐨勫 */
	public short wHideAlarmAreaHeight;				/*閬尅鍖哄煙鐨勯珮*/
	public NET_DVR_HANDLEEXCEPTION strHideAlarmHandleType;	/* 澶勭悊鏂瑰紡 */
}

    public static class NET_DVR_VILOST_V30 extends Structure {    //淇″彿涓㈠け鎶ヨ(瀛愮粨鏋�)(9000鎵╁睍)
        public byte byEnableHandleVILost;	                     /* 鏄惁澶勭悊淇″彿涓㈠け鎶ヨ */
        public NET_DVR_HANDLEEXCEPTION_V30 strVILostHandleType;	     /* 澶勭悊鏂瑰紡 */
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];//甯冮槻鏃堕棿
    }

public static class NET_DVR_VILOST extends Structure {    //淇″彿涓㈠け鎶ヨ(瀛愮粨鏋�)
	byte byEnableHandleVILost;	/* 鏄惁澶勭悊淇″彿涓㈠け鎶ヨ */
	NET_DVR_HANDLEEXCEPTION strVILostHandleType;	/* 澶勭悊鏂瑰紡 */
}

    public static class NET_DVR_SHELTER extends Structure {  //閬尅鍖哄煙(瀛愮粨鏋�)
        public short wHideAreaTopLeftX;				/* 閬尅鍖哄煙鐨剎鍧愭爣 */
        public short wHideAreaTopLeftY;				/* 閬尅鍖哄煙鐨剏鍧愭爣 */
        public short wHideAreaWidth;				/* 閬尅鍖哄煙鐨勫 */
        public short wHideAreaHeight;				/* 閬尅鍖哄煙鐨勯珮*/
    }

    public static class NET_DVR_COLOR extends Structure {
        public byte byBrightness;  	/*浜害,0-255*/
        public byte byContrast;    	/*瀵规瘮搴�,0-255*/
        public byte bySaturation;  	/*楗卞拰搴�,0-255*/
        public byte byHue;    		/*鑹茶皟,0-255*/
    }

    public static class NET_DVR_VICOLOR extends Structure {
        public NET_DVR_COLOR[] struColor = new NET_DVR_COLOR[MAX_TIMESEGMENT_V30];/*鍥捐薄鍙傛暟(绗竴涓湁鏁堬紝鍏朵粬涓変釜淇濈暀)*/
        public NET_DVR_SCHEDTIME[] struHandleTime = new NET_DVR_SCHEDTIME[MAX_TIMESEGMENT_V30];/*澶勭悊鏃堕棿娈�(淇濈暀)*/
    };

    public static class NET_DVR_PICCFG_V30 extends Structure {
        public int dwSize;
        public byte[] sChanName = new byte[NAME_LEN];
        public int dwVideoFormat;	            /* 鍙 瑙嗛鍒跺紡 1-NTSC 2-PAL*/
        public NET_DVR_VICOLOR struViColor;        // 鍥惧儚鍙傛暟鎸夋椂闂存璁剧疆
        public int dwShowChanName;               // 棰勮鐨勫浘璞′笂鏄惁鏄剧ず閫氶亾鍚嶇О,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
        public short wShowNameTopLeftX;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剎鍧愭爣 */
        public short wShowNameTopLeftY;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剏鍧愭爣 */
        public NET_DVR_VILOST_V30 struVILost;      //瑙嗛淇″彿涓㈠け鎶ヨ
        public NET_DVR_VILOST_V30 struAULost;	/*闊抽淇″彿涓㈠け鎶ヨ(淇濈暀)*/
        public NET_DVR_MOTION_V30 struMotion;      //绉诲姩渚︽祴
        public NET_DVR_HIDEALARM_V30 struHideAlarm;//閬尅鎶ヨ
        public int dwEnableHide;		            /* 鏄惁鍚姩閬洊(鍖哄煙澶у皬704*576) ,0-鍚�,1-鏄�*/
        public NET_DVR_SHELTER[] struShelter = new NET_DVR_SHELTER[4];
        public int dwShowOsd;                //棰勮鐨勫浘璞′笂鏄惁鏄剧ずOSD,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
        public short wOSDTopLeftX;				/* OSD鐨剎鍧愭爣 */
        public short wOSDTopLeftY;				/* OSD鐨剏鍧愭爣 */
        public byte byOSDType;					/* OSD绫诲瀷(涓昏鏄勾鏈堟棩鏍煎紡) */
        public byte byDispWeek;				/* 鏄惁鏄剧ず鏄熸湡 */
        public byte byOSDAttrib;				/* OSD灞炴��:閫忔槑锛岄棯鐑� */
        public byte byHourOSDType;				/* OSD灏忔椂鍒�:0-24灏忔椂鍒�,1-12灏忔椂鍒� */
        public byte[] byRes = new byte[64];
    }

    public static class NET_DVR_PICCFG_EX extends Structure {//閫氶亾鍥捐薄缁撴瀯SDK_V14鎵╁睍
	public int dwSize;
	 public byte[] sChanName = new byte[NAME_LEN];
	 public int dwVideoFormat;	/* 鍙 瑙嗛鍒跺紡 1-NTSC 2-PAL*/
	 public byte byBrightness;  	/*浜害,0-255*/
	 public byte byContrast;    	/*瀵规瘮搴�,0-255*/
	 public byte bySaturation;  	/*楗卞拰搴�,0-255 */
	 public byte byHue;    			/*鑹茶皟,0-255*/
	//鏄剧ず閫氶亾鍚�
	 public int dwShowChanName; // 棰勮鐨勫浘璞′笂鏄惁鏄剧ず閫氶亾鍚嶇О,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
	 public short wShowNameTopLeftX;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剎鍧愭爣 */
	 public short wShowNameTopLeftY;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剏鍧愭爣 */
	//淇″彿涓㈠け鎶ヨ
	 public NET_DVR_VILOST struVILost;
	//绉诲姩渚︽祴
	 public NET_DVR_MOTION struMotion;
	//閬尅鎶ヨ
	 public NET_DVR_HIDEALARM struHideAlarm;
	//閬尅  鍖哄煙澶у皬704*576
	 public int dwEnableHide;		/* 鏄惁鍚姩閬尅 ,0-鍚�,1-鏄�*/
	 public NET_DVR_SHELTER[] struShelter = new NET_DVR_SHELTER[MAX_SHELTERNUM];
	//OSD
	 public int dwShowOsd;// 棰勮鐨勫浘璞′笂鏄惁鏄剧ずOSD,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
	 public short wOSDTopLeftX;				/* OSD鐨剎鍧愭爣 */
	 public short wOSDTopLeftY;				/* OSD鐨剏鍧愭爣 */
	 public byte byOSDType;					/* OSD绫诲瀷(涓昏鏄勾鏈堟棩鏍煎紡) */
	/* 0: XXXX-XX-XX 骞存湀鏃� */
	/* 1: XX-XX-XXXX 鏈堟棩骞� */
	/* 2: XXXX骞碭X鏈圶X鏃� */
	/* 3: XX鏈圶X鏃XXX骞� */
	/* 4: XX-XX-XXXX 鏃ユ湀骞�*/
	/* 5: XX鏃X鏈圶XXX骞� */
	 public byte byDispWeek;				/* 鏄惁鏄剧ず鏄熸湡 */
	 public byte byOSDAttrib;				/* OSD灞炴��:閫忔槑锛岄棯鐑� */
	/* 0: 涓嶆樉绀篛SD */
	/* 1: 閫忔槑,闂儊 */
	/* 2: 閫忔槑,涓嶉棯鐑� */
	/* 3: 闂儊,涓嶉�忔槑 */
	/* 4: 涓嶉�忔槑,涓嶉棯鐑� */
	 public byte byHourOsdType;	//灏忔椂鍒讹細0琛ㄧず24灏忔椂鍒讹紝1-12灏忔椂鍒舵垨am/pm
}


 public static class NET_DVR_PICCFG extends Structure { //閫氶亾鍥捐薄缁撴瀯(SDK_V13鍙婁箣鍓嶇増鏈�)
	 public int dwSize;
	 public byte[] sChanName = new byte[NAME_LEN];
	 public int dwVideoFormat;	/* 鍙 瑙嗛鍒跺紡 1-NTSC 2-PAL*/
	 public byte byBrightness;  	/*浜害,0-255*/
	 public byte byContrast;    	/*瀵规瘮搴�,0-255*/
	 public byte bySaturation;  	/*楗卞拰搴�,0-255 */
	 public byte byHue;    			/*鑹茶皟,0-255*/
	//鏄剧ず閫氶亾鍚�
	 public int dwShowChanName; // 棰勮鐨勫浘璞′笂鏄惁鏄剧ず閫氶亾鍚嶇О,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
	 public short wShowNameTopLeftX;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剎鍧愭爣 */
	 public short wShowNameTopLeftY;				/* 閫氶亾鍚嶇О鏄剧ず浣嶇疆鐨剏鍧愭爣 */
	//淇″彿涓㈠け鎶ヨ
	 public NET_DVR_VILOST struVILost;
	//绉诲姩渚︽祴
	 public NET_DVR_MOTION struMotion;
	//閬尅鎶ヨ
	 public NET_DVR_HIDEALARM struHideAlarm;
	//閬尅  鍖哄煙澶у皬704*576
	 public int dwEnableHide;		/* 鏄惁鍚姩閬尅 ,0-鍚�,1-鏄�*/
	 public short wHideAreaTopLeftX;				/* 閬尅鍖哄煙鐨剎鍧愭爣 */
	 public short wHideAreaTopLeftY;				/* 閬尅鍖哄煙鐨剏鍧愭爣 */
	 public short wHideAreaWidth;				/* 閬尅鍖哄煙鐨勫 */
	 public short wHideAreaHeight;				/*閬尅鍖哄煙鐨勯珮*/
	//OSD
	 public int dwShowOsd;// 棰勮鐨勫浘璞′笂鏄惁鏄剧ずOSD,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576
	 public short wOSDTopLeftX;				/* OSD鐨剎鍧愭爣 */
	 public short wOSDTopLeftY;				/* OSD鐨剏鍧愭爣 */
	 public byte byOSDType;					/* OSD绫诲瀷(涓昏鏄勾鏈堟棩鏍煎紡) */
	/* 0: XXXX-XX-XX 骞存湀鏃� */
	/* 1: XX-XX-XXXX 鏈堟棩骞� */
	/* 2: XXXX骞碭X鏈圶X鏃� */
	/* 3: XX鏈圶X鏃XXX骞� */
	/* 4: XX-XX-XXXX 鏃ユ湀骞�*/
	/* 5: XX鏃X鏈圶XXX骞� */
	byte byDispWeek;				/* 鏄惁鏄剧ず鏄熸湡 */
	byte byOSDAttrib;				/* OSD灞炴��:閫忔槑锛岄棯鐑� */
	/* 0: 涓嶆樉绀篛SD */
	/* 1: 閫忔槑,闂儊 */
	/* 2: 閫忔槑,涓嶉棯鐑� */
	/* 3: 闂儊,涓嶉�忔槑 */
	/* 4: 涓嶉�忔槑,涓嶉棯鐑� */
	 public byte reservedData2;
}

    //鐮佹祦鍘嬬缉鍙傛暟(瀛愮粨鏋�)(9000鎵╁睍)
    public static class NET_DVR_COMPRESSION_INFO_V30 extends Structure {
        public byte byStreamType;		//鐮佹祦绫诲瀷 0-瑙嗛娴�, 1-澶嶅悎娴�
        public byte byResolution;  	//鍒嗚鲸鐜�0-DCIF 1-CIF, 2-QCIF, 3-4CIF, 4-2CIF 5锛堜繚鐣欙級16-VGA锛�640*480锛� 17-UXGA锛�1600*1200锛� 18-SVGA 锛�800*600锛�19-HD720p锛�1280*720锛�20-XVGA  21-HD900p
        public byte byBitrateType;		//鐮佺巼绫诲瀷 0:瀹氱爜鐜囷紝1:鍙樼爜鐜�
        public byte byPicQuality;		//鍥捐薄璐ㄩ噺 0-鏈�濂� 1-娆″ソ 2-杈冨ソ 3-涓�鑸� 4-杈冨樊 5-宸�
        public int dwVideoBitrate; 	//瑙嗛鐮佺巼 0-淇濈暀 1-16K 2-32K 3-48k 4-64K 5-80K 6-96K 7-128K 8-160k 9-192K 10-224K 11-256K 12-320K 13-384K 14-448K 15-512K 16-640K 17-768K 18-896K 19-1024K 20-1280K 21-1536K 22-1792K 23-2048鏈�楂樹綅(31浣�)缃垚1琛ㄧず鏄嚜瀹氫箟鐮佹祦, 0-30浣嶈〃绀虹爜娴佸�笺��
        public int dwVideoFrameRate;	//甯х巼 0-鍏ㄩ儴; 1-1/16; 2-1/8; 3-1/4; 4-1/2; 5-1; 6-2; 7-4; 8-6; 9-8; 10-10; 11-12; 12-16; 13-20; V2.0鐗堟湰涓柊鍔�14-15; 15-18; 16-22;
        public short wIntervalFrameI;  //I甯ч棿闅�
        public byte byIntervalBPFrame;//0-BBP甯�; 1-BP甯�; 2-鍗昉甯�
        public byte byENumber;        //E甯ф暟閲忥紙淇濈暀锛�
        public byte byVideoEncType;//瑙嗛缂栫爜绫诲瀷 0 hik264;1鏍囧噯h264; 2鏍囧噯mpeg4;
        public byte byAudioEncType;//闊抽缂栫爜绫诲瀷 0 G722
        public byte[] byres = new byte[10];
    }

    //閫氶亾鍘嬬缉鍙傛暟(9000鎵╁睍)
    public static class NET_DVR_COMPRESSIONCFG_V30 extends Structure {
        public int dwSize;
        public NET_DVR_COMPRESSION_INFO_V30 struNormHighRecordPara;    //褰曞儚 瀵瑰簲8000鐨勬櫘閫�
        public NET_DVR_COMPRESSION_INFO_V30 struRes;   //淇濈暀 String[28];
        public NET_DVR_COMPRESSION_INFO_V30 struEventRecordPara;       //浜嬩欢瑙﹀彂鍘嬬缉鍙傛暟
        public NET_DVR_COMPRESSION_INFO_V30 struNetPara;               //缃戜紶(瀛愮爜娴�)
    }


    public static class NET_DVR_COMPRESSION_INFO extends Structure {//鐮佹祦鍘嬬缉鍙傛暟(瀛愮粨鏋�)
	public byte byStreamType;		//鐮佹祦绫诲瀷0-瑙嗛娴�,1-澶嶅悎娴�,琛ㄧず鍘嬬缉鍙傛暟鏃舵渶楂樹綅琛ㄧず鏄惁鍚敤鍘嬬缉鍙傛暟
	public byte byResolution;  	//鍒嗚鲸鐜�0-DCIF 1-CIF, 2-QCIF, 3-4CIF, 4-2CIF, 5-2QCIF(352X144)(杞﹁浇涓撶敤)
	public byte byBitrateType;		//鐮佺巼绫诲瀷0:鍙樼爜鐜囷紝1:瀹氱爜鐜�
	public byte  byPicQuality;		//鍥捐薄璐ㄩ噺 0-鏈�濂� 1-娆″ソ 2-杈冨ソ 3-涓�鑸� 4-杈冨樊 5-宸�
	public int dwVideoBitrate; 	//瑙嗛鐮佺巼 0-淇濈暀 1-16K(淇濈暀) 2-32K 3-48k 4-64K 5-80K 6-96K 7-128K 8-160k 9-192K 10-224K 11-256K 12-320K
							// 13-384K 14-448K 15-512K 16-640K 17-768K 18-896K 19-1024K 20-1280K 21-1536K 22-1792K 23-2048K
							//鏈�楂樹綅(31浣�)缃垚1琛ㄧず鏄嚜瀹氫箟鐮佹祦, 0-30浣嶈〃绀虹爜娴佸��(MIN-32K MAX-8192K)銆�
	public int dwVideoFrameRate;	//甯х巼 0-鍏ㄩ儴; 1-1/16; 2-1/8; 3-1/4; 4-1/2; 5-1; 6-2; 7-4; 8-6; 9-8; 10-10; 11-12; 12-16; 13-20;
}

    public static class NET_DVR_COMPRESSIONCFG extends Structure {//閫氶亾鍘嬬缉鍙傛暟
	public int dwSize;
	public NET_DVR_COMPRESSION_INFO struRecordPara; //褰曞儚/浜嬩欢瑙﹀彂褰曞儚
	public NET_DVR_COMPRESSION_INFO struNetPara;	//缃戜紶/淇濈暀
}


    public static class NET_DVR_COMPRESSION_INFO_EX extends Structure {//鐮佹祦鍘嬬缉鍙傛暟(瀛愮粨鏋�)(鎵╁睍) 澧炲姞I甯ч棿闅�
	public byte byStreamType;		//鐮佹祦绫诲瀷0-瑙嗛娴�, 1-澶嶅悎娴�
	public byte byResolution;  	//鍒嗚鲸鐜�0-DCIF 1-CIF, 2-QCIF, 3-4CIF, 4-2CIF, 5-2QCIF(352X144)(杞﹁浇涓撶敤)
	public byte byBitrateType;		//鐮佺巼绫诲瀷0:鍙樼爜鐜囷紝1:瀹氱爜鐜�
	public byte  byPicQuality;		//鍥捐薄璐ㄩ噺 0-鏈�濂� 1-娆″ソ 2-杈冨ソ 3-涓�鑸� 4-杈冨樊 5-宸�
	public int dwVideoBitrate; 	//瑙嗛鐮佺巼 0-淇濈暀 1-16K(淇濈暀) 2-32K 3-48k 4-64K 5-80K 6-96K 7-128K 8-160k 9-192K 10-224K 11-256K 12-320K
	// 13-384K 14-448K 15-512K 16-640K 17-768K 18-896K 19-1024K 20-1280K 21-1536K 22-1792K 23-2048K
	//鏈�楂樹綅(31浣�)缃垚1琛ㄧず鏄嚜瀹氫箟鐮佹祦, 0-30浣嶈〃绀虹爜娴佸��(MIN-32K MAX-8192K)銆�
	public int dwVideoFrameRate;	//甯х巼 0-鍏ㄩ儴; 1-1/16; 2-1/8; 3-1/4; 4-1/2; 5-1; 6-2; 7-4; 8-6; 9-8; 10-10; 11-12; 12-16; 13-20, //V2.0澧炲姞14-15, 15-18, 16-22;
	public short  wIntervalFrameI;  //I甯ч棿闅�
	//2006-08-11 澧炲姞鍗昉甯х殑閰嶇疆鎺ュ彛锛屽彲浠ユ敼鍠勫疄鏃舵祦寤舵椂闂
	public byte  byIntervalBPFrame;//0-BBP甯�; 1-BP甯�; 2-鍗昉甯�
	public byte  byENumber;//E甯ф暟閲�
}

    public static class NET_DVR_COMPRESSIONCFG_EX extends Structure {//閫氶亾鍘嬬缉鍙傛暟(鎵╁睍)
	public int dwSize;
	public NET_DVR_COMPRESSION_INFO_EX struRecordPara; //褰曞儚
	public NET_DVR_COMPRESSION_INFO_EX struNetPara;	//缃戜紶
}

    public static class NET_DVR_RECCOMPRESSIONCFG_EX extends Structure {//褰曡薄鏃堕棿娈靛帇缂╁弬鏁伴厤缃�(GE瀹氬埗)2006-09-18
	int dwSize;
	NET_DVR_COMPRESSION_INFO_EX[][]  struRecTimePara = new NET_DVR_COMPRESSION_INFO_EX[MAX_DAYS][MAX_TIMESEGMENT]; //褰曞儚鏃堕棿娈�
}

    public static class NET_DVR_RECORDSCHED extends Structure //鏃堕棿娈靛綍鍍忓弬鏁伴厤缃�(瀛愮粨鏋�)
    {
        public  NET_DVR_SCHEDTIME struRecordTime = new NET_DVR_SCHEDTIME() ;
        public byte byRecordType;	//0:瀹氭椂褰曞儚锛�1:绉诲姩渚︽祴锛�2:鎶ヨ褰曞儚锛�3:鍔ㄦ祴|鎶ヨ锛�4:鍔ㄦ祴&鎶ヨ, 5:鍛戒护瑙﹀彂, 6: 鏅鸿兘褰曞儚
        public byte[] reservedData = new byte[3];
    }

    public static class NET_DVR_RECORDDAY extends Structure //鍏ㄥぉ褰曞儚鍙傛暟閰嶇疆(瀛愮粨鏋�)
    {
        public short wAllDayRecord;				/* 鏄惁鍏ㄥぉ褰曞儚 0-鍚� 1-鏄�*/
        public byte byRecordType;				/* 褰曡薄绫诲瀷 0:瀹氭椂褰曞儚锛�1:绉诲姩渚︽祴锛�2:鎶ヨ褰曞儚锛�3:鍔ㄦ祴|鎶ヨ锛�4:鍔ㄦ祴&鎶ヨ 5:鍛戒护瑙﹀彂, 6: 鏅鸿兘褰曞儚*/
        public byte reservedData;
    }

    public static class NET_DVR_RECORDSCHEDWEEK extends Structure
    {
       public 	NET_DVR_RECORDSCHED[] struRecordSched = new NET_DVR_RECORDSCHED[MAX_TIMESEGMENT_V30];
    }

    public static class NET_DVR_RECORD_V30 extends Structure {    //閫氶亾褰曞儚鍙傛暟閰嶇疆(9000鎵╁睍)
        public int dwSize;
        public int dwRecord;  						/*鏄惁褰曞儚 0-鍚� 1-鏄�*/
        public NET_DVR_RECORDDAY[] struRecAllDay = new NET_DVR_RECORDDAY[MAX_DAYS];
        public NET_DVR_RECORDSCHEDWEEK[] struRecordSched = new NET_DVR_RECORDSCHEDWEEK[MAX_DAYS];
        public int dwRecordTime;					/* 褰曡薄寤舵椂闀垮害 0-5绉掞紝 1-20绉掞紝 2-30绉掞紝 3-1鍒嗛挓锛� 4-2鍒嗛挓锛� 5-5鍒嗛挓锛� 6-10鍒嗛挓*/
        public int dwPreRecordTime;				/* 棰勫綍鏃堕棿 0-涓嶉褰� 1-5绉� 2-10绉� 3-15绉� 4-20绉� 5-25绉� 6-30绉� 7-0xffffffff(灏藉彲鑳介褰�) */
        public int dwRecorderDuration;				/* 褰曞儚淇濆瓨鐨勬渶闀挎椂闂� */
        public byte byRedundancyRec;	/*鏄惁鍐椾綑褰曞儚,閲嶈鏁版嵁鍙屽浠斤細0/1*/
        public byte byAudioRec;		/*褰曞儚鏃跺鍚堟祦缂栫爜鏃舵槸鍚﹁褰曢煶棰戞暟鎹細鍥藉鏈夋娉曡*/
        public byte[] byReserve = new byte[10];
    }

 public static class NET_DVR_RECORD extends Structure { //閫氶亾褰曞儚鍙傛暟閰嶇疆
	 public int dwSize;
	 public int dwRecord;  /*鏄惁褰曞儚 0-鍚� 1-鏄�*/
	 public NET_DVR_RECORDDAY[]  struRecAllDay = new NET_DVR_RECORDDAY[MAX_DAYS];
         public NET_DVR_RECORDSCHEDWEEK[] struRecordSched = new NET_DVR_RECORDSCHEDWEEK[MAX_DAYS];
	 public int dwRecordTime;	/* 褰曡薄鏃堕棿闀垮害 0-5绉掞紝 1-20绉掞紝 2-30绉掞紝 3-1鍒嗛挓锛� 4-2鍒嗛挓锛� 5-5鍒嗛挓锛� 6-10鍒嗛挓*/
	 public int dwPreRecordTime;	/* 棰勫綍鏃堕棿 0-涓嶉褰� 1-5绉� 2-10绉� 3-15绉� 4-20绉� 5-25绉� 6-30绉� 7-0xffffffff(灏藉彲鑳介褰�) */
}

public static class NET_DVR_STATFRAME extends Structure { //鍗曞抚缁熻鍙傛暟
	 public int dwRelativeTime;
	 public int dwAbsTime;  /*缁熻缁濆鏃舵爣*/
         public byte[] byRes = new byte[92];
}

public static class NET_DVR_STATTIME extends Structure { //鍗曞抚缁熻鍙傛暟
	 public NET_DVR_TIME  tmStart;  //缁熻寮�濮嬫椂闂�
	 public NET_DVR_TIME  tmEnd;    //缁熻缁撴潫鏃堕棿
         public byte[] byRes = new byte[92];
}

 public static class UNION_PDC_STATPARAM extends Union
{
   // public byte[] byLen = new byte[140];
    public NET_DVR_STATFRAME struStatFrame;
    public NET_DVR_STATTIME  struStatTime;
}

public static class NET_DVR_PDC_ALRAM_INFO extends Structure { //閫氶亾褰曞儚鍙傛暟閰嶇疆
	 public int dwSize;
	 public byte byMode;  /*0-鍗曞抚缁熻缁撴灉锛�1-鏈�灏忔椂闂存缁熻缁撴灉*/
         public byte byChannel;
         public byte bySmart;         //涓撲笟鏅鸿兘杩斿洖0锛孲mart 杩斿洖 1
         public byte byRes1;          // 淇濈暀瀛楄妭
         public NET_VCA_DEV_INFO  struDevInfo;		     //鍓嶇璁惧淇℃伅
         public UNION_PDC_STATPARAM uStatModeParam = new UNION_PDC_STATPARAM();
	 public int dwLeaveNum;	/* 绂诲紑浜烘暟 */
	 public int dwEnterNum;	/* 杩涘叆浜烘暟 */
         public byte byBrokenNetHttp;     //鏂綉缁紶鏍囧織浣嶏紝0-涓嶆槸閲嶄紶鏁版嵁锛�1-閲嶄紶鏁版嵁
         public byte byRes3;
         public short wDevInfoIvmsChannelEx;     //涓嶯ET_VCA_DEV_INFO閲岀殑byIvmsChannel鍚箟鐩稿悓锛岃兘琛ㄧず鏇村ぇ鐨勫�笺�傝�佸鎴风鐢╞yIvmsChannel鑳界户缁吋瀹癸紝浣嗘槸鏈�澶у埌255銆傛柊瀹㈡埛绔増鏈浣跨敤wDevInfoIvmsChannelEx
	 public int dwPassingNum;        // 缁忚繃浜烘暟锛堣繘鍏ュ尯鍩熷悗寰樺緤娌℃湁瑙﹀彂杩涘叆銆佺寮�鐨勪汉鏁帮級
         public byte[] byRes2 = new byte[32];
         
         public void read()
         {
             super.read();
             switch(byMode)
             {
                 case 0:
                     uStatModeParam.setType(NET_DVR_STATFRAME.class);
                     break;
                 case 1:
                     uStatModeParam.setType(NET_DVR_STATTIME.class);
                     break;
                 default:
                     break;
             }
             uStatModeParam.read();
         }
         public void write()
         {
             super.write();
             uStatModeParam.write();
         }
}

//浜戝彴鍗忚琛ㄧ粨鏋勯厤缃�
 public static class NET_DVR_PTZ_PROTOCOL extends Structure {
       public int dwType;               /*瑙ｇ爜鍣ㄧ被鍨嬪�硷紝浠�1寮�濮嬭繛缁�掑*/
       public byte[]  byDescribe = new byte[DESC_LEN]; /*瑙ｇ爜鍣ㄧ殑鎻忚堪绗︼紝鍜�8000涓殑涓�鑷�*/
}

 public static class NET_DVR_PTZCFG extends Structure {
       public  int   dwSize;
       public  NET_DVR_PTZ_PROTOCOL[] struPtz = new NET_DVR_PTZ_PROTOCOL[PTZ_PROTOCOL_NUM];/*鏈�澶�200涓璓TZ鍗忚*/
       public  int   dwPtzNum;           /*鏈夋晥鐨刾tz鍗忚鏁扮洰锛屼粠0寮�濮�(鍗宠绠楁椂鍔�1)*/
       public  byte[]  byRes = new byte[8];
}
/***************************浜戝彴绫诲瀷(end)******************************/
 public static class NET_DVR_DECODERCFG_V30 extends Structure {//閫氶亾瑙ｇ爜鍣�(浜戝彴)鍙傛暟閰嶇疆(9000鎵╁睍)
	public int dwSize;
	public int dwBaudRate;       //娉㈢壒鐜�(bps)锛�0锛�50锛�1锛�75锛�2锛�110锛�3锛�150锛�4锛�300锛�5锛�600锛�6锛�1200锛�7锛�2400锛�8锛�4800锛�9锛�9600锛�10锛�19200锛� 11锛�38400锛�12锛�57600锛�13锛�76800锛�14锛�115.2k;
	public byte byDataBit;         // 鏁版嵁鏈夊嚑浣� 0锛�5浣嶏紝1锛�6浣嶏紝2锛�7浣嶏紝3锛�8浣�;
	public byte byStopBit;         // 鍋滄浣� 0锛�1浣嶏紝1锛�2浣�;
	public byte byParity;          // 鏍￠獙 0锛嶆棤鏍￠獙锛�1锛嶅鏍￠獙锛�2锛嶅伓鏍￠獙;
	public byte byFlowcontrol;     // 0锛嶆棤锛�1锛嶈蒋娴佹帶,2-纭祦鎺�
	public short wDecoderType;      //瑙ｇ爜鍣ㄧ被鍨�, 0锛峐ouLi锛�1锛峀iLin-1016锛�2锛峀iLin-820锛�3锛峆elco-p锛�4锛岲M DynaColor锛�5锛岺D600锛�6锛岼C-4116锛�7锛峆elco-d WX锛�8锛峆elco-d PICO
	public short wDecoderAddress;	/*瑙ｇ爜鍣ㄥ湴鍧�:0 - 255*/
	public byte[] bySetPreset = new byte[MAX_PRESET_V30];		/* 棰勭疆鐐规槸鍚﹁缃�,0-娌℃湁璁剧疆,1-璁剧疆*/
	public byte[] bySetCruise = new byte[MAX_CRUISE_V30];		/* 宸¤埅鏄惁璁剧疆: 0-娌℃湁璁剧疆,1-璁剧疆 */
	public byte[] bySetTrack = new byte[MAX_TRACK_V30];		    /* 杞ㄨ抗鏄惁璁剧疆,0-娌℃湁璁剧疆,1-璁剧疆*/
}

 public static class NET_DVR_DECODERCFG extends Structure {//閫氶亾瑙ｇ爜鍣�(浜戝彴)鍙傛暟閰嶇疆
	public int dwSize;
	public int dwBaudRate;       //娉㈢壒鐜�(bps)锛�0锛�50锛�1锛�75锛�2锛�110锛�3锛�150锛�4锛�300锛�5锛�600锛�6锛�1200锛�7锛�2400锛�8锛�4800锛�9锛�9600锛�10锛�19200锛� 11锛�38400锛�12锛�57600锛�13锛�76800锛�14锛�115.2k;
	public byte byDataBit;         // 鏁版嵁鏈夊嚑浣� 0锛�5浣嶏紝1锛�6浣嶏紝2锛�7浣嶏紝3锛�8浣�;
	public byte byStopBit;         // 鍋滄浣� 0锛�1浣嶏紝1锛�2浣�;
	public byte byParity;          // 鏍￠獙 0锛嶆棤鏍￠獙锛�1锛嶅鏍￠獙锛�2锛嶅伓鏍￠獙;
	public byte byFlowcontrol;     // 0锛嶆棤锛�1锛嶈蒋娴佹帶,2-纭祦鎺�
	public short wDecoderType;      //瑙ｇ爜鍣ㄧ被鍨�, 0锛峐ouLi锛�1锛峀iLin-1016锛�2锛峀iLin-820锛�3锛峆elco-p锛�4锛岲M DynaColor锛�5锛岺D600锛�6锛岼C-4116锛�7锛峆elco-d WX锛�8锛峆elco-d PICO
	public short wDecoderAddress;	/*瑙ｇ爜鍣ㄥ湴鍧�:0 - 255*/
	public byte[] bySetPreset = new byte[MAX_PRESET];		/* 棰勭疆鐐规槸鍚﹁缃�,0-娌℃湁璁剧疆,1-璁剧疆*/
	public byte[] bySetCruise = new byte[MAX_CRUISE];		/* 宸¤埅鏄惁璁剧疆: 0-娌℃湁璁剧疆,1-璁剧疆 */
	public byte[] bySetTrack = new byte[MAX_TRACK];		    /* 杞ㄨ抗鏄惁璁剧疆,0-娌℃湁璁剧疆,1-璁剧疆*/
}

public static class NET_DVR_PPPCFG_V30 extends Structure {//ppp鍙傛暟閰嶇疆(瀛愮粨鏋�)
	public NET_DVR_IPADDR struRemoteIP;	//杩滅IP鍦板潃
	public NET_DVR_IPADDR struLocalIP;		//鏈湴IP鍦板潃
	public byte[] sLocalIPMask = new byte[16];			//鏈湴IP鍦板潃鎺╃爜
	public byte[] sUsername = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public byte byPPPMode;					//PPP妯″紡, 0锛嶄富鍔紝1锛嶈鍔�
	public byte byRedial;					//鏄惁鍥炴嫧 锛�0-鍚�,1-鏄�
	public byte byRedialMode;				//鍥炴嫧妯″紡,0-鐢辨嫧鍏ヨ�呮寚瀹�,1-棰勭疆鍥炴嫧鍙风爜
	public byte byDataEncrypt;				//鏁版嵁鍔犲瘑,0-鍚�,1-鏄�
	public int dwMTU;					//MTU
	public byte[] sTelephoneNumber = new byte[PHONENUMBER_LEN];   //鐢佃瘽鍙风爜
}

public static class NET_DVR_PPPCFG extends Structure {//ppp鍙傛暟閰嶇疆(瀛愮粨鏋�)
	public byte[] sRemoteIP = new byte[16];				//杩滅IP鍦板潃
	public byte[] sLocalIP = new byte[16];				//鏈湴IP鍦板潃
	public byte[] sLocalIPMask = new byte[16];			//鏈湴IP鍦板潃鎺╃爜
	public byte[] sUsername = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public byte byPPPMode;					//PPP妯″紡, 0锛嶄富鍔紝1锛嶈鍔�
	public byte byRedial;					//鏄惁鍥炴嫧 锛�0-鍚�,1-鏄�
	public byte byRedialMode;				//鍥炴嫧妯″紡,0-鐢辨嫧鍏ヨ�呮寚瀹�,1-棰勭疆鍥炴嫧鍙风爜
	public byte byDataEncrypt;				//鏁版嵁鍔犲瘑,0-鍚�,1-鏄�
	public int dwMTU;					//MTU
	public byte[] sTelephoneNumber = new byte[PHONENUMBER_LEN];   //鐢佃瘽鍙风爜
}


public static class NET_DVR_SINGLE_RS232 extends Structure {//RS232涓插彛鍙傛暟閰嶇疆(9000鎵╁睍)
       public int dwBaudRate;   /*娉㈢壒鐜�(bps)锛�0锛�50锛�1锛�75锛�2锛�110锛�3锛�150锛�4锛�300锛�5锛�600锛�6锛�1200锛�7锛�2400锛�8锛�4800锛�9锛�9600锛�10锛�19200锛� 11锛�38400锛�12锛�57600锛�13锛�76800锛�14锛�115.2k;*/
       public byte byDataBit;     /* 鏁版嵁鏈夊嚑浣� 0锛�5浣嶏紝1锛�6浣嶏紝2锛�7浣嶏紝3锛�8浣� */
       public byte byStopBit;     /* 鍋滄浣� 0锛�1浣嶏紝1锛�2浣� */
       public byte byParity;      /* 鏍￠獙 0锛嶆棤鏍￠獙锛�1锛嶅鏍￠獙锛�2锛嶅伓鏍￠獙 */
       public byte byFlowcontrol; /* 0锛嶆棤锛�1锛嶈蒋娴佹帶,2-纭祦鎺� */
       public int dwWorkMode;   /* 宸ヤ綔妯″紡锛�0锛�232涓插彛鐢ㄤ簬PPP鎷ㄥ彿锛�1锛�232涓插彛鐢ㄤ簬鍙傛暟鎺у埗锛�2锛嶉�忔槑閫氶亾 */
}

public static class NET_DVR_RS232CFG_V30 extends Structure {//RS232涓插彛鍙傛暟閰嶇疆(9000鎵╁睍)
	public int dwSize;
        public NET_DVR_SINGLE_RS232 struRs232;/*鐩墠鍙湁绗竴涓覆鍙ｈ缃湁鏁堬紝鎵�鏈夎澶囬兘鍙敮鎸佷竴涓覆鍙ｏ紝鍏朵粬涓冧釜淇濈暀*/
	public byte[] byRes = new byte[84];
        public NET_DVR_PPPCFG_V30 struPPPConfig;/*ppp鍙傛暟*/
}

public static class NET_DVR_RS232CFG extends Structure {//RS232涓插彛鍙傛暟閰嶇疆
	public int dwSize;
	public int dwBaudRate;//娉㈢壒鐜�(bps)锛�0锛�50锛�1锛�75锛�2锛�110锛�3锛�150锛�4锛�300锛�5锛�600锛�6锛�1200锛�7锛�2400锛�8锛�4800锛�9锛�9600锛�10锛�19200锛� 11锛�38400锛�12锛�57600锛�13锛�76800锛�14锛�115.2k;
	public byte byDataBit;// 鏁版嵁鏈夊嚑浣� 0锛�5浣嶏紝1锛�6浣嶏紝2锛�7浣嶏紝3锛�8浣�;
	public byte byStopBit;// 鍋滄浣� 0锛�1浣嶏紝1锛�2浣�;
	public byte byParity;// 鏍￠獙 0锛嶆棤鏍￠獙锛�1锛嶅鏍￠獙锛�2锛嶅伓鏍￠獙;
	public byte byFlowcontrol;// 0锛嶆棤锛�1锛嶈蒋娴佹帶,2-纭祦鎺�
	public int dwWorkMode;// 宸ヤ綔妯″紡锛�0锛嶇獎甯︿紶杈�(232涓插彛鐢ㄤ簬PPP鎷ㄥ彿)锛�1锛嶆帶鍒跺彴(232涓插彛鐢ㄤ簬鍙傛暟鎺у埗)锛�2锛嶉�忔槑閫氶亾
	public NET_DVR_PPPCFG struPPPConfig;
}

public static class NET_DVR_ALARMINCFG_V30 extends Structure {//鎶ヨ杈撳叆鍙傛暟閰嶇疆(9000鎵╁睍)
        public 	int dwSize;
        public 	byte[] sAlarmInName = new byte[NAME_LEN];	/* 鍚嶇О */
        public 	byte byAlarmType;	            //鎶ヨ鍣ㄧ被鍨�,0锛氬父寮�,1锛氬父闂�
        public 	byte byAlarmInHandle;	        /* 鏄惁澶勭悊 0-涓嶅鐞� 1-澶勭悊*/
        public    byte[] reservedData = new byte[2];
	public NET_DVR_HANDLEEXCEPTION_V30 struAlarmHandleType;	/* 澶勭悊鏂瑰紡 */
	public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];//甯冮槻鏃堕棿
	public byte[] byRelRecordChan = new byte[MAX_CHANNUM_V30]; //鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾,涓�1琛ㄧず瑙﹀彂璇ラ�氶亾
	public byte[] byEnablePreset = new byte[MAX_CHANNUM_V30];		/* 鏄惁璋冪敤棰勭疆鐐� 0-鍚�,1-鏄�*/
	public byte[] byPresetNo = new byte[MAX_CHANNUM_V30];			/* 璋冪敤鐨勪簯鍙伴缃偣搴忓彿,涓�涓姤璀﹁緭鍏ュ彲浠ヨ皟鐢ㄥ涓�氶亾鐨勪簯鍙伴缃偣, 0xff琛ㄧず涓嶈皟鐢ㄩ缃偣銆�*/
	public byte[] byEnablePresetRevert = new byte[MAX_CHANNUM_V30]; /* 鏄惁鎭㈠鍒拌皟鐢ㄩ缃偣鍓嶇殑浣嶇疆(淇濈暀) */
	public short[] wPresetRevertDelay = new short[MAX_CHANNUM_V30];	/* 鎭㈠棰勭疆鐐瑰欢鏃�(淇濈暀) */
	public byte[] byEnableCruise = new byte[MAX_CHANNUM_V30];		/* 鏄惁璋冪敤宸¤埅 0-鍚�,1-鏄�*/
	public byte[] byCruiseNo = new byte[MAX_CHANNUM_V30];			/* 宸¤埅 */
	public byte[] byEnablePtzTrack = new byte[MAX_CHANNUM_V30];		/* 鏄惁璋冪敤杞ㄨ抗 0-鍚�,1-鏄�*/
	public byte[] byPTZTrack = new byte[MAX_CHANNUM_V30];			/* 璋冪敤鐨勪簯鍙扮殑杞ㄨ抗搴忓彿 */
        public   byte[] byRes = new byte[16];
}

public static class NET_DVR_ALARMINCFG extends Structure {//鎶ヨ杈撳叆鍙傛暟閰嶇疆
	public int dwSize;
	public byte[] sAlarmInName = new byte[NAME_LEN];	/* 鍚嶇О */
	public byte byAlarmType;	//鎶ヨ鍣ㄧ被鍨�,0锛氬父寮�,1锛氬父闂�
	public byte byAlarmInHandle;	/* 鏄惁澶勭悊 0-涓嶅鐞� 1-澶勭悊*/
	public NET_DVR_HANDLEEXCEPTION struAlarmHandleType;	/* 澶勭悊鏂瑰紡 */
        public  NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];//甯冮槻鏃堕棿
	public byte[] byRelRecordChan = new byte[MAX_CHANNUM]; //鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾,涓�1琛ㄧず瑙﹀彂璇ラ�氶亾
	public byte[] byEnablePreset = new byte[MAX_CHANNUM];		/* 鏄惁璋冪敤棰勭疆鐐� 0-鍚�,1-鏄�*/
	public byte[] byPresetNo = new byte[MAX_CHANNUM];			/* 璋冪敤鐨勪簯鍙伴缃偣搴忓彿,涓�涓姤璀﹁緭鍏ュ彲浠ヨ皟鐢ㄥ涓�氶亾鐨勪簯鍙伴缃偣, 0xff琛ㄧず涓嶈皟鐢ㄩ缃偣銆�*/
	public byte[] byEnableCruise = new byte[MAX_CHANNUM];		/* 鏄惁璋冪敤宸¤埅 0-鍚�,1-鏄�*/
	public byte[] byCruiseNo = new byte[MAX_CHANNUM];			/* 宸¤埅 */
	public byte[] byEnablePtzTrack = new byte[MAX_CHANNUM];		/* 鏄惁璋冪敤杞ㄨ抗 0-鍚�,1-鏄�*/
	public byte[] byPTZTrack = new byte[MAX_CHANNUM];			/* 璋冪敤鐨勪簯鍙扮殑杞ㄨ抗搴忓彿 */
}

public static class NET_DVR_ADDIT_POSITION extends Structure {//杞﹁浇GPS淇℃伅缁撴瀯(2007-12-27)
	public byte[]	sDevName = new byte[32];		/* 璁惧鍚嶇О */
	public int	dwSpeed;			/*閫熷害*/
	public int	dwLongitude;		/* 缁忓害*/
	public int	dwLatitude;       /* 绾害*/
	public byte[]	direction = new byte[2];   /* direction[0]:'E'or'W'(涓滅粡/瑗跨粡), direction[1]:'N'or'S'(鍖楃含/鍗楃含) */
	public byte[]  res = new byte[2];              /* 淇濈暀浣� */
}

public static class NET_DVR_ALARMINFO_V30 extends Structure {//涓婁紶鎶ヨ淇℃伅(9000鎵╁睍)
	public int dwAlarmType;/*0-淇″彿閲忔姤璀�,1-纭洏婊�,2-淇″彿涓㈠け,3锛嶇Щ鍔ㄤ睛娴�,4锛嶇‖鐩樻湭鏍煎紡鍖�,5-璇诲啓纭洏鍑洪敊,6-閬尅鎶ヨ,7-鍒跺紡涓嶅尮閰�, 8-闈炴硶璁块棶, 0xa-GPS瀹氫綅淇℃伅(杞﹁浇瀹氬埗)*/
	public int dwAlarmInputNumber;/*鎶ヨ杈撳叆绔彛*/
	public byte[]  byAlarmOutputNumber = new byte[MAX_ALARMOUT_V30];/*瑙﹀彂鐨勮緭鍑虹鍙ｏ紝涓�1琛ㄧず瀵瑰簲杈撳嚭*/
	public byte[]  byAlarmRelateChannel= new byte[MAX_CHANNUM_V30];/*瑙﹀彂鐨勫綍鍍忛�氶亾锛屼负1琛ㄧず瀵瑰簲褰曞儚, dwAlarmRelateChannel[0]瀵瑰簲绗�1涓�氶亾*/
	public byte[]  byChannel= new byte[MAX_CHANNUM_V30];/*dwAlarmType涓�2鎴�3,6鏃讹紝琛ㄧず鍝釜閫氶亾锛宒wChannel[0]瀵瑰簲绗�1涓�氶亾*/
	public byte[]  byDiskNumber= new byte[MAX_DISKNUM_V30];/*dwAlarmType涓�1,4,5鏃�,琛ㄧず鍝釜纭洏, dwDiskNumber[0]瀵瑰簲绗�1涓‖鐩�*/
}

public static class NET_DVR_ALARMINFO extends Structure {
	public int dwAlarmType;/*0-淇″彿閲忔姤璀�,1-纭洏婊�,2-淇″彿涓㈠け,3锛嶇Щ鍔ㄤ睛娴�,4锛嶇‖鐩樻湭鏍煎紡鍖�,5-璇诲啓纭洏鍑洪敊,6-閬尅鎶ヨ,7-鍒跺紡涓嶅尮閰�, 8-闈炴硶璁块棶, 9-涓插彛鐘舵��, 0xa-GPS瀹氫綅淇℃伅(杞﹁浇瀹氬埗)*/
	public int dwAlarmInputNumber;/*鎶ヨ杈撳叆绔彛, 褰撴姤璀︾被鍨嬩负9鏃惰鍙橀噺琛ㄧず涓插彛鐘舵��0琛ㄧず姝ｅ父锛� -1琛ㄧず閿欒*/
	public int[] dwAlarmOutputNumber = new int[MAX_ALARMOUT];/*瑙﹀彂鐨勮緭鍑虹鍙ｏ紝涓�1琛ㄧず瀵瑰簲鍝竴涓緭鍑�*/
	public int[] dwAlarmRelateChannel = new int[MAX_CHANNUM];/*瑙﹀彂鐨勫綍鍍忛�氶亾锛宒wAlarmRelateChannel[0]涓�1琛ㄧず绗�1涓�氶亾褰曞儚*/
	public int[] dwChannel = new int[MAX_CHANNUM];/*dwAlarmType涓�2鎴�3,6鏃讹紝琛ㄧず鍝釜閫氶亾锛宒wChannel[0]浣嶅搴旂1涓�氶亾*/
	public int[] dwDiskNumber = new int[MAX_DISKNUM];/*dwAlarmType涓�1,4,5鏃�,琛ㄧず鍝釜纭洏, dwDiskNumber[0]浣嶅搴旂1涓‖鐩�*/
}

public static class NET_DVR_ALARMINFO_EX extends Structure {//涓婁紶鎶ヨ淇℃伅(鏉窞绔炲ぉ瀹氬埗 2006-07-28)
	public int dwAlarmType;/*0-淇″彿閲忔姤璀�,1-纭洏婊�,2-淇″彿涓㈠け,3锛嶇Щ鍔ㄤ睛娴�,4锛嶇‖鐩樻湭鏍煎紡鍖�,5-璇诲啓纭洏鍑洪敊,6-閬尅鎶ヨ,7-鍒跺紡涓嶅尮閰�, 8-闈炴硶璁块棶*/
	public int dwAlarmInputNumber;/*鎶ヨ杈撳叆绔彛*/
	public int[] dwAlarmOutputNumber = new int[MAX_ALARMOUT];/*鎶ヨ杈撳叆绔彛瀵瑰簲鐨勮緭鍑虹鍙ｏ紝鍝竴浣嶄负1琛ㄧず瀵瑰簲鍝竴涓緭鍑�*/
	public int[] dwAlarmRelateChannel = new int[MAX_CHANNUM];/*鎶ヨ杈撳叆绔彛瀵瑰簲鐨勫綍鍍忛�氶亾锛屽摢涓�浣嶄负1琛ㄧず瀵瑰簲鍝竴璺綍鍍�,dwAlarmRelateChannel[0]瀵瑰簲绗�1涓�氶亾*/
	public int[] dwChannel = new int[MAX_CHANNUM];/*dwAlarmType涓�2鎴�3,6鏃讹紝琛ㄧず鍝釜閫氶亾锛宒wChannel[0]浣嶅搴旂0涓�氶亾*/
	public int[] dwDiskNumber = new int[MAX_DISKNUM];/*dwAlarmType涓�1,4,5鏃�,琛ㄧず鍝釜纭洏*/
	public byte[] sSerialNumber = new byte[SERIALNO_LEN];  //搴忓垪鍙�
	public byte[]  sRemoteAlarmIP = new byte[16];			//杩滅▼鎶ヨIP鍦板潃锛�
}

//////////////////////////////////////////////////////////////////////////////////////
//IPC鎺ュ叆鍙傛暟閰嶇疆
public static class NET_DVR_IPDEVINFO extends Structure {/* IP璁惧缁撴瀯 */
       public   int dwEnable;				    /* 璇P璁惧鏄惁鍚敤 */
       public   byte[] sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
       public   byte[] sPassword = new byte[PASSWD_LEN];	    /* 瀵嗙爜 */
       public   NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();			/* IP鍦板潃 */
       public   short wDVRPort;			 	    /* 绔彛鍙� */
       public   byte[] byres = new byte[34];				/* 淇濈暀 */
}

public static class NET_DVR_IPCHANINFO extends Structure {/* IP閫氶亾鍖归厤鍙傛暟 */
       public   byte byEnable;					/* 璇ラ�氶亾鏄惁鍚敤 */
       public  byte byIPID;					/* IP璁惧ID 鍙栧��1- MAX_IP_DEVICE */
       public  byte byChannel;					/* 閫氶亾鍙� */
       public   byte[] byres = new byte[33];					/* 淇濈暀 */
}

public static class NET_DVR_IPPARACFG extends Structure {/* IP鎺ュ叆閰嶇疆缁撴瀯 */
       public  int dwSize;			                            /* 缁撴瀯澶у皬 */
       public  NET_DVR_IPDEVINFO[]  struIPDevInfo = new NET_DVR_IPDEVINFO[MAX_IP_DEVICE];    /* IP璁惧 */
       public   byte[] byAnalogChanEnable = new byte[MAX_ANALOG_CHANNUM];        /* 妯℃嫙閫氶亾鏄惁鍚敤锛屼粠浣庡埌楂樿〃绀�1-32閫氶亾锛�0琛ㄧず鏃犳晥 1鏈夋晥 */
       public NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[MAX_IP_CHANNEL];	/* IP閫氶亾 */
}

public static class NET_DVR_IPALARMOUTINFO extends Structure {/* 鎶ヨ杈撳嚭鍙傛暟 */
       public  byte byIPID;					/* IP璁惧ID鍙栧��1- MAX_IP_DEVICE */
       public  byte byAlarmOut;				/* 鎶ヨ杈撳嚭鍙� */
       public  byte[] byRes = new byte[18];					/* 淇濈暀 */
}

public static class NET_DVR_IPALARMOUTCFG extends Structure {/* IP鎶ヨ杈撳嚭閰嶇疆缁撴瀯 */
       public  int dwSize;			                        /* 缁撴瀯澶у皬 */
       public  NET_DVR_IPALARMOUTINFO[] struIPAlarmOutInfo = new NET_DVR_IPALARMOUTINFO[MAX_IP_ALARMOUT];/* IP鎶ヨ杈撳嚭 */
}

public static class NET_DVR_IPALARMININFO extends Structure {/* 鎶ヨ杈撳叆鍙傛暟 */
       public  byte byIPID;					/* IP璁惧ID鍙栧��1- MAX_IP_DEVICE */
       public  byte byAlarmIn;					/* 鎶ヨ杈撳叆鍙� */
       public  byte[] byRes = new byte[18];					/* 淇濈暀 */
}

public static class NET_DVR_IPALARMINCFG extends Structure {/* IP鎶ヨ杈撳叆閰嶇疆缁撴瀯 */
       public  int dwSize;			                        /* 缁撴瀯澶у皬 */
       public NET_DVR_IPALARMININFO[] struIPAlarmInInfo = new NET_DVR_IPALARMININFO[MAX_IP_ALARMIN];/* IP鎶ヨ杈撳叆 */
}

public static class NET_DVR_IPALARMINFO extends Structure {//ipc alarm info
       public  NET_DVR_IPDEVINFO[]  struIPDevInfo = new NET_DVR_IPDEVINFO[MAX_IP_DEVICE];            /* IP璁惧 */
       public  byte[] byAnalogChanEnable = new byte[MAX_ANALOG_CHANNUM];                /* 妯℃嫙閫氶亾鏄惁鍚敤锛�0-鏈惎鐢� 1-鍚敤 */
       public  NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[MAX_IP_CHANNEL];	        /* IP閫氶亾 */
       public NET_DVR_IPALARMININFO[] struIPAlarmInInfo = new NET_DVR_IPALARMININFO[MAX_IP_ALARMIN];    /* IP鎶ヨ杈撳叆 */
       public NET_DVR_IPALARMOUTINFO[] struIPAlarmOutInfo = new NET_DVR_IPALARMOUTINFO[MAX_IP_ALARMOUT]; /* IP鎶ヨ杈撳嚭 */
}

public static class NET_DVR_SINGLE_HD extends Structure {//鏈湴纭洏淇℃伅閰嶇疆
       public int dwHDNo;         /*纭洏鍙�, 鍙栧��0~MAX_DISKNUM_V30-1*/
       public int dwCapacity;     /*纭洏瀹归噺(涓嶅彲璁剧疆)*/
       public int dwFreeSpace;    /*纭洏鍓╀綑绌洪棿(涓嶅彲璁剧疆)*/
       public int dwHdStatus;     /*纭洏鐘舵��(涓嶅彲璁剧疆) 0-姝ｅ父, 1-鏈牸寮忓寲, 2-閿欒, 3-SMART鐘舵��, 4-涓嶅尮閰�, 5-浼戠湢*/
       public byte  byHDAttr;       /*0-榛樿, 1-鍐椾綑; 2-鍙*/
       public byte[]  byRes1 = new byte[3];
       public  int dwHdGroup;      /*灞炰簬鍝釜鐩樼粍 1-MAX_HD_GROUP*/
       public  byte[]  byRes2 = new byte[120];
}

public static class NET_DVR_HDCFG extends Structure {
       public  int dwSize;
       public  int dwHDCount;          /*纭洏鏁�(涓嶅彲璁剧疆)*/
       public  NET_DVR_SINGLE_HD[] struHDInfo = new NET_DVR_SINGLE_HD[MAX_DISKNUM_V30];//纭洏鐩稿叧鎿嶄綔閮介渶瑕侀噸鍚墠鑳界敓鏁堬紱
}

public static class NET_DVR_SINGLE_HDGROUP extends Structure {//鏈湴鐩樼粍淇℃伅閰嶇疆
       public  int dwHDGroupNo;       /*鐩樼粍鍙�(涓嶅彲璁剧疆) 1-MAX_HD_GROUP*/
       public  byte[] byHDGroupChans = new byte[64]; /*鐩樼粍瀵瑰簲鐨勫綍鍍忛�氶亾, 0-琛ㄧず璇ラ�氶亾涓嶅綍璞″埌璇ョ洏缁勶紝1-琛ㄧず褰曡薄鍒拌鐩樼粍*/
       public  byte[] byRes = new byte[8];
}

public static class NET_DVR_HDGROUP_CFG extends Structure {
       public int dwSize;
       public  int dwHDGroupCount;        /*鐩樼粍鎬绘暟(涓嶅彲璁剧疆)*/
       public  NET_DVR_SINGLE_HDGROUP[] struHDGroupAttr = new NET_DVR_SINGLE_HDGROUP[MAX_HD_GROUP];//纭洏鐩稿叧鎿嶄綔閮介渶瑕侀噸鍚墠鑳界敓鏁堬紱
}

public static class NET_DVR_SCALECFG extends Structure {//閰嶇疆缂╂斁鍙傛暟鐨勭粨鏋�
       public  int dwSize;
       public  int dwMajorScale;    /* 涓绘樉绀� 0-涓嶇缉鏀撅紝1-缂╂斁*/
       public  int dwMinorScale;    /* 杈呮樉绀� 0-涓嶇缉鏀撅紝1-缂╂斁*/
       public  int[] dwRes = new int[2];
}

public static class NET_DVR_ALARMOUTCFG_V30 extends Structure {//DVR鎶ヨ杈撳嚭(9000鎵╁睍)
	public int dwSize;
	public byte[] sAlarmOutName = new byte[NAME_LEN];	/* 鍚嶇О */
	public int dwAlarmOutDelay;	/* 杈撳嚭淇濇寔鏃堕棿(-1涓烘棤闄愶紝鎵嬪姩鍏抽棴) */
	//0-5绉�,1-10绉�,2-30绉�,3-1鍒嗛挓,4-2鍒嗛挓,5-5鍒嗛挓,6-10鍒嗛挓,7-鎵嬪姩
	public NET_DVR_SCHEDTIMEWEEK[] struAlarmOutTime= new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];/* 鎶ヨ杈撳嚭婵�娲绘椂闂存 */
        public     byte[] byRes = new byte[16];
}

public static class NET_DVR_ALARMOUTCFG extends Structure {//DVR鎶ヨ杈撳嚭
	public int dwSize;
	public byte[] sAlarmOutName = new byte[NAME_LEN];	/* 鍚嶇О */
	public int dwAlarmOutDelay;	/* 杈撳嚭淇濇寔鏃堕棿(-1涓烘棤闄愶紝鎵嬪姩鍏抽棴) */
	//0-5绉�,1-10绉�,2-30绉�,3-1鍒嗛挓,4-2鍒嗛挓,5-5鍒嗛挓,6-10鍒嗛挓,7-鎵嬪姩
	public NET_DVR_SCHEDTIMEWEEK[] struAlarmOutTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];/* 鎶ヨ杈撳嚭婵�娲绘椂闂存 */
}

public static class NET_DVR_PREVIEWCFG_V30 extends Structure {//DVR鏈湴棰勮鍙傛暟(9000鎵╁睍)
       public  int dwSize;
       public  byte byPreviewNumber;//棰勮鏁扮洰,0-1鐢婚潰,1-4鐢婚潰,2-9鐢婚潰,3-16鐢婚潰, 4-6鐢婚潰, 5-8鐢婚潰, 0xff:鏈�澶х敾闈�
       public  byte byEnableAudio;//鏄惁澹伴煶棰勮,0-涓嶉瑙�,1-棰勮
       public  short wSwitchTime;//鍒囨崲鏃堕棿,0-涓嶅垏鎹�,1-5s,2-10s,3-20s,4-30s,5-60s,6-120s,7-300s
       public  byte[][] bySwitchSeq = new byte[MAX_PREVIEW_MODE][MAX_WINDOW_V30];//鍒囨崲椤哄簭,濡傛灉lSwitchSeq[i]涓� 0xff琛ㄧず涓嶇敤
       public  byte[] byRes = new byte[24];
}

public static class NET_DVR_PREVIEWCFG extends Structure {//DVR鏈湴棰勮鍙傛暟
	public int dwSize;
	public byte byPreviewNumber;//棰勮鏁扮洰,0-1鐢婚潰,1-4鐢婚潰,2-9鐢婚潰,3-16鐢婚潰,0xff:鏈�澶х敾闈�
	public byte byEnableAudio;//鏄惁澹伴煶棰勮,0-涓嶉瑙�,1-棰勮
	public short wSwitchTime;//鍒囨崲鏃堕棿,0-涓嶅垏鎹�,1-5s,2-10s,3-20s,4-30s,5-60s,6-120s,7-300s
	public byte[] bySwitchSeq = new byte[MAX_WINDOW];//鍒囨崲椤哄簭,濡傛灉lSwitchSeq[i]涓� 0xff琛ㄧず涓嶇敤
}

public static class NET_DVR_VGAPARA extends Structure {//DVR瑙嗛杈撳嚭
	public short wResolution;							/* 鍒嗚鲸鐜� */
	public short wFreq;									/* 鍒锋柊棰戠巼 */
	public int dwBrightness;							/* 浜害 */
}

/*
* MATRIX杈撳嚭鍙傛暟缁撴瀯
*/
public static class NET_DVR_MATRIXPARA_V30 extends Structure {
	public short[]	wOrder = new short[MAX_ANALOG_CHANNUM];		/* 棰勮椤哄簭, 0xff琛ㄧず鐩稿簲鐨勭獥鍙ｄ笉棰勮 */
	public short	wSwitchTime;				    /* 棰勮鍒囨崲鏃堕棿 */
	public byte[]	res = new byte[14];
}

public static class NET_DVR_MATRIXPARA extends Structure {
	public short wDisplayLogo;						/* 鏄剧ず瑙嗛閫氶亾鍙�(淇濈暀) */
	public short wDisplayOsd;						/* 鏄剧ず鏃堕棿(淇濈暀) */
}

public static class NET_DVR_VOOUT extends Structure {
	public byte byVideoFormat;						/* 杈撳嚭鍒跺紡,0-PAL,1-NTSC */
	public byte byMenuAlphaValue;					/* 鑿滃崟涓庤儗鏅浘璞″姣斿害 */
	public short wScreenSaveTime;					/* 灞忓箷淇濇姢鏃堕棿 0-浠庝笉,1-1鍒嗛挓,2-2鍒嗛挓,3-5鍒嗛挓,4-10鍒嗛挓,5-20鍒嗛挓,6-30鍒嗛挓 */
	public short wVOffset;							/* 瑙嗛杈撳嚭鍋忕Щ */
	public short wBrightness;						/* 瑙嗛杈撳嚭浜害 */
	public byte byStartMode;						/* 鍚姩鍚庤棰戣緭鍑烘ā寮�(0:鑿滃崟,1:棰勮)*/
	public byte byEnableScaler;                    /* 鏄惁鍚姩缂╂斁 (0-涓嶅惎鍔�, 1-鍚姩)*/
}

public static class NET_DVR_VIDEOOUT_V30 extends Structure {//DVR瑙嗛杈撳嚭(9000鎵╁睍)
	public int dwSize;
	public NET_DVR_VOOUT[] struVOOut = new NET_DVR_VOOUT[MAX_VIDEOOUT_V30];
	public NET_DVR_VGAPARA[] struVGAPara = new NET_DVR_VGAPARA[MAX_VGA_V30];	/* VGA鍙傛暟 */
	public NET_DVR_MATRIXPARA_V30[] struMatrixPara = new NET_DVR_MATRIXPARA_V30[MAX_MATRIXOUT];		/* MATRIX鍙傛暟 */
        public   byte[] byRes = new byte[16];
}

public static class NET_DVR_VIDEOOUT extends Structure {//DVR瑙嗛杈撳嚭
	public int dwSize;
	public NET_DVR_VOOUT[] struVOOut = new NET_DVR_VOOUT[MAX_VIDEOOUT];
	public NET_DVR_VGAPARA[] struVGAPara = new NET_DVR_VGAPARA[MAX_VGA];	/* VGA鍙傛暟 */
	public NET_DVR_MATRIXPARA struMatrixPara;		/* MATRIX鍙傛暟 */
}

public static class NET_DVR_USER_INFO_V30 extends Structure {//鍗曠敤鎴峰弬鏁�(瀛愮粨鏋�)(9000鎵╁睍)
	public byte[] sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
        public byte[] byLocalRight = new byte[MAX_RIGHT];	/* 鏈湴鏉冮檺 */
	/*鏁扮粍0: 鏈湴鎺у埗浜戝彴*/
	/*鏁扮粍1: 鏈湴鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 鏈湴鍥炴斁*/
	/*鏁扮粍3: 鏈湴璁剧疆鍙傛暟*/
	/*鏁扮粍4: 鏈湴鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 鏈湴楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
        /*鏁扮粍6: 鏈湴鏌ョ湅鍙傛暟 */
        /*鏁扮粍7: 鏈湴绠＄悊妯℃嫙鍜孖P camera */
        /*鏁扮粍8: 鏈湴澶囦唤 */
        /*鏁扮粍9: 鏈湴鍏虫満/閲嶅惎 */
	public byte[] byRemoteRight = new byte[MAX_RIGHT];/* 杩滅▼鏉冮檺 */
	/*鏁扮粍0: 杩滅▼鎺у埗浜戝彴*/
	/*鏁扮粍1: 杩滅▼鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 杩滅▼鍥炴斁 */
	/*鏁扮粍3: 杩滅▼璁剧疆鍙傛暟*/
	/*鏁扮粍4: 杩滅▼鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 杩滅▼楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
	/*鏁扮粍6: 杩滅▼鍙戣捣璇煶瀵硅*/
	/*鏁扮粍7: 杩滅▼棰勮*/
	/*鏁扮粍8: 杩滅▼璇锋眰鎶ヨ涓婁紶銆佹姤璀﹁緭鍑�*/
	/*鏁扮粍9: 杩滅▼鎺у埗锛屾湰鍦拌緭鍑�*/
	/*鏁扮粍10: 杩滅▼鎺у埗涓插彛*/
        /*鏁扮粍11: 杩滅▼鏌ョ湅鍙傛暟 */
        /*鏁扮粍12: 杩滅▼绠＄悊妯℃嫙鍜孖P camera */
        /*鏁扮粍13: 杩滅▼鍏虫満/閲嶅惎 */
	public byte[] byNetPreviewRight = new byte[MAX_CHANNUM_V30];		/* 杩滅▼鍙互棰勮鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byLocalPlaybackRight = new byte[MAX_CHANNUM_V30];	    /* 鏈湴鍙互鍥炴斁鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byNetPlaybackRight = new byte[MAX_CHANNUM_V30];	    /* 杩滅▼鍙互鍥炴斁鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byLocalRecordRight = new byte[MAX_CHANNUM_V30];		/* 鏈湴鍙互褰曞儚鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byNetRecordRight = new byte[MAX_CHANNUM_V30];		    /* 杩滅▼鍙互褰曞儚鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byLocalPTZRight = new byte[MAX_CHANNUM_V30];		    /* 鏈湴鍙互PTZ鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byNetPTZRight = new byte[MAX_CHANNUM_V30];			/* 杩滅▼鍙互PTZ鐨勯�氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public byte[] byLocalBackupRight = new byte[MAX_CHANNUM_V30];		/* 鏈湴澶囦唤鏉冮檺閫氶亾 0-鏈夋潈闄愶紝1-鏃犳潈闄�*/
	public NET_DVR_IPADDR struUserIP;		/* 鐢ㄦ埛IP鍦板潃(涓�0鏃惰〃绀哄厑璁镐换浣曞湴鍧�) */
	public byte[] byMACAddr = new byte[MACADDR_LEN];	/* 鐗╃悊鍦板潃 */
	public byte byPriority;				/* 浼樺厛绾э紝0xff-鏃狅紝0--浣庯紝1--涓紝2--楂� */
                                    /*
                                    鏃犫�︹�﹁〃绀轰笉鏀寔浼樺厛绾х殑璁剧疆
                                    浣庘�︹�﹂粯璁ゆ潈闄�:鍖呮嫭鏈湴鍜岃繙绋嬪洖鏀�,鏈湴鍜岃繙绋嬫煡鐪嬫棩蹇楀拰鐘舵��,鏈湴鍜岃繙绋嬪叧鏈�/閲嶅惎
                                    涓�︹�﹀寘鎷湰鍦板拰杩滅▼鎺у埗浜戝彴,鏈湴鍜岃繙绋嬫墜鍔ㄥ綍鍍�,鏈湴鍜岃繙绋嬪洖鏀�,璇煶瀵硅鍜岃繙绋嬮瑙�
                                          鏈湴澶囦唤,鏈湴/杩滅▼鍏虫満/閲嶅惎
                                    楂樷�︹�︾鐞嗗憳
                                    */
	public byte[] byRes = new byte[17];
}

public static class NET_DVR_USER_INFO_EX extends Structure {//鍗曠敤鎴峰弬鏁�(SDK_V15鎵╁睍)(瀛愮粨鏋�)
	public byte[] sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public int[] dwLocalRight = new int[MAX_RIGHT];	/* 鏉冮檺 */
	/*鏁扮粍0: 鏈湴鎺у埗浜戝彴*/
	/*鏁扮粍1: 鏈湴鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 鏈湴鍥炴斁*/
	/*鏁扮粍3: 鏈湴璁剧疆鍙傛暟*/
	/*鏁扮粍4: 鏈湴鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 鏈湴楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
	public int dwLocalPlaybackRight;		/* 鏈湴鍙互鍥炴斁鐨勯�氶亾 bit0 -- channel 1*/
	public int[] dwRemoteRight = new int[MAX_RIGHT];	/* 鏉冮檺 */
	/*鏁扮粍0: 杩滅▼鎺у埗浜戝彴*/
	/*鏁扮粍1: 杩滅▼鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 杩滅▼鍥炴斁 */
	/*鏁扮粍3: 杩滅▼璁剧疆鍙傛暟*/
	/*鏁扮粍4: 杩滅▼鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 杩滅▼楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
	/*鏁扮粍6: 杩滅▼鍙戣捣璇煶瀵硅*/
	/*鏁扮粍7: 杩滅▼棰勮*/
	/*鏁扮粍8: 杩滅▼璇锋眰鎶ヨ涓婁紶銆佹姤璀﹁緭鍑�*/
	/*鏁扮粍9: 杩滅▼鎺у埗锛屾湰鍦拌緭鍑�*/
	/*鏁扮粍10: 杩滅▼鎺у埗涓插彛*/
	public int dwNetPreviewRight;		/* 杩滅▼鍙互棰勮鐨勯�氶亾 bit0 -- channel 1*/
	public int dwNetPlaybackRight;		/* 杩滅▼鍙互鍥炴斁鐨勯�氶亾 bit0 -- channel 1*/
	public byte[] sUserIP = new byte[16];				/* 鐢ㄦ埛IP鍦板潃(涓�0鏃惰〃绀哄厑璁镐换浣曞湴鍧�) */
	public byte[] byMACAddr = new byte[MACADDR_LEN];	/* 鐗╃悊鍦板潃 */
}

public static class NET_DVR_USER_INFO extends Structure {//鍗曠敤鎴峰弬鏁�(瀛愮粨鏋�)
	public byte[] sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public int[] dwLocalRight = new int[MAX_RIGHT];	/* 鏉冮檺 */
	/*鏁扮粍0: 鏈湴鎺у埗浜戝彴*/
	/*鏁扮粍1: 鏈湴鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 鏈湴鍥炴斁*/
	/*鏁扮粍3: 鏈湴璁剧疆鍙傛暟*/
	/*鏁扮粍4: 鏈湴鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 鏈湴楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
	public int[] dwRemoteRight = new int[MAX_RIGHT];	/* 鏉冮檺 */
	/*鏁扮粍0: 杩滅▼鎺у埗浜戝彴*/
	/*鏁扮粍1: 杩滅▼鎵嬪姩褰曡薄*/
	/*鏁扮粍2: 杩滅▼鍥炴斁 */
	/*鏁扮粍3: 杩滅▼璁剧疆鍙傛暟*/
	/*鏁扮粍4: 杩滅▼鏌ョ湅鐘舵�併�佹棩蹇�*/
	/*鏁扮粍5: 杩滅▼楂樼骇鎿嶄綔(鍗囩骇锛屾牸寮忓寲锛岄噸鍚紝鍏虫満)*/
	/*鏁扮粍6: 杩滅▼鍙戣捣璇煶瀵硅*/
	/*鏁扮粍7: 杩滅▼棰勮*/
	/*鏁扮粍8: 杩滅▼璇锋眰鎶ヨ涓婁紶銆佹姤璀﹁緭鍑�*/
	/*鏁扮粍9: 杩滅▼鎺у埗锛屾湰鍦拌緭鍑�*/
	/*鏁扮粍10: 杩滅▼鎺у埗涓插彛*/
	public byte[] sUserIP = new byte[16];				/* 鐢ㄦ埛IP鍦板潃(涓�0鏃惰〃绀哄厑璁镐换浣曞湴鍧�) */
	public byte[] byMACAddr = new byte[MACADDR_LEN];	/* 鐗╃悊鍦板潃 */
}

public static class NET_DVR_USER_V30 extends Structure {//DVR鐢ㄦ埛鍙傛暟(9000鎵╁睍)
	public int dwSize;
	public NET_DVR_USER_INFO_V30[] struUser = new NET_DVR_USER_INFO_V30[MAX_USERNUM_V30];
}

public static class NET_DVR_USER_EX extends Structure {//DVR鐢ㄦ埛鍙傛暟(SDK_V15鎵╁睍)
	public int dwSize;
	public NET_DVR_USER_INFO_EX[] struUser = new NET_DVR_USER_INFO_EX[MAX_USERNUM];
}

public static class NET_DVR_USER extends Structure {//DVR鐢ㄦ埛鍙傛暟
	public int dwSize;
	public NET_DVR_USER_INFO[] struUser = new NET_DVR_USER_INFO[MAX_USERNUM];
}

public static class NET_DVR_EXCEPTION_V30 extends Structure {//DVR寮傚父鍙傛暟(9000鎵╁睍)
	public int dwSize;
	public NET_DVR_HANDLEEXCEPTION_V30[] struExceptionHandleType = new NET_DVR_HANDLEEXCEPTION_V30[MAX_EXCEPTIONNUM_V30];
	/*鏁扮粍0-鐩樻弧,1- 纭洏鍑洪敊,2-缃戠嚎鏂�,3-灞�鍩熺綉鍐匢P 鍦板潃鍐茬獊,4-闈炴硶璁块棶, 5-杈撳叆/杈撳嚭瑙嗛鍒跺紡涓嶅尮閰�, 6-琛岃溅瓒呴��(杞﹁浇涓撶敤), 7-瑙嗛淇″彿寮傚父(9000)*/
}

public static class NET_DVR_EXCEPTION extends Structure {//DVR寮傚父鍙傛暟
	public int dwSize;
	public NET_DVR_HANDLEEXCEPTION[] struExceptionHandleType = new NET_DVR_HANDLEEXCEPTION[MAX_EXCEPTIONNUM];
	/*鏁扮粍0-鐩樻弧,1- 纭洏鍑洪敊,2-缃戠嚎鏂�,3-灞�鍩熺綉鍐匢P 鍦板潃鍐茬獊,4-闈炴硶璁块棶, 5-杈撳叆/杈撳嚭瑙嗛鍒跺紡涓嶅尮閰�, 6-琛岃溅瓒呴��(杞﹁浇涓撶敤)*/
}

public static class NET_DVR_CHANNELSTATE_V30 extends Structure {//閫氶亾鐘舵��(9000鎵╁睍)
	public byte byRecordStatic; //閫氶亾鏄惁鍦ㄥ綍鍍�,0-涓嶅綍鍍�,1-褰曞儚
	public byte bySignalStatic; //杩炴帴鐨勪俊鍙风姸鎬�,0-姝ｅ父,1-淇″彿涓㈠け
	public byte byHardwareStatic;//閫氶亾纭欢鐘舵��,0-姝ｅ父,1-寮傚父,渚嬪DSP姝绘帀
	public byte reservedData;		//淇濈暀
	public int dwBitRate;//瀹為檯鐮佺巼
	public int dwLinkNum;//瀹㈡埛绔繛鎺ョ殑涓暟
	public NET_DVR_IPADDR[] struClientIP = new NET_DVR_IPADDR[MAX_LINK];//瀹㈡埛绔殑IP鍦板潃
        public  int dwIPLinkNum;//濡傛灉璇ラ�氶亾涓篒P鎺ュ叆锛岄偅涔堣〃绀篒P鎺ュ叆褰撳墠鐨勮繛鎺ユ暟
        public  byte[] byRes = new byte[12];
}

public static class NET_DVR_CHANNELSTATE extends Structure {//閫氶亾鐘舵��
	public byte byRecordStatic; //閫氶亾鏄惁鍦ㄥ綍鍍�,0-涓嶅綍鍍�,1-褰曞儚
	public byte bySignalStatic; //杩炴帴鐨勪俊鍙风姸鎬�,0-姝ｅ父,1-淇″彿涓㈠け
	public byte byHardwareStatic;//閫氶亾纭欢鐘舵��,0-姝ｅ父,1-寮傚父,渚嬪DSP姝绘帀
	public byte reservedData;		//淇濈暀
	public int dwBitRate;//瀹為檯鐮佺巼
	public int dwLinkNum;//瀹㈡埛绔繛鎺ョ殑涓暟
	public int[] dwClientIP = new int[MAX_LINK];//瀹㈡埛绔殑IP鍦板潃
}

public static class NET_DVR_DISKSTATE extends Structure {//纭洏鐘舵��
	public int dwVolume;//纭洏鐨勫閲�
	public int dwFreeSpace;//纭洏鐨勫墿浣欑┖闂�
	public int dwHardDiskStatic; //纭洏鐨勭姸鎬�,鎸変綅:1-浼戠湢,2-涓嶆甯�,3-浼戠湢纭洏鍑洪敊
}

public static class NET_DVR_WORKSTATE_V30 extends Structure {//DVR宸ヤ綔鐘舵��(9000鎵╁睍)
	public int dwDeviceStatic; 	//璁惧鐨勭姸鎬�,0-姝ｅ父,1-CPU鍗犵敤鐜囧お楂�,瓒呰繃85%,2-纭欢閿欒,渚嬪涓插彛姝绘帀
	public NET_DVR_DISKSTATE[]  struHardDiskStatic = new NET_DVR_DISKSTATE[MAX_DISKNUM_V30];
	public NET_DVR_CHANNELSTATE_V30[] struChanStatic = new NET_DVR_CHANNELSTATE_V30[MAX_CHANNUM_V30];//閫氶亾鐨勭姸鎬�
	public byte[]  byAlarmInStatic = new byte[MAX_ALARMIN_V30]; //鎶ヨ绔彛鐨勭姸鎬�,0-娌℃湁鎶ヨ,1-鏈夋姤璀�
	public byte[]  byAlarmOutStatic = new byte[MAX_ALARMOUT_V30]; //鎶ヨ杈撳嚭绔彛鐨勭姸鎬�,0-娌℃湁杈撳嚭,1-鏈夋姤璀﹁緭鍑�
	public int  dwLocalDisplay;//鏈湴鏄剧ず鐘舵��,0-姝ｅ父,1-涓嶆甯�
        public  byte [] byAudioChanStatus = new byte[MAX_AUDIO_V30];//琛ㄧず璇煶閫氶亾鐨勭姸鎬� 0-鏈娇鐢紝1-浣跨敤涓�, 0xff鏃犳晥
        public  byte[]  byRes = new byte[10];
}

public static class NET_DVR_WORKSTATE extends Structure {//DVR宸ヤ綔鐘舵��
	public int dwDeviceStatic; 	//璁惧鐨勭姸鎬�,0-姝ｅ父,1-CPU鍗犵敤鐜囧お楂�,瓒呰繃85%,2-纭欢閿欒,渚嬪涓插彛姝绘帀
	public NET_DVR_DISKSTATE[]  struHardDiskStatic = new NET_DVR_DISKSTATE[MAX_DISKNUM];
	public NET_DVR_CHANNELSTATE[] struChanStatic = new NET_DVR_CHANNELSTATE[MAX_CHANNUM];//閫氶亾鐨勭姸鎬�
	public byte[]  byAlarmInStatic = new byte[MAX_ALARMIN]; //鎶ヨ绔彛鐨勭姸鎬�,0-娌℃湁鎶ヨ,1-鏈夋姤璀�
	public byte[]  byAlarmOutStatic = new byte[MAX_ALARMOUT]; //鎶ヨ杈撳嚭绔彛鐨勭姸鎬�,0-娌℃湁杈撳嚭,1-鏈夋姤璀﹁緭鍑�
	public int  dwLocalDisplay;//鏈湴鏄剧ず鐘舵��,0-姝ｅ父,1-涓嶆甯�
}

public static class NET_DVR_LOG_V30 extends Structure {//鏃ュ織淇℃伅(9000鎵╁睍)
	public NET_DVR_TIME strLogTime;
	public int	dwMajorType;	//涓荤被鍨� 1-鎶ヨ; 2-寮傚父; 3-鎿嶄綔; 0xff-鍏ㄩ儴
	public int	dwMinorType;//娆＄被鍨� 0-鍏ㄩ儴;
	public byte[]	sPanelUser = new byte[MAX_NAMELEN]; //鎿嶄綔闈㈡澘鐨勭敤鎴峰悕
	public byte[]	sNetUser = new byte[MAX_NAMELEN];//缃戠粶鎿嶄綔鐨勭敤鎴峰悕
	public NET_DVR_IPADDR	struRemoteHostAddr;//杩滅▼涓绘満鍦板潃
	public int	dwParaType;//鍙傛暟绫诲瀷
	public int	dwChannel;//閫氶亾鍙�
	public int	dwDiskNumber;//纭洏鍙�
	public int	dwAlarmInPort;//鎶ヨ杈撳叆绔彛
	public int	dwAlarmOutPort;//鎶ヨ杈撳嚭绔彛
       public  int     dwInfoLen;
       public  byte[]    sInfo = new byte[LOG_INFO_LEN];
}

//鏃ュ織淇℃伅
public static class NET_DVR_LOG extends Structure {
	public NET_DVR_TIME strLogTime;
	public int	dwMajorType;	//涓荤被鍨� 1-鎶ヨ; 2-寮傚父; 3-鎿嶄綔; 0xff-鍏ㄩ儴
	public int	dwMinorType;//娆＄被鍨� 0-鍏ㄩ儴;
	public byte[]	sPanelUser = new byte[MAX_NAMELEN]; //鎿嶄綔闈㈡澘鐨勭敤鎴峰悕
	public byte[]	sNetUser = new byte[MAX_NAMELEN];//缃戠粶鎿嶄綔鐨勭敤鎴峰悕
	public byte[]	sRemoteHostAddr = new byte[16];//杩滅▼涓绘満鍦板潃
	public int	dwParaType;//鍙傛暟绫诲瀷
	public int	dwChannel;//閫氶亾鍙�
	public int	dwDiskNumber;//纭洏鍙�
	public int	dwAlarmInPort;//鎶ヨ杈撳叆绔彛
	public int	dwAlarmOutPort;//鎶ヨ杈撳嚭绔彛
}

/************************DVR鏃ュ織 end***************************/
public static class NET_DVR_ALARMOUTSTATUS_V30 extends Structure {//鎶ヨ杈撳嚭鐘舵��(9000鎵╁睍)
	public byte[] Output = new byte[MAX_ALARMOUT_V30];
}

public static class NET_DVR_ALARMOUTSTATUS extends Structure {//鎶ヨ杈撳嚭鐘舵��
	public byte[] Output = new byte[MAX_ALARMOUT];
}

public static class NET_DVR_TRADEINFO extends Structure {//浜ゆ槗淇℃伅
	public short m_Year;
	public short m_Month;
	public short m_Day;
	public short m_Hour;
	public short m_Minute;
	public short m_Second;
	public byte[] DeviceName = new byte[24];	//璁惧鍚嶇О
	public int dwChannelNumer;	//閫氶亾鍙�
	public byte[] CardNumber = new byte[32];	//鍗″彿
	public byte[] cTradeType = new byte[12];	//浜ゆ槗绫诲瀷
	public int dwCash;			//浜ゆ槗閲戦
}

public static class NET_DVR_FRAMETYPECODE extends Structure {/*甯ф牸寮�*/
	public byte[] code = new byte[12];		/* 浠ｇ爜 */
}

public static class NET_DVR_FRAMEFORMAT_V30 extends Structure {//ATM鍙傛暟(9000鎵╁睍)
	public int	dwSize;
	public NET_DVR_IPADDR	struATMIP;               	/* ATM IP鍦板潃 */
	public int	dwATMType;							/* ATM绫诲瀷 */
	public int	dwInputMode;						/* 杈撳叆鏂瑰紡	0-缃戠粶渚﹀惉 1-缃戠粶鎺ユ敹 2-涓插彛鐩存帴杈撳叆 3-涓插彛ATM鍛戒护杈撳叆*/
	public int	dwFrameSignBeginPos;				/* 鎶ユ枃鏍囧織浣嶇殑璧峰浣嶇疆*/
	public int	dwFrameSignLength;					/* 鎶ユ枃鏍囧織浣嶇殑闀垮害 */
	public byte[]	byFrameSignContent = new byte[12];				/* 鎶ユ枃鏍囧織浣嶇殑鍐呭 */
	public int	dwCardLengthInfoBeginPos;			/* 鍗″彿闀垮害淇℃伅鐨勮捣濮嬩綅缃� */
	public int	dwCardLengthInfoLength;				/* 鍗″彿闀垮害淇℃伅鐨勯暱搴� */
	public int	dwCardNumberInfoBeginPos;			/* 鍗″彿淇℃伅鐨勮捣濮嬩綅缃� */
	public int	dwCardNumberInfoLength;				/* 鍗″彿淇℃伅鐨勯暱搴� */
	public int	dwBusinessTypeBeginPos;				/* 浜ゆ槗绫诲瀷鐨勮捣濮嬩綅缃� */
	public int	dwBusinessTypeLength;				/* 浜ゆ槗绫诲瀷鐨勯暱搴� */
	public NET_DVR_FRAMETYPECODE[]	frameTypeCode = new NET_DVR_FRAMETYPECODE[10];	/* 绫诲瀷 */
	public short	wATMPort;							/* 鍗″彿鎹曟崏绔彛鍙�(缃戠粶鍗忚鏂瑰紡) (淇濈暀)0xffff琛ㄧず璇ュ�兼棤鏁�*/
	public short	wProtocolType;						/* 缃戠粶鍗忚绫诲瀷(淇濈暀) 0xffff琛ㄧず璇ュ�兼棤鏁�*/
        public byte[]   byRes = new byte[24];
}

public static class NET_DVR_FRAMEFORMAT extends Structure {//ATM鍙傛暟
	public int dwSize;
	public byte[] sATMIP = new byte[16];						/* ATM IP鍦板潃 */
	public int dwATMType;						/* ATM绫诲瀷 */
	public int dwInputMode;						/* 杈撳叆鏂瑰紡	0-缃戠粶渚﹀惉 1-缃戠粶鎺ユ敹 2-涓插彛鐩存帴杈撳叆 3-涓插彛ATM鍛戒护杈撳叆*/
	public int dwFrameSignBeginPos;              /* 鎶ユ枃鏍囧織浣嶇殑璧峰浣嶇疆*/
	public int dwFrameSignLength;				/* 鎶ユ枃鏍囧織浣嶇殑闀垮害 */
	public byte[]  byFrameSignContent = new byte[12];			/* 鎶ユ枃鏍囧織浣嶇殑鍐呭 */
	public int dwCardLengthInfoBeginPos;			/* 鍗″彿闀垮害淇℃伅鐨勮捣濮嬩綅缃� */
	public int dwCardLengthInfoLength;			/* 鍗″彿闀垮害淇℃伅鐨勯暱搴� */
	public int dwCardNumberInfoBeginPos;			/* 鍗″彿淇℃伅鐨勮捣濮嬩綅缃� */
	public int dwCardNumberInfoLength;			/* 鍗″彿淇℃伅鐨勯暱搴� */
	public int dwBusinessTypeBeginPos;           /* 浜ゆ槗绫诲瀷鐨勮捣濮嬩綅缃� */
	public int dwBusinessTypeLength;				/* 浜ゆ槗绫诲瀷鐨勯暱搴� */
	public NET_DVR_FRAMETYPECODE[] frameTypeCode = new NET_DVR_FRAMETYPECODE[10];/* 绫诲瀷 */
}

public static class NET_DVR_FTPTYPECODE extends Structure {
	public byte[] sFtpType = new byte[32];     /*瀹㈡埛瀹氫箟鐨勬搷浣滅被鍨�*/
	public byte[] sFtpCode = new byte[8];      /*瀹㈡埛瀹氫箟鐨勬搷浣滅被鍨嬬殑瀵瑰簲鐨勭爜*/
}

public static class NET_DVR_FRAMEFORMAT_EX extends Structure {//ATM鍙傛暟娣诲姞FTP涓婁紶鍙傛暟, 淇勭綏鏂摱琛屽畾鍒�, 2006-11-17
	public int dwSize;
	public byte[] sATMIP = new byte[16];						/* ATM IP鍦板潃 */
	public int dwATMType;						/* ATM绫诲瀷 */
	public int dwInputMode;						/* 杈撳叆鏂瑰紡	0-缃戠粶渚﹀惉 1-缃戠粶鎺ユ敹 2-涓插彛鐩存帴杈撳叆 3-涓插彛ATM鍛戒护杈撳叆*/
	public int dwFrameSignBeginPos;              /* 鎶ユ枃鏍囧織浣嶇殑璧峰浣嶇疆*/
	public int dwFrameSignLength;				/* 鎶ユ枃鏍囧織浣嶇殑闀垮害 */
	public byte[]  byFrameSignContent = new byte[12];			/* 鎶ユ枃鏍囧織浣嶇殑鍐呭 */
	public int dwCardLengthInfoBeginPos;			/* 鍗″彿闀垮害淇℃伅鐨勮捣濮嬩綅缃� */
	public int dwCardLengthInfoLength;			/* 鍗″彿闀垮害淇℃伅鐨勯暱搴� */
	public int dwCardNumberInfoBeginPos;			/* 鍗″彿淇℃伅鐨勮捣濮嬩綅缃� */
	public int dwCardNumberInfoLength;			/* 鍗″彿淇℃伅鐨勯暱搴� */
	public int dwBusinessTypeBeginPos;           /* 浜ゆ槗绫诲瀷鐨勮捣濮嬩綅缃� */
	public int dwBusinessTypeLength;				/* 浜ゆ槗绫诲瀷鐨勯暱搴� */
	public NET_DVR_FRAMETYPECODE[] frameTypeCode = new NET_DVR_FRAMETYPECODE[10];/* 绫诲瀷 */
	public byte[] sFTPIP = new byte[16];						/* FTP IP */
	public byte[] byFtpUsername = new byte[NAME_LEN];			/* 鐢ㄦ埛鍚� */
	public byte[] byFtpPasswd = new byte[PASSWD_LEN];			/* 瀵嗙爜 */
	public byte[] sDirName = new byte[NAME_LEN];				/*鏈嶅姟鍣ㄧ洰褰曞悕*/
	public int dwATMSrvType;						/*ATM鏈嶅姟鍣ㄧ被鍨嬶紝0--wincor 锛�1--diebold*/
	public int dwTimeSpace;						/*鍙栧�间负1.2.3.4.5.10*/
	public NET_DVR_FTPTYPECODE[] sFtpTypeCodeOp = new NET_DVR_FTPTYPECODE[300];    /*鏂板姞鐨�*/
	public int dwADPlay;    /* 1 琛ㄧず鍦ㄦ挱鏀惧箍鍛婏紝0 琛ㄧず娌℃湁鎾斁骞垮憡*/
	public int dwNewPort;  //绔彛
}
/****************************ATM(end)***************************/

/*****************************DS-6001D/F(begin)***************************/
//DS-6001D Decoder
public static class NET_DVR_DECODERINFO extends Structure {
	public byte[] byEncoderIP = new byte[16];		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒IP
	public byte[] byEncoderUser = new byte[16];		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勭敤鎴峰悕
	public byte[] byEncoderPasswd = new byte[16];	//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勫瘑鐮�
	public byte bySendMode;			//瑙ｇ爜璁惧杩炴帴鏈嶅姟鍣ㄧ殑杩炴帴妯″紡
	public byte byEncoderChannel;		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勯�氶亾鍙�
	public short wEncoderPort;			//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勭鍙ｅ彿
	public byte[] reservedData = new byte[4];		//淇濈暀
}

public static class NET_DVR_DECODERSTATE extends Structure {
	public byte[] byEncoderIP = new byte[16];		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒IP
	public byte[] byEncoderUser = new byte[16];		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勭敤鎴峰悕
	public byte[] byEncoderPasswd = new byte[16];	//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勫瘑鐮�
	public byte byEncoderChannel;		//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勯�氶亾鍙�
	public byte bySendMode;			//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勮繛鎺ユā寮�
	public short wEncoderPort;			//瑙ｇ爜璁惧杩炴帴鐨勬湇鍔″櫒鐨勭鍙ｅ彿
	public int dwConnectState;		//瑙ｇ爜璁惧杩炴帴鏈嶅姟鍣ㄧ殑鐘舵��
	public byte[] reservedData = new byte[4];		//淇濈暀
}

public static class NET_DVR_DECCHANINFO extends Structure {
	public byte[] sDVRIP = new byte[16];				/* DVR IP鍦板潃 */
	public short wDVRPort;			 		/* 绔彛鍙� */
	public byte[] sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[] sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public byte byChannel;					/* 閫氶亾鍙� */
	public byte byLinkMode;				/* 杩炴帴妯″紡 */
	public byte byLinkType;				/* 杩炴帴绫诲瀷 0锛嶄富鐮佹祦 1锛嶅瓙鐮佹祦 */
}

public static class NET_DVR_DECINFO extends Structure {/*姣忎釜瑙ｇ爜閫氶亾鐨勯厤缃�*/
	public byte	byPoolChans;			/*姣忚矾瑙ｇ爜閫氶亾涓婄殑寰幆閫氶亾鏁伴噺, 鏈�澶�4閫氶亾 0琛ㄧず娌℃湁瑙ｇ爜*/
	public NET_DVR_DECCHANINFO[] struchanConInfo = new NET_DVR_DECCHANINFO[MAX_DECPOOLNUM];
	public byte	byEnablePoll;			/*鏄惁杞贰 0-鍚� 1-鏄�*/
	public byte	byPoolTime;				/*杞贰鏃堕棿 0-淇濈暀 1-10绉� 2-15绉� 3-20绉� 4-30绉� 5-45绉� 6-1鍒嗛挓 7-2鍒嗛挓 8-5鍒嗛挓 */
}

public static class NET_DVR_DECCFG extends Structure {/*鏁翠釜璁惧瑙ｇ爜閰嶇疆*/
	public int	dwSize;
	public int	dwDecChanNum; 		/*瑙ｇ爜閫氶亾鐨勬暟閲�*/
	public NET_DVR_DECINFO[] struDecInfo = new NET_DVR_DECINFO[MAX_DECNUM];
}

//2005-08-01
public static class NET_DVR_PORTINFO extends Structure {/* 瑙ｇ爜璁惧閫忔槑閫氶亾璁剧疆 */
	public int dwEnableTransPort;	/* 鏄惁鍚姩閫忔槑閫氶亾 0锛嶄笉鍚敤 1锛嶅惎鐢�*/
	public byte[] sDecoderIP = new byte[16];		/* DVR IP鍦板潃 */
	public short wDecoderPort;			/* 绔彛鍙� */
	public short wDVRTransPort;			/* 閰嶇疆鍓嶇DVR鏄粠485/232杈撳嚭锛�1琛ㄧず232涓插彛,2琛ㄧず485涓插彛 */
	public byte[] cReserve = new byte[4];
}

public static class NET_DVR_PORTCFG extends Structure {
	public int dwSize;
	public NET_DVR_PORTINFO[] struTransPortInfo = new NET_DVR_PORTINFO[MAX_TRANSPARENTNUM]; /* 鏁扮粍0琛ㄧず232 鏁扮粍1琛ㄧず485 */
}

/*https://jna.dev.java.net/javadoc/com/sun/jna/Union.html#setType(java.lang.Class)  see how to use the JNA Union*/
public static class NET_DVR_PLAYREMOTEFILE extends Structure {/* 鎺у埗缃戠粶鏂囦欢鍥炴斁 */
	public int dwSize;
	public byte[] sDecoderIP = new byte[16];		/* DVR IP鍦板潃 */
	public short wDecoderPort;			/* 绔彛鍙� */
	public short wLoadMode;				/* 鍥炴斁涓嬭浇妯″紡 1锛嶆寜鍚嶅瓧 2锛嶆寜鏃堕棿 */
        public   byte[] byFile = new byte[100];
        public static class mode_size extends Union
	{
		public byte[] byFile = new byte[100];		// 鍥炴斁鐨勬枃浠跺悕
		public static class bytime extends Structure
		{
			public int dwChannel;
			public byte[] sUserName = new byte[NAME_LEN];	//璇锋眰瑙嗛鐢ㄦ埛鍚�
			public byte[] sPassword = new byte[PASSWD_LEN];	// 瀵嗙爜
			public NET_DVR_TIME struStartTime;	//鎸夋椂闂村洖鏀剧殑寮�濮嬫椂闂�
			public NET_DVR_TIME struStopTime;	// 鎸夋椂闂村洖鏀剧殑缁撴潫鏃堕棿
		}
	}
}

public static class NET_DVR_DECCHANSTATUS extends Structure {/*褰撳墠璁惧瑙ｇ爜杩炴帴鐘舵��*/
	public int dwWorkType;		/*宸ヤ綔鏂瑰紡锛�1锛氳疆宸°��2锛氬姩鎬佽繛鎺ヨВ鐮併��3锛氭枃浠跺洖鏀句笅杞� 4锛氭寜鏃堕棿鍥炴斁涓嬭浇*/
	public byte[] sDVRIP = new byte[16];		/*杩炴帴鐨勮澶噄p*/
	public short wDVRPort;			/*杩炴帴绔彛鍙�*/
	public byte byChannel;			/* 閫氶亾鍙� */
	public byte byLinkMode;		/* 杩炴帴妯″紡 */
	public int	dwLinkType;		/*杩炴帴绫诲瀷 0锛嶄富鐮佹祦 1锛嶅瓙鐮佹祦*/
	public byte[] sUserName = new byte[NAME_LEN];	/*璇锋眰瑙嗛鐢ㄦ埛鍚�*/
	public byte[] sPassword = new byte[PASSWD_LEN];	/* 瀵嗙爜 */
	public byte[] cReserve = new byte[52];
        public static class objectInfo extends Union
	{
		public static class userInfo extends Structure
		{
			public byte[] sUserName = new byte[NAME_LEN];	//璇锋眰瑙嗛鐢ㄦ埛鍚�
			public byte[] sPassword = new byte[PASSWD_LEN];	// 瀵嗙爜
			public byte[] cReserve = new byte[52];
		}
		public static class fileInfo extends Structure
		{
			public byte[]  fileName = new byte[100];
		}
		public static class timeInfo extends Structure
		{
			public int	dwChannel;
			public byte[] sUserName = new byte[NAME_LEN];	//璇锋眰瑙嗛鐢ㄦ埛鍚�
			public byte[] sPassword = new byte[PASSWD_LEN];	// 瀵嗙爜
			public NET_DVR_TIME struStartTime;		// 鎸夋椂闂村洖鏀剧殑寮�濮嬫椂闂�
			public NET_DVR_TIME struStopTime;		//鎸夋椂闂村洖鏀剧殑缁撴潫鏃堕棿
		}
	}
}

public static class NET_DVR_DECSTATUS extends Structure {
	public int   dwSize;
	public NET_DVR_DECCHANSTATUS[] struDecState = new NET_DVR_DECCHANSTATUS[MAX_DECNUM];
}
/*****************************DS-6001D/F(end)***************************/

public static class NET_DVR_SHOWSTRINGINFO extends Structure {//鍗曞瓧绗﹀弬鏁�(瀛愮粨鏋�)
	public short wShowString;				// 棰勮鐨勫浘璞′笂鏄惁鏄剧ず瀛楃,0-涓嶆樉绀�,1-鏄剧ず 鍖哄煙澶у皬704*576,鍗曚釜瀛楃鐨勫ぇ灏忎负32*32
	public short wStringSize;				/* 璇ヨ瀛楃鐨勯暱搴︼紝涓嶈兘澶т簬44涓瓧绗� */
	public short wShowStringTopLeftX;		/* 瀛楃鏄剧ず浣嶇疆鐨剎鍧愭爣 */
	public short wShowStringTopLeftY;		/* 瀛楃鍚嶇О鏄剧ず浣嶇疆鐨剏鍧愭爣 */
	public byte[] sString = new byte[44];				/* 瑕佹樉绀虹殑瀛楃鍐呭 */
}

//鍙犲姞瀛楃(9000鎵╁睍)
public static class NET_DVR_SHOWSTRING_V30 extends Structure {
	public int dwSize;
	public NET_DVR_SHOWSTRINGINFO[] struStringInfo = new NET_DVR_SHOWSTRINGINFO[MAX_STRINGNUM_V30];				/* 瑕佹樉绀虹殑瀛楃鍐呭 */
}

//鍙犲姞瀛楃鎵╁睍(8鏉″瓧绗�)
public static class NET_DVR_SHOWSTRING_EX extends Structure {
	public int dwSize;
	public NET_DVR_SHOWSTRINGINFO[] struStringInfo = new NET_DVR_SHOWSTRINGINFO[MAX_STRINGNUM_EX];				/* 瑕佹樉绀虹殑瀛楃鍐呭 */
}

//鍙犲姞瀛楃
public static class NET_DVR_SHOWSTRING extends Structure {
	public int dwSize;
	public NET_DVR_SHOWSTRINGINFO[] struStringInfo = new NET_DVR_SHOWSTRINGINFO[MAX_STRINGNUM];				/* 瑕佹樉绀虹殑瀛楃鍐呭 */
}

/****************************DS9000鏂板缁撴瀯(begin)******************************/

/*
EMAIL鍙傛暟缁撴瀯
*/
    public static class NET_DVR_SENDER extends Structure {
       public  byte[] sName = new byte[NAME_LEN];				/* 鍙戜欢浜哄鍚� */
       public   byte[] sAddress = new byte[MAX_EMAIL_ADDR_LEN];		/* 鍙戜欢浜哄湴鍧� */
    }
       public static class NET_DVRRECEIVER extends Structure {
       public  byte[]	sName = new byte[NAME_LEN];				/* 鏀朵欢浜哄鍚� */
       public  byte[]	sAddress = new byte[MAX_EMAIL_ADDR_LEN];		/* 鏀朵欢浜哄湴鍧� */
    }

    public static class NET_DVR_EMAILCFG_V30 extends Structure {
	public int		dwSize;
	public byte[]		sAccount = new byte[NAME_LEN];				/* 璐﹀彿*/
	public byte[]		sPassword = new byte[MAX_EMAIL_PWD_LEN];			/*瀵嗙爜 */
        public   NET_DVR_SENDER struSender;
	public byte[]		sSmtpServer  = new byte[MAX_EMAIL_ADDR_LEN];	/* smtp鏈嶅姟鍣� */
	public byte[]		sPop3Server = new byte[MAX_EMAIL_ADDR_LEN];	/* pop3鏈嶅姟鍣� */
	public NET_DVRRECEIVER[] struReceiver = new NET_DVRRECEIVER[3];							/* 鏈�澶氬彲浠ヨ缃�3涓敹浠朵汉 */
	public byte		byAttachment;					/* 鏄惁甯﹂檮浠� */
	public byte		bySmtpServerVerify;				/* 鍙戦�佹湇鍔″櫒瑕佹眰韬唤楠岃瘉 */
        public  byte        byMailInterval;                 /* mail interval */
        public  byte[]        res = new byte[77];
}

/*
DVR瀹炵幇宸¤埅鏁版嵁缁撴瀯
*/
    public static class NET_DVR_CRUISE_PARA extends Structure {
	public int 	dwSize;
	public byte[]	byPresetNo = new byte[CRUISE_MAX_PRESET_NUMS];		/* 棰勭疆鐐瑰彿 */
	public byte[] 	byCruiseSpeed = new byte[CRUISE_MAX_PRESET_NUMS];	/* 宸¤埅閫熷害 */
	public short[]	wDwellTime = new short[CRUISE_MAX_PRESET_NUMS];		/* 鍋滅暀鏃堕棿 */
	public byte[]	byEnableThisCruise;						/* 鏄惁鍚敤 */
	public byte[]	res = new byte[15];
}

    /****************************DS9000鏂板缁撴瀯(end)******************************/

//鏃堕棿鐐�
    public static class NET_DVR_TIMEPOINT extends Structure {
	public int dwMonth;		//鏈� 0-11琛ㄧず1-12涓湀
	public int dwWeekNo;		//绗嚑鍛� 0锛嶇1鍛� 1锛嶇2鍛� 2锛嶇3鍛� 3锛嶇4鍛� 4锛嶆渶鍚庝竴鍛�
	public int dwWeekDate;	//鏄熸湡鍑� 0锛嶆槦鏈熸棩 1锛嶆槦鏈熶竴 2锛嶆槦鏈熶簩 3锛嶆槦鏈熶笁 4锛嶆槦鏈熷洓 5锛嶆槦鏈熶簲 6锛嶆槦鏈熷叚
	public int dwHour;		//灏忔椂	寮�濮嬫椂闂�0锛�23 缁撴潫鏃堕棿1锛�23
	public int dwMin;		//鍒�	0锛�59
}

//澶忎护鏃跺弬鏁�
    public static class NET_DVR_ZONEANDDST extends Structure {
	public int dwSize;
	public byte[] byRes1 = new byte[16];			//淇濈暀
	public int dwEnableDST;		//鏄惁鍚敤澶忔椂鍒� 0锛嶄笉鍚敤 1锛嶅惎鐢�
	public byte byDSTBias;	//澶忎护鏃跺亸绉诲�硷紝30min, 60min, 90min, 120min, 浠ュ垎閽熻锛屼紶閫掑師濮嬫暟鍊�
	public byte[] byRes2 = new byte[3];
	public NET_DVR_TIMEPOINT struBeginPoint;	//澶忔椂鍒跺紑濮嬫椂闂�
	public NET_DVR_TIMEPOINT struEndPoint;	//澶忔椂鍒跺仠姝㈡椂闂�
}

//鍥剧墖璐ㄩ噺
    public static class NET_DVR_JPEGPARA extends Structure {
	/*娉ㄦ剰锛氬綋鍥惧儚鍘嬬缉鍒嗚鲸鐜囦负VGA鏃讹紝鏀寔0=CIF, 1=QCIF, 2=D1鎶撳浘锛�
	褰撳垎杈ㄧ巼涓�3=UXGA(1600x1200), 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA,7=XVGA, 8=HD900p
	浠呮敮鎸佸綋鍓嶅垎杈ㄧ巼鐨勬姄鍥�*/
	public short	wPicSize;				/* 0=CIF, 1=QCIF, 2=D1 3=UXGA(1600x1200), 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA*/
	public short	wPicQuality;			/* 鍥剧墖璐ㄩ噺绯绘暟 0-鏈�濂� 1-杈冨ソ 2-涓�鑸� */
    }

/* aux video out parameter */
//杈呭姪杈撳嚭鍙傛暟閰嶇疆
    public static class NET_DVR_AUXOUTCFG extends Structure {
	public int dwSize;
	public int dwAlarmOutChan;                       /* 閫夋嫨鎶ヨ寮瑰嚭澶ф姤璀﹂�氶亾鍒囨崲鏃堕棿锛�1鐢婚潰鐨勮緭鍑洪�氶亾: 0:涓昏緭鍑�/1:杈�1/2:杈�2/3:杈�3/4:杈�4 */
	public int dwAlarmChanSwitchTime;                /* :1绉� - 10:10绉� */
	public int[] dwAuxSwitchTime = new int[MAX_AUXOUT];			/* 杈呭姪杈撳嚭鍒囨崲鏃堕棿: 0-涓嶅垏鎹�,1-5s,2-10s,3-20s,4-30s,5-60s,6-120s,7-300s */
	public byte[][]  byAuxOrder = new byte[MAX_AUXOUT][MAX_WINDOW];	/* 杈呭姪杈撳嚭棰勮椤哄簭, 0xff琛ㄧず鐩稿簲鐨勭獥鍙ｄ笉棰勮 */
}

//ntp
    public static class NET_DVR_NTPPARA extends Structure {
	public byte[] sNTPServer = new byte[64];   /* Domain Name or IP addr of NTP server */
	public short wInterval;		 /* adjust time interval(hours) */
	public byte byEnableNTP;    /* enable NPT client 0-no锛�1-yes*/
        public byte cTimeDifferenceH; /* 涓庡浗闄呮爣鍑嗘椂闂寸殑 灏忔椂鍋忕Щ-12 ... +13 */
	public byte cTimeDifferenceM;/* 涓庡浗闄呮爣鍑嗘椂闂寸殑 鍒嗛挓鍋忕Щ0, 30, 45*/
	public byte res1;
       public   short wNtpPort;         /* ntp server port 9000鏂板 璁惧榛樿涓�123*/
       public   byte[] res2 = new byte[8];
}

//ddns
    public static class NET_DVR_DDNSPARA extends Structure {
	public byte[] sUsername = new byte[NAME_LEN];  /* DDNS璐﹀彿鐢ㄦ埛鍚�/瀵嗙爜 */
	public byte[] sPassword = new byte[PASSWD_LEN];
	public byte[] sDomainName = new byte[64];       /* 鍩熷悕 */
	public byte byEnableDDNS;			/*鏄惁搴旂敤 0-鍚︼紝1-鏄�*/
	public byte[] res = new byte[15];
}

   public static class NET_DVR_DDNSPARA_EX extends Structure {
	public byte byHostIndex;					/* 0-Hikvision DNS 1锛岲yndns 2锛峆eanutHull(鑺辩敓澹�), 3-甯岀綉3322*/
	public byte byEnableDDNS;					/*鏄惁搴旂敤DDNS 0-鍚︼紝1-鏄�*/
	public short wDDNSPort;						/* DDNS绔彛鍙� */
	public byte[] sUsername = new byte[NAME_LEN];			/* DDNS鐢ㄦ埛鍚�*/
	public byte[] sPassword = new byte[PASSWD_LEN];			/* DDNS瀵嗙爜 */
	public byte[] sDomainName = new byte[MAX_DOMAIN_NAME];	/* 璁惧閰嶅鐨勫煙鍚嶅湴鍧� */
	public byte[] sServerName = new byte[MAX_DOMAIN_NAME];	/* DDNS 瀵瑰簲鐨勬湇鍔″櫒鍦板潃锛屽彲浠ユ槸IP鍦板潃鎴栧煙鍚� */
	public byte[] byRes = new byte[16];
}

   public static class NET_DVR_DDNS extends Structure {
       public  byte[] sUsername = new byte[NAME_LEN];			/* DDNS璐﹀彿鐢ㄦ埛鍚�*/
       public  byte[] sPassword = new byte[PASSWD_LEN];			/* 瀵嗙爜 */
       public  byte[] sDomainName = new byte[MAX_DOMAIN_NAME];	/* 璁惧閰嶅鐨勫煙鍚嶅湴鍧� */
       public  byte[] sServerName = new byte[MAX_DOMAIN_NAME];	/* DDNS鍗忚瀵瑰簲鐨勬湇鍔″櫒鍦板潃锛屽彲浠ユ槸IP鍦板潃鎴栧煙鍚� */
       public  short wDDNSPort;						/* 绔彛鍙� */
       public   byte[] byRes = new byte[10];
   }
//9000鎵╁睍
public static class NET_DVR_DDNSPARA_V30 extends Structure {
  public   byte byEnableDDNS;
  public   byte byHostIndex;/* 0-Hikvision DNS(淇濈暀) 1锛岲yndns 2锛峆eanutHull(鑺辩敓澹�) 3锛嶅笇缃�3322 */
  public  byte[] byRes1 = new byte[2];
  public   NET_DVR_DDNS[] struDDNS = new NET_DVR_DDNS[MAX_DDNS_NUMS];//9000鐩墠鍙敮鎸佸墠3涓厤缃紝鍏朵粬閰嶇疆淇濈暀
  public   byte[] byRes2 = new byte[16];
}

//email
public static class NET_DVR_EMAILPARA extends Structure {
	public byte[] sUsername = new byte[64];  /* 閭欢璐﹀彿/瀵嗙爜 */
	public byte[] sPassword = new byte[64];
	public byte[] sSmtpServer = new byte[64];
	public byte[] sPop3Server = new byte[64];
	public byte[] sMailAddr = new byte[64];   /* email */
	public byte[] sEventMailAddr1 = new byte[64];  /* 涓婁紶鎶ヨ/寮傚父绛夌殑email */
	public byte[] sEventMailAddr2 = new byte[64];
	public byte[] res = new byte[16];
}

public static class NET_DVR_NETAPPCFG extends Structure {//缃戠粶鍙傛暟閰嶇疆
	public int  dwSize;
	public byte[]  sDNSIp = new byte[16];                /* DNS鏈嶅姟鍣ㄥ湴鍧� */
	public NET_DVR_NTPPARA  struNtpClientParam;      /* NTP鍙傛暟 */
	public NET_DVR_DDNSPARA struDDNSClientParam;     /* DDNS鍙傛暟 */
	//NET_DVR_EMAILPARA struEmailParam;       /* EMAIL鍙傛暟 */
	public byte[] res = new byte[464];			/* 淇濈暀 */
}

public static class NET_DVR_SINGLE_NFS extends Structure {//nfs缁撴瀯閰嶇疆
    public byte[] sNfsHostIPAddr = new byte[16];
    public byte[] sNfsDirectory = new byte[PATHNAME_LEN];        // PATHNAME_LEN = 128
}

public static class NET_DVR_NFSCFG extends Structure {
	public int  dwSize;
        public NET_DVR_SINGLE_NFS[] struNfsDiskParam = new NET_DVR_SINGLE_NFS[MAX_NFS_DISK];
}

//宸¤埅鐐归厤缃�(HIK IP蹇悆涓撶敤)
public static class NET_DVR_CRUISE_POINT extends Structure {
  public   byte	PresetNum;	//棰勭疆鐐�
  public  byte	Dwell;		//鍋滅暀鏃堕棿
  public   byte	Speed;		//閫熷害
  public   byte	Reserve;	//淇濈暀
}

public static class NET_DVR_CRUISE_RET extends Structure {
	public NET_DVR_CRUISE_POINT[] struCruisePoint = new NET_DVR_CRUISE_POINT[32];			//鏈�澶ф敮鎸�32涓贰鑸偣
}

/************************************澶氳矾瑙ｇ爜鍣�(begin)***************************************/
//澶氳矾瑙ｇ爜鍣ㄦ墿灞� added by zxy 2007-05-23
public static class NET_DVR_NETCFG_OTHER extends Structure {
	public int	dwSize;
	public byte[]	sFirstDNSIP = new byte[16];
	public byte[]	sSecondDNSIP = new byte[16];
	public byte[]	sRes = new byte[32];
}

public static class NET_DVR_MATRIX_DECINFO extends Structure {
	public byte[] 	sDVRIP = new byte[16];				/* DVR IP鍦板潃 */
	public short 	wDVRPort;			 	/* 绔彛鍙� */
	public byte 	byChannel;				/* 閫氶亾鍙� */
	public byte	byTransProtocol;			/* 浼犺緭鍗忚绫诲瀷 0-TCP 1-UDP */
	public byte	byTransMode;				/* 浼犺緭鐮佹祦妯″紡 0锛嶄富鐮佹祦 1锛嶅瓙鐮佹祦*/
	public byte[]	byRes = new byte[3];
	public byte[]	sUserName = new byte[NAME_LEN];			/* 鐩戞帶涓绘満鐧婚檰甯愬彿 */
	public byte[]	sPassword = new byte[PASSWD_LEN];			/* 鐩戞帶涓绘満瀵嗙爜 */
}

public static class NET_DVR_MATRIX_DYNAMIC_DEC extends Structure {//鍚姩/鍋滄鍔ㄦ�佽В鐮�
	public int	dwSize;
	public NET_DVR_MATRIX_DECINFO struDecChanInfo;		/* 鍔ㄦ�佽В鐮侀�氶亾淇℃伅 */
}

public static class NET_DVR_MATRIX_DEC_CHAN_STATUS extends Structure {//2007-12-13 modified by zxy 淇敼澶氳矾瑙ｇ爜鍣ㄧ殑NET_DVR_MATRIX_DEC_CHAN_STATUS缁撴瀯
   public  int   dwSize;//2008-1-16 modified by zxy dwIsLinked鐨勭姸鎬佺敱鍘熸潵鐨�0锛嶆湭閾炬帴 1锛嶈繛鎺ヤ慨鏀规垚浠ヤ笅涓夌鐘舵�併��
   public  int   dwIsLinked;         /* 瑙ｇ爜閫氶亾鐘舵�� 0锛嶄紤鐪� 1锛嶆鍦ㄨ繛鎺� 2锛嶅凡杩炴帴 3-姝ｅ湪瑙ｇ爜 */
   public  int   dwStreamCpRate;     /* Stream copy rate, X kbits/second */
   public  byte[]    cRes = new byte[64];		/* 淇濈暀 */
}
//end 2007-12-13 modified by zxy

public static class NET_DVR_MATRIX_DEC_CHAN_INFO extends Structure {
	public int	dwSize;
	public NET_DVR_MATRIX_DECINFO struDecChanInfo;		/* 瑙ｇ爜閫氶亾淇℃伅 */
	public int	dwDecState;	/* 0-鍔ㄦ�佽В鐮� 1锛嶅惊鐜В鐮� 2锛嶆寜鏃堕棿鍥炴斁 3锛嶆寜鏂囦欢鍥炴斁 */
	public NET_DVR_TIME StartTime;		/* 鎸夋椂闂村洖鏀惧紑濮嬫椂闂� */
	public NET_DVR_TIME StopTime;		/* 鎸夋椂闂村洖鏀惧仠姝㈡椂闂� */
	public byte[]    sFileName = new byte[128];		/* 鎸夋枃浠跺洖鏀炬枃浠跺悕 */
}

//杩炴帴鐨勯�氶亾閰嶇疆 2007-11-05
public static class NET_DVR_MATRIX_DECCHANINFO extends Structure {
	public int dwEnable;					/* 鏄惁鍚敤 0锛嶅惁 1锛嶅惎鐢�*/
	public NET_DVR_MATRIX_DECINFO struDecChanInfo;		/* 杞惊瑙ｇ爜閫氶亾淇℃伅 */
}

//2007-11-05 鏂板姣忎釜瑙ｇ爜閫氶亾鐨勯厤缃�
public static class NET_DVR_MATRIX_LOOP_DECINFO extends Structure {
	public int	dwSize;
	public int	dwPoolTime;			/*杞贰鏃堕棿 */
	public NET_DVR_MATRIX_DECCHANINFO[] struchanConInfo = new NET_DVR_MATRIX_DECCHANINFO[MAX_CYCLE_CHAN];
}

//2007-05-25  澶氳矾瑙ｇ爜鍣ㄦ暟瀛楃煩闃甸厤缃�
//鐭╅樀琛屼俊鎭� 2007-12-28
public static class NET_DVR_MATRIX_ROW_ELEMENT extends Structure {
	public byte[]	sSurvChanName = new byte[128];			/* 鐩戞帶閫氶亾鍚嶇О锛屾敮鎸佷腑鏂� */
	public int	dwRowNum;				/* 琛屽彿 */
	public NET_DVR_MATRIX_DECINFO struDecChanInfo;		/* 鐭╅樀琛屼俊鎭� */
}

public static class NET_DVR_MATRIX_ROW_INDEX extends Structure {
	public byte[]	sSurvChanName = new byte[128];			/* 鐩戞帶閫氶亾鍚嶇О锛屾敮鎸佷腑鏂� */
	public int	dwRowNum;				/* 琛屽彿 */
}

//鐭╅樀鍒椾俊鎭� 2007-12-28
public static class NET_DVR_MATRIX_COLUMN_ELEMENT extends Structure {
	public int  dwLocalDispChanNum;	/* 鏈湴鏄剧ず閫氶亾鍙� */
	public int  dwGlobalDispChanNum;	/* 鍏ㄥ眬鏄剧ず閫氶亾鍙� */
	public int  dwRes;			/* 淇濈暀 */
}

public static class NET_DVR_MATRIX_GLOBAL_COLUMN_ELEMENT extends Structure {
	public int		dwConflictTag;		/* 鍐茬獊鏍囪锛�0锛氭棤鍐茬獊锛�1锛氬啿绐� */
	public int		dwConflictGloDispChan;	/* 涓庝箣鍐茬獊鐨勫叏灞�閫氶亾鍙� */
	public NET_DVR_MATRIX_COLUMN_ELEMENT  struColumnInfo;/* 鐭╅樀鍒楀厓绱犵粨鏋勪綋 */
}

//鎵嬪姩鏌ョ湅 2007-12-28
public static class NET_DVR_MATRIX_ROW_COLUMN_LINK extends Structure {
	public int	dwSize;
	/*
	*	浠ヤ笅涓変釜鍙傛暟鍙渶瑕佹寚瀹氬叾涓竴涓究鍙寚瀹氭暟瀛楃煩闃甸噷鐨勬煇涓�琛�
	*	鎵�浠ｈ〃鐨勮繙绋嬬洃鎺ч�氶亾銆�
	*	濡傛灉鎸囧畾浜嗗涓煙骞舵湁鍐茬獊锛岃澶囧皢鎸夌収鍩熺殑鍏堝悗椤哄簭涓哄噯鍙栨渶鍏堝畾涔夎�呫��
	*/
	public int	dwRowNum;			/* -1浠ｈ〃鏃犳晥鍩�,澶т簬0鑰呮柟涓烘湁鏁堢殑鐭╅樀琛屽彿 */
	public byte[]	sSurvChanName = new byte[128];	/* 鐩戞帶閫氶亾鍚�,鏄惁鏃犳晥鎸夊瓧绗︿覆鐨勬湁鏁堟�у垽鏂� */
	public int	dwSurvNum;			/* 鐩戞帶閫氶亾鍙�,鎸夌煩闃佃鍒楄〃鐨勯『搴忔寚瀹氾紝涓�鑸儏鍐典笅涓庤鍙蜂竴鑷� */
								/*
								*	浠ヤ笅涓ら」鍙渶瑕佹寚瀹氬叾涓竴椤逛究鍙紝濡傛灉涓ら」閮芥湁鏁堥粯璁ら�夋嫨绗竴椤�
	*/
	public int	dwGlobalDispChanNum;			/* 鐢佃澧欎笂鐨勭數瑙嗘満缂栧彿 */
	public int	dwLocalDispChanNum;
	/*
	*	0浠ｈ〃鎾斁鍗虫椂鐮佹祦锛�
	*	1琛ㄧず鎸夋椂闂村洖璁胯繙绋嬬洃鎺ц澶囩殑鏂囦欢
	*	2琛ㄧず鎸夋枃浠跺悕鍥炶
	*/
	public int	dwTimeSel;
	public NET_DVR_TIME StartTime;
	public NET_DVR_TIME StopTime;
	public byte[]	sFileName = new byte[128];
}

public static class NET_DVR_MATRIX_PREVIEW_DISP_CHAN extends Structure {
	public int		dwSize;
	public int		dwGlobalDispChanNum;		/* 鐢佃澧欎笂鐨勭數瑙嗘満缂栧彿 */
	public int		dwLocalDispChanNum;		/* 瑙ｇ爜閫氶亾 */
}

public static class NET_DVR_MATRIX_LOOP_PLAY_SET extends Structure {//杞惊鍔熻兘 2007-12-28
	public int	dwSize;
	/* 浠绘剰鎸囧畾涓�涓�,-1涓烘棤鏁�,濡傛灉閮芥寚瀹氬垯浠ocalDispChanNum涓哄噯 */
	public int	dwLocalDispChanNum;	/* 瑙ｇ爜閫氶亾 */
	public int	dwGlobalDispChanNum;  	/* 鐢佃澧欎笂鐨勭數瑙嗘満缂栧彿 */
	public int	dwCycTimeInterval;	/* 杞惊鏃堕棿闂撮殧 */
}

public static class NET_DVR_MATRIX_LOCAL_HOST_INFO extends Structure {//鐭╅樀涓績閰嶇疆 2007-12-28
	public int	dwSize;
	public int	dwLocalHostProperty;  	/* 鏈湴涓绘満绫诲瀷 0锛嶆湇鍔″櫒 1锛嶅鎴风*/
	public int	dwIsIsolated;		/* 鏈湴涓绘満鏄惁鐙珛浜庣郴缁燂紝0锛氳仈缃戯紝1锛氱嫭绔� */
	public int	dwLocalMatrixHostPort;	/* 鏈湴涓绘満璁块棶绔彛 */
	public byte[]	byLocalMatrixHostUsrName = new byte[NAME_LEN];		/* 鏈湴涓绘満鐧诲綍鐢ㄦ埛鍚� */
	public byte[]	byLocalMatrixHostPasswd = new byte[PASSWD_LEN];		/* 鏈湴涓绘満鐧诲綍瀵嗙爜 */
	public int	dwLocalMatrixCtrlMedia;				/* 鎺у埗鏂瑰紡 0x1涓插彛閿洏鎺у埗 0x2缃戠粶閿洏鎺у埗 0x4鐭╅樀涓績鎺у埗 0x8PC瀹㈡埛绔帶鍒�*/
	public byte[]	sMatrixCenterIP = new byte[16];		/* 鐭╅樀涓績IP鍦板潃 */
	public int	dwMatrixCenterPort;	 	/* 鐭╅樀涓績绔彛鍙� */
	public byte[]	byMatrixCenterUsrName = new byte[NAME_LEN];	/* 鐭╅樀涓績鐧诲綍鐢ㄦ埛鍚� */
	public byte[]	byMatrixCenterPasswd = new byte[PASSWD_LEN];	/* 鐭╅樀涓績鐧诲綍瀵嗙爜 */
}

//2007-12-22
public static class TTY_CONFIG extends Structure {
	public byte	baudrate; 	/* 娉㈢壒鐜� */
	public byte	databits;		/* 鏁版嵁浣� */
	public byte	stopbits;		/* 鍋滄浣� */
	public byte	parity;		/* 濂囧伓鏍￠獙浣� */
	public byte	flowcontrol;	/* 娴佹帶 */
	public byte[]	res = new byte[3];
}

public static class NET_DVR_MATRIX_TRAN_CHAN_INFO extends Structure {
	public byte byTranChanEnable;	/* 褰撳墠閫忔槑閫氶亾鏄惁鎵撳紑 0锛氬叧闂� 1锛氭墦寮� */
         /*
	 *	澶氳矾瑙ｇ爜鍣ㄦ湰鍦版湁1涓�485涓插彛锛�1涓�232涓插彛閮藉彲浠ヤ綔涓洪�忔槑閫氶亾,璁惧鍙峰垎閰嶅涓嬶細
	 *	0 RS485
	 *	1 RS232 Console
	 */
	public byte	byLocalSerialDevice;			/* Local serial device */
         /*
	 *	杩滅▼涓插彛杈撳嚭杩樻槸涓や釜,涓�涓猂S232锛屼竴涓猂S485
	 *	1琛ㄧず232涓插彛
	 *	2琛ㄧず485涓插彛
	 */
	public byte	byRemoteSerialDevice;			/* Remote output serial device */
	public byte	res1;							/* 淇濈暀 */
	public byte[]	sRemoteDevIP= new byte[16];				/* Remote Device IP */
	public short	wRemoteDevPort;				/* Remote Net Communication Port */
	public byte[]	res2= new byte[2];						/* 淇濈暀 */
	public TTY_CONFIG RemoteSerialDevCfg;
}

public static class NET_DVR_MATRIX_TRAN_CHAN_CONFIG extends Structure {
        public 	int dwSize;
	public byte 	by232IsDualChan; /* 璁剧疆鍝矾232閫忔槑閫氶亾鏄叏鍙屽伐鐨� 鍙栧��1鍒癕AX_SERIAL_NUM */
	public byte	by485IsDualChan; /* 璁剧疆鍝矾485閫忔槑閫氶亾鏄叏鍙屽伐鐨� 鍙栧��1鍒癕AX_SERIAL_NUM */
	public byte[]	res = new byte[2];	/* 淇濈暀 */
	public NET_DVR_MATRIX_TRAN_CHAN_INFO[] struTranInfo = new NET_DVR_MATRIX_TRAN_CHAN_INFO[MAX_SERIAL_NUM];/*鍚屾椂鏀寔寤虹珛MAX_SERIAL_NUM涓�忔槑閫氶亾*/
}

//2007-12-24 Merry Christmas Eve...
public static class NET_DVR_MATRIX_DEC_REMOTE_PLAY extends Structure {
	public int	dwSize;
	public byte[]	sDVRIP = new byte[16];		/* DVR IP鍦板潃 */
	public short	wDVRPort;			/* 绔彛鍙� */
	public byte	byChannel;			/* 閫氶亾鍙� */
	public byte 	byReserve;
	public byte[]	sUserName = new byte[NAME_LEN];		/* 鐢ㄦ埛鍚� */
	public byte[]	sPassword = new byte[PASSWD_LEN];		/* 瀵嗙爜 */
	public int	dwPlayMode;   	/* 0锛嶆寜鏂囦欢 1锛嶆寜鏃堕棿*/
	public NET_DVR_TIME StartTime;
	public NET_DVR_TIME StopTime;
	public byte[]    sFileName = new byte[128];
}


public static class NET_DVR_MATRIX_DEC_REMOTE_PLAY_CONTROL extends Structure {
	public int	dwSize;
	public int	dwPlayCmd;		/* 鎾斁鍛戒护 瑙佹枃浠舵挱鏀惧懡浠�*/
	public int	dwCmdParam;		/* 鎾斁鍛戒护鍙傛暟 */
}

public static class NET_DVR_MATRIX_DEC_REMOTE_PLAY_STATUS extends Structure {
	public int dwSize;
	public int dwCurMediaFileLen; /* 褰撳墠鎾斁鐨勫獟浣撴枃浠堕暱搴� */
	public int dwCurMediaFilePosition; /* 褰撳墠鎾斁鏂囦欢鐨勬挱鏀句綅缃� */
	public int dwCurMediaFileDuration; /* 褰撳墠鎾斁鏂囦欢鐨勬�绘椂闂� */
	public int dwCurPlayTime; /* 褰撳墠宸茬粡鎾斁鐨勬椂闂� */
	public int dwCurMediaFIleFrames; /* 褰撳墠鎾斁鏂囦欢鐨勬�诲抚鏁� */
	public int dwCurDataType; /* 褰撳墠浼犺緭鐨勬暟鎹被鍨嬶紝19-鏂囦欢澶达紝20-娴佹暟鎹紝 21-鎾斁缁撴潫鏍囧織 */
        public  byte[] res = new byte[72];
}

public static class NET_DVR_MATRIX_PASSIVEMODE extends Structure {
	public short	wTransProtol;		//浼犺緭鍗忚锛�0-TCP, 1-UDP, 2-MCAST
	public short	wPassivePort;		//TCP,UDP鏃朵负TCP,UDP绔彛, MCAST鏃朵负MCAST绔彛
	public byte[]	sMcastIP = new byte[16];		//TCP,UDP鏃舵棤鏁�, MCAST鏃朵负澶氭挱鍦板潃
	public byte[]	res = new byte[8];
}
/************************************澶氳矾瑙ｇ爜鍣�(end)***************************************/

/************************************澶氳矾瑙ｇ爜鍣�(end)***************************************/

public static class NET_DVR_EMAILCFG  extends Structure
{	/* 12 bytes */
	public int	dwSize;
	public byte[]	sUserName = new byte[32];
	public byte[]	sPassWord = new byte[32];
	public byte[] 	sFromName = new byte[32];			/* Sender *///瀛楃涓蹭腑鐨勭涓�涓瓧绗﹀拰鏈�鍚庝竴涓瓧绗︿笉鑳芥槸"@",骞朵笖瀛楃涓蹭腑瑕佹湁"@"瀛楃
	public byte[] 	sFromAddr = new byte[48];			/* Sender address */
	public byte[] 	sToName1 = new byte[32];			/* Receiver1 */
	public byte[] 	sToName2 = new byte[32];			/* Receiver2 */
	public byte[] 	sToAddr1 = new byte[48];			/* Receiver address1 */
	public byte[] 	sToAddr2 = new byte[48];			/* Receiver address2 */
	public byte[]	sEmailServer = new byte[32];		/* Email server address */
        public byte	byServerType;			/* Email server type: 0-SMTP, 1-POP, 2-IMTP鈥�*/
	public byte	byUseAuthen;			/* Email server authentication method: 1-enable, 0-disable */
	public byte	byAttachment;			/* enable attachment */
	public byte	byMailinterval;			/* mail interval 0-2s, 1-3s, 2-4s. 3-5s*/
}

public static class NET_DVR_COMPRESSIONCFG_NEW extends Structure
{
	public int dwSize;
	public NET_DVR_COMPRESSION_INFO_EX  struLowCompression;	//瀹氭椂褰曞儚
	public NET_DVR_COMPRESSION_INFO_EX  struEventCompression;	//浜嬩欢瑙﹀彂褰曞儚
}

//鐞冩満浣嶇疆淇℃伅
public static class NET_DVR_PTZPOS extends Structure
{
   public  short wAction;//鑾峰彇鏃惰瀛楁鏃犳晥
   public  short wPanPos;//姘村钩鍙傛暟
   public  short wTiltPos;//鍨傜洿鍙傛暟
   public  short wZoomPos;//鍙樺�嶅弬鏁�
}

//鐞冩満鑼冨洿淇℃伅
public static class NET_DVR_PTZSCOPE extends Structure
{
   public  short wPanPosMin;//姘村钩鍙傛暟min
   public  short wPanPosMax;//姘村钩鍙傛暟max
   public  short wTiltPosMin;//鍨傜洿鍙傛暟min
   public  short wTiltPosMax;//鍨傜洿鍙傛暟max
   public   short wZoomPosMin;//鍙樺�嶅弬鏁癿in
   public   short wZoomPosMax;//鍙樺�嶅弬鏁癿ax
}

//rtsp閰嶇疆 ipcamera涓撶敤
public static class NET_DVR_RTSPCFG extends Structure
{
 public    int dwSize;         //闀垮害
 public    short  wPort;          //rtsp鏈嶅姟鍣ㄤ睛鍚鍙�
 public    byte[]  byReserve = new byte[54];  //棰勭暀
}

/********************************鎺ュ彛鍙傛暟缁撴瀯(begin)*********************************/

//NET_DVR_Login()鍙傛暟缁撴瀯
public static class NET_DVR_DEVICEINFO extends Structure
{
	public byte[] sSerialNumber = new byte[SERIALNO_LEN];   //搴忓垪鍙�
	public byte byAlarmInPortNum;		        //DVR鎶ヨ杈撳叆涓暟
	public byte byAlarmOutPortNum;		        //DVR鎶ヨ杈撳嚭涓暟
	public byte byDiskNum;				        //DVR纭洏涓暟
	public byte byDVRType;				        //DVR绫诲瀷, 1:DVR 2:ATM DVR 3:DVS ......
	public byte byChanNum;				        //DVR 閫氶亾涓暟
	public byte byStartChan;			        //璧峰閫氶亾鍙�,渚嬪DVS-1,DVR - 1
}

//NET_DVR_Login_V30()鍙傛暟缁撴瀯
public static class NET_DVR_DEVICEINFO_V30 extends Structure
{
   public  byte[] sSerialNumber = new byte[SERIALNO_LEN];  //搴忓垪鍙�
   public  byte byAlarmInPortNum;		        //鎶ヨ杈撳叆涓暟
   public  byte byAlarmOutPortNum;		        //鎶ヨ杈撳嚭涓暟
   public  byte byDiskNum;				    //纭洏涓暟
   public  byte byDVRType;				    //璁惧绫诲瀷, 1:DVR 2:ATM DVR 3:DVS ......
   public  byte byChanNum;				    //妯℃嫙閫氶亾涓暟
   public  byte byStartChan;			        //璧峰閫氶亾鍙�,渚嬪DVS-1,DVR - 1
   public  byte byAudioChanNum;                //璇煶閫氶亾鏁�
   public  byte byIPChanNum;					//鏈�澶ф暟瀛楅�氶亾涓暟
   public  byte[] byRes1 = new byte[24];					//淇濈暀
}

//sdk缃戠粶鐜鏋氫妇鍙橀噺锛岀敤浜庤繙绋嬪崌绾�
 enum _SDK_NET_ENV
{
    LOCAL_AREA_NETWORK ,
    WIDE_AREA_NETWORK
}

//鏄剧ず妯″紡
 enum DISPLAY_MODE
{
	NORMALMODE ,
	OVERLAYMODE
}

//鍙戦�佹ā寮�
 enum SEND_MODE
{
	PTOPTCPMODE,
	PTOPUDPMODE,
	MULTIMODE,
	RTPMODE,
	RESERVEDMODE
};

//鎶撳浘妯″紡
 enum CAPTURE_MODE
{
	BMP_MODE,		//BMP妯″紡
	JPEG_MODE		//JPEG妯″紡
};

//瀹炴椂澹伴煶妯″紡
 enum REALSOUND_MODE
{
	NONE,                   //SDK涓棤姝ゆā寮�,鍙槸涓轰簡濉ˉ0杩欎釜浣嶇疆
        MONOPOLIZE_MODE ,       //鐙崰妯″紡 1
	SHARE_MODE 		//鍏变韩妯″紡 2
};

//杞В鐮侀瑙堝弬鏁�
    public static class NET_DVR_CLIENTINFO extends Structure {
        public NativeLong lChannel;
        public NativeLong lLinkMode;
        public HWND hPlayWnd;
        public String sMultiCastIP;
    }

//SDK鐘舵�佷俊鎭�(9000鏂板)
public static class NET_DVR_SDKSTATE extends Structure
{
    public int dwTotalLoginNum;		//褰撳墠login鐢ㄦ埛鏁�
    public int dwTotalRealPlayNum;	//褰撳墠realplay璺暟
    public int dwTotalPlayBackNum;	//褰撳墠鍥炴斁鎴栦笅杞借矾鏁�
    public int dwTotalAlarmChanNum;	//褰撳墠寤虹珛鎶ヨ閫氶亾璺暟
    public int dwTotalFormatNum;		//褰撳墠纭洏鏍煎紡鍖栬矾鏁�
    public  int dwTotalFileSearchNum;	//褰撳墠鏃ュ織鎴栨枃浠舵悳绱㈣矾鏁�
    public  int dwTotalLogSearchNum;	//褰撳墠鏃ュ織鎴栨枃浠舵悳绱㈣矾鏁�
    public  int dwTotalSerialNum;	    //褰撳墠閫忔槑閫氶亾璺暟
    public int dwTotalUpgradeNum;	//褰撳墠鍗囩骇璺暟
    public int dwTotalVoiceComNum;	//褰撳墠璇煶杞彂璺暟
    public int dwTotalBroadCastNum;	//褰撳墠璇煶骞挎挱璺暟
    public int[] dwRes = new int[10];
}

//SDK鍔熻兘鏀寔淇℃伅(9000鏂板)
public static class NET_DVR_SDKABL extends Structure
{
    public int dwMaxLoginNum;		//鏈�澶ogin鐢ㄦ埛鏁� MAX_LOGIN_USERS
    public int dwMaxRealPlayNum;		//鏈�澶ealplay璺暟 WATCH_NUM
    public int dwMaxPlayBackNum;		//鏈�澶у洖鏀炬垨涓嬭浇璺暟 WATCH_NUM
    public int dwMaxAlarmChanNum;	//鏈�澶у缓绔嬫姤璀﹂�氶亾璺暟 ALARM_NUM
    public int dwMaxFormatNum;		//鏈�澶х‖鐩樻牸寮忓寲璺暟 SERVER_NUM
    public int dwMaxFileSearchNum;	//鏈�澶ф枃浠舵悳绱㈣矾鏁� SERVER_NUM
    public int dwMaxLogSearchNum;	//鏈�澶ф棩蹇楁悳绱㈣矾鏁� SERVER_NUM
    public int dwMaxSerialNum;	    //鏈�澶ч�忔槑閫氶亾璺暟 SERVER_NUM
    public int dwMaxUpgradeNum;	    //鏈�澶у崌绾ц矾鏁� SERVER_NUM
    public int dwMaxVoiceComNum;		//鏈�澶ц闊宠浆鍙戣矾鏁� SERVER_NUM
    public int dwMaxBroadCastNum;	//鏈�澶ц闊冲箍鎾矾鏁� MAX_CASTNUM
    public int[] dwRes = new int[10];
}

//鎶ヨ璁惧淇℃伅
public static class NET_DVR_ALARMER extends Structure
{
   public  byte byUserIDValid;                 /* userid鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public  byte bySerialValid;                 /* 搴忓垪鍙锋槸鍚︽湁鏁� 0-鏃犳晥锛�1-鏈夋晥 */
   public  byte byVersionValid;                /* 鐗堟湰鍙锋槸鍚︽湁鏁� 0-鏃犳晥锛�1-鏈夋晥 */
   public  byte byDeviceNameValid;             /* 璁惧鍚嶅瓧鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public byte byMacAddrValid;                /* MAC鍦板潃鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public   byte byLinkPortValid;               /* login绔彛鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public    byte byDeviceIPValid;               /* 璁惧IP鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public   byte bySocketIPValid;               /* socket ip鏄惁鏈夋晥 0-鏃犳晥锛�1-鏈夋晥 */
   public   NativeLong lUserID;                       /* NET_DVR_Login()杩斿洖鍊�, 甯冮槻鏃舵湁鏁� */
   public   byte[] sSerialNumber = new byte[SERIALNO_LEN];	/* 搴忓垪鍙� */
   public  int dwDeviceVersion;			    /* 鐗堟湰淇℃伅 楂�16浣嶈〃绀轰富鐗堟湰锛屼綆16浣嶈〃绀烘鐗堟湰*/
   public   byte[] sDeviceName = new byte[NAME_LEN];		    /* 璁惧鍚嶅瓧 */
   public    byte[] byMacAddr = new byte[MACADDR_LEN];		/* MAC鍦板潃 */
   public   short wLinkPort;                     /* link port */
   public   byte[] sDeviceIP = new byte[128];    			/* IP鍦板潃 */
   public   byte[] sSocketIP = new byte[128];    			/* 鎶ヨ涓诲姩涓婁紶鏃剁殑socket IP鍦板潃 */
   public  byte byIpProtocol;                  /* Ip鍗忚 0-IPV4, 1-IPV6 */
   public    byte[] byRes2 = new byte[11];
}

//纭В鐮佹樉绀哄尯鍩熷弬鏁�(瀛愮粨鏋�)
public static class NET_DVR_DISPLAY_PARA extends Structure
{
	public NativeLong bToScreen;
	public NativeLong bToVideoOut;
	public NativeLong nLeft;
	public NativeLong nTop;
	public NativeLong nWidth;
	public NativeLong nHeight;
	public NativeLong nReserved;
}

//纭В鐮侀瑙堝弬鏁�
public static class NET_DVR_CARDINFO extends Structure
{
	public NativeLong lChannel;//閫氶亾鍙�
	public NativeLong lLinkMode; //鏈�楂樹綅(31)涓�0琛ㄧず涓荤爜娴侊紝涓�1琛ㄧず瀛愶紝0锛�30浣嶈〃绀虹爜娴佽繛鎺ユ柟寮�:0锛歍CP鏂瑰紡,1锛歎DP鏂瑰紡,2锛氬鎾柟寮�,3 - RTP鏂瑰紡锛�4-鐢佃瘽绾匡紝5锛�128k瀹藉甫锛�6锛�256k瀹藉甫锛�7锛�384k瀹藉甫锛�8锛�512k瀹藉甫锛�
	public String sMultiCastIP;
	public NET_DVR_DISPLAY_PARA struDisplayPara;
}

//褰曡薄鏂囦欢鍙傛暟
public static class NET_DVR_FIND_DATA extends Structure
{
	public byte[] sFileName = new byte[100];//鏂囦欢鍚�
	public NET_DVR_TIME struStartTime;//鏂囦欢鐨勫紑濮嬫椂闂�
	public NET_DVR_TIME struStopTime;//鏂囦欢鐨勭粨鏉熸椂闂�
	public int dwFileSize;//鏂囦欢鐨勫ぇ灏�
}

//褰曡薄鏂囦欢鍙傛暟(9000)
  public static class NET_DVR_FINDDATA_V30 extends Structure {
        public byte[] sFileName = new byte[100];//鏂囦欢鍚�
        public NET_DVR_TIME struStartTime;//鏂囦欢鐨勫紑濮嬫椂闂�
        public NET_DVR_TIME struStopTime;//鏂囦欢鐨勭粨鏉熸椂闂�
        public int dwFileSize;//鏂囦欢鐨勫ぇ灏�
        public byte[] sCardNum = new byte[32];
        public byte byLocked;//9000璁惧鏀寔,1琛ㄧず姝ゆ枃浠跺凡缁忚閿佸畾,0琛ㄧず姝ｅ父鐨勬枃浠�
        public byte[] byRes = new byte[3];
    }

//褰曡薄鏂囦欢鍙傛暟(甯﹀崱鍙�)
public static class NET_DVR_FINDDATA_CARD extends Structure
{
	public byte[] sFileName = new byte[100];//鏂囦欢鍚�
	public NET_DVR_TIME struStartTime;//鏂囦欢鐨勫紑濮嬫椂闂�
	public NET_DVR_TIME struStopTime;//鏂囦欢鐨勭粨鏉熸椂闂�
	public int dwFileSize;//鏂囦欢鐨勫ぇ灏�
	public byte[] sCardNum = new byte[32];
}


 public static class NET_DVR_FILECOND extends Structure //褰曡薄鏂囦欢鏌ユ壘鏉′欢缁撴瀯
    {
        public NativeLong lChannel;//閫氶亾鍙�
        public int dwFileType;//褰曡薄鏂囦欢绫诲瀷0xff锛嶅叏閮紝0锛嶅畾鏃跺綍鍍�,1-绉诲姩渚︽祴 锛�2锛嶆姤璀﹁Е鍙戯紝3-鎶ヨ|绉诲姩渚︽祴 4-鎶ヨ&绉诲姩渚︽祴 5-鍛戒护瑙﹀彂 6-鎵嬪姩褰曞儚
        public int dwIsLocked;//鏄惁閿佸畾 0-姝ｅ父鏂囦欢,1-閿佸畾鏂囦欢, 0xff琛ㄧず鎵�鏈夋枃浠�
        public int dwUseCardNo;//鏄惁浣跨敤鍗″彿
        public byte[] sCardNumber = new byte[32];//鍗″彿
        public NET_DVR_TIME struStartTime;//寮�濮嬫椂闂�
        public NET_DVR_TIME struStopTime;//缁撴潫鏃堕棿
    }


//浜戝彴鍖哄煙閫夋嫨鏀惧ぇ缂╁皬(HIK 蹇悆涓撶敤)
public static class NET_DVR_POINT_FRAME extends Structure
{
	public int xTop;     //鏂规璧峰鐐圭殑x鍧愭爣
	public int yTop;     //鏂规缁撴潫鐐圭殑y鍧愭爣
	public int xBottom;  //鏂规缁撴潫鐐圭殑x鍧愭爣
	public int yBottom;  //鏂规缁撴潫鐐圭殑y鍧愭爣
	public int bCounter; //淇濈暀
}

//璇煶瀵硅鍙傛暟
public static class NET_DVR_COMPRESSION_AUDIO extends Structure
{
	public byte  byAudioEncType;   //闊抽缂栫爜绫诲瀷 0-G722; 1-G711
	public byte[] byres= new byte[7];//杩欓噷淇濈暀闊抽鐨勫帇缂╁弬鏁�
}

//甯冮槻鍙傛暟
public static class NET_DVR_SETUPALARM_PARAM extends Structure
{
        public int dwSize;
	public byte byLevel; 
	public byte byAlarmInfoType;
	public byte byRetAlarmTypeV40;
	public byte byRetDevInfoVersion;
	public byte byRetVQDAlarmType;
	public byte byFaceAlarmDetection;
	public byte bySupport;
	public byte byBrokenNetHttp;
        public short wTaskNo;    //浠诲姟澶勭悊鍙� 鍜� (涓婁紶鏁版嵁NET_DVR_VEHICLE_RECOG_RESULT涓殑瀛楁dwTaskNo瀵瑰簲 鍚屾椂 涓嬪彂浠诲姟缁撴瀯 NET_DVR_VEHICLE_RECOG_COND涓殑瀛楁dwTaskNo瀵瑰簲)
	public byte byDeployType;    //甯冮槻绫诲瀷锛�0-瀹㈡埛绔竷闃诧紝1-瀹炴椂甯冮槻
	public byte[] byRes1= new byte[3];
	public byte byAlarmTypeURL;//bit0-琛ㄧず浜鸿劯鎶撴媿鎶ヨ涓婁紶锛圛NTER_FACESNAP_RESULT锛夛紱0-琛ㄧず浜岃繘鍒朵紶杈擄紝1-琛ㄧずURL浼犺緭锛堣澶囨敮鎸佺殑鎯呭喌涓嬶紝璁惧鏀寔鑳藉姏鏍规嵁鍏蜂綋鎶ヨ鑳藉姏闆嗗垽鏂�,鍚屾椂璁惧闇�瑕佹敮鎸乁RL鐨勭浉鍏虫湇鍔★紝褰撳墠鏄�濅簯瀛樺偍鈥滐級
                         //bit1-琛ㄧずEVENT_JSON涓浘鐗囨暟鎹暱浼犵被鍨嬶紱0-琛ㄧず浜岃繘鍒朵紶杈擄紝1-琛ㄧずURL浼犺緭锛堣澶囨敮鎸佺殑鎯呭喌涓嬶紝璁惧鏀寔鑳藉姏鏍规嵁鍏蜂綋鎶ヨ鑳藉姏闆嗗垽鏂級
	public byte byCustomCtrl;
}

//鍖哄煙妗嗗弬鏁�
public static class NET_VCA_RECT extends Structure
{
        public float fX;
        public float fY;
        public float fWidth;
        public float fHeight;
}

//鎶ヨ鐩爣淇℃伅
public static class NET_VCA_TARGET_INFO extends Structure
{
        public int dwID;
	public NET_VCA_RECT struRect;
	public byte[] byRes= new byte[4];
}

//鍓嶇璁惧淇℃伅
public static class NET_VCA_DEV_INFO extends Structure
{
        public NET_DVR_IPADDR struDevIP;
        public short wPort;
	public byte byChannel;
	public byte byIvmsChannel;
}

//浜嬩欢瑙勫垯淇℃伅
public static class NET_VCA_RULE_INFO extends Structure
{
        public byte byRuleID;
        public byte byRes;
        public short wEventTypeEx;
	public byte[] byRuleName= new byte[NAME_LEN];
        public int dwEventType;
	public NET_VCA_EVENT_UNION  uEventParam;
        public void read() {
            super.read();
            switch (wEventTypeEx) {
                case 1:
                    uEventParam.setType(NET_VCA_TRAVERSE_PLANE.class);
                    break;
                case 2:
                case 3:
                    uEventParam.setType(NET_VCA_AREA.class);
                    break;
                default:
                    break;
            }
            uEventParam.read();
        }
        public void write() {
            super.write();
            uEventParam.write();
        }

}

//璀︽垝瑙勫垯鍙傛暟鑱斿悎浣�
public static class NET_VCA_EVENT_UNION extends Union
{
    public int[] uLen = new int[23];
    public NET_VCA_TRAVERSE_PLANE struTraversePlane;
    public NET_VCA_AREA  struArea;
}

//绌胯秺璀︽垝闈㈠弬鏁�
public static class NET_VCA_TRAVERSE_PLANE extends Structure
{
    public NET_VCA_LINE struPlaneBottom;
    public int dwCrossDirection;
    public byte bySensitivity;
    public byte byPlaneHeight;
    public byte byDetectionTarget;/*妫�娴嬬洰鏍囷細0- 鎵�鏈夌洰鏍囷紝1- 浜猴紝2- 杞�   */
    public byte[] byRes2= new byte[37];
}

 public static class NET_DVR_HANDLEEXCEPTION_V40 extends Structure
 {
    public int    dwHandleType;/*澶勭悊鏂瑰紡锛屽悇绉嶅紓甯稿鐞嗘柟寮忕殑"鎴�"缁撴灉锛屽紓甯稿鐞嗘柟寮忥細
                                    0x00: 鏃犲搷搴�    0x01: 鐩戣鍣ㄤ笂璀﹀憡  0x02: 澹伴煶璀﹀憡  0x04: 涓婁紶涓績
                                    0x08: 瑙﹀彂鎶ヨ杈撳嚭  0x10: Jpeg鎶撳浘骞朵笂浼燛Mail
                                    0x20: 鏃犵嚎澹板厜鎶ヨ鍣ㄨ仈鍔�    0x40: 鑱斿姩鐢靛瓙鍦板浘(鐩墠浠匬CNVR鏀寔)
                                    0x200锛氭姄鍥惧苟涓婁紶ftp    0x400: 铏氱劍渚︽祴鑱斿姩鑱氱劍
                                    0x800: PTZ鑱斿姩璺熻釜(鐞冩満璺熻釜鐩爣)
                                    E.g. dwHandleType==0x01|0x04 琛ㄧず閰嶇疆鎶ヨ鍙戠敓鏃惰仈鍔ㄧ洃瑙嗗櫒涓婅鍛婂苟涓斿皢鎶ヨ淇℃伅涓婁紶涓績銆� */
    public int    dwMaxRelAlarmOutChanNum;/*璁惧鏈�澶ф敮鎸佺殑瑙﹀彂鎶ヨ杈撳嚭閫氶亾鏁帮紙鍙锛� */
    public int    dwRelAlarmOutChanNum ;/*宸查厤缃殑瑙﹀彂鐨勬姤璀﹁緭鍑洪�氶亾涓暟锛屽喅瀹歞wRelAlarmOut鍙栧墠澶氬皯涓暟缁勪笅鏍� */
    public int[]  dwRelAlarmOut = new int[MAX_CHANNUM_V30];/*瑙﹀彂鎶ヨ杈撳嚭閫氶亾锛屽彇鏁扮粍鍓峝wRelAlarmOutChanNum涓�硷紝
                                        鍏跺�艰〃绀烘姤璀﹁緭鍑洪�氶亾鍙�(浠�1寮�濮�)锛屽垵濮嬪�兼槸0xfffffffff(涓嶅叧鑱旈�氶亾)銆�
                                        渚嬪锛宒wRelAlarmOutChanNum=5锛屽垯鍙互閰嶇疆瑙﹀彂鎶ヨ杈撳嚭閫氶亾dwRelAlarmOut[0]~dwRelAlarmOut[4]銆� */
    public byte[] byRes = new  byte[64]; /*淇濈暀锛岀疆涓�0 */
}

public static final int MAX_ALERTLINE_NUM = 8;
public static class NET_VCA_TRAVERSE_PLANE_DETECTION extends Structure
{
    public int dwSize;
    public byte byEnable;//浣胯兘
    public byte byEnableDualVca;// 鍚敤鏀寔鏅鸿兘鍚庢绱� 0-涓嶅惎鐢紝1-鍚敤
    public byte[] byRes1 = new byte[2];
    public NET_VCA_TRAVERSE_PLANE[] struAlertParam = new NET_VCA_TRAVERSE_PLANE[MAX_ALERTLINE_NUM];  //璀︽垝绾垮弬鏁�
    public NET_DVR_SCHEDTIMEWEEK[] struAlarmSched = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];
    public NET_DVR_HANDLEEXCEPTION_V40 struHandleException;  //寮傚父澶勭悊鏂瑰紡
    public int dwMaxRelRecordChanNum ;  //鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾 鏁帮紙鍙锛夋渶澶ф敮鎸佹暟閲�
    public int dwRelRecordChanNum ;     //鎶ヨ瑙﹀彂鐨勫綍璞￠�氶亾 鏁� 瀹為檯鏀寔鐨勬暟閲�
    public int[] byRelRecordChan = new int[MAX_CHANNUM_V30];//瑙﹀彂褰曞儚鐨勯�氶亾鍙�
    public NET_DVR_SCHEDTIME[] struHolidayTime = new NET_DVR_SCHEDTIME[MAX_TIMESEGMENT_V30]; //鍋囨棩甯冮槻鏃堕棿
    public byte[] byRes2 = new byte[100];
}

public static class NET_DVR_CHANNEL_GROUP extends Structure
{
    public int dwSize;
    public int dwChannel;
    public int dwGroup;
    public byte byID;
    public byte[] byRes1 = new byte[3];
    public int dwPositionNo;
    public byte[] byRes = new byte[56];
}

//绾跨粨鏋勫弬鏁�
public static class NET_VCA_LINE extends Structure
{
        public NET_VCA_POINT struStart;
        public NET_VCA_POINT struEnd;
}

//鐐瑰潗鏍囧弬鏁�
public static class NET_VCA_POINT extends Structure
{
        public float fX;
        public float fY;
}

//杩涘叆/绂诲紑鍖哄煙鍙傛暟
public static class NET_VCA_AREA extends Structure
{
        public NET_VCA_POLYGON struRegion;
	public byte[] byRes= new byte[8];
}

//澶氳竟褰㈢粨鏋勪綋
public static class NET_VCA_POLYGON extends Structure
{
        public int dwPointNum;
	public NET_VCA_POINT[] struPos= new NET_VCA_POINT[VCA_MAX_POLYGON_POINT_NUM];
}

//琛屼负鍒嗘瀽鎶ヨ
public static class NET_VCA_RULE_ALARM extends Structure
{
        public int dwSize;
        public int dwRelativeTime;
        public int dwAbsTime;
	public NET_VCA_RULE_INFO struRuleInfo;
	public NET_VCA_TARGET_INFO struTargetInfo;
        public NET_VCA_DEV_INFO struDevInfo;
        public int dwPicDataLen;
	public byte byPicType;
	public byte byRelAlarmPicNum; //鍏宠仈閫氶亾鎶ヨ鍥剧墖鏁伴噺
	public byte bySmart;//IDS璁惧杩斿洖0(榛樿鍊�)锛孲mart Functiom Return 1
	public byte byPicTransType;        //鍥剧墖鏁版嵁浼犺緭鏂瑰紡: 0-浜岃繘鍒讹紱1-url
	public int dwAlarmID;     //鎶ヨID锛岀敤浠ユ爣璇嗛�氶亾闂村叧鑱斾骇鐢熺殑缁勫悎鎶ヨ锛�0琛ㄧず鏃犳晥
	public short wDevInfoIvmsChannelEx;     //涓嶯ET_VCA_DEV_INFO閲岀殑byIvmsChannel鍚箟鐩稿悓锛岃兘琛ㄧず鏇村ぇ鐨勫�笺�傝�佸鎴风鐢╞yIvmsChannel鑳界户缁吋瀹癸紝浣嗘槸鏈�澶у埌255銆傛柊瀹㈡埛绔増鏈浣跨敤wDevInfoIvmsChannelEx銆�
	public byte byRelativeTimeFlag;      //dwRelativeTime瀛楁鏄惁鏈夋晥  0-鏃犳晥锛� 1-鏈夋晥锛宒wRelativeTime琛ㄧずUTC鏃堕棿 
	public byte byAppendInfoUploadEnabled; //闄勫姞淇℃伅涓婁紶浣胯兘 0-涓嶄笂浼� 1-涓婁紶
        public Pointer pAppendInfo;     //鎸囧悜闄勫姞淇℃伅
        public Pointer pImage;
}

//杞︾墝璇嗗埆缁撴灉瀛愮粨鏋勪綋
public static class NET_DVR_PLATE_INFO extends Structure
{
          public byte byPlateType;
          public byte byColor;
          public byte byBright;
          public byte byLicenseLen;
          public byte byEntireBelieve;
          public byte byRegion;
          public byte byCountry;
          public byte byArea;                         //鍖哄煙锛堢渷浠斤級锛屽悇鍥藉鍐呴儴鍖哄煙鏋氫妇锛岄樋鑱旈厠鍙傜収 EMI_AREA
          public byte byPlateSize;                    //杞︾墝灏哄锛�0~鏈煡锛�1~long, 2~short(涓笢杞︾墝浣跨敤)
          public byte[] byRes = new byte[15];                       //淇濈暀
          public byte[] sPlateCategory = new byte[8];//杞︾墝闄勫姞淇℃伅, 鍗充腑涓滆溅鐗屼腑杞︾墝鍙风爜鏃佽竟鐨勫皬瀛椾俊鎭紝(鐩墠鍙湁涓笢鍦板尯鏀寔)
	  public int dwXmlLen;                        //XML鎶ヨ淇℃伅闀垮害
          public Pointer pXmlBuf;    
          public NET_VCA_RECT struPlateRect;
          public byte[] sLicense = new byte[MAX_LICENSE_LEN];
          public byte[] byBelieve = new byte[MAX_LICENSE_LEN];
}

//杞﹁締淇℃伅鍙傛暟
 public static class NET_DVR_VEHICLE_INFO extends Structure
 {
	   public int dwIndex;
	   public byte byVehicleType;
	   public byte byColorDepth;
	   public byte byColor;
	   public byte byRadarState;
	   public short wSpeed;
	   public short wLength;
           public byte byIllegalType;
	   public byte byVehicleLogoRecog;
	   public byte byVehicleSubLogoRecog; //杞﹁締鍝佺墝瀛愮被鍨嬭瘑鍒紱鍙傝�僔SB_VOLKSWAGEN_CLASS绛夊瓙绫诲瀷鏋氫妇銆�
	   public byte byVehicleModel; //杞﹁締瀛愬搧鐗屽勾娆撅紝0-鏈煡锛屽弬鑰�"杞﹁締瀛愬搧鐗屽勾娆�.xlsx"
	   public byte[] byCustomInfo = new byte[16];
	   public short wVehicleLogoRecog;  //杞﹁締涓诲搧鐗岋紝鍙傝��"杞﹁締涓诲搧鐗�.xlsx" (璇ュ瓧娈靛吋瀹筨yVehicleLogoRecog);
           public byte[] byRes3= new byte[14];
  }

//浜ら�氭姄鎷嶇粨鏋滀俊鎭�
public static class NET_DVR_PLATE_RESULT extends Structure
{
	  public int dwSize;
	  public byte byResultType;
	  public byte byChanIndex;
          public short wAlarmRecordID;
	  public int dwRelativeTime;
	  public byte[] byAbsTime = new byte[32];
	  public int dwPicLen;
	  public int dwPicPlateLen;
          public int dwVideoLen;
          public byte byTrafficLight;
          public byte byPicNum;
          public byte byDriveChan;
          public byte byVehicleType;
	  public int dwBinPicLen;
          public int dwCarPicLen;
	  public int dwFarCarPicLen;
          public Pointer  pBuffer3;
          public Pointer  pBuffer4;
	  public Pointer pBuffer5;
          public byte byRelaLaneDirectionType;
          public byte byCarDirectionType; //杞﹁締鍏蜂綋琛岄┒鐨勬柟鍚戯紝0琛ㄧず浠庝笂寰�涓嬶紝1琛ㄧず浠庝笅寰�涓婏紙鏍规嵁瀹為檯杞﹁締鐨勮椹舵柟鍚戞潵鐨勫尯鍒嗭級   
          public byte[] byRes3 = new byte[6];
	  public NET_DVR_PLATE_INFO struPlateInfo;
	  public NET_DVR_VEHICLE_INFO struVehicleInfo;
	  public Pointer pBuffer1;
          public Pointer pBuffer2;
}


public static class NET_DVR_TIME_V30 extends Structure
{
          public short wYear;
	  public byte byMonth;
	  public byte byDay;
	  public byte byHour;
	  public byte byMinute;
	  public byte bySecond;
	  public byte byRes;
          public short wMilliSec;
	  public byte[] byRes1 = new byte[2];
 }

public static class NET_ITS_PICTURE_INFO extends Structure
{
         public int dwDataLen;
         public byte byType;
         public byte byDataType;
         public byte byCloseUpType;
         public byte byPicRecogMode;
         public int dwRedLightTime;
         public byte[] byAbsTime = new byte[32];
         public NET_VCA_RECT   struPlateRect;
         public NET_VCA_RECT   struPlateRecgRect;
         public Pointer pBuffer;
         public int dwUTCTime;//UTC鏃堕棿
         public byte byCompatibleAblity;//鍏煎鑳藉姏瀛楁锛屾寜浣嶈〃绀猴紝鍊硷細0- 鏃犳晥锛�1- 鏈夋晥
         public byte byTimeDiffFlag;      /*鏃跺樊瀛楁鏄惁鏈夋晥  0-鏃跺樊鏃犳晥锛� 1-鏃跺樊鏈夋晥 */
         public byte cTimeDifferenceH;         /*涓嶶TC鐨勬椂宸紙灏忔椂锛夛紝-12 ... +14锛� +琛ㄧず涓滃尯,锛宐yTimeDiffFlag涓�1鏃舵湁鏁�*/
         public byte cTimeDifferenceM;      	/*涓嶶TC鐨勬椂宸紙鍒嗛挓锛夛紝-30, 30, 45锛� +琛ㄧず涓滃尯锛宐yTimeDiffFlag涓�1鏃舵湁鏁�*/
         public byte[] byRes2 = new byte[4];
}


public static class NET_ITS_PLATE_RESULT extends Structure
 {
	  public int dwSize;
	  public int dwMatchNo;
	  public byte byGroupNum;
	  public byte byPicNo;
	  public byte bySecondCam;
	  public byte byFeaturePicNo;
	  public byte byDriveChan;
	  public byte byVehicleType;
	  public byte byDetSceneID;
	  public byte byVehicleAttribute;
          public short wIllegalType;
	  public byte[] byIllegalSubType = new byte[8];
	  public byte byPostPicNo;
	  public byte byChanIndex;
          public short wSpeedLimit;
	  public byte byChanIndexEx;//byChanIndexEx*256+byChanIndex琛ㄧず鐪熷疄閫氶亾鍙枫��
	  public byte byRes2;
	  public NET_DVR_PLATE_INFO   struPlateInfo;
	  public NET_DVR_VEHICLE_INFO   struVehicleInfo;
	  public byte[] byMonitoringSiteID= new byte[48];
 	  public byte[] byDeviceID= new byte[48];
	  public byte byDir;
	  public byte byDetectType;
	  public byte byRelaLaneDirectionType;
	  public byte byCarDirectionType;
	  public int dwCustomIllegalType;
	  public Pointer pIllegalInfoBuf;
	  public byte byIllegalFromatType;
	  public byte byPendant;
	  public byte byDataAnalysis;
	  public byte byYellowLabelCar;
	  public byte byDangerousVehicles;
	  public byte byPilotSafebelt;
	  public byte byCopilotSafebelt;
	  public byte byPilotSunVisor;
	  public byte byCopilotSunVisor;
	  public byte byPilotCall;
	  public byte byBarrierGateCtrlType;
	  public byte byAlarmDataType;
	  public NET_DVR_TIME_V30  struSnapFirstPicTime;
	  public int dwIllegalTime;
	  public int dwPicNum;
	  public NET_ITS_PICTURE_INFO[] struPicInfo= new NET_ITS_PICTURE_INFO[6];
}

public int MAX_PARKNO_LEN = 16; //杞︿綅缂栧彿闀垮害
public int MAX_ID_LEN = 48; //缂栧彿鏈�澶ч暱搴�

//鍋滆溅鍦烘暟鎹笂浼�
public static class NET_ITS_PARK_VEHICLE extends Structure
{
	  public int dwSize; //缁撴瀯闀垮害
	  public byte byGroupNum; //鍥剧墖缁勬暟閲忥紙鍗曟杞鎶撴媿鐨勫浘鐗囨暟閲忥級
	  public byte byPicNo; //杩炴媿鐨勫浘鐗囩粍涓婁紶鍥剧墖搴忓彿锛堟帴鏀跺埌鍥剧墖缁勬暟閲忓悗锛岃〃绀烘帴鏀跺畬鎴�
          //鎺ユ敹瓒呮椂涓嶈冻鍥剧墖缁勬暟閲忔椂锛屾牴鎹渶瑕佷繚鐣欐垨鍒犻櫎锛�
	  public byte byLocationNum; //鍗曞紶鍥剧墖鎵�绠＄悊鐨勮溅浣嶆暟
	  public byte byParkError; //鍋滆溅寮傚父锛�0-姝ｅ父 1 寮傚父
	  public byte[] byParkingNo = new byte[MAX_PARKNO_LEN];//杞︿綅缂栧彿
	  public byte byLocationStatus; //杞︿綅杞﹁締鐘舵�侊紝0-鏃犺溅锛�1鏈夎溅
	  public byte bylogicalLaneNum;//閫昏緫杞︿綅鍙凤紝0-3锛屼竴涓浉鏈烘渶澶ц兘绠�4涓溅浣� 锛�0浠ｈ〃鏈�宸﹁竟锛�3浠ｈ〃鏈�鍙宠竟锛�
          public short wUpLoadType;//绗浂浣嶈〃绀猴細0~杞涓婁紶銆�1~鍙樺寲涓婁紶
	  public byte[] byRes1 = new byte[4]; //淇濈暀瀛楄妭
	  public int dwChanIndex; //閫氶亾鍙锋暟瀛楅�氶亾
	  public NET_DVR_PLATE_INFO   struPlateInfo;  //杞︾墝淇℃伅缁撴瀯
	  public NET_DVR_VEHICLE_INFO struVehicleInfo; //杞﹁締淇℃伅
	  public byte[] byMonitoringSiteID = new byte[MAX_ID_LEN]; //鐩戞祴鐐圭紪鍙�
	  public byte[] byDeviceID = new byte[MAX_ID_LEN]; //璁惧缂栧彿
	  public int dwPicNum; //鍥剧墖鏁伴噺锛堜笌picGroupNum涓嶅悓锛屼唬琛ㄦ湰鏉′俊鎭檮甯︾殑鍥剧墖鏁伴噺锛屽浘鐗囦俊鎭敱struVehicleInfoEx瀹氫箟
	  public NET_ITS_PICTURE_INFO[] struPicInfo = new NET_ITS_PICTURE_INFO[2];  //鍥剧墖淇℃伅,鍗曞紶鍥炶皟锛屾渶澶�2寮犲浘锛岀敱搴忓彿鍖哄垎
	  public byte[] byRes2 = new byte[256];
}

public int MAX_INTERVAL_NUM = 4;

public static class NET_DVR_SNAPCFG extends Structure {

       public int dwSize;
       public byte byRelatedDriveWay;//瑙﹀彂IO鍏宠仈鐨勮溅閬撳彿
       public byte bySnapTimes; //绾垮湀鎶撴媿娆℃暟锛�0-涓嶆姄鎷嶏紝闈�0-杩炴媿娆℃暟锛岀洰鍓嶆渶澶�5娆�
       public short wSnapWaitTime;  //鎶撴媿绛夊緟鏃堕棿锛屽崟浣峬s锛屽彇鍊艰寖鍥碵0,60000]
       public short[] wIntervalTime = new short[MAX_INTERVAL_NUM];//杩炴媿闂撮殧鏃堕棿锛宮s
       public int dwSnapVehicleNum; //鎶撴媿杞﹁締搴忓彿銆�
       public NET_DVR_JPEGPARA  struJpegPara;//鎶撴媿鍥剧墖鍙傛暟
       public byte[] byRes2 = new byte[16];
}

//鍗″弬鏁伴厤缃潯浠�
public static class NET_DVR_CARD_CFG_COND extends Structure {
      public int dwSize;
      public int dwCardNum;
      public byte byCheckCardNo;
      public byte[] ibyRes = new byte[31];
}

//鑾峰彇鍗″弬鏁扮殑鍙戦�佹暟鎹�
public static class NET_DVR_CARD_CFG_SEND_DATA extends Structure {
      public int dwSize;
      public byte[] byCardNo = new byte[32];
      public byte[] byRes = new byte[16];
}

public static class CARDRIGHTPLAN extends Structure {
   public byte[] byRightPlan = new byte[4];
}

//鍗″弬鏁�
public static class NET_DVR_CARD_CFG extends Structure {
	public int dwSize;
	public int dwModifyParamType;
	public byte[] byCardNo = new byte[32];
	public byte byCardValid;
	public byte byCardType;
	public byte byLeaderCard;
	public byte byRes1;
	public int dwDoorRight;
	public NET_DVR_VALID_PERIOD_CFG struValid;
	public int dwBelongGroup;
	public byte[] byCardPassword = new byte[8];
	public CARDRIGHTPLAN[] byCardRightPlan = new CARDRIGHTPLAN[32];
	public int dwMaxSwipeTime;
	public int dwSwipeTime;
	public short wRoomNumber;
	public short wFloorNumber;
	public byte[] byRes2 = new byte[20];
}

//鏈夋晥鏈熷弬鏁扮粨鏋勪綋
public static class NET_DVR_VALID_PERIOD_CFG extends Structure {
	public byte byEnable;
	public byte[] byRes1 = new byte[3];
	public NET_DVR_TIME_EX struBeginTime;
	public NET_DVR_TIME_EX struEndTime;
	public byte[] byRes2 = new byte[32];
}

public static class NET_DVR_TIME_EX extends Structure {
	public short wYear;
	public byte byMonth;
	public byte byDay;
	public byte byHour;
	public byte byMinute;
	public byte bySecond;
	public byte byRes;
}

//韬唤璇佷俊鎭姤璀�
public static class NET_DVR_ID_CARD_INFO_ALARM extends Structure {
	public int dwSize;        //缁撴瀯闀垮害
        public NET_DVR_ID_CARD_INFO  struIDCardCfg = new NET_DVR_ID_CARD_INFO();//韬唤璇佷俊鎭�
	public int dwMajor; //鎶ヨ涓荤被鍨嬶紝鍙傝�冨畯瀹氫箟
	public int dwMinor; //鎶ヨ娆＄被鍨嬶紝鍙傝�冨畯瀹氫箟
	public NET_DVR_TIME_V30  struSwipeTime; //鏃堕棿
	public byte[] byNetUser = new byte[MAX_NAMELEN] ;//缃戠粶鎿嶄綔鐨勭敤鎴峰悕
	public NET_DVR_IPADDR    struRemoteHostAddr ;//杩滅▼涓绘満鍦板潃
	public int dwCardReaderNo; //璇诲崱鍣ㄧ紪鍙凤紝涓�0鏃犳晥
	public int dwDoorNo; //闂ㄧ紪鍙凤紝涓�0鏃犳晥
	public int dwPicDataLen;   //鍥剧墖鏁版嵁澶у皬锛屼笉涓�0鏄〃绀哄悗闈㈠甫鏁版嵁
	public Pointer pPicData;
	public byte byCardType; //鍗＄被鍨嬶紝1-鏅�氬崱锛�2-娈嬬柧浜哄崱锛�3-榛戝悕鍗曞崱锛�4-宸℃洿鍗★紝5-鑳佽揩鍗★紝6-瓒呯骇鍗★紝7-鏉ュ鍗★紝8-瑙ｉ櫎鍗★紝涓�0鏃犳晥
	public byte byDeviceNo;                             // 璁惧缂栧彿锛屼负0鏃舵棤鏁堬紙鏈夋晥鑼冨洿1-255锛�
	public byte[] byRes2 = new byte[2];
	public int dwFingerPrintDataLen;                  // 鎸囩汗鏁版嵁澶у皬锛屼笉涓�0鏄〃绀哄悗闈㈠甫鏁版嵁
	public Pointer pFingerPrintData;
	public int dwCapturePicDataLen;                   // 鎶撴媿鍥剧墖鏁版嵁澶у皬锛屼笉涓�0鏄〃绀哄悗闈㈠甫鏁版嵁
	public Pointer pCapturePicData;
	public byte[] byRes = new byte[188];
}

public static class NET_DVR_DATE extends Structure{
	public short wYear;        //骞�
	public byte byMonth;        //鏈�
	public byte byDay;        //鏃�
}

//韬唤璇佷俊鎭�
public static class NET_DVR_ID_CARD_INFO extends Structure{
	public int dwSize;        //缁撴瀯闀垮害
	public byte[] byName = new byte[MAX_ID_NAME_LEN];   //濮撳悕
	public NET_DVR_DATE struBirth; //鍑虹敓鏃ユ湡
	public byte[] byAddr = new byte[MAX_ID_ADDR_LEN];  //浣忓潃
	public byte[] byIDNum = new byte[MAX_ID_NUM_LEN];   //韬唤璇佸彿鐮�
	public byte[] byIssuingAuthority = new byte[MAX_ID_ISSUING_AUTHORITY_LEN];  //绛惧彂鏈哄叧
	public NET_DVR_DATE struStartDate;  //鏈夋晥寮�濮嬫棩鏈�
	public NET_DVR_DATE struEndDate;  //鏈夋晥鎴鏃ユ湡
	public byte byTermOfValidity;  //鏄惁闀挎湡鏈夋晥锛� 0-鍚︼紝1-鏄紙鏈夋晥鎴鏃ユ湡鏃犳晥锛�
	public byte bySex;  //鎬у埆锛�1-鐢凤紝2-濂�
	public byte byNation;    //姘戞棌锛�1-"姹�"锛�2-"钂欏彜"锛�3-"鍥�",4-"钘�",5-"缁村惥灏�",6-"鑻�",7-"褰�",8-"澹�",9-"甯冧緷",10-"鏈濋矞",
    //11-"婊�",12-"渚�",13-"鐟�",14-"鐧�",15-"鍦熷",16-"鍝堝凹",17-"鍝堣惃鍏�",18-"鍌�",19-"榛�",20-"鍌堝兂",
    //21-"浣�",22-"鐣�",23-"楂樺北",24-"鎷夌",25-"姘�",26-"涓滀埂",27-"绾宠タ",28-"鏅",29-"鏌皵鍏嬪瓬",30-"鍦�",
    //31-"杈炬枴灏�",32-"浠浆",33-"缇�",34-"甯冩湕",35-"鎾掓媺",36-"姣涘崡",37-"浠′浆",38-"閿′集",39-"闃挎槍",40-"鏅背",
    //41-"濉斿悏鍏�",42-"鎬�",43-"涔屽瓬鍒厠",44-"淇勭綏鏂�",45-"閯傛俯鍏�",46-"寰锋槀",47-"淇濆畨",48-"瑁曞浐",49-"浜�",50-"濉斿灏�",
    //51-"鐙緳",52-"閯備鸡鏄�",53-"璧摬",54-"闂ㄥ反",55-"鐝炲反",56-"鍩鸿"
	public byte[] byRes = new byte[101];
}

//闂ㄧ涓绘満鎶ヨ淇℃伅缁撴瀯浣�
public static class NET_DVR_ACS_ALARM_INFO extends Structure{
	public int dwSize;
	public int dwMajor;
	public int dwMinor;
	public NET_DVR_TIME struTime;
	public byte[] sNetUser = new byte[MAX_NAMELEN];
	public NET_DVR_IPADDR struRemoteHostAddr;
	public NET_DVR_ACS_EVENT_INFO struAcsEventInfo;
	public int dwPicDataLen;
	public Pointer pPicData;
	public short wInductiveEventType; //褰掔撼浜嬩欢绫诲瀷锛�0-鏃犳晥锛屽鎴风鍒ゆ柇璇ュ�间负闈�0鍊煎悗锛屾姤璀︾被鍨嬮�氳繃褰掔撼浜嬩欢绫诲瀷鍖哄垎锛屽惁鍒欓�氳繃鍘熸湁鎶ヨ涓绘绫诲瀷锛坉wMajor銆乨wMinor锛夊尯鍒�
	public byte byPicTransType;        //鍥剧墖鏁版嵁浼犺緭鏂瑰紡: 0-浜岃繘鍒讹紱1-url
	public byte byRes1;             //淇濈暀瀛楄妭
	public int dwIOTChannelNo;    //IOT閫氶亾鍙�
	public byte[] byRes = new byte[16];
}

//闂ㄧ涓绘満浜嬩欢淇℃伅
public static class NET_DVR_ACS_EVENT_INFO extends Structure {
	public int dwSize;
	public byte[] byCardNo = new byte[32];
	public byte byCardType;
	public byte byWhiteListNo;
	public byte byReportChannel;
	public byte byCardReaderKind;
	public int dwCardReaderNo;
	public int dwDoorNo;
	public int dwVerifyNo;
	public int dwAlarmInNo;
	public int dwAlarmOutNo;
	public int dwCaseSensorNo;
	public int dwRs485No;
	public int dwMultiCardGroupNo;
	public short wAccessChannel;
	public byte byDeviceNo;
	public byte byDistractControlNo;
	public int dwEmployeeNo;
	public short wLocalControllerID;
	public byte byInternetAccess;
	public byte byType;
	public byte[] byRes = new byte[20];
}

public static class NET_DVR_XML_CONFIG_INPUT extends Structure {
	public int dwSize;
	public Pointer lpRequestUrl;
	public int dwRequestUrlLen;
        public Pointer lpInBuffer;
	public int dwInBufferSize;
	public int dwRecvTimeOut;
	public byte[] byRes = new byte[32];
}

public static class NET_DVR_STRING_POINTER extends Structure {
	public byte[] byString = new byte[2*1024];
}

public static class NET_DVR_XML_CONFIG_OUTPUT extends Structure {
	public int dwSize;
	public Pointer lpOutBuffer;
	public int dwOutBufferSize;
        public int dwReturnedXMLSize;
        public Pointer lpStatusBuffer;
	public int dwStatusSize;
	public byte[] byRes = new byte[32];
}

//鎶ヨ鍦烘櫙淇℃伅
public static class NET_DVR_SCENE_INFO  extends Structure {
	public int dwSceneID;              //鍦烘櫙ID, 0 - 琛ㄧず璇ュ満鏅棤鏁�
	public byte[] bySceneName = new byte[NAME_LEN];  //鍦烘櫙鍚嶇О
	public byte byDirection;            //鐩戞祴鏂瑰悜 1-涓婅锛�2-涓嬭锛�3-鍙屽悜锛�4-鐢变笢鍚戣タ锛�5-鐢卞崡鍚戝寳锛�6-鐢辫タ鍚戜笢锛�7-鐢卞寳鍚戝崡锛�8-鍏跺畠
	public byte[] byRes1 = new byte[3];              //淇濈暀
	public NET_DVR_PTZPOS  struPtzPos;             //Ptz 鍧愭爣
	public byte[] byRes2 = new byte[64] ;            //淇濈暀
}

// 鏂瑰悜缁撴瀯浣�
public static class NET_DVR_DIRECTION  extends Structure {
	public NET_VCA_POINT struStartPoint = new NET_VCA_POINT();   // 鏂瑰悜璧峰鐐�
	public NET_VCA_POINT struEndPoint = new NET_VCA_POINT();     // 鏂瑰悜缁撴潫鐐�
}

// 浜ら�氫簨浠朵俊鎭�
public static class NET_DVR_AID_INFO  extends Structure {
	public byte byRuleID;   // 瑙勫垯搴忓彿锛屼负瑙勫垯閰嶇疆缁撴瀯涓嬫爣锛�0-16
	public byte[] byRes1 = new byte[3];
	public byte[] byRuleName = new byte[NAME_LEN]; //  瑙勫垯鍚嶇О
	public int dwAIDType;  // 鎶ヨ浜嬩欢绫诲瀷
	public NET_DVR_DIRECTION struDirect; // 鎶ヨ鎸囧悜鍖哄煙
	public byte bySpeedLimit; //闄愰�熷�硷紝鍗曚綅km/h[0,255]
 	public byte byCurrentSpeed; //褰撳墠閫熷害鍊硷紝鍗曚綅km/h[0,255]
        public byte byVehicleEnterState; //杞﹁締鍑哄叆鐘舵�侊細0- 鏃犳晥锛�1- 椹跺叆锛�2- 椹跺嚭 
	public byte[] byRes2 = new byte[37];  // 淇濈暀瀛楄妭
}

public int MONITORSITE_ID_LEN = 48;
public int DEVICE_ID_LEN = 48;
public int ILLEGAL_LEN = 32; //杩濇硶浠ｇ爜闀垮害
public static class NET_DVR_TFS_ALARM  extends Structure {
	public int dwSize;
	public int dwRelativeTime;
	public int dwAbsTime;
	public int dwIllegalType;
	public int dwIllegalDuration;
	public byte[] byMonitoringSiteID = new byte[MONITORSITE_ID_LEN];
	public byte[] byDeviceID = new byte[DEVICE_ID_LEN];
	public NET_VCA_DEV_INFO struDevInfo;
	public NET_DVR_SCENE_INFO struSceneInfo;
	public NET_DVR_TIME_EX  struBeginRecTime;
	public NET_DVR_TIME_EX  struEndRecTime;
	public NET_DVR_AID_INFO  struAIDInfo;
	public NET_DVR_PLATE_INFO     struPlateInfo;
	public NET_DVR_VEHICLE_INFO    struVehicleInfo;
	public int dwPicNum;
	public NET_ITS_PICTURE_INFO[]  struPicInfo = new NET_ITS_PICTURE_INFO[8];
	public byte  bySpecificVehicleType;
	public byte  byLaneNo;
	public byte[] byRes1 = new byte[2];
	public NET_DVR_TIME_V30 struTime;
	public int dwSerialNo;
	public byte byVehicleAttribute;
	public byte byPilotSafebelt;
	public byte byCopilotSafebelt;
	public byte byPilotSunVisor;
	public byte byCopilotSunVisor;
	public byte byPilotCall;
	public byte[] byRes2 = new byte[2];
	public  byte[] byIllegalCode = new byte[ILLEGAL_LEN];
	public short wCountry; // 鍥藉绱㈠紩鍊�,鍙傜収鏋氫妇COUNTRY_INDEX
	public byte byRegion; //鍖哄煙绱㈠紩鍊�,0-淇濈暀锛�1-娆ф床(Europe Region)锛�2-淇勮鍖哄煙(Russian Region)锛�3-娆ф床&淇勭綏鏂�(EU&CIS) , 4-涓笢锛圡iddle East锛夛紝0xff-鎵�鏈�
	public byte byCrossLine;//鏄惁鍘嬬嚎鍋滆溅锛堜晶鏂瑰仠杞︼級锛�0-琛ㄧず鏈煡锛�1-涓嶅帇绾匡紝2-鍘嬬嚎
	public byte[] byParkingSerialNO = new byte[16];//娉婅溅浣嶇紪鍙�
	public byte byCrossSpaces;//鏄惁璺ㄦ硦杞︿綅鍋滆溅锛堜晶鏂瑰仠杞︼級锛�0-琛ㄧず鏈煡锛�1-鏈法娉婅溅浣嶅仠杞︼紝2-璺ㄦ硦杞︿綅鍋滆溅
	public byte byAngledParking;//鏄惁鍊炬枩鍋滆溅锛堜晶鏂瑰仠杞︼級, 0-琛ㄧず鏈煡锛�1-鏈�炬枩鍋滆溅锛�2-鍊炬枩鍋滆溅
	public byte byAlarmValidity;//鎶ヨ缃俊搴︼紝鍙互杈撳嚭椹跺叆椹跺嚭鐨勭疆淇″害锛岃寖鍥�0-100锛涚疆淇″害瓒婇珮锛屼簨浠剁湡瀹炴�ц秺楂�
	public byte[] byRes = new byte[45];
}

//浜轰綋鐗瑰緛璇嗗埆缁撴灉缁撴瀯浣�
public static class NET_VCA_HUMAN_FEATURE  extends Structure {
	public byte byAgeGroup;    //骞撮緞娈�,鍙傝 HUMAN_AGE_GROUP_ENUM
	public byte bySex;         //鎬у埆, 1 - 鐢� , 2 - 濂�
	public byte byEyeGlass;    //鏄惁鎴寸溂闀� 1 - 涓嶆埓, 2 - 鎴�
	public byte byAge;//骞撮緞 0-琛ㄧず鈥滄湭鐭モ�濓紙绠楁硶涓嶆敮鎸侊級,0xff-绠楁硶鏀寔锛屼絾鏄病鏈夎瘑鍒嚭鏉�
	public byte byAgeDeviation;//骞撮緞璇樊鍊�
	public byte byEthnic;   //瀛楁棰勭暀,鏆備笉寮�鏀�
	public byte byMask;       //鏄惁鎴村彛缃� 0-琛ㄧず鈥滄湭鐭モ�濓紙绠楁硶涓嶆敮鎸侊級,1 鈥� 涓嶆埓, 2 鈥� 鎴�, 0xff-绠楁硶鏀寔锛屼絾鏄病鏈夎瘑鍒嚭鏉�
	public byte bySmile;      //鏄惁寰瑧 0-琛ㄧず鈥滄湭鐭モ�濓紙绠楁硶涓嶆敮鎸侊級,1 鈥� 涓嶅井绗�, 2 鈥� 寰瑧, 0xff-绠楁硶鏀寔锛屼絾鏄病鏈夎瘑鍒嚭鏉�
	public byte[] byRes = new byte[8];    //淇濈暀
}

//浜鸿劯鎶撴媿缁撴灉
public static class NET_VCA_FACESNAP_RESULT  extends Structure {
	public int dwSize;             // 缁撴瀯澶у皬
	public int dwRelativeTime;     // 鐩稿鏃舵爣
	public int dwAbsTime;            // 缁濆鏃舵爣
	public int dwFacePicID;       //浜鸿劯鍥綢D
	public int dwFaceScore;        //浜鸿劯璇勫垎,0-100
	public NET_VCA_TARGET_INFO struTargetInfo;//鎶ヨ鐩爣淇℃伅
	public NET_VCA_RECT struRect;      //浜鸿劯瀛愬浘鍖哄煙
	public NET_VCA_DEV_INFO struDevInfo;    //鍓嶇璁惧淇℃伅
	public int dwFacePicLen;        //浜鸿劯瀛愬浘鐨勯暱搴︼紝涓�0琛ㄧず娌℃湁鍥剧墖锛屽ぇ浜�0琛ㄧず鏈夊浘鐗�
	public int dwBackgroundPicLen; //鑳屾櫙鍥剧殑闀垮害锛屼负0琛ㄧず娌℃湁鍥剧墖锛屽ぇ浜�0琛ㄧず鏈夊浘鐗�(淇濈暀)
	public byte bySmart;            //IDS璁惧杩斿洖0(榛樿鍊�)锛孲mart Functiom Return 1
	public byte byAlarmEndMark;//鎶ヨ缁撴潫鏍囪0-淇濈暀锛�1-缁撴潫鏍囪锛堣瀛楁缁撳悎浜鸿劯ID瀛楁浣跨敤锛岃〃绀鸿ID瀵瑰簲鐨勪笅鎶ヨ缁撴潫锛屼富瑕佹彁渚涚粰NVR浣跨敤锛岀敤浜庡垽鏂姤璀︾粨鏉燂紝鎻愬彇璇嗗埆鍥剧墖鏁版嵁涓紝娓呮櫚搴︽渶楂樼殑鍥剧墖锛�
	public byte byRepeatTimes;   //閲嶅鎶ヨ娆℃暟锛�0-鏃犳剰涔�
	public byte byUploadEventDataType;//浜鸿劯鍥剧墖鏁版嵁闀夸紶鏂瑰紡锛�0-浜岃繘鍒舵暟鎹紝1-URL
	public NET_VCA_HUMAN_FEATURE struFeature;  //浜轰綋灞炴��
	public float   fStayDuration;  //鍋滅暀鐢婚潰涓椂闂�(鍗曚綅: 绉�)
	public byte[] sStorageIP = new byte[16];        //瀛樺偍鏈嶅姟IP鍦板潃
	public short wStoragePort;            //瀛樺偍鏈嶅姟绔彛鍙�
	public short wDevInfoIvmsChannelEx;     //涓嶯ET_VCA_DEV_INFO閲岀殑byIvmsChannel鍚箟鐩稿悓锛岃兘琛ㄧず鏇村ぇ鐨勫�笺�傝�佸鎴风鐢╞yIvmsChannel鑳界户缁吋瀹癸紝浣嗘槸鏈�澶у埌255銆傛柊瀹㈡埛绔増鏈浣跨敤wDevInfoIvmsChannelEx銆�
	public byte byFacePicQuality;
	public byte byUIDLen;     // 涓婁紶鎶ヨ鐨勬爣璇嗛暱搴�
	public byte byRes1;     // 淇濈暀瀛楄妭
    /*闄勫姞淇℃伅鏍囪瘑浣嶏紙鍗虫槸鍚︽湁NET_VCA_FACESNAP_ADDINFO缁撴瀯浣擄級,0-鏃犻檮鍔犱俊鎭�, 1-鏈夐檮鍔犱俊鎭��*/
	public byte byAddInfo;
        public Pointer pUIDBuffer;  //鏍囪瘑鎸囬拡
    //闄勫姞淇℃伅鎸囬拡,鎸囧悜NET_VCA_FACESNAP_ADDINFO缁撴瀯浣�
        public Pointer pAddInfoBuffer;
	public byte byTimeDiffFlag;      /*鏃跺樊瀛楁鏄惁鏈夋晥  0-鏃跺樊鏃犳晥锛� 1-鏃跺樊鏈夋晥 */
	public byte cTimeDifferenceH;         /*涓嶶TC鐨勬椂宸紙灏忔椂锛夛紝-12 ... +14锛� +琛ㄧず涓滃尯,锛宐yTimeDiffFlag涓�1鏃舵湁鏁�*/
	public byte cTimeDifferenceM;      	/*涓嶶TC鐨勬椂宸紙鍒嗛挓锛夛紝-30, 30, 45锛� +琛ㄧず涓滃尯锛宐yTimeDiffFlag涓�1鏃舵湁鏁�*/
	public byte byBrokenNetHttp;     //鏂綉缁紶鏍囧織浣嶏紝0-涓嶆槸閲嶄紶鏁版嵁锛�1-閲嶄紶鏁版嵁
        public Pointer pBuffer1;  //浜鸿劯瀛愬浘鐨勫浘鐗囨暟鎹�
        public Pointer pBuffer2;  //鑳屾櫙鍥剧殑鍥剧墖鏁版嵁锛堜繚鐣欙紝閫氳繃鏌ユ壘鑳屾櫙鍥炬帴鍙ｅ彲浠ヨ幏鍙栬儗鏅浘锛�
}

//浜鸿劯鎶撴媿淇℃伅
public static class NET_VCA_FACESNAP_INFO_ALARM extends Structure {
       public int dwRelativeTime;     // 鐩稿鏃舵爣
       public int dwAbsTime;            // 缁濆鏃舵爣
       public int dwSnapFacePicID;       //鎶撴媿浜鸿劯鍥綢D
       public int dwSnapFacePicLen;        //鎶撴媿浜鸿劯瀛愬浘鐨勯暱搴︼紝涓�0琛ㄧず娌℃湁鍥剧墖锛屽ぇ浜�0琛ㄧず鏈夊浘鐗�
       public NET_VCA_DEV_INFO struDevInfo;        //鍓嶇璁惧淇℃伅
       public byte byFaceScore;        //浜鸿劯璇勫垎锛屾寚浜鸿劯瀛愬浘鐨勮川閲忕殑璇勫垎,0-100
       public byte bySex;//鎬у埆锛�0-鏈煡锛�1-鐢凤紝2-濂�
       public byte byGlasses;//鏄惁甯︾溂闀滐紝0-鏈煡锛�1-鏄紝2-鍚�
        //鎶撴媿鍥剧墖浜鸿劯骞撮緞鐨勪娇鐢ㄦ柟寮忥紝濡俠yAge涓�15,byAgeDeviation涓�1,琛ㄧず锛屽疄闄呬汉鑴稿浘鐗囧勾榫勭殑涓�14-16涔嬮棿
       public byte byAge;//骞撮緞
       public byte byAgeDeviation;//骞撮緞璇樊鍊�
       public byte byAgeGroup;//骞撮緞娈碉紝璇﹁HUMAN_AGE_GROUP_ENUM锛岃嫢浼犲叆0xff琛ㄧず鏈煡
       public byte byFacePicQuality;
       public byte byRes1;              // 淇濈暀瀛楄妭
       public int dwUIDLen; // 涓婁紶鎶ヨ鐨勬爣璇嗛暱搴�
        public Pointer pUIDBuffer;  //鏍囪瘑鎸囬拡
        public float fStayDuration;  //鍋滅暀鐢婚潰涓椂闂�(鍗曚綅: 绉�)
        public Pointer pBuffer1;  //鎶撴媿浜鸿劯瀛愬浘鐨勫浘鐗囨暟鎹�
}

//绫嶈疮鍙傛暟
public static class NET_DVR_AREAINFOCFG extends Structure {
	public short wNationalityID; //鍥界睄
	public short wProvinceID; //鐪�
	public short wCityID; //甯�
	public short wCountyID; //鍘�
	public int dwCode; //鍥藉鏍囧噯鐨勭渷浠姐�佸煄甯傘�佸幙绾т唬鐮侊紝褰撹繖涓瓧娈典笉涓�0鐨勬椂鍊欙紝浣跨敤杩欎釜鍊硷紝鏂拌澶囦笂浼犺繖涓�艰〃绀虹睄璐弬鏁帮紝鑰佽澶囪繖涓�间负0
}

//浜哄憳淇℃伅
public int MAX_HUMAN_BIRTHDATE_LEN = 10;
public static class NET_VCA_HUMAN_ATTRIBUTE extends Structure {
       public byte bySex; //鎬у埆锛�0-鐢凤紝1-濂�
       public byte byCertificateType; //璇佷欢绫诲瀷锛�0-韬唤璇侊紝1-璀﹀畼璇�
       public byte[] byBirthDate = new byte[MAX_HUMAN_BIRTHDATE_LEN]; //鍑虹敓骞存湀锛屽锛�201106
       public byte[] byName = new byte[NAME_LEN]; //濮撳悕
       public NET_DVR_AREAINFOCFG struNativePlace; //绫嶈疮鍙傛暟
       public byte[] byCertificateNumber = new byte[NAME_LEN];  //璇佷欢鍙�
       public int dwPersonInfoExtendLen;// 浜哄憳鏍囩淇℃伅鎵╁睍闀垮害
        public Pointer pPersonInfoExtend;  //浜哄憳鏍囩淇℃伅鎵╁睍淇℃伅
       public byte byAgeGroup;//骞撮緞娈碉紝璇﹁HUMAN_AGE_GROUP_ENUM锛屽浼犲叆0xff琛ㄧず鏈煡
       public byte[] byRes2 = new byte[11];
}

//榛戝悕鍗曚俊鎭�
public static class NET_VCA_BLACKLIST_INFO extends Structure {
       public int dwSize;   //缁撴瀯澶у皬
       public int dwRegisterID;  //鍚嶅崟娉ㄥ唽ID鍙凤紙鍙锛�
       public int dwGroupNo; //鍒嗙粍鍙�
       public byte byType; //榛戠櫧鍚嶅崟鏍囧織锛�0-鍏ㄩ儴锛�1-鐧藉悕鍗曪紝2-榛戝悕鍗�
       public byte byLevel; //榛戝悕鍗曠瓑绾э紝0-鍏ㄩ儴锛�1-浣庯紝2-涓紝3-楂�
       public byte[] byRes1 = new byte[2];  //淇濈暀
       public NET_VCA_HUMAN_ATTRIBUTE struAttribute;  //浜哄憳淇℃伅
       public byte[] byRemark = new byte[NAME_LEN]; //澶囨敞淇℃伅
       public int dwFDDescriptionLen;//浜鸿劯搴撴弿杩版暟鎹暱搴�
       public Pointer pFDDescriptionBuffer;//浜鸿劯搴撴弿杩版暟鎹寚閽�
       public int dwFCAdditionInfoLen;//鎶撴媿搴撻檮鍔犱俊鎭暱搴�
       public Pointer pFCAdditionInfoBuffer;//鎶撴媿搴撻檮鍔犱俊鎭暟鎹寚閽�
       public byte[] byRes2 = new byte[4];
}

//榛戝悕鍗曟姤璀︿俊鎭�
public static class NET_VCA_BLACKLIST_INFO_ALARM extends Structure {
       public NET_VCA_BLACKLIST_INFO struBlackListInfo; //榛戝悕鍗曞熀鏈俊鎭�
       public int dwBlackListPicLen;       //榛戝悕鍗曚汉鑴稿瓙鍥剧殑闀垮害锛屼负0琛ㄧず娌℃湁鍥剧墖锛屽ぇ浜�0琛ㄧず鏈夊浘鐗�
       public int dwFDIDLen;// 浜鸿劯搴揑D闀垮害
        public Pointer pFDID;  //浜鸿劯搴揑d鎸囬拡
       public int dwPIDLen;// 浜鸿劯搴撳浘鐗嘔D闀垮害
        public Pointer pPID;  //浜鸿劯搴撳浘鐗嘔D鎸囬拡
       public short wThresholdValue; //浜鸿劯搴撻槇鍊糩0,100]
       public byte[] byRes = new byte[2];              // 淇濈暀瀛楄妭
       public Pointer pBuffer1;  //榛戝悕鍗曚汉鑴稿瓙鍥剧殑鍥剧墖鏁版嵁
}


//榛戝悕鍗曟瘮瀵圭粨鏋滄姤璀︿笂浼�
public static class NET_VCA_FACESNAP_MATCH_ALARM extends Structure {
	public int dwSize;             // 缁撴瀯澶у皬
	public float fSimilarity; //鐩镐技搴︼紝[0.001,1]
	public NET_VCA_FACESNAP_INFO_ALARM  struSnapInfo; //鎶撴媿淇℃伅
	public NET_VCA_BLACKLIST_INFO_ALARM struBlackListInfo; //榛戝悕鍗曚俊鎭�
	public byte[] sStorageIP = new byte[16];        //瀛樺偍鏈嶅姟IP鍦板潃
	public short wStoragePort;            //瀛樺偍鏈嶅姟绔彛鍙�
	public byte byMatchPicNum; //鍖归厤鍥剧墖鐨勬暟閲忥紝0-淇濈暀锛堣�佽澶囪繖涓�奸粯璁�0锛屾柊璁惧杩欎釜鍊间负0鏃惰〃绀哄悗缁病鏈夊尮閰嶇殑鍥剧墖淇℃伅锛�
	public byte byPicTransType;//鍥剧墖鏁版嵁浼犺緭鏂瑰紡: 0-浜岃繘鍒讹紱1-url
	public int dwSnapPicLen;//璁惧璇嗗埆鎶撴媿鍥剧墖闀垮害
        public Pointer pSnapPicBuffer;//璁惧璇嗗埆鎶撴媿鍥剧墖鎸囬拡
	public NET_VCA_RECT  struRegion;//鐩爣杈圭晫妗嗭紝璁惧璇嗗埆鎶撴媿鍥剧墖涓紝浜鸿劯瀛愬浘鍧愭爣
	public int dwModelDataLen;//寤烘ā鏁版嵁闀垮害
        public Pointer pModelDataBuffer;// 寤烘ā鏁版嵁鎸囬拡
	public byte byModelingStatus;// 寤烘ā鐘舵��
	public byte byLivenessDetectionStatus;//娲讳綋妫�娴嬬姸鎬侊細0-淇濈暀锛�1-鏈煡锛堟娴嬪け璐ワ級锛�2-闈炵湡浜轰汉鑴革紝3-鐪熶汉浜鸿劯锛�4-鏈紑鍚椿浣撴娴�
	public byte cTimeDifferenceH;         /*涓嶶TC鐨勬椂宸紙灏忔椂锛夛紝-12 ... +14锛� +琛ㄧず涓滃尯,0xff鏃犳晥*/
	public byte cTimeDifferenceM;      	/*涓嶶TC鐨勬椂宸紙鍒嗛挓锛夛紝-30, 30, 45锛� +琛ㄧず涓滃尯锛�0xff鏃犳晥*/
	public byte byMask;                //鎶撴媿鍥炬槸鍚︽埓鍙ｇ僵锛�0-淇濈暀锛�1-鏈煡锛�2-涓嶆埓鍙ｇ僵锛�3-鎴村彛缃�
	public byte bySmile;               //鎶撴媿鍥炬槸鍚﹀井绗戯紝0-淇濈暀锛�1-鏈煡锛�2-涓嶅井绗戯紝3-寰瑧
	public byte byContrastStatus;      //姣斿缁撴灉锛�0-淇濈暀锛�1-姣斿鎴愬姛锛�2-姣斿澶辫触
	public byte byBrokenNetHttp;     //鏂綉缁紶鏍囧織浣嶏紝0-涓嶆槸閲嶄紶鏁版嵁锛�1-閲嶄紶鏁版嵁
}

//浜ら�氫簨浠舵姤璀�(鎵╁睍)
public static class NET_DVR_AID_ALARM_V41 extends Structure {
	public int dwSize;              //缁撴瀯闀垮害
	public int dwRelativeTime;        //鐩稿鏃舵爣
	public int dwAbsTime;            //缁濆鏃舵爣
	public NET_VCA_DEV_INFO   struDevInfo;            //鍓嶇璁惧淇℃伅
	public NET_DVR_AID_INFO   struAIDInfo;         //浜ら�氫簨浠朵俊鎭�
	public NET_DVR_SCENE_INFO struSceneInfo;       //鍦烘櫙淇℃伅
	public int dwPicDataLen;        //鍥剧墖闀垮害
        public Pointer pImage;             //鎸囧悜鍥剧墖鐨勬寚閽�
        // 0-鏁版嵁鐩存帴涓婁紶; 1-浜戝瓨鍌ㄦ湇鍔″櫒URL(3.7Ver)鍘熷厛鐨勫浘鐗囨暟鎹彉鎴怳RL鏁版嵁锛屽浘鐗囬暱搴﹀彉鎴怳RL闀垮害
	public byte byDataType;
	public byte byLaneNo;  //鍏宠仈杞﹂亾鍙�
	public short wMilliSecond;        //鏃舵爣姣
        //鐩戞祴鐐圭紪鍙凤紙璺彛缂栧彿銆佸唴閮ㄧ紪鍙凤級
	public byte[] byMonitoringSiteID = new byte[MONITORSITE_ID_LEN/*48*/];
	public byte[] byDeviceID = new byte[DEVICE_ID_LEN/*48*/];//璁惧缂栧彿
	public int dwXmlLen;//XML鎶ヨ淇℃伅闀垮害
        public Pointer pXmlBuf;// XML鎶ヨ淇℃伅鎸囬拡,鍏禭ML瀵瑰簲鍒癊ventNotificationAlert XML Block
	public byte[] byRes = new byte[20]; // 淇濈暀瀛楄妭
}

//浜ら�氱粺璁′俊鎭姤璀�(鎵╁睍)
public static class NET_DVR_TPS_ALARM_V41 extends Structure {
	public int dwSize;          // 缁撴瀯浣撳ぇ灏�
	public int dwRelativeTime;  // 鐩稿鏃舵爣
	public int dwAbsTime;       // 缁濆鏃舵爣
	public NET_VCA_DEV_INFO      struDevInfo;     // 鍓嶇璁惧淇℃伅
	public NET_DVR_TPS_INFO_V41  struTPSInfo;     // 浜ら�氬弬鏁扮粺璁′俊鎭�
    //鐩戞祴鐐圭紪鍙凤紙璺彛缂栧彿銆佸唴閮ㄧ紪鍙凤級
	public byte[] byMonitoringSiteID = new byte[MONITORSITE_ID_LEN/*48*/];
	public byte[] byDeviceID = new byte[DEVICE_ID_LEN/*48*/];//璁惧缂栧彿
	public int dwStartTime;  // 寮�濮嬬粺璁℃椂闂�
	public int dwStopTime;    // 缁撴潫缁熻鏃堕棿
	public byte[] byRes = new byte[24];      // 淇濈暀
}

public static class NET_DVR_LANE_PARAM_V41 extends Structure {
	public byte[] byRuleName = new byte[NAME_LEN]; // 杞﹂亾瑙勫垯鍚嶇О
	public byte byRuleID;             // 瑙勫垯搴忓彿锛屼负瑙勫垯閰嶇疆缁撴瀯涓嬫爣锛�0-7
	public byte byLaneType;             // 杞﹂亾涓婅鎴栦笅琛�
	public byte byTrafficState;       // 杞﹂亾鐨勪氦閫氱姸鎬侊紝0-鏃犳晥锛�1-鐣呴�氾紝2-鎷ユ尋锛�3-鍫靛
	public byte byLaneNo;            //杞﹂亾鍙�
	public int dwVaryType;           // 杞﹂亾浜ら�氬弬鏁板彉鍖栫被鍨嬪弬鐓�  TRAFFIC_DATA_VARY_TYPE_EX_ENUM锛屾寜浣嶅尯鍒�
	public int dwTpsType;            // 鏁版嵁鍙樺寲绫诲瀷鏍囧織锛岃〃绀哄綋鍓嶄笂浼犵殑缁熻鍙傛暟涓紝鍝簺鏁版嵁鏈夋晥锛屽弬鐓TS_TPS_TYPE,鎸変綅鍖哄垎
	public int dwLaneVolume;         // 杞﹂亾娴侀噺锛岀粺璁℃湁澶氬皯杞﹀瓙閫氳繃
	public int dwLaneVelocity;       // 杞﹂亾閫熷害锛屽叕閲岃绠�
	public int dwTimeHeadway ;       // 杞﹀ご鏃惰窛锛屼互绉掕绠�
	public int dwSpaceHeadway;       // 杞﹀ご闂磋窛锛屼互绫虫潵璁＄畻
	public float fSpaceOccupyRation;   // 杞﹂亾鍗犳湁鐜囷紝鐧惧垎姣旇绠楋紙绌洪棿涓�)
	public float fTimeOccupyRation;    // 鏃堕棿鍗犳湁鐜囷紝鐧惧垎姣旇绠�
	public int dwLightVehicle;       // 灏忓瀷杞︽暟閲�
	public int dwMidVehicle;         // 涓瀷杞︽暟閲�
	public int dwHeavyVehicle;       // 閲嶅瀷杞︽暟閲�
	public NET_DVR_LANE_QUEUE struLaneQueue;        // 杞﹂亾闃熷垪闀垮害
	public NET_VCA_POINT      struRuleLocation;     // 瑙勫垯浣嶇疆铏氭嫙绾垮湀鐨勪腑蹇�
	public int dwOversizeVehicle;    // 澶у瀷杞︽暟閲�
	public byte[] byRes2 = new byte[60];           // 淇濈暀
}

public int  MAX_TPS_RULE = 8;     // 鏈�澶у弬鏁拌鍒欐暟鐩�
public static class NET_DVR_TPS_INFO_V41 extends Structure {
	public int dwLanNum;          // 浜ら�氬弬鏁扮殑杞﹂亾鏁扮洰
	public NET_DVR_LANE_PARAM_V41[]  struLaneParam= new NET_DVR_LANE_PARAM_V41[MAX_TPS_RULE];
	public int dwSceneID;//鍦烘櫙ID
	public byte[] byRes = new byte[28];         //淇濈暀
}

// 杞﹂亾闃熷垪缁撴瀯浣�
public static class NET_DVR_LANE_QUEUE extends Structure {
	public NET_VCA_POINT   struHead;       //闃熷垪澶�
	public NET_VCA_POINT   struTail;       //闃熷垪灏�
	public int dwLength;      //瀹為檯闃熷垪闀垮害 鍗曚綅涓虹背 [0-500]
}


 /***API鍑芥暟澹版槑,璇︾粏璇存槑瑙丄PI鎵嬪唽***/
   public static interface FRealDataCallBack_V30 extends StdCallCallback {
        public void invoke(NativeLong lRealHandle, int dwDataType,
                ByteByReference pBuffer, int dwBufSize, Pointer pUser);
    }

   public static interface FMSGCallBack extends StdCallCallback {
        public void invoke(NativeLong lCommand, NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser);
    }

   public static interface FMSGCallBack_V31 extends StdCallCallback {
        public boolean invoke(NativeLong lCommand, NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser);
        
    }

   public static interface FMessCallBack extends StdCallCallback {
        public boolean invoke(NativeLong lCommand,String sDVRIP,String pBuf,int dwBufLen);
    }

   public static interface FMessCallBack_EX extends StdCallCallback {
        public boolean invoke(NativeLong lCommand,NativeLong lUserID,String pBuf,int dwBufLen);
    }

   public static interface FMessCallBack_NEW extends StdCallCallback {
        public boolean invoke(NativeLong lCommand,String sDVRIP,String pBuf,int dwBufLen, short dwLinkDVRPort);
    }

   public static interface FMessageCallBack extends StdCallCallback {
        public boolean invoke(NativeLong lCommand,String sDVRIP,String pBuf,int dwBufLen, int dwUser);
    }

      public static interface FExceptionCallBack extends StdCallCallback {
        public void invoke(int dwType, NativeLong lUserID, NativeLong lHandle, Pointer pUser);
    }
      public static interface FDrawFun extends StdCallCallback {
        public void invoke(NativeLong lRealHandle,W32API.HDC hDc,int dwUser);
      }

    public static interface FStdDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lRealHandle, int dwDataType, ByteByReference pBuffer,int dwBufSize,int dwUser);
      }

    public static interface FPlayDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lPlayHandle, int dwDataType, ByteByReference pBuffer,int dwBufSize,int dwUser);
      }

    public static interface FVoiceDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lVoiceComHandle, String pRecvDataBuffer, int dwBufSize, byte byAudioFlag, int dwUser);
      }

    public static interface FVoiceDataCallBack_V30 extends StdCallCallback {
        public void invoke(NativeLong lVoiceComHandle, String pRecvDataBuffer, int dwBufSize, byte byAudioFlag,Pointer pUser);
      }

    public static interface FVoiceDataCallBack_MR extends StdCallCallback {
        public void invoke(NativeLong lVoiceComHandle, String pRecvDataBuffer, int dwBufSize, byte byAudioFlag, int dwUser);
      }

    public static interface FVoiceDataCallBack_MR_V30 extends StdCallCallback {
        public void invoke(NativeLong lVoiceComHandle, String pRecvDataBuffer, int dwBufSize, byte byAudioFlag, String pUser);
      }

    public static interface FVoiceDataCallBack2 extends StdCallCallback {
        public void invoke(String pRecvDataBuffer, int dwBufSize, Pointer pUser);
      }

   public static interface FSerialDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lSerialHandle,String pRecvDataBuffer,int dwBufSize,int dwUser);
      }

    public static interface FRowDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lUserID,String  sIPAddr, NativeLong lRowAmout, String pRecvDataBuffer,int dwBufSize,int dwUser);
      }

    public static interface FColLocalDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lUserID, String sIPAddr, NativeLong lColumnAmout, String pRecvDataBuffer,int dwBufSize,int dwUser);
      }

     public static interface FColGlobalDataCallBack extends StdCallCallback {
        public void invoke(NativeLong lUserID, String sIPAddr, NativeLong lColumnAmout, String pRecvDataBuffer,int dwBufSize,int dwUser);
      }

   public static interface FJpegdataCallBack extends StdCallCallback {
        public int invoke(NativeLong lCommand, NativeLong lUserID, String sDVRIP, String sJpegName, String pJpegBuf,int dwBufLen, int dwUser);
      }

    public static interface FPostMessageCallBack extends StdCallCallback {
        public int invoke(int dwType, NativeLong lIndex);
      }

 boolean  NET_DVR_Init();
 boolean  NET_DVR_Cleanup();
 boolean  NET_DVR_SetDVRMessage(int nMessage,int hWnd);
//NET_DVR_SetDVRMessage鐨勬墿灞�
 boolean  NET_DVR_SetExceptionCallBack_V30(int nMessage, int hWnd, FExceptionCallBack fExceptionCallBack, Pointer pUser);

 boolean  NET_DVR_SetDVRMessCallBack(FMessCallBack fMessCallBack);
 boolean  NET_DVR_SetDVRMessCallBack_EX(FMessCallBack_EX fMessCallBack_EX);
 boolean  NET_DVR_SetDVRMessCallBack_NEW(FMessCallBack_NEW fMessCallBack_NEW);
 boolean  NET_DVR_SetDVRMessageCallBack(FMessageCallBack fMessageCallBack, int dwUser);

 boolean  NET_DVR_SetDVRMessageCallBack_V30(FMSGCallBack fMessageCallBack, Pointer pUser);
 boolean  NET_DVR_SetDVRMessageCallBack_V31(FMSGCallBack_V31 fMessageCallBack, Pointer pUser);

 boolean  NET_DVR_SetConnectTime(int dwWaitTime, int dwTryTimes );
 boolean  NET_DVR_SetReconnect(int dwInterval, boolean bEnableRecon );
 int  NET_DVR_GetSDKVersion();
 int  NET_DVR_GetSDKBuildVersion();
 int  NET_DVR_IsSupport();
 boolean  NET_DVR_StartListen(String sLocalIP,short wLocalPort);
 boolean  NET_DVR_StopListen();

 NativeLong  NET_DVR_StartListen_V30(String sLocalIP, short wLocalPort, FMSGCallBack DataCallback , Pointer pUserData );
 boolean  NET_DVR_StopListen_V30(NativeLong lListenHandle);
 NativeLong  NET_DVR_Login(String sDVRIP,short wDVRPort,String sUserName,String sPassword,NET_DVR_DEVICEINFO lpDeviceInfo);
 NativeLong  NET_DVR_Login_V30(String sDVRIP, short wDVRPort, String sUserName, String sPassword, NET_DVR_DEVICEINFO_V30 lpDeviceInfo);
 boolean  NET_DVR_Logout(NativeLong lUserID);
 boolean  NET_DVR_Logout_V30(NativeLong lUserID);
 int  NET_DVR_GetLastError();
 String   NET_DVR_GetErrorMsg(NativeLongByReference pErrorNo );
 boolean  NET_DVR_SetShowMode(int dwShowType,int colorKey);
 boolean  NET_DVR_GetDVRIPByResolveSvr(String sServerIP, short wServerPort, String sDVRName,short wDVRNameLen,String sDVRSerialNumber,short wDVRSerialLen,String sGetIP);
 boolean   NET_DVR_GetDVRIPByResolveSvr_EX(String sServerIP, short wServerPort,  String sDVRName, short wDVRNameLen, String sDVRSerialNumber, short wDVRSerialLen,String sGetIP, IntByReference dwPort);

//棰勮鐩稿叧鎺ュ彛
 NativeLong  NET_DVR_RealPlay(NativeLong lUserID,NET_DVR_CLIENTINFO lpClientInfo);
 NativeLong  NET_DVR_RealPlay_V30(NativeLong lUserID, NET_DVR_CLIENTINFO lpClientInfo, FRealDataCallBack_V30 fRealDataCallBack_V30, Pointer pUser , boolean bBlocked );
 boolean  NET_DVR_StopRealPlay(NativeLong lRealHandle);
 boolean  NET_DVR_RigisterDrawFun(NativeLong lRealHandle,FDrawFun fDrawFun,int dwUser);
 boolean  NET_DVR_SetPlayerBufNumber(NativeLong lRealHandle,int dwBufNum);
 boolean  NET_DVR_ThrowBFrame(NativeLong lRealHandle,int dwNum);
 boolean  NET_DVR_SetAudioMode(int dwMode);
 boolean  NET_DVR_OpenSound(NativeLong lRealHandle);
 boolean  NET_DVR_CloseSound();
 boolean  NET_DVR_OpenSoundShare(NativeLong lRealHandle);
 boolean  NET_DVR_CloseSoundShare(NativeLong lRealHandle);
 boolean  NET_DVR_Volume(NativeLong lRealHandle,short wVolume);
 boolean  NET_DVR_SaveRealData(NativeLong lRealHandle,String sFileName);
 boolean  NET_DVR_StopSaveRealData(NativeLong lRealHandle);
 boolean  NET_DVR_SetRealDataCallBack(NativeLong lRealHandle,FRowDataCallBack fRealDataCallBack,int dwUser);
 boolean  NET_DVR_SetStandardDataCallBack(NativeLong lRealHandle,FStdDataCallBack fStdDataCallBack,int dwUser);
 boolean  NET_DVR_CapturePicture(NativeLong lRealHandle,String sPicFileName);//bmp

//鍔ㄦ�佺敓鎴怚甯�
 boolean  NET_DVR_MakeKeyFrame(NativeLong lUserID, NativeLong lChannel);//涓荤爜娴�
 boolean  NET_DVR_MakeKeyFrameSub(NativeLong lUserID, NativeLong lChannel);//瀛愮爜娴�

//浜戝彴鎺у埗鐩稿叧鎺ュ彛
 boolean  NET_DVR_PTZControl(NativeLong lRealHandle,int dwPTZCommand,int dwStop);
 boolean  NET_DVR_PTZControl_Other(NativeLong lUserID,NativeLong lChannel,int dwPTZCommand,int dwStop);
 boolean  NET_DVR_TransPTZ(NativeLong lRealHandle,String pPTZCodeBuf,int dwBufSize);
 boolean  NET_DVR_TransPTZ_Other(NativeLong lUserID,NativeLong lChannel,String pPTZCodeBuf,int dwBufSize);
 boolean  NET_DVR_PTZPreset(NativeLong lRealHandle,int dwPTZPresetCmd,int dwPresetIndex);
 boolean  NET_DVR_PTZPreset_Other(NativeLong lUserID,NativeLong lChannel,int dwPTZPresetCmd,int dwPresetIndex);
 boolean  NET_DVR_TransPTZ_EX(NativeLong lRealHandle,String pPTZCodeBuf,int dwBufSize);
 boolean  NET_DVR_PTZControl_EX(NativeLong lRealHandle,int dwPTZCommand,int dwStop);
 boolean  NET_DVR_PTZPreset_EX(NativeLong lRealHandle,int dwPTZPresetCmd,int dwPresetIndex);
 boolean  NET_DVR_PTZCruise(NativeLong lRealHandle,int dwPTZCruiseCmd,byte byCruiseRoute, byte byCruisePoint, short wInput);
 boolean  NET_DVR_PTZCruise_Other(NativeLong lUserID,NativeLong lChannel,int dwPTZCruiseCmd,byte byCruiseRoute, byte byCruisePoint, short wInput);
 boolean  NET_DVR_PTZCruise_EX(NativeLong lRealHandle,int dwPTZCruiseCmd,byte byCruiseRoute, byte byCruisePoint, short wInput);
 boolean  NET_DVR_PTZTrack(NativeLong lRealHandle, int dwPTZTrackCmd);
 boolean  NET_DVR_PTZTrack_Other(NativeLong lUserID, NativeLong lChannel, int dwPTZTrackCmd);
 boolean  NET_DVR_PTZTrack_EX(NativeLong lRealHandle, int dwPTZTrackCmd);
 boolean  NET_DVR_PTZControlWithSpeed(NativeLong lRealHandle, int dwPTZCommand, int dwStop, int dwSpeed);
 boolean  NET_DVR_PTZControlWithSpeed_Other(NativeLong lUserID, NativeLong lChannel, int dwPTZCommand, int dwStop, int dwSpeed);
 boolean  NET_DVR_PTZControlWithSpeed_EX(NativeLong lRealHandle, int dwPTZCommand, int dwStop, int dwSpeed);
 boolean  NET_DVR_GetPTZCruise(NativeLong lUserID,NativeLong lChannel,NativeLong lCruiseRoute, NET_DVR_CRUISE_RET lpCruiseRet);
 boolean  NET_DVR_PTZMltTrack(NativeLong lRealHandle,int dwPTZTrackCmd, int dwTrackIndex);
 boolean  NET_DVR_PTZMltTrack_Other(NativeLong lUserID,NativeLong lChannel,int dwPTZTrackCmd, int dwTrackIndex);
 boolean  NET_DVR_PTZMltTrack_EX(NativeLong lRealHandle,int dwPTZTrackCmd, int dwTrackIndex);

//鏂囦欢鏌ユ壘涓庡洖鏀�
 NativeLong  NET_DVR_FindFile(NativeLong lUserID,NativeLong lChannel,int dwFileType, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime);
 NativeLong  NET_DVR_FindNextFile(NativeLong lFindHandle,NET_DVR_FIND_DATA lpFindData);
 boolean  NET_DVR_FindClose(NativeLong lFindHandle);
 NativeLong  NET_DVR_FindNextFile_V30(NativeLong lFindHandle, NET_DVR_FINDDATA_V30 lpFindData);
 NativeLong  NET_DVR_FindFile_V30(NativeLong lUserID, NET_DVR_FILECOND pFindCond);
 boolean  NET_DVR_FindClose_V30(NativeLong lFindHandle);
//2007-04-16澧炲姞鏌ヨ缁撴灉甯﹀崱鍙风殑鏂囦欢鏌ユ壘
 NativeLong  NET_DVR_FindNextFile_Card(NativeLong lFindHandle, NET_DVR_FINDDATA_CARD lpFindData);
 NativeLong  NET_DVR_FindFile_Card(NativeLong lUserID, NativeLong lChannel, int dwFileType, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime);
 boolean  NET_DVR_LockFileByName(NativeLong lUserID, String sLockFileName);
 boolean  NET_DVR_UnlockFileByName(NativeLong lUserID, String sUnlockFileName);
 NativeLong  NET_DVR_PlayBackByName(NativeLong lUserID,String sPlayBackFileName, HWND hWnd);
 NativeLong  NET_DVR_PlayBackByTime(NativeLong lUserID,NativeLong lChannel, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime, HWND hWnd);
 boolean  NET_DVR_PlayBackControl(NativeLong lPlayHandle,int dwControlCode,int dwInValue,IntByReference LPOutValue);
 boolean  NET_DVR_StopPlayBack(NativeLong lPlayHandle);
 boolean  NET_DVR_SetPlayDataCallBack(NativeLong lPlayHandle,FPlayDataCallBack fPlayDataCallBack,int dwUser);
 boolean  NET_DVR_PlayBackSaveData(NativeLong lPlayHandle,String sFileName);
 boolean  NET_DVR_StopPlayBackSave(NativeLong lPlayHandle);
 boolean  NET_DVR_GetPlayBackOsdTime(NativeLong lPlayHandle, NET_DVR_TIME lpOsdTime);
 boolean  NET_DVR_PlayBackCaptureFile(NativeLong lPlayHandle,String sFileName);
 NativeLong  NET_DVR_GetFileByName(NativeLong lUserID,String sDVRFileName,String sSavedFileName);
 NativeLong  NET_DVR_GetFileByTime(NativeLong lUserID,NativeLong lChannel, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime, String sSavedFileName);
 boolean  NET_DVR_StopGetFile(NativeLong lFileHandle);
 int  NET_DVR_GetDownloadPos(NativeLong lFileHandle);
 int	 NET_DVR_GetPlayBackPos(NativeLong lPlayHandle);

//鍗囩骇
 NativeLong  NET_DVR_Upgrade(NativeLong lUserID, String sFileName);
 int  NET_DVR_GetUpgradeState(NativeLong lUpgradeHandle);
 int  NET_DVR_GetUpgradeProgress(NativeLong lUpgradeHandle);
 boolean  NET_DVR_CloseUpgradeHandle(NativeLong lUpgradeHandle);
 boolean  NET_DVR_SetNetworkEnvironment(int dwEnvironmentLevel);
//杩滅▼鏍煎紡鍖栫‖鐩�
 NativeLong  NET_DVR_FormatDisk(NativeLong lUserID,NativeLong lDiskNumber);
 boolean  NET_DVR_GetFormatProgress(NativeLong lFormatHandle, NativeLongByReference pCurrentFormatDisk,NativeLongByReference pCurrentDiskPos,NativeLongByReference pFormatStatic);
 boolean  NET_DVR_CloseFormatHandle(NativeLong lFormatHandle);
//鎶ヨ
 NativeLong  NET_DVR_SetupAlarmChan(NativeLong lUserID);
 boolean  NET_DVR_CloseAlarmChan(NativeLong lAlarmHandle);
 NativeLong  NET_DVR_SetupAlarmChan_V30(NativeLong lUserID);
 NativeLong  NET_DVR_SetupAlarmChan_V41(NativeLong lUserID, NET_DVR_SETUPALARM_PARAM lpSetupParam);
 boolean  NET_DVR_CloseAlarmChan_V30(NativeLong lAlarmHandle);
//璇煶瀵硅
 NativeLong  NET_DVR_StartVoiceCom(NativeLong lUserID, FVoiceDataCallBack fVoiceDataCallBack, int dwUser);
 NativeLong  NET_DVR_StartVoiceCom_V30(NativeLong lUserID, int dwVoiceChan, boolean bNeedCBNoEncData, FVoiceDataCallBack_V30 fVoiceDataCallBack, Pointer pUser);
 boolean  NET_DVR_SetVoiceComClientVolume(NativeLong lVoiceComHandle, short wVolume);
 boolean  NET_DVR_StopVoiceCom(NativeLong lVoiceComHandle);
//璇煶杞彂
 NativeLong  NET_DVR_StartVoiceCom_MR(NativeLong lUserID, FVoiceDataCallBack_MR fVoiceDataCallBack, int dwUser);
 NativeLong  NET_DVR_StartVoiceCom_MR_V30(NativeLong lUserID, int dwVoiceChan, FVoiceDataCallBack_MR_V30 fVoiceDataCallBack, Pointer pUser);
 boolean  NET_DVR_VoiceComSendData(NativeLong lVoiceComHandle, String pSendBuf, int dwBufSize);

//璇煶骞挎挱
 boolean  NET_DVR_ClientAudioStart();
 boolean  NET_DVR_ClientAudioStart_V30(FVoiceDataCallBack2 fVoiceDataCallBack2, Pointer pUser);
 boolean  NET_DVR_ClientAudioStop();
 boolean  NET_DVR_AddDVR(NativeLong lUserID);
 NativeLong  NET_DVR_AddDVR_V30(NativeLong lUserID, int dwVoiceChan);
 boolean  NET_DVR_DelDVR(NativeLong lUserID);
 boolean  NET_DVR_DelDVR_V30(NativeLong lVoiceHandle);
////////////////////////////////////////////////////////////
//閫忔槑閫氶亾璁剧疆
 NativeLong  NET_DVR_SerialStart(NativeLong lUserID,NativeLong lSerialPort,FSerialDataCallBack fSerialDataCallBack,int dwUser);
//485浣滀负閫忔槑閫氶亾鏃讹紝闇�瑕佹寚鏄庨�氶亾鍙凤紝鍥犱负涓嶅悓閫氶亾鍙�485鐨勮缃彲浠ヤ笉鍚�(姣斿娉㈢壒鐜�)
 boolean  NET_DVR_SerialSend(NativeLong lSerialHandle, NativeLong lChannel, String pSendBuf,int dwBufSize);
 boolean  NET_DVR_SerialStop(NativeLong lSerialHandle);
 boolean  NET_DVR_SendTo232Port(NativeLong lUserID, String pSendBuf, int dwBufSize);
 boolean  NET_DVR_SendToSerialPort(NativeLong lUserID, int dwSerialPort, int dwSerialIndex, String pSendBuf, int dwBufSize);

//瑙ｇ爜 nBitrate = 16000
 Pointer  NET_DVR_InitG722Decoder(int nBitrate);
 void  NET_DVR_ReleaseG722Decoder(Pointer pDecHandle);
 boolean  NET_DVR_DecodeG722Frame(Pointer pDecHandle, String pInBuffer, String pOutBuffer);
//缂栫爜
 Pointer  NET_DVR_InitG722Encoder();
 boolean  NET_DVR_EncodeG722Frame(Pointer pEncodeHandle,String pInBuff,String pOutBuffer);
 void  NET_DVR_ReleaseG722Encoder(Pointer pEncodeHandle);

//杩滅▼鎺у埗鏈湴鏄剧ず
 boolean  NET_DVR_ClickKey(NativeLong lUserID, NativeLong lKeyIndex);
//杩滅▼鎺у埗璁惧绔墜鍔ㄥ綍鍍�
 boolean  NET_DVR_StartDVRRecord(NativeLong lUserID,NativeLong lChannel,NativeLong lRecordType);
 boolean  NET_DVR_StopDVRRecord(NativeLong lUserID,NativeLong lChannel);
//瑙ｇ爜鍗�
 boolean  NET_DVR_InitDevice_Card(NativeLongByReference pDeviceTotalChan);
 boolean  NET_DVR_ReleaseDevice_Card();
 boolean  NET_DVR_InitDDraw_Card(int hParent,int colorKey);
 boolean  NET_DVR_ReleaseDDraw_Card();
 NativeLong  NET_DVR_RealPlay_Card(NativeLong lUserID,NET_DVR_CARDINFO lpCardInfo,NativeLong lChannelNum);
 boolean  NET_DVR_ResetPara_Card(NativeLong lRealHandle,NET_DVR_DISPLAY_PARA lpDisplayPara);
 boolean  NET_DVR_RefreshSurface_Card();
 boolean  NET_DVR_ClearSurface_Card();
 boolean  NET_DVR_RestoreSurface_Card();
 boolean  NET_DVR_OpenSound_Card(NativeLong lRealHandle);
 boolean  NET_DVR_CloseSound_Card(NativeLong lRealHandle);
 boolean  NET_DVR_SetVolume_Card(NativeLong lRealHandle,short wVolume);
 boolean  NET_DVR_AudioPreview_Card(NativeLong lRealHandle,boolean bEnable);
 NativeLong  NET_DVR_GetCardLastError_Card();
 Pointer  NET_DVR_GetChanHandle_Card(NativeLong lRealHandle);
 boolean  NET_DVR_CapturePicture_Card(NativeLong lRealHandle, String sPicFileName);
//鑾峰彇瑙ｇ爜鍗″簭鍒楀彿姝ゆ帴鍙ｆ棤鏁堬紝鏀圭敤GetBoardDetail鎺ュ彛鑾峰緱(2005-12-08鏀寔)
 boolean  NET_DVR_GetSerialNum_Card(NativeLong lChannelNum,IntByReference pDeviceSerialNo);
//鏃ュ織
 NativeLong  NET_DVR_FindDVRLog(NativeLong lUserID, NativeLong lSelectMode, int dwMajorType,int dwMinorType, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime);
 NativeLong  NET_DVR_FindNextLog(NativeLong lLogHandle, NET_DVR_LOG lpLogData);
 boolean  NET_DVR_FindLogClose(NativeLong lLogHandle);
 NativeLong  NET_DVR_FindDVRLog_V30(NativeLong lUserID, NativeLong lSelectMode, int dwMajorType,int dwMinorType, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime, boolean bOnlySmart );
 NativeLong  NET_DVR_FindNextLog_V30(NativeLong lLogHandle, NET_DVR_LOG_V30 lpLogData);
 boolean  NET_DVR_FindLogClose_V30(NativeLong lLogHandle);
//鎴2004骞�8鏈�5鏃�,鍏�113涓帴鍙�
//ATM DVR
 NativeLong  NET_DVR_FindFileByCard(NativeLong lUserID,NativeLong lChannel,int dwFileType, int nFindType, String sCardNumber, NET_DVR_TIME lpStartTime, NET_DVR_TIME lpStopTime);
//鎴2004骞�10鏈�5鏃�,鍏�116涓帴鍙�

//2005-09-15
 boolean  NET_DVR_CaptureJPEGPicture(NativeLong lUserID, NativeLong lChannel, NET_DVR_JPEGPARA lpJpegPara, String sPicFileName);
//JPEG鎶撳浘鍒板唴瀛�
 boolean  NET_DVR_CaptureJPEGPicture_NEW(NativeLong lUserID, NativeLong lChannel, NET_DVR_JPEGPARA lpJpegPara, String sJpegPicBuffer, int dwPicSize,  IntByReference lpSizeReturned);


//2006-02-16
 int  NET_DVR_GetRealPlayerIndex(NativeLong lRealHandle);
 int  NET_DVR_GetPlayBackPlayerIndex(NativeLong lPlayHandle);

//2006-08-28 704-640 缂╂斁閰嶇疆
 boolean  NET_DVR_SetScaleCFG(NativeLong lUserID, int dwScale);
 boolean  NET_DVR_GetScaleCFG(NativeLong lUserID, IntByReference lpOutScale);
 boolean  NET_DVR_SetScaleCFG_V30(NativeLong lUserID, NET_DVR_SCALECFG pScalecfg);
 boolean  NET_DVR_GetScaleCFG_V30(NativeLong lUserID, NET_DVR_SCALECFG pScalecfg);
//2006-08-28 ATM鏈虹鍙ｈ缃�
 boolean  NET_DVR_SetATMPortCFG(NativeLong lUserID, short wATMPort);
 boolean  NET_DVR_GetATMPortCFG(NativeLong lUserID, ShortByReference LPOutATMPort);

//2006-11-10 鏀寔鏄惧崱杈呭姪杈撳嚭
 boolean  NET_DVR_InitDDrawDevice();
 boolean  NET_DVR_ReleaseDDrawDevice();
 NativeLong  NET_DVR_GetDDrawDeviceTotalNums();
 boolean  NET_DVR_SetDDrawDevice(NativeLong lPlayPort, int nDeviceNum);

 boolean  NET_DVR_PTZSelZoomIn(NativeLong lRealHandle, NET_DVR_POINT_FRAME pStruPointFrame);
 boolean  NET_DVR_PTZSelZoomIn_EX(NativeLong lUserID, NativeLong lChannel, NET_DVR_POINT_FRAME pStruPointFrame);

//瑙ｇ爜璁惧DS-6001D/DS-6001F
 boolean  NET_DVR_StartDecode(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECODERINFO lpDecoderinfo);
 boolean  NET_DVR_StopDecode(NativeLong lUserID, NativeLong lChannel);
 boolean  NET_DVR_GetDecoderState(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECODERSTATE lpDecoderState);
//2005-08-01
 boolean  NET_DVR_SetDecInfo(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECCFG lpDecoderinfo);
 boolean  NET_DVR_GetDecInfo(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECCFG lpDecoderinfo);
 boolean  NET_DVR_SetDecTransPort(NativeLong lUserID, NET_DVR_PORTCFG lpTransPort);
 boolean  NET_DVR_GetDecTransPort(NativeLong lUserID, NET_DVR_PORTCFG lpTransPort);
 boolean  NET_DVR_DecPlayBackCtrl(NativeLong lUserID, NativeLong lChannel, int dwControlCode, int dwInValue,IntByReference LPOutValue, NET_DVR_PLAYREMOTEFILE lpRemoteFileInfo);
 boolean  NET_DVR_StartDecSpecialCon(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECCHANINFO lpDecChanInfo);
 boolean  NET_DVR_StopDecSpecialCon(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECCHANINFO lpDecChanInfo);
 boolean  NET_DVR_DecCtrlDec(NativeLong lUserID, NativeLong lChannel, int dwControlCode);
 boolean  NET_DVR_DecCtrlScreen(NativeLong lUserID, NativeLong lChannel, int dwControl);
 boolean  NET_DVR_GetDecCurLinkStatus(NativeLong lUserID, NativeLong lChannel, NET_DVR_DECSTATUS lpDecStatus);

//澶氳矾瑙ｇ爜鍣�
//2007-11-30 V211鏀寔浠ヤ笅鎺ュ彛 //11
 boolean  NET_DVR_MatrixStartDynamic(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_DYNAMIC_DEC lpDynamicInfo);
 boolean  NET_DVR_MatrixStopDynamic(NativeLong lUserID, int dwDecChanNum);
 boolean  NET_DVR_MatrixGetDecChanInfo(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_DEC_CHAN_INFO lpInter);
 boolean  NET_DVR_MatrixSetLoopDecChanInfo(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_LOOP_DECINFO lpInter);
 boolean  NET_DVR_MatrixGetLoopDecChanInfo(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_LOOP_DECINFO lpInter);
 boolean  NET_DVR_MatrixSetLoopDecChanEnable(NativeLong lUserID, int dwDecChanNum, int dwEnable);
 boolean  NET_DVR_MatrixGetLoopDecChanEnable(NativeLong lUserID, int dwDecChanNum, IntByReference lpdwEnable);
 boolean  NET_DVR_MatrixGetLoopDecEnable(NativeLong lUserID, IntByReference lpdwEnable);
 boolean  NET_DVR_MatrixSetDecChanEnable(NativeLong lUserID, int dwDecChanNum, int dwEnable);
 boolean  NET_DVR_MatrixGetDecChanEnable(NativeLong lUserID, int dwDecChanNum, IntByReference lpdwEnable);
 boolean  NET_DVR_MatrixGetDecChanStatus(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_DEC_CHAN_STATUS lpInter);
//2007-12-22 澧炲姞鏀寔鎺ュ彛 //18
 boolean  NET_DVR_MatrixSetTranInfo(NativeLong lUserID, NET_DVR_MATRIX_TRAN_CHAN_CONFIG lpTranInfo);
 boolean  NET_DVR_MatrixGetTranInfo(NativeLong lUserID, NET_DVR_MATRIX_TRAN_CHAN_CONFIG lpTranInfo);
 boolean  NET_DVR_MatrixSetRemotePlay(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_DEC_REMOTE_PLAY lpInter);
 boolean  NET_DVR_MatrixSetRemotePlayControl(NativeLong lUserID, int dwDecChanNum, int dwControlCode, int dwInValue, IntByReference LPOutValue);
 boolean  NET_DVR_MatrixGetRemotePlayStatus(NativeLong lUserID, int dwDecChanNum, NET_DVR_MATRIX_DEC_REMOTE_PLAY_STATUS lpOuter);
//end
 boolean  NET_DVR_RefreshPlay(NativeLong lPlayHandle);
//鎭㈠榛樿鍊�
 boolean  NET_DVR_RestoreConfig(NativeLong lUserID);
//淇濆瓨鍙傛暟
 boolean  NET_DVR_SaveConfig(NativeLong lUserID);
//閲嶅惎
 boolean  NET_DVR_RebootDVR(NativeLong lUserID);
//鍏抽棴DVR
 boolean  NET_DVR_ShutDownDVR(NativeLong lUserID);
//鍙傛暟閰嶇疆 begin
 boolean  NET_DVR_GetDeviceConfig(NativeLong lUserID, int dwCommand,int dwCount, Pointer lpInBuffer, int dwInBufferSize, Pointer lpStatusList,Pointer lpOutBuffer, int dwOutBufferSize);
 boolean  NET_DVR_GetDVRConfig(NativeLong lUserID, int dwCommand,NativeLong lChannel, Pointer lpOutBuffer, int dwOutBufferSize, IntByReference lpBytesReturned);
 boolean  NET_DVR_SetDVRConfig(NativeLong lUserID, int dwCommand,NativeLong lChannel, Pointer lpInBuffer, int dwInBufferSize);
 boolean  NET_DVR_GetDVRWorkState_V30(NativeLong lUserID, NET_DVR_WORKSTATE_V30 lpWorkState);
 boolean  NET_DVR_GetDVRWorkState(NativeLong lUserID, NET_DVR_WORKSTATE lpWorkState);
 boolean  NET_DVR_SetVideoEffect(NativeLong lUserID, NativeLong lChannel, int dwBrightValue, int dwContrastValue, int dwSaturationValue, int dwHueValue);
 boolean  NET_DVR_GetVideoEffect(NativeLong lUserID, NativeLong lChannel, IntByReference pBrightValue, IntByReference pContrastValue, IntByReference pSaturationValue, IntByReference pHueValue);
 boolean  NET_DVR_ClientGetframeformat(NativeLong lUserID, NET_DVR_FRAMEFORMAT lpFrameFormat);
 boolean  NET_DVR_ClientSetframeformat(NativeLong lUserID, NET_DVR_FRAMEFORMAT lpFrameFormat);
 boolean  NET_DVR_ClientGetframeformat_V30(NativeLong lUserID, NET_DVR_FRAMEFORMAT_V30 lpFrameFormat);
 boolean  NET_DVR_ClientSetframeformat_V30(NativeLong lUserID, NET_DVR_FRAMEFORMAT_V30 lpFrameFormat);
 boolean  NET_DVR_GetAlarmOut_V30(NativeLong lUserID, NET_DVR_ALARMOUTSTATUS_V30 lpAlarmOutState);
 boolean  NET_DVR_GetAlarmOut(NativeLong lUserID, NET_DVR_ALARMOUTSTATUS lpAlarmOutState);
 boolean  NET_DVR_SetAlarmOut(NativeLong lUserID, NativeLong lAlarmOutPort,NativeLong lAlarmOutStatic);

//瑙嗛鍙傛暟璋冭妭
 boolean  NET_DVR_ClientSetVideoEffect(NativeLong lRealHandle,int dwBrightValue,int dwContrastValue, int dwSaturationValue,int dwHueValue);
 boolean  NET_DVR_ClientGetVideoEffect(NativeLong lRealHandle,IntByReference pBrightValue,IntByReference pContrastValue, IntByReference pSaturationValue,IntByReference pHueValue);

//閰嶇疆鏂囦欢
 boolean  NET_DVR_GetConfigFile(NativeLong lUserID, String sFileName);
 boolean  NET_DVR_SetConfigFile(NativeLong lUserID, String sFileName);
 boolean  NET_DVR_GetConfigFile_V30(NativeLong lUserID, String sOutBuffer, int dwOutSize, IntByReference pReturnSize);

 boolean  NET_DVR_GetConfigFile_EX(NativeLong lUserID, String sOutBuffer, int dwOutSize);
 boolean  NET_DVR_SetConfigFile_EX(NativeLong lUserID, String sInBuffer, int dwInSize);

//鍚敤鏃ュ織鏂囦欢鍐欏叆鎺ュ彛
 boolean  NET_DVR_SetLogToFile(boolean bLogEnable , String  strLogDir, boolean bAutoDel );
 boolean  NET_DVR_GetSDKState( NET_DVR_SDKSTATE pSDKState);
 boolean  NET_DVR_GetSDKAbility( NET_DVR_SDKABL pSDKAbl);
 boolean  NET_DVR_GetPTZProtocol(NativeLong lUserID, NET_DVR_PTZCFG  pPtzcfg);
//鍓嶉潰鏉块攣瀹�
 boolean  NET_DVR_LockPanel(NativeLong lUserID);
 boolean  NET_DVR_UnLockPanel(NativeLong lUserID);

 boolean  NET_DVR_SetRtspConfig(NativeLong lUserID, int dwCommand,  NET_DVR_RTSPCFG lpInBuffer, int dwInBufferSize);
 boolean  NET_DVR_GetRtspConfig(NativeLong lUserID, int dwCommand,  NET_DVR_RTSPCFG lpOutBuffer, int dwOutBufferSize);

 boolean  NET_DVR_ContinuousShoot(NativeLong lUserID, NET_DVR_SNAPCFG lpInter);

  public static interface FRemoteConfigCallback extends StdCallCallback {
  public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData);
 }

NativeLong NET_DVR_StartRemoteConfig (NativeLong lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferLen, FRemoteConfigCallback cbStateCallback, Pointer pUserData);
boolean  NET_DVR_SendRemoteConfig(NativeLong lHandle, int dwDataType, Pointer pSendBuf, int dwBufSize);
NativeLong NET_DVR_GetNextRemoteConfig (NativeLong lHandle, Pointer lpOutBuff, int dwOutBuffSize);
boolean NET_DVR_StopRemoteConfig(NativeLong lHandle);

 boolean  NET_DVR_STDXMLConfig(NativeLong lUserID, NET_DVR_XML_CONFIG_INPUT lpInputParam, NET_DVR_XML_CONFIG_OUTPUT lpOutputParam);

 //gps鐩稿叧缁撴瀯瀹氫箟
public static class TimeSegParam extends Structure{
    //GPS鏁版嵁鏌ユ壘璧峰鏃堕棿
    public NET_DVR_TIME struBeginTime;
    //GPS鏁版嵁鏌ユ壘缁撴潫鏃堕棿
    public NET_DVR_TIME struEndTime;
    //GPS鐐规椂闂撮棿闅旓紝鍗曚綅锛氱
    public int dwInterval;
    //淇濈暀
    public byte[] byRes = new byte[76];
}

//鎸夋椂闂寸偣鏌ヨ
public static class TimePointParam extends Structure{
    //GPS鏁版嵁鏌ユ壘鏃堕棿鐐�
    public NET_DVR_TIME struTimePoint ;
    //淇濈暀
    public byte[] byRes = new byte[104];
}

public static class GpsDataParamUion extends Union{
    //鎸夋椂闂存鏌ヨ
    public TimeSegParam timeSeg = new TimeSegParam();
    //鎸夋椂闂寸偣鏌ヨ
    public TimePointParam timePoint = new TimePointParam();
}

//gps鏌ヨ鍙傛暟瀹氫箟
public static class NET_DVR_GET_GPS_DATA_PARAM extends Structure {
    //鏌ユ壘鏂瑰紡锛�0- 鎸夋椂闂存鏌ユ壘GPS鏁版嵁锛�1- 鎸夋椂闂寸偣鏌ユ壘GPS鏁版嵁
    public int dwCmdType;
    public GpsDataParamUion gpsDataParam;

    public void read()
    {
        super.read();
        switch(dwCmdType)
        {
            case 0:
              gpsDataParam.setType(TimeSegParam.class);
                break;
            case 1:
              gpsDataParam.setType(TimePointParam.class);
                break;
            default:
                break;
        }
        gpsDataParam.read();
    }
    public void write()
    {
        super.write();
        gpsDataParam.write();
    }
}

//gps鏁版嵁缁撴瀯瀹氫箟
public static class NET_DVR_GPS_INFO extends Structure {
     public byte[] byDirection = new byte[2];
     public byte bySvs;
     public byte byLocateMode;
     public short wHDOP;
     public short wHeight;
     public int dwLatitude;
     public int dwLongitude;
     public int dwVehicleSpeed;
     public int dwVehicleDirection;
     public byte[] byRes= new byte[8];
}

//gps杩斿洖鏁版嵁缁撴瀯瀹氫箟
public static class NET_DVR_GPS_DATA extends Structure {
    public NET_DVR_GPS_INFO struGPSInfo;
    public NET_DVR_TIME struTime;
    public byte[] byRes = new byte[12];
}

 public static interface fGPSDataCallback extends StdCallCallback {
       public void invoke(NativeLong nHandle, int dwState, Pointer lpBuffer, int dwBufLen, Pointer pUser);
 }

 NativeLong NET_DVR_GetVehicleGpsInfo(NativeLong lUserID, NET_DVR_GET_GPS_DATA_PARAM lpGPSDataParam, fGPSDataCallback cbGPSDataCallback, Pointer pUser);
}


//鎾斁搴撳嚱鏁板０鏄�,PlayCtrl.dll
interface PlayCtrl extends StdCallLibrary
{
    PlayCtrl INSTANCE = (PlayCtrl) Native.loadLibrary("C:\\Users\\sunrenjie\\workspace\\Test1\\lib\\PlayCtrl.dll",
            PlayCtrl.class);

    public static final int STREAME_REALTIME = 0;
    public static final int STREAME_FILE = 1;

    boolean PlayM4_GetPort(NativeLongByReference nPort);
    boolean PlayM4_OpenStream(NativeLong nPort, ByteByReference pFileHeadBuf, int nSize, int nBufPoolSize);
    boolean PlayM4_InputData(NativeLong nPort, ByteByReference pBuf, int nSize);
    boolean PlayM4_CloseStream(NativeLong nPort);
    boolean PlayM4_SetStreamOpenMode(NativeLong nPort, int nMode);
    boolean PlayM4_Play(NativeLong nPort, HWND hWnd);
    boolean PlayM4_Stop(NativeLong nPort);
    boolean PlayM4_SetSecretKey(NativeLong nPort, NativeLong lKeyType, String pSecretKey, NativeLong lKeyLen);
}

//windows gdi鎺ュ彛,gdi32.dll in system32 folder, 鍦ㄨ缃伄鎸″尯鍩�,绉诲姩渚︽祴鍖哄煙绛夋儏鍐典笅浣跨敤
interface GDI32 extends W32API
{
    GDI32 INSTANCE = (GDI32) Native.loadLibrary("gdi32", GDI32.class, DEFAULT_OPTIONS);

    public static final int TRANSPARENT = 1;

    int SetBkMode(HDC hdc, int i);

    HANDLE CreateSolidBrush(int icolor);
}

//windows user32鎺ュ彛,user32.dll in system32 folder, 鍦ㄨ缃伄鎸″尯鍩�,绉诲姩渚︽祴鍖哄煙绛夋儏鍐典笅浣跨敤
interface USER32 extends W32API
{

    USER32 INSTANCE = (USER32) Native.loadLibrary("user32", USER32.class, DEFAULT_OPTIONS);

    public static final int BF_LEFT = 0x0001;
    public static final int BF_TOP = 0x0002;
    public static final int BF_RIGHT = 0x0004;
    public static final int BF_BOTTOM = 0x0008;
    public static final int BDR_SUNKENOUTER = 0x0002;
    public static final int BF_RECT = (BF_LEFT | BF_TOP | BF_RIGHT | BF_BOTTOM);

    boolean DrawEdge(HDC hdc, RECT qrc, int edge, int grfFlags);

    int FillRect(HDC hDC, RECT lprc, HANDLE hbr);
}
