package xKing.branch.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.BranchMemberRequest;
import xKing.utils.CrudRepository;

public interface BranchMemberRequestRepository extends CrudRepository<BranchMemberRequest, Long> {

	BranchMemberRequest findByUser_idAndBranch_id(long user_id, long branch_id);
	
	BranchMemberRequest findByUser_idAndBranch_idAndState(long user_id, long branch_id, int state);
	
	Page<BranchMemberRequest> findByBranch_idAndStateOrderByRequestTimeDesc(long branch_id, int state, Pageable pageable);
	
	Page<BranchMemberRequest> findByUser_idAndStateOrderByRequestTimeDesc(long User_id, int state, Pageable pageable);
	
	long countByBranch_id(long branch_id);
}
