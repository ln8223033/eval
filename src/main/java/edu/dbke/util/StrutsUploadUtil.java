package edu.dbke.util;

import java.io.*;

public class StrutsUploadUtil {
	static final int BUFFER_SIZE = 20 * 1024;

	public static void copy(File upload, File dstFile) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(upload), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}