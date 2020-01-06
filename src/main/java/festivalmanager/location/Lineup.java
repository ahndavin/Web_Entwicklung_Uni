package festivalmanager.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import festivalmanager.contract.Contract;

@Entity
public class Lineup {
	private @Id @GeneratedValue long id;
	private String date;
	private @OneToOne Contract contract;

	@SuppressWarnings("unused")
	private Lineup() {}
	
	public Lineup(String date, Contract contract) {
		this.date = date;
		this.contract = contract;
	}
	
	public String getDate() {
		return date;
	}
	
	public String editDate(String date) {
		return this.date = date;
	}
	
	public Contract getArtist() {
		return contract;
	}
	
	public Contract editArtist(Contract artist) {
		return this.contract = artist;
	}


}
