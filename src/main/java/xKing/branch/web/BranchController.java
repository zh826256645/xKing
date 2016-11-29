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

import xKing.branch.domain.Branch;
import xKing.branch.service.BranchService;
import xKing.user.domain.User;
import xKing.user.service.UserService;

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

	// Branch 主页
	@GetMapping(path="/{branchName}")
	public String intoBranch(@PathVariable("branchName") String branchName) {
		return "branchIndex";
	}
	
	// Branch 注册页面
	@GetMapping(path="/new")
	public String createBranchPage(Principal principal, Model model) {
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute(new Branch());
		return "createBranch";
	}
	
	// Branch 注册
	@PostMapping(path="/new")
	public String createBranch(@Valid Branch branch, Errors errors,
			String yourRoleName, String newComerRoleName,
			Model model, Principal principal) {
		if(errors.hasErrors() || yourRoleName.trim().isEmpty() || newComerRoleName.trim().isEmpty()) {
			model.addAttribute("user", userService.getUserByUsername(principal.getName()));
			return "createBranch";
		}
		try {
			branchService.createBranch(branch, yourRoleName, newComerRoleName, principal.getName());
			return "redirect:/user/me?tab=branches";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "createBranch";
		}
	}
}
