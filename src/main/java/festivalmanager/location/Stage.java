package festivalmanager.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stage {
	private @Id @GeneratedValue long id;
	private long areaId;
	private String name;
	private String poster;
	
	@SuppressWarnings("unused")
	private Stage() {}
	
	public Stage(String name, String poster) {
		this.name = name;
		this.poster = poster;
	}
	
	public long getId() {
		return id;
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
}
