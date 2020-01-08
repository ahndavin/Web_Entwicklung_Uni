package festivalmanager.ticket;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@PostMapping(path = "/ticketCamping")
	 	public String buyCampingticket2(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){

		Festival festival = ticketManagement.findById(festivalIdForm.getId());
		if(result.hasErrors()){
			return "welcome";
		} else{
			if(ticketManagement.dayTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.DAYTICKET){
				ticketManagement.buyDayticket(festival);
			}
			if(ticketManagement.campingTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.CAMPINGTICKET){
				ticketManagement.buyCampingticket(festival);
			}
		}

		model.addAttribute("festivallist", ticketManagement.findAll());
		return "redirect:/#ticketsell	";
	}


	@PostMapping(path = "/ticketDay")
	public String buyDayticket3(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
		Festival festival = ticketManagement.findById(festivalIdForm.getId());
		if(result.hasErrors()){
			return "welcome";
		} else{
			if(ticketManagement.dayTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.DAYTICKET){
				ticketManagement.buyDayticket(festival);
			}
			if(ticketManagement.campingTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.CAMPINGTICKET){
				ticketManagement.buyCampingticket(festival);
			}
		}

		model.addAttribute("festivallist", ticketManagement.findAll());
		return "redirect:/#ticketsell";
	}

}