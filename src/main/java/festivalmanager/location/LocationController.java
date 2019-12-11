package festivalmanager.location;

import java.util.List;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		model.addAttribute("areaList", findLocation(name).getAllAreas());

		return "area";
	}
	
	@PostMapping("/location/{location}/createArea")
	public String addArea(@PathVariable("location") String name, @Valid Area area) {
		findLocation(name).addArea(area);
		
		System.out.println(name);
		
		return "redirect:area";
	}

	@GetMapping("/location/{location}/createArea")
	public String addArea(Model model, Area area) {
		model.addAttribute("area", area);

		return "createArea";
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
}
