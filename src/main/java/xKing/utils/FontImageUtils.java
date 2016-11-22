package xKing.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Test;

// 生产头像
public class FontImageUtils {
	
	private  final String  ENGLISH_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	// 随机对象
	private Random r = new Random();

	// 生产随机颜色
	public Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	// 获得首字母
	private String getFirstChar(String str) {
		if(str == null) {
			str = String.valueOf(ENGLISH_CHARS.charAt(r.nextInt(26)));
		}
		char[] chars = str.toCharArray();
		return String.valueOf(chars[0]);
	}
	
	// 创建 BufferedImage
	private BufferedImage createImage(int w, int h, int rgb) {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(new Color(rgb));
		g.fillRect(0, 0, w, h);
		return image;
	}
	
	public BufferedImage getImage(int w, int h,int rgb, String str) {
		BufferedImage image = createImage(w, h, rgb);
		Graphics2D g = (Graphics2D) image.getGraphics();
		String first = getFirstChar(str);
		g.setFont(new Font("黑体", Font.BOLD, 60));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.drawString(first, w/2-15, h/2+20);
		return image;
	}
	
	public static void outPut(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "png", out);
	}
	
	@Test
	public void fun1() {
		System.out.println(randomColor().getRGB());
	}
}
