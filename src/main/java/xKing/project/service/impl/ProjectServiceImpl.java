package xKing.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.service.BranchMemberSerivce;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;
import xKing.project.dao.ProblemRepository;
import xKing.project.dao.ProjectRepository;
import xKing.project.dao.TaskRepository;
import xKing.project.domain.Problem;
import xKing.project.domain.ProblemState;
import xKing.project.domain.Project;
import xKing.project.domain.State;
import xKing.project.domain.Task;
import xKing.project.service.ProjectService;
import xKing.user.domain.User;
import xKing.utils.Utils;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@Autowired
	private MailService mailService;

	
	// 获取组织的项目
	@Override
	public Project getProject(Branch currentBranch, String projectName) {
		Project project = projectRepository.findByProjectNameAndBranch_id(projectName, currentBranch.getId());
		if(project == null) {
			throw new AbsentException("该项目不存在！");
		}
		return project;
	}
	
	// 创建 project
	@Override
	public Project createProject(Branch currentBranch, BranchMember currentMember, String projectName) {
		Project project = projectRepository.findByProjectNameAndBranch_id(projectName, currentBranch.getId());
		if(projectName == null || projectName.trim().isEmpty()) {
			throw new FaultyOperationException("项目名不能为空！");
		}
		if(project != null) {
			throw new ExistedException("该项目名已经被使用！");
		}
		Project newProject = new Project();
		newProject.init(currentBranch, currentMember, projectName);
		return projectRepository.save(newProject);
	}

	// 获取组织的项目
	@Override
	public Page<Project> getProjects(Branch currentBranch, Pageable pageable) {
		return projectRepository.findByBranch_idOrderByCreateTimeDesc(currentBranch.getId(), pageable);
	}

	// 添加组织成员
	@Override
	public Project addProjectMember(Branch currentBranch, Project project, User user) {
		BranchMember branchMember = branchMemberService.findByBranchidAndUserId(currentBranch, user);
		if(branchMember == null) {
			throw new FaultyOperationException("组织里没有该成员！");
		}
		Project this_project = projectRepository.findByProjectMemberAndId(branchMember, project.getId());
		if(this_project != null) {
			throw new FaultyOperationException("该成员已经是项目组成员了，请不要重复添加");
		}
		project.getProjectMember().add(branchMember);
		Project thisProject = projectRepository.save(project);
		
		Mail mail = mailService.initMessageMail(user, "你已经加入了" + currentBranch.getBranchName() + "的" + project.getProjectName() + "项目！");
		mailService.sendMessageEmailToUserByVelocity(user.getEmail(), mail);
		return thisProject;
	}

	// 获取项目数
	@Override
	public Long getProjectNum(Branch currentBranch) {
		return projectRepository.countByBranch_id(currentBranch.getId());
	}

	// 判断用户是否是组织成员
	@Override
	public Project getProjectByMember(BranchMember currentMember, Project project) {
		Project this_project = projectRepository.findByProjectMemberAndId(currentMember, project.getId());
		return this_project;
	}

	// 创建任务
	@Override
	public Task createTask(Branch currentBranch, Project currentProject, BranchMember currentBranchMember, Task task,
			String startTimeStr, String endTimeStr, long memberId) {
		task.setEndTime(xKing.utils.Utils.timeStrToLong(endTimeStr));
		task.setProject(currentProject);
		task.setPublishMember(currentBranchMember);
		task.setStartTime(xKing.utils.Utils.timeStrToLong(startTimeStr));
		task.setState(State.New);
		task.setPublishTime(System.currentTimeMillis());
		BranchMember member = branchMemberService.findByBranchAndMember(currentBranch, memberId);
		if(member == null) {
			throw new FaultyOperationException("没有该成员");
		}
		task.getTakeMembers().add(member);
		task.getUsers().add(member.getUser());
		
		Task this_task = taskRepository.save(task);
		
		Mail mail = mailService.initMessageMail(member.getUser(),
				currentBranchMember.getMemberName() + "给你在" + currentProject.getProjectName() + "发布了新任务！" );
		mailService.sendMessageEmailToUserByVelocity(member.getUser().getEmail(), mail);
		
		return this_task;
	}

	// 获取项目的任务
	@Override
	public Page<Task> getTasksByProject(Project project, Pageable pageable) {
		return taskRepository.findByProject_idAndFtaskOrderByPublishTimeDesc(project.getId(),null, pageable);
	}

	// 获取任务信息
	@Override
	public Task getTaskByProject(Project currentProject, long taskId) {
		return taskRepository.findByProject_idAndIdAndFtask(currentProject.getId(), taskId, null);
	}

	// 添加子任务
	@Override
	public Task addSubTask(Project currentProject, Task currentTask, BranchMember currentMember, String content) {
		Task task = new Task();
		task.setContent(content);
		task.setTitle(currentTask.getTitle());
		task.setStartTime(currentTask.getStartTime());
		task.setEndTime(currentTask.getEndTime());
		task.setPublishTime(System.currentTimeMillis());
		task.setType(currentTask.getType());
		task.setState(State.New);
		task.setProject(currentProject);
		task.setFtask(currentTask);
		task.setPublishMember(currentMember);
		task.setTaskLevel(currentTask.getTaskLevel());
		task.getTakeMembers().addAll(currentTask.getTakeMembers());
		task.getUsers().addAll(currentTask.getUsers());
		Task this_task = taskRepository.save(task);
		
		for(User user: currentTask.getUsers()){
			Mail mail = mailService.initMessageMail(user, currentProject.getProjectName() + "项目的" + currentTask.getTitle() + "任务有了新的子任务！" );
			mailService.sendMessageEmailToUserByVelocity(user.getEmail(), mail);
		}
		return this_task;
	}

	// 查看组织成员是否是任务指定成员
	@Override
	public Task getTaskByTaskMember(Task currentTask, BranchMember member) {
		return taskRepository.findByTakeMembersAndId(member, currentTask.getId());
	}

	// 修改任务状态
	@Override
	public Task changeTaskState(Task currentTask, State state, long subTaskId) {
		Task needChangeTask = null;
		if(state == null) {
			throw new FaultyOperationException("错误操作！");
		}
		if(subTaskId != 0) {
			needChangeTask = taskRepository.findByIdAndFtask_id(subTaskId, currentTask.getId());
		} else {
			needChangeTask  = currentTask;
		}
		
		if(needChangeTask == null) {
			throw new FaultyOperationException("没有这个子任务");
		}
		
		if(needChangeTask.getStartTime() > Utils.getTodayTimeLong()) {
			throw new FaultyOperationException("任务还未开始, 不能修改其状态");
		}
		
		if(needChangeTask.getEndTime() < Utils.getTodayTimeLong()) {
			throw new FaultyOperationException("任务已经结束，不能修改其状态");
		}
		
		if(needChangeTask.getState() == State.Finish) {
			throw new FaultyOperationException("任务已完成，不能更改其状态！");
		}

		if(subTaskId == 0 && state == State.Finish) {
			for(Task task: currentTask.getSubtasks()){
				if(task.getState() != State.Finish) {
					throw new FaultyOperationException("还有子任务未完成");
				}
			}
		}
		
		if(needChangeTask.getState() == state) {
			throw new FaultyOperationException("任务已经是该状态，请勿重复修改");
		}
		
		if(state == State.New) {
			throw new FaultyOperationException("不能将任务修改为新建状态！");
		}
		needChangeTask.setState(state);
		Task this_task = taskRepository.save(needChangeTask);
		
		Mail mail = mailService.initMessageMail(currentTask.getPublishMember().getUser(), currentTask.getTitle() + "任务的状态已被修改！" );
		mailService.sendMessageEmailToUserByVelocity(currentTask.getPublishMember().getUser().getEmail(), mail);
		return this_task;
	}

	// 发表问题
	@Override
	public Problem publishProblem(BranchMember currentMember, Task currentTask, String content) {
		if(content == null || content.trim().isEmpty()) {
			throw new FaultyOperationException("问题内容不能为空！");
		}
		Problem problem = new Problem();
		problem.setContent(content);
		problem.setMember(currentMember);
		problem.setProblemState(ProblemState.New);
		problem.setTask(currentTask);
		problem.setPublishTIme(System.currentTimeMillis());
		Problem this_problem = problemRepository.save(problem);
		
		Mail mail = mailService.initMessageMail(currentTask.getPublishMember().getUser(), currentMember.getMemberName() + "在任务" + currentTask.getTitle() + "中提出了新问题！");
		mailService.sendMessageEmailToUserByVelocity(currentTask.getPublishMember().getUser().getEmail(), mail); 
		return this_problem;
	}

	// 获取任务问题数量
	@Override
	public Long getTaskProblemNum(Task currentTask) {
		return problemRepository.countByTask_id(currentTask.getId());
	}

	// 获取任务问题
	@Override
	public List<Problem> getTaskProblems(Task currentTask) {
		return problemRepository.findByTask_idOrderByPublishTImeDesc(currentTask.getId());
	}

	// 获取用户的任务
	@Override
	public Page<Task> getUserTasks(User currentUser, Task ftask, Pageable pageable) {
		return taskRepository.findByUsersAndFtaskOrderByPublishTimeDesc(currentUser, null, pageable);
	}

	// 获取用户未完成的任务
	@Override
	public Long getUserNotFinishTask(User currentUser) {
		return taskRepository.countByUsersAndFtaskAndStateNot(currentUser, null, State.Finish);
	}
}
