package xKing.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.message.dao.MessageRepository;
import xKing.message.dao.MessageTagRepository;
import xKing.message.domain.BranchMessage;
import xKing.message.domain.MessageTag;
import xKing.message.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageTagRepository messageTagRepository;

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
		return messageRepository.findByBranch_idOrderByCreateTime(currentBranch.getId(), pageable);
	}
	
}
