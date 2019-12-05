/*package festivalmanager.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class TicketTest{

    @Test

    //501
    public void shouldCreateTickets(){
        TicketManager ticketManager = new TicketManager();
        
        Ticket ticket1 = new Ticket("DAYTICKET");
        ticketManager.addTicket(ticket1);

        Ticket ticket2 = new Ticket("CAMPING");
        ticketManager.addTicket(ticket2);

        HashMap<String, Ticket> result = new HashMap<String, Ticket>();
        result.put(ticket1.getNumber(), ticket1);
        result.put(ticket2.getNumber(), ticket2);

        assertEquals(result, ticketManager.getAllTickets(), "All tickets need to be added to the TicketManager");
    }

    //U502
    public void shouldBeAbleToFindTickets(){
        TicketManager ticketManager = new TicketManager();
        
        Ticket ticket1 = new Ticket("DAYTICKET");
        ticketManager.addTicket(ticket1);

        assertEquals(ticket1, ticketManager.findTicket(ticket1.getNumber()), "Ticket should be found by TicketManager");
    }
} */