package festivalmanager.location;

public class LocationForm {
	private long id;
	private String name;
	private String address;
	private boolean isBooked;
	private Integer price;
	private Integer currVisitors;
	private Integer maxVisitors;
	private String thumbnail;
	private String groundPlan;
	
	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	public String getName() {		
		return name;
	}
	
	public String setName(String name) {		
		return this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String setAddress(String address) {		
		return this.address = address;
	}
	
	public boolean getStatus() {
		return isBooked;
	}
	
	public boolean setStatus(boolean isBooked) {
		return this.isBooked = isBooked;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer setPrice(Integer price) {
		return this.price = price;
	}
	
	public Integer getCurrVisitors() {
		return currVisitors;
	}
	
	public Integer setCurrVisitors(Integer currVisitors) {
		return this.currVisitors = currVisitors;
	}
	
	public Integer getMaxVisitors() {
		return maxVisitors;
	}
	
	public Integer setMaxVisitors(Integer maxVisitors) {
		return this.maxVisitors = maxVisitors;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String setThumbnail(String thumbnail) {
		return this.thumbnail = thumbnail;
	}
	
	public String getGroundPlan() {
		return groundPlan;
	}
	
	public String setGroundPlan(String groundPlan) {
		return this.groundPlan = groundPlan;
	}
	
	public Location toLocation() {
		Location location = new Location(name, address, price, maxVisitors, thumbnail, groundPlan);

		return location;
	}
}