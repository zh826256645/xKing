package xKing.branch.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import xKing.project.domain.Project;
import xKing.user.domain.User;
import xKing.utils.Utils;

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
	private long createTime;
	private String country;
	private String homePage;
	private String type;
	@OneToOne
	private BranchRole newMemberRole ;
	@Transient
	private long memberNum;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User user;
	
	@OneToMany(targetEntity=BranchRole.class, mappedBy="branch", cascade={CascadeType.ALL})
	private List<BranchRole> branchRoles = new ArrayList<BranchRole>();
	
	@OneToMany(targetEntity=BranchMember.class, mappedBy="branch", cascade={CascadeType.ALL})
	private List<BranchMember> branchMembers = new ArrayList<BranchMember>();
	
	@OneToOne(mappedBy="branch")
	private BranchAuthority branchAuthority;
	
	@OneToMany(targetEntity=BranchMemberRequest.class, mappedBy="branch", cascade={CascadeType.ALL})
	private List<BranchMemberRequest> branchMemberRequests = new ArrayList<BranchMemberRequest>();
	
	@OneToMany(targetEntity=Project.class, mappedBy="branch")
	private List<Project> projects = new ArrayList<Project>();
	
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
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

	public BranchAuthority getBranchAuthority() {
		return branchAuthority;
	}

	public void setBranchAuthority(BranchAuthority branchAuthority) {
		this.branchAuthority = branchAuthority;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BranchMemberRequest> getBranchMemberRequests() {
		return branchMemberRequests;
	}

	public void setBranchMemberRequests(List<BranchMemberRequest> branchMemberRequests) {
		this.branchMemberRequests = branchMemberRequests;
	}

	public BranchRole getNewMemberRole() {
		return newMemberRole;
	}

	public void setNewMemberRole(BranchRole newMemberRole) {
		this.newMemberRole = newMemberRole;
	}

	public long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(long memberNum) {
		this.memberNum = memberNum;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", branchName=" + branchName + ", email=" + email + ", intro=" + intro
				+ ", picture=" + picture + ", user=" + user + ", branchRoles=" + branchRoles.size() + ", branchMembers="
				+ branchMembers.size() + "]";
	}
	
	public String getFormatTime() {
		return Utils.getFormatData(this.createTime);
	}
}
