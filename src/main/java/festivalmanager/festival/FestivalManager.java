package festivalmanager.festival;

import org.springframework.stereotype.Component;

import java.util.List;
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
			if(festival.getId() == f.getId()) {
				continue;
			}

			if(Festival.areAtTheSameTimeAndPlace(festival, f)) {
				return null;
			}
		}

		return festivalRepository.save(festival);
	}

	public Festival update(Festival festival) {
		Optional<Festival> festivalOptional = festivalRepository.findById(festival.getId());

		if(festivalOptional.isPresent()) {
			Festival f = festivalOptional.get();

			festival.editPlan().addAll(f.editPlan());
			festival.editInventory().addAll(f.editInventory());

			return save(festival);
		}

		return null;
	}

	public Festival updatePlan(Festival festival) {
		Optional<Festival> festivalOptional = festivalRepository.findById(festival.getId());

		if(festivalOptional.isPresent()) {
			Festival f = festivalOptional.get();

			f.editPlan().clear();
			f.editPlan().addAll(festival.editPlan());

			return save(f);
		}

		return null;
	}

	public Festival updateInventory(Festival festival) {
		Optional<Festival> festivalOptional = festivalRepository.findById(festival.getId());

		if(festivalOptional.isPresent()) {
			Festival f = festivalOptional.get();

			f.editInventory().clear();
			f.editInventory().addAll(festival.editInventory());

			return save(f);
		}

		return null;
	}

	public int getCount() {
		int counter = 0;
		for (Festival ignored : findAll()) {
			counter++;
		}

		return counter;
	}
}
