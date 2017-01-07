package edu.dbke.web.acl;

import javax.servlet.http.HttpSession;

/**
 * session自定义处理
 * @author Zhonghua Hu
 *
 */
public interface SessionInitCustom {

	public void sessionCustomInit(HttpSession httpSession);

}
