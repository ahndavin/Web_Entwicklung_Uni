package festivalmanager.contract;

public class Contract {
	private String price;
	private String artist;
	private Boolean accepted;
	private String techniciansCount;
	private String techniciansWorkingHours;
	private String techniciansHourlyWage;

	public Contract(String price, String artist, Boolean accepted, String techniciansCount, String techniciansWorkingHours, String techniciansHourlyWage){
		this.price = price;
		this.artist = artist;
		this.techniciansCount = techniciansCount;
		this.techniciansWorkingHours = techniciansWorkingHours;
		this.techniciansHourlyWage = techniciansHourlyWage;
		this.accepted = accepted;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public void setTechniciansCount(String techniciansCount) {
		this.techniciansCount = techniciansCount;
	}
	public void setTechniciansWorkingHours(String techniciansWorkingHours) {
		this.techniciansWorkingHours = techniciansWorkingHours;
	}
	public void setTechniciansHourlyWage(String techniciansHourlyWage) {
		this.techniciansHourlyWage = techniciansHourlyWage;

	}
	public String getPrice() {
		return this.price;
	}
	public String getArtist() {
		return this.artist;
	}
	public Boolean getAccepted() {
		return this.accepted;
	}
	public String getTechniciansCount() {
		return this.techniciansCount;
	}
	public String getTechniciansWorkingHours() {
		return this.techniciansWorkingHours;
	}
	public String getTechniciansHourlyWage() {
		return this.techniciansHourlyWage;
	}






}
