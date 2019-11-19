package festivalmanager.festival;

import org.salespointframework.catalog.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FestivalController {
	private FestivalRepository festivals;

	public FestivalController(FestivalRepository festivals) {
		this.festivals = festivals;
	}

	@GetMapping("/festivals")
	String festivals(Model model) {
		model.addAttribute("festivals", festivals.findAll());

		return "festivals";
	}
}
