package festivalmanager.staff;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class StaffDataInitializer implements DataInitializer{

	private static final Logger LOG = LoggerFactory.getLogger(StaffDataInitializer.class);

	private final UserAccountManager userAccountManager;
	private final AccountManager accountManager;


	public StaffDataInitializer(UserAccountManager userAccountManager, AccountManager accountManager) {

		this.userAccountManager = userAccountManager;
		this.accountManager = accountManager;
	}
	@Override
	public void initialize() {

		// Skip creation if database was already populated
		if (userAccountManager.findByUsername("MANAGER").isPresent()) {
			return;
		}

		LOG.info("Creating default users and customers.");

		userAccountManager.create("MANAGER", Password.UnencryptedPassword.of("123"), Role.of("MANAGER"));

		var password = "123";

		accountManager.createAccount(new CreationForm("CATERING", "123", "CATERING", "CATERING",
				true, false, false, false), null);
		accountManager.createAccount(new CreationForm("SECURITY", "123", "SECURITY", "SECURITY",
				false, true, false, false), null);
		accountManager.createAccount(new CreationForm("TICKET_SALESMAN", "123", "TICKET_SALESMAN", "TICKET_SALESMAN",
				false, false, false, true), null);

	}

}
