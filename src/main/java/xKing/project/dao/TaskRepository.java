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
}
