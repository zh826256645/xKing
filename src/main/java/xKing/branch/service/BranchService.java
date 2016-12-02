package xKing.branch.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;

public interface BranchService {

	Branch createBranch(Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch createBranch(InputStream in, Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch findBranchByBranchName(String branchName);
	
	Branch findBranchByBranchId(long branchId);
	
	InputStream getBranchPicture(String branchName);
	
	List<Branch> getBranchByUserId(String username, Pageable pageable);
}
