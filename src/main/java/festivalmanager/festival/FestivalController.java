package festivalmanager.festival;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/festival/{festivalName}")
	String festival(@PathVariable String festivalName, Model model) {
		festivalName = festivalName.replaceAll("_", " ");

		Festival festival = null;

		for(Festival f : festivals.findAll()) {
			if(f.getName().equals(festivalName)) {
				festival = f;
				break;
			}
		}

		if(festival == null) {
			return "redirect:/404";
		}

		model.addAttribute("festival", festival);
		
		return "festival";
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
