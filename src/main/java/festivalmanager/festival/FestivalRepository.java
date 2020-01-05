package festivalmanager.festival;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long> {

    @Override
    Streamable<Festival> findAll();

	Festival findByName (String name);
}
