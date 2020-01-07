package festivalmanager.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import festivalmanager.contract.Contract;

@Entity
public class Lineup {
	private @Id @GeneratedValue long id;
	private long stageId;
	private String date;
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
	
	public Contract getArtist() {
		return contract;
	}
	
	public Contract setArtist(Contract contract) {
		return this.contract = contract;
	}
}
