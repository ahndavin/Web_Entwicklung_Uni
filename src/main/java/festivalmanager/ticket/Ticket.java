package festivalmanager.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import festivalmanager.festival.Festival;

@Entity
public class Ticket{

    private @Id @GeneratedValue Long id;
    private String name;
    private javax.money.MonetaryAmount price;
    protected Sort sort;
    private boolean used = false;
    @OneToOne private Festival festival;

    public Ticket(String name, javax.money.MonetaryAmount price, Festival festival){
        this.name = name;
        this.price = price;
        this.festival = festival;
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

    public Festival getFestival(){
        return this.festival;
    }

    public void setUsed(boolean used){
        this.used = used;
    }
}

//TODO Implement possibility to check tickets within the frontend