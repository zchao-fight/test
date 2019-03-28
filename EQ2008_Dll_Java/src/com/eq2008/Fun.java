package com.eq2008;

import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.Structure;

//EQ2008��̬��ӿڶ���
interface DllLibrary extends StdCallLibrary {
	// 1����Ŀ����������===================================================
	// ��ӽ�Ŀ
	int User_AddProgram(int CardNum, boolean bWaitToEnd, int iPlayTime);

	// ɾ�����н�Ŀ
	boolean User_DelAllProgram(int CardNum);

	// ���ͼ����
	int User_AddBmpZone(int CardNum, User_Bmp pBmp, int iProgramIndex);

	boolean User_AddBmp(int CardNum, int iBmpPartNum, HANDLE hBitmap, User_MoveSet pMoveSet, int iProgramIndex);

	boolean User_AddBmpFile(int CardNum, int iBmpPartNum, String strFileName, User_MoveSet pMoveSet, int iProgramIndex);

	// ����ı���
	int User_AddText(int CardNum, User_Text pText, int iProgramIndex);

	// ���RTF��
	int User_AddRTF(int CardNum, User_RTF pRTF, int iProgramIndex);

	// ��ӵ����ı���
	int User_AddSingleText(int CardNum, User_SingleText pSingleText, int iProgramIndex);

	// ���ʱ����
	int User_AddTime(int CardNum, User_DateTime pDateTime, int iProgramIndex);

	// ��Ӽ�ʱ��
	int User_AddTimeCount(int CardNum, User_Timer pTimeCount, int iProgramIndex);

	// ����¶���
	int User_AddTemperature(int CardNum, User_Temperature pTemperature, int iProgramIndex);

	// ��������
	boolean User_SendToScreen(int CardNum);

	// 2��ʵʱ���º�����=================================================
	// ʵʱ�������ݽ�������
	boolean User_RealtimeConnect(int CardNum);

	// ʵʱ����ͼƬ���
	boolean User_RealtimeSendData(int CardNum, int x, int y, int iWidth, int iHeight, HANDLE hBitmap);

	// ʵʱ����ͼƬ�ļ�
	boolean User_RealtimeSendBmpData(int CardNum, int x, int y, int iWidth, int iHeight, String strFileName);

	// ʵʱ�����ı�
	boolean User_RealtimeSendText(int CardNum, int x, int y, int iWidth, int iHeight, String strText,
			User_FontSet pFontInfo);

	// ʵʱ���ͶϿ�����
	boolean User_RealtimeDisConnect(int CardNum);

	// ʵʱ��������
	boolean User_RealtimeScreenClear(int CardNum);

	// 3����ʾ�����ƺ�����==============================================
	// ����
	boolean User_OpenScreen(int CardNum);

	// ����
	boolean User_CloseScreen(int CardNum);

	// У��ʱ��
	boolean User_AdjustTime(int CardNum);

	// ���ȵ���
	boolean User_SetScreenLight(int CardNum, int iLightDegreen);

	// ���¼��������ļ�
	void User_ReloadIniFile(String strEQ2008_Dll_Set_Path);

	/**
	 * ��Ŀ�������
	 */
	public class User_PartInfo extends Structure {

		public int iX; // ���ڵ����X
		public int iY; // ���ڵ����Y
		public int iWidth; // ����Ŀ��
		public int iHeight; // ����ĸ߶�
		public int iFrameMode; // �߿����ʽ
		public int FrameColor; // �߿���ɫ
	}

	/**
	 * �������
	 */
	public class User_FontSet extends Structure {

		public String strFontName; // ���������
		public int iFontSize; // ����Ĵ�С
		public boolean bFontBold; // �����Ƿ�Ӵ�
		public boolean bFontItaic; // �����Ƿ���б��
		public boolean bFontUnderline; // �����Ƿ���»���
		public int colorFont; // �������ɫ
		public int iAlignStyle; // ���뷽ʽ
		// 0�� �����
		// 1������
		// 2���Ҷ���
		public int iVAlignerStyle; // ���¶��뷽ʽ
		// 0-������
		// 1-���¾���
		// 2-�׶���
		public int iRowSpace; // �м��
	}

