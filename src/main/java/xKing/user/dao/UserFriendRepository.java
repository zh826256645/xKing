package xKing.user.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query(value="select uf from UserFriend uf where (uf.user.id=?1 or uf.friend.id=?2) and uf.state=?3 order by uf.createTime asc",
			countQuery="select count(uf) from UserFriend uf where (uf.user.id=?1 or uf.friend.id=?2) and uf.state=?3")
	Page<UserFriend> findByUser_idOrFriend_idAndStateOrderByCreateTime(long user_id, long friend_id, int state, Pageable pageable);
	
	@Query(countQuery="select count(uf) from UserFriend uf where (uf.user.id=?1 or uf.friend.id=?2) and uf.state=?3")
	Long countByUser_idOrFriend_idAndState(long user_id, long friend_id, int state);

	Page<UserFriend> findByFriend_idAndStateOrderByCreateTime(long friend_id, int state, Pageable pageable);
}
