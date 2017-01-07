package edu.dbke.web.acl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Zhonghua Hu
 *
 */
public class AclValidateUrl {

	protected static Set<String> CUT_SYMBOL = new HashSet<String>();
	protected static Integer ACL_EXTEND = -1;
	protected static Integer ACL_READ = 0;
	protected static Integer ACL_CREATE = 1;
	protected static Integer ACL_DELETE = 2;
	protected static Integer ACL_UPDATE = 3;
	protected static String aclCreate = "";
	protected static String aclRead = "";
	protected static String aclUpdate = "";
	protected static String aclDelete = "";
	protected static String aclError = SystemConf.getConf("aclError");

	protected static Integer authFailCode = 403;

	static {
		String cutSymbol = SystemConf.getConf("aclClassifySeparate");
		aclCreate = SystemConf.getConf("aclCreate");
		aclRead = SystemConf.getConf("aclRead");
		aclUpdate = SystemConf.getConf("aclUpdate");
		aclDelete = SystemConf.getConf("aclDelete");
		if (cutSymbol.trim().length() > 0) {
			for (String c : cutSymbol.split(",")) {
				CUT_SYMBOL.add(c);
			}
		}
	}

	//将targetUrl于modules中的URL进行对比
	protected static boolean judgeUrl(String targetUrl, List<Module> modules) {
		boolean flag = false;//权限标识
		String[] cutUri = analyzeUri(targetUrl);//解析url
		boolean isCut = (cutUri[0].equals(cutUri[1]));//不可解析为true
		int index = isCut ? -1 : getIndex(cutUri[1]);
		if (null != modules) {
			for (Module m : modules) {
				if (targetUrl.equals(m.getUrl())) {//访问请求
					if (m.getAcl().length() > 1 && m.getAcl().substring(0, 1).equals("1")) {
						flag = true;
						break;
					}
				} else if (targetUrl.equals(m.getAclUrl())&&m.getAcl().length() == 1) {//扩展请求、、//,,,,,,,
					if (m.getAcl().equals("1")) {
						flag = true;
						break;
					}
				} else {//操作请求
					if (!isCut && cutUri[0].equals(m.getAclUrl())) {
						if (index >= 0 && m.getAcl().length() != 1 && m.getAcl().split(",")[index].equals("1")) {
							flag = true;
							break;
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * @param opt
	 * @return
	 */
	private static int getIndex(String opt) {
		int index = -1;
		if (aclCreate.contains(opt)) {
			index = 1;
		} else if (aclRead.contains(opt)) {
			index = 0;
		} else if (aclUpdate.contains(opt)) {
			index = 3;
		} else if (aclDelete.contains(opt)) {
			index = 2;
		}
		return index;
	}

	protected static boolean urlFilter(HttpSession httpSession, HttpServletResponse servletResponse,
									   HttpServletRequest servletRequest) throws IOException {
		@SuppressWarnings("unchecked")
		List<Module> acl = (List<Module>) httpSession.getAttribute("acl");
		String targetUrl = servletRequest.getRequestURI().replaceFirst(servletRequest.getContextPath() + "/", "");
		if (!AclValidateUrl.judgeUrl(targetUrl, acl)) {//不放行
			if ("XMLHttpRequest".equals(servletRequest.getHeader("X-Requested-With"))) {//ajax请求
				servletResponse.setStatus(authFailCode);
				//				String jsonObject = "{\"message\":\"权限验证不通过！\","
				//						+ "\"AUTH_FAILURE\":true,\"cause\":\"AUTH_FAILURE\"}";
				//				String contentType = "application/json";
				//				servletResponse.setContentType(contentType);
				//				servletResponse.getWriter().print(jsonObject);
				//				servletResponse.getWriter().flush();
				//				servletResponse.getWriter().close();
			} else {//同步请求
				String basePath = servletRequest.getScheme() + "://" + servletRequest.getServerName() + ":"
						+ servletRequest.getServerPort() + servletRequest.getContextPath() + "/";
				servletResponse.sendRedirect(basePath + aclError);
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析uri
	 * @param uri
	 * @return
	 */
	private static String[] analyzeUri(String uri) {
		String[] cutUri = new String[] { "", "" };
		try {
			String newUri = (uri.indexOf("?") == -1) ? uri : (uri.substring(0, uri.indexOf("?")));
			int index = 0;
			if (CUT_SYMBOL.size() <= 0) {
				return cutUri;
			}
			for (String c : CUT_SYMBOL) {
				if (newUri.indexOf(c) != -1) {
					index = newUri.indexOf(c);
				}
			}
			//不可解析时，cutUri数组元素相同
			//即index=0时，元素equalnewUri
			cutUri[0] = index == 0 ? newUri : newUri.substring(0, index);
			cutUri[1] = index == 0 ? newUri : newUri.substring(index + 1, newUri.indexOf("."));
		} catch (Exception e) {

		}
		return cutUri;
	}

	/**
	 * 是否为访问请求
	 * @param targetUrl
	 * @param modules
	 * @return
	 */
	protected static boolean isRetrieve(String targetUrl, List<Module> modules) {
		boolean flag = true;//权限标识
		for (Module m : modules) {
			/* 权限列表中存在url匹配target，即为访问请求
			 * acl中应该存在四个值
			 * 第一个值不为0则验证通过（即1）
			 */
			if (targetUrl.equals(m.getUrl())) {
				if (m.getAcl().length() > 1 && m.getAcl().substring(0, 1).equals("0")) {
					flag = false;
				}
			}
		}
		return !flag ? flag : isExtend(targetUrl, modules);
	}

	/**
	 * 是否为扩展权限请求
	 * @param targetUrl
	 * @param modules
	 * @return
	 */
	private static boolean isExtend(String targetUrl, List<Module> modules) {
		boolean flag = true;//权限标识
		for (Module m : modules) {
			/* 权限列表中存在aclurl匹配target，即为扩展请求
			 * 第一个值不为0则验证通过（即1）
			 */
			if (targetUrl.equals(m.getAclUrl())) {
				if (m.getAcl().length() == 1 && m.getAcl().equals("0")) {
					flag = false;
				}
			}
		}
		return !flag ? flag : isOpt(targetUrl, modules);
	}

	/**
	 * 是否为操作权限请求
	 * @param targetUrl
	 * @param modules
	 * @return
	 */
	private static boolean isOpt(String targetUrl, List<Module> modules) {
		boolean flag = true;//权限标识
		String[] cutUri = analyzeUri(targetUrl);//解析url
		if (cutUri[0].equals(cutUri[1])) {//未解析,则目标url不在权限列表中，予以放行
			return flag;
		} else {
			for (Module m : modules) {
				/* 
				 * 权限列表中存在aclurl匹配操作基址，即为操作请求
				 */
				if (cutUri[0].equals(m.getAclUrl())) {
					int index = getIndex(cutUri[1]);
					if (m.getAcl().length() != 1 && m.getAcl().split(",")[index].equals("0")) {
						flag = false;
					}
				}
			}
		}
		return flag;
	}
}
