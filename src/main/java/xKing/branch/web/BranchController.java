package xKing.branch.web;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchMemberRequest;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberRequestService;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchRoleSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.FaultyOperationException;
import xKing.user.domain.User;
import xKing.user.exception.UserNotExistException;
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
	
	@Autowired
	private BranchMemberRequestService branchMemberRequestService;
	
	@Autowired
	private BranchRoleSerivce branchRoleService;

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
			
			// 判断用户是否有权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowInto());
			
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
	
	// 用户页面
	@GetMapping(path="/{branchName}/member")
	public String getBranchMemberPage(@PathVariable("branchName") String branchName,
			Principal principal, Pageable pageable, Model model, RedirectAttributes reModel){
		try{
			System.out.println(branchName);
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowSeeMember());
			
			Page<BranchMember> currentMemberPage = branchMemberService.findByBranch(currentBranch, pageable);
			Page<BranchMemberRequest> inviteRequestPage = branchMemberRequestService.getByBranchAndState(currentBranch, 1, pageable);
			Page<BranchMemberRequest> joinRequestPage = branchMemberRequestService.getByBranchAndState(currentBranch, 2, pageable);
			List<BranchRole> currentBranchRoleList = branchRoleService.findByBranchId(currentBranch);
			
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("page", currentMemberPage);
			model.addAttribute("invitePage", inviteRequestPage);
			model.addAttribute("joinRequestPage", joinRequestPage);
			model.addAttribute("currentBranchRoleList", currentBranchRoleList);
			model.addAttribute("tab", "member");
		return "/branch/branchMember";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 邀请用户
	@PostMapping(path="/{branchName}/member/invite")
	public String inviteUser(@PathVariable("branchName") String branchName,
			@RequestParam(name="username", required=false) String username,
			@RequestParam(name="message", required=false) String message,
			Principal principal, Model model, RedirectAttributes reModel) throws UnsupportedEncodingException{
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowAddMember());
			
			boolean result = branchService.inviteUser(currentBranch, username, message);
			if(result){
				reModel.addFlashAttribute("message", "邀请成功！");
			} else {
				reModel.addFlashAttribute("message", "该用户已经提交了申请，自动同意加入！");
			}
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		}catch (FaultyOperationException|UserNotExistException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 处理用户加入请求
	@PostMapping(path="/{branchName}/member/request")
	public String handleJoinRequest(@PathVariable("branchName") String branchName,
			@RequestParam(name="username", required=false) String username,
			@RequestParam(name="state", required=false, defaultValue="0") int state, 
			Principal principal, Model model, RedirectAttributes reModel ) throws UnsupportedEncodingException {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowAddMember());
			
			boolean result = branchService.handleJoinRequest(currentBranch, username, state);
			if(result){
				reModel.addFlashAttribute("message", "已同意添加！");
			} else {
				reModel.addFlashAttribute("message", "已拒绝添加！");
			}
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		}catch (FaultyOperationException|UserNotExistException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 修改成员角色
	@PostMapping(path="/{branchName}/member/role")
	public String changeMemberRole(@PathVariable(name="branchName") String branchName, 
			@RequestParam(name="username", required=false) String username,
			@RequestParam(name="roleName", required=false) String roleName,
			Principal principal, RedirectAttributes reModel) throws UnsupportedEncodingException {
		try {
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeMember());
			
			branchService.changeMemberRole(currentBranch, branchMember, username, roleName);
			reModel.addFlashAttribute("message", "修改成员 " + username + " 角色为 " + roleName);
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		} catch (FaultyOperationException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ UriUtils.encode(branchName, "utf-8") + "/member";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
}
