package festivalmanager.festival;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FestivalManager {
	private final FestivalRepository festivalRepository;

	public FestivalManager(FestivalRepository festivalRepository) {
		this.festivalRepository = festivalRepository;
	}

	public Iterable<Festival> findAll() {
		return festivalRepository.findAll();
	}

	public Optional<Festival> findById(long id) {
		return festivalRepository.findById(id);
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
