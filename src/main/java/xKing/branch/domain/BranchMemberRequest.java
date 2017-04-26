package xKing.branch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.user.domain.User;

/**
 * branch member 请求表
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_member_request")
public class BranchMemberRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="branch_id", referencedColumnName="id", nullable=false)
	private Branch branch;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User user;
	
	private String message;
	
	// 组织邀请 state = 1
	// 用户请求 state = 2
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}	
	
}
