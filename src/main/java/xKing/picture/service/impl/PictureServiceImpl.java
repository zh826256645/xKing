package xKing.picture.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import xKing.picture.service.PictureService;
import xKing.utils.FontImageUtils;

@Service
public class PictureServiceImpl implements PictureService {

	// 保存图片
	@Override
	public boolean savePicuture(InputStream inputStream, String pictureName) {

		File file = new File(this.getPath(pictureName));
		
		// 判断目录是否存在
		if(!file.exists()) {
			file.mkdirs();
		}
		try {
			OutputStream out = new FileOutputStream(new File(file.getPath() + "//" + pictureName));
			byte[] b = new byte[1024];
			while(inputStream.read(b) != -1) {
				out.write(b);
			}
			inputStream.close();
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 读取图片
	@Override
	public InputStream getPicture(String pictureName) {
		String picturePath = getPath(pictureName) + "//" + pictureName;
		File file = new File(picturePath);
		try {
			InputStream in = new FileInputStream(file);
			return in;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	// 读取图片
	@Override
	public void getPicture(String pictureName, OutputStream out) throws IOException {
		InputStream in = getPicture(pictureName);
		byte[] b = new byte[1024]; 
		while(in.read(b) != -1) {
			out.write(b);
		}
		in.close();
		out.flush();
		out.close();
	}
	
	// 生产图片
	@Override
	public void createPicture(int w, int h, int rgb, String name, OutputStream out) throws IOException {
		FontImageUtils utils = new FontImageUtils();
		utils.getImage(w, h, rgb, name);
		FontImageUtils.outPut(utils.getImage(160, 200, rgb, name), out);
	}
	
	public String getPath(String pictureName) {
		String basicPaht = "C://Xking";
		String hashCode = pictureName.hashCode() + "";
		String a = hashCode.substring(1, 2);
		String b = hashCode.substring(2, 3);
		return basicPaht + "//" + a + "//" + b;
	}


}
