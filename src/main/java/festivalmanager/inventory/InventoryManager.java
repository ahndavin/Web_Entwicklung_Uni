package festivalmanager.inventory;

import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryManager {
	private InventoryRepository inventory;

	public InventoryManager(InventoryRepository inventory) {
		this.inventory = inventory;
	}

	public void save(Item item, Quantity quantity) {
		inventory.save(new UniqueInventoryItem(item, quantity));
	}

	public Iterable<UniqueInventoryItem> findAll() {
		return inventory.findAll();
	}

	public Optional<UniqueInventoryItem> findByItem(Item item) {
		return inventory.findByProduct(item);
	}
}