	/**
	 * ��ʱ����
	 */
	public class User_Timer extends Structure {

		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public int BkColor; // ������ɫ
		public User_FontSet FontInfo = new User_FontSet(); // ��������
		public int ReachTimeYear; // ������
		public int ReachTimeMonth; // ������
		public int ReachTimeDay; // ������
		public int ReachTimeHour; // ����ʱ
		public int ReachTimeMinute; // �����
		public int ReachTimeSecond; // ������
		public boolean bDay; // �Ƿ���ʾ�� 0������ʾ 1����ʾ
		public boolean bHour; // �Ƿ���ʾСʱ
		public boolean bMin; // �Ƿ���ʾ����
		public boolean bSec; // �Ƿ���ʾ��
		public boolean bMulOrSingleLine; // ���л��Ƕ���
		public String chTitle; // �����ʾ����
	}

	/**
	 * �¶ȴ���
	 */
	public class User_Temperature extends Structure {

		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public int BkColor; // ������ɫ
		public User_FontSet FontInfo = new User_FontSet(); // ��������
		public String chTitle; // ����
		public int DisplayType; // ��ʾ��ʽ��0���� 1��C
	}

	/**
	 * ����ʱ�䴰��
	 */
	public class User_DateTime extends Structure {

		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public int BkColor; // ������ɫ
		public User_FontSet FontInfo = new User_FontSet(); // ��������
		public int iDisplayType; // ��ʾ���
		public String chTitle; // �����ʾ����
		public int bYearDisType; // ���λ��0 ��4��1��2λ
		public int bMulOrSingleLine; // ���л��Ƕ���
		public int bYear; // �Ƿ���ʾ��
		public int bMouth; // �Ƿ���ʾ��
		public int bDay; // �Ƿ���ʾ��
		public int bWeek; // �Ƿ���ʾ����
		public int bHour; // �Ƿ���ʾСʱ
		public int bMin; // �Ƿ���ʾ����
		public int bSec; // �Ƿ���ʾ����
	}

	/**
	 * ͼ�Ŀ�
	 */
	public class User_Bmp extends Structure {
		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
	}

	/**
	 * �����ı���
	 */
	public class User_SingleText extends Structure {

		public String chContent; // ��ʾ����
		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public int BkColor; // ������ɫ
		public User_FontSet FontInfo = new User_FontSet(); // ��������
		public User_MoveSet MoveSet = new User_MoveSet(); // ������ʽ����

	}

	/**
	 * �ı���
	 */
	public class User_Text extends Structure {

		public String chContent; // ��ʾ����
		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public int BkColor; // ������ɫ
		public User_FontSet FontInfo = new User_FontSet(); // ��������
		public User_MoveSet MoveSet = new User_MoveSet(); // ������ʽ����

	}

	/**
	 * RTF�ļ�
	 */
	public class User_RTF extends Structure {

		public String strFileName; // RTF�ļ���
		public User_PartInfo PartInfo = new User_PartInfo(); // ������Ϣ
		public User_MoveSet MoveSet = new User_MoveSet(); // ������ʽ����

	}

	/**
	 * ������ʽ����
	 */
	public class User_MoveSet extends Structure {

		public int iActionType; // ��Ŀ�任��ʽ
		public int iActionSpeed; // ��Ŀ�Ĳ����ٶ�
		public boolean bClear; // �Ƿ���Ҫ�������
		public int iHoldTime; // ����Ļ��ͣ����ʱ��
		public int iClearSpeed; // �����ʾ�����ٶ�
		public int iClearActionType; // ��Ŀ����ı任��ʽ
		public int iFrameTime; // ÿ֡ʱ��

	}

	boolean User_RealtimeSendData(int m_iCardNum, int x, int y, int iWidth, int iHeight, Pointer pointer);
}

// ����ͼƬ����ӿ�
interface User32 extends com.sun.jna.platform.win32.User32 {
	User32 INSTANCE = (User32) Native.loadLibrary(User32.class, W32APIOptions.ASCII_OPTIONS);

	final int IMAGE_BITMAP = 0;
	final int LR_LOADFROMFILE = 0x00000010;

