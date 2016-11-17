package xKing.user.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.user.domain.User;
import xKing.user.exception.SameUsernameException;
import xKing.user.service.UserService;

// User 模块的控制器
@Controller
// 映射路径为 /user
@RequestMapping("/user")
public class UserController {

	// 注入 UserService
	@Autowired
	private UserService userService;
	
	// 登录页面
	@RequestMapping(method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	// 注册页面
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute(new User());
		return "register";
	}
	
	// 用户注册
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String register(
			@Valid User user, 
			Errors errors,
			Model model,
			RedirectAttributes reModel) {
		if(errors.hasErrors()) {
			model.addAttribute("errors", true);
			return "register";
		}
		try {
			reModel.addFlashAttribute("message", "注册成功！请进行登录");
			userService.register(user);
			return "redirect:/user";
		} catch (SameUsernameException e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}
	}
	
	// 用户激活
	@RequestMapping(value="/state", method=RequestMethod.GET)
	public String activate(
			@RequestParam(name="username", required=false) String username,
			@RequestParam(name="key", required=false) String key,
			RedirectAttributes reModel) {
		if(key != null && username != null) {
			try {
				if(userService.activateUser(username, key)) {
					reModel.addFlashAttribute("message", "用户激活！请进行登录");
					return "redirect:/user";
				} else {
					reModel.addFlashAttribute("error", "该用户已激活不能重新激活!");
					return "redirect:/user";
				}
			} catch (Exception e) {
				reModel.addFlashAttribute("error", e.getMessage());
				return "redirect:/user";
			}
		} else {
			reModel.addAttribute("error", "激活码或用户名不能为空！");
			return "redirect:/user";
		}
	}
	
	// 获取本人信息
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public String profile(
			@RequestParam(name="tab", defaultValue="profile") final String tab, 
			Model model,
			Principal principal) {
		
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("currentUser", currentUser);
		
		switch(tab) 
		{
		case "profile" :
			model.addAttribute("tab", "profile");
			return "profile";
		case "branches" :
			model.addAttribute("tab", "branches");
			return "myBranches";
		case "tasks" :
			model.addAttribute("tab", "tasks");
			return "myTasks";
		case "friends" :
			model.addAttribute("tab", "friends");
			return "myFriends";
		default :
			model.addAttribute("tab", "profile");
			return "profile";
		}
	}
	
	// 获取用户信息,非本人
	@RequestMapping(value="/{userId}")
	public String userMessage() {
		return "userMessage";
	}
}
