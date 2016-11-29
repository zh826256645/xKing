package xKing.branch.dao;

import java.util.List;

import xKing.branch.domain.BranchMember;
import xKing.utils.CrudRepository;

public interface BranchMemberRepository extends CrudRepository<BranchMember, Long> {

	BranchMember findByBranch_idAndUser_id(long branch_id, long user_id);
	
	List<BranchMember> findByUser_id(long user_id);
}
