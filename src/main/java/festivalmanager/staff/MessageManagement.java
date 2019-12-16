package festivalmanager.staff;


import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageManagement {
	private final MessageRepository messages;

	public MessageManagement(MessageRepository messages){this.messages = messages;}

	public List<Message> getAllMessagesForAccount(Account account){
		return this.findAllByReceiver(account);

	}

	public Message createNewMessage(MessageEvent event){
		Message message = new Message(event.getSender(), event.getReceiver(), event.getMessage());
		return messages.save(message);
	}

	public List<Message> findAllByReceiver(Account receiver) {
		List<Message> messagesByReceiver = Collections.emptyList();
		Iterable<Message> allMessages = messages.findAll();
		for (Message message : allMessages) {
			if (message.getReceiver() == receiver) {
				messagesByReceiver.add(message);
			}
		}
		return messagesByReceiver;
	}
}
