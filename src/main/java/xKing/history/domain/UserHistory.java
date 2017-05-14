package xKing.history.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.branch.domain.Branch;
import xKing.user.domain.User;
import xKing.utils.Utils;

/**
 * 用户历史记录实体
 * 
 * @author zhonghao
 *
 */
@Entity
@Table(name="user_history")
public class UserHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Branch branch;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User acceptedUser;
	
	@Enumerated(EnumType.STRING)
	private UserHistoryType type;
	
	private String action;
	
	private String content;
	
	private long executionTime;

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

	public User getAcceptedUser() {
		return acceptedUser;
	}

	public void setAcceptedUser(User acceptedUser) {
		this.acceptedUser = acceptedUser;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public UserHistoryType getType() {
		return type;
	}

	public void setType(UserHistoryType type) {
		this.type = type;
	}

	public UserHistory(Branch branch, User user, User acceptedUser, UserHistoryType type, String action,
			String content) {
		super();
		this.branch = branch;
		this.user = user;
		this.acceptedUser = acceptedUser;
		this.type = type;
		this.action = action;
		this.content = content;
		this.executionTime = System.currentTimeMillis();
	}

	public UserHistory() {
		super();
	}
	
	public String getFormatTime() {
		return Utils.getFormatData(this.executionTime);
	}

	
}
