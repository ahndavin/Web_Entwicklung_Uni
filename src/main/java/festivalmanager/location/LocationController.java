package festivalmanager.location;

import java.util.List;
import javax.validation.Valid;
import com.google.common.collect.Lists;

import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;

import festivalmanager.contract.Contract;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;

@Controller
public class LocationController {
	private final LocationManager locationManager;
	private final FestivalManager festivalManager;

	public LocationController(LocationManager locationManager, FestivalManager festivalManager){
		Assert.notNull(locationManager, "Location must not be null!");
		this.locationManager = locationManager;
		this.festivalManager = festivalManager;
	}


	@GetMapping("/location")
	public String locationManagement(Model model) {
		List<Location> locations = locationManager.findAllLocations();

		model.addAttribute("locationList", locations);

		return "location";
	}


	@PostMapping("/createLocation")
	public String addLocation(@Valid Location location) {
		locationManager.save(location);

		return "redirect:location";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/createLocation")
	public String addLocation(Model model, Location location) {
		model.addAttribute("location", location);

		return "createLocation";
	}

	@PostMapping("/location/{location}/editLocation")
	public String editLocation(@ModelAttribute("location") @Valid Location location) {
		//TODO
		return "redirect:location";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/location/{location}/editLocation")
	public String editLocation(Model model, @PathVariable("location") String locationName) {
		Location location = locationManager.findByName(locationName);

		model.addAttribute("location", location);

		return "editLocation";
	}

	@GetMapping("/location/{location}")
	public String detailLocation(Model model, @PathVariable("location") String locationName){
		Location location = locationManager.findByName(locationName);
		List<Contract> contracts = locationManager.findByName().toList();
		List<Festival> festivals = (List<Festival>) festivalManager.findAll();

		model.addAttribute("location", location);
		model.addAttribute("contractList", contracts);
		model.addAttribute("festivalList", festivals);

		return "detailLocation";
	}

	@GetMapping("/location/{location}/area")
	public String areaManagement(Model model, @PathVariable("location") String locationName) {
		Location location = locationManager.findByName(locationName);
		List<Area> areas = locationManager.findAllAreas(location);

		model.addAttribute("location", location);
		model.addAttribute("areaList", areas);

		return "area";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")					왜 갑자기 안 되는거냐
	@GetMapping("/location/{location}/area/changeStatus")
	public String changeAreaStatus(@PathVariable("location") String locationName, @RequestParam("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		area.toggleLock();
		locationManager.deleteAreaById(area.getId());
		locationManager.save(area);

		return "redirect:";
	}

	@PostMapping("/location/{location}/createArea")
	public String addArea(@Valid Area area, @PathVariable("location") String locationName) {
		Location location = locationManager.findByName(locationName);

		area.setLocationId(location.getId());
		locationManager.save(area);

		return "redirect:area";
	}


	@GetMapping("/location/{location}/createArea")
	public String addArea(Model model, Area area, @PathVariable("location") String locationName) {
		Location location = locationManager.findByName(locationName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);

		return "createArea";
	}

	@PostMapping("/location/{location}/area/{area}")
	String editArea(@ModelAttribute("area") @Valid Area area, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		System.out.println(area.getZone());
//		locationManager.deleteAreaById(area.getId());
//		locationManager.save(area);

		return "redirect:";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/location/{location}/area/{area}")
	String editArea(Model model, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);

		return "editArea";
	}

	@GetMapping("/location/{location}/area/{area}/stage")
	public String stageManagement(Model model, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		List<Stage> stages = locationManager.findAllStages(area);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stageList", stages);

		return "stage";
	}

	@PostMapping("/location/{location}/area/{area}/createStage")
	public String addStage(@Valid Stage stage, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		stage.setAreaId(area.getId());
		locationManager.save(stage);

		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/createStage")
	public String addStage(Model model, Stage stage, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);

		return "createStage";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}")
	public String editStage(@ModelAttribute("stage") @Valid Stage stage, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		//TODO
		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}")
	public String editStage(Model model, @PathVariable("location") String locationName, @PathVariable("area") String areaName, @PathVariable("stage") String stageName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);

		return "editStage";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup")
	public String detailLineup(Model model, @PathVariable("location") String locationName, @PathVariable("area") String areaName, @PathVariable("stage") String stageName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		List<Lineup> lineups = locationManager.findAllLineups(stage);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);
		model.addAttribute("lineupList", lineups);

		return "detailLineup";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(@Valid Lineup lineup, @RequestParam("contract") String contract, @PathVariable("location") String locationName, @PathVariable("area") String areaName, @PathVariable("stage") String stageName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		List<Contract> contracts = locationManager.findByName().toList();
		contract = contract.substring(0, contract.length()-1);

		for(Contract cont : contracts) {
			if(cont.getArtist().equals(contract))
				lineup.setArtist(cont);
		}

		lineup.setStageId(stage.getId());
		locationManager.save(lineup);

		return "redirect:lineup";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(Model model, Lineup lineup, @PathVariable("location") String locationName, @PathVariable("area") String areaName) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		List<Contract> contracts = locationManager.findByName().toList();

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("lineup", lineup);
		model.addAttribute("contractList", contracts);

		return "createLineup";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}/lineup/{lineup}")
	public String editLineup(@Valid Lineup lineup, @RequestParam("contract") String contract, @PathVariable("location") String locationName, @PathVariable("area") String areaName, @PathVariable("stage") String stageName) {
		//TODO
		return "redirect:lineup";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup/{lineup}")
	public String editLineup(Model model, @PathVariable("location") String locationName, @PathVariable("area") String areaName, @PathVariable("stage") String stageName, @PathVariable("lineup") String lineupIdAsString) {
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		long lineupId = Long.parseLong(lineupIdAsString);
		Lineup lineup = locationManager.findById(stage, lineupId);
		List<Contract> contracts = locationManager.findByName().toList();

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("lineup", lineup);
		model.addAttribute("contractList", contracts);

		return "editLineup";
	}
}
