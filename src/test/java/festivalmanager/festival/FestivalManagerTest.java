package festivalmanager.festival;

import com.google.common.collect.Iterables;
import festivalmanager.inventory.InventoryManager;
import festivalmanager.inventory.Item;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class FestivalManagerTest {
	@Autowired FestivalManager festivals;
	@Autowired InventoryManager inventory;

	@Test
	public void canSaveFestival() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1980-01-01",
				"1980-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		long savedFestivalId = festivals.save(festivalToSave).getId();

		Optional<Festival> festivalOptional = festivals.findById(savedFestivalId);

		assertTrue(festivalOptional.isPresent());
		assertEquals(savedFestivalId, festivalOptional.get().getId());
	}

	@Test
	public void canDeleteFestival() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1985-01-01",
				"1985-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		Festival festival = festivals.save(festivalToSave);

		festivals.delete(festival);

		assertTrue(festivals.findById(festival.getId()).isEmpty());
	}

	@Test
	public void canPreventCreationOfTwoFestivalsAtSameDateAndLocation() {
		long startFestivalCount = festivals.getCount();

		Festival f1 = new Festival(
				"test 1",
				"Dresden",
				"2020-01-11",
				"2020-01-13",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		Festival f2 = new Festival(
				"test 2",
				"Dresden",
				"2020-01-12",
				"2020-01-12",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		festivals.save(f1);
		Festival savedF2 = festivals.save(f2);

		long endFestivalCount = festivals.getCount();

		assertNull(savedF2);
		assertEquals(1, endFestivalCount - startFestivalCount);
	}

	@Test
	public void canUpdateFestival() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1984-01-01",
				"1984-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		Festival festival = festivals.save(festivalToSave);
		festival.setName("other name than before");

		festivals.update(festival);

		Festival updatedFestival = festivals.findById(festival.getId()).get();

		assertEquals(festival.getName(), updatedFestival.getName());
	}

	@Test
	public void canUpdateFestivalPlan() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1983-01-01",
				"1983-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		Festival festival = festivals.save(festivalToSave);
		festival.editPlan().add("test plan item");

		festivals.save(festival);

		Festival updatedFestival = festivals.findById(festival.getId()).get();

		assertEquals(festival.getPlan(), updatedFestival.getPlan());
	}

	@Test
	public void canFindFestivalsSortedByDate() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1983-01-01",
				"1983-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		festivals.save(festivalToSave);

		Festival festivalToSave2 = new Festival(
				"test2",
				"Dresden",
				"1982-01-01",
				"1982-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		festivals.save(festivalToSave2);


		Festival[] sortedFestivals = Iterables.toArray(festivals.findAllSortedByDate(), Festival.class);

		System.out.println(festivals.findAllSortedByDate());

		for(int i = 0; i < sortedFestivals.length; i++) {
			for(int y = i + 1; y < sortedFestivals.length; y++) {
				long startDateFestival1 = sortedFestivals[i].getDate()[Festival.START_DATE].getTime();
				long startDateFestival2 = sortedFestivals[y].getDate()[Festival.START_DATE].getTime();

				assertTrue(startDateFestival1 < startDateFestival2);
			}
		}
	}

	@Test
	public void canAddAndBuyInventoryItem() {
		Festival festivalToSave = new Festival(
				"test",
				"Dresden",
				"1981-01-01",
				"1981-01-01",
				100,
				100,
				50.0f,
				100.0f,
				1000,
				true
		);

		Festival festival = festivals.save(festivalToSave);

		Item item = inventory.addItem(
				"test item",
				5.00f,
				1.00f,
				20,
				"food",
				10
		);

		InventoryItemIdentifier itemId = inventory.findByItem(item).get().getId();

		Quantity quantity = Quantity.of(10);

		festivals.updateInventoryItem(festival, itemId, quantity);

		Festival updatedFestival = festivals.findById(festival.getId()).get();

		assertEquals(updatedFestival.getInventory().get(itemId).getAmount().intValue(), quantity.getAmount().intValue());



		Quantity boughtQuantity = Quantity.of(5);

		festivals.buyInventoryItem(festival, itemId, boughtQuantity);

		updatedFestival = festivals.findById(festival.getId()).get();

		assertEquals(updatedFestival.getInventory().get(itemId).getAmount().intValue(), quantity.subtract(boughtQuantity).getAmount().intValue());
	}
}
