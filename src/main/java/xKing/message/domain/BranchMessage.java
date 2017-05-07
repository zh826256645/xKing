package xKing.message.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.utils.Utils;

/**
 * 组织信息实体类
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_message")
public class BranchMessage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="标题不能为空")
	@Size(min=1, message="标题不能为空")
	private String title;
	
	@NotNull(message="内容不能为空")
	@Size(min=1, message="内容不能为空")
	private String messageContent;
	
	@ManyToOne(targetEntity=Branch.class)
	private Branch branch;
	
	@ManyToOne(targetEntity=BranchMember.class)
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
	
	public void init(Branch currentBranch, BranchMember currentMember, MessageTag messageTag, String title, String messageContent) {
		this.branch = currentBranch;
		this.branchMember = currentMember;
		this.messageTag = messageTag;
		this.title = title;
		this.messageContent = messageContent;
		this.createTime = System.currentTimeMillis();
	}
	
	public void change(BranchMessage newBranchMessage, MessageTag messageTag) {
		this.messageContent = newBranchMessage.getMessageContent();
		this.title = newBranchMessage.getTitle();
		if(this.messageTag == null && messageTag != null) {
			this.messageTag = messageTag;
		} else if(this.messageTag != null && messageTag != null && !this.messageTag.getTagName().equals(messageTag.getTagName())) {
			this.messageTag = messageTag;
		}
	}
	
	public String getFormatTime() {
		return Utils.getFormatData(this.createTime);
	}
	
	public String getFormatTime2() {
		return Utils.getFormatDataTime(this.createTime);
	}
}
