package festivalmanager.inventory;

import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventoryManagerTest {
	@Autowired InventoryManager inventory;
	@Autowired Catalog<Item> catalog;

	@Test
	public void canSaveItem() {
		Item item = saveTestItem(true);

		Optional<UniqueInventoryItem> uniqueInventoryItemOptional = inventory.findByItem(item);

		assertTrue(uniqueInventoryItemOptional.isPresent());
	}

	@Test
	public void canFindAllItemsExceptGivenSet() {
		Item item = saveTestItem(true);

		Optional<UniqueInventoryItem> uniqueInventoryItemOptional = inventory.findByItem(item);

		assertTrue(uniqueInventoryItemOptional.isPresent());

		UniqueInventoryItem uniqueInventoryItem = uniqueInventoryItemOptional.get();

		Set<InventoryItemIdentifier> idSet = new HashSet<>();
		idSet.add(uniqueInventoryItem.getId());

		Iterable<UniqueInventoryItem> items = inventory.findAllExcept(idSet);

		for(UniqueInventoryItem listItem : items) {
			assertNotEquals(listItem.getId(), uniqueInventoryItem.getId());
		}
	}

	@Test
	public void canFindItemsOutOfStock() {
		Item item = saveTestItem(false);

		Iterable<Item> notInStockItems = inventory.findAllNotInStock();

		boolean foundItem = false;
		for(Item notInStockItem : notInStockItems) {
			if(notInStockItem.getId() != null && notInStockItem.getId().equals(item.getId())) {
				foundItem = true;
				break;
			}
		}

		assertTrue(foundItem);
	}

	@Test
	public void canDeleteItem() {
		Item item = saveTestItem(true);

		Optional<UniqueInventoryItem> uniqueInventoryItemOptional = inventory.findByItem(item);

		assertTrue(uniqueInventoryItemOptional.isPresent());

		UniqueInventoryItem uniqueInventoryItem = uniqueInventoryItemOptional.get();

		inventory.delete(uniqueInventoryItem);

		assertTrue(inventory.findById(uniqueInventoryItem.getId()).isEmpty());
	}

	private Item saveTestItem(boolean saveToInventory) {
		Item item = catalog.save(new Item(
				"test",
				Money.of(12.00, "EUR"),
				Money.of(5.00, "EUR"),
				Quantity.of(10, Metric.UNIT),
				new String[]{ "test" }
		));

		if(saveToInventory) {
			inventory.save(new UniqueInventoryItem(item, Quantity.of(10, Metric.UNIT)));
		}

		return item;
	}
}
