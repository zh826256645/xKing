package xKing.branch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 组织权限配置 实体类
 * 
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_authority")
public class BranchAuthority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToOne()
	private Branch branch;

	// 允许进入组织
	@ManyToOne
	private BranchRole allowInto;
	
	// 允许修改组织信息
	@ManyToOne
	private BranchRole allowChangeInformation;
	
	
	// 允许查看信息
	@ManyToOne
	private BranchRole allowSeeMessage;

	// 允许创建信息
	@ManyToOne
	private BranchRole allowCreateMessage;
	
	// 允许修改信息
	@ManyToOne
	private BranchRole allowChangeMessage;
	
	// 允许删除信息
	@ManyToOne
	private BranchRole allowDeleteMessage;
	
	
	// 允许查看成员
	@ManyToOne
	private BranchRole allowSeeMember;
	
	// 允许添加成员
	@ManyToOne
	private BranchRole allowAddMember;
	
	// 允许删除成员
	@ManyToOne
	private BranchRole allowDeleteMember;
	
	// 允许修改成员信息
	@ManyToOne
	private BranchRole allowChangeMember;
	
	
	// 允许发布任务
	@ManyToOne
	private BranchRole allowCreateTask;
	
	// 允许修改任务
	@ManyToOne
	private BranchRole allowChangeTask;
	
	// 允许接受任务
	@ManyToOne
	private BranchRole allowTakeTask;
	
	// 允许删除任务
	@ManyToOne
	private BranchRole allowDeleteTask;
	
	
	// 允许发表评论
	@ManyToOne
	private BranchRole allowPublishComment;
	
	// 允许删除评论
	@ManyToOne
	private BranchRole allowDeleteComment;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BranchRole getAllowInto() {
		return allowInto;
	}

	public void setAllowInto(BranchRole allowInto) {
		this.allowInto = allowInto;
	}

	public BranchRole getAllowChangeInformation() {
		return allowChangeInformation;
	}

	public void setAllowChangeInformation(BranchRole allowChangeInformation) {
		this.allowChangeInformation = allowChangeInformation;
	}

	public BranchRole getAllowSeeMessage() {
		return allowSeeMessage;
	}

	public void setAllowSeeMessage(BranchRole allowSeeMessage) {
		this.allowSeeMessage = allowSeeMessage;
	}

	public BranchRole getAllowCreateMessage() {
		return allowCreateMessage;
	}

	public void setAllowCreateMessage(BranchRole allowCreateMessage) {
		this.allowCreateMessage = allowCreateMessage;
	}

	public BranchRole getAllowChangeMessage() {
		return allowChangeMessage;
	}

	public void setAllowChangeMessage(BranchRole allowChangeMessage) {
		this.allowChangeMessage = allowChangeMessage;
	}

	public BranchRole getAllowDeleteMessage() {
		return allowDeleteMessage;
	}

	public void setAllowDeleteMessage(BranchRole allowDeleteMessage) {
		this.allowDeleteMessage = allowDeleteMessage;
	}

	public BranchRole getAllowSeeMember() {
		return allowSeeMember;
	}

	public void setAllowSeeMember(BranchRole allowSeeMember) {
		this.allowSeeMember = allowSeeMember;
	}

	public BranchRole getAllowAddMember() {
		return allowAddMember;
	}

	public void setAllowAddMember(BranchRole allowAddMember) {
		this.allowAddMember = allowAddMember;
	}

	public BranchRole getAllowDeleteMember() {
		return allowDeleteMember;
	}

	public void setAllowDeleteMember(BranchRole allowDeleteMember) {
		this.allowDeleteMember = allowDeleteMember;
	}

	public BranchRole getAllowChangeMember() {
		return allowChangeMember;
	}

	public void setAllowChangeMember(BranchRole allowChangeMember) {
		this.allowChangeMember = allowChangeMember;
	}

	public BranchRole getAllowCreateTask() {
		return allowCreateTask;
	}

	public void setAllowCreateTask(BranchRole allowCreateTask) {
		this.allowCreateTask = allowCreateTask;
	}

	public BranchRole getAllowChangeTask() {
		return allowChangeTask;
	}

	public void setAllowChangeTask(BranchRole allowChangeTask) {
		this.allowChangeTask = allowChangeTask;
	}

	public BranchRole getAllowTakeTask() {
		return allowTakeTask;
	}

	public void setAllowTakeTask(BranchRole allowTakeTask) {
		this.allowTakeTask = allowTakeTask;
	}

	public BranchRole getAllowDeleteTask() {
		return allowDeleteTask;
	}

	public void setAllowDeleteTask(BranchRole allowDeleteTask) {
		this.allowDeleteTask = allowDeleteTask;
	}

	public BranchRole getAllowPublishComment() {
		return allowPublishComment;
	}

	public void setAllowPublishComment(BranchRole allowPublishComment) {
		this.allowPublishComment = allowPublishComment;
	}

	public BranchRole getAllowDeleteComment() {
		return allowDeleteComment;
	}

	public void setAllowDeleteComment(BranchRole allowDeleteComment) {
		this.allowDeleteComment = allowDeleteComment;
	}
	
	// 初始化权限
	public void init(Branch branch, BranchRole role){
		this.branch = branch;
		this.setAllowChangeInformation(role);
	}
}
