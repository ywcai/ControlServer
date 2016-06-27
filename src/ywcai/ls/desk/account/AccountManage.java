package ywcai.ls.desk.account;

import java.util.HashMap;
import org.apache.mina.core.session.IoSession;

import ywcai.ls.desk.control.ControlServer;
import ywcai.ls.desk.manage.CoreMap;

public class AccountManage implements AccountManageInf {
	private HashMap<String,String> tokenMap;
	public AccountManage()
	{
		tokenMap=CoreMap.getTokenMap();//��������Redis�����ڴ�洢
		System.out.println("�˺���֤ģ������");
	}
	@Override
	public void Login(IoSession session, String message) {
		// TODO Auto-generated method stub

		boolean login=CheckPsw("ywcai","psw");
		Response(session,"ywcai",login);
	}
	@Override
	public void LoginOut(IoSession session, String username) {
		// TODO Auto-generated method stub
		tokenMap.remove(username);
		Response(session,username,false);
		System.out.print("\n"+username+": login out");
	}
	@Override
	public void Response(IoSession session,String username,boolean pLogin) {
		// TODO Auto-generated method stub
		String token="null";
		if(pLogin)
		{
			token=GetToken(session,username);
			session.write(token);
			session.write("end");
			ControlServer.logger.info("user  {} login success, session: {}",username,session);
			System.out.print("\n"+username+": login success");
		}
		else
		{
			session.write("fail");
			session.write("end");
			ControlServer.logger.info("user  {} login failed, session: {}",username,session);
		}
	}
	@Override
	public String GetToken(IoSession session,String username) {
		String token="null";
		if(tokenMap.containsKey(username))
		{
			token=tokenMap.get(username);
		}
		else
		{
			token=CreateToken(session,username);
		}
		return token;
	}
	
	private String CreateToken(IoSession session,String username) {
		// TODO Auto-generated method stub
		String token=Encode(session,username);
		tokenMap.put(username, token);//��������ΪRedis�洢
		return token;
	}
	private String Encode(IoSession session,String username)
	{
		String token=username+"#"+session.getId()+"#"+session.getRemoteAddress();
		return token;
	}
	@Override
	public boolean CheckPsw(String pUserName, String pPsw) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
