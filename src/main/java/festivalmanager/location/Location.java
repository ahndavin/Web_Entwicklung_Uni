package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;

public class Location {
	private String name;
	private boolean isBooked;
	private Integer currVisitors;
	private Integer maxVisitors;
	private Layout groundPlan;
	private List<Area> areas;
	
	public Location(String name, Integer maxVisitors, Layout groundPlan) {
		this.name = name;
		this.maxVisitors = maxVisitors;
		this.groundPlan = groundPlan;
		this.isBooked = false;
		this.currVisitors = 0;
		this.areas = new LinkedList<Area>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean toggleBook() {
		return isBooked = !isBooked;
	}
	
	public boolean getStatus() {
		return isBooked;
	}
	
	public Integer countVisitors(int visitors) {		
		return currVisitors += visitors;
	}
	
	public Integer getCurrVisitors() {
		return currVisitors;
	}
	
	public Integer getMaxVisitors() {
		return maxVisitors;
	}
	
	public Layout editGroundPlan() {
		//TODO
		return null;
	}
	
	public Layout getGroundPlan() {
		return groundPlan;
	}
		
	public List<Area> addArea(Area area){
		if(areas.contains(area))
			return null;
		
		areas.add(area);
		return areas;
	}
	
	public Area getArea(Area area) {
		int index = areas.indexOf(area);
		
		if(index == -1)
			return null;
		
		return areas.get(index);
	}
	
	public List<Area> removeArea(Area area) {
		if(!areas.remove(area))
			return null;
		
		return areas;
	}
	
	public List<Area> getAllAreas() {
		return areas;
	}
}
