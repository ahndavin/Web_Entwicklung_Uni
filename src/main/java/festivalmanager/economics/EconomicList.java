package festivalmanager.economics;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import org.salespointframework.accountancy.AccountancyEntry;

@Embeddable
public class EconomicList {

    @OneToMany
    private List<AccountancyEntry> accountancyEntryList;

    public EconomicList(){
        accountancyEntryList = new ArrayList<>();
    }

    public List<AccountancyEntry> getList(){
        return accountancyEntryList;
    }

    public void add(AccountancyEntry accountancyEntry){
        accountancyEntryList.add(accountancyEntry);
    }
}