package xKing.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.project.domain.Project;
import xKing.project.domain.Task;
import xKing.user.domain.User;

public interface ProjectService {

	Project createProject(Branch currentBranch, BranchMember currentMember, String projectName);
	
	Project getProject(Branch currentBranch, String projectName);
	
	Page<Project> getProjects(Branch currentBranch, Pageable pageable);
	
	Project addProjectMember(Branch currentBranch, Project project, User user);
	
	Long getProjectNum(Branch currentBranch);
	
	Task createTask(Branch currentBranch,Project currentProject, BranchMember currentBranchMember, Task task, String startTimeStr, String endTimeStr, long memberId);
	
	Page<Task> getTasksByProject(Project project, Pageable pageable);
	
	Project getProjectByMember(BranchMember currentMember, Project currentProject);
}
