package xKing.user.dao;

import org.springframework.data.repository.CrudRepository;

import xKing.user.domain.User;

/**
 * UserRepository
 * 操作 User 表
 * 
 * @author 钟浩
 * @date 2016年11月16日
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	User saveAndFlush(User user);
}
