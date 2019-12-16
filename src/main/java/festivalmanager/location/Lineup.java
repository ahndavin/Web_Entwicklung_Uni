package festivalmanager.location;

import java.util.Date;

import festivalmanager.contract.Contract;

public class Lineup {
	private String date;
	//private Contract artist;

	public Lineup(String date) {
		this.date = date;
		//this.artist = artist;
	}
	
	public String getDate() {
		return date;
	}
	
	public String editDate(String date) {
		return this.date = date;
	}
	
	/*public Contract getArtist() {
		return artist;
	}
	
	public Contract editArtist(Contract artist) {
		return this.artist = artist;
	}

	 */
}
