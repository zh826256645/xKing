package xKing.project.service.impl;

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
import xKing.project.dao.ProjectRepository;
import xKing.project.dao.TaskRepository;
import xKing.project.domain.Project;
import xKing.project.domain.State;
import xKing.project.domain.Task;
import xKing.project.service.ProjectService;
import xKing.user.domain.User;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private TaskRepository taskRepository;

	
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
		if(projectName == null) {
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
		return projectRepository.save(project);
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
			throw new FaultyOperationException("没有改成员");
		}
		task.getTakeMembers().add(member);
		
		return taskRepository.save(task);
	}

	// 获取项目的任务
	@Override
	public Page<Task> getTasksByProject(Project project, Pageable pageable) {
		return taskRepository.findByProject_idAndFtask(project.getId(),null, pageable);
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
		return taskRepository.save(task);
	}
}
