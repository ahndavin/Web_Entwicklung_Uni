package festivalmanager.location;

public class LocationForm extends Location {
	public LocationForm(String name, String address, Integer price, Integer maxVisitors, String thumbnail, String groundPlan) {
		super(name, address, price, maxVisitors, thumbnail, groundPlan);
	}

	public Location toLocation() {
		return new Location(getName(), getAddress(), getPrice(), getMaxVisitors(), getThumbnail(), getGroundPlan());
	}
}