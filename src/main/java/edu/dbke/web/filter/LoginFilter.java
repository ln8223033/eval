package edu.dbke.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 非登录用户拦截
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
			ServletException {
	    //HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//HttpSession session = request.getSession();
		
		response.setHeader("P3P","CP=CAO PSA OUR");//p3p
		
		chain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
