package xKing.message.web;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.message.domain.BranchMessage;
import xKing.message.domain.MessageTag;
import xKing.message.service.MessageService;
import xKing.user.domain.User;
import xKing.user.service.UserService;

@Controller
@RequestMapping("/branch/{branchName}/message")
public class MessageController {
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private MessageService messageService;
	
	// Branch Message 页面
	@GetMapping(path="")
	public String getBranchMessage(@PathVariable("branchName") String branchName, Principal principal, 
			Model model, RedirectAttributes reModel) {
		
		try {
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowSeeMessage());
		
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("tab", "message");
			return "/branch/branchMessage";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	
	// 创建信息页面
	@GetMapping(path="/new")
	public String createBranchMessagePage(@PathVariable("branchName") String branchName,
			Principal principal, Model model,RedirectAttributes reModel){
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowSeeMessage());
			
			List<MessageTag> messageTags = messageService.getMessageTags(currentBranch);
			
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("messageTags", messageTags);
			model.addAttribute("tab", "message");
			model.addAttribute("branchMessage", new BranchMessage());
		return "/branch/createBranchMessage";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@PostMapping(path="/tag/new")
	public String createMessageTag(@PathVariable("branchName") String branchName,
			@RequestParam(name="tagName") String tagName, Principal principal,
			Model model,RedirectAttributes reModel) throws UnsupportedEncodingException{
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateMessage());
			
			messageService.addMessageTag(currentBranch, branchMember, tagName);
			
			reModel.addFlashAttribute("message", "添加标签 " + tagName + " 成功");
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/message/new";
		}catch (ExistedException|FaultyOperationException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/message/new";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@PostMapping(path="/new")
	public String createMessage(@PathVariable("branchName") String branchName, Principal principal,
			@Validated BranchMessage branchMessage, Errors errors, 
			@RequestParam(name="tagName", required=false) String tagName,
			Model model, RedirectAttributes reModel) throws UnsupportedEncodingException {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			// 判断用户是否由权限
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateMessage());
			
			if(errors.hasErrors()) {
				List<MessageTag> messageTags = messageService.getMessageTags(currentBranch);
				model.addAttribute("currentBranch", currentBranch);
				model.addAttribute("messageTags", messageTags);
				model.addAttribute("tab", "message");
				model.addAttribute("error", "文章标题或者内容不能为空！");
				return "/branch/createBranchMessage";
			}
			MessageTag messageTag = null;
			if(tagName != null && ! tagName.trim().isEmpty()) {
				messageTag = messageService.getMessageTagByBranchAndTagName(currentBranch, tagName);
			}
			messageService.createMessage(currentBranch, branchMember, branchMessage, messageTag);
			
			reModel.addFlashAttribute("message", "添加信息成功");
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/message/new";
		}catch (ExistedException|FaultyOperationException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/message/new";
		} catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
}
