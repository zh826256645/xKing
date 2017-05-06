package xKing.message.dao;

import java.util.List;

import xKing.message.domain.BranchMessageComment;
import xKing.utils.CrudRepository;

public interface CommentRepository extends CrudRepository<BranchMessageComment, Long> {

	List<BranchMessageComment> findByBranch_idAndBranchMessage_idOrderByCreateTime(long branch_id, long branchMessage_id);
	
	Long countByBranch_idAndBranchMessage_id(long branch_id, long branchMessage_id);
}
