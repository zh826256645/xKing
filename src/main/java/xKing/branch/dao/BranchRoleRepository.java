package xKing.branch.dao;

import java.util.List;

import xKing.branch.domain.BranchRole;
import xKing.utils.CrudRepository;

/**
 * BranchRole 表
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
public interface BranchRoleRepository extends CrudRepository<BranchRole, Long> {

	BranchRole findByRoleNameAndBranch_id (String roleName, long branch_id);
	List<BranchRole> findByBranchIdOrderByRoleLevelAsc(long branch_id);
}
