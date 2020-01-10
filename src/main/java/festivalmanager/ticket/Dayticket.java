package festivalmanager.ticket;

import javax.persistence.Entity;

@Entity 
public class Dayticket extends Ticket{

    public Dayticket(String name, javax.money.MonetaryAmount price){
        super(name, price);
        this.sort = Sort.DAYTICKET;
    }
}