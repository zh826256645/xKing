package xKing.history.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.history.domain.BranchHisotryType;
import xKing.history.domain.BranchHistory;
import xKing.project.domain.Project;
import xKing.project.domain.Task;

public interface HistoryService {

	BranchHistory createBranchHisotry(BranchHistory history);
	
	List<BranchHistory> findBranchHistoryByTask(Task currentTask);
	
	List<BranchHistory> findBranchHistoryByTaskAndProject(Project currentProject, Task currentTask);
	
	List<BranchHistory> findBranchHistoryByProjectAndType(Project currentProject, BranchHisotryType type);
	
	List<BranchHistory> findbyProjectAndTwoType(Project currentProject, BranchHisotryType oneType,BranchHisotryType twoType );
	
	Page<BranchHistory> findbyBracnhAndTwoType(Branch currentBranch, BranchHisotryType oneType,BranchHisotryType twoType, Pageable pageable);
}
