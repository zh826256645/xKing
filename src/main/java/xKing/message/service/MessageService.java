package xKing.message.service;

import java.util.List;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.message.domain.MessageTag;

public interface MessageService {
	MessageTag addMessageTag(Branch currentBranch, BranchMember currentMember, String tagName);
	List<MessageTag> getMessageTags(Branch currentBranch);
}
