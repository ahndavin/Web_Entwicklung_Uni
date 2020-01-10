package festivalmanager.location;

public class StageForm {
	private long id;
	private long areaId;
	private String name;
	private String poster;
	
	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	public long getAreaId() {
		return areaId;
	}
	
	public long setAreaId(long areaId) {
		return this.areaId = areaId;
	}
	
	public String getName() {
		return name;
	}
	
	public String setName(String name) {
		return this.name = name;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public String setPoster(String poster) {
		return this.poster = poster;
	}
	
	public Stage toStage() {
		return new Stage(name, poster);
	}
}
