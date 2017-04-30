package xKing.project.web;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.project.domain.Project;
import xKing.project.service.ProjectService;
import xKing.user.domain.User;
import xKing.user.service.UserService;

@Controller
@RequestMapping("/branch/{branchName}/project")
public class ProjectController {
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(path="", method={RequestMethod.GET, RequestMethod.POST})
	public String branchProject(@PathVariable(name="branchName") String branchName,
			@RequestParam(name="projectName", required=false) String projectName,
			Principal principal, Model model, Pageable pageable,
			RedirectAttributes reModel, HttpServletRequest request) throws UnsupportedEncodingException{
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			if(request.getMethod().equalsIgnoreCase("GET")){
				// 判断用户是否由权限
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowTakeTask());
				
				Page<Project> page = projectService.getProjects(currentBranch, pageable);
				
				model.addAttribute("currentBranch", currentBranch);
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("tab", "project");
				model.addAttribute("page", page);
				return "/branch/branchProject";
			} else {
				// 判断用户是否有权限
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateTask());
				
				projectService.createProject(currentBranch, branchMember, projectName);
				reModel.addFlashAttribute("message", "项目 " + projectName + "创建成功！");
				reModel.addFlashAttribute("tag", "project");
				return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project";		
			}
		} catch (FaultyOperationException|ExistedException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project";
		}catch (AbsentException e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
}
