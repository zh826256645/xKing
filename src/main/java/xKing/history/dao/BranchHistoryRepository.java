package xKing.history.dao;

import java.util.List;

import xKing.history.domain.BranchHistory;
import xKing.utils.CrudRepository;

public interface BranchHistoryRepository extends CrudRepository<BranchHistory, Long> {
	
	List<BranchHistory> findByTask_idOrderByExecutionTimeDesc(long task_id);
}
