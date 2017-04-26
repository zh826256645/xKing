package xKing.branch.service;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMemberRequest;
import xKing.user.domain.User;

public interface BranchMemberRequestService {

	BranchMemberRequest getByUserAndBranch(User user, Branch branch);
}
