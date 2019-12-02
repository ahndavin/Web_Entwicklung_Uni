package festivalmanager.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Dayticket{

    private @Id @GeneratedValue Long id;
    private String name;
    private javax.money.MonetaryAmount price;
    private Sort sort;

    public enum Sort {DAYTICKET, CAMPING};

    public Dayticket(String name, javax.money.MonetaryAmount price){
        this.name = name;
        this.price = price;
        this.sort = Sort.DAYTICKET;
    }

    public String getName(){
        return this.name;
    }

    public javax.money.MonetaryAmount getPrice(){
        return this.price;
    }

    public Sort getSort(){
        return this.sort;
    }

    public Long getId(){
        return this.id;
    }
}