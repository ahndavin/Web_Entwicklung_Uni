package festivalmanager.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketTest{

    @Test
    public void checkAllTheCampingGetters(){
        Campingticket ticket = new Campingticket("Camping", Money.of(20, "EUR"));

        assertEquals("Camping", ticket.getName());
        assertEquals(Money.of(20, "EUR"), ticket.getPrice());
        assertEquals(Sort.CAMPINGTICKET, ticket.getSort());
        assertEquals(false, ticket.getUsed());
    }

    @Test
    public void checkAllTheDayGetters(){
        Dayticket ticket = new Dayticket("Dayticket", Money.of(3, "EUR"));

        assertEquals("Dayticket", ticket.getName());
        assertEquals(Money.of(3, "EUR"), ticket.getPrice());
        assertEquals(Sort.DAYTICKET, ticket.getSort());
        assertEquals(false, ticket.getUsed());
    }

    @Test
    public void checkAllTheCampingSetters(){
        Campingticket ticket = new Campingticket("Camp", Money.of(12, "EUR"));

        ticket.setUsed(true);

        assertEquals(true, ticket.getUsed());
    }

    @Test
    public void checkAllTheDaySetters(){
        Dayticket ticket = new Dayticket("Ticket", Money.of(5, "EUR"));

        ticket.setUsed(true);

        assertEquals(true, ticket.getUsed());
    }
}