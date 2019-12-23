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
	public final AccountRepository accounts;
	public final MessageManagement messageManagement;
	public final MessageRepository messageRepository;
	public static final Role MANAGER_ROLE = Role.of("MANAGER");

	public StaffDataInitializer(UserAccountManager userAccountManager, AccountManager accountManager, AccountRepository accounts, MessageManagement messageManagement, MessageRepository messageRepository) {

		this.accounts = accounts;
		this.userAccountManager = userAccountManager;
		this.accountManager = accountManager;
		this.messageManagement = messageManagement;
		this.messageRepository = messageRepository;
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


		Account MANAGER = accounts.save(new Account(userAccountManager.findByUsername("MANAGER").get(), "MANAGER", "MANAGER"));

		var password = "123";

		Account CATERING = accountManager.createAccount(new CreationForm("CATERING", "123", "CATERING", "CATERING",
				true, false, false), null);
		Account SECURITY = accountManager.createAccount(new CreationForm("SECURITY", "123", "SECURITY", "SECURITY",
				false, true, false), null);
		Account TICKET_SALESMAN =  accountManager.createAccount(new CreationForm("TICKET_SALESMAN", "123", "TICKET_SALESMAN", "TICKET_SALESMAN",
				false, false, true), null);


		messageManagement.createNewMessage(new MessageEvent(MANAGER, MANAGER, SECURITY, "Hallo"));
		messageManagement.findAll();
		LOG.info("Creating default messages");


	}

}
