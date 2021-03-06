package xKing.branch.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.BranchMember;
import xKing.utils.CrudRepository;

public interface BranchMemberRepository extends CrudRepository<BranchMember, Long> {

	BranchMember findByBranch_idAndUser_id(long branch_id, long user_id);
	
	BranchMember findByBranch_idAndId(long branch_id, long id);
	
	List<BranchMember> findByUser_id(long user_id);
	
	Page<BranchMember> findByUser_id(long user_id,Pageable pageable);
	
	Page<BranchMember> findByUser_idOrderByJoinTimeDesc(long User_id,Pageable pageable);
	
	Page<BranchMember> findByBranch_id(long branch_id,Pageable pageable);
	
	long countByBranch_id(long branch_id);
	
	List<BranchMember> findByBranch_id(long branch_id);
	
}
