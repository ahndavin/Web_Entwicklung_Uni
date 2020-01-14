package festivalmanager.location;

public class AreaForm extends Area {
	public AreaForm(String zone, Integer maxVisitors, Integer maxStages, Type type) {
		super(zone, maxVisitors, maxStages, type);
	}

	public Area toArea() {
		return new Area(getZone(), getMaxVisitors(), getMaxStages(), getType());
	}
}
