package festivalmanager.festival;

import javax.persistence.*;

import org.javamoney.moneta.Money;
import org.salespointframework.inventory.UniqueInventoryItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class Festival {
	public static final int START_DATE = 0;
	public static final int END_DATE = 1;

	private @Id @GeneratedValue long id;

	private String name;
	private String location;

	private Date[] festivalDate = new Date[2];


	private int amountDaytickets;
	private int amountCampingtickets;
	private float priceDayticket;
	private float priceCampingticket;

	private int maxVisitors;
	private int currentVisitors;

	private boolean sellingTickets;

	@ElementCollection
	private final List<String> plan = new ArrayList<>();

	@ElementCollection
	private final List<UniqueInventoryItem> inventory = new ArrayList<>();

	@Transient
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


	private Festival() {}

	public Festival(String name, String location,
					String startDate, String endDate,
					int amountDaytickets, int amountCampingtickets,
					float priceDayticket, float priceCampingticket,
					int maxVisitors, boolean sellingTickets) {

		this.name = name;
		this.location = location;
		this.amountDaytickets = amountDaytickets;
		this.amountCampingtickets = amountCampingtickets;
		this.priceDayticket = priceDayticket;
		this.priceCampingticket = priceCampingticket;

		if(endDate == null) {
			endDate = startDate;
		}

		setDate(START_DATE, startDate);
		setDate(END_DATE, endDate);

		this.maxVisitors = maxVisitors;
		this.sellingTickets = sellingTickets;
	}

	public static boolean areAtTheSameTimeAndPlace(Festival f1, Festival f2) {
		Date[] d1 = f1.getDate();
		Date[] d2 = f2.getDate();

		boolean sameDate =
			(d1[START_DATE].getTime() <= d2[START_DATE].getTime() && d2[START_DATE].getTime() <= d1[END_DATE].getTime()) ||
			(d2[START_DATE].getTime() <= d1[START_DATE].getTime() && d1[START_DATE].getTime() <= d2[END_DATE].getTime());

		boolean sameLocation = f1.getLocation().equals(f2.getLocation());

		return sameDate && sameLocation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public String getUrlName() {
		return this.name.replaceAll(" ", "%20");
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

	public Date[] getDate() {
		return festivalDate;
	}

	public String[] getFormattedDate() {
		return new String[] { dateFormat.format(festivalDate[START_DATE]), dateFormat.format(festivalDate[END_DATE]) };
	}

	public String getStartDate() {
		return getFormattedDate()[START_DATE];
	}

	public String getEndDate() {
		return getFormattedDate()[END_DATE];
	}

	public void setDate(int dateType, String date) {
		try {
			festivalDate[dateType] = dateFormat.parse(date);
		} catch (ParseException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();

			// TODO show error somehow
		}
	}

	public int getMaxVisitors() {
		return maxVisitors;
	}

	public void setMaxVisitors(int maxVisitors) {
		this.maxVisitors = maxVisitors;
	}

	public int getVisitorCount() {
		return currentVisitors;
	}

	public boolean isSellingTickets() {
		return sellingTickets;
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

	public void setSellingTickets(boolean sellingTickets) {
		this.sellingTickets = sellingTickets;
	}

	public Iterable<String> getPlan() {
		return plan;
	}

	public List<String> editPlan() {
		return plan;
	}

	public Iterable<UniqueInventoryItem> getInventory() {
		return inventory;
	}

	public List<UniqueInventoryItem> editInventory() {
		return inventory;
	}

	public String toString() {
		return 	this.id + ": " +
				this.name + " in " +
				this.location + " from " +
				this.getStartDate() + " to " +
				this.getEndDate();
	}
}
