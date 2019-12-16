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

	@SuppressWarnings("deprecation")
	@Override
	public void initialize() {
		if (locationManager.findAll().iterator().hasNext()) {
			return;
		}

		locationManager.save(new Location("Dresden", "blablastr. 10, 01069, Dresden", 1000, "/img/location/thumbnail/Dresden.jpg", "/img/location/ground_plan/Dresden.jpg"));
		locationManager.findAll().get(0).addArea(new Area("D", 1000, 0, Type.CAMPING));
		
		locationManager.findAll().get(0).addArea(new Area("B", 700, 4, Type.STAGE));
		locationManager.findAll().get(0).getAllAreas().get(1).addStage(new Stage("first", "/img/location/lineup_poster/first.jpg"));
		//locationManager.findAll().get(0).getAllAreas().get(1).getAllStages().get(0).addLineup(new Lineup(new Date(2019, 12, 22, 18, 00),
		//		new Contract("500", "Max", true, "5", "50", "1000")));
		//locationManager.findAll().get(0).getAllAreas().get(1).getAllStages().get(0).addLineup(new Lineup(new Date(2019, 12, 22, 19, 00),
				//new Contract("1000", "John", true, "10", "100", "2000")));
		
		locationManager.findAll().get(0).getAllAreas().get(1).addStage(new Stage("second", "/img/location/lineup_poster/second.jpg"));
		locationManager.findAll().get(0).getAllAreas().get(1).addStage(new Stage("third", "/img/location/lineup_poster/third.jpg"));
		
		locationManager.findAll().get(0).addArea(new Area("EW", 500, 0, Type.PARK));
		locationManager.findAll().get(0).addArea(new Area("C", 1500, 0, Type.CATERING));
		
		
		locationManager.save(new Location("Berlin", "blablastr. 50, 01069, Berlin", 2000, "/img/location/thumbnail/Berlin.jpg", "/img/location/ground_plan/Berlin.jpg"));
		locationManager.findAll().get(1).addArea(new Area("6-10", 500, 5, Type.STAGE));
		locationManager.findAll().get(1).getAllAreas().get(0).addStage(new Stage("Erste", "/img/location/lineup_poster/Erste.jpg"));
		locationManager.findAll().get(1).getAllAreas().get(0).addStage(new Stage("Zweite", "/img/location/lineup_poster/Zweite.jpg"));
		//locationManager.findAll().get(1).getAllAreas().get(0).getAllStages().get(0).addLineup(new Lineup(new Date(),
		//		new Contract("500", "John", true, "5", "50", "1000")));
	}
}