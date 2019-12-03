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
    private final CampingticketRepository campingticketRepository;

    public TicketManagement(FestivalRepository festivalrepository, DayticketRepository dayticketRepository, CampingticketRepository campingticketRepository){
		Assert.notNull(festivalrepository, "FestivalRepository must not be null!");
		Assert.notNull(dayticketRepository, "TagesticketRepository must not be null!");
		Assert.notNull(campingticketRepository, "CampingticketRepository must not be null!");
        
        this.festivalrepository = festivalrepository;
        this.dayticketRepository = dayticketRepository;
        this.campingticketRepository = campingticketRepository;
    }

    public Streamable<Festival> findAll(){
        return festivalrepository.findAll();
    }

    public Festival findById(Long id) {
        return festivalrepository.findById(id).isPresent() ? festivalrepository.findById(id).get() : null;
      }

    public boolean isAvailable(Sort sort, Festival festival){
        if(festival.isSellingTickets() == false){
            return false;
        }
        else{
            if(sort == Sort.DAYTICKET){
                if(festival.getAmountDaytickets() > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            if(sort == Sort.CAMPINGTICKET){
                if(festival.getAmountCampingtickets() > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            return false;
        }
    }

    public Dayticket buyDayticket(Festival festival){
        festival.setAmountDaytickets(festival.getAmountDaytickets() -1);
        Dayticket ticket = new Dayticket(festival.getName(), festival.getPriceDayticket());
        dayticketRepository.save(ticket);
        return ticket;
    }

    public Campingticket buyCampingticket(Festival festival){
        festival.setAmountCampingtickets(festival.getAmountCampingtickets() -1);
        Campingticket ticket = new Campingticket(festival.getName(), festival.getPriceCampingticket());
        campingticketRepository.save(ticket);
        return ticket;
    }
}