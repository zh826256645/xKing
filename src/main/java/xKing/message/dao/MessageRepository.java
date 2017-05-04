package xKing.message.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.message.domain.BranchMessage;
import xKing.utils.CrudRepository;

public interface MessageRepository extends CrudRepository<BranchMessage, Long> {

	Page<BranchMessage> findByBranch_idOrderByCreateTime(long branch_id, Pageable pageable);
}
