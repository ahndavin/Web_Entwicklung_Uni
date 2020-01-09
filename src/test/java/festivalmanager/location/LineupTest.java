package festivalmanager.location;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import festivalmanager.contract.Contract;

@SpringBootTest
public class LineupTest{

    @Test
    public void checkAllTheGetters(){
        Lineup lineup = new Lineup("01.12.2020");

        assertEquals("01.12.2020", lineup.getDate());
    }

    @Test
    public void checkAllTheSetters(){
        Lineup lineup = new Lineup("12.02.2020");

        lineup.setDate("12.12.2020");
        lineup.setStageId(1234567890);

        assertEquals("12.12.2020", lineup.getDate());
        assertEquals(1234567890, lineup.getStageId());
    }

    @Test
    public void shouldDealWithContracts(){
        Lineup lineup = new Lineup("12.02.2020");
        Contract contract = new Contract("contract", "artist", 100, false, 10, 13, 22);

        lineup.setArtist(contract);

        assertEquals(contract, lineup.getArtist());
    }

}