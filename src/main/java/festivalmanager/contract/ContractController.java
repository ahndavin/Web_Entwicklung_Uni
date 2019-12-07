package festivalmanager.contract;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContractController {

	private ContractManager contractManager;

	public ContractController(ContractManager contractManager){
		this.contractManager = contractManager;
	}


	@GetMapping("/contractManagement")
	public String contractManagement(Model model){
		model.addAttribute("contractList", contractManager.getAllContracts());
		return "contractManagement";
	}


	@PostMapping("/createContract")
	public String addProduct(@Valid Contract contract) {

		contractManager.addContract(contract);

		return "redirect:/contractManagement";
	}

	@GetMapping("/createContract")
	public String addProduct(Model model, Contract contract) {

		model.addAttribute("contract", contract);

		return "createContract";
	}

}
