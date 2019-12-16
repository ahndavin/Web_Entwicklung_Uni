package festivalmanager.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Campingticket{

    private @Id @GeneratedValue Long id;
    private String name;
    private javax.money.MonetaryAmount price;
    private Sort sort;
    private boolean used = false;

    public Campingticket(String name, javax.money.MonetaryAmount price){
        this.name = name;
        this.price = price;
        this.sort = Sort.CAMPINGTICKET;
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

    public boolean getUsed(){
        return this.used;
    }

    public void setUsed(boolean used){
        this.used = used;
    }
}