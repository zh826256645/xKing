package xKing.branch.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.user.domain.User;

@Entity
@Table(name="branch_member")
public class BranchMember {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String memberName;
	private String email;
	private Date joinTime;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="branch_id", referencedColumnName="id", nullable=false)
	private Branch branch;
	@ManyToOne
	@JoinColumn(name="branch_role_id", referencedColumnName="id", nullable=false)
	private BranchRole branchRole;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public BranchRole getBranchRole() {
		return branchRole;
	}
	public void setBranchRole(BranchRole branchRole) {
		this.branchRole = branchRole;
	}
	
	public BranchMember() {
		super();
	}
	
	public BranchMember(String memberName, String email, Date joinTime, Branch branch, BranchRole branchRole, User user) {
		this.memberName = memberName;
		this.email = email;
		this.joinTime = joinTime;
		this.branch = branch;
		this.branchRole = branchRole;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "BranchMember [id=" + id + ", memberName=" + memberName + ", email=" + email + ", user=" + user
				+ ", branch=" + branch + ", branchRole=" + branchRole + "]";
	}
}
