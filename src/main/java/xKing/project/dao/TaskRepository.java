package xKing.project.dao;

import xKing.project.domain.Task;
import xKing.utils.CrudRepository;
/**
 * Task DAO 层
 * @author zhonghao
 *
 */

public interface TaskRepository extends CrudRepository<Task, Long> {

}
