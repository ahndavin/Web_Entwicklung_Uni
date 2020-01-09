package festivalmanager.location;

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
		if (locationManager.findAllLocations().iterator().hasNext()) {
			return;
		}
		
		Location location1 = new Location("Dresden", "blablastr. 10, 01069, Dresden", 1000, "/img/location/thumbnail/Dresden.jpg", "/img/location/ground_plan/Dresden.jpg");
		Area area1 = new Area("D", 1000, 4, Type.STAGE);
		Area area2 = new Area("B", 700, null, Type.CAMPING);
		Stage stage1 = new Stage("first", "/img/location/lineup_poster/first.jpg");
		Stage stage2 = new Stage("second", "/img/location/lineup_poster/second.jpg");
		Stage stage3 = new Stage("third", "/img/location/lineup_poster/third.jpg");
		
		locationManager.save(location1);
		locationManager.save(area1);
		locationManager.save(area2);
		locationManager.save(stage1);
		locationManager.save(stage2);
		locationManager.save(stage3);
		
		area1.setLocationId(location1.getId());
		area2.setLocationId(location1.getId());
		stage1.setAreaId(area1.getId());
		stage2.setAreaId(area1.getId());
		stage3.setAreaId(area1.getId());
		//locationManager.findAll().get(0).getAllAreas().get(1).getAllStages().get(0).addLineup(new Lineup(new Date(2019, 12, 22, 18, 00),
		//		new Contract("500", "Max", true, "5", "50", "1000")));
		//locationManager.findAll().get(0).getAllAreas().get(1).getAllStages().get(0).addLineup(new Lineup(new Date(2019, 12, 22, 19, 00),
				//new Contract("1000", "John", true, "10", "100", "2000")));
		
		
//		locationManager.findAll().get(0).save(new Area("EW", 500, 0, Type.PARK));
//		locationManager.findAll().get(0).save(new Area("C", 1500, 0, Type.CATERING));
//		
//		
//		locationManager.save(new Location("Berlin", "blablastr. 50, 01069, Berlin", 2000, "/img/location/thumbnail/Berlin.jpg", "/img/location/ground_plan/Berlin.jpg"));
//		locationManager.findAll().get(1).save(new Area("6-10", 500, 5, Type.STAGE));
//		locationManager.findAll().get(1).findAll().get(0).save(new Stage("Erste", "/img/location/lineup_poster/Erste.jpg"));
//		locationManager.findAll().get(1).findAll().get(0).save(new Stage("Zweite", "/img/location/lineup_poster/Zweite.jpg"));
//		//locationManager.findAll().get(1).getAllAreas().get(0).getAllStages().get(0).addLineup(new Lineup(new Date(),
//		//		new Contract("500", "John", true, "5", "50", "1000")));
	}
}