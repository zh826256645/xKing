package xKing.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.message.dao.CommentRepository;
import xKing.message.dao.MessageRepository;
import xKing.message.dao.MessageTagRepository;
import xKing.message.domain.BranchMessage;
import xKing.message.domain.BranchMessageComment;
import xKing.message.domain.MessageTag;
import xKing.message.service.MessageService;
import xKing.user.domain.User;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageTagRepository messageTagRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	// 添加信息的标签
	@Override
	public MessageTag addMessageTag(Branch currentBranch, BranchMember currentMember, String tagName) {
		if(tagName.trim().isEmpty()) {
			throw new FaultyOperationException("标签名不能为空！");
		}
		MessageTag tag = this.getMessageTagByBranchAndTagName(currentBranch, tagName);
		if(tag != null) {
			throw new ExistedException("该标签已被创建请不要重复创建！");
		}
		MessageTag newTag = new MessageTag();
		newTag.init(currentBranch, currentMember, tagName);
		return messageTagRepository.save(newTag);
	}

	// 获取组织的标签
	@Override
	public List<MessageTag> getMessageTags(Branch currentBranch) {
		return messageTagRepository.findByBranch_id(currentBranch.getId());
	}

	// 创建组织信息
	@Override
	public BranchMessage createMessage(Branch currentBranch, BranchMember currentMember, BranchMessage message,
			MessageTag messageTag) {
		BranchMessage newMessage = new BranchMessage();
		newMessage.init(currentBranch, currentMember, messageTag, message.getTitle(), message.getMessageContent());
		return messageRepository.save(newMessage);
	}

	// 获取信息标签
	@Override
	public MessageTag getMessageTagByBranchAndTagName(Branch currentBranch, String tagName) {
		return messageTagRepository.findByTagNameAndBranch_id(tagName, currentBranch.getId());
	}

	// 获取组织的公告
	@Override
	public Page<BranchMessage> getBranchMessages(Branch currentBranch, Pageable pageable) {
		return messageRepository.findByBranch_idOrderByCreateTimeDesc(currentBranch.getId(), pageable);
	}

	// 获取组织公告
	@Override
	public BranchMessage getBranchMessage(Branch currentBranch, long messageId) {
		if(messageId == 0) {
			throw new AbsentException("错误操作！");
		}
		BranchMessage branchMessage = messageRepository.findByBranch_idAndId(currentBranch.getId(), messageId);
		if(branchMessage == null) {
			throw new AbsentException("该公告不存在！");
		}
		return branchMessage;
	}

	// 发表评论
	@Override
	public BranchMessageComment publishedComment(User currentUser, Branch currentBranch, BranchMessage currentBranchMessage, String comment) {
		if(comment.trim().isEmpty()) {
			throw new FaultyOperationException("评论内容不能为空！");
		}
		if(comment.length() > 120) {
			throw new FaultyOperationException("评论不能大于 120 字符！");
		} 
		BranchMessageComment branchMessagecomment = new BranchMessageComment();
		branchMessagecomment.init(currentUser, currentBranch, currentBranchMessage, comment);
		return commentRepository.save(branchMessagecomment);
	}

	// 获取文章的评论
	@Override
	public List<BranchMessageComment> getMessageComments(Branch currentBranch, BranchMessage currentBranchMessage) {
		return commentRepository.findByBranch_idAndBranchMessage_idOrderByCreateTime(currentBranch.getId(), currentBranchMessage.getId());
	}

	// 获取文章评论数量
	@Override
	public Long getMessageCommentNum(Branch currentBranch, BranchMessage currentBranchMessage) {
		return commentRepository.countByBranch_idAndBranchMessage_id(currentBranch.getId(), currentBranchMessage.getId());
	}

	// 修改组织公告
	@Override
	public BranchMessage changBranchMessage(Branch currentBrnach, BranchMessage oldBranchMessage,
			BranchMessage newBranchMessage, String tagName) {
		MessageTag newMessageTag = null;
		if(!tagName.trim().isEmpty()) {
			newMessageTag = messageTagRepository.findByTagNameAndBranch_id(tagName, currentBrnach.getId());
		}
		oldBranchMessage.change(newBranchMessage, newMessageTag);
		return messageRepository.save(oldBranchMessage);
	}

	// 获取组织公告数量
	@Override
	public Long getMessageNum(Branch currentBranch) {
		return messageRepository.countByBranch_id(currentBranch.getId());
	}
}
