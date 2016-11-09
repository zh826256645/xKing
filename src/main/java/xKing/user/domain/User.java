package xKing.user.domain;

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
public class User {
	private Integer id;
	@NotNull
	@Size(min=6, max=16, message="{username.size}")
	private String username;
	@NotNull
	@Size(min=6, max=16, message="{password.size}")
	private String password;
	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$", message="{email.pattern}")
	private String email;
	private String blog;
	private String sex;
	private int state;
	private String key;
	private String introduction;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public User() {
		
	}
	
	public User(Integer id, String username, String password) {
		this(id, username, password, null, null, null, 0, null, null);
	}
	
	public User(Integer id, String username, String password, String email, String blog, String sex, int state,
			String key, String introduction) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.blog = blog;
		this.sex = sex;
		this.state = state;
		this.key = key;
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", blog="
				+ blog + ", sex=" + sex + ", state=" + state + ", key=" + key + ", introduction=" + introduction + "]";
	}
	
	
}
