package festivalmanager.contract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;



@Service
public class ContractManager {
	ArrayList<Contract> allContracts;
	ArrayList<String> allStages;
	HashMap<String, Contract> contractsPerStage;


	public ContractManager(){
	 this.allContracts = new ArrayList<>();
	 this.allStages = new ArrayList<>();
	 this.contractsPerStage = new HashMap<>();

	}
	/*public int getOverallCost(){
		int cost = 0;
		for(Contract allCosts : allContracts){
			cost = cost + allCosts.getPrice() + (allCosts.getTechniciansCount()*allCosts.getTechniciansHourlyWage()*allCosts.getTechniciansWorkingHours());
		}
		return cost;
	}
	 */

	public void addContract(Contract contract){
		allContracts.add(contract);
	}
	public void removeContract(Contract contract){
		allContracts.remove(contract);
	}
	public void addStage(String stage){
		allStages.add(stage);
	}
	public void removeStage(String stage){
		allStages.remove(stage);

	}
	public void bindContractToStage(String stage, Contract contract){
		this.contractsPerStage.put(stage, contract);
	}
	public void removeContractFromStage(String stage, Contract contract){
		this.contractsPerStage.remove(stage, contract);
	}
	public ArrayList<Contract> getAllContracts(){
		return allContracts;
	}


}
