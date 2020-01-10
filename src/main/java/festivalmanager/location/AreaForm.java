package festivalmanager.location;

public class AreaForm {
	private long id;
	private long locationId;
	private String zone;
	private boolean blocked;
	private Integer currVisitors;
	private Integer maxVisitors;
	private Integer maxStages;
	private Type type;
	
	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	public long getLocationId() {
		return locationId;
	}
	
	public long setLocationId(long locationId) {
		return this.locationId = locationId;
	}
	
	public String getZone() {
		return zone;
	}
	
	public String setZone(String zone) {
		return this.zone = zone;
	}
	
	public boolean getStatus() {
		return blocked;
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
	
	public Integer getMaxStages() {
		return maxStages;
	}
	
	public Integer setMaxStages(Integer maxStages) {
		return this.maxStages = maxStages;
	}
	
	public Type getType() {
		return type;
	}
	
	public Type setType(Type type) {
		return this.type = type;
	}
	
	public Area toArea() {
		return new Area(zone, maxVisitors, maxStages, type);
	}
}
