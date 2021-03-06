package xKing.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import xKing.user.domain.FriendMessage;
import xKing.user.domain.FriendMessageState;
import xKing.utils.CrudRepository;

public interface FriendMessageRepository extends CrudRepository<FriendMessage, Long>{

	@Query(value="select fm from FriendMessage fm where (fm.user.id=?1 and fm.acceptedUser.id=?2) or (fm.user.id=?2 and fm.acceptedUser.id=?1) order by fm.dateline desc",
			countQuery="select fm from FriendMessage fm where (fm.user.id=?1 and fm.acceptedUser.id=?2) or (fm.user.id=?2 and fm.acceptedUser.id=?1)")
	List<FriendMessage> findByUser_idAndAcceptedUser_id(long user_id, long acceptedUser_id);
		
	Long countByAcceptedUser_idAndState(long acceptedUser_id, FriendMessageState state);
	
	Long countByAcceptedUser_idAndUser_idAndState(long acceptedUser_id, long user_id, FriendMessageState state);
	
	List<FriendMessage> findByAcceptedUser_idAndUser_idAndState(long acceptedUser_id, long user_id, FriendMessageState state);
}
