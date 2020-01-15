package festivalmanager.staff;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;
import org.javamoney.moneta.Money;
import org.salespointframework.useraccount.Password.UnencryptedPassword;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.salespointframework.useraccount.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.salespointframework.core.Currencies.EURO;

@Service
@Transactional
public class AccountManager {

	public static final Role CATERING_ROLE = Role.of("CATERING");
	public static final Role MANAGER_ROLE = Role.of("MANAGER");
	public static final Role SECURITY_ROLE = Role.of("SECURITY");
	public static final Role TICKET_SALESMAN_ROLE = Role.of("TICKET_SALESMAN");
	public static final Role FESTIVAL_MANAGER_ROLE = Role.of("FESTIVAL_MANAGER");


	public final AccountRepository accounts;
	public final UserAccountManager userAccounts;
	private final ApplicationEventPublisher publisher;
	private final FestivalManager festivalManager;



	@Autowired
	private SessionRegistry sessionRegistry;




	private static final Logger LOG = LoggerFactory.getLogger(AccountManager.class);


	AccountManager(AccountRepository accounts, UserAccountManager userAccounts, ApplicationEventPublisher publisher, FestivalManager festivalManager) {

		Assert.notNull(accounts, "kickstart.account.AccountRepository must not be null");
		Assert.notNull(userAccounts, "UserAccountManager must not be null");

		this.accounts = accounts;
		this.userAccounts = userAccounts;
		this.publisher = publisher;
		this.festivalManager = festivalManager;
	}

	public Account createAccount(CreationForm form, Errors result) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = UnencryptedPassword.of(form.getPassword());
		if (userAccounts.findByUsername(form.getUsrName()).isPresent()) {
			System.out.println("username already used");
			result.rejectValue("name", "Duplicate.creationForm.username");
			return null;
		} else {
			var userAccount = userAccounts.create(form.getUsrName(), password);
			if (Boolean.TRUE.equals(form.getCatering())) {
				userAccount.add(CATERING_ROLE);
			}
			if (Boolean.TRUE.equals(form.getSecurity())) {
				userAccount.add(SECURITY_ROLE);
			}
			/*if (Boolean.TRUE.equals(form.getManager())){

				userAccount.add(MANAGER_ROLE);
			}*/
			if (Boolean.TRUE.equals(form.getTicketSalesman())) {
				userAccount.add(TICKET_SALESMAN_ROLE);
			}

			if (Boolean.TRUE.equals(form.getFestivalManager())){
				userAccount.add(FESTIVAL_MANAGER_ROLE);
			}

			//float workedHours = form.getWorkedHours();
			Money hourlyWage = null;
			if(form.getHourlyWage() != null) {
				hourlyWage = Money.of(form.getHourlyWage(), EURO);
			}

			Festival festival = null;
			if(form.getFestival() != null){
				festival = festivalManager.findByName(form.getFestival());
			}
			return accounts.save(new Account(userAccount, form.getFirstName(), form.getLastName(), form.getWorkedHours(), hourlyWage, festival));
		}
	}

	public void changePassword(UserAccount account, changePasswordForm form) {

		var password = UnencryptedPassword.of(form.getNewPassword());
		userAccounts.changePassword(account, UnencryptedPassword.of(form.getNewPassword()));
	}

	public Streamable<Account> findAll() {
		return accounts.findAll();
	}

	public void sendMessage(MessageForm form){

		LOG.info(form.getRole());

		UserAccount sender = userAccounts.findByUsername(form.getSender()).get();
		if(!(form.getReceiver() == null)) {
			UserAccount receiver = userAccounts.findByUsername(form.getReceiver()).get();;
			MessageEvent messageEvent = new MessageEvent(this, accounts.findByUserAccount(sender).get(),
					accounts.findByUserAccount(receiver).get(), form.getMessage());
			publisher.publishEvent(messageEvent);

		} else {
			Role role = Role.of(form.getRole());


			findByRole(role).forEach(acc -> publisher.publishEvent(new MessageEvent(this, accounts.findByUserAccount(sender).get(),acc
					, form.getMessage())));

		}
	}


	public void deleteAccount(Account account){
		userAccounts.delete(account.getUserAccount());
		accounts.delete(account);
		LOG.info("deleting " + account.getUserAccount().getUsername());
	}
	
	public Optional<Account> findByUserAccount(UserAccount userAccount) {
		return accounts.findByUserAccount(userAccount);
	}

	public Stream<Account> findByRole(Role role){
		return findAll().stream().filter(acc -> acc.getUserAccount().getRoles().toSet().contains(role));
	}


	public List<String> getUsersFromSessionRegistry() {
		List<String> newReeeturn = sessionRegistry.getAllPrincipals().stream()
				.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
				.map(u -> ((UserDetails) u).getUsername())
				.collect(Collectors.toList());

		return newReeeturn;
	}
}

