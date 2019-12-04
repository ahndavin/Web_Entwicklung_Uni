package festivalmanager.festival;

import javax.money.MonetaryAmount;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;

import java.util.List;

public class FestivalForm {
	@NotNull
	private long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String location;

	@NotEmpty
	private String startDate;

	private String endDate;

	@NotEmpty
	private int amountDaytickets;

	@NotEmpty
	private int amountCampingtickets;

	@NotEmpty
	private float priceDayticket;

	@NotEmpty
	private float priceCampingticket;

	@Min(0)
	private int maxVisitors;

	private boolean sellingTickets;

	private List<String> plan = null;

	public FestivalForm() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if(endDate.equals("")) {
			endDate = null;
		}

		this.endDate = endDate;
	}

	public int getMaxVisitors() {
		return maxVisitors;
	}

	public void setMaxVisitors(int maxVisitors) {
		this.maxVisitors = maxVisitors;
	}

	public boolean isSellingTickets() {
		return sellingTickets;
	}

	public void setSellingTickets(boolean sellingTickets) {
		this.sellingTickets = sellingTickets;
	}

	public Festival toFestival() {
		Festival festival = new Festival(name, location, startDate, endDate, amountDaytickets, amountCampingtickets, priceDayticket, priceCampingticket, maxVisitors, sellingTickets);

		festival.setId(id);

		if(plan != null) {
			festival.editPlan().addAll(plan);
		}

		return festival;
	}

	public void setPlan(List<String> plan) {
		this.plan = plan;
	}

	public int getAmountDaytickets(){
		return this.amountDaytickets;
	}

	public int getAmountCampingtickets(){
		return this.amountCampingtickets;
	}

	public javax.money.MonetaryAmount getPriceDayticket(){
		return Money.of(priceDayticket, "EUR");
	}

	public javax.money.MonetaryAmount getPriceCampingticket(){
		return Money.of(priceCampingticket, "EUR");
	}

	public void setAmountDaytickets(int amount){
		this.amountDaytickets = amount;
	}

	public void setAmountCampingtickets(int amount){
		this.amountCampingtickets = amount;
	}

	public void setPriceDayticket(float price){
		this.priceDayticket = price;
	}

	public void setPriceCampingticket(float price){
		this.priceCampingticket = price;
	}
}
