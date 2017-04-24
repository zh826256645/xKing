package xKing.branch.web;

/**
 * Branch 设置控制器
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchRoleSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.FaultyOperationException;
import xKing.exception.SameNameException;
import xKing.user.domain.User;
import xKing.user.service.UserService;

@Controller
@RequestMapping("/branch")
public class BranchSettingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private BranchRoleSerivce branchRoleSerivce;
	
	@Autowired
	private BranchMemberSerivce branchMemberSerivce;

	// Branch 设置页面
	@RequestMapping(path="/{branchName}/setting",method=RequestMethod.GET)
	public String settingPage(@PathVariable("branchName") String branchName,
			Principal principal, Model model, RedirectAttributes reModel){
		try {
			User currentUser = userService.getUserByUsername(principal.getName());
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			BranchMember currentUserMember = branchMemberSerivce.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否有权限
			branchService.checkUserAuthority(currentUserMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeInformation());
			
			List<BranchRole> currentBranchRoleList = branchRoleSerivce.findByBranchId(currentBranch);
			model.addAttribute("currentBranchRoleList", currentBranchRoleList);
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("branch", new Branch());
			
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}

		return "/branch/branchSetting";
	}
	
	// 修改 Branch 基本信息
	@RequestMapping(path="/{branchName}/setting",method=RequestMethod.POST)
	public String setting(@PathVariable("branchName") String branchName,
			@RequestPart("branchPicture") MultipartFile branchPicture, Branch branch,
			Principal principal, Model model, RedirectAttributes reModel) throws UnsupportedEncodingException{
		try {
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember currentUserMember = branchMemberSerivce.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否有权限
			branchService.checkUserAuthority(currentUserMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeInformation());
		
			currentBranch = branchService.changeBranchInformation(branch, branchPicture, currentBranch);
			reModel.addFlashAttribute("message", "修改 " + currentBranch.getBranchName() + " 信息成功");
			return "redirect:/branch/"+ URLEncoder.encode(currentBranch.getBranchName(), "utf-8") +"/setting";
			
		} catch(SameNameException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ URLEncoder.encode(branchName, "utf-8") + "/setting";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/user/me";
		
	}
	
	// 添加 Branch 角色
	@RequestMapping(path="/{branchName}/role/new", method=RequestMethod.POST)
	public String addBranchRole(@PathVariable("branchName") String branchName,
			@RequestParam(required=false, name="roleName") String roleName,
			@RequestParam(required=false, name="roleLevle", defaultValue="0") int roleLevel,
			Principal principal, RedirectAttributes reModel) throws UnsupportedEncodingException {
		try{
			User currentUser = userService.getUserByUsername(principal.getName());
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			BranchMember currentUserMember = branchMemberSerivce.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否有权限
			branchService.checkUserAuthority(currentUserMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeInformation());
			
			BranchRole newBranchRole = branchRoleSerivce.addBranchRole(currentBranch, roleName, roleLevel);
			reModel.addFlashAttribute("message", "添加角色 " + newBranchRole.getRoleName() + " 成功！");
			return "redirect:/branch/"+ URLEncoder.encode(currentBranch.getBranchName(), "utf-8") +"/setting";
			
		} catch(SameNameException|FaultyOperationException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ URLEncoder.encode(branchName, "utf-8") + "/setting";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/user/me";
	}
}
