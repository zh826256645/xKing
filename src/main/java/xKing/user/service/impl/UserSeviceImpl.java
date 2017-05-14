package xKing.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchMemberRequest;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberRequestService;
import xKing.branch.service.BranchMemberSerivce;
import xKing.branch.service.BranchService;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.history.domain.UserHistory;
import xKing.history.domain.UserHistoryType;
import xKing.history.service.HistoryService;
import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;
import xKing.user.dao.UserFriendRepository;
import xKing.user.dao.UserRepository;
import xKing.user.domain.ChangePassword;
import xKing.user.domain.User;
import xKing.user.domain.UserFriend;
import xKing.user.exception.SameUsernameException;
import xKing.user.exception.UserActivateErrorException;
import xKing.user.exception.UserNotExistException;
import xKing.user.service.UserService;
import xKing.utils.FontImageUtils;
import xKing.utils.MD5;
import xKing.utils.Utils;

// Service 组件
@Service
@Transactional
public class UserSeviceImpl implements UserService {

	@Autowired
	private MailService mailSerivce;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserFriendRepository userFriendRepository;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private BranchMemberRequestService branchMemberRequestService;
	
	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private HistoryService historyService;

	
	// 用户认证
	@Override
	public User Login(User user) {
		String passwordMd5 = MD5.EncoderByMd5(user.getPassword());
		User userData = userRepository.findByUsername(user.getUsername());
		if(userData.getPassword().equals(passwordMd5)) {
			return userData;
		}
		return null;
	}
	
	// 用户注册
	@Override
	public User register(User user, String scheme, String hostName) {
		User getUser = userRepository.findByUsername(user.getUsername());
		User eUser = userRepository.findByEmail(user.getEmail());
				
		if(getUser != null) {
			throw new SameUsernameException("用户名重复");
		}
		if(eUser != null){
			throw new SameUsernameException("该 Email 已被注册");
		}
		
		User newUser = userRepository.save(initNewUser(user));
		Mail mail = this.activationMail(newUser, scheme, hostName);
		mailSerivce.sendActivationEmailToUserByVelocity(user.getEmail(), mail);
		return newUser;
	}

	// 根据用户名获取用户
	@Override
	public User getUserByUsername(String username) {
		if(username == null || username.trim().isEmpty()) {
			throw new FaultyOperationException("错误操作！");
		}
		User user = userRepository.findByUsername(username);
		if(user != null) {
			return user;
		} else {
			throw new UserNotExistException("用户不存在！");
		}
	}
	
	// 用户激活
	@Override
	public boolean activateUser(String username, String key) {
		User user = this.getUserByUsername(username);
		if(user.getState() == 0) {
			if(user.getKeyCode().equals(key)) {
				long keyDate = user.getKeyDate().getTime();
				if(keyDate - (new Date()).getTime() < 1800000) {
					user.setState(1);
					userRepository.save(user);
					return true;
				} else {
					User userAddNewKey = this.addNewKey(user);
					Mail mail = this.activationMail(userAddNewKey);
					mailSerivce.sendActivationEmailToUserByVelocity(userAddNewKey.getEmail(), mail);
					throw new UserActivateErrorException("你的激活码已过期，新的激活码已发送至你的邮箱，请前去邮箱激活!");
				}
			} else {
				throw new UserActivateErrorException("激活码错误!");
			}
		} else {
			return false;
		}
	}
	
	// 更新用户信息
	@Override
	public User updateProfile(User userUpdateMessage) {
		User user = this.getUserByUsername(userUpdateMessage.getUsername());
		
		user.setBlog(userUpdateMessage.getBlog());
		user.setIntroduction(userUpdateMessage.getIntroduction());
		user.setName(userUpdateMessage.getName());
		
		User currentUser = userRepository.save(user);
		return currentUser;
	}
	
	// 更改密码
	@Override
	public boolean changePassword(String username, ChangePassword changePassword) {
		User currentUser = this.getUserByUsername(username);
		String oldPassword = MD5.EncoderByMd5(changePassword.getOldPassword());
		if(currentUser.getPassword().equals(oldPassword)) {
			currentUser.setPassword(MD5.EncoderByMd5(changePassword.getNewPassword()));
			userRepository.save(currentUser);
			return true;
		} else {
			return false;
		}
	}

