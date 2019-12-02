package festivalmanager.ticket;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalRepository;;

@Service
@Transactional
public class TicketManagement{

    private final FestivalRepository festivalrepository;
    private final DayticketRepository dayticketRepository;
    //private final CampingticketRepository campingticketRepository;

    public TicketManagement(FestivalRepository festivalrepository, DayticketRepository dayticketRepository){
		Assert.notNull(festivalrepository, "FestivalRepository must not be null!");
		Assert.notNull(dayticketRepository, "TagesticketRepository must not be null!");
		//Assert.notNull(campingticketRepository, "CampingticketRepository must not be null!");
        
        this.festivalrepository = festivalrepository;
        this.dayticketRepository = dayticketRepository;
        //this.campingticketRepository = campingticketRepository;
    }

    public Streamable<Festival> findAll(){
        return festivalrepository.findAll();
    }
}