package xKing.user.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户关系实体
 * 
 * @author zhonghao
 *
 */
@Entity
@Table(name="user_friend")
public class UserFriend {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="firend_id")
	private User friend;
	
	// 添加时间
	private long createTime;
	
	// 状态
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public void initUserFriend(User user, User friend){
		this.user = user;
		this.friend = friend;
		this.createTime =  System.currentTimeMillis();
		this.state = 0;
	}
	
	
	public UserFriend() {
		super();
	}

	
	public UserFriend(User user, User friend) {
		this.initUserFriend(user, friend);
	}

	@Override
	public String toString() {
		return "UserFriend [id=" + id + ", user=" + user + ", friend=" + friend + ", createTime=" + createTime
				+ ", state=" + state + "]";
	}
}
