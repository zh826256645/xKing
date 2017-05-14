package xKing.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_friend_message")
public class FriendMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
}
