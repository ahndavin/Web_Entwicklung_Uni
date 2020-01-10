package festivalmanager.ticket;

import javax.persistence.Entity;

@Entity
public class Campingticket extends Ticket{

    public Campingticket(String name, javax.money.MonetaryAmount price){
        super(name, price);
        this.sort = Sort.CAMPINGTICKET;
    }
}
