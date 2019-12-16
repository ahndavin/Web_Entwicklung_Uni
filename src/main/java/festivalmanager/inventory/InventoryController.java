package festivalmanager.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {
	private InventoryManager inventory;

	public InventoryController(InventoryManager inventory) {
		this.inventory = inventory;
	}


	@GetMapping("/inventory")
	public String inventory(Model model) {
		model.addAttribute("inventory", inventory.findAll());

		return "inventory";
	}
}
