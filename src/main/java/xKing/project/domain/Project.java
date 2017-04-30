package xKing.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.utils.Utils;

/**
 *  项目实体
 * @author zhonghao
 *
 */

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	private String projectName;
	
	private String intro;
	
	private long createTime;
	
	@Transient
	private String formatTime;
	
	// 所属组织
	@ManyToOne(targetEntity=Branch.class)
	private Branch branch;
	
	// 创建人
	@ManyToOne(targetEntity=BranchMember.class)
	private BranchMember branchMember;
	
	// 项目成员
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="project_member", joinColumns=@JoinColumn(name="project_id"),
	inverseJoinColumns=@JoinColumn(name="branch_member_id"))
	private List<BranchMember> projectMember = new ArrayList<BranchMember>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public BranchMember getBranchMember() {
		return branchMember;
	}

	public void setBranchMember(BranchMember branchMember) {
		this.branchMember = branchMember;
	}

	public List<BranchMember> getProjectMember() {
		return projectMember;
	}

	public void setProjectMember(List<BranchMember> projectMember) {
		this.projectMember = projectMember;
	}
	
	public void init(Branch branch, BranchMember createMember, String projectName){
		this.projectName = projectName;
		this.branch = branch;
		this.branchMember = createMember;
		this.projectMember.add(createMember);
		this.createTime = System.currentTimeMillis();
	}

	public String getFormatTime() {
		return Utils.getFormatData(this.createTime);
	}

}
