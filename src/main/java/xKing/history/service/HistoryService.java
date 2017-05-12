package xKing.history.service;

import java.util.List;

import xKing.history.domain.BranchHistory;
import xKing.project.domain.Task;

public interface HistoryService {

	BranchHistory createBranchHisotry(BranchHistory history);
	
	List<BranchHistory> findBranchHistoryByTask(Task currentTask);
}
