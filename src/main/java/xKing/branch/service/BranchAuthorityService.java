package xKing.branch.service;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchAuthority;
import xKing.branch.domain.BranchRole;

public interface BranchAuthorityService {
	
	BranchAuthority initBranchAuthority(Branch currentBranch, BranchRole branchRole);
}
