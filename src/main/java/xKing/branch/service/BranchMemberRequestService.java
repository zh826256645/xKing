package xKing.branch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMemberRequest;
import xKing.user.domain.User;

public interface BranchMemberRequestService {

	BranchMemberRequest getByUserAndBranch(User user, Branch branch);
	
	BranchMemberRequest getByUserAndBranchAndState(User user, Branch branch, int state);
	
	BranchMemberRequest addNewBranchMemberRequest(Branch branch, User user, String message, int state);
	
	Page<BranchMemberRequest> getByBranchAndState(Branch currentBranch, int state, Pageable pageable);
	
	Page<BranchMemberRequest> getByUserAndState(User currentUser, int state, Pageable pageable);
	
	void removeBranchMemberRequest(BranchMemberRequest branchMemberRequest);
}
