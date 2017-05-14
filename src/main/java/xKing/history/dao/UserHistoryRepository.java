package xKing.history.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.history.domain.UserHistory;
import xKing.utils.CrudRepository;

public interface UserHistoryRepository extends CrudRepository<UserHistory, Long> {

	Page<UserHistory> findByUser_idOrderByExecutionTimeDesc(long user_id, Pageable pageable);
}
