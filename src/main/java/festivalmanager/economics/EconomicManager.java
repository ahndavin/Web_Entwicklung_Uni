package festivalmanager.economics;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EconomicManager{

    private final Accountancy accountency;

    public EconomicManager(Accountancy accountency) {
        this.accountency = accountency;
    }

	public void add(int amount, String description){
        MonetaryAmount value = Money.of(amount, "EUR");
        AccountancyEntry entry = new AccountancyEntry(value, description);
        accountency.add(entry);
    }

    public Streamable<AccountancyEntry> getAll(){
        return accountency.findAll();
    }

    public MonetaryAmount getRevenues(){
        MonetaryAmount sum = Money.of(0, "EUR");
        for (AccountancyEntry entry : getAll()){
            if(entry.isRevenue() == true){
                sum = sum.add(entry.getValue());
            }
        }
        return sum;
    }

    public MonetaryAmount getExpenses(){
        MonetaryAmount sum = Money.of(0, "EUR");
        for (AccountancyEntry entry : getAll()){
            if(entry.isExpense() == true){
                sum = sum.add(entry.getValue());
            }
        }
        return sum;
    }

    public MonetaryAmount getSum(){
        MonetaryAmount sum = Money.of(0, "EUR");
        sum = sum.add(getRevenues());
        sum = sum.subtract(getExpenses());
        return sum;
    }
}