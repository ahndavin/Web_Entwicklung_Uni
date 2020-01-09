package festivalmanager.ticket;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import festivalmanager.festival.FestivalIdForm;

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
	@PostMapping(path = "/ticketCamping")
	public String buyCampingticket(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
		if(result.hasErrors()){
			return "welcome";
		} else{
			ticketManagement.buyTicket(festivalIdForm);
		}

	model.addAttribute("festivallist", ticketManagement.findAll());
	return "redirect:/#ticketsell	";
	}


	@PostMapping(path = "/ticketDay")
	public String buyDayticket(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
	if(result.hasErrors()){
		return "welcome";
	} else{
		ticketManagement.buyTicket(festivalIdForm);
	}

	model.addAttribute("festivallist", ticketManagement.findAll());
	return "redirect:/#ticketsell";
	}

}