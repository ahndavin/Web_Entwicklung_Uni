package festivalmanager.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Area {
	private @Id @GeneratedValue long id;
	private long locationId;
	private String zone;
	private boolean blocked;
	private Integer currVisitors;
	private Integer maxVisitors;
	private Integer maxStages;
	private Type type;
	
	@SuppressWarnings("unused")
	private Area() {}
	
	public Area(String zone, Integer maxVisitors, Integer maxStages, Type type) {
		this.zone = zone;
		this.maxVisitors = maxVisitors;
		this.maxStages = maxStages;
		this.blocked = false;
		this.currVisitors = 0;
		this.type = type;
	}
	
	public long getId() {
		return id;
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
	
	public boolean toggleLock() {
		blocked = !blocked;
		
		return blocked;
	}
	
	public Integer countVisitors(Integer visitors) {
		if(currVisitors + visitors < 0)
			return null;
		
		return currVisitors += visitors;
	}
	
	public Integer getCurrVisitors() {
		return currVisitors;
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
	
	public String toString() {
		return zone;
	}
}
