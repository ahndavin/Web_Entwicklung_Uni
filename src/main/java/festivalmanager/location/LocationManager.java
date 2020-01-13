package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;

import festivalmanager.contract.*;
import festivalmanager.festival.Festival;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import org.springframework.data.util.Streamable;

@Service
public class LocationManager {
	private LocationRepository locationRepository;
	private AreaRepository areaRepository;
	private StageRepository stageRepository;
	private LineupRepository lineupRepository;
	private ContractsRepository contractsRepository;

 	public LocationManager(	LocationRepository locationRepository, AreaRepository areaRepository,
							StageRepository stageRepository, LineupRepository lineupRepository,
							ContractsRepository contractsRepository) {
 		
 		this.locationRepository = locationRepository;
 		this.areaRepository = areaRepository;
 		this.stageRepository = stageRepository;
 		this.lineupRepository = lineupRepository;
 		this.contractsRepository = contractsRepository;
	}

	//--------Location--------
	public Location save(Location location) {
		return locationRepository.save(location);
	}
	
	public Location update(LocationForm locationForm, long locationId) {
		Location location = findById(locationId);
		Location editedLocation= locationForm.toLocation();
		
		editedLocation.setId(location.getId());
		editedLocation.setStatus(location.getStatus());
		editedLocation.setCurrVisitors(location.getCurrVisitors());
		
		return save(editedLocation);
	}

	public void deleteLocationById(long id) {
		locationRepository.deleteById(id);
	}
	
	public Location findById(long id) {
		int i = 0;
		List<Location> locations = findAllLocations();
		
		while(i < locations.size()) {
			if(locations.get(i).getId() == id)
				break;
			
			i++;
		}
		
		return locationRepository.findById(locations.get(i).getId()).get();
	}

	public Location findByName(String name) {
		int i = 0;
		List<Location> locations = findAllLocations();
		
		while(i < locations.size()) {
			if(locations.get(i).getName().equals(name))
				break;

			i++;
		}
		
		return locationRepository.findById(locations.get(i).getId()).get();
	}

	public List<Location> findAllLocations() {
		return locationRepository.findAll();
	}
	//----------------------

	//--------Area--------
	public Area save(Area area){
		return areaRepository.save(area);
	}
	
	public Area update(Location location, AreaForm areaForm, long areaId) {
		Area area = findById(location, areaId);
		Area editedArea = areaForm.toArea();
		
		editedArea.setId(area.getId());
		editedArea.setLocationId(area.getLocationId());
		editedArea.setCurrVisitors(area.getCurrVisitors());
		
		return save(editedArea);
	}

	public void deleteAreaById(long id) {
		areaRepository.deleteById(id);
	}
	
	public Area findById(Location location, long id) {
		int i = 0;
		List<Area> areas = findAllAreas(location);

		while(i < areas.size()) {
			if(areas.get(i).getId() == id)
				break;

			i++;
		}

		return areaRepository.findById(areas.get(i).getId()).get();
	}

	public Area findByName(Location location, String name) {
		int i;
		List<Area> areas = areaRepository.findAll();

		for(i = 0; i < areas.size(); i++) {
			if((areas.get(i).getLocationId() == location.getId()) && (areas.get(i).getZone().equals(name)))
				break;
		}

		return areaRepository.findAll().get(i);
	}

	public List<Area> findAllAreas(Location location) {
		List<Area> allAreasInLocation = new LinkedList<Area>();
		List<Area> allAreas = areaRepository.findAll();

		for(Area area : allAreas) {
			if(area.getLocationId() == location.getId())
				allAreasInLocation.add(area);
		}

		return allAreasInLocation;
	}
	//----------------------

	//--------Stage--------
	public Stage save(Stage stage){
		return stageRepository.save(stage);
	}
	
	public Stage update(Area area, StageForm stageForm, long stageId) {
		Stage stage = findById(area, stageId);
		Stage editedStage = stageForm.toStage();
		
		editedStage.setId(stage.getId());
		editedStage.setAreaId(stage.getAreaId());
		
		return save(editedStage);
	}

	public void deleteStageById(long id) {
		stageRepository.deleteById(id);
	}

	public Stage findById(Area area, long id) {
		int i = 0;
		List<Stage> stages = findAllStages(area);

		while(i < stages.size()) {
			if(stages.get(i).getId() == id)
				break;

			i++;
		}

		return stageRepository.findById(stages.get(i).getId()).get();
	}
	
	public Stage findByName(Area area, String name) {
		int i = 0;
		List<Stage> stages = findAllStages(area);

		while(i < stages.size()) {
			if(stages.get(i).getName().equals(name))
				break;

			i++;
		}

		return stageRepository.findById(stages.get(i).getId()).get();
	}

	public List<Stage> findAllStages(Area area) {
		List<Stage> allStagesInArea = new LinkedList<Stage>();
		List<Stage> stages = stageRepository.findAll();

		for(Stage stage : stages) {
			if(stage.getAreaId() == area.getId())
				allStagesInArea.add(stage);
		}

		return allStagesInArea;
	}
	//----------------------

	//--------Lineup--------
	public Lineup save(Lineup lineup){
		return lineupRepository.save(lineup);
	}
	
	public Lineup update(Stage stage, LineupForm lineupForm, long lineupId, String festival, String contract) {
		Lineup lineup = findById(stage, lineupId);
		Lineup editedLineup = lineupForm.toLineup();
		List<Festival> festivals = Lists.newArrayList(findFestivals().iterator());
		festival = festival.substring(0, festival.length()-1);
		List<Contract> contracts = findByName().toList();
		contract = contract.substring(0, contract.length()-1);
		
		for(Festival fest : festivals) {
			if(fest.getName().equals(festival)) {
				editedLineup.festival = fest;
				break;
			}
				
		}
		for(Contract cont : contracts) {
			if(cont.getArtist().equals(contract)) {
				editedLineup.setArtist(cont);
				break;
			}
		}
		
		
		editedLineup.setId(lineup.getId());
		editedLineup.setStageId(lineup.getStageId());
		
		return save(editedLineup);
	}

	public void deleteLineupById(long id) {
		lineupRepository.deleteById(id);
	}

	public Lineup findById(Stage stage, long id) {
		int i = 0;
		List<Lineup> lineups = findAllLineups(stage);

		while(i < lineups.size()) {
			if(lineups.get(i).getId() == id)
				break;

			i++;
		}

		return lineupRepository.findById(lineups.get(i).getId()).get();
	}

	public List<Lineup> findAllLineups(Stage stage) {
		List<Lineup> allLineupForStage = new LinkedList<Lineup>();
		List<Lineup> lineups = lineupRepository.findAll();

		for(Lineup lineup : lineups) {
			if(lineup.getStageId() == stage.getId())
				allLineupForStage.add(lineup);
		}

		return allLineupForStage;
	}
	//----------------------

	public Streamable<Contract> findByName() {
		return contractsRepository.findAll();
	}
	
	public Iterable<Festival> findFestivals() {
		return LocationController.getFestivalManager().findAll();
	}
}
