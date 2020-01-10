package festivalmanager.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {
	private @Id @GeneratedValue long id;
	private String name;
	private String address;
	private boolean isBooked;
	private Integer price;
	private Integer currVisitors;
	private Integer maxVisitors;
	private String thumbnail;
	private String groundPlan;
	
	@SuppressWarnings("unused")
	private Location() {}
	
	public Location(String name, String address, Integer price, Integer maxVisitors, String thumbnail, String groundPlan) {
		this.name = name;
		this.address = address;
		this.price = price;
		this.maxVisitors = maxVisitors;
		this.thumbnail = thumbnail;
		this.groundPlan = groundPlan;
		this.isBooked = false;
		this.currVisitors = 0;
	}
	
	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	public String getName() {		
		return name;
	}
	
	public String setName(String name) {		
		return this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String setAddress(String address) {		
		return this.address = address;
	}
	
	public boolean getStatus() {
		return isBooked;
	}
	
	public boolean setStatus(boolean isBooked) {
		return this.isBooked = isBooked;
	}
	
	public boolean toggleBook() {
		return isBooked = !isBooked;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer setPrice(Integer price) {
		return this.price = price;
	}
	
	public Integer countVisitors(int visitors) {		
		return currVisitors += visitors;
	}
	
	public Integer getCurrVisitors() {
		return currVisitors;
	}
	
	public Integer setCurrVisitors(Integer currVisitors) {
		return this.currVisitors = currVisitors;
	}
	
	public Integer getMaxVisitors() {
		return maxVisitors;
	}
	
	public Integer setMaxVisitors(Integer maxVisitors) {
		return this.maxVisitors = maxVisitors;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String setThumbnail(String thumbnail) {
		return this.thumbnail = thumbnail;
	}
	
	public String getGroundPlan() {
		return groundPlan;
	}
	
	public String setGroundPlan(String groundPlan) {
		return this.groundPlan = groundPlan;
	}
}