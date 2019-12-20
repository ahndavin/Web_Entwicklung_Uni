package festivalmanager.staff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Collections;
import java.util.List;

interface MessageRepository extends CrudRepository<Message, Long> {


	Streamable<Message> findAllByReceiver(Account account);
	Streamable<Message> findAllBySender(Account account);

	@Override
	Streamable<Message> findAll();


}
