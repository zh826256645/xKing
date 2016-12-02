package xKing.picture.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Pircture Service
 * 用于保存图片，读取图片
 * 
 * @author 钟浩
 * @date 2016年11月29日
 *
 */
public interface PictureService {
	boolean savePicuture(InputStream inputStream, String picutureName);
	
	InputStream getPicture(String pictureName);
	
	void getPicture(String pictureName, OutputStream out) throws IOException;
	
	void createPicture(int w, int h, int rgb ,String name, OutputStream out) throws IOException;
}
