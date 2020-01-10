package festivalmanager.festival;

import festivalmanager.inventory.InventoryManager;
import festivalmanager.location.LocationManager;
import festivalmanager.staff.Account;
import festivalmanager.staff.AccountManager;
import festivalmanager.staff.CreationForm;
import festivalmanager.staff.MessageManager;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class FestivalController {
	private FestivalManager festivals;
	private InventoryManager stock;
	private LocationManager locations;
	private final AccountManager accountManager;
	private final MessageManager messageManager;


	public FestivalController(FestivalManager festivals, InventoryManager stock, AccountManager accountManager, LocationManager locations, MessageManager messageManager) {
		this.festivals = festivals;
		this.stock = stock;
		this.accountManager = accountManager;
		this.locations = locations;
		this.messageManager = messageManager;
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
	String everyThing(Model model , CreationForm form, Errors result, @LoggedIn Optional<UserAccount> userAccount) {
		model.addAttribute("festivals", festivals.findAllSortedByDate());
		model.addAttribute("festival_form", new FestivalForm());
		model.addAttribute("accountList", accountManager.findAll());
		model.addAttribute("accountManager", accountManager);
		model.addAttribute("form", form);
		model.addAttribute("error", result);
		model.addAttribute("locations", locations.findAllLocations());
		Assert.notNull(messageManager, "MessageManagement must not be null");

		if(userAccount.isPresent()) {
			Account account = accountManager.findByUserAccount(userAccount.get()).get();
			model.addAttribute("Account", account);
		}

		model.addAttribute("messageManagement", messageManager);
		model.addAttribute("messageList", messageManager.findAll());


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
	@PreAuthorize("hasAuthority('FESTIVAL_MANAGER') or hasAuthority('MANAGER') or hasRole('MANAGER') or hasRole('FESTIVAL_MANAGER')")
	@GetMapping("/festival/add")
	String addFestival(Model model) {
		model.addAttribute("festival_form", new FestivalForm());
		model.addAttribute("locations", locations.findAllLocations());

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

		if (!errors.hasErrors() && !festival.hasErrors()) {
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
	@PreAuthorize("hasAuthority('FESTIVAL_MANAGER') or hasAuthority('MANAGER') or hasRole('MANAGER') or hasRole('FESTIVAL_MANAGER')")
	@GetMapping("/festival/{festivalName}-{festivalId}/edit")
	String editFestival(@PathVariable long festivalId, Model model) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if (festivalOptional.isEmpty()) {
			return "redirect:/404";
		}

		Festival festival = festivalOptional.get();

		if(festival.getDate()[Festival.START_DATE].getTime() < new Date().getTime()) {
			return "redirect:/#festivals";
		}
		
		model.addAttribute("festival_form", festival);
		model.addAttribute("locations", locations.findAllLocations());

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

		if (!errors.hasErrors() && !festival.hasErrors()) {
			result = festivals.update(festival) != null;
		}

		if (!result) {
			redirectAttributes.addFlashAttribute("error", "Fehler beim editieren des Festivals.");
		}

		return "redirect:/#festivals";
	}

	/**
	 *
	 * @param festivalForm: form with the new data
	 * @param errors:       errors from validation of the festival data
	 */


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
			@RequestParam String quantity) {

		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if (festivalOptional.isEmpty()) {
			return "redirect:/404";
		}

		Festival festival = festivalOptional.get();

		if(quantity.equals("")) {
			return "redirect:/festival/" + festival.getName() + "-" + festivalId + "/inventory/edit";
		}

		long quantityParsed = Long.parseLong(quantity);

		Festival result = festivals.updateInventoryItem(festival, itemId, Quantity.of(quantityParsed));

		return "redirect:/festival/" + festival.getName() + "-" + festivalId + "/inventory/edit";
	}

	@GetMapping("/festival/{festivalId}/delete")
	String deleteFestival(@PathVariable long festivalId) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		festivalOptional.ifPresent(festival -> festivals.delete(festival));

		return "redirect:/#festivals";
	}

	@GetMapping("festival/{festivalId}/buy")
	String buyItem(Model model, @PathVariable long festivalId) {
		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if(festivalOptional.isEmpty()) {
			return "redirect:/festivals";
		}

		Map<InventoryItemIdentifier, Quantity> festivalInventory =  festivalOptional.get().getInventory();
		Map<InventoryItemIdentifier, Quantity> festivalInventoryWithoutFurniture =  new HashMap<>();


		for(Map.Entry<InventoryItemIdentifier, Quantity> entry : festivalInventory.entrySet()) {
			Optional<UniqueInventoryItem> itemOptional = stock.findById(entry.getKey());

			if(itemOptional.isPresent()) {
				UniqueInventoryItem item = itemOptional.get();

				if(!item.getProduct().getCategories().toList().contains("furniture")) {
					festivalInventoryWithoutFurniture.put(entry.getKey(), entry.getValue());
				}
			}
		}

		model.addAttribute("festival", festivalOptional.get());
		model.addAttribute("festivalInventory", festivalInventoryWithoutFurniture);
		model.addAttribute("inventory", stock);

		return "festival_catering";
	}

	@PostMapping("festival/buy")
	String buyItem(@RequestParam long festivalId,
				   @RequestParam InventoryItemIdentifier itemId,
				   @RequestParam long amount) {

		Optional<Festival> festivalOptional = festivals.findById(festivalId);

		if(festivalOptional.isEmpty()) {
			return "redirect:/festivals";
		}

		festivals.buyInventoryItem(festivalOptional.get(), itemId, Quantity.of(amount));

		return "redirect:/festival/" + festivalId + "/buy";
	}
}