	HANDLE LoadImage(HINSTANCE hinst, String lpszName, int uType, int cxDesired, int cyDesired, int fuLoad);
}

interface GDI32 extends com.sun.jna.platform.win32.GDI32 {
	GDI32 INSTANCE = (GDI32) Native.loadLibrary(GDI32.class, W32APIOptions.ASCII_OPTIONS);

	boolean DeleteObject(HANDLE handle);
}

// JNA �������ö�̬��
public class Fun {
	// ȫ�ֲ�������
	static int m_iCardNum = 1;
	static int m_iProgramIndex = -1;
	static int m_iProgramCount = 0;
	static DllLibrary m_DllLibrary = null;
	static String m_strUserPath = System.getProperty("user.dir");
	private static final User32 USER = User32.INSTANCE;
	private static final GDI32 GDI = GDI32.INSTANCE;

	// ������
	public static void main(String[] args) {
		// 1�����ض�̬��EQ2008_Dll.dll
		String strDllFileName = m_strUserPath + "\\res\\EQ2008_Dll";
		String strEQ2008_Dll_Set_Path = m_strUserPath + "\\res\\EQ2008_Dll_Set.ini";
		m_DllLibrary = (DllLibrary) Native.loadLibrary(strDllFileName, DllLibrary.class);
		m_DllLibrary.User_ReloadIniFile(strEQ2008_Dll_Set_Path);

		// 2����Ŀ����������ʾ
		//// (1)��ɾ�����н�Ŀ
		// OnDelAllProgram();
		//// (2)����ӽ�Ŀ(������Ӷ����Ŀ)
		/*
		 * for(int i=0;i<1;i++) { OnAddProgram();//��ӽ�Ŀ
		 * ////(3)����ӷ������ڵ���Ŀ,ÿ����Ŀ������Ӷ��������������λ�ò����ص� OnAddText(); //�ı���������ʾ
		 * //OnAddRTF(); //RTF��������ʾ //OnAddSingleText();//�����ı���������ʾ
		 * //OnAddbmp(); //ͼƬ��������ʾ //OnAddTime(); //ʱ�䴰������ʾ //OnAddTimeCount();
		 * //��ʱ��������ʾ //OnAddTemperature();//�¶ȴ�������ʾ } ////(3)�����ͽ�Ŀ����ʾ��
		 * OnSendToScreen();
		 */

		// 3��ʵʱ��������
		//// (0)����տ��ƿ�ԭ�н�Ŀ��ֻ��Ҫ���һ��
		OnRealtimeScreenClear();
		//// (1)����������
		if (OnRealtimeConnect()) {
			//// (2)����������
			// OnRealtimeSendData(); //ͼƬ���
			// OnRealtimeSendBmpData();//ͼƬ·��
			try {
				RandomAccessFile raf = new RandomAccessFile("c:\\swipe-number\\perNum.txt", "rw");
				int count = 0;
				int quota = 0;
				while (true) {
					// count = raf.read();
					// if (count == -1) {
					// count = 0;
					// }
					// raf.seek(0);

					String msg = raf.readLine();
					raf.seek(0);
					if (msg != null) {
						String[] contents = msg.split(",");
						quota = Integer.parseInt(contents[0]);
						count = Integer.parseInt((contents[1]));
					}
					System.out.println(quota);
					System.out.println(count);
					
					
					OnRealtimeSendText(quota, count); // �ı���Ϣ
					

				}
				//// (3)���Ͽ�����
				// OnRealtimeDisConnect();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}

		// 4����ʾ�����ƺ���
		// OnAjusttime(); //У׼��ʾ��ʱ��
		// OnOpenScreen(); //����ʾ��
		// OnCloseScreen();//�ر���ʾ��
	}

	// ��������ӽ�Ŀ����
	public static void OnAddProgram() {
		m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, false, 10);
		m_iProgramCount++;
		System.out.println("��ӽ�Ŀ" + m_iProgramCount);
	}

	// ����������ı���
	public static void OnAddText() {
		DllLibrary.User_Text Text = new DllLibrary.User_Text();

		Text.BkColor = 0;
		Text.chContent = "��Ա19�ˣ��ѽ���5��";

		Text.PartInfo.FrameColor = 0;
		Text.PartInfo.iFrameMode = 0;
		Text.PartInfo.iHeight = 32;
		Text.PartInfo.iWidth = 256;
		Text.PartInfo.iX = 0;
		Text.PartInfo.iY = 0;

		Text.FontInfo.bFontBold = false;
		Text.FontInfo.bFontItaic = false;
		Text.FontInfo.bFontUnderline = false;
		Text.FontInfo.colorFont = 0xFFFF;
		Text.FontInfo.iFontSize = 16;
		Text.FontInfo.strFontName = "";
		Text.FontInfo.iAlignStyle = 1;
		Text.FontInfo.iVAlignerStyle = 1;
		Text.FontInfo.iRowSpace = 0;

		Text.MoveSet.bClear = false;
		Text.MoveSet.iActionSpeed = 1;
		Text.MoveSet.iActionType = 1;
		Text.MoveSet.iHoldTime = 10;
		Text.MoveSet.iClearActionType = 0;
		Text.MoveSet.iClearSpeed = 4;
		Text.MoveSet.iFrameTime = 10;

		if (-1 == m_DllLibrary.User_AddText(m_iCardNum, Text, m_iProgramIndex)) {
			System.out.println("����ı�ʧ�ܣ�");
		} else {
			System.out.println("����ı��ɹ���");
		}
	}

	// ���������RTF�ļ�
	public static void OnAddRTF() {
		DllLibrary.User_RTF RTF = new DllLibrary.User_RTF();

		String strFileName = m_strUserPath + "\\res\\EQ2008_RTF.rtf";
		RTF.strFileName = strFileName;

		RTF.PartInfo.FrameColor = 0;
		RTF.PartInfo.iFrameMode = 0;
		RTF.PartInfo.iHeight = 32;
		RTF.PartInfo.iWidth = 64;
		RTF.PartInfo.iX = 0;
		RTF.PartInfo.iY = 0;

		RTF.MoveSet.bClear = false;
		RTF.MoveSet.iActionSpeed = 4;
		RTF.MoveSet.iActionType = 0;
		RTF.MoveSet.iHoldTime = 10;
		RTF.MoveSet.iClearActionType = 0;
		RTF.MoveSet.iClearSpeed = 4;
		RTF.MoveSet.iFrameTime = 10;

		if (-1 == m_DllLibrary.User_AddRTF(m_iCardNum, RTF, m_iProgramIndex)) {
			System.out.println("���rtf�ļ�ʧ�ܣ�");
		} else {
			System.out.println("���rtf�ļ��ɹ���");
		}
	}

	// ��������ӵ����ı�
	public static void OnAddSingleText() {
		DllLibrary.User_SingleText SingleText = new DllLibrary.User_SingleText();

		SingleText.BkColor = 0;
		SingleText.chContent = "2019-2020��ӭʹ��EQ�첽���ƿ���";
		SingleText.PartInfo.iFrameMode = 0;
		SingleText.PartInfo.iHeight = 32;
		SingleText.PartInfo.iWidth = 256;
		SingleText.PartInfo.iX = 0;
		SingleText.PartInfo.iY = 0;
		SingleText.FontInfo.bFontBold = false;
		SingleText.FontInfo.bFontItaic = false;
		SingleText.FontInfo.bFontUnderline = false;
		SingleText.FontInfo.colorFont = 0xFF;
		SingleText.FontInfo.iFontSize = 12;
		SingleText.PartInfo.FrameColor = 0;
		SingleText.FontInfo.strFontName = "";
		SingleText.MoveSet.bClear = false;
		SingleText.MoveSet.iActionSpeed = 8;
		SingleText.MoveSet.iActionType = 3;
		SingleText.MoveSet.iHoldTime = 0;
		SingleText.MoveSet.iClearActionType = 0;
		SingleText.MoveSet.iClearSpeed = 4;
		SingleText.MoveSet.iFrameTime = 20;
		if (-1 == m_DllLibrary.User_AddSingleText(m_iCardNum, SingleText, m_iProgramIndex)) {
			System.out.println("��ӵ����ı�ʧ�ܣ�");
		} else {
			System.out.println("��ӵ����ı��ɹ���");
		}
	}

	// ���������ͼƬ
	public static void OnAddbmp() {
		DllLibrary.User_Bmp BmpZone = new DllLibrary.User_Bmp();
		DllLibrary.User_MoveSet MoveSet = new DllLibrary.User_MoveSet();
		int iBMPZoneNum;

		BmpZone.PartInfo.iX = 0;
		BmpZone.PartInfo.iY = 0;
		BmpZone.PartInfo.iHeight = 32;
		BmpZone.PartInfo.iWidth = 64;
		BmpZone.PartInfo.FrameColor = 0xFF00;
		BmpZone.PartInfo.iFrameMode = 1;

		MoveSet.bClear = true;
		MoveSet.iActionSpeed = 4;
		MoveSet.iActionType = 0;
		MoveSet.iHoldTime = 50;
		MoveSet.iClearActionType = 0;
		MoveSet.iClearSpeed = 4;
		MoveSet.iFrameTime = 10;

		iBMPZoneNum = m_DllLibrary.User_AddBmpZone(m_iCardNum, BmpZone, m_iProgramIndex);

		// ͨ��ͼƬ·���������ͼƬ
		if (m_DllLibrary.User_AddBmpFile(m_iCardNum, iBMPZoneNum, m_strUserPath + "\\res\\BMP1.bmp", MoveSet,
				m_iProgramIndex)) {
			System.out.println("���ͼƬ·��1�ɹ���");
		} else {
			System.out.println("���ͼƬ·��1ʧ�ܣ�");
		}

		// ͨ��ͼƬ������ͼƬ
		HANDLE hBitmap = USER.LoadImage(null, // ģ��ʵ�����
				m_strUserPath + "\\res\\BMP1.bmp", // λͼ·��
				USER.IMAGE_BITMAP, // λͼ����
				64, // ָ��ͼƬ��
				32, // ָ��ͼƬ��
				USER.LR_LOADFROMFILE);
		if (hBitmap != null) {
			if (m_DllLibrary.User_AddBmp(m_iCardNum, iBMPZoneNum, hBitmap, MoveSet, m_iProgramIndex)) {
				System.out.println("���ͼƬ���2�ɹ���");
			} else {
				System.out.println("���ͼƬ���2ʧ�ܣ�");
			}
			GDI.DeleteObject(hBitmap);
		} else {
			System.out.println("���ͼƬ���2ʧ�ܣ�");
		}
	}

	// ���������ʱ��
	public static void OnAddTime() {
		DllLibrary.User_DateTime DateTime = new DllLibrary.User_DateTime();

		DateTime.bDay = 1;
		DateTime.bHour = 1;
		DateTime.BkColor = 1;
		DateTime.bMin = 1;
		DateTime.bMouth = 1;
		DateTime.bMulOrSingleLine = 1;
		DateTime.bSec = 1;
		DateTime.bWeek = 1;
		DateTime.bYear = 1;
		DateTime.bYearDisType = 1;
		DateTime.chTitle = "����";

		DateTime.PartInfo.iFrameMode = 1;
		DateTime.iDisplayType = 4;
		DateTime.PartInfo.FrameColor = 0xFFFF;
		DateTime.PartInfo.iHeight = 64;
		DateTime.PartInfo.iWidth = 64;
		DateTime.PartInfo.iX = 0;
		DateTime.PartInfo.iY = 0;
		DateTime.FontInfo.bFontBold = false;
		DateTime.FontInfo.bFontItaic = false;
		DateTime.FontInfo.bFontUnderline = false;
		DateTime.FontInfo.colorFont = 0xFFFF;
		DateTime.FontInfo.iAlignStyle = 1;
		DateTime.FontInfo.iFontSize = 12;
		DateTime.FontInfo.strFontName = "";

		if (-1 == m_DllLibrary.User_AddTime(m_iCardNum, DateTime, m_iProgramIndex)) {
			System.out.println("���ʱ��ʧ�ܣ�");
		} else {
			System.out.println("���ʱ��ɹ���");
		}
	}

	// ��������Ӽ�ʱ
	public static void OnAddTimeCount() {
		DllLibrary.User_Timer TimeCount = new DllLibrary.User_Timer();

		TimeCount.bDay = true;
		TimeCount.bHour = false;
		TimeCount.BkColor = 0;
		TimeCount.bMin = false;
		TimeCount.bMulOrSingleLine = true;
		TimeCount.bSec = false;
		TimeCount.chTitle = "������һ";
		TimeCount.FontInfo.bFontBold = false;
		TimeCount.FontInfo.bFontItaic = false;
		TimeCount.FontInfo.bFontUnderline = false;
		TimeCount.FontInfo.colorFont = 0xFFFF;
		TimeCount.FontInfo.iAlignStyle = 2;
		TimeCount.FontInfo.iFontSize = 12;
		TimeCount.FontInfo.strFontName = "";
		TimeCount.PartInfo.FrameColor = 0xFF00;
		TimeCount.PartInfo.iFrameMode = 0;
		TimeCount.PartInfo.iHeight = 32;
		TimeCount.PartInfo.iWidth = 64;
		TimeCount.PartInfo.iX = 0;
		TimeCount.PartInfo.iY = 0;
		TimeCount.ReachTimeYear = 2015;
		TimeCount.ReachTimeMonth = 5;
		TimeCount.ReachTimeDay = 1;
		TimeCount.ReachTimeHour = 10;
		TimeCount.ReachTimeMinute = 0;
		TimeCount.ReachTimeSecond = 0;

		if (-1 == m_DllLibrary.User_AddTimeCount(m_iCardNum, TimeCount, m_iProgramIndex)) {
			System.out.println("��Ӽ�ʱʧ�ܣ�");
		} else {
			System.out.println("��Ӽ�ʱ�ɹ���");
		}
	}

	// ����������¶�
	public static void OnAddTemperature() {
		DllLibrary.User_Temperature Temperature = new DllLibrary.User_Temperature();

		Temperature.BkColor = 0;
		Temperature.chTitle = "";
		Temperature.DisplayType = 0;
		Temperature.PartInfo.FrameColor = 0xFF00;
		Temperature.PartInfo.iFrameMode = 1;
		Temperature.PartInfo.iHeight = 32;
		Temperature.PartInfo.iWidth = 64;
		Temperature.PartInfo.iX = 0;
		Temperature.PartInfo.iY = 0;
		Temperature.FontInfo.bFontBold = false;
		Temperature.FontInfo.bFontItaic = false;
		Temperature.FontInfo.bFontUnderline = false;
		Temperature.FontInfo.colorFont = 0xFFFF;
		Temperature.FontInfo.iAlignStyle = 0;
		Temperature.FontInfo.iFontSize = 12;
		Temperature.FontInfo.strFontName = "����";

		if (-1 == m_DllLibrary.User_AddTemperature(m_iCardNum, Temperature, m_iProgramIndex)) {
			System.out.println("����¶�ʧ�ܣ�");
		} else {
			System.out.println("����¶ȳɹ���");
		}
	}

	// ������ɾ�����н�Ŀ
	public static void OnDelAllProgram() {
		if (!m_DllLibrary.User_DelAllProgram(m_iCardNum)) {
			System.out.println("ɾ����Ŀʧ�ܣ�");
		} else {
			// ��ʾ��Ϣ
			m_iProgramCount = 0;
			System.out.println("��������ӽ�Ŀ��");
		}
	}

	// �������������ݵ���ʾ��
	public static void OnSendToScreen() {
		if (!m_DllLibrary.User_SendToScreen(m_iCardNum)) {
			System.out.println("���ݷ���ʧ�ܣ�");
		} else {
			System.out.println("���ݷ��ͳɹ���");
		}

	}

	// ������У��ʱ��
	public static void OnAjusttime() {
		if (!m_DllLibrary.User_AdjustTime(m_iCardNum)) {
			System.out.println("У׼ʱ��ʧ�ܣ�");
		} else {
			System.out.println("У׼ʱ��ɹ���");
		}
	}

	// ����������ʾ��
	public static void OnOpenScreen() {
		if (!m_DllLibrary.User_OpenScreen(m_iCardNum)) {
			System.out.println("����ʾ��ʧ�ܣ�");
		} else {
			System.out.println("����ʾ���ɹ���");
		}
	}

	// �������ر���ʾ��
	public static void OnCloseScreen() {
		if (!m_DllLibrary.User_CloseScreen(m_iCardNum)) {
			System.out.println("�ر���ʾ��ʧ�ܣ�");
		} else {
			System.out.println("�ر���ʾ���ɹ���");
		}
	}

	// ������ʵʱ�������ݣ���������
	// ���ڣ�2008-04-30
	public static boolean OnRealtimeConnect() {
		if (!m_DllLibrary.User_RealtimeConnect(m_iCardNum)) {
			System.out.println("ʵʱ�������ݽ�������ʧ�ܣ�");
			return false;
		} else {
			System.out.println("ʵʱ�������ݽ������ӳɹ���");
			return true;
		}
	}

	// ������ʵʱ�������ݣ���������
	// ���ڣ�2008-04-30
	public static void OnRealtimeSendData() {
		// ͨ��ͼƬ������ͼƬ
		HANDLE hBitmap = USER.LoadImage(null, // ģ��ʵ�����
				m_strUserPath + "\\res\\BMP1.bmp", // λͼ·��
				USER.IMAGE_BITMAP, // λͼ����
				64, // ָ��ͼƬ��
				32, // ָ��ͼƬ��
				USER.LR_LOADFROMFILE);// ��·��������ͼƬ
		if (hBitmap != null) {
			if (false == m_DllLibrary.User_RealtimeSendData(m_iCardNum, 0, 0, 64, 32, hBitmap)) {
				System.out.println("����ʵʱͼƬ���ʧ�ܣ�");
			} else {
				System.out.println("����ʵʱͼƬ����ɹ���");
			}
			GDI.DeleteObject(hBitmap);
		} else {
			System.out.println("����ʵʱͼƬ���ʧ�ܣ�");
		}
	}

	// ������ʵʱ����ͼƬ·��
	// ���ڣ�2015-09-21
	public static void OnRealtimeSendBmpData() {
		if (false == m_DllLibrary.User_RealtimeSendBmpData(m_iCardNum, 0, 0, 64, 32,
				m_strUserPath + "\\res\\BMP2.bmp")) {
			System.out.println("����ʵʱͼƬ·��ʧ�ܣ�");
		} else {
			System.out.println("����ʵʱͼƬ·���ɹ���");
		}
	}

	// ������ʵʱ�����ı�����
	// ���ڣ�2015-09-21
	public static void OnRealtimeSendText(int quota, int currNum) {
		String strText = "��Ա" + quota + "�ˣ��ѽ���" + currNum + "��";
		DllLibrary.User_FontSet FontInfo = new DllLibrary.User_FontSet();

		FontInfo.bFontBold = false;
		FontInfo.bFontItaic = false;
		FontInfo.bFontUnderline = false;
		FontInfo.colorFont = 0xFFFF;
		FontInfo.iFontSize = 14;
		FontInfo.strFontName = "����";
		FontInfo.iAlignStyle = 0;
		FontInfo.iVAlignerStyle = 0;
		FontInfo.iRowSpace = 0;

		if (!m_DllLibrary.User_RealtimeSendText(m_iCardNum, 0, 0, 256, 32, strText, FontInfo)) {
			System.out.println("����ʵʱ�ı�ʧ�ܣ�");
		} else {
			System.out.println("����ʵʱ�ı��ɹ���");
		}
	}

	// ������ʵʱ�������ݣ��Ͽ�����
	// ���ڣ�2008-04-30
	public static void OnRealtimeDisConnect() {
		if (!m_DllLibrary.User_RealtimeDisConnect(m_iCardNum)) {
			System.out.println("ʵʱ�������ݶϿ�����ʧ�ܣ�");
		} else {
			System.out.println("ʵʱ�������ݶϿ����ӳɹ���");
		}
	}

	// ��������տ��ƿ���Ŀ
	// ���ڣ�2015-09-21
	public static void OnRealtimeScreenClear() {
		if (!m_DllLibrary.User_RealtimeScreenClear(m_iCardNum)) {
			System.out.println("��տ��ƿ���Ŀʧ�ܣ�");
		} else {
			System.out.println("��տ��ƿ���Ŀ�ɹ���");
		}
	}
}
