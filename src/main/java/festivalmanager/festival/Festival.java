package festivalmanager.festival;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Festival {
	private @Id @GeneratedValue long id;
	private final int maxVisitors;
	private int currentVisitors;

	private boolean sellingTickets;

	private final List<String> plan = new ArrayList<>();

	public Festival(int maxVisitors, boolean sellingTickets) {
		this.maxVisitors = maxVisitors;

		this.sellingTickets = sellingTickets;
	}
}
