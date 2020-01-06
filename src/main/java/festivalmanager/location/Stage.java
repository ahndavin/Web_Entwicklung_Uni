package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stage {
	private @Id @GeneratedValue long id;
	private String name;
	private String poster;
	private @OneToMany List<Lineup> lineups;
	
	@SuppressWarnings("unused")
	private Stage() {}
	
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
