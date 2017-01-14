package xKing.user.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.user.domain.User;
import xKing.user.exception.SameUsernameException;
import xKing.user.service.UserService;
import xKing.utils.FontImageUtils;

// User 模块的控制器
@Controller
// 映射路径为 /user
@RequestMapping("/user")
public class UserController {

	// 注入 UserService
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchMemberSerivce branchMemberSerivce;
	
	@Autowired
	private BranchService branchService;
	
	// 登录页面
	@RequestMapping(method=RequestMethod.GET)
	public String loginPage(Principal principal) {
		if(principal != null) {
			return "redirect:/user/me";
		}
		return "login";
	}
	
	// 注册页面
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public String registerPage(Principal principal, Model model) {
		if(principal != null) {
			return "redirect:/user/me";
		}
		model.addAttribute(new User());
		return "register";
	}
	
	// 用户注册
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String register(
			@Validated User user, Errors errors,
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
			Pageable pageable,
			Model model,
			Principal principal) {
		
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("currentUser", currentUser);
		
		switch(tab) 
		{
		case "profile" :
			model.addAttribute("branches", branchService.getBranchByUserId(
					currentUser, new PageRequest(0, 2)));
			model.addAttribute("tab", "profile");
			return "/user/profile";
		case "branches" :
			model.addAttribute("page", 
					branchMemberSerivce.findByUserId(currentUser, pageable));
			model.addAttribute("tab", "branches");
			return "/user/myBranches";
		case "tasks" :
			model.addAttribute("tab", "tasks");
			return "/user/myTasks";
		case "friends" :
			model.addAttribute("tab", "friends");
			return "/user/myFriends";
		default :
			model.addAttribute("tab", "profile");
			return "/user/profile";
		}
	}
	
	
	// 获取用户信息,非本人
	@RequestMapping(value="/{userId}")
	public String userMessage() {
		return "userMessage";
	}
}
