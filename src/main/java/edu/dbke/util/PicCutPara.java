package edu.dbke.util;

/**
 * 图片剪切参数
 * 
 * @author figo
 * 
 */
public class PicCutPara {
	private int x;// x坐标

	private int y;// y坐标

	private int w;// 宽度

	private double rate;// 压缩比例率

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
