package festivalmanager.economics;

import java.util.ArrayList;

import javax.persistence.Embeddable;

import org.salespointframework.accountancy.AccountancyEntry;

@Embeddable
public class EconomicList {

    private ArrayList<AccountancyEntry> accountancyEntryList = new ArrayList<>();

    public EconomicList(){
    }

    public ArrayList<AccountancyEntry> getList(){
        return accountancyEntryList;
    }

    public void add(AccountancyEntry accountancyEntry){
        accountancyEntryList.add(accountancyEntry);
    }
}