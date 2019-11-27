package festivalmanager.economics;

import org.salespointframework.accountancy.AccountancyEntry;

public class EconomicEntry extends AccountancyEntry{
    private String description;
    private javax.money.MonetaryAmount value;

    public EconomicEntry(javax.money.MonetaryAmount value, String description){
        this.description = description;
        this.value = value;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public javax.money.MonetaryAmount getValue(){
        return this.value;
    }

    public void setValue(javax.money.MonetaryAmount value){
        this.value = value;
    }
}