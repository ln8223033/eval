package edu.dbke.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class ResponseUtil {
	public static void write(HttpServletResponse response, Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o);
		out.flush();
		out.close();
	}
}
