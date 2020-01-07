package festivalmanager.location;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends CrudRepository<Stage, Long> {
    List<Stage> findAll();
}
