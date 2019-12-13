package festivalmanager.location;

import festivalmanager.contract.*;
import java.util.Date;
import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(20)
public class LocationDataInitializer implements DataInitializer {
	private LocationManager locationManager;

	public LocationDataInitializer(LocationManager locationManager) {
		Assert.notNull(locationManager, "Locations must not be null!");
		this.locationManager = locationManager;
	}

	@Override
	public void initialize() {
		if (locationManager.findAll().iterator().hasNext()) {
			return;
		}

		locationManager.save(new Location("Dresden", "blablastr. 10, 01069, Dresden", 1000, "/img/location/thumbnail/Dresden.jpg", "/img/location/ground_plan/Dresden.jpg"));
		locationManager.findAll().get(0).addArea(new Area("B", 500, 2, Type.STAGE));
		locationManager.findAll().get(0).getAllAreas().get(0).addStage(new Stage("Erste"));
		locationManager.findAll().get(0).getAllAreas().get(0).addStage(new Stage("Zweite"));
		locationManager.findAll().get(0).getAllAreas().get(0).getAllStages().get(0).addArtist
		(
			new Contract("500", "John", true, "5", "50", "1000"),
			new Date()
		);
		
		
		locationManager.save(new Location("Berlin", "blablastr. 50, 01069, Berlin", 2000, "/img/location/thumbnail/Berlin.jpg", "/img/location/ground_plan/Berlin.jpg"));
		locationManager.findAll().get(1).addArea(new Area("Hof", 1000, 0, Type.CAMPING));
		
		locationManager.findAll().get(1).addArea(new Area("1", 700, 4, Type.STAGE));
		locationManager.findAll().get(1).getAllAreas().get(1).addStage(new Stage("first"));
		locationManager.findAll().get(1).getAllAreas().get(1).addStage(new Stage("second"));
		locationManager.findAll().get(1).getAllAreas().get(1).addStage(new Stage("third"));
		
		locationManager.findAll().get(1).addArea(new Area("12-19", 500, 0, Type.PARK));
		locationManager.findAll().get(1).addArea(new Area("6-10", 1500, 0, Type.CATERING));
	}
}