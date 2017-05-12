package xKing.project.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xKing.branch.domain.BranchMember;
import xKing.utils.Utils;

@Entity
@Table(name="branch_project_task_problem")
public class Problem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	private long publishTIme;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private BranchMember member;
	
	@Enumerated(EnumType.STRING)
	private ProblemState problemState;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPublishTIme() {
		return publishTIme;
	}

	public void setPublishTIme(long publishTIme) {
		this.publishTIme = publishTIme;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public BranchMember getMember() {
		return member;
	}

	public void setMember(BranchMember member) {
		this.member = member;
	}

	public ProblemState getProblemState() {
		return problemState;
	}

	public void setProblemState(ProblemState problemState) {
		this.problemState = problemState;
	}
	
	public String getFormatTime() {
		return Utils.getFormatData(this.publishTIme);
	}
	
}
