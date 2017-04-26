package xKing.branch.dao;

import xKing.branch.domain.BranchMemberRequest;
import xKing.utils.CrudRepository;

public interface BranchMemberRequestRepository extends CrudRepository<BranchMemberRequest, Long> {

	BranchMemberRequest findByUser_idAndBranch_id(long user_id, long branch_id);
}
