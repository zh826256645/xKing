package xKing.project.dao;

import java.util.List;

import xKing.project.domain.Problem;
import xKing.utils.CrudRepository;

public interface ProblemRepository extends CrudRepository<Problem, Long> {

	List<Problem> findByTask_idOrderByPublishTImeDesc(long Task_id);
	
	Long countByTask_id(long Task_id);
}
