package festivalmanager.staff;

import org.springframework.context.ApplicationEvent;



public class MessageEvent extends ApplicationEvent {
	private Account sender;
	private Account receiver;
	private MessageState state;
	private String message;

	public MessageEvent(Object source, Account sender, Account receiver, String message) {
		super(source);
		this.sender = sender;
		this. receiver = receiver;
		this.message = message;
	}

	public Account getSender() {
		return sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public MessageState getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public Object getSource() {
		return super.getSource();
	}
}
