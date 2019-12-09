

package festivalmanager.festival;


import festivalmanager.inventory.InventoryRepository;
import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(30)
public class FestivalInitializer implements DataInitializer {
	private FestivalManager festivals;
	private InventoryRepository inventory;

	public FestivalInitializer(FestivalManager festivals, InventoryRepository inventory) {
		this.festivals = festivals;
		this.inventory = inventory;
	}

	@Override
	public void initialize() {
		if(festivals.findAll().iterator().hasNext()) {
			return;
		}

		Festival f = new Festival("Beispiel Festival 2019", "Dresden", "19.12.2019","20.12.2019", 100, 50, 20, 40, 2, true);

		f.editPlan().add("blabla");
		f.editPlan().add("lalala");

		f.editInventory().add(inventory.findAll().iterator().next());

		festivals.save(f);

		festivals.save(new Festival("Test Festival 2019", "Berlin", "18.12.2019", null, 1000, 400, 50, 100, 2, true));
	}
}