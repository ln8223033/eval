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
 * 图片处理
 * @author huitang
 *
 */
@SuppressWarnings("restriction")
public final class ImageUtils {
	public ImageUtils() {

	}

	/**   
	 * 把图片印刷到图片上   
	 *    
	 * @param pressImg --   
	 *            水印文件   
	 * @param targetImg --   
	 *            目标文件   
	 * @param x   
	 * @param y   
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件    
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
			// /    
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	* 把图片印刷到图片上
	*
	* @param pressImg --
	*            水印文件
	* @param targetImg --
	*            目标文件
	* @param x
	*            --x坐标
	* @param y
	*            --y坐标
	* @param alpha
	*           --透明度     
	*/
	public final static void pressImage(String pressImg, String targetImg, int x, int y, float alpha) {
		try {
			//目标文件
			float Alpha = alpha;
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			//水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, Alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao,
					null);
			//水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**   
	 * 打印文字水印图片   
	 *    
	 * @param pressText   
	 *            --文字   
	 * @param targetImg --   
	 *            目标图片   
	 * @param fontName --   
	 *            字体名   
	 * @param fontStyle --   
	 *            字体样式   
	 * @param color --   
	 *            字体颜色   
	 * @param fontSize --   
	 *            字体大小   
	 * @param x --   
	 *            偏移量   
	 * @param y   
	 */

	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, int color,
			int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));

			g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 透明文字水印
	 * @param pressText
	 * @param targetImg
	 * @param fontName
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 * @param x
	 * @param y
	 * @param alpha
	 */
	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			float Alpha = alpha;
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();

			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, Alpha));
			g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**Returns the format name of the image in the object 'o'.
	 * Returns null if the format is not known.
	 * @param o
	 * @return
	 */
	private static String getFormatName(Object o) {
		try {
			// Create an image input stream on the image
			ImageInputStream iis = ImageIO.createImageInputStream(o);

			// Find all image readers that recognize the image format
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				// No readers found
				return null;
			}

			// Use the first reader
			ImageReader reader = iter.next();

			// Close stream
			iis.close();

			// Return the format name
			return reader.getFormatName();
		} catch (IOException e) {
			//
		}

		// The image could not be read
		return null;
	}

	// Returns the format of the image in the file 'f'.
	// Returns null if the format is not known.
	public static String getFormatInFile(File f) {
		return getFormatName(f);
	}

	/**
	 * 图片剪切
	 * @param cutPara
	 * @param file
	 */
	public static void cropImage(PicCutPara cutPara, File file) {
		cropImage(cutPara.getX(), cutPara.getY(), cutPara.getW(), cutPara.getW(), cutPara.getRate(), file);
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
			// Create an image input stream on the image
			ImageInputStream iis = ImageIO.createImageInputStream(src);

			// Find all image readers that recognize the image format
			Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
			if (!readers.hasNext()) {
				// No readers found
				return;
			}

			// Use the first reader
			ImageReader reader = readers.next();

			InputStream source = new FileInputStream(src);

			reader.setInput(iis, true);

			ImageReadParam param = reader.getDefaultReadParam();

			Rectangle rect = new Rectangle(x, y, w, h);

			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			File tempFile = new File(src.getAbsoluteFile() + ".tmp");
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

	public static void main(String[] args) {
		String fileName = "F:\\uploadfile\\5e921f9d2f2ff2a7012f32a666af005b";
		//pressImage("F:\\photo\\证件照\\logo_big.png", "F:\\photo\\证件照\\DSC_4215 - Copy.jpg", 20, 20, 0.05f);
		//pressText("中国人民银行", "F:\\photo\\证件照\\DSC_4215(1) - Copy.JPG", "宋体", FontConstants.ALIGN_CENTER, 30, 20, 20, 20);
		//pressText("DBKE版权所有", fileName, "宋体", FontConstants.ALIGN_CENTER, new Color(207, 207, 207), 25, 170, 20, 0.5f);
		/*File folder = new File("F:\\uploadfile");
		for (File file : folder.listFiles()) {
			System.out.println(getFormatInFile(file));
		}*/
		//compress(new File(fileName), 1024, 1024, 1);
		cropImage(100, 111, 200, 200, 0.6129032258064516, new File(fileName));
	}
}
