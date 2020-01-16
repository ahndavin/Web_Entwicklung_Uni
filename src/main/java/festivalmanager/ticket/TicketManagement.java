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

    private final TicketRepository ticketRepository;
    private final EconomicManager economicManager;
    private final FestivalManager festivalManager;
    private final LocationManager locationManager;

    public TicketManagement(FestivalManager festivalManager,
    						TicketRepository ticketRepository,
    						EconomicManager economicManager,
    						LocationManager locationManager){
		Assert.notNull(festivalManager, "FestivalManager must not be null!");
		Assert.notNull(ticketRepository, "TicketRepository must not be null!");
		Assert.notNull(economicManager, "EconomicManager must not be null!");

        this.festivalManager = festivalManager;
        this.ticketRepository = ticketRepository;
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

    public Ticket buyTicket(FestivalIdForm festivalIdForm){
        Festival festival = findById(festivalIdForm.getId());
        if(dayTicketIsAvailable(festivalIdForm.getSort(), festival)&&festivalIdForm.getSort()==Sort.DAYTICKET){
            Ticket ticket = buyDayticket(festival);
            return ticket;
        }
        if(campingTicketIsAvailable(festivalIdForm.getSort(),festival)&&festivalIdForm.getSort()==Sort.CAMPINGTICKET){
            Ticket ticket = buyCampingticket(festival);
            return ticket;
        }
        return null;
    }

    public Dayticket buyDayticket(Festival festival){
		Quantity newQuantity = festival.getTicketBuilder().getAmountDaytickets().subtract(Quantity.of(1));

        economicManager.add(festival.getTicketBuilder().getPriceDayticket(), "Day Ticket", festival);

        festival.getTicketBuilder().setAmountDaytickets(newQuantity);
        Dayticket ticket = festival.getTicketBuilder().createDayticket(festival);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Campingticket buyCampingticket(Festival festival){
        Quantity newQuantity = festival.getTicketBuilder().getAmountCampingtickets().subtract(Quantity.of(1));

        economicManager.add(festival.getTicketBuilder().getPriceCampingticket(), "Camping Ticket", festival);

        festival.getTicketBuilder().setAmountCampingtickets(newQuantity);
        Campingticket ticket = festival.getTicketBuilder().createCampingticket(festival);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket checkTicket(String festival, String sort_str, Long id){
        Ticket ticket = ticketRepository.findById(id).isPresent() ? ticketRepository.findById(id).get() : null;

        if(ticket!=null&&(!(ticket.getFestival().getName().equals(festival))||!(ticket.getSort().toString().equals(sort_str)))){
            ticket = null;
        }
        
        return ticket;
    }
    
    public Ticket setTicketStatus(Ticket ticket){
    	locationManager.findByName(ticket.getFestival().getLocation()).countVisitors(1);
        ticket.setUsed(true);
        ticketRepository.save(ticket);
        
        return ticket;
    }
}
