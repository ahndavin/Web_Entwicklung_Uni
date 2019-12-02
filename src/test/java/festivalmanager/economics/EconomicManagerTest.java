package festivalmanager.economics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class EconomicManagerTest{

    @Test

    //U302
    public void shouldCreateEconomicEntry(){
        EconomicManager ecoManager = new EconomicManager();

        javax.money.MonetaryAmount value = Money.of(15, "EUR");
        ecoManager.addEntry(value, "15");

        ArrayList<EconomicEntry> result = new ArrayList<EconomicEntry>();
        EconomicEntry element = new EconomicEntry(value, "15");
        result.add(element);

        assertEquals(result, ecoManager.getAll(), "EconomicEntry should be added");
    }

    @Test

    public void shouldCreateEntryWithInteger(){
        EconomicManager ecoManager = new EconomicManager();

        ecoManager.addEntry(15, "15");

        ArrayList<EconomicEntry> result = new ArrayList<EconomicEntry>();
        javax.money.MonetaryAmount value = Money.of(15, "EUR");
        EconomicEntry element = new EconomicEntry(value, "15");
        result.add(element);

        assertEquals(result, ecoManager.getAll(), "EconomicEntry should be added");
    }

    @Test

    //U303
    public void shouldShowEmptyOverview(){
        EconomicManager ecoManager = new EconomicManager();
        ArrayList<EconomicEntry> result = new ArrayList<EconomicEntry>();

        assertEquals(result, ecoManager.getAll(), "EconomicEntry should be added");

    }




}