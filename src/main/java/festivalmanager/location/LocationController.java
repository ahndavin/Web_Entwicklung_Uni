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
	public String addContract(Model model, Location location) {
		model.addAttribute("location", location);

		return "createLocation";
	}
	
	@GetMapping("/location/{location}")
	public String detailLocation(Model model, @PathVariable("location") String name) {
		int i = 0;
		Location location = null;
		List<Location> locations = locationManager.findAll();
		
		while(i < locations.size()) {
			if(locations.get(i).getName().equals(name))
				location = locations.get(i);
			
			i++;
		}
		
		model.addAttribute("location", location);

		return "detailLocation";
	}
}
