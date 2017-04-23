package xKing.branch.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.user.domain.User;
import xKing.user.service.UserService;
import xKing.utils.FontImageUtils;

/**
 * Branch 控制器
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
@Controller
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;

	// Branch 主页
	@GetMapping(path="/{branchName}")
	public String intoBranch(@PathVariable("branchName") String branchName,
			Principal principal, Model model, RedirectAttributes reModel) {
		try { 
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			if(branchMember != null) {
				model.addAttribute("currentBranchMember", branchMember);
			} else {
				model.addAttribute("message", "你还未加入该组织!");
			}
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
		model.addAttribute("tab", "index");
		return "/branch/branchIndex";
	}
	
	// Branch 注册页面
	@GetMapping(path="/new")
	public String createBranchPage(Principal principal, Model model) {
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute(new Branch());
		return "/user/createBranch";
	}
	
	// Branch 注册
	@PostMapping(path="/new")
	public String createBranch(
			@RequestPart("branchPicture") MultipartFile branchPicture,
			@Valid Branch branch, Errors errors,
			String yourRoleName, String newComerRoleName,
			Model model,RedirectAttributes reModel,
			Principal principal) {
		if(errors.hasErrors() || yourRoleName.trim().isEmpty() || newComerRoleName.trim().isEmpty()) {
			model.addAttribute("user", userService.getUserByUsername(principal.getName()));
			model.addAttribute("error","错误！");
			return "/user/createBranch";
		}
		
		try {
			if(branchPicture.getOriginalFilename().trim().isEmpty()) {
				// 无图片
				FontImageUtils utils = new FontImageUtils();
				branch.setPicture(utils.randomColor().getRGB() + "");
				branchService.createBranch(branch, yourRoleName, newComerRoleName, principal.getName());
			} else {
				// 有图片
				branch.setPicture(branchPicture.getOriginalFilename());
				branchService.createBranch(branchPicture.getInputStream(), branch, yourRoleName, newComerRoleName, principal.getName());
			}
			reModel.addFlashAttribute("message", "创建 " + branch.getBranchName() + " 成功！");
			return "redirect:/user/me?tab=branches&page=0&size=3";
		} catch (Exception e) {
			model.addAttribute("user", userService.getUserByUsername(principal.getName()));
			model.addAttribute("error", e.getMessage());
			return "/user/createBranch";
		}
	}
	
	// Branch Message 页面
	@GetMapping(path="/{branchName}/message")
	public String getBranchMessage(@PathVariable("branchName") String branchName, 
			Model model) {
		
		model.addAttribute("currentBranch", branchService.findBranchByBranchName(branchName));
		model.addAttribute("tab", "message");
		return "/branch/branchMessage";
	}
	
	
	// 创建信息页面
	@GetMapping(path="/{branchName}/message/new")
	public String createBranchMessagePage(@PathVariable("branchName") String branchName,
			Principal principal, Model model){
		Branch currentBranch = branchService.findBranchByBranchName(branchName);
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("currentBranch", currentBranch);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("tab", "message");
		return "/branch/createBranchMessage";
	}
}
