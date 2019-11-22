package festivalmanager.festival;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class Festival {
	private static final int START_DATE = 0;
	private static final int END_DATE = 1;

	private @Id @GeneratedValue long id;

	private String name;
	private int maxVisitors;
	private int currentVisitors;

	private boolean sellingTickets;

	@ElementCollection
	private final List<String> plan = new ArrayList<>();

	private String location;

	@Transient
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	private Date[] festivalDate = new Date[2];


	private Festival() {}

	public Festival(String name, String location, String startDate, String endDate, int maxVisitors, boolean sellingTickets) {
		this.name = name;
		this.location = location;

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

	public String getName() {
		return this.name;
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

	public void setSellingTickets(boolean sellingTickets) {
		this.sellingTickets = sellingTickets;
	}

	public Iterable<String> getPlan() {
		return plan;
	}

	public void editPlan(int index, String newValue) {
		if(index == -1) {
			plan.add(newValue);
		} else if(plan.size() >= index) {
			plan.set(index, newValue);
		}
	}
}
