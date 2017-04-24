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
			throw new PermissionDeniedException("你并非" + branch.getBranchName() + "成员，不能进行该操作");
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
		if(becomeRole != null) {
			if(currentMember.getBranchRole().getRoleLevel() > becomeRole.getRoleLevel()) {
				throw new FaultyOperationException("你不能设置比自己权限大的角色！");
			}
		}

		switch(authorityName) 
		{
		case "allowInto" :
			if(roleName != null && !roleName.trim().isEmpty()) {
				checkBanchRole(becomeRole, "访问权限", currentBranchAuthority.getAllowChangeInformation(), "组织设置权限", null, null);
				currentBranchAuthority.setAllowInto(becomeRole);
			} else {
				currentBranchAuthority.setAllowInto(null);
			}
			branchAuthorityRepository.save(currentBranchAuthority);
			return "访问权限";
			
		case "allowChangeInformation" :
			if(becomeRole == null) {
				throw new FaultyOperationException("该权限不能为空");
			}
			checkBanchRole(becomeRole, "组织设置权限", null, null, currentBranchAuthority.getAllowInto(), "组织访问权限");
			currentBranchAuthority.setAllowChangeInformation(becomeRole);
			branchAuthorityRepository.save(currentBranchAuthority);
			return "组织设置权限";
			
		default:
			return "没有设置";
		}
	}
	
	protected boolean checkBanchRole(BranchRole becomeRlole, String currentAuthorityName, BranchRole maxRole, String maxAuthorityName, BranchRole minRole, String minAuthorityName) {
		if(maxAuthorityName != null && maxRole == null) {
			throw new FaultyOperationException("请先定义 " + maxAuthorityName);
		}
		if(minRole != null && becomeRlole.getRoleLevel() > minRole.getRoleLevel()) {
			throw new FaultyOperationException(currentAuthorityName + " 不能小于 " + minAuthorityName);
		}
		if(maxRole != null && becomeRlole.getRoleLevel() < maxRole.getRoleLevel()) {
			throw new FaultyOperationException(currentAuthorityName + " 不能大于 " + maxAuthorityName);
		}
		return true;
	}
}
