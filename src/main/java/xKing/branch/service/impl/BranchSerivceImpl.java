package xKing.branch.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import xKing.branch.dao.BranchAuthorityRepository;
import xKing.branch.dao.BranchRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchAuthority;
import xKing.branch.domain.BranchAuthorityName;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchAuthorityService;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchRoleSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.AbsentException;
import xKing.exception.FaultyOperationException;
import xKing.exception.PermissionDeniedException;
import xKing.exception.SameNameException;
import xKing.picture.service.PictureService;
import xKing.user.domain.User;
import xKing.user.service.UserService;

import xKing.utils.Utils;

/**
 * Branch Service
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
@Service
@Transactional
public class BranchSerivceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private BranchAuthorityRepository branchAuthorityRepository;
	
	@Autowired
	private BranchRoleSerivce branchRoleSerivce;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private BranchAuthorityService branchAuthorityService;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private UserService userService;

	
	// 通过 BranId 查找 Branch
	@Override
	public Branch findBranchByBranchId(long branchId) {
		Branch branch = branchRepository.findOne(branchId);
		if(branch == null) {
			throw new AbsentException("Branch 不存在");
		}
		return branch;
	}
	
	// 通过 BranchName 查找 Branch
	@Override
	public Branch findBranchByBranchName(String branchName) {
		Branch branch = branchRepository.findByBranchName(branchName);
		if(branch == null) {
			throw new AbsentException("Branch 不存在");
		}
		return branch;
	}
	
	// 创建 Branch
	@Override
	public Branch createBranch(Branch branch, String yourRoleName, String newComerRoleName, String username) {
		if(branchRepository.findByBranchName(branch.getBranchName()) != null) {
			throw new SameNameException("该名字已被创建！");
		}
		User currentUser = userService.getUserByUsername(username);
		branch.setUser(currentUser);
		branch.setCreateTime(new Timestamp(new Date().getTime()));
		Branch currentBranch = branchRepository.save(branch);
		// 创建 branch admin 身份,level 为 1
		BranchRole adminBranchRole = branchRoleSerivce.addBranchRole(currentBranch, yourRoleName, 1);
		// 创建 branch newcomer 身份,level 为 99
		branchRoleSerivce.addBranchRole(currentBranch, newComerRoleName, 99);
		// 创建 branch authority
		branchAuthorityService.initBranchAuthority(currentBranch, adminBranchRole);
		// 创建 BranchMember
		branchMemberService.addBranchMember(
				currentUser.getUsername(), currentUser.getEmail(),
				currentBranch, adminBranchRole, currentUser);
		
		return this.findBranchByBranchName(branch.getBranchName());
	}
	
	// 创建 Branch 有图片
	@Override
	public Branch createBranch(InputStream in, Branch branch, String yourRoleName, String newComerRoleName,
			String username) {
		branch.setPicture(branch.getBranchName() + Utils.getExtensionName(branch.getPicture()));
		pictureService.savePicuture(in, branch.getPicture());
		return createBranch(branch, yourRoleName, newComerRoleName, username);
	}

	// 获取 Branch 图片
	@Override
	public InputStream getBranchPicture(String picutre) {	
		return pictureService.getPicture(picutre);
	}

	// 获取 Branch 用户加入的组织
	@Override
	public List<Branch> getBranchByUserId(User user, Pageable pageable) {
		Page<BranchMember> page = branchMemberService.findByUserIdOrderByJoinTimeDesc(user, pageable);
		List<Branch> branches = new ArrayList<Branch>();
		List<BranchMember> BranchMembers = page.getContent();
		for (BranchMember branchMember : BranchMembers) {
			branches.add(branchMember.getBranch());
		} 
		return branches;
	}

	// 校验用户的权限
	@Override
	public boolean checkUserAuthority(BranchMember member, Branch branch, BranchRole branchRole) {
		if(branchRole == null) {
			return true;
		}
		
		if(member == null) {
			throw new PermissionDeniedException("请先加入 " + branch.getBranchName() + " 组织！");
		}
		
		if(member.getBranchRole().getRoleLevel() > branchRole.getRoleLevel()) {
			throw new PermissionDeniedException("对不起权限对" + branch.getBranchName() + "进行这个操作！");
		}
		
		return true;
	}

	// 更改组织信息
	@Override
	public Branch changeBranchInformation(Branch branch, MultipartFile branchPicture, Branch currentBranch) throws IOException {
		
		// 判断是否更改了 branchName
		String newBranchName = branch.getBranchName();
		if(!newBranchName.trim().isEmpty() && !newBranchName.equals(currentBranch.getBranchName())){
			if(branchRepository.findByBranchName(newBranchName) != null) {
				throw new SameNameException("该名字已被创建！");
			} else {
				currentBranch.setBranchName(newBranchName);
			}
		}
		
		// 判断是否有图片
		if(branchPicture != null && !branchPicture.getOriginalFilename().trim().isEmpty()) {
			branch.setPicture(branch.getBranchName() + Utils.getExtensionName(branchPicture.getOriginalFilename()));
			pictureService.savePicuture(branchPicture.getInputStream(), branch.getPicture());
		}
		
		currentBranch.setHomePage(branch.getHomePage());
		currentBranch.setIntro(branch.getIntro());
		currentBranch.setType(branch.getType());
		
		return branchRepository.save(currentBranch);
	}

	// 更改组织权限
	@Override
	public String changeBranchAuthority(Branch currentBranch, BranchMember currentMember, String roleName, final String authorityName) {
		BranchAuthority currentBranchAuthority = currentBranch.getBranchAuthority();
		BranchRole becomeRole = null;
		if(roleName != null && !roleName.trim().isEmpty()) {
			becomeRole = branchRoleSerivce.findByRoleNameAndBranchId(roleName, currentBranch);
		}
		if(authorityName == null || (roleName != null && !roleName.trim().isEmpty() && becomeRole == null)) {
			throw new FaultyOperationException("错误操作！");
		}
		this.checkBecomeRole(currentBranch, currentMember, becomeRole);

		switch(authorityName) 
		{
		// 访问权限
		case "allowInto" :
			checkBanchRole(becomeRole, BranchAuthorityName.INTO,
					       currentBranchAuthority.getAllowSeeMessage(), BranchAuthorityName.SEEMESSAGE,
					       null, null);
			checkBanchRole(becomeRole, BranchAuthorityName.INTO,
				       currentBranchAuthority.getAllowTakeTask(), BranchAuthorityName.TAKETASK,
				       null, null);
			checkBanchRole(becomeRole, BranchAuthorityName.INTO,
				       currentBranchAuthority.getAllowPublishComment(), BranchAuthorityName.PUBLISHCOMMENT,
				       null, null);
			checkBanchRole(becomeRole, BranchAuthorityName.INTO,
				       currentBranchAuthority.getAllowSeeMember(), BranchAuthorityName.SEEMEMBER,
				       null, null);
			currentBranchAuthority.setAllowInto(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.INTO;
		
		// 设置更改权限
		case "allowChangeInformation":
			if(becomeRole == null) {
				throw new FaultyOperationException("该权限不能为空");
			}
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEINFORMATION,
					   null, null,
					   currentBranchAuthority.getAllowDeleteMessage(), BranchAuthorityName.DELETEMESSAGE);
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEINFORMATION,
					   null, null,
					   currentBranchAuthority.getAllowDeleteTask(), BranchAuthorityName.DELETETASK);
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEINFORMATION,
						   null, null,
						   currentBranchAuthority.getAllowDeleteComment(), BranchAuthorityName.DELETECOMMENT);
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEINFORMATION,
					   null, null,
					   currentBranchAuthority.getAllowDeleteMember(), BranchAuthorityName.DELETEMEMBER);
			currentBranchAuthority.setAllowChangeInformation(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CHANGEINFORMATION;
		
		// 信息查看权限
		case "allowSeeMessage":
			checkBanchRole(becomeRole, BranchAuthorityName.SEEMESSAGE,
						   currentBranchAuthority.getAllowCreateMessage(), BranchAuthorityName.CREATEMESSAGE,
					       currentBranchAuthority.getAllowInto(), BranchAuthorityName.INTO);
			
			currentBranchAuthority.setAllowSeeMessage(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.SEEMESSAGE;
		
		// 信息创建权限
		case "allowCreateMessage":
			checkBanchRole(becomeRole, BranchAuthorityName.CREATEMESSAGE,
						   currentBranchAuthority.getAllowChangeMessage(), BranchAuthorityName.CHANGEMESSAGE,
					       currentBranchAuthority.getAllowSeeMessage(), BranchAuthorityName.SEEMESSAGE);
			
			currentBranchAuthority.setAllowCreateMessage(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CREATEMESSAGE;
		
		// 信息更改权限
		case "allowChangeMessage":
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEMESSAGE,
						   currentBranchAuthority.getAllowDeleteMessage(), BranchAuthorityName.DELETEMESSAGE,
					       currentBranchAuthority.getAllowCreateMessage(), BranchAuthorityName.CREATEMESSAGE);
			
			currentBranchAuthority.setAllowChangeMessage(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CHANGEMESSAGE;
		
		// 信息删除权限
		case "allowDeleteMessage":
			checkBanchRole(becomeRole, BranchAuthorityName.DELETEMEMBER,
						   currentBranchAuthority.getAllowChangeInformation(), BranchAuthorityName.CHANGEINFORMATION,
					       currentBranchAuthority.getAllowChangeMessage(), BranchAuthorityName.CHANGEMESSAGE);
			currentBranchAuthority.setAllowDeleteMessage(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.DELETEMEMBER;
		
		// 获取任务权限
		case "allowTakeTask":
			checkBanchRole(becomeRole, BranchAuthorityName.TAKETASK,
					   	   currentBranchAuthority.getAllowCreateTask(), BranchAuthorityName.CREATETASK,
				           currentBranchAuthority.getAllowInto(), BranchAuthorityName.INTO);
			currentBranchAuthority.setAllowTakeTask(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.TAKETASK;
		
		// 任务创建权限
		case "allowCreateTask":
			checkBanchRole(becomeRole, BranchAuthorityName.CREATETASK,
				   	   	   currentBranchAuthority.getAllowChangeTask(), BranchAuthorityName.CHANGETASK,
			               currentBranchAuthority.getAllowTakeTask(), BranchAuthorityName.TAKETASK);
			currentBranchAuthority.setAllowCreateTask(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CREATETASK;
		
		// 任务更改权限
		case "allowChangeTask":
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGETASK,
			   	   	   	   currentBranchAuthority.getAllowDeleteTask(), BranchAuthorityName.DELETETASK,
		                   currentBranchAuthority.getAllowCreateTask(), BranchAuthorityName.CREATETASK);
			currentBranchAuthority.setAllowChangeTask(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CHANGETASK;
		
		// 任务删除权限
		case "allowDeleteTask":
			checkBanchRole(becomeRole, BranchAuthorityName.DELETETASK,
			   	   	   	   currentBranchAuthority.getAllowChangeInformation(), BranchAuthorityName.CHANGEINFORMATION,
		                   currentBranchAuthority.getAllowChangeTask(), BranchAuthorityName.CHANGETASK);
			currentBranchAuthority.setAllowDeleteTask(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.DELETETASK;
		
		// 评论发布权限
		case "allowPublishComment":
			checkBanchRole(becomeRole, BranchAuthorityName.PUBLISHCOMMENT,
		   	   	   	   	   currentBranchAuthority.getAllowDeleteComment(), BranchAuthorityName.DELETECOMMENT,
	                       currentBranchAuthority.getAllowInto(), BranchAuthorityName.INTO);
		    currentBranchAuthority.setAllowPublishComment(becomeRole);
		    branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.PUBLISHCOMMENT;
		
		// 评论删除权限
		case "allowDeleteComment":
			checkBanchRole(becomeRole, BranchAuthorityName.DELETECOMMENT,
						   currentBranchAuthority.getAllowChangeInformation(), BranchAuthorityName.CHANGEINFORMATION,
						   currentBranchAuthority.getAllowPublishComment(), BranchAuthorityName.PUBLISHCOMMENT);
			currentBranchAuthority.setAllowDeleteComment(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.DELETECOMMENT;
		
		// 成员查看权限
		case "allowSeeMember":
			checkBanchRole(becomeRole, BranchAuthorityName.SEEMEMBER,
					   	   currentBranchAuthority.getAllowAddMember(), BranchAuthorityName.ADDMEMBER,
					       currentBranchAuthority.getAllowInto(), BranchAuthorityName.INTO);
			currentBranchAuthority.setAllowSeeMember(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.SEEMEMBER;
		
		// 成员添加权限
		case "allowAddMember":
			checkBanchRole(becomeRole, BranchAuthorityName.ADDMEMBER,
					   	   currentBranchAuthority.getAllowChangeMember(), BranchAuthorityName.CHANGEMEMBER,
					       currentBranchAuthority.getAllowSeeMember(), BranchAuthorityName.SEEMEMBER);
			currentBranchAuthority.setAllowAddMember(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.ADDMEMBER;
		
		// 成员修改权限
		case "allowChangeMember":
			checkBanchRole(becomeRole, BranchAuthorityName.CHANGEMEMBER,
					   	   currentBranchAuthority.getAllowDeleteMember(), BranchAuthorityName.DELETEMEMBER,
					       currentBranchAuthority.getAllowAddMember(), BranchAuthorityName.ADDMEMBER);
			currentBranchAuthority.setAllowChangeMember(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.CHANGEMEMBER;
		
		// 成员删除权限
		case "allowDeleteMember":
			checkBanchRole(becomeRole, BranchAuthorityName.DELETEMEMBER,
					   	   currentBranchAuthority.getAllowChangeInformation(), BranchAuthorityName.CHANGEINFORMATION,
					       currentBranchAuthority.getAllowChangeMember(), BranchAuthorityName.CHANGEMEMBER);
			currentBranchAuthority.setAllowDeleteMember(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return BranchAuthorityName.DELETEMEMBER;
			
		default:
			throw new FaultyOperationException("错误操作！");
		}
	}
	
	// 修改角色
	@Override
	public boolean changeBranchRole(Branch currentBranch, BranchMember currentMember, String oldRoleName,
			String newRoleName, int newRoleLevel) {
		BranchRole oldBranchRole = branchRoleSerivce.findByRoleNameAndBranchId(oldRoleName, currentBranch);
		int oldBranchLevel = oldBranchRole.getRoleLevel(); 
		int memberLevel = currentMember.getBranchRole().getRoleLevel();
		if(oldBranchRole.getRoleLevel() < memberLevel || newRoleLevel < memberLevel){
			throw new PermissionDeniedException("你没有权限设置权限等级比你高的角色");
		}
		branchRoleSerivce.ChangeBranchRole(currentBranch, oldBranchRole, newRoleName, newRoleLevel);
		
		if(newRoleLevel != oldBranchLevel){
			// 对权限等级修改将重置 branch 设置
			branchAuthorityService.resetBranchAthority(currentBranch);
		}
		return true;
	}
	
	// 判断能否对权限进行修改
	protected boolean checkBanchRole(BranchRole becomeRlole, String currentAuthorityName, BranchRole maxRole, String maxAuthorityName, BranchRole minRole, String minAuthorityName) {
		if(maxAuthorityName != null && maxRole == null) {
			throw new FaultyOperationException("请先定义 " + maxAuthorityName);
		}
		
		if((becomeRlole == null && minRole != null) || (minRole != null && becomeRlole != null && becomeRlole.getRoleLevel() > minRole.getRoleLevel())) {
			throw new FaultyOperationException(currentAuthorityName + " 不能小于 " + minAuthorityName);
		}
		if(becomeRlole != null && maxRole != null && becomeRlole.getRoleLevel() < maxRole.getRoleLevel()) {
			throw new FaultyOperationException(currentAuthorityName + " 不能大于 " + maxAuthorityName);
		}
		return true;
	}
	
	// 判断改角色是否符合要求
	protected boolean checkBecomeRole(Branch currentBranch, BranchMember currentMember, BranchRole becomeRole) {
		if(becomeRole != null) {
			if(currentMember.getBranchRole().getRoleLevel() > becomeRole.getRoleLevel()) {
				throw new FaultyOperationException("你不能设置比自己权限大的角色！");
			}
		}
		return true;
	}
}
