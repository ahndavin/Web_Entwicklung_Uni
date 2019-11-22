package festivalmanager.ticket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {

	private TicketManager ticketManager;

	public TicketController(TicketManager ticketManager){
		this.ticketManager = ticketManager;
	}
}