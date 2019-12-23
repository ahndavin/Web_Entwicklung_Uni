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
        System.out.println(contractList.toString());
    }

	public void delete(Contract delete) {
        List<Contract> contractList2 = new ArrayList<>();
        for(Contract contract : contractList){
            if(contract != delete){
                contractList2.add(contract);
            }
        }
        contractList = contractList2;
	}
	public int size(){
    	return contractList.size();
	}
}