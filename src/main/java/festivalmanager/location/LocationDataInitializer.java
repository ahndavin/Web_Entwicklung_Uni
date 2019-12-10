package festivalmanager.location;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(20)
public class LocationDataInitializer implements DataInitializer {
	private LocationManager locations;

	public LocationDataInitializer(LocationManager locations) {
		Assert.notNull(locations, "Locations must not be null!");
		this.locations = locations;
	}

	@Override
	public void initialize() {
		if (locations.findAll().iterator().hasNext()) {
			return;
		}

		locations.save(new Location("Dresden", "blablastr. 10, 01069, Dresden", 1000, "/img/location/thumbnail/Dresden.jpg", "/img/location/ground_plan/Dresden.jpg"));
		locations.save(new Location("Berlin", "blablastr. 50, 01069, Berlin", 2000, "/img/location/thumbnail/Berlin.jpg", "/img/location/ground_plan/Berlin.jpg"));
	}
}