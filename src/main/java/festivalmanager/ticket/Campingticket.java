package festivalmanager.ticket;

import javax.persistence.Entity;

import festivalmanager.festival.Festival;

@Entity
public class Campingticket extends Ticket{

    public Campingticket(String name, javax.money.MonetaryAmount price, Festival festival){
        super(name, price, festival);
        this.sort = Sort.CAMPINGTICKET;
    }
}
