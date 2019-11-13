
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface AccountRepository extends CrudRepository<Account, Long> {

	@Override
	Streamable<Account> findAll();
}
