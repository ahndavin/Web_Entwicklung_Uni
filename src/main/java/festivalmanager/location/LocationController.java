package festivalmanager.location;

import java.util.List;
import java.util.LinkedList;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/createLocation")
	public String addLocation(Model model, Location location) {
		model.addAttribute("location", location);

		return "createLocation";
	}
	
	@GetMapping("/location/{location}")
	public String detailLocation(Model model, @PathVariable("location") String name) {
		model.addAttribute("location", findLocation(name));
		
		return "detailLocation";
	}
	
	@GetMapping("/location/{location}/area")
	public String areaManagement(Model model, @PathVariable("location") String name) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("areaList", findLocation(name).getAllAreas());

		return "area";
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
		System.out.println(area);
		findArea(findLocation(name), area).addStage(stage);
		
		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/createStage")
	public String addStage(Model model, Stage stage, @PathVariable("location") String name, @PathVariable("area") String zone) {
		model.addAttribute("location", findLocation(name));
		model.addAttribute("stage", stage);

		return "createStage";
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
}
