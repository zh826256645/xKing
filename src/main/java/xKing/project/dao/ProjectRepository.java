package xKing.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.project.domain.Project;
import xKing.utils.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectNameAndBranch_id(String projectName, long branch_id);
	
	Page<Project> findByBranch_idOrderByCreateTimeDesc(long branch_id, Pageable pageable);
}
