package festivalmanager.staff;

import festivalmanager.festival.FestivalManager;
import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static org.salespointframework.core.Currencies.EURO;

@Component
@Order(10)
public class StaffDataInitializer implements DataInitializer{

	private static final Logger LOG = LoggerFactory.getLogger(StaffDataInitializer.class);

	private final UserAccountManager userAccountManager;
	private final AccountManager accountManager;
	public final AccountRepository accounts;
	public final MessageManager messageManagement;
	public final MessageRepository messageRepository;
	private final FestivalManager festivalManager;
	public static final Role MANAGER_ROLE = Role.of("MANAGER");

	public StaffDataInitializer(UserAccountManager userAccountManager, AccountManager accountManager, AccountRepository accounts,
								MessageManager messageManagement, MessageRepository messageRepository, FestivalManager festivalManager) {

		this.accounts = accounts;
		this.userAccountManager = userAccountManager;
		this.accountManager = accountManager;
		this.messageManagement = messageManagement;
		this.messageRepository = messageRepository;
		this.festivalManager = festivalManager;
	}
	@Override
	public void initialize() {

		// Skip creation if database was already populated
		if (userAccountManager.findByUsername("MANAGER").isPresent()) {
			return;
		}

		LOG.info("Creating default users and customers.");

		userAccountManager.create("MANAGER", Password.UnencryptedPassword.of("123"));
		userAccountManager.findByUsername("MANAGER").get().add(MANAGER_ROLE);


		Account MANAGER = accounts.save(new Account(userAccountManager.findByUsername("MANAGER").get(), "MANAGER", "MANAGER", null, null, null ));

		var password = "123";

		Account CATERING = accountManager.createAccount(new CreationForm("CATERING", "123", "CATERING", "CATERING",
				true, false, false, false, (float) 20.0 , (float) 12.0, "Abriss"), null);
		Account SECURITY2 = accountManager.createAccount(new CreationForm("SECURITY2", "123", "SECURITY2", "SECURITY2",
				false, true, false, false, (float) 20.0, (float)12.0, "Abriss"), null);
		Account TICKET_SALESMAN =  accountManager.createAccount(new CreationForm("TICKET_SALESMAN", "123", "TICKET_SALESMAN", "TICKET_SALESMAN",
				false, false, false, true, null, null, null ), null);
		Account FESTIVAL_MANAGER =  accountManager.createAccount(new CreationForm("FESTIVAL_MANAGER", "123", "FESTIVAL_MANAGER", "FESTIVAL_MANAGER",
			false, false, true, false, null, null, null ), null);

	}

}
