package festivalmanager.ticket;

import org.salespointframework.quantity.Quantity;
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

      public boolean dayTicketIsAvailable(Sort sort, Festival festival){
        if(festival.isSellingTickets() != false && sort == Sort.DAYTICKET && festival.getTicketBuilder().getAmountDaytickets().isGreaterThan(Quantity.NONE)){
            return true;
        }
        return false;

    }

    public boolean campingTicketIsAvailable(Sort sort, Festival festival){
        if(festival.isSellingTickets() != false && sort == Sort.CAMPINGTICKET && festival.getTicketBuilder().getAmountCampingtickets().isGreaterThan(Quantity.NONE)){
            return true;
        }
        return false;

    }
    

    public Dayticket buyDayticket(Festival festival){
		Quantity newQuantity = festival.getTicketBuilder().getAmountDaytickets().subtract(Quantity.of(1));

		festival.getTicketBuilder().setAmountDaytickets(newQuantity);
        Dayticket ticket = new Dayticket(festival.getName(), festival.getTicketBuilder().getPriceDayticket());
        dayticketRepository.save(ticket);
        return ticket;
    }

    public Campingticket buyCampingticket(Festival festival){
		Quantity newQuantity = festival.getTicketBuilder().getAmountCampingtickets().subtract(Quantity.of(1));

        festival.getTicketBuilder().setAmountCampingtickets(newQuantity);
        Campingticket ticket = new Campingticket(festival.getName(), festival.getTicketBuilder().getPriceCampingticket());
        campingticketRepository.save(ticket);
        return ticket;
    }
}