package festivalmanager.festival;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class FestivalManagerTest {
	@Autowired FestivalManager festivals;

	@Test
	public void canSaveFestival() {
		Festival festivalToSave = new Festival(
				"test",
				"test",
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
	public void canPreventCreationOfTwoFestivalsAtSameDateAndLocation() {
		long startFestivalCount = festivals.getCount();

		Festival f1 = new Festival(
				"test 1",
				"test",
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
				"test",
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
}
