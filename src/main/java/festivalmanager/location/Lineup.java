package festivalmanager.location;

import java.util.Date;

import festivalmanager.contract.Contract;

public class Lineup {
	private Date date;
	private Contract artist;

	public Lineup(Date date, Contract artist) {
		this.date = date;
		this.artist = artist;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Date editDate(Date date) {
		return this.date = date;
	}
	
	public Contract getArtist() {
		return artist;
	}
	
	public Contract editArtist(Contract artist) {
		return this.artist = artist;
	}
}
