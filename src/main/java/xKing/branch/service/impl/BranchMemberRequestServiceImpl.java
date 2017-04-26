package xKing.branch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchMemberRequestRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMemberRequest;
import xKing.branch.service.BranchMemberRequestService;
import xKing.user.domain.User;

@Service
@Transactional
public class BranchMemberRequestServiceImpl implements BranchMemberRequestService {

	@Autowired
	private BranchMemberRequestRepository branchMemberRequestRepository;
	
	// 通过用户 id 和组织 id 获取 成员请求
	@Override
	public BranchMemberRequest getByUserAndBranch(User user, Branch branch) {
		return branchMemberRequestRepository.findByUser_idAndBranch_id(user.getId(), branch.getId());
	}
	
	@Override
	public BranchMemberRequest getByUserAndBranchAndState(User user, Branch branch, int state) {
		// TODO Auto-generated method stub
		return branchMemberRequestRepository.findByUser_idAndBranch_idAndState(user.getId(), branch.getId(), state);
	}

	// 通过 组织 Id 和 state 获取 成员请求
	@Override
	public Page<BranchMemberRequest> getByBranchAndState(Branch currentBranch, int state, Pageable pageable) {
		return branchMemberRequestRepository.findByBranch_idAndStateOrderByRequestTimeDesc(currentBranch.getId(), state, pageable);
	}

	// 添加信息的成员请求
	@Override
	public BranchMemberRequest addNewBranchMemberRequest(Branch branch, User user, String message, int state) {
		BranchMemberRequest newMemberRequest = new BranchMemberRequest(branch, user, message, state);
		return branchMemberRequestRepository.save(newMemberRequest);
	}

	// 通过 用户 Id 和 state 获取 组织邀请
	@Override
	public Page<BranchMemberRequest> getByUserAndState(User currentUser, int state, Pageable pageable) {
		return branchMemberRequestRepository.findByUser_idAndStateOrderByRequestTimeDesc(currentUser.getId(), state, pageable);
	}

	// 删除 brnachMemberRequest
	@Override
	public void removeBranchMemberRequest(BranchMemberRequest branchMemberRequest) {
		int state = 0;

		if(branchMemberRequest.getState() == 1) {
			state = 2;
		} else {
			state = 1;
		}
		
		BranchMemberRequest memberRequest = this.getByUserAndBranchAndState(branchMemberRequest.getUser(), branchMemberRequest.getBranch(), state);
		
		if(memberRequest != null) {
			branchMemberRequestRepository.delete(memberRequest);
		}
		branchMemberRequestRepository.delete(branchMemberRequest);
	}
}
