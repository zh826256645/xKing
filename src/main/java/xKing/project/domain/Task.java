package xKing.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import xKing.branch.domain.BranchMember;
import xKing.user.domain.User;
import xKing.utils.Utils;

/**
 * 项目任务实体类
 * @author zhonghao
 *
 */

@Entity
@Table(name="branch_project_task")
public class Task {
	enum Type {Task, Test, Debugging, Features, Bug, BiSai}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="任务标题不能为空")
	@Size(min=1, message="任务标题不能为空")
	private String title;
	
	@NotNull(message="任务内容不能为空")
	@Size(min=1, message="任务内容不能为空")
	private String content;
	
	private long startTime;
	
	private long endTime;
	
	@Enumerated(EnumType.STRING)
	private State state;

	@NotNull(message="任务类型不能为空")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@NotNull(message="任务等级不能为空")
	@Enumerated(EnumType.STRING)
	private TaskLevel taskLevel;
	
	@ManyToOne
	private Project project;
	
	// 发布成员
	@ManyToOne
	private BranchMember publishMember;
	
	// 指定任务接受成员
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="project_take_members", joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="branch_member_id"))
	private List<BranchMember> takeMembers = new ArrayList<BranchMember>();
	
	// 指定任务接受成员
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="project_take_users", joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> users = new ArrayList<User>();
	
	// 发布时间
	private long publishTime;
	
	// 子任务
	@OneToMany(mappedBy="ftask", fetch=FetchType.EAGER)
	private List<Task> subtasks = new ArrayList<Task>();
	
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

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
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

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", content=" + content + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", state=" + state + ", type=" + type + ", taskLevel=" + taskLevel
				+ ", project=" + project + ", publishMember=" + publishMember + ", takeMembers=" + takeMembers
				+ ", publishTime=" + publishTime + ", subtasks=" + subtasks + ", ftask=" + ftask + "]";
	}
	
	public String getFormatPublishTime() {
		return Utils.getFormatData(this.publishTime);
	}
	
	public String getFormatStartTime() {
		return Utils.getFormatData(this.startTime);
	}
	
	public String getFormatEndTime() {
		return Utils.getFormatData(this.endTime);
	}

	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getLastDay(){
		long today = Utils.getTodayTimeLong();
		long lastTime = this.endTime - today;
		if(lastTime < 0) {
			return 0;
		} else if(lastTime == 0) {
			return 1;
		} else {
			return (int) (lastTime / 24 / 60 / 60/1000);
		}
	}
	
	public int getStartState() {
		long today = Utils.getTodayTimeLong();
		long startTime = today - this.startTime;
		if(startTime < 0) {
			return 0;
		}else {
			return 1;
		}
	}
}
