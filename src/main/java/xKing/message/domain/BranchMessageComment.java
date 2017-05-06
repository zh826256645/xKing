package xKing.message.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.branch.domain.Branch;
import xKing.user.domain.User;
import xKing.utils.Utils;

@Entity
@Table(name="branch_message_comment")
public class BranchMessageComment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Branch branch;
	
	@ManyToOne
	private BranchMessage branchMessage;
	
	private String comment;
	
	private long createTime;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BranchMessage getBranchMessage() {
		return branchMessage;
	}

	public void setBranchMessage(BranchMessage branchMessage) {
		this.branchMessage = branchMessage;
	}

	public String getFormatTime() {
		return Utils.getFormatDataTime(this.createTime);
	}
	
	public void init(User currentUser, Branch currentBranch, BranchMessage currentBranchMessage, String comment) {
		this.user = currentUser;
		this.branch = currentBranch;
		this.comment = comment;
		this.branchMessage = currentBranchMessage;
		this.createTime = System.currentTimeMillis();
	}
}
