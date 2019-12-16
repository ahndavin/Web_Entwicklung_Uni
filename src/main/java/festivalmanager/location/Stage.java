package festivalmanager.location;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Stage {
	private String name;
	private String poster;
	private List<Lineup> lineups;
	
	public Stage(String name, String poster) {
		this.name = name;
		this.poster = poster;
		this.lineups = new LinkedList<Lineup>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public List<Lineup> getLineups(){
		return lineups;
	}
	
	public boolean addLineup(Lineup lineup) {
		return lineups.add(lineup);
	}
	
//	public boolean editLineup(Lineup lineup) {
//		//Iterator
//	}
//	
//	public Date removeLineup(Date date) {
//		//Iterator
//	}
}
