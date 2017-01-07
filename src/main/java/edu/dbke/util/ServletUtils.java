/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ServletUtils.java 1048 2010-04-17 11:23:09Z calvinxiu $
 */
package edu.dbke.util;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Http与Servlet工具类.
 * 
 * @author calvin
 */
public class ServletUtils {
	static final int BUFFER_SIZE = 32 * 1024;// 文件下载缓冲区

	//-- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	public static final String JPEG_TYPE = "image/jpeg";
	public static final String PNG_TYPE = "image/png";
	public static final String GIF_TYPE = "image/gif";

	public static final String SWF_TYPE = "application/x-shockwave-flash";
	public static final String FLV_TYPE = "video/x-flv";
	public static final String MOV_TYPE = "video/quicktime";
	public static final String WMV_TYPE = "video/x-ms-wmv";
	public static final String MP3_TYPE = "audio/x-mpeg";

	public static final String RMVB_TYPE = "application/vnd.rn-realmedia-vbr";

	//-- Header 定义 --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	//-- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		//Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		//Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		//Http 1.0 header
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified 内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
											   long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag 内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			//中文文件名支持
			String encodedfileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParametersStartingWith(HttpServletRequest request, String prefix) {
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {//NOSONAR
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 设置http响应文件
	 * @param file 系统文件
	 * @param response http响应
	 * @param contentType 响应头类型
	 */
	public static void setHttpFile(File file, HttpServletResponse response, String contentType) {
		response.setContentType(contentType);
		if (null != file && file.exists()) {
			response.setContentLength((int) file.length());
			BufferedInputStream in = null;
			ServletOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
				out = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int length = 0;
				while ((length = in.read(buffer)) != -1) {
					out.write(buffer, 0, length);
					response.getOutputStream().flush();
				}
			} catch (IOException ignore) {
				ignore.printStackTrace();
			} finally {
				try {
					if (null != in) {
						in.close();
					}
					if (null != out) {
						out.flush();
						out.close();
					}
				} catch (IOException ignore) {
				}
			}
		}
	}

	/**
	 * 对Http Basic验证的 Header进行编码.
	 */
	public static String encodeHttpBasic(String userName, String password) {
		String encode = userName + ":" + password;
		return "Basic " + Base64.encodeBase64String(encode.getBytes());
	}
	/**
	 * 设置http响应输入流
	 * @param inputStream 输入流
	 * @param response http响应
	 * @param contentType 响应头类型
	 */
	public static void setHttpInputStream(InputStream inputStream, HttpServletResponse response, String contentType) {
		response.setContentType(contentType);
		BufferedInputStream in = null;
		ServletOutputStream out = null;
		try {
			response.setContentLength(inputStream.available());
			in = new BufferedInputStream(inputStream, BUFFER_SIZE);
			out = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
				out.flush();
			}
		} catch (IOException ignore) {
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

}
