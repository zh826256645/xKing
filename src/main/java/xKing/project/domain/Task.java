package xKing.project.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xKing.branch.domain.BranchMember;

/**
 * 项目任务实体类
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_project_task")
public class Task {

	enum State {New, Take, Doding, FrontendFinish, RearendFinish, Finish, Refuse}
	
	enum Type {Task, Test, Debugging, Features, Bug}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String content;
	
	private long startTime;
	
	private long endTime;
	
	@Enumerated(EnumType.STRING)
	private State state;

	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
	private TaskLevel taskLevel;
	
	@ManyToOne
	private Project project;
	
	// 发布成员
	@ManyToOne
	private BranchMember publishMember;
	
	// 指定任务接受成员
	@ManyToMany
	@JoinTable(name="project_take_members", joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="branch_member_id"))
	private List<BranchMember> takeMembers;
	
	// 发布时间
	private int publishTime;
	
	// 子任务
	@OneToMany(mappedBy="ftask")
	private List<Task> subtasks;
	
	@ManyToOne
	private Task ftask;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BranchMember getPublishMember() {
		return publishMember;
	}

	public void setPublishMember(BranchMember publishMember) {
		this.publishMember = publishMember;
	}

	public List<BranchMember> getTakeMembers() {
		return takeMembers;
	}

	public void setTakeMembers(List<BranchMember> takeMembers) {
		this.takeMembers = takeMembers;
	}

	public int getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(int publishTime) {
		this.publishTime = publishTime;
	}

	public List<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<Task> subtasks) {
		this.subtasks = subtasks;
	}

	public Task getFtask() {
		return ftask;
	}

	public void setFtask(Task ftask) {
		this.ftask = ftask;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}

	public State getState() {
		return state;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
