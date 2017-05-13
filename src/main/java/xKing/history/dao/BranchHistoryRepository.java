package xKing.history.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import xKing.history.domain.BranchHisotryType;
import xKing.history.domain.BranchHistory;
import xKing.project.domain.Task;
import xKing.utils.CrudRepository;

public interface BranchHistoryRepository extends CrudRepository<BranchHistory, Long> {
	
	List<BranchHistory> findByTask_idOrderByExecutionTimeDesc(long task_id);
	
	List<BranchHistory> findByProject_idAndTaskOrderByExecutionTimeDesc(long project_id, Task task);
	
	List<BranchHistory> findByProject_idAndTypeOrderByExecutionTimeDesc(long project_id, BranchHisotryType type);
	
	@Query(value="select bh from BranchHistory bh where (bh.type=?1 or bh.type=?2) and bh.project.id=?3 order by bh.executionTime desc",
			countQuery="select count(bh) from BranchHistory bh where (bh.type=?1 or bh.type=?2) and bh.project.id=?3")
	List<BranchHistory> findByProject_idAndTaskTypeOrMemberType( BranchHisotryType type, BranchHisotryType memberType, long project_id);
	
	@Query(value="select bh from BranchHistory bh where (bh.type=?1 or bh.type=?2) and bh.branch.id=?3 order by bh.executionTime desc",
			countQuery="select count(bh) from BranchHistory bh where (bh.type=?1 or bh.type=?2) and bh.branch.id=?3")
	Page<BranchHistory> findByBrancht_idAndTaskTypeOrMemberType( BranchHisotryType type, BranchHisotryType memberType, long Branch_id, Pageable pageable);
}
