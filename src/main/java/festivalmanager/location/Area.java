package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;

public class Area {
	private String zone;
	private boolean blocked;
	private Integer currVisitors;
	private Integer maxVisitors;
	private Integer maxStages;
	private Type type;
	private List<Stage> stages;
	
	public Area(String zone, Integer maxVisitors, Integer maxStages, Type type) {
		this.zone = zone;
		this.maxVisitors = maxVisitors;
		this.maxStages = maxStages;
		this.blocked = false;
		this.currVisitors = 0;
		this.type = type;
		
		this.stages = null;
		if(this.type == Type.STAGE)
			this.stages = new LinkedList<Stage>();
	}
	
	public String getZone() {
		return zone;
	}
	
	public boolean toggleLock() {
		blocked = !blocked;
		
		return blocked;
	}
	
	public boolean getStatus() {
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
	
	public Integer getMaxStages() {
		return maxStages;
	}
	
	public Type getType() {
		return type;
	}
	
	public List<Stage> addStage(Stage stage){
		if((maxStages <= stages.size()) || (stages.contains(stage)))
			return null;
		
		stages.add(stage);
		
		return stages;
	}
	
	public Stage getStage(Stage stage) {
		int index = stages.indexOf(stage);
		
		if(index == -1)
			return null;
		
		return stages.get(index);
	}
	
	public List<Stage> removeStage(Stage stage) {
		if(!stages.remove(stage))
			return null;
		
		return stages;
	}
	
	public List<Stage> getAllStages() {
		return stages;
	}
}
