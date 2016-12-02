package xKing.branch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.user.domain.User;

public interface BranchMemberSerivce {
	BranchMember findByBranchidAndUserId(Branch branch, User user);
	BranchMember addBranchMember(String memberName, String email,Branch branch, BranchRole branchRole, User user);
	List<BranchMember> findByUserId(User user);
	Page<BranchMember> findByUserId(User user, Pageable pageable);
	Page<BranchMember> findByUserIdOrderByJoinTimeDesc(User user, Pageable pageable);
}
