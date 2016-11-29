package xKing.branch.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import xKing.user.domain.User;

/**
 * Branch 领域对象
 * 
 * @author 钟浩
 * @date 2016年11月27日
 *
 */
@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String branchName;
	private String email;
	private String intro;
	private String picture;
	private Date createTime;
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User user;
	@OneToMany(targetEntity=BranchRole.class, mappedBy="branch", cascade={CascadeType.ALL})
	private List<BranchRole> branchRoles = new ArrayList<BranchRole>();
	@OneToMany(targetEntity=BranchMember.class, mappedBy="branch", cascade={CascadeType.ALL})
	private List<BranchMember> branchMembers = new ArrayList<BranchMember>();
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<BranchRole> getBranchRoles() {
		return branchRoles;
	}

	public void setBranchRoles(List<BranchRole> branchRoles) {
		this.branchRoles = branchRoles;
	}

	public List<BranchMember> getBranchMembers() {
		return branchMembers;
	}

	public void setBranchMembers(List<BranchMember> branchMembers) {
		this.branchMembers = branchMembers;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", branchName=" + branchName + ", email=" + email + ", intro=" + intro
				+ ", picture=" + picture + ", user=" + user + ", branchRoles=" + branchRoles.size() + ", branchMembers="
				+ branchMembers.size() + "]";
	}

}