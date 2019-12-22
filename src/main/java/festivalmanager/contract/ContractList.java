package festivalmanager.contract;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class ContractList {

    @OneToMany
    private List<Contract> contractList;

    public ContractList(){
        contractList = new ArrayList<>();
    }

    public List<Contract> getList(){
        return contractList;
    }

    public void add(Contract contract){
        contractList.add(contract);
    }
}