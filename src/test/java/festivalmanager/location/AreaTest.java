package festivalmanager.location;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AreaTest {

	@Test
	public void checkAllTheGetters(){
		Area area = new Area("zone", 12, 3, Type.PARK);

		assertEquals("zone", area.getZone());
		assertEquals(12, area.getMaxVisitors());
		assertEquals(3, area.getMaxStages());
		assertEquals(Type.PARK, area.getType());
		assertEquals(false, area.getStatus());
		assertEquals(0, area.getCurrVisitors());
	}

	@Test
	public void checkAllTheSetters(){
		Area area = new Area("Zone", 3, 12, Type.CAMPING);

		area.setLocationId(1111);
		area.setZone("enoZ");
		area.toggleLock();
		area.countVisitors(123);
		area.setMaxVisitors(5);
		area.setMaxStages(1);
		area.setType(Type.CATERING);

		assertEquals(1111, area.getLocationId());
		assertEquals(123, area.getCurrVisitors());
		assertEquals("enoZ", area.getZone());
		assertEquals(5, area.getMaxVisitors());
		assertEquals(1, area.getMaxStages());
		assertEquals(Type.CATERING, area.getType());
		assertEquals(true, area.getStatus());
	}
}
