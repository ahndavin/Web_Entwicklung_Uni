package festivalmanager.festival;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class FestivalController {
	private FestivalManager festivals;

	public FestivalController(FestivalManager festivals) {
		this.festivals = festivals;
	}

	/**
	 *	list all existing festivals
	 */
	@GetMapping("/festivals")
	String festivals(Model model) {
		model.addAttribute("festivals", festivals.findAll());

		return "festivals";
	}

	/**
	 *
	 * @param festivalName: name of the festival, passed in the URL
	 * @param festivalId:	id of the festival, passed in the URL
	 */
	@GetMapping("/festival/{festivalName}-{festivalId}")
	String festival(@PathVariable String festivalName, @PathVariable long festivalId, Model model) {
		Optional<Festival> festival = festivals.findById(festivalId);

		if(festival.isEmpty()) {
			return "redirect:/404";
		}

		model.addAttribute("festival", festival.get());

		return "festival_detail";
	}


	/**
	 * add a new festival
	 */
	@GetMapping("/festival/add")
	String addFestival(Model model) {
		model.addAttribute("festival_form", new FestivalForm());

		return "festival_add";
	}

	/**
	 *
	 * @param festivalForm: form the user filed with information about the new festival
	 * @param errors: errors from validation of festival data
	 */
	@PostMapping("/festival/add")
	String addFestival(@ModelAttribute @Valid FestivalForm festivalForm, Errors errors, RedirectAttributes redirectAttributes) {
		boolean result = saveOrUpdate(festivalForm, errors);

		if(!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim erstellen des Festivals.");
		}

		return "redirect:/festivals";
	}

	/**
	 *
	 * @param festivalId: id of the festival
	 */
	@GetMapping("/festival/{festivalName}-{festivalId}/edit")
	String editFestival(@PathVariable long festivalId, Model model) {
		Optional<Festival> festival = festivals.findById(festivalId);;

		if(festival.isEmpty()) {
			return "redirect:/404";
		}

		model.addAttribute("festival_form", festival.get());

		return "festival_edit";
	}

	/**
	 *
	 * @param festivalForm: form with the new data
	 * @param errors: errors from validation of the festival data
	 */
	@PostMapping("/festival/edit")
	String editFestival(@ModelAttribute("festival_form") @Valid FestivalForm festivalForm, Errors errors, RedirectAttributes redirectAttributes) {
		Optional<Festival> unupdatedFestival = festivals.findById(festivalForm.getId());
		unupdatedFestival.ifPresent(value -> festivalForm.setPlan((List<String>) value.getPlan()));

		boolean result = saveOrUpdate(festivalForm, errors);

		if(!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim editieren des Festivals.");
		}

		return "redirect:/festival/" + festivalForm.getName() + "-" + festivalForm.getId();
	}

	/**
	 * helper method to reduce duplicate code
	 */
	private boolean saveOrUpdate(FestivalForm festivalForm, Errors errors) {
		boolean result = false;

		Festival festival = festivalForm.toFestival();

		if(!errors.hasErrors()) {
			result = festivals.save(festival) != null;
		}

		return result;
	}
}