	// 更新 introduction
	@Override
	public String updateIntroduction(String username, String newIntroduction) {
			User currentUser = userRepository.findByUsername(username);
			currentUser.setIntroduction(newIntroduction);
			userRepository.save(currentUser);
			return newIntroduction;
	}

	// 保存用户上传文件
	@Override
	public String saveUploda(String originalFilename, String username) {
		String extensionName = getExtensionName(originalFilename);
		User currentUser = this.getUserByUsername(username);
		if(extensionName != null) {
			long pid = currentUser.getId();
			String picuture = pid + extensionName;
			currentUser.setPicture(picuture);
			userRepository.save(currentUser);
			return picuture;
		}
		return null;
	}
	
	
	// 初始化新用
	protected User initNewUser(User user) {
		// 设置激活码
		User newUser = this.addNewKey(user);
		// MD5 加密密码
		String password = user.getPassword();
		String MD5Password = MD5.EncoderByMd5(password);
		newUser.setPassword(MD5Password);	
		FontImageUtils utils = new FontImageUtils();
		newUser.setPicture(utils.randomColor().getRGB() + "");
		return newUser;
	}
	
	// 设置用户状态，添加验证码
	protected User addNewKey(User user) {
		user.setState(0);
		user.setKeyCode(Utils.getUUID());
		user.setKeyDate(new Date());
		return user;
	}
	
	// 设置激活邮件
	protected Mail activationMail(User user) {
		Mail mail = new Mail();
		mail.setSubject("xKing 账户激活邮件");
		mail.setActiveCode(user.getKeyCode());
		mail.setUsername(user.getUsername());
		mail.setText("");
		mail.setTime(Utils.getCurrentDate());
		return mail;
	}
	
	// 设置激活邮件
	protected Mail activationMail(User user, String scheme, String hostName) {
		Mail mail = new Mail();
		mail.setSubject("xKing 账户激活邮件");
		mail.setScheme(scheme);
		mail.setHostName(hostName);
		mail.setActiveCode(user.getKeyCode());
		mail.setUsername(user.getUsername());
		mail.setText("");
		mail.setTime(Utils.getCurrentDate());
		return mail;
	}
	
	
	// 截取文件名后缀
	protected String getExtensionName(String fileName) {
		int i = fileName.lastIndexOf(".");
		if((i > -1) && i < (fileName.length() - 1)) {
			return fileName.substring(i); 
		}
		return null;
	}

	// 添加好友
	@Override
	public boolean addFriend(String username, User currentUser) {
		
		if(username == null || username.equalsIgnoreCase(currentUser.getUsername())){
			String msg = "用户名不能空";
			if(username != null){
				msg = "不能添加自己";
			}
			throw new FaultyOperationException(msg);
		}
		User user = getUserByUsername(username);
		if(user == null){
			throw new UserNotExistException("该用户不存在，请确认后在添加");
		}
		
		UserFriend userFriend = userFriendRepository.findOneByUser_idAndFriend_id(currentUser.getId(), user.getId());
		UserFriend friendUser = userFriendRepository.findOneByUser_idAndFriend_id(user.getId(), currentUser.getId());
		if(userFriend != null || friendUser != null){
			String msg = "该用户已经是你的好友，不能重复添加";
			if(userFriend != null && userFriend.getState() == 0){
				msg = "你已经发送发送好友请求，请等待对方确认";
			}else if(friendUser != null && friendUser.getState() == 0){
				msg = "对方已经发送好友请求，请进行确认";
			}
			throw new ExistedException(msg);
		}
		
		
		UserFriend newUserFriend = new UserFriend(currentUser, user);
		userFriendRepository.save(newUserFriend);
		
		// 记录操作
		UserHistory history = new UserHistory(null, currentUser, user, UserHistoryType.AddFriend, "发出了好友请求", null);
		historyService.createUserHistory(history);
		
		Mail mail = mailSerivce.initMessageMail(user, currentUser.getUsername() + " 向你发送了好友请求！");
		mailSerivce.sendMessageEmailToUserByVelocity(user.getEmail(), mail);
		return true;
	}

