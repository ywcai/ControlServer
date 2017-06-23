package ywcai.ls.desk.control;



import java.util.HashMap;

import ywcai.ls.desk.core.DataProcess;
import ywcai.ls.desk.core.DataProcessInf;
import ywcai.ls.desk.manage.CoreMap;
import ywcai.ls.desk.manage.CurrentUser;
import ywcai.ls.desk.manage.LinkManage;
import ywcai.ls.desk.manage.SessionManage;
import ywcai.ls.desk.manage.SessionManageInf;
import ywcai.ls.desk.tcpserver.WorkTcpServer;
import ywcai.ls.desk.ui.InfoFind;

public class ControlBus {
	
	public void Init()
	{
		HashMap<String, CurrentUser> userMap=CoreMap.getUserMap();
		userMap.clear();
		//��Ҫһ����̬��HashMap���������Լ���ͬ�û�������Ϣ
		
		//�����˺���֤����Ҫ����������ģ����д���.//�ⲻ����ʵ��
//		AccountManageInf accountManageInf=new AccountManage();
//		
//		AuthenTcpServer authenTcpServer =new AuthenTcpServer();
//		authenTcpServer.Init(accountManageInf);
		
		LinkManage linkManageInf=new LinkManage();
		//��������û��ɹ���������ߵ�session���й���Ҳ���ں������ݴ���ʱ���г�ʼ��������dataProcessʵ����ʵ������
		SessionManageInf sessionManageInf=new SessionManage();

		
		//�������ݴ���ģ��
		DataProcessInf dataProcessInf=new DataProcess(sessionManageInf);
		
		//�����ʼ��������ģ����Ҫ session��������ݴ���ģ�������
		WorkTcpServer workTcpServer=new WorkTcpServer();
		workTcpServer.Init(sessionManageInf,linkManageInf,dataProcessInf);
	
		//��Ϣ��ѯ����Ҫhash��;��Ϣ��ѯ����������棬��Ϊ��������������
		InfoFind infoFind=new InfoFind();
		infoFind.Init();	
	}
	
}
