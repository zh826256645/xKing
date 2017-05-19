package xKing.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.project.domain.Problem;
import xKing.project.domain.Project;
import xKing.project.domain.State;
import xKing.project.domain.Task;
import xKing.user.domain.User;

public interface ProjectService {

	Project createProject(Branch currentBranch, BranchMember currentMember, String projectName);
	
	Project getProject(Branch currentBranch, String projectName);
	
	Page<Project> getProjects(Branch currentBranch, Pageable pageable);
	
	Project addProjectMember(Branch currentBranch, Project project, User user);
	
	Long getProjectNum(Branch currentBranch);
	
	Task createTask(Branch currentBranch,Project currentProject, BranchMember currentBranchMember, Task task, String startTimeStr, String endTimeStr, long memberId);
	
	Page<Task> getTasksByProject(Project project, State state, Pageable pageable);
	
	Project getProjectByMember(BranchMember currentMember, Project currentProject);
	
	Task getTaskByProject(Project currentProject, long taskId);
	
	Task addSubTask(Project currentProject, Task currentTask, BranchMember currentMember, String content);
	
	Task getTaskByTaskMember(Task currentTask, BranchMember member);
	
	Task changeTaskState(Task currentTask, State state, long subTaskId);
	
	Problem publishProblem(BranchMember currentMember, Task currentTask, String content);
	
	Long getTaskProblemNum(Task currentTask);
	
	List<Problem> getTaskProblems(Task currentTask);
	
	Page<Task> getUserTasks(User currentUser, Task ftask, Pageable pageable);
	
	Long getUserNotFinishTask(User currentUser);
}
