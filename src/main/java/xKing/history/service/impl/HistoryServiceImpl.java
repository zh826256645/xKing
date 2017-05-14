package xKing.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.history.dao.BranchHistoryRepository;
import xKing.history.dao.UserHistoryRepository;
import xKing.history.domain.BranchHisotryType;
import xKing.history.domain.BranchHistory;
import xKing.history.domain.UserHistory;
import xKing.history.service.HistoryService;
import xKing.project.domain.Project;
import xKing.project.domain.Task;
import xKing.user.domain.User;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private BranchHistoryRepository branchHistoryRepository;
	
	@Autowired
	private UserHistoryRepository userHistoryRepository;

	// 记录组织的历史记录
	@Override
	public BranchHistory createBranchHisotry(BranchHistory history) {
		history.setExecutionTime(System.currentTimeMillis());
		return branchHistoryRepository.save(history);
	}

	// 获取任务的历史记录
	@Override
	public List<BranchHistory> findBranchHistoryByTask(Task currentTask) {
		// TODO Auto-generated method stub
		return branchHistoryRepository.findByTask_idOrderByExecutionTimeDesc(currentTask.getId());
	}

	
	// 获取项目的历史记录
	@Override
	public List<BranchHistory> findBranchHistoryByTaskAndProject(Project currentProject, Task currentTask) {
		return branchHistoryRepository.findByProject_idAndTaskOrderByExecutionTimeDesc(currentProject.getId(), currentTask);
	}

	// 根据类型获取历史记录
	@Override
	public List<BranchHistory> findBranchHistoryByProjectAndType(Project currentProject, BranchHisotryType type) {
		// TODO Auto-generated method stub
		return branchHistoryRepository.findByProject_idAndTypeOrderByExecutionTimeDesc(currentProject.getId(), type);
	}

	@Override
	public List<BranchHistory> findbyProjectAndTwoType(Project currentProject, BranchHisotryType oneType,
			BranchHisotryType twoType) {
		return branchHistoryRepository.findByProject_idAndTaskTypeOrMemberType(oneType, twoType, currentProject.getId());
	}

	@Override
	public Page<BranchHistory> findbyBracnhAndTwoType(Branch currentBranch, BranchHisotryType oneType,
			BranchHisotryType twoType, Pageable pageable) {
		return branchHistoryRepository.findByBrancht_idAndTaskTypeOrMemberType(oneType, twoType, currentBranch.getId(), pageable);
	}

	// 记录用户的历史记录
	@Override
	public UserHistory createUserHistory(UserHistory history) {
		return userHistoryRepository.save(history);
	}

	// 获取用户的历史记录
	@Override
	public Page<UserHistory> findUserHistories(User currentUser, Pageable pageable) {
		return userHistoryRepository.findByUser_idOrderByExecutionTimeDesc(currentUser.getId(), pageable);
	}


}
