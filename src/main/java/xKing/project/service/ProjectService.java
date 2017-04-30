package xKing.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.project.domain.Project;

public interface ProjectService {

	Project createProject(Branch currentBranch, BranchMember currentMember, String projectName);
	
	Project getProject(Branch currentBranch, String projectName);
	
	Page<Project> getProjects(Branch currentBranch, Pageable pageable);
	

}
