package festivalmanager.inventory;

import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InventoryManager {
	private InventoryRepository inventory;

	public InventoryManager(InventoryRepository inventory) {
		this.inventory = inventory;
	}

	public void save(UniqueInventoryItem item) {
		inventory.save(item);
	}

	public Iterable<UniqueInventoryItem> findAll() {
		return inventory.findAll();
	}

	public Iterable<UniqueInventoryItem> findAllExcept(Set<InventoryItemIdentifier> exceptValues) {
		List<UniqueInventoryItem> list = new ArrayList<>();

		for(UniqueInventoryItem item : findAll()) {
			boolean found = false;

			for(InventoryItemIdentifier entry : exceptValues) {
				if(item.getId() != null && item.getId().equals(entry)) {
					found = true;
					break;
				}
			}

			if(!found) {
				list.add(item);
			}
		}

		return list;
	}

	public Optional<UniqueInventoryItem> findByItem(Item item) {
		return inventory.findByProduct(item);
	}

	public Optional<UniqueInventoryItem> findById(InventoryItemIdentifier identifier) {
		return inventory.findById(identifier);
	}

	public Iterable<UniqueInventoryItem> findByCategory(String category) {
		Iterable<UniqueInventoryItem> allItems = findAll();
		List<UniqueInventoryItem> categoryItems = new ArrayList<>();

		for(UniqueInventoryItem item : allItems) {
			Streamable<String> categories = item.getProduct().getCategories();

			for(String itemCategory : categories) {
				if(itemCategory.equals(category)) {
					categoryItems.add(item);
					break;
				}
			}
		}

		return categoryItems;
	}
}
