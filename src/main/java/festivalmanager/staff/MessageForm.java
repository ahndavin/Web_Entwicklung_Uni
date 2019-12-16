package festivalmanager.staff;

import javax.validation.constraints.NotEmpty;

public class MessageForm  {

	@NotEmpty
	private String sender;

	@NotEmpty
	private String receiver;

	@NotEmpty
	private String message;

	public MessageForm(String sender, String receiver, String message){
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
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

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