	// 处理好友请求
	@Override
	public boolean setUserFriendState(String username, int state, User currentUser) {
		if((state != 1 & state != 2) || username == null){
			throw new FaultyOperationException("错误操作");
		}
		User user = this.getUserByUsername(username);
		UserFriend newUserFriend = userFriendRepository.findOneByUser_idAndFriend_idAndState(user.getId(), currentUser.getId(), 0);
		if(newUserFriend == null){
			throw new AbsentException("该用户没有新的好友请求");
		}
		
		newUserFriend.setState(state);
		userFriendRepository.save(newUserFriend);
		
		// 记录操作
		UserHistory history = new UserHistory(null, currentUser, user, UserHistoryType.AgreeFriend, "同意了", null);
		historyService.createUserHistory(history);
		return true;
	}

	// 获取好友
	@Override
	public Page<UserFriend> getFriends(User currentUser, Pageable pageable) {
		Page<UserFriend> page = userFriendRepository.findByUser_idOrFriend_idAndStateOrderByCreateTime(currentUser.getId(), currentUser.getId(), 1, pageable);
		return page;
	}
	
	// 获取好友请求
	public Page<UserFriend> getFriendRequests(User currentUser, Pageable pageable) {
		Page<UserFriend> page = userFriendRepository.findByFriend_idAndStateOrderByCreateTime(currentUser.getId(), 0, pageable);
		return page;
	}

	// 处理 组织邀请
	@Override
	public boolean handelMemberRequest(User currentUser, String branchName, int state) {
		Branch branch = branchService.findBranchByBranchName(branchName);
		BranchMember member = branchMemberService.findByBranchidAndUserId(branch, currentUser);
		if(member != null) {
			throw new FaultyOperationException("你已经是改组织的成员！");
		}
		BranchMemberRequest memberRequest = branchMemberRequestService.getByUserAndBranchAndState(currentUser, branch, 1);
		if(memberRequest == null) {
			branchMemberRequestService.removeBranchMemberRequest(memberRequest);
			throw new FaultyOperationException("没有该请求！");
		}
		
		if(state == 1) {
			BranchRole newBranchRole = branch.getNewMemberRole();
			branchMemberService.addBranchMember(currentUser.getUsername(), currentUser.getEmail(), branch, newBranchRole, currentUser);
			branchMemberRequestService.removeBranchMemberRequest(memberRequest);
			
			// 记录操作
			UserHistory history = new UserHistory(branch, currentUser, null, UserHistoryType.Branch, "同意了", null);
			historyService.createUserHistory(history);
			return true;
		} else {
			branchMemberRequestService.removeBranchMemberRequest(memberRequest);
			UserHistory history = new UserHistory(branch, currentUser, null, UserHistoryType.Branch, "拒绝了", null);
			historyService.createUserHistory(history);
			return false;
		}
	}

	// 请求加入组织
	@Override
	public boolean requestJoin(User currentUser, String branchName, String message) {
		Branch branch = branchService.findBranchByBranchName(branchName);
		BranchMember member = branchMemberService.findByBranchidAndUserId(branch, currentUser);
		if(member != null){
			throw new FaultyOperationException("你已经是该组织的成员！");
		}
		BranchMemberRequest userRequest = branchMemberRequestService.getByUserAndBranchAndState(currentUser, branch, 2);
		if(userRequest != null) {
			throw new FaultyOperationException("请不要重复请求!");
		}
		BranchMemberRequest inviteRequest = branchMemberRequestService.getByUserAndBranchAndState(currentUser, branch, 1);
		if(inviteRequest != null) {
			branchMemberService.addBranchMember(currentUser.getUsername(), currentUser.getEmail(), branch, branch.getNewMemberRole(), currentUser);
			branchMemberRequestService.removeBranchMemberRequest(inviteRequest);
			return false;
		}
		branchMemberRequestService.addNewBranchMemberRequest(branch, currentUser, message, 2);
		UserHistory history = new UserHistory(branch, currentUser, null, UserHistoryType.RequestBranch, "你申请加入了", null);
		historyService.createUserHistory(history);
		return true;
	}

	// 获取好友数量
	@Override
	public long getFriendNum(User currentUser) {
		return userFriendRepository.countByUser_idOrFriend_idAndState(currentUser.getId(), currentUser.getId(), 1);
	}
}
