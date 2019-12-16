package festivalmanager.staff;

import org.salespointframework.useraccount.Password.UnencryptedPassword;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@Transactional
public class AccountManager {

	public static final Role CATERING_ROLE = Role.of("CATERING");
	public static final Role MANAGER_ROLE = Role.of("MANAGER");
	public static final Role SECURITY_ROLE = Role.of("SECURITY");
	public static final Role TICKET_SALESMAN_ROLE = Role.of("TICKET_SALESMAN");

	public final AccountRepository accounts;
	public final UserAccountManager userAccounts;

	AccountManager(AccountRepository accounts, UserAccountManager userAccounts){

		Assert.notNull(accounts, 	"kickstart.account.AccountRepository must not be null");
		Assert.notNull(userAccounts, "UserAccountManager must not be null");

		this.accounts = accounts;
		this.userAccounts = userAccounts;
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
			if (Boolean.TRUE.equals(form.getSecurity())){
				userAccount.add(SECURITY_ROLE);
			}
			/*if (Boolean.TRUE.equals(form.getManager())){

				userAccount.add(MANAGER_ROLE);
			}*/
			if (Boolean.TRUE.equals(form.getTicketSalesman())){
				userAccount.add(TICKET_SALESMAN_ROLE);
			}

			return accounts.save(new Account(userAccount, form.getFirstName(), form.getLastName()));
		}
	}

	public void changePassword(UserAccount account, changePasswordForm form){

		var password = UnencryptedPassword.of(form.getNewPassword());
		userAccounts.changePassword(account, UnencryptedPassword.of(form.getNewPassword()));
	}

	public Streamable<Account> findAll(){
		return accounts.findAll();
	}

	public Account findByUserAccount(UserAccount userAccount){
		Streamable<Account> allAccounts = this.findAll();
		for(Account account : allAccounts){
			if(account.getUserAccount() == userAccount){
				return account;
			}
		}
		return null;
	}

}

