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
 * 信息标签实体
 * 
 * @author zhonghao
 *
 */
@Entity
@Table(name="branch_message_tag")
public class MessageTag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String tagName;
	
	@ManyToOne(targetEntity=Branch.class)
	private Branch branch;
	
	@ManyToOne(targetEntity=BranchMember.class)
	private BranchMember branchMember;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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
	
	public void init(Branch branch, BranchMember member, String tagName) {
		this.branch = branch;
		this.branchMember = member;
		this.tagName = tagName;
	}
}
