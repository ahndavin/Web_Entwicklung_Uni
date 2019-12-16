package festivalmanager.contract;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import festivalmanager.contract.Contract;
import festivalmanager.contract.ContractsRepository;

@Controller
@RequestMapping("/contracts/")
public class ContractController {

	private final ContractsRepository contractsRepository;

	@Autowired
	public ContractController(ContractsRepository contractsRepository) {
		this.contractsRepository = contractsRepository;
	}

	@GetMapping("create")
	public String showSignUpForm(Contract contract) {
		return "createContract";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("contract", contractsRepository.findAll());
		return "contractManagement";
	}

	@PostMapping("add")
	public String addContract(@Valid Contract contract, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createContract";
		}

		contractsRepository.save(contract);
		return "redirect:list";
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
}
