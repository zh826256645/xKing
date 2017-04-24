package xKing.branch.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.user.domain.User;

public interface BranchService {

	Branch createBranch(Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch createBranch(InputStream in, Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch findBranchByBranchName(String branchName);
	
	Branch findBranchByBranchId(long branchId);
	
	InputStream getBranchPicture(String branchName);
	
	List<Branch> getBranchByUserId(User user, Pageable pageable);
	
	boolean checkUserAuthority(BranchMember member, Branch branch, BranchRole branchRole);
	
	Branch changeBranchInformation(Branch branch, MultipartFile branchPicture, Branch currentBranch) throws IOException;
	
	String changeBranchAuthority(Branch currentBranch,BranchMember currentMember, String roleName, final String authorityName);
}
