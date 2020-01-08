package festivalmanager.festival;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
interface FestivalRepository extends CrudRepository<Festival, Long> {
	Festival findByName (String name);
}
