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
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.message.domain.BranchMessage;
import xKing.message.domain.BranchMessageComment;
import xKing.message.domain.MessageTag;
import xKing.project.domain.Problem;
import xKing.project.domain.Project;
import xKing.project.domain.Task;

@Entity
@Table(name="branch_history")
public class BranchHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	// 所属组织
	@ManyToOne
	private Branch branch;
	
	// 动作发起的成员
	@ManyToOne
	private BranchMember initiateMember;
	
	// 所属类型
	@Enumerated(EnumType.STRING)
	private BranchHisotryType type;
	
	private String action;
	
	private String actionMessage;
	
	@ManyToOne
	private BranchMessage branchMessage;
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private BranchRole branchRole;
	
	@ManyToOne
	private Problem problem;

	@ManyToOne
	private MessageTag messageTag;
	
	@ManyToOne
	private BranchMessageComment branchMessageComment;

	@ManyToOne
	private BranchMember acceptedMember;
	
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

	public BranchMember getInitiateMember() {
		return initiateMember;
	}

	public void setInitiateMember(BranchMember initiateMember) {
		this.initiateMember = initiateMember;
	}

	public BranchHisotryType getType() {
		return type;
	}

	public void setType(BranchHisotryType type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public BranchMessage getBranchMessage() {
		return branchMessage;
	}

	public void setBranchMessage(BranchMessage branchMessage) {
		this.branchMessage = branchMessage;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public BranchRole getBranchRole() {
		return branchRole;
	}

	public void setBranchRole(BranchRole branchRole) {
		this.branchRole = branchRole;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public MessageTag getMessageTag() {
		return messageTag;
	}

	public void setMessageTag(MessageTag messageTag) {
		this.messageTag = messageTag;
	}

	public BranchMessageComment getBranchMessageComment() {
		return branchMessageComment;
	}

	public void setBranchMessageComment(BranchMessageComment branchMessageComment) {
		this.branchMessageComment = branchMessageComment;
	}

	public BranchMember getAcceptedMember() {
		return acceptedMember;
	}

	public void setAcceptedMember(BranchMember acceptedMember) {
		this.acceptedMember = acceptedMember;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public BranchHistory(Branch branch, BranchMember initiateMember, BranchHisotryType type, String action,
			String actionMessage, BranchMessage branchMessage, Project project, Task task, BranchRole branchRole,
			Problem problem, MessageTag messageTag, BranchMessageComment branchMessageComment,
			BranchMember acceptedMember) {
		super();
		this.branch = branch;
		this.initiateMember = initiateMember;
		this.type = type;
		this.action = action;
		this.actionMessage = actionMessage;
		this.branchMessage = branchMessage;
		this.project = project;
		this.task = task;
		this.branchRole = branchRole;
		this.problem = problem;
		this.messageTag = messageTag;
		this.branchMessageComment = branchMessageComment;
		this.acceptedMember = acceptedMember;
	}

	public BranchHistory() {
		super();
	}
	
	public BranchHistory(Branch branch, BranchMember initiateMember, BranchHisotryType type,Project project, Task task, String action) {
		super();
		this.branch = branch;
		this.initiateMember = initiateMember;
		this.project = project;
		this.action = action;
		this.task = task;
		this.type = type;
	}
	
	public BranchHistory(Branch branch, BranchMember initiateMember,BranchMember acceptedMember, BranchHisotryType type,Project project,  String action) {
		super();
		this.branch = branch;
		this.initiateMember = initiateMember;
		this.project = project;
		this.action = action;
		this.acceptedMember = acceptedMember;
		this.type = type;
	}
}
