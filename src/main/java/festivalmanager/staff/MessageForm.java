package festivalmanager.staff;

import javax.validation.constraints.NotEmpty;

public class MessageForm  {

	@NotEmpty
	private String sender;


	private String receiver;

	@NotEmpty
	private String message;

	private String role;

	public MessageForm(String sender, String receiver, String role, String message){
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getSender() {
		return sender;
	}

	public String getRole() {
		return role;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


	public void setRole(String rolle) {
		this.role = role;
	}
}
