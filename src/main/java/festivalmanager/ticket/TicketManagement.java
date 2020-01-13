package festivalmanager.ticket;

import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.economics.EconomicManager;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalIdForm;
import festivalmanager.festival.FestivalManager;
import festivalmanager.location.LocationManager;

@Service
@Transactional
public class TicketManagement{

    private final DayticketRepository dayticketRepository;
    private final CampingticketRepository campingticketRepository;
    private final EconomicManager economicManager;
    private final FestivalManager festivalManager;
    private final LocationManager locationManager;

    public TicketManagement(FestivalManager festivalManager, 
    						DayticketRepository dayticketRepository,
    						CampingticketRepository campingticketRepository,
    						EconomicManager economicManager,
    						LocationManager locationManager){
		Assert.notNull(festivalManager, "FestivalManager must not be null!");
		Assert.notNull(dayticketRepository, "TagesticketRepository must not be null!");
		Assert.notNull(campingticketRepository, "CampingticketRepository must not be null!");
		Assert.notNull(economicManager, "EconomicManager must not be null!");
        
        this.festivalManager = festivalManager;
        this.dayticketRepository = dayticketRepository;
        this.campingticketRepository = campingticketRepository;
        this.economicManager = economicManager;
        this.locationManager = locationManager;
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

    public void buyTicket(FestivalIdForm festivalIdForm){
        Festival festival = findById(festivalIdForm.getId());
        if(dayTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.DAYTICKET){
            buyDayticket(festival);
        }
        if(campingTicketIsAvailable(festivalIdForm.getSort(), festival) && festivalIdForm.getSort() == Sort.CAMPINGTICKET){
            buyCampingticket(festival);
        }      
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

    public Ticket checkTicket(String festival, String sort_str, Long id){
        if(sort_str.equals("Campingticket")){
            Campingticket ticket = campingticketRepository.findById(id).isPresent() ? campingticketRepository.findById(id).get() : null;
            if(ticket != null) {
            	if(!ticket.getUsed()) {
            		ticket.setUsed(true);
            		locationManager.findByName(festivalManager.findByName(festival).getLocation()).setCurrVisitors(1);
            	}
            	campingticketRepository.save(ticket);
            }
            return ticket;
        } else{
            Dayticket ticket = dayticketRepository.findById(id).isPresent() ? dayticketRepository.findById(id).get() : null;
            if(ticket != null) {
            	if(!ticket.getUsed()) {
            		ticket.setUsed(true);
            		locationManager.findByName(festivalManager.findByName(festival).getLocation()).setCurrVisitors(1);
            	}
            	dayticketRepository.save(ticket);
            }
            return ticket;
        }
    }
}