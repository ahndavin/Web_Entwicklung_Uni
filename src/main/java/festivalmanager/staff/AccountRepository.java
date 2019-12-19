package festivalmanager.staff;

import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Optional;

interface AccountRepository extends CrudRepository<Account, Long> {

	@Override
	Streamable<Account> findAll();


	Optional<Account> findByUserAccount(UserAccount userAccount);
}