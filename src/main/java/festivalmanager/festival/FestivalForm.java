package festivalmanager.festival;

public class FestivalForm {
	private String location;
	private String startDate;
	private String endDate;
	private int maxVisitors;
	private boolean sellingTickets;

	public FestivalForm() {}

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
		return new Festival(location, startDate, endDate, maxVisitors, sellingTickets);
	}
}
