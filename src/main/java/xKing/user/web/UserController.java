package xKing.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 注册页面
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public String registerPage() {
		return "register";
	}
	
	// 用户认证
	@RequestMapping(method=RequestMethod.POST)
	public String login(User user) {
		return "redirect:/user/me";
	}
	
	// 获取本人信息
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public String profile(
			@RequestParam(name="tab", defaultValue="profile") final String tab, 
			Model model) {
		
		if(tab.equals("profile")) {
			model.addAttribute("tab", "profile");
			return "profile";
		} else if(tab.equals("branches")) {
			model.addAttribute("tab", "branches");
			return "myBranches";
		} else if(tab.equals("tasks")) {
			model.addAttribute("tab", "tasks");
			return "myTasks";
		} else if(tab.equals("friends")) {
			model.addAttribute("tab", "friends");
			return "myFriends";
		}
		return "profile";
	}
	

	// 获取用户信息,非本人
	@RequestMapping(value="/{userId}")
	public String userMessage() {
		return "userMessage";
	}
}
