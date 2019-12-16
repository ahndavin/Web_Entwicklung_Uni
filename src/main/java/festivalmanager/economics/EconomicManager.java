package festivalmanager.economics;

import javax.money.MonetaryAmount;

import java.util.List;

import org.javamoney.moneta.Money;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalRepository;

@Service
@Transactional
public class EconomicManager{

    private final Accountancy accountency;
    private final FestivalRepository festivalRepository;

    public EconomicManager(Accountancy accountency, FestivalRepository festivalRepository) {
        this.accountency = accountency;
        this.festivalRepository = festivalRepository;
    }

    public Festival findById(Long id) {
        return festivalRepository.findById(id).isPresent() ? festivalRepository.findById(id).get() : null;
      }

	public void add(int amount, String description, Festival festival){
        MonetaryAmount value = Money.of(amount, "EUR");
        AccountancyEntry entry = new AccountancyEntry(value, description);
        accountency.add(entry);
        festival.getEconomicList().add(entry);
    }

    public void add(Money value, String description, Festival festival){
        AccountancyEntry entry = new AccountancyEntry(value, description);
        accountency.add(entry);
        festival.getEconomicList().add(entry);
    }

    public List<AccountancyEntry> getAll(Festival festival){
        return festival.getEconomicList();
    }

    public MonetaryAmount getRevenues(Festival festival){
        List<AccountancyEntry> entrys = festival.getEconomicList();
        MonetaryAmount sum = Money.of(0, "EUR");
        for (AccountancyEntry entry : entrys){
            if(entry.isRevenue() == true){
                sum = sum.add(entry.getValue());
            }
        }
        return sum;
    }

    public MonetaryAmount getExpenses(Festival festival){
        List<AccountancyEntry> entrys = festival.getEconomicList();
        MonetaryAmount sum = Money.of(0, "EUR");
        for (AccountancyEntry entry : entrys){
            if(entry.isExpense() == true){
                sum = sum.add(entry.getValue());
            }
        }
        return sum;
    }

    public MonetaryAmount getSum(Festival festival){
        MonetaryAmount sum = Money.of(0, "EUR");
        sum = sum.add(getRevenues(festival));
        sum = sum.subtract(getExpenses(festival));
        return sum;
    }
}