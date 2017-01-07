package edu.dbke.web.acl;

import javax.servlet.http.HttpSession;

/**
 * 副控端退出
 * @author Zhonghua Hu
 *
 */
public class AclLogOut {

	/**
	 * 
	 * @param session
	 */
	public static void aclLogout(HttpSession session) {
		session.removeAttribute("loginUserId");
		session.removeAttribute("ProResult");
		session.removeAttribute("acl");
		session.invalidate();
	}

}
