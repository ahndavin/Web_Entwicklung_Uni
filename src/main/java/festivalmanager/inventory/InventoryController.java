package festivalmanager.inventory;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class InventoryController {
	private InventoryManager inventory;
	private Catalog<Item> catalog;
	private FestivalManager festivals;

	public InventoryController(InventoryManager inventory, Catalog<Item> catalog, FestivalManager festivals) {
		this.inventory = inventory;
		this.catalog = catalog;
		this.festivals = festivals;
	}


	@GetMapping("/inventory")
	public String inventory(Model model) {
		model.addAttribute("inventory", inventory);

		return "inventory";
	}

	@GetMapping("/inventory/add")
	public String add() {
		return "inventory_add_item";
	}

	@PostMapping("/inventory/add")
	public String add(@RequestParam String name,
					  @RequestParam float price,
					  @RequestParam float cost,
					  @RequestParam int minimalQuantity,
					  @RequestParam int quantity,
					  @RequestParam String category) {

		Item item = catalog.save(
				new Item(
						name,
						Money.of(price, "EUR"),
						Money.of(cost, "EUR"),
						Quantity.of(minimalQuantity, Metric.UNIT),
						new String[]{ category }
				)
		);

		if(quantity > 0) {
			inventory.save(new UniqueInventoryItem(item, Quantity.of(quantity, Metric.UNIT)));
		}

		return "redirect:/inventory";
	}

	@PostMapping("/inventory/addExisting")
	public String add(@RequestParam ProductIdentifier id,
					  @RequestParam int amount) {

		Optional<Item> itemOptional = catalog.findById(id);

		if(itemOptional.isPresent() && amount > 0) {
			inventory.save(new UniqueInventoryItem(itemOptional.get(), Quantity.of(amount, Metric.UNIT)));
		}

		return "redirect:/inventory/edit";
	}

	@GetMapping("inventory/edit")
	public String edit(Model model) {
		model.addAttribute("inventory", inventory);

		return "inventory_edit";
	}

	@PostMapping("inventory/edit")
	public String edit(@RequestParam InventoryItemIdentifier id, @RequestParam int amount) {
		Optional<UniqueInventoryItem> itemOptional = inventory.findById(id);

		if(itemOptional.isPresent()) {
			UniqueInventoryItem	item = itemOptional.get();
			Quantity quantity = Quantity.of(amount, Metric.UNIT);

			inventory.setQuantity(item, quantity);
		}

		return "redirect:/inventory/";
	}
}
