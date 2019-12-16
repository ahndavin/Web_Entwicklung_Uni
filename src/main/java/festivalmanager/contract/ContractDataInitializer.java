package festivalmanager.contract;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(20)
public class ContractDataInitializer implements DataInitializer {
	private ContractsRepository contractsRepository;

	public ContractDataInitializer(ContractsRepository contractsRepository) {
		Assert.notNull(contractsRepository, "Locations must not be null!");
		this.contractsRepository = contractsRepository;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void initialize() {
		if (contractsRepository.findAll().iterator().hasNext()) {
			return;
		}

		contractsRepository.save(new Contract("name", "artist", "price", "accepted", "technicianscount",
			"workinghours", "workerswage"));
		contractsRepository.save(new Contract("name", "artist12", "price", "accepted", "technicianscount",
			"workinghours", "workerswage"));
		contractsRepository.save(new Contract("name", "artist123", "price", "accepted", "technicianscount",
			"workinghours", "workerswage"));
	}
}