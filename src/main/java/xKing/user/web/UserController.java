package xKing.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// User 模块的控制器
@Controller
// 映射路径为 /user
@RequestMapping("/user")
public class UserController {

	// 用户认证
	@RequestMapping(method=RequestMethod.POST)
	public String login() {
		// 重定向
		return "redirect:/user/profile/";
	}
	
	// 获取用户信息,非本人
	@RequestMapping(value="/{userId}")
	public String userMessage() {
		return "userMessage";
	}
	
	// 获取本人信息
	public String profile() {
		return "profile";
	}
}
