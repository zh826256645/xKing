package xKing.picture.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xKing.picture.service.PictureService;

@Controller
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	// 获取图片
	@GetMapping(path="/branch/{branchName}")
	public void getBranchPicture(
			@PathVariable("branchName") String branchName,
			@RequestParam(name="pid",defaultValue="-10101010") String pid,
			HttpServletResponse response) {
		try {
			if(pid.substring(0, 1).equals("-")) {
				response.setContentType("image/png"); 
				pictureService.createPicture(160, 200, Integer.parseInt(pid), branchName, response.getOutputStream());
			} else {
				response.setContentType("image/" + pid.substring(pid.indexOf(".") + 1));
				pictureService.getPicture(pid, response.getOutputStream());
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	// 获取图片
	@GetMapping(path="/user/{userName}")
	public void getUserPicture(
			@PathVariable("userName") String userName,
			@RequestParam(name="pid",defaultValue="-10101010") String pid,
			HttpServletResponse response) {
		try {
			if(pid.substring(0, 1).equals("-")) {
				response.setContentType("image/png"); 
				pictureService.createPicture(100, 100, Integer.parseInt(pid), userName, response.getOutputStream());
			} else {
				response.setContentType("image/" + pid.substring(pid.indexOf(".") + 1));
				pictureService.getPicture(pid, response.getOutputStream());
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
