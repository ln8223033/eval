package edu.dbke.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * 对照片进行剪切，压缩等处理，生成用户头像
 */
@SuppressWarnings("restriction")
public class HeadIconUtil {
	/**
	 * 图片进行等比例压缩
	 * 
	 * @param file
	 *            将要压缩的图片
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩长
	 * @param quality
	 *            压缩清晰度 <b>建议为1.0</b>
	 */
	public static void compress(File file, int width, int height, float quality) {
		try {
			if (!file.exists()) {
				return;// 文件不存在
			}
			Image srcFile = ImageIO.read(file);

			double rate1 = ((double) srcFile.getWidth(null)) / (double) width + 0.1;
			double rate2 = ((double) srcFile.getHeight(null)) / (double) height + 0.1;
			double rate = rate1 > rate2 ? rate1 : rate2;
			int new_w = (int) ((srcFile.getWidth(null)) / rate);
			int new_h = (int) ((srcFile.getHeight(null)) / rate);

			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

			OutputStream out = new FileOutputStream(file);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

			jep.setQuality(quality, true);
			encoder.encode(tag, jep);

			out.close();
			srcFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片剪切
	 * 
	 * @param x
	 *            坐标
	 * @param y
	 *            坐标
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @param rate
	 *            缩放缩率，0或1表示未进行缩放
	 * 
	 * @param
	 * 
	 */

	public static void cropImage(int x, int y, int w, int h, double rate, File src) {
		try {
			if (rate != 0) {
				x = (int) (x / rate);
				y = (int) (y / rate);
				w = (int) (w / rate);
				h = (int) (h / rate);
			}
			Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = (ImageReader) readers.next();

			InputStream source = new FileInputStream(src);

			ImageInputStream iis = ImageIO.createImageInputStream(source);

			reader.setInput(iis, true);

			ImageReadParam param = reader.getDefaultReadParam();

			Rectangle rect = new Rectangle(x, y, w, h);

			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			File tempFile = new File(src.getAbsoluteFile() + ".jpg");
			ImageIO.write(bi, "jpg", tempFile);
			source.close();
			iis.close();

			src.delete();// 删除原文件
			tempFile.renameTo(src);// 临时文件名恢复成原始文件名
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 测试用main方法
	public static void main(String... args) {
		File file = new File("H:\\photo\\me\\1.jpeg");

		cropImage(0, 0, 3000, 2000, 0, file);// 进行图片剪切

		if (file.length() > 1024 * 350) {// 对大于350K的图片进行压缩
			compress(file, 600, 600, 1.0f);
		}
	}

	public static void cropImage(PicCutPara cutPara, File file) {
		cropImage(cutPara.getX(), cutPara.getY(), cutPara.getW(), cutPara.getW(), cutPara.getRate(), file);
	}
}
