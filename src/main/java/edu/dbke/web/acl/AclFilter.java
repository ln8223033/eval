package edu.dbke.web.acl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 身份认证过滤器
 * @author Zhonghua Hu
 *
 */
public class AclFilter implements Filter {

 static List<String> filterUrls = new ArrayList<String>();//需要进行权限验证的URL
	protected String mainCtrlUrl = SystemConf.getConf("mainCtrlUrl");
	protected String system_id = SystemConf.getConf("system_id");
	protected String aclError = SystemConf.getConf("aclError");
	protected String appSessionInitUrl = SystemConf.getConf("appSessionInitUrl");
	protected String appAclType = SystemConf.getConf("appAclType");
	protected String mainCtrlAclAcquireUrl = SystemConf.getConf("mainCtrlAclAcquireUrl");
	protected String mainCtrlLoginUrl = SystemConf.getConf("mainCtrlLoginUrl");

	protected static String GET_IsLogin = "-1";
	protected static String GET_ACL = "0";
	protected static String GET_ACL_MENU = "1";
	protected static String GET_MODULE_ONLY = "2";

	protected static boolean isModuleReady = false;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession httpSession = request.getSession();
		String requestUri = request.getRequestURI();
		
		String requestUrl = request.getRequestURL().toString();
		
		String targetUrl = requestUri.replaceFirst(request.getContextPath() + "/", "");
		String loginUserId = (String) httpSession.getAttribute("loginUserId");
		boolean isRedirect = false;
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		
		
		if (!isModuleReady) {
			isModuleReady = this.initModule();
		}

		
		//js image(png,jpg,gif,) css 之类的文件直接跳过
		String Temp = requestUri.toLowerCase();
		if (Temp.endsWith(".js") || Temp.endsWith(".css") ||Temp.endsWith(".swf") ||Temp.endsWith(".png") || Temp.endsWith(".jpg") || Temp.endsWith(".bmp") || Temp.endsWith(".gif")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}	
		
		//未登录且此次操作并非由主控服务器请求重定向到session处理的servlet
		if (loginUserId == null && !(requestUri.endsWith(appSessionInitUrl) ||requestUri.endsWith("login.jsp") )) {
			isRedirect = true;
			StringBuffer requestPara = getRequestPara(servletRequest);
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/";
			String url = mainCtrlLoginUrl + "?loginType=0&systemId=" + system_id + "&redirectUrl=" + java.net.URLEncoder.encode(requestUrl + (request.getQueryString() != null ? ("?" + request.getQueryString()) : "") , "utf-8");
			response.sendRedirect(url);
		} else  if( loginUserId != null){//已经登录且需要进行权限过滤
			if ((GET_ACL.equals(appAclType) || GET_ACL_MENU.equals(appAclType)) && filterUrls != null) {
				for (String url : filterUrls) {
					if(url.contains(targetUrl)){//需要被过滤
						//isRedirect = AclValidateUrl.urlFilter(httpSession, response, request);
						break;
					}
				}
			}
		}else{//未登录(loginUserId == null )
			isRedirect = false ;
		}
		
		
		if (!isRedirect) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	private StringBuffer getRequestPara(ServletRequest servletRequest) {
		Enumeration<?> em = servletRequest.getParameterNames();
		StringBuffer requestPara = new StringBuffer();//请求参数
		while (em.hasMoreElements()) {
			String obj = em.nextElement().toString();
			String value = servletRequest.getParameter(obj);
			if (requestPara.length() > 0) {
				requestPara.append('&');
			} else {
				requestPara.append('?');
			}
			requestPara.append(obj).append('=').append(value);
		}
		return requestPara;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		isModuleReady = this.initModule();
		//账号同步线程启动
		new AccountSynchronizeThread(WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())).start();
	}

	private boolean initModule() {
		try {
			String result = AclHttpConnection.httpPost(
						mainCtrlAclAcquireUrl + "?&systemid=" + system_id + "&aclType=" + GET_MODULE_ONLY).
						replaceAll("\"", "\\\"");
			System.out.println("系统模块JSON:" + result);
		
			if (result == null || result.trim().length() <= 0) {
				return true;
			}
			result = result.replace("(", " ");//去掉两端圆括号
			result = result.replace(")", " ");
			JSONObject object = new JSONObject(result);
			Object msg = object.get("Msg");
			if (object.get("ProResult").equals("Success")) {//获取成功
				if (msg instanceof JSONArray || msg instanceof JSONObject) {
					List<String> moduleList = new ArrayList<String>();
					for( String item : AclSaveByJson.saveModuleJsonToSession((JSONArray) object.get("Msg")) ){
						if( !item.trim().equals(""))
							moduleList.add(item);
					}
					filterUrls.addAll(moduleList);
					System.out.println("------------------------ACL--系统模块初始化------------------------");
				}
			}
			return true;			
		} catch (JSONException e) {	
			e.printStackTrace();	
			return false;			
		} catch (IOException e) {				
			e.printStackTrace();				
			return false;			
		}
		
	}
}
