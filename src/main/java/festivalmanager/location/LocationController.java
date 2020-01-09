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

import festivalmanager.contract.Contract;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;

@Controller
public class LocationController {
	private final LocationManager locationManager;
	private static FestivalManager festivalManager;

	@SuppressWarnings("static-access")
	public LocationController(LocationManager locationManager, FestivalManager festivalManager){
		Assert.notNull(locationManager, "Location must not be null!");
		this.locationManager = locationManager;
		this.festivalManager = festivalManager;
	}
	
	public static FestivalManager getFestivalManager() {
		return festivalManager;
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
	public String editLocation(	@ModelAttribute("location") @Valid LocationForm locationForm,
								@PathVariable("location") String locationIdAsString) {
		long locationId = Long.parseLong(locationIdAsString);
		Location editedLocation= locationForm.toLocation();
		Location location = locationManager.findById(locationId);		//locationId = 6
		
		System.out.println(location.getId());							//6
		
		editedLocation.setId(locationId);
		editedLocation.setStatus(location.getStatus());
		editedLocation.setCurrVisitors(location.getCurrVisitors());
		
		System.out.println(editedLocation.getId());						//6
		
		locationManager.deleteLocationById(locationId);					//altes Location gelöscht
		
		System.out.println(locationManager.findAllLocations().size());	//0
		System.out.println(editedLocation.getId());			 			//6
		
		locationManager.save(editedLocation);							//neues Location save
		
		System.out.println(locationManager.findAllLocations().size());	//1
		System.out.println(locationManager.findAllLocations().get(0).getId());	//13  Hier muss 6 kommen!
		
		return "redirect:/location";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/location/{location}/editLocation")
	public String editLocation(Model model, @PathVariable("location") String locationIdAsString) {
		long locationId = Long.parseLong(locationIdAsString);
		Location location = locationManager.findById(locationId);
		
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
	
	@GetMapping("/location/{location}/editLocation/delete")
	public String deleteLocation(@PathVariable("location") String locationName) {
		Location location = locationManager.findByName(locationName);
		
		locationManager.deleteLocationById(location.getId());

		return "redirect:/location";
	}
	
//	@GetMapping("/location/{location}/area")
//	public String areaManagement(Model model, @PathVariable("location") String locationName) {
//		Location location = locationManager.findByName(locationName);
//		System.out.println(location.getId());
//		List<Area> areas = locationManager.findAllAreas(location);
//		
//		model.addAttribute("location", location);
//		model.addAttribute("areaList", areas);
//
//		return "area";
//	}
	
	@GetMapping("/location/{location}/area")
	public String areaManagement(Model model, @PathVariable("location") String locationName) {
		
		System.out.println(locationManager.findByName(locationName).getId());
		List<Area> areas = locationManager.findAllAreas(locationManager.findByName(locationName));
		
		model.addAttribute("location", locationManager.findByName(locationName));
		model.addAttribute("areaList", areas);

		return "area";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")					왜 갑자기 안 되는거냐
	@GetMapping("/location/{location}/area/changeStatus")
	public String changeAreaStatus(	@PathVariable("location") String locationName,
									@RequestParam("area") String areaName) {
		
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
	String editArea(@ModelAttribute("area") @Valid Area area,
					@PathVariable("location") String locationName,
					@PathVariable("area") String areaName) {
		
//		System.out.println(area.getZone());
//		locationManager.deleteAreaById(area.getId());
//		locationManager.save(area);

		return "redirect:";
	}

	//@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/location/{location}/area/{area}")
	String editArea(Model model,
					@PathVariable("location") String locationName,
					@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);

		return "editArea";
	}
	
	@GetMapping("/location/{location}/area/{area}/delete")
	public String deleteArea(	@PathVariable("location") String locationName,
								@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		locationManager.deleteAreaById(area.getId());

		return "redirect:/location/" + location.getName() + "/area";
	}

	@GetMapping("/location/{location}/area/{area}/stage")
	public String stageManagement(	Model model,
									@PathVariable("location") String locationName,
									@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		List<Stage> stages = locationManager.findAllStages(area);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stageList", stages);

		return "stage";
	}

	@PostMapping("/location/{location}/area/{area}/createStage")
	public String addStage(	@Valid Stage stage,
							@PathVariable("location") String locationName,
							@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		stage.setAreaId(area.getId());
		locationManager.save(stage);

		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/createStage")
	public String addStage(	Model model, Stage stage,
							@PathVariable("location") String locationName,
							@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);

		return "createStage";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}")
	public String editStage(@ModelAttribute("stage") @Valid Stage stage,
							@PathVariable("location") String locationName,
							@PathVariable("area") String areaName) {
		//TODO
		return "redirect:stage";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}")
	public String editStage(Model model,
							@PathVariable("location") String locationName,
							@PathVariable("area") String areaName,
							@PathVariable("stage") String stageName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);

		return "editStage";
	}
	
	@GetMapping("/location/{location}/area/{area}/stage/{stage}/delete")
	public String deleteStage(	@PathVariable("location") String locationName,
								@PathVariable("area") String areaName,
								@PathVariable("stage") String stageName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);

		locationManager.deleteStageById(stage.getId());

		return "redirect:/location/" + location.getName() + "/area/" + area.getZone() + "/stage";
	}
	
	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup")
	public String detailLineup(	Model model,
								@PathVariable("location") String locationName,
								@PathVariable("area") String areaName,
								@PathVariable("stage") String stageName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		List<Lineup> lineups = locationManager.findAllLineups(stage);

		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("stage", stage);
		model.addAttribute("lineupList", lineups);
		
		return "lineup";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(@RequestParam("festival") String festival,
							@RequestParam("contract") String contract,
							@Valid Lineup lineup,
							@PathVariable("location") String locationName,
							@PathVariable("area") String areaName,
							@PathVariable("stage") String stageName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		List<Festival> festivals = Lists.newArrayList(locationManager.findFestivals().iterator());
		festival = festival.substring(0, festival.length()-1);
		List<Contract> contracts = locationManager.findByName().toList();
		contract = contract.substring(0, contract.length()-1);
		
		for(Festival fest : festivals) {
			if(fest.getName().equals(festival)) {}
				lineup.festival = fest;
		}
		for(Contract cont : contracts) {
			if(cont.getArtist().equals(contract))
				lineup.setArtist(cont);
		}
		lineup.setStageId(stage.getId());
		
		locationManager.save(lineup);

		return "redirect:lineup";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/createLineup")
	public String addLineup(Model model,
							Lineup lineup,
							@PathVariable("location") String locationName, 
							@PathVariable("area") String areaName) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		List<Contract> contracts = locationManager.findByName().toList();
		List<Festival> festivals = Lists.newArrayList(locationManager.findFestivals().iterator());
		
		model.addAttribute("location", location);
		model.addAttribute("area", area);
		model.addAttribute("lineup", lineup);
		model.addAttribute("festivalList", festivals);
		model.addAttribute("contractList", contracts);

		return "createLineup";
	}

	@PostMapping("/location/{location}/area/{area}/stage/{stage}/lineup/{lineup}")
	public String editLineup(	@Valid Lineup lineup,
								@RequestParam("contract") String contract,
								@PathVariable("location") String locationName,
								@PathVariable("area") String areaName,
								@PathVariable("stage") String stageName) {
		//TODO
		return "redirect:lineup";
	}

	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup/{lineup}")
	public String editLineup(	Model model,
								@PathVariable("location") String locationName,
								@PathVariable("area") String areaName,
								@PathVariable("stage") String stageName,
								@PathVariable("lineup") String lineupIdAsString) {
		
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
	
	@GetMapping("/location/{location}/area/{area}/stage/{stage}/lineup/{lineup}/delete")
	public String deleteLineup(	@PathVariable("location") String locationName,
								@PathVariable("area") String areaName,
								@PathVariable("stage") String stageName,
								@PathVariable("lineup") String lineupIdAsString) {
		
		Location location = locationManager.findByName(locationName);
		Area area = locationManager.findByName(location, areaName);
		Stage stage = locationManager.findByName(area, stageName);
		long lineupId = Long.parseLong(lineupIdAsString);
		Lineup lineup = locationManager.findById(stage, lineupId);
		
		locationManager.deleteLineupById(lineup.getId());

		return "redirect:/location/" + location.getName() + "/area/" + area.getZone() + "/stage/" + stage.getName() + "/lineup";
	}
}