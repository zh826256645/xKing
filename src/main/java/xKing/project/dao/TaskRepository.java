package xKing.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.BranchMember;
import xKing.project.domain.State;
import xKing.project.domain.Task;
import xKing.project.domain.TaskLevel;
import xKing.user.domain.User;
import xKing.utils.CrudRepository;
/**
 * Task DAO 层
 * @author zhonghao
 *
 */

public interface TaskRepository extends CrudRepository<Task, Long> {
	Page<Task> findByProject_id(long project_id, Pageable pageable);
	
	Page<Task> findByProject_idAndFtask(long project_id, Task Ftask, Pageable pageable);
	
	Page<Task> findByProject_idAndFtaskOrderByPublishTimeDesc(long project_id, Task Ftask, Pageable pageable);
	
	Task findByProject_idAndId(long project_id, long id);
	
	Task findByProject_idAndIdAndFtask(long project_id, long id, Task Ftask);
	
	Task findByTakeMembersAndId(BranchMember member, long id);
	
	Task findByIdAndFtask_id(long id, long ftask_id);
	
	Page<Task> findByUsersAndFtaskOrderByPublishTimeDesc(User user, Task ftask, Pageable pageable);
	
	Long countByUsersAndFtaskAndStateNot(User user, Task ftask, State state);
	
	Page<Task> findByProject_idAndStateAndFtaskOrderByPublishTimeDesc(long project_id, State state, Task ftask, Pageable pageable);
	
	Page<Task> findByUsersAndTaskLevelAndFtaskOrderByPublishTimeDesc(User user, TaskLevel taskLevel, Task ftask, Pageable pageable);
}
