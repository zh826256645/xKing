package xKing.user.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 信息实体
 * 
 * @author zhonghao
 *
 */

@Entity
@Table(name="user_friend_message")
public class FriendMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User acceptedUser;
	
	@Enumerated(EnumType.STRING)
	private FriendMessageState state;
	
	private String content;
	
	private long dateline;

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

	public User getAcceptedUser() {
		return acceptedUser;
	}

	public void setAcceptedUser(User acceptedUser) {
		this.acceptedUser = acceptedUser;
	}

	public FriendMessageState getState() {
		return state;
	}

	public void setState(FriendMessageState state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getDateline() {
		return dateline;
	}

	public void setDateline(long dateline) {
		this.dateline = dateline;
	}
	
	public void init(User currentUser, User sendedUser, String content) {
		this.user = currentUser;
		this.acceptedUser = sendedUser;
		this.content = content;
		this.dateline = System.currentTimeMillis();
		this.state = FriendMessageState.UNREAD;
	}
}
