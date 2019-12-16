package festivalmanager.staff;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class MessageEventListener implements ApplicationListener<MessageEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(MessageEventListener.class);
	private MessageManagement messageManagement;

	public MessageEventListener(MessageManagement messageManagement){
		this.messageManagement = messageManagement;
	}

	@Override
	public void onApplicationEvent(@NotNull MessageEvent event){
		LOG.info("Received MessageEvent: " + event.getSender() + " | " + event.getReceiver() + " | " + event.getMessage());
		messageManagement.createNewMessage(event);
	}
}
