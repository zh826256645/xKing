package xKing.user.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.branch.domain.Branch;
import xKing.branch.service.BranchMemberRequestService;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.history.service.HistoryService;
import xKing.project.service.ProjectService;
import xKing.user.domain.User;
import xKing.user.exception.SameUsernameException;
import xKing.user.exception.UserNotExistException;
import xKing.user.service.UserService;


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
	
	@Autowired
	private BranchMemberRequestService branchMemberRequestService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private HistoryService historyService;
	
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
			RedirectAttributes reModel, HttpServletRequest request) {
		if(errors.hasErrors()) {
			model.addAttribute("errors", true);
			return "register";
		}
		try {
			userService.register(user, request.getScheme(),  request.getServerName() + ":" + request.getServerPort());
			reModel.addFlashAttribute("message", "注册成功！请进行登录");
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
			model.addAttribute("firendNum", userService.getFriendNum(currentUser));
			model.addAttribute("tasks", projectService.getUserTasks(currentUser, null, new PageRequest(0, 3)));
			model.addAttribute("histories", historyService.findUserHistories(currentUser, new PageRequest(0, 5)));
			model.addAttribute("toDoTaskNum", projectService.getUserNotFinishTask(currentUser));
			model.addAttribute("tab", "profile");
			return "/user/profile";
			
		case "branches" :
			model.addAttribute("page", branchMemberSerivce.findByUserId(currentUser, pageable));
			model.addAttribute("invitePage", branchMemberRequestService.getByUserAndState(currentUser, 1, pageable));
			model.addAttribute("requestJoinPage", branchMemberRequestService.getByUserAndState(currentUser, 2, pageable));
			model.addAttribute("tab", "branches");
			return "/user/myBranches";
			
		case "tasks" :
			model.addAttribute("page", projectService.getUserTasks(currentUser, null, pageable));
			model.addAttribute("tab", "tasks");
			return "/user/myTasks";
			
		case "friends" :
			model.addAttribute("page", userService.getFriends(currentUser, pageable));
			model.addAttribute("reuqestPage", userService.getFriendRequests(currentUser, pageable));
			model.addAttribute("tab", "friends");
			return "/user/myFriends";
		default :
			model.addAttribute("branches", branchService.getBranchByUserId(
					currentUser, new PageRequest(0, 2)));
			model.addAttribute("firendNum", userService.getFriendNum(currentUser));
			model.addAttribute("tab", "profile");
			return "/user/profile";
		}
	}
	
	// 获取用户信息,非本人
	@RequestMapping(value="/{userId}")
	public String userMessage() {
		return "userMessage";
	}
	
	// 获取所有好友请求
	@RequestMapping(value="/friends/request", method=RequestMethod.GET)
	public String getFriendsRequest(Model model, Principal principal){
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("tab", "friends");
		
		return "/user/myFriends";
	}
	
	// 发送好友请求
	@RequestMapping(value="/friends/new", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addFriend(@RequestBody String username, Principal principal){
		
		Map<String, String> map = new HashMap<String, String>();
		try{
			User currentUser = userService.getUserByUsername(principal.getName());
			userService.addFriend(username, currentUser);
			
		} catch (UserNotExistException|FaultyOperationException|ExistedException e) {
			map.put("code", "202");
			map.put("msg", e.getMessage());
			return map;
		}
		map.put("code", "200");
		map.put("msg", "请求发送成功");
		return map;
	}
	
	// 同意或者，不同意
	@RequestMapping(value="/friends/state", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> changeFriendState(
			@RequestParam(name="state", defaultValue="1", required=false) int state,
			@RequestParam(name="username", required=false) String username,
			Principal principal){
		Map<String, String> map = new HashMap<String, String>();
		User currentUser = userService.getUserByUsername(principal.getName());
		try{
			userService.setUserFriendState(username, state, currentUser);
		}catch (UserNotExistException|FaultyOperationException|AbsentException e) {
			map.put("code", "202");
			map.put("msg", e.getMessage());
			return map;
		}
		map.put("code", "200");
		String msg = null;
		if(state == 1){
			msg = "已确认添加!";
		} else if(state == 2){
			msg = "已经拒绝添加!";
		}
		map.put("msg", msg);
		return map;
	}
	
	// 处理 组织邀请
	@RequestMapping(value="/member/request", method=RequestMethod.POST)
	public String handelMemberRequest(@RequestParam(name="branchName", required=false) String branchName, 
			@RequestParam(name="state", required=false, defaultValue="0") int state,
			Principal principal, RedirectAttributes reMoldel){
		try {
			User currentUser = userService.getUserByUsername(principal.getName());
			boolean result = userService.handelMemberRequest(currentUser, branchName, state);
			if(result){
				reMoldel.addFlashAttribute("message", "已同意加入！");
			} else {
				reMoldel.addFlashAttribute("message", "已拒绝加入！");
			}
			return "redirect:/user/me?tab=branches";
		} catch (Exception e) {
			reMoldel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 申请加入组织
	@RequestMapping(value="/branch/request", method=RequestMethod.POST)
	public String RequestJoin(@RequestParam(name="branchName", required=false) String branchName,
			@	RequestParam (name="message", required=false) String message,
			Principal principal, RedirectAttributes reModel) {
		try {
			User currentUser = userService.getUserByUsername(principal.getName());
			boolean result = userService.requestJoin(currentUser, branchName, message);
			if(result){
				reModel.addFlashAttribute("message", "请申请加入 " + branchName + " !");
			} else {
				reModel.addFlashAttribute("message", branchName + "已对你发出邀请，已经直接加入该组织！");
			}
			return "redirect:/user/me?tab=branches";
		}catch (AbsentException|FaultyOperationException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me?tab=branches";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
}
