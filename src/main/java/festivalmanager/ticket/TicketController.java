package festivalmanager.ticket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

	private final TicketManagement ticketManagement;

	public TicketController(TicketManagement ticketManagement){
		Assert.notNull(ticketManagement, "TicketManagement must not be null!");
		this.ticketManagement = ticketManagement;
	}

	//GetMapping
	@GetMapping(path = "/ticketManagement")
	public String ticketOverview(Model model){
		model.addAttribute("festivallist", ticketManagement.findAll());
		return "ticketManagement";
	}

	//PostMapping

}