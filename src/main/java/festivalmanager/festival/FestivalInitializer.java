

package festivalmanager.festival;


import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.quantity.Quantity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(30)
public class FestivalInitializer implements DataInitializer {
	private FestivalManager festivals;
	private InventoryManager inventory;

	public FestivalInitializer(FestivalManager festivals, InventoryManager inventory) {
		this.festivals = festivals;
		this.inventory = inventory;
	}

	@Override
	public void initialize() {
		if(festivals.findAll().iterator().hasNext()) {
			return;
		}

		Festival f = new Festival("Abriss", "Dresden", "2020-12-19","2019-12-20", 100, 50, 20, 40, 2, true);	

		f.editInventory().put(inventory.findAll().iterator().next().getId(), Quantity.of(5));

		festivals.save(f);

		festivals.save(new Festival("Abiball", "Leipzig", "2019-8-16", null, 1000, 400, 50, 100, 2, true));
		festivals.save(new Festival("Silvester", "Berlin", "2019-12-31", null, 1000, 400, 50, 100, 2, true));
	}
}