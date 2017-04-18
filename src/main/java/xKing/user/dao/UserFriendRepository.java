package xKing.user.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import xKing.user.domain.UserFriend;
import xKing.utils.CrudRepository;
/**
 * 好友表
 * @author zhonghao
 *
 */

@Repository
public interface UserFriendRepository extends CrudRepository<UserFriend, Long> {
	
	UserFriend findOneByUser_idAndFriend_id(long user_id, long friend_id);
	
	UserFriend findOneByUser_idAndFriend_idAndState(long user_id, long friend_id, int state);
	
	Page<UserFriend> findByUser_idOrFriend_idAndStateOrderByCreateTime(long user_id, long friend_id, int state, Pageable pageable); 
}
