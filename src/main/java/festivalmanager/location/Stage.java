package festivalmanager.location;

import festivalmanager.contract.*;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Stage {
	private String name;
	private Map<Contract, Date> lineup;
	
	public Stage(String name) {
		this.name = name;
		this.lineup = new HashMap<Contract, Date>();
	}
	
	public String getName() {
		return name;
	}
	
	public Map<Contract, Date> getLineup() {
		return lineup;
	}
	
	public Map<Contract, Date> changeTime(Contract contract, Date time) {
		if(lineup.containsKey(contract)) {
			lineup.put(contract, time);
			return lineup;
		}
		
		return null;
	}
	
	public Map<Contract, Date> addArtist(Contract contract, Date time) {
		if(lineup.containsKey(contract))
			return null;
		
		lineup.put(contract, time);
		
		return lineup;
	}
	
	public Contract removeArtist(Contract contract) {
		if(lineup.remove(contract) == null)
			return null;
		
		return contract;
	}
}
