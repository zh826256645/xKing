package xKing.message.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.message.domain.BranchMessage;
import xKing.message.domain.BranchMessageComment;
import xKing.message.domain.MessageTag;
import xKing.user.domain.User;

public interface MessageService {
	MessageTag addMessageTag(Branch currentBranch, BranchMember currentMember, String tagName);
	List<MessageTag> getMessageTags(Branch currentBranch);
	BranchMessage createMessage(Branch currentBranch, BranchMember currentMember, BranchMessage message, MessageTag messageTag);
	MessageTag getMessageTagByBranchAndTagName(Branch currentBranch, String tagName);
	Page<BranchMessage> getBranchMessages(Branch currentBranch, Pageable pageable);
	BranchMessage getBranchMessage(Branch currentBranch, long messageId);
	BranchMessageComment publishedComment(User currentUser, Branch currentBranch, BranchMessage currentBranchMessage, String comment);
	List<BranchMessageComment> getMessageComments(Branch currentBranch, BranchMessage currentBranchMessage);
	Long getMessageCommentNum(Branch currentBranch, BranchMessage currentBranchMessage);
	BranchMessage changBranchMessage(Branch currentBranch, BranchMessage oldBranchMessage, BranchMessage newBranchMessage, String tagName);
}
