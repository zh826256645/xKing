package xKing.branch.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * BranchRole 领域对象
 * 
 * @author 钟浩
 * @date 2016年11月27日
 *
 */
@Entity
@Table(name="branch_role")
public class BranchRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String roleName;
	@NotNull
	private int roleLevel;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branch_id", referencedColumnName="id", nullable=false)
	private Branch branch;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public BranchRole() {
		super();
	}
	
	public BranchRole(String roleName, int roleLevel, Branch branch) {
		this.roleName = roleName;
		this.roleLevel = roleLevel;
		this.branch = branch;
	}
	
	@Override
	public String toString() {
		return "BranchRole [id=" + id + ", roleName=" + roleName + ", roleLevel=" + roleLevel + ", branch=" + branch
				+ "]";
	}
}
