package festivalmanager.location;

import java.time.LocalDateTime;

import festivalmanager.contract.Contract;
import festivalmanager.festival.Festival;

public class LineupForm {
	private long id;
	private long stageId;
	private LocalDateTime date;
	protected Festival festival;
	private Contract contract;

	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	public long getStageId() {
		return stageId;
	}
	
	public long setStageId(long stageId) {
		return this.stageId = stageId;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public LocalDateTime setDate(LocalDateTime date) {
		return this.date = date;
	}
	
	public Festival getFestival() {
		return festival;
	}
	
	public Contract getArtist() {
		return contract;
	}
	
	public Contract setArtist(Contract contract) {
		return this.contract = contract;
	}
	
	public Lineup toLineup() {
		return new Lineup(date);
	}
}
