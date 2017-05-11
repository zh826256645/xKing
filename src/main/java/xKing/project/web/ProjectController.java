package xKing.project.web;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
import xKing.project.domain.Task;
import xKing.project.service.ProjectService;
import xKing.user.domain.User;
import xKing.user.service.UserService;
import xKing.utils.Utils;

/**
 * 组织信息控制器
 * 
 * @author zhonghao
 *
 */
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
	
	// 获取项目成员信息
	@RequestMapping(path="/{projectName}/member", method=RequestMethod.GET)
	public String getProjectMember(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowTakeTask());
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			model.addAttribute("currentProject", currentProject);
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("tab", "projectMember");
			
			return "/branch/projectMember";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 项目成员添加
	@RequestMapping(path="/{projectName}/member", method=RequestMethod.POST)
	public String addProjectMember(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			@RequestParam(name="username") String username,
			Principal principal, RedirectAttributes reModel){
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			if(currentProject.getBranchMember().getId() != branchMember.getId()){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeTask());
			}
			
			User user = userService.getUserByUsername(username);
			projectService.addProjectMember(currentBranch, currentProject, user);
			
			reModel.addFlashAttribute("message", "成员添加成功！");
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/member";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	// 项目任务页面
	@RequestMapping(path="/{projectName}/task", method=RequestMethod.GET)
	public String projectTasks(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			Principal principal, Model model, RedirectAttributes reModel, Pageable pageable) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			if(currentProject.getBranchMember().getId() != branchMember.getId()){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowChangeTask());
			}
			
			Page<Task> page = projectService.getTasksByProject(currentProject, pageable);
			
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("currentProject", currentProject);
			model.addAttribute("tab", "projectTask");
			model.addAttribute("page", page);
			return "/branch/projectTasks";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@RequestMapping(path="/{projectName}/task/new", method=RequestMethod.GET)
	public String createProjectTaskPage(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			Project thisProject = projectService.getProjectByMember(branchMember, currentProject );
			if(thisProject == null){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateTask());
			}
			
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("currentProject", currentProject);
			model.addAttribute("task", new Task());
			model.addAttribute("tab", "projectTask");
			return "/branch/createProjectTask";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@RequestMapping(path="/{projectName}/task/new", method=RequestMethod.POST)
	public String createProjectTask(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			@RequestParam(name="startTimeStr", required=false) String startTimeStr,
			@RequestParam(name="endTimeStr", required=false) String endTimeStr,
			@RequestParam(name="memberId", required=false, defaultValue="0") long memberId,
			@Validated Task task, Errors errors,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			Project thisProject = projectService.getProjectByMember(branchMember, currentProject );
			if(thisProject == null){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateTask());
			}
			
			if(errors.hasErrors() || startTimeStr == null || startTimeStr.trim().isEmpty() || endTimeStr == null || endTimeStr.trim().isEmpty() || memberId == 0 || 
					Utils.timeStrToLong(startTimeStr) == 0 || Utils.timeStrToLong(endTimeStr) == 0 ||
					Utils.timeStrToLong(startTimeStr) > Utils.timeStrToLong(endTimeStr) || Utils.getTodayTimeLong() > Utils.timeStrToLong(startTimeStr)  ){
				if(startTimeStr == null || startTimeStr.trim().isEmpty()  || endTimeStr == null || endTimeStr.trim().isEmpty()) {
					model.addAttribute("error", "任务时间不能为空！");
				} else if(Utils.timeStrToLong(startTimeStr) == 0 || Utils.timeStrToLong(endTimeStr) == 0 ) {
					model.addAttribute("error", "任务格式错误！");
				} else if(Utils.timeStrToLong(startTimeStr) > Utils.timeStrToLong(endTimeStr)) {
					model.addAttribute("error", "任务开始时间不能大于结束时间");
				} else if(Utils.getTodayTimeLong() > Utils.timeStrToLong(startTimeStr)) {
					model.addAttribute("error", "任务开始时间不能小于今天");
				}
					else {
					model.addAttribute("error", "任务标题、内容、时间、优先级、类型、指派人员均不能空！");
				}
				model.addAttribute("currentBranch", currentBranch);
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("currentProject", currentProject);
				model.addAttribute("task", task);
				model.addAttribute("tab", "projectTask");
				model.addAttribute("startTimeStr", startTimeStr);
				model.addAttribute("endTimeStr", endTimeStr);
				model.addAttribute("memberId", memberId);
				return "/branch/createProjectTask";
			}
			
			projectService.createTask(currentBranch, currentProject, branchMember, task, startTimeStr, endTimeStr, memberId);
			
			reModel.addFlashAttribute("message", "任务发布成功！");
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/task";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@RequestMapping(path="/{projectName}/task/{taskId}", method=RequestMethod.GET)
	public String getTask(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			@PathVariable(name="taskId") long taskId,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			Project thisProject = projectService.getProjectByMember(branchMember, currentProject );
			
			if(thisProject == null){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowTakeTask());
			}
			
			Task currentTask = projectService.getTaskByProject(currentProject, taskId);
			
			if(currentTask == null) {
				reModel.addFlashAttribute("error", "没有这个任务");
				return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/task";
			}
			model.addAttribute("tab", "projectTask");
			model.addAttribute("currentTask", currentTask);
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("currentProject", currentProject);
			return "/branch/projectTaskPage";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@RequestMapping(path="/{projectName}/task/{taskId}/subTask", method=RequestMethod.GET)
	public String addSubTaskPage(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			@PathVariable(name="taskId") long taskId,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			
			Task currentTask = projectService.getTaskByProject(currentProject, taskId);
			
			if(currentTask == null) {
				reModel.addFlashAttribute("error", "没有这个任务");
				return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/task";
			}
			
			if(currentTask.getPublishMember().getId() != branchMember.getId()){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateTask());
			}
			
			model.addAttribute("tab", "projectTask");
			model.addAttribute("task", currentTask);
			model.addAttribute("currentBranch", currentBranch);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("currentProject", currentProject);
			return "/branch/createProjectSubTask";
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
	@RequestMapping(path="/{projectName}/task/{taskId}/subTask", method=RequestMethod.POST)
	public String addSubTask(@PathVariable(name="branchName") String branchName,
			@PathVariable(name="projectName") String projectName,
			@PathVariable(name="taskId") long taskId,
			@RequestParam(name="content", required=false) String content,
			Principal principal, Model model, RedirectAttributes reModel) {
		try{
			Branch currentBranch = branchService.findBranchByBranchName(branchName);
			User currentUser = userService.getUserByUsername(principal.getName());
			BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, currentUser);
			
			Project currentProject = projectService.getProject(currentBranch, projectName);
			
			Task currentTask = projectService.getTaskByProject(currentProject, taskId);
			
			if(currentTask == null) {
				reModel.addFlashAttribute("error", "没有这个任务");
				return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/task";
			}
		
			
			if(currentTask.getPublishMember().getId() != branchMember.getId()){
				branchService.checkUserAuthority(branchMember, currentBranch, currentBranch.getBranchAuthority().getAllowCreateTask());
			}
			
			if(content == null || content.trim().isEmpty()) {
				model.addAttribute("error", "子任务内容不能为空");
				model.addAttribute("tab", "projectTask");
				model.addAttribute("task", currentTask);
				model.addAttribute("currentBranch", currentBranch);
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("currentProject", currentProject);
				return "/branch/createProjectSubTask";
			}
			
			projectService.addSubTask(currentProject, currentTask, branchMember, content);
			
			reModel.addFlashAttribute("message", "子任务添加成功!");
			return "redirect:/branch/" + UriUtils.encode(branchName, "utf-8") + "/project/" + UriUtils.encode(projectName, "utf-8") + "/task/" + taskId;
		}catch (Exception e) {
			reModel.addFlashAttribute("error", e.getMessage());
			return "redirect:/user/me";
		}
	}
	
}
