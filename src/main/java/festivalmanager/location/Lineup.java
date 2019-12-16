package festivalmanager.location;

import java.util.Date;

import festivalmanager.contract.Contract;

public class Lineup {
	private String date;
	private Contract contract;

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
	
	public String getArtist() {
		return contract.getArtist();
	}
	
	public Contract editArtist(Contract artist) {
		return this.contract = artist;
	}


}
