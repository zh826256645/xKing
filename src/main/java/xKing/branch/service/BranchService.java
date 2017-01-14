package xKing.branch.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.user.domain.User;

public interface BranchService {

	Branch createBranch(Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch createBranch(InputStream in, Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch findBranchByBranchName(String branchName);
	
	Branch findBranchByBranchId(long branchId);
	
	InputStream getBranchPicture(String branchName);
	
	List<Branch> getBranchByUserId(User user, Pageable pageable);
}
