package festivalmanager.economics;

import java.util.ArrayList;

import org.javamoney.moneta.Money;
//import org.salespointframework.accountancy.Accountancy;

public class EconomicManager{
    private static ArrayList<EconomicEntry> allEntrys;

    public EconomicManager(){
        allEntrys = new ArrayList<EconomicEntry>();
    }

    public static void addEntry(javax.money.MonetaryAmount value, String description){
        EconomicEntry ecoentry = new EconomicEntry(value, description);
        allEntrys.add(ecoentry);
    }

    public static void addEntry(int value, String description){
        javax.money.MonetaryAmount amount = Money.of(value, "EURO");
        EconomicEntry ecoentry = new EconomicEntry(amount, description);
        allEntrys.add(ecoentry);
    }

    public ArrayList<EconomicEntry> getAll(){
        return allEntrys;        
    }
}