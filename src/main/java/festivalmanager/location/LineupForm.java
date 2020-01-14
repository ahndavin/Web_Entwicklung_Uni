package festivalmanager.location;

import java.time.LocalDateTime;

public class LineupForm extends Lineup{
	public LineupForm(LocalDateTime date) {
		super(date);
	}
	
	public Lineup toLineup() {
		return new Lineup(getDate());
	}
}
