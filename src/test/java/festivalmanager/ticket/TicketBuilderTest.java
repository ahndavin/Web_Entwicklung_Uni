package festivalmanager.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketBuilderTest{

    @Test
    public void checkAllTheGetters(){
        TicketBuilder ticketBuilder = new TicketBuilder(2, 100, 50, 20, 50);

        assertEquals(Quantity.of(100, Metric.UNIT), ticketBuilder.getAmountDaytickets());
        assertEquals(Quantity.of(50, Metric.UNIT), ticketBuilder.getAmountCampingtickets());
        assertEquals(Money.of(20, "EUR"), ticketBuilder.getPriceDayticket());
        assertEquals(Money.of(50, "EUR"), ticketBuilder.getPriceCampingticket());
    }

    @Test
    public void checkAllTheSetters(){
        TicketBuilder ticketBuilder = new TicketBuilder(3, 200, 75, 3, 10);

        ticketBuilder.setAmountCampingtickets(Quantity.of(76, Metric.UNIT));
        ticketBuilder.setAmountDaytickets(Quantity.of(1500, Metric.UNIT));
        ticketBuilder.setPriceCampingticket(Money.of(11, "EUR"));
        ticketBuilder.setPriceDayticket(Money.of(2, "EUR"));

        assertEquals(Quantity.of(1500, Metric.UNIT), ticketBuilder.getAmountDaytickets());
        assertEquals(Quantity.of(76, Metric.UNIT), ticketBuilder.getAmountCampingtickets());
        assertEquals(Money.of(2, "EUR"), ticketBuilder.getPriceDayticket());
        assertEquals(Money.of(11, "EUR"), ticketBuilder.getPriceCampingticket());
    }
}