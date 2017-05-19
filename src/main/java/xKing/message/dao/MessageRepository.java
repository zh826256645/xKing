package xKing.message.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import xKing.message.domain.BranchMessage;
import xKing.utils.CrudRepository;

public interface MessageRepository extends CrudRepository<BranchMessage, Long> {

	Page<BranchMessage> findByBranch_idOrderByCreateTimeDesc(long branch_id, Pageable pageable);
	
	@Query(value="select bm from BranchMessage bm where bm.branch.id=?1 and bm.title like %?2% order by bm.createTime desc",
		   countQuery="select count(bm) from BranchMessage bm where bm.branch.id=?1 and bm.title like %?2%")
	Page<BranchMessage> findByBranch_idAndTitleLikeOrderByCreateTimeDesc(long branch_id, String title, Pageable pageable);
	
	Page<BranchMessage> findByBranch_idAndMessageTag_idOrderByCreateTimeDesc(long branch_id, long messageTag_id, Pageable pageable);
	
	BranchMessage findByBranch_idAndId(long branch_id, long id);
	
	Long countByBranch_id(long branch_id);
}
