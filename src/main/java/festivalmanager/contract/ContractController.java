package festivalmanager.contract;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import festivalmanager.contract.Contract;
import festivalmanager.contract.ContractsRepository;
import festivalmanager.economics.EconomicManager;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalIdForm;
import festivalmanager.festival.FestivalManager;

@Controller
@RequestMapping("/contracts/")
public class ContractController {

	private final ContractsRepository contractsRepository;
    public final EconomicManager economicManager;
	public final FestivalManager festivalManager;
	private Festival festivalForCreation;

	@Autowired
	public ContractController(ContractsRepository contractsRepository, EconomicManager economicManager, FestivalManager festivalManager) {
		this.contractsRepository = contractsRepository;
		this.economicManager = economicManager;
		this.festivalManager = festivalManager;
	}

	@GetMapping("create")
	public String showSignUpForm(Contract contract) {
		return "createContract";
	}

	@GetMapping("list")
	public String showUpdateForm(@Valid @ModelAttribute("form") FestivalIdForm festivalIdForm, Errors result, Model model){
		festivalForCreation = economicManager.findById(festivalIdForm.getId());
		if(result.hasErrors()){
			return "festivals";
		}

		model.addAttribute("contract", festivalForCreation.getContractList().getList());

		return "contractManagement";
	}

	@PostMapping("add")

	public String addContract(@Valid @ModelAttribute Contract contract, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createContract";
		}
		contractsRepository.save(contract);
		festivalForCreation.getContractList().add(contract);
		festivalManager.save(festivalForCreation);
		//economicManager.add(contract.totalCost(), contract.getName(), festivalForCreation);
		return "redirect:/festivals";

	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Contract contract = contractsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid Contract Id:" + id));
		model.addAttribute("contract", contract);
		return "update-Contract";
	}

	@PostMapping("update/{id}")
	public String updateContract(@PathVariable("id") long id, @Valid Contract contract, BindingResult result, Model model) {
		if (result.hasErrors()) {
			contract.setId(id);
			return "update-Contract";
		}

		contractsRepository.save(contract);
		model.addAttribute("contract", contractsRepository.findAll());
		return "contractManagement";
	}

	@GetMapping("delete/{id}")
	public String deleteContract(@PathVariable("id") long id, Model model) {
		Contract contract = contractsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid contract Id:" + id));
		contractsRepository.delete(contract);
		model.addAttribute("contract", contractsRepository.findAll());
		return "contractManagement";
	}
	public ContractsRepository getContracts(){
		return this.contractsRepository;
	}
}
