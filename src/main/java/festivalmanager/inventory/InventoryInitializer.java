package festivalmanager.inventory;

import org.salespointframework.catalog.Catalog;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class InventoryInitializer implements DataInitializer {
	private Catalog<Item> catalog;
	private InventoryRepository inventory;

	public InventoryInitializer(Catalog<Item> catalog, InventoryRepository inventory) {
		this.catalog = catalog;
		this.inventory = inventory;
	}

	@Override
	public void initialize() {
		if(inventory.findAll().iterator().hasNext()) {
			return;
		}

		Iterable<Item> items =  catalog.findAll();

		for(Item item : items) {
			inventory.save(new UniqueInventoryItem(item, Quantity.of(10)));
		}
	}
}
