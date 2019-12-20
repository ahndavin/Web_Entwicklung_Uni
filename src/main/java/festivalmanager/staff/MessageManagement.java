package festivalmanager.staff;


import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageManagement {
	private final MessageRepository messages;
	private static final Logger LOG = LoggerFactory.getLogger(MessageManagement.class);

	public MessageManagement(MessageRepository messages){this.messages = messages;}



	public Message createNewMessage(MessageEvent event){
		Message message = new Message(event.getSender(), event.getReceiver(), event.getMessage());
		return messages.save(message);
	}

	public boolean anyMessages(Account account){
		try{

			findAllByReceiver(account);
			return true;

		} catch (NullPointerException error){
			return false;
		}
	}

	public Streamable<Message> findAllBySender(Account sender) {

		LOG.info("searching sent messages of " + sender.getUserAccount().getUsername());
		return messages.findAllBySender(sender);
	}

	public Streamable<Message> findAllByReceiver(Account receiver) {
		LOG.info("searching received messages of " + receiver.getUserAccount().getUsername());
		Streamable<Message> messagesByReceiver = messages.findAllByReceiver(receiver);
		for(Message message : messagesByReceiver){
			LOG.info(message.getMessage());
		}

		return messagesByReceiver;
	}

	public Streamable<Message> findAll(){

		return messages.findAll();
	}
}
