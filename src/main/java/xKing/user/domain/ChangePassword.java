package xKing.user.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ChangePassword {
	
	@NotNull
	@Size(min=6, max=32, message="{password.size}")
	private String newPassword;
	@NotNull
	@Size(min=6, max=32, message="{password.size}")
	private String oldPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	@Override
	public String toString() {
		return "ChangePassword [newPassword=" + newPassword + ", oldPassword=" + oldPassword + "]";
	}
	
	
}
