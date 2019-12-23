package festivalmanager.staff;

import javax.validation.constraints.NotEmpty;

public class changeUsernameForm {


	@NotEmpty
	public String newUsername;

	public changeUsernameForm( String newUsername){
		this.newUsername = newUsername;


		//this.newPassword_validate = newPassword_validate;
	}



	public String getNewUsername() {
		return newUsername;
	}


	public void setNewPassword(String newUsername) {
		this.newUsername = newUsername;
	}


}