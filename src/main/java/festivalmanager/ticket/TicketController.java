package festivalmanager.ticket;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import festivalmanager.festival.Festival;
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
	@PostMapping(path = "/ticketManagement")
	public String buyTicket(@Valid FestivalIdForm festivalIdForm, Errors result, Sort sort){
		Festival festival = ticketManagement.findById(festivalIdForm.getId());
		if(result.hasErrors()){
			return "ticketManagement";
		}
		else{
			if(ticketManagement.isAvailable(sort, festival) == true){
				if(sort == Sort.CAMPINGTICKET){
					ticketManagement.buyCampingticket(festival);
				}
				if(sort == Sort.DAYTICKET){
					ticketManagement.buyDayticket(festival);
				}
			}
			return "ticketManagement";
		}
	}

}