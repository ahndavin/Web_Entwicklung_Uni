package festivalmanager.ticket;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		}
		Ticket ticket = ticketManagement.buyTicket(festivalIdForm);
		
		model.addAttribute("festivalName", ticket.getFestival().getName());
		model.addAttribute("festivalStart", ticket.getFestival().getStartDate());
		model.addAttribute("festivalEnd", ticket.getFestival().getEndDate());
		model.addAttribute("ticketType", ticket.getSort());
		model.addAttribute("id", ticket.getId());
		model.addAttribute("price", ticket.getPrice());
		return "ticket";
	}


	@PostMapping(path = "/ticketDay")
	public String buyDayticket(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
		if(result.hasErrors()){
			return "welcome";
		}
		Ticket ticket = ticketManagement.buyTicket(festivalIdForm);
		
		model.addAttribute("festivalName", ticket.getFestival().getName());
		model.addAttribute("festivalStart", ticket.getFestival().getStartDate());
		model.addAttribute("festivalEnd", ticket.getFestival().getEndDate());
		model.addAttribute("ticketType", ticket.getSort());
		model.addAttribute("id", ticket.getId());
		model.addAttribute("price", ticket.getPrice());
		return "ticket";
	}
	
	@PostMapping(path = "/checkTicket")
	public String checkTicket(	@RequestParam("festival") String festival,
								@RequestParam("ticketType") String sort_str,
								@RequestParam("id") String id_str,
								HttpServletResponse response) throws Exception {
		festival = festival.substring(0, festival.length()-1);
		Ticket ticket = ticketManagement.checkTicket(festival, sort_str, Long.parseLong(id_str));
		
		if(ticket == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Ticket does not exist.'); location.href='/';</script>");
			out.flush();
		}
		else if(ticket.getUsed()) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('This ticket has already been used.'); location.href='/';</script>");
			out.flush();
		}
		else {
			ticketManagement.setTicketStatus(ticket);
            
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Welcome!'); location.href='/';</script>");
			out.flush();
		}
		
		return "redirect:/";
	}
}