package festivalmanager.staff;

import javax.validation.constraints.NotEmpty;


public class changePasswordForm {


	@NotEmpty
	public String newPassword;

	public changePasswordForm( String newPassword){
		this.newPassword = newPassword;


		//this.newPassword_validate = newPassword_validate;
	}



	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


}
