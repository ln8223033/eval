package edu.dbke.web.acl;



import edu.dbke.model.org.AccountMap;
import edu.dbke.model.org.AccountSynchronize;
import edu.dbke.model.org.User;
import edu.dbke.service.org.AccountMapService;
import edu.dbke.service.org.AccountSynchronizeService;
import edu.dbke.service.org.UserService;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Xiao Xiong
 * 用于同步主控和应用系统账号的线程
 *
 */
public class AccountSynchronizeThread extends Thread{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unused")
	private WebApplicationContext wac = null ;
	private int accountSynchronizeMinutes = Integer.parseInt(SystemConf.getConf("accountSynchronizeMinutes"));
	private String accoutSynchronizeStartTimeString = SystemConf.getConf("accoutSynchronizeStartTime");//同步此时以后的账号
	private String accountSynchronizeUrl = SystemConf.getConf("accountSynchronizeUrl");
	private String accoutSynchronizeRecordId = SystemConf.getConf("accoutSynchronizeRecordId");
	
	private AccountMap accountMap = null ;
	private AccountMapService accountMapService = null ;
	private AccountSynchronizeService accountSynchronizeService = null ;
	private UserService userService = null ;
	
	public AccountSynchronizeThread(WebApplicationContext wac){
		this.wac = wac ;
		accountMapService = (AccountMapService)wac.getBean("accountMapService");
		userService = (UserService)wac.getBean("userService");
		accountSynchronizeService = (AccountSynchronizeService)wac.getBean("accountSynchronizeService");
	}
	
	//从主控获取从timeString以后的新增的账号
	public void getAccountFrom(){
		String requestResult = null ;
		AccountSynchronize accountSynchronize = accountSynchronizeService.find(accoutSynchronizeRecordId) ;
		String lastSuccedTime = (accountSynchronize == null ? null : sdf.format(accountSynchronize.getUpdateSuccessTime()));
		try {
			requestResult = AclHttpConnection.httpPost(accountSynchronizeUrl + "?startTime=" +( lastSuccedTime != null ?  lastSuccedTime : accoutSynchronizeStartTimeString));
			requestResult = requestResult.replace("(","" ).replace(")","");
			JSONObject object = new JSONObject(requestResult);
			
			if (object.get("ProResult").toString().equals("Success")) {//请求成功
				Object accountResult = object.get("Msg");
				String[] accounts = accountResult.toString().split("#&");
				if( accounts.length  > 0 ){//请求的账号存在
					//更新数据库中账号同步时间
					accountSynchronize.setUpdateSuccessTime(new Date());
					accountSynchronizeService.edit(accountSynchronize);
					for(String item : accounts){//遍历处理每一个账号
						if( item.trim().equals("") == false)
							handleItem(item);
					}
					System.out.println();
				}
			}
		} catch (IOException e) {
			System.out.println("-------------------------账号获取出错--------------------");
			e.printStackTrace();
		}
	}
	
	//处理一个ACl主控账号
	public void handleItem(String externalAccount){
		System.out.print(externalAccount + " , ");
		accountMap = accountMapService.querySingle(externalAccount);
		
		if( accountMap == null){//不存在映射，则增加
			accountMap = new AccountMap();
			accountMap.setExternalAccount(externalAccount);
			accountMap.setInternalAccount(externalAccount);
			
			//存在对应user,且名称不同，则覆盖。
			User user = userService.querySingle( externalAccount );
			if( user == null){
				User u = new User();
				u.setUserName(externalAccount);
				//*********************************业务相关的初始化*******************************
				userService.create(u);
			}
			accountMapService.create(accountMap);
		}
	}
	
	public void run(){
		System.out.println("------------------------ACL 账号同步线程启动:----- " + new Date() + "------------------------");
		while(true){
			System.out.println("------------------------ACL 账号同步------- " + new Date() + "------------------------------");
			getAccountFrom();
			
			try {
				Thread.sleep( accountSynchronizeMinutes * 60 * 1000);//以分钟为单位
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}