package xKing.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.project.domain.Task;
import xKing.utils.CrudRepository;
/**
 * Task DAO 层
 * @author zhonghao
 *
 */

public interface TaskRepository extends CrudRepository<Task, Long> {
	Page<Task> findByProject_id(long project_id, Pageable pageable);
	
	Page<Task> findByProject_idAndFtask(long project_id, Task Ftask, Pageable pageable);
	
	Task findByProject_idAndId(long project_id, long id);
	
	Task findByProject_idAndIdAndFtask(long project_id, long id, Task Ftask);
}
