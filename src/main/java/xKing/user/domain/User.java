package xKing.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@NotNull
	@Size(min=6, max=16, message="{password.size}")
	private String password;
	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$", message="{email.pattern}")
	private String email;
	private String name;
	private String blog;
	private String sex;
	private int state;
	private String keyCode;
	private Date keyDate;
	private String introduction;
	private int showEmail;
	private int enabled;
	private String role_user;
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
	public User() {
		
	}
	public User(long id, String username, String password) {
		this(id, username, password, null, null, null, 0, null, null);
	}
	
	public User(long id, String username, String password, String email, String blog, String sex, int state,
			String key, String introduction) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.blog = blog;
		this.sex = sex;
		this.state = state;
		this.keyCode = key;
		this.introduction = introduction;
	}
	
	public User(long id, String username, String password, String email, String name, String blog, String sex,
			int state, String key, Date keyDate, String introduction, int showEmail, int enabled, String role_user) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.blog = blog;
		this.sex = sex;
		this.state = state;
		this.keyCode = key;
		this.keyDate = keyDate;
		this.introduction = introduction;
		this.showEmail = showEmail;
		this.enabled = enabled;
		this.role_user = role_user;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
				+ name + ", blog=" + blog + ", sex=" + sex + ", state=" + state + ", key=" + keyCode + ", keyDate="
				+ keyDate + ", introduction=" + introduction + ", showEmail=" + showEmail + ", enabled=" + enabled
				+ ", role_user=" + role_user + "]";
	}
	
}
