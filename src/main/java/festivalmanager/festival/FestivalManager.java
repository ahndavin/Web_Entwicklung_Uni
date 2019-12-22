package festivalmanager.festival;

import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FestivalManager {
	private final FestivalRepository festivalRepository;
	private final InventoryManager inventory;

	public FestivalManager(FestivalRepository festivalRepository, InventoryManager inventory) {
		this.festivalRepository = festivalRepository;
		this.inventory = inventory;
	}

	public Iterable<Festival> findAll() {
		return festivalRepository.findAll();
	}

	public Optional<Festival> findById(long id) {
		return festivalRepository.findById(id);
	}

	public Festival save(Festival festival) {
		Iterable<Festival> festivals = festivalRepository.findAll();

		for(Festival f : festivals) {
			if(festival.getId() == f.getId()) {
				continue;
			}

			if(Festival.areAtTheSameTimeAndPlace(festival, f)) {
				return null;
			}
		}

		return festivalRepository.save(festival);
	}

	public Festival update(Festival festival) {
		Optional<Festival> festivalOptional = festivalRepository.findById(festival.getId());

		if(festivalOptional.isPresent()) {
			Festival f = festivalOptional.get();

			festival.editPlan().addAll(f.editPlan());
			festival.editInventory().putAll(f.editInventory());

			return save(festival);
		}

		return null;
	}

	public Festival updatePlan(Festival festival) {
		Optional<Festival> festivalOptional = festivalRepository.findById(festival.getId());

		if(festivalOptional.isPresent()) {
			Festival f = festivalOptional.get();

			f.editPlan().clear();
			f.editPlan().addAll(festival.editPlan());

			return save(f);
		}

		return null;
	}

	public Festival updateInventoryItem(Festival festival, InventoryItemIdentifier itemId, Quantity newQuantity) {
		if(newQuantity.isLessThan(Quantity.NONE)) {
			return null;
		}

		Quantity oldQuantity = festival.editInventory().getOrDefault(itemId, Quantity.NONE);

		festival.editInventory().put(itemId, newQuantity);

		Optional<UniqueInventoryItem> itemOptional = inventory.findById(itemId);

		if(itemOptional.isPresent()) {
			UniqueInventoryItem item = itemOptional.get();

			if(item.getQuantity().add(oldQuantity).isLessThan(newQuantity)) {
				return null;
			} else if(newQuantity.equals(Quantity.NONE)) {
				festival.editInventory().remove(itemId);
			}

			if(oldQuantity.isLessThan(newQuantity)) {
				item.decreaseQuantity(newQuantity.subtract(oldQuantity));
			} else if(oldQuantity.isGreaterThan(newQuantity)) {
				item.increaseQuantity(oldQuantity.subtract(newQuantity));
			}

		} else {
			return null;
		}

		return save(festival);
	}

	public void delete(Festival festival) {
		festivalRepository.delete(festival);
	}

	public long getCount() {
		return festivalRepository.count();
	}
}
