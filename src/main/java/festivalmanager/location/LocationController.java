package festivalmanager.location;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		model.addAttribute("locationList", locationManager.getAllLocations());
		return "location";
	}

	@PostMapping("/createLocation")
	public String addLocation(@Valid Location location) {

		locationManager.addLocation(location);

		return "redirect:location";
	}

	@GetMapping("/createLocation")
	public String addContract(Model model, Location location) {

		model.addAttribute("location", location);

		return "createLocation";
	}
	
	@GetMapping("/location/{location}")
	public String detailLocation(Model model, Location location) {
		model.addAttribute("location", location);

		return "detailLocation";
	}
}
