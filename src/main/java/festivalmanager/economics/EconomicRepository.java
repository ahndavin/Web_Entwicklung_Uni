package festivalmanager.economics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicRepository extends CrudRepository<EconomicEntry, Long> {

    @Override
    Streamable<EconomicEntry> findAll();
}
