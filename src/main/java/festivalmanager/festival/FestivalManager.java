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

	public Festival save(Festival festival) {
		Iterable<Festival> festivals = festivalRepository.findAll();

		for(Festival f : festivals) {
			if(festival.getId() == f.getId()) continue;

			if(Festival.areAtTheSameTimeAndPlace(festival, f)) {
				return null;
			}
		}

		return festivalRepository.save(festival);
	}

	public int getCount() {
		int counter = 0;
		for (Festival ignored : findAll()) {
			counter++;
		}

		return counter;
	}
}
