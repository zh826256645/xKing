package xKing.branch.dao;

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

}
