package xKing.branch.dao;

import xKing.branch.domain.Branch;
import xKing.utils.CrudRepository;

/**
 * branch 表
 * 
 * @author 钟浩
 * @date 2016年11月27日
 *
 */
public interface BranchRepository extends CrudRepository<Branch, Long> {
	Branch findByBranchName(String branchName);
}
