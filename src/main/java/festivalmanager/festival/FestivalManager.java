package festivalmanager.festival;

import festivalmanager.contract.Contract;
import festivalmanager.economics.EconomicManager;
import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManager;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class FestivalManager {
	private final FestivalRepository festivalRepository;
	private final InventoryManager inventory;
	private final LocationManager locations;
	private final EconomicManager economics;

	public FestivalManager(FestivalRepository festivalRepository,
						   InventoryManager inventory,
						   LocationManager locations,
						   EconomicManager economics) {

		this.festivalRepository = festivalRepository;
		this.inventory = inventory;
		this.locations = locations;
		this.economics = economics;
	}

	public Iterable<Festival> findAll() {
		return festivalRepository.findAll();
	}

	public Optional<Festival> findById(long id) {
		return festivalRepository.findById(id);
	}

	public Festival findByName(String name) {
		return festivalRepository.findByName(name);
	}

	public Festival save(Festival festival) {
		Iterable<Location> locationList = locations.findAll();

		boolean foundLocation = false;
		for(Location location : locationList) {
			if(festival.getLocation().equals(location.getName())) {
				foundLocation = true;
				break;
			}
		}

		if(!foundLocation) {
			return null;
		}

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
			festival.getEconomicList().addAll(f.getEconomicList());
			festival.getContractList().getList().addAll(f.getContractList().getList());

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

			boolean isFurniture = item.getProduct().getCategories().toList().contains("furniture");

			if(oldQuantity.isLessThan(newQuantity)) {
				Quantity difference = newQuantity.subtract(oldQuantity);

				item.decreaseQuantity(difference);

				if(!isFurniture) {
					economics.add(
							((Item) item.getProduct()).getCost().multiply(difference.getAmount().intValue()).negate(),
							"added " + difference + "x " + item.getProduct().getName() + " to stock ",
							festival
					);
				}
			} else if(oldQuantity.isGreaterThan(newQuantity)) {
				Quantity difference = oldQuantity.subtract(newQuantity);

				item.increaseQuantity(difference);

				if(!isFurniture) {
					economics.add(
							((Item) item.getProduct()).getCost().multiply(difference.getAmount().intValue()),
							"removed " + difference + "x " + item.getProduct().getName() + " from stock ",
							festival
					);
				}
			}

		} else {
			return null;
		}

		return save(festival);
	}

	public void buyInventoryItem(Festival festival, InventoryItemIdentifier itemId, Quantity quantity) {
		Optional<UniqueInventoryItem> uniqueInventoryItemOptional = inventory.findById(itemId);

		if(uniqueInventoryItemOptional.isPresent()) {
			UniqueInventoryItem item = uniqueInventoryItemOptional.get();

			Quantity newQuantity = festival.getInventory().get(itemId).subtract(quantity);

			if(newQuantity.isLessThan(Quantity.NONE)) {
				return;

			} else if(newQuantity.isLessThan(((Item) item.getProduct()).getMinimalQuantity())) {
				// TODO send msg
			}

			economics.add(
					(Money) item.getProduct().getPrice().multiply(quantity.getAmount().intValue()),
					"sold " + quantity + "x " + item.getProduct().getName(),
					festival
			);
			
			if(newQuantity.isGreaterThan(Quantity.NONE)) {
				festival.getInventory().put(itemId, newQuantity);
			} else {
				festival.getInventory().remove(itemId);
			}

			save(festival);
		}
	}

	public Iterable<Festival> findAllSortedByDate() {
		List<Festival> festivalList = (List<Festival>) findAll();

		festivalList.sort(new Comparator<Festival>() {
			@Override
			public int compare(Festival festival1, Festival festival2) {
				Long date1 = festival1.getDate()[Festival.START_DATE].getTime();
				Long date2 = festival2.getDate()[Festival.START_DATE].getTime();
				
				return date1.compareTo(date2);
			}
		});

		return festivalList;
	}

	public void delete(Festival festival) {
		festivalRepository.delete(festival);
	}

	public long getCount() {
		return festivalRepository.count();
	}
}
