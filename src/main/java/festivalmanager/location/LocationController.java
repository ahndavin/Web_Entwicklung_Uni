package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import festivalmanager.contract.*;
import festivalmanager.festival.FestivalRepository;

@Controller
public class LocationController {
	private final LocationManager locationManager;
	
	public LocationController(LocationManager locationManager){
		Assert.notNull(locationManager, "Location must not be null!");
		this.locationManager = locationManager;
	}

	@GetMapping("/location")
	public String locationManagement(Model model){
		model.addAttribute("locationList", locationManager.findAll());
		
		return "location";
	}


	@PostMapping("/createLocation")
	public String addLocation(@Valid Location location) {
		locationManager.save(location);

		return "redirect:location";
	}

	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/createLocation")
	public String addLocation(Model model, Location location) {
		model.addAttribute("location", location);

		return "createLocation";
	}
	
//	@GetMapping("/location/{location}")
//	public String detailLocation(Model model, @PathVariable("location") String name, FestivalRepository festivalRepository) {
//		model.addAttribute("festivals", festivalRepository);
//		model.addAttribute("location", findLocation(name));
//		
//		return "detailLocation";
//	}
	
	//GetMapping
		@GetMapping(path = "/location/{location}")
		public String detailLocation(Model model, @PathVariable("location") String name){
			model.addAttribute("location", findLocation(name));
			model.addAttribute("contractList", locationManager.findByName());
			
			return "detailLocation";
		}
	
	@GetMapping("/location/{location}/area")
	public String areaManagement(Model model, @PathVariable("location") String name) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("areaList", findLocation(name).getAllAreas());

		return "area";
	}

	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/location/{location}/area/changeStatus")
	public String changeAreaStatus(@PathVariable("location") String name, @RequestParam("area") String area) {
		findArea(findLocation(name), area).toggleLock();
		
		return "redirect:";
	}

	@PostMapping("/location/{location}/createArea")
	public String addArea(@Valid Area area, @PathVariable("location") String name) {
		findLocation(name).addArea(area);
		
		return "redirect:area";
	}


	@GetMapping("/location/{location}/createArea")
	public String addArea(Model model, Area area, @PathVariable("location") String name) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("area", area);

		return "createArea";
	}
	
	@GetMapping("/location/{location}/area/{area}/stage")
	public String stageManagement(Model model, @PathVariable("location") String name, @PathVariable("area") String area) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("area", findArea(findLocation(name), area));
		model.addAttribute("stageList", findStages(findLocation(name).getAllAreas(), area));
		
		return "stage";
	}

	@PostMapping("/location/{location}/area/{area}/createStage")
	public String addStage(@Valid Stage stage, @PathVariable("location") String name, @PathVariable("area") String area) {
		findArea(findLocation(name), area).addStage(stage);
		
		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/createStage")
	public String addStage(Model model, Stage stage, @PathVariable("location") String name, @PathVariable("area") String area) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("area", findArea(findLocation(name), area));
		model.addAttribute("stage", stage);

		return "createStage";
	}
	
	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup")
	public String detailLineup(Model model, @PathVariable("location") String name, @PathVariable("area") String area, @PathVariable("stage") String stage) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("area", findArea(findLocation(name), area));
		model.addAttribute("stage", findStage(findStages(findLocation(name).getAllAreas(), area), stage));
		model.addAttribute("lineupList", findLineups(findStages(findLocation(name).getAllAreas(), area), stage));
		
		return "detailLineup";
	}
	
	@PostMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(@Valid Lineup lineup, @PathVariable("location") String name, @PathVariable("area") String area, @PathVariable("stage") String stage) {
		findLineups(findStages(findLocation(name).getAllAreas(), area), stage).add(lineup);
		
		return "redirect:detailLineup";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(Model model, Lineup lineup, @PathVariable("location") String name, @PathVariable("area") String zone, @PathVariable("stage") String stage) {

		model.addAttribute("location", findLocation(name));
		model.addAttribute("area", findArea(findLocation(name), zone));
		model.addAttribute("lineup", lineup);
		
		return "createLineup";
	}
	
	private Location findLocation(String name) {	// Um wiederholten Code zu vermeiden
		int i = 0;
		Location location = null;
		List<Location> locations = locationManager.findAll();
		
		while(i < locations.size()) {
			if(locations.get(i).getName().equals(name)) {
				location = locations.get(i);
				break;
			}
			
			i++;
		}
		
		return location;
	}
	
	private Area findArea(Location location, String name) {	// Um wiederholten Code zu vermeiden
		int i = 0;
		Area area = null;
		List<Area> areas = location.getAllAreas();
		
		while(i < areas.size()) {
			if(areas.get(i).getZone().equals(name)) {
				area = areas.get(i);
				break;
			}
			
			i++;
		}
		
		return area;
	}
	
	private List<Stage> findStages(List<Area> areas, String area) {	// Um wiederholten Code zu vermeiden
		int i = 0;
		
		while(i < areas.size()) {
			if(areas.get(i).getZone().equals(area))
				return areas.get(i).getAllStages();
			
			i++;
		}
		
		return null;
	}
	
	private Stage findStage(List<Stage> stages, String name) {	// Um wiederholten Code zu vermeiden
		int i = 0;
		Stage stage = null;
		
		while(i < stages.size()) {
			if(stages.get(i).getName().equals(name)) {
				stage = stages.get(i);
				break;
			}
				
			i++;
		}
		
		return stage;
	}
	
	private List<Lineup> findLineups(List<Stage> stages, String stage) {	// Um wiederholten Code zu vermeiden
		int i = 0;
		
		while(i < stages.size()) {
			System.out.println(stages.get(i));
			if(stages.get(i).getName().equals(stage))
				return stages.get(i).getLineups();
			
			i++;
		}
		
		return null;
	}
}
