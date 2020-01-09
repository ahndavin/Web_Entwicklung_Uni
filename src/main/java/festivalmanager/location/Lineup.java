package festivalmanager.location;

import festivalmanager.contract.Contract;
import festivalmanager.festival.Festival;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lineup {
	private @Id @GeneratedValue long id;
	private long stageId;
	private String date;
	protected @OneToOne Festival festival;
	private @OneToOne Contract contract;

	@SuppressWarnings("unused")
	private Lineup() {}
	
	public Lineup(String date) {
		this.date = date;
	}
	
	public long getId() {
		return id;
	}
	
	public long getStageId() {
		return stageId;
	}
	
	public long setStageId(long stageId) {
		return this.stageId = stageId;
	}
	
	public String getDate() {
		return date;
	}
	
	public String setDate(String date) {
		return this.date = date;
	}
	
	public Festival getFestival() {
		return festival;
	}
	
//	public Festival setFestival(Festival festival) {
//		return this.festival = festival;
//	} 
//	이것만 추가하면 org.springframework.validation.BindException 에러 나옴. 그래서 그냥 festival 변수를 protected로 바꿈.
	
	public Contract getArtist() {
		return contract;
	}
	
	public Contract setArtist(Contract contract) {
		return this.contract = contract;
	}
}
