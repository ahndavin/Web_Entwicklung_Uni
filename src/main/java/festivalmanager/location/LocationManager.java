package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;

import festivalmanager.festival.Festival;
import org.springframework.stereotype.Service;
import org.springframework.data.util.Streamable;
import festivalmanager.contract.*;

@Service
public class LocationManager {
	private List<Location> locations;
	private ContractsRepository contractsRepository;

	public LocationManager(ContractsRepository contractsRepository) {
		this.contractsRepository = contractsRepository;
		this.locations = new LinkedList<Location>();
	}
	
	public List<Location> save(Location location) {
		if(locations.contains(location))
			return null;
		
		locations.add(location);
		return locations;
	}
	
	public Location getLocation(Location location) {
		int index = locations.indexOf(location);
		
		if(index == -1)
			return null;
		
		return locations.get(index);
	}
	
	public List<Location> removeLocation(Location location) {
		if(!locations.remove(location))
			return null;
		
		return locations;
	}
	
	public List<Location> findAll(){return locations;}
	
	public Streamable<Contract> findByName() {
		return contractsRepository.findAll();
	}
}