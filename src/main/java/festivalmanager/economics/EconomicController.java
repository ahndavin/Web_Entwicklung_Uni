package festivalmanager.economics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping (path = "/accountancy")
    public String getFestivalsListed(Model model){
        model.addAttribute("festivallist", festivalManager.findAll());
		return "accountancy";
    }

    //PostMapping
}