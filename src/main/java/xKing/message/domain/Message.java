package xKing.message.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;

/**
 * 组织信息实体类
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_message")
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String messageContent;
	
	private String tag;
	
	@ManyToOne(targetEntity=Branch.class)
	private Branch branch;
	
	@ManyToOne(targetEntity=Branch.class)
	private BranchMember branchMember;
	
	@ManyToOne(targetEntity=MessageTag.class)
	private MessageTag messageTag;
	
	private long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BranchMember getBranchMember() {
		return branchMember;
	}

	public void setBranchMember(BranchMember branchMember) {
		this.branchMember = branchMember;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public MessageTag getMessageTag() {
		return messageTag;
	}

	public void setMessageTag(MessageTag messageTag) {
		this.messageTag = messageTag;
	}
}
