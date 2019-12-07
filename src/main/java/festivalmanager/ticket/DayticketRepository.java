package festivalmanager.ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface DayticketRepository extends CrudRepository<Dayticket, Long> {

    @Override
    Streamable<Dayticket> findAll();
}
