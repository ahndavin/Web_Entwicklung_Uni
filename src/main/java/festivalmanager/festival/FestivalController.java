package festivalmanager.festival;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FestivalController {
	private FestivalManager festivals;

	public FestivalController(FestivalManager festivals) {
		this.festivals = festivals;
	}

	@GetMapping("/festivals")
	String festivals(Model model) {
		model.addAttribute("festivals", festivals.findAll());

		return "festivals";
	}

	@GetMapping("/festival/add")
	String addFestival(Model model) {
		model.addAttribute("festival_form", new FestivalForm());

		return "add_festival";
	}

	@PostMapping("/festival/add")
	String addFestival(@ModelAttribute FestivalForm festivalForm) {

		festivals.save(festivalForm.toFestival());

		return "redirect:/festivals";
	}
}
