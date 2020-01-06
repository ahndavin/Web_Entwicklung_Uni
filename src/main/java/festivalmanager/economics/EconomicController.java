package festivalmanager.economics;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalIdForm;

@Controller
public class EconomicController {

    public final EconomicManager economicManager;

    public EconomicController(EconomicManager economicManager) {
        Assert.notNull(economicManager, "TicketManagement must not be null!");
        this.economicManager = economicManager;
    }

    // GetMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping(path = "/accountancy")
    public String goToAccountancy(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result,
            Model model) {
        Optional<Festival> festivalOptinal = economicManager.findById(festivalIdForm.getId());
        Festival festival = festivalOptinal.get();
		if(result.hasErrors() || festivalOptinal.isPresent()==false){
			return "festivals";
		}
	
        model.addAttribute("entrylist", economicManager.getAll(festival));
        model.addAttribute("sumRevenues", economicManager.getRevenues(festival));
        model.addAttribute("sumExpenses", economicManager.getExpenses(festival));
        model.addAttribute("sumAll", economicManager.getSum(festival));
		return "accountancy";
	} 
}