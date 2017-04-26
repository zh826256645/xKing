package xKing.branch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchAuthorityRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchAuthority;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchAuthorityService;

@Service
@Transactional
public class BranchAuthorityServiceImpl implements BranchAuthorityService {
	
	@Autowired
	private BranchAuthorityRepository branchAuthorityRepository;

	// 初始化 branchAuthority
	@Override
	public BranchAuthority initBranchAuthority(Branch currentBranch, BranchRole branchRole) {
		BranchAuthority branchAuthority = new BranchAuthority();
		branchAuthority.init(currentBranch, branchRole);
		BranchAuthority currentBranchAuthority = branchAuthorityRepository.save(branchAuthority);
		return currentBranchAuthority;
	}

	// 对 branchAuthority 进行重置
	@Override
	public BranchAuthority resetBranchAthority(Branch currentBranch) {
		BranchAuthority oldBranchAuthority = currentBranch.getBranchAuthority();
		oldBranchAuthority.reset();
		return branchAuthorityRepository.save(oldBranchAuthority);
	}

}
