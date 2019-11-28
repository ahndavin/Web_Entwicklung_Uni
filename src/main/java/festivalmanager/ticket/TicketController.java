package festivalmanager.ticket;

import javax.validation.Valid;
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


	@GetMapping("/ticketManagement")
	public String ticketManagement(Model model){
		model.addAttribute("ticketList", ticketManager.getAllTickets());
		return "ticketManagement";
	}


	@PostMapping("/createTicket")
	public String addProduct(@Valid Ticket ticket) {

		ticketManager.addTicket(ticket);

		return "redirect:/ticketManagement";
	}

	@GetMapping("/createTicket")
	public String addProduct(Model model, Ticket ticket) {

		model.addAttribute("ticket", ticket);

		return "createTicket";
	}

}