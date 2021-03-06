package xKing.user.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import xKing.branch.domain.BranchMemberRequest;

/**
 * User 领域模型
 * 
 * @author 钟浩
 * @date 2016年10月24日
 *
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min=6, max=16, message="{username.size}")
	private String username;
	
	@JsonIgnore(value=true)
	@NotNull
	@Size(min=6, max=32, message="{password.size}")
	private String password;
	
	@JsonIgnore(value=true)
	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$", message="{email.pattern}")
	private String email;
	
	@JsonIgnore(value=true)
	@OneToMany(mappedBy = "user")
	private List<BranchMemberRequest> branchMemberRequests;
    
	private String name;
	private String blog;
	private String picture;
	private String sex;
	@JsonIgnore(value=true)
	private int state;
	@JsonIgnore(value=true)
	private String keyCode;
	@JsonIgnore(value=true)
	private Date keyDate;
	
	private String introduction;
	@JsonIgnore(value=true)
	private int showEmail;
	@JsonIgnore(value=true)
	private int enabled = 1;
	@JsonIgnore(value=true)
	private String role_user;
	
	@Transient
	private Long notReadMessageCount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getKeyDate() {
		return keyDate;
	}
	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getShowEmail() {
		return showEmail;
	}
	public void setShowEmail(int showEmail) {
		this.showEmail = showEmail;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRole_user() {
		return role_user;
	}
	public void setRole_user(String role_user) {
		this.role_user = role_user;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public User() {
		
	}
	public User(String username, String password) {
		this(username, password, null);
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public List<BranchMemberRequest> getBranchMemberRequests() {
		return branchMemberRequests;
	}
	public void setBranchMemberRequests(List<BranchMemberRequest> branchMemberRequests) {
		this.branchMemberRequests = branchMemberRequests;
	}
	public Long getNotReadMessageCount() {
		return notReadMessageCount;
	}
	public void setNotReadMessageCount(Long notReadMessageCount) {
		this.notReadMessageCount = notReadMessageCount;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
				+ name + ", blog=" + blog + ", picture=" + picture + ", sex=" + sex + ", state=" + state + ", keyCode="
				+ keyCode + ", keyDate=" + keyDate + ", introduction=" + introduction + ", showEmail=" + showEmail
				+ ", enabled=" + enabled + ", role_user=" + role_user + "]";
	}
}
