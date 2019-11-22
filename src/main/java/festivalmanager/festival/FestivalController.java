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

	@GetMapping("/festival/{festivalName}-{festivalId}")
	String festival(@PathVariable String festivalName, @PathVariable long festivalId, Model model) {
		Festival festival = null;

		for(Festival f : festivals.findAll()) {
			if(f.getName().equals(festivalName) && f.getId() == festivalId) {
				festival = f;
				break;
			}
		}

		if(festival == null) {
			return "redirect:/404";
		}

		model.addAttribute("festival", festival);

		return "festival_detail";
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
