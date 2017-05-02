package xKing.message.dao;

import java.util.List;

import xKing.message.domain.MessageTag;
import xKing.utils.CrudRepository;

public interface MessageTagRepository extends CrudRepository<MessageTag, Long> {

	MessageTag findByTagNameAndBranch_id(String tagName, long branch_id);
	
	List<MessageTag> findByBranch_id(long branch_id);
}
