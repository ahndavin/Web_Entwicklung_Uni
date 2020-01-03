package festivalmanager.festival;

import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.quantity.Quantity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class FestivalController {
	private FestivalManager festivals;
	private InventoryManager stock;

	public FestivalController(FestivalManager festivals, InventoryManager stock) {
		this.festivals = festivals;
		this.stock = stock;
	}

	/**
	 * list all existing festivals
	 */

	@GetMapping("/festivals")
	String festivals(Model model) {
		model.addAttribute("festivals", festivals.findAll());

		return "festivals";
	}

	@GetMapping("/")
	String allFestivals(Model model) {
		model.addAttribute("festivals", festivals.findAll());
		model.addAttribute("festival_form", new FestivalForm());

		return "welcome";
	}
	@PostMapping("/")
	String addFestivals(@ModelAttribute @Valid FestivalForm festivalForm, Errors errors,
					   RedirectAttributes redirectAttributes) {

		boolean result = false;

		Festival festival = festivalForm.toFestival();

		if (!errors.hasErrors()) {
			result = festivals.save(festival) != null;
		}

		if (!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim erstellen des Festivals.");
		}

		return "redirect:/";
	}


	/**
	 *
	 * @param festivalId: id of the festival, passed in the URL
	 */
	@GetMapping("/festival/{festivalName}-{festivalId}")
	String festival(@PathVariable long festivalId, Model model) {
		Optional<Festival> festival = festivals.findById(festivalId);

		if (festival.isEmpty()) {
			return "redirect:/404";
		}

		model.addAttribute("festival", festival.get());

		return "festival_detail";
	}

	/**
	 * add a new festival
	 */
	@PreAuthorize("hasAuthority('FESTIVAL_MANAGER') or hasAuthority('MANAGER')")
	@GetMapping("/festival/add")
	String addFestival(Model model) {
		model.addAttribute("festival_form", new FestivalForm());

		return "festival_add";
	}

	/**
	 *
	 * @param festivalForm: form the user filed with information about the new
	 *                      festival
	 * @param errors:       errors from validation of festival data
	 */
	@PostMapping("/festival/add")
	String addFestival(@ModelAttribute @Valid FestivalForm festivalForm, Errors errors,
			RedirectAttributes redirectAttributes) {

		boolean result = false;

		Festival festival = festivalForm.toFestival();

		if (!errors.hasErrors()) {
			result = festivals.save(festival) != null;
		}

		if (!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim erstellen des Festivals.");
		}

		return "redirect:/";
	}

	/**
	 *
	 * @param festivalId: id of the festival
	 */
	@PreAuthorize("hasAuthority('FESTIVAL_MANAGER') or hasAuthority('MANAGER') ")
	@GetMapping("/festival/{festivalName}-{festivalId}/edit")
	String editFestival(@PathVariable long festivalId, Model model) {
		Optional<Festival> festival = festivals.findById(festivalId);
		;

		if (festival.isEmpty()) {
			return "redirect:/404";
		}

		model.addAttribute("festival_form", festival.get());

		return "festival_edit";
	}

	/**
	 *
	 * @param festivalForm: form with the new data
	 * @param errors:       errors from validation of the festival data
	 */
	@PostMapping("/festival/edit")
	String editFestival(@ModelAttribute("festival_form") @Valid FestivalForm festivalForm, Errors errors,
			RedirectAttributes redirectAttributes) {

		boolean result = false;

		Festival festival = festivalForm.toFestival();

		if (!errors.hasErrors()) {
			result = festivals.update(festival) != null;
		}

		if (!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim editieren des Festivals.");
		}

		return "redirect:/festivals";
	}

	/**
	 *
	 * @param festivalId id of the festival
	 */
	@GetMapping("/festival/{festivalName}-{festivalId}/inventory")
	String festivalInventory(@PathVariable long festivalId, Model model) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);
		;

		if (festivalOptional.isEmpty()) {
			return "redirect:/404";
		}

		Festival festival = festivalOptional.get();

		model.addAttribute("festival", festival);
		model.addAttribute("stock", stock);

		return "festival_inventory";
	}

	@GetMapping("/festival/{festivalName}-{festivalId}/inventory/edit")
	String editFestivalInventory(@PathVariable long festivalId, Model model) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if (festivalOptional.isEmpty()) {
			return "redirect:/404";
		}

		Festival festival = festivalOptional.get();

		model.addAttribute("festivalId", festivalId);
		model.addAttribute("festivalName", festival.getName());
		model.addAttribute("festivalInventory", festival.getInventory());

		model.addAttribute("stock", stock);

		return "festival_inventory_edit";
	}

	@PostMapping("/festival/inventory/edit")
	String editFestivalInventory(@RequestParam long festivalId, @RequestParam InventoryItemIdentifier itemId,
			@RequestParam long quantity) {

		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if (festivalOptional.isEmpty()) {
			return "redirect:/404";
		}

		Festival festival = festivalOptional.get();

		Festival result = festivals.updateInventoryItem(festival, itemId, Quantity.of(quantity));

		return "redirect:/festival/" + festival.getName() + "-" + festivalId + "/inventory/edit";
	}

	@GetMapping("/festival/{festivalId}/delete")
	String deleteFestival(@PathVariable long festivalId) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		festivalOptional.ifPresent(festival -> festivals.delete(festival));

		return "redirect:/festivals";
	}
}
