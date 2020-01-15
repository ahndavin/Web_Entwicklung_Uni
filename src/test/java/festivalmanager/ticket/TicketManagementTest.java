package festivalmanager.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import festivalmanager.economics.EconomicManager;
import festivalmanager.festival.FestivalManager;
import festivalmanager.location.LocationManager;

@SpringBootTest
public class TicketManagementTest{

    @Autowired  TicketRepository ticketRepository;
    @Autowired EconomicManager economicManager;
    @Autowired FestivalManager festivalManager;
    @Autowired LocationManager locationManager;


}