package festivalmanager.festival;

import org.springframework.stereotype.Component;

@Component
public class FestivalManager {
	private final FestivalRepository festivalRepository;

	public FestivalManager(FestivalRepository festivalRepository) {
		this.festivalRepository = festivalRepository;
	}

	public Iterable<Festival> findAll() {
		return festivalRepository.findAll();
	}

	public boolean save(Festival festival) {
		Iterable<Festival> festivals = festivalRepository.findAll();

		for(Festival f : festivals) {
			if(Festival.areAtTheSameTimeAndPlace(festival, f)) {
				return false;
			}
		}

		festivalRepository.save(festival);

		return true;
	}
}
