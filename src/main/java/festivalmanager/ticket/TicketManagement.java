package festivalmanager.ticket;

import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.economics.EconomicManager;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;

@Service
@Transactional
public class TicketManagement{

    private final DayticketRepository dayticketRepository;
    private final CampingticketRepository campingticketRepository;
    private final EconomicManager economicManager;
    private final FestivalManager festivalManager;

    public TicketManagement(FestivalManager festivalManager, 
    DayticketRepository dayticketRepository, CampingticketRepository campingticketRepository, EconomicManager economicManager){
		Assert.notNull(festivalManager, "FestivalManager must not be null!");
		Assert.notNull(dayticketRepository, "TagesticketRepository must not be null!");
		Assert.notNull(campingticketRepository, "CampingticketRepository must not be null!");
		Assert.notNull(economicManager, "EconomicManager must not be null!");
        
        this.festivalManager = festivalManager;
        this.dayticketRepository = dayticketRepository;
        this.campingticketRepository = campingticketRepository;
        this.economicManager = economicManager;
    }

    public Iterable<Festival> findAll() {
        return festivalManager.findAll();
    }

    public Festival findById(Long id) {
        return festivalManager.findById(id).isPresent() ? festivalManager.findById(id).get() : null;
      }

      public boolean dayTicketIsAvailable(Sort sort, Festival festival){
        if(festival.isSellingTickets() != false && sort == Sort.DAYTICKET && 
        festival.getTicketBuilder().getAmountDaytickets().isGreaterThan(Quantity.NONE)){
            return true;
        }
        return false;

    }

    public boolean campingTicketIsAvailable(Sort sort, Festival festival){
        if(festival.isSellingTickets() != false && sort == Sort.CAMPINGTICKET && 
        festival.getTicketBuilder().getAmountCampingtickets().isGreaterThan(Quantity.NONE)){
            return true;
        }
        return false;

    }

    public Dayticket buyDayticket(Festival festival){
		Quantity newQuantity = festival.getTicketBuilder().getAmountDaytickets().subtract(Quantity.of(1));

        economicManager.add(festival.getTicketBuilder().getPriceDayticket(), "Day Ticket", festival);

        festival.getTicketBuilder().setAmountDaytickets(newQuantity);
        Dayticket ticket = festival.getTicketBuilder().createDayticket();
        dayticketRepository.save(ticket);
        return ticket;
    }

    public Campingticket buyCampingticket(Festival festival){
        Quantity newQuantity = festival.getTicketBuilder().getAmountCampingtickets().subtract(Quantity.of(1));

        economicManager.add(festival.getTicketBuilder().getPriceCampingticket(), "Camping Ticket", festival);

        festival.getTicketBuilder().setAmountCampingtickets(newQuantity);
        Campingticket ticket = festival.getTicketBuilder().createCampingticket();
        campingticketRepository.save(ticket);
        return ticket;
    }

    public boolean checkTicket(String sort_str, Long id){
        if(sort_str.equals("Campingticket")){
            Campingticket ticket = campingticketRepository.findById(id).isPresent() ? campingticketRepository.findById(id).get() : null;
            return ticket.getUsed();    
        } else{
            Dayticket ticket = dayticketRepository.findById(id).isPresent() ? dayticketRepository.findById(id).get() : null;
            return ticket.getUsed();    
        }
    }
}