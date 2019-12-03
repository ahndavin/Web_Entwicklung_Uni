package festivalmanager.inventory;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryManager {
	private InventoryRepository inventory;

	public InventoryManager(InventoryRepository inventory) {
		this.inventory = inventory;
	}

	public void save(Item item) {
		inventory.save(item);
	}

	public Iterable<Item> findAll() {
		return inventory.findAll();
	}

	public Optional<Item> findById(Long id) {
		return inventory.findById(id);
	}
}
