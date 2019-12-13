package festivalmanager.economics;

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
import festivalmanager.festival.FestivalManager;

@Controller
public class EconomicController{

    public final EconomicManager economicManager;
    public final FestivalManager festivalManager;

    public EconomicController(EconomicManager economicManager, FestivalManager festivalManager){
        Assert.notNull(economicManager, "TicketManagement must not be null!");
        Assert.notNull(festivalManager, "FestivalManager must not be null!");
        this.economicManager = economicManager;
        this.festivalManager = festivalManager;
    }

    //GetMapping 
    @GetMapping(path = "/accountancy")
	public String goToAccountancy (@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
		Festival festival = economicManager.findById(festivalIdForm.getId());
		if(result.hasErrors()){
			return "festivals";
		}
	
        model.addAttribute("entrylist", economicManager.getAll(festival));
        model.addAttribute("sumRevenues", economicManager.getRevenues(festival));
        model.addAttribute("sumExpenses", economicManager.getExpenses(festival));
        model.addAttribute("sumAll", economicManager.getSum(festival));
		return "accountancy";
	} 
}