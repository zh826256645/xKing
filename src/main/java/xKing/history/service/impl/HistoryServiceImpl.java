package xKing.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.history.dao.BranchHistoryRepository;
import xKing.history.domain.BranchHistory;
import xKing.history.service.HistoryService;
import xKing.project.domain.Task;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private BranchHistoryRepository branchHistoryRepository;

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


}
