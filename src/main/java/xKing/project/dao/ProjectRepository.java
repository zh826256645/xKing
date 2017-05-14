package xKing.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.BranchMember;
import xKing.project.domain.Project;
import xKing.utils.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectNameAndBranch_id(String projectName, long branch_id);
	
	Page<Project> findByBranch_idOrderByCreateTimeDesc(long branch_id, Pageable pageable);
	
	Project findByProjectMemberAndId(BranchMember branchMember, long id);
	
	Long countByBranch_id(long branch_id);
	
}
