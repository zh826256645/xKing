package xKing.branch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

}
