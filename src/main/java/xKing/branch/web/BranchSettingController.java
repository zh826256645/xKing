package xKing.branch.web;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchRoleSerivce;
import xKing.branch.service.BranchService;
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
			
		}catch(SameNameException e){
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/"+ URLEncoder.encode(branchName, "utf-8") + "/setting";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/user/me";
		
	}
}
