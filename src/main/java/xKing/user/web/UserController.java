package xKing.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xKing.user.domain.User;
import xKing.user.service.UserService;

// User 模块的控制器
@Controller
// 映射路径为 /user
@RequestMapping("/user")
public class UserController {

	// 注入 UserService
	@Autowired
	public UserService userService;
	
	// 登录页面
	@RequestMapping(method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	// 
	@RequestMapping(value="/newUser",method=RequestMethod.GET)
	public String registerPage() {
		return "register";
	}
	
	// 用户认证
	@RequestMapping(method=RequestMethod.POST)
	public String login(User user) {
		// 重定向
		User userAfterValidate = userService.Login(user);
		if (userAfterValidate == null) {
			return "login";
		}
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
