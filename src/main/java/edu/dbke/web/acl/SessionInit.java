package edu.dbke.web.acl;


import edu.dbke.model.org.User;
import edu.dbke.model.org.AccountMap;
import edu.dbke.service.org.AccountMapService;
import edu.dbke.service.org.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 副控中间件session处理
 * 对比token，保存权限信息
 *
 */
public class SessionInit extends HttpServlet {

	private static final long serialVersionUID = 7557818689855957685L;
	UserService userService;
	AccountMapService accountMapService = null ;
	AccountMap accountMap = null ;
	WebApplicationContext wac = null ;

	protected SessionInitCustom sessionInitCustom = null;

	protected String system_id = SystemConf.getConf("system_id");
	protected String aclError = SystemConf.getConf("aclError");
	protected String mainCtrlLoginUrl = SystemConf.getConf("mainCtrlLoginUrl");
	protected String mainCtrlAclAcquireUrl = SystemConf.getConf("mainCtrlAclAcquireUrl");
	protected String loginType = SystemConf.getConf("appAclType");
	protected String appSessionInitUrl = SystemConf.getConf("appSessionInitUrl");
	


	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String LoginType = request.getParameter("loginType");
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		String jsoncallback = request.getParameter("jsoncallback");
		//String roles = request.getParameter("Roles");
		String redirectUrl = request.getParameter("redirectUrl");
		String cusValue = request.getParameter("cusValue");
		String aclReTryTime = request.getParameter("aclReTryTime");

//		if ("true".equals(request.getParameter("mainCtrlValidate"))) {//
//			response.getWriter().write("true");
//			response.getWriter().flush();
//			return;
//		}
		
		
		response.setHeader("P3P","CP=CAO PSA OUR");

		
		if (!isEmpty(userId) && !isEmpty(token)) {
			String result = AclHttpConnection.httpPost(
					mainCtrlAclAcquireUrl + "?&token=" + token + "&systemId=" + system_id +"&userId=" + userId).replaceAll("\"", "\\\"");
			
			try {
				result = result.replace("(","" ).replace("(","");
				JSONObject object = new JSONObject(result);
				Object msg = object.get("Msg");
				if (object.get("ProResult").toString().equals("Success")) {//登陆成功将权限信息保存到session中
					httpSession.setAttribute("ProResult", "Success");
					if (msg instanceof JSONArray || msg instanceof JSONObject) {
						httpSession.setAttribute("acl", AclSaveByJson.saveAclJsonToSession((JSONArray) msg, loginType));
					}
					httpSession.setAttribute("loginUserId", userId);
				
					//通过账号映射表，查出主控账号对应的应用系统账号对应的User
					accountMap = accountMapService.getAccountMapByName(userId);
					 
					//没有对应的账号，提示不存在权限。并跳转到提示页。
					if( accountMap == null ){
						System.out.println("不存在内部账号,跳转至" + request.getContextPath() + this.aclError);
						response.getWriter().println("<center>您没有访问该页面的权限!</center>");
						return ;
					}
					
					
					/*
					 * 测试语句
					 */
					System.out.println(" .." +userId);
					System.out.println("内部账号:" + accountMap.getInternalAccount());
					
					
					User user = userService.findByName(accountMap.getInternalAccount());
					
					//设置用户的orgId
					//

					/*
					 *	得到登录用户的机构ID 
					 *	user.setOrg_id(userService.getUserOrgIdByUserId(user.getId()));
					 */
					user.setOrg_id(userService.getUserOrgIdByUserId(user.getId()));
					
					
					httpSession.setAttribute("loginUser", user);
					
					httpSession.setAttribute("loginName", userId);
					if (sessionInitCustom != null) {
						sessionInitCustom.sessionCustomInit(httpSession);
					}
					
					//根据账号查询出类型，知道该类型的主界面。
					if(redirectUrl == ""){
						redirectUrl = "index.jsp";//默认跳转页面
					}
					if(LoginType.equals("1")){
						//redirectUrl = "security/login!login.action?userId" + queryResult.toString();
						response.getWriter().write(jsoncallback+"({ProResult: 'success', Msg:{redirectUrl:'"+redirectUrl+"'}})");				
					}
					else{
						response.sendRedirect(redirectUrl);
					}
					//response.sendRedirect(redirectUrl);
				} else {//失败重定向到登录url
					//response.sendRedirect(mainCtrlLoginUrl + "?&msg=" + msg);
					if(LoginType.equals("1")){
						response.getWriter().write(jsoncallback+"({ProResult: 'error', Msg:'"+msg.toString()+"'})");		
					}
					else{
						response.sendRedirect("login.jsp" + "?&msg=" + msg);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void init() throws ServletException {
		/*ServletConfig config = getServletConfig();
		String sessionInitCustomImpl = config.getInitParameter("SessionInitCustomImpl");
		try {
			Class<?> clazz = Class.forName(sessionInitCustomImpl);
			sessionInitCustom = (SessionInitCustom) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
		
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		userService = ((UserService) wac.getBean("userService"));
		accountMapService = (AccountMapService)wac.getBean("accountMapService");	
	}

	private boolean isEmpty(String parameter) {
		return ((parameter == null) || parameter.trim().length() <= 0) ? true : false;
	}

}