package xKing.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import xKing.user.domain.User;
import xKing.utils.CrudRepository;

/**
 * UserRepository
 * 操作 User 表
 * 
 * @author 钟浩
 * @date 2016年11月16日
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(final String username);

	User saveAndFlush(User user);
	
	User findByEmail(final String emali);
}
