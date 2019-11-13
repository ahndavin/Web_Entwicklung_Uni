

import org.h2.engine.User;
import org.salespointframework.useraccount.Password.UnencryptedPassword;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;


public class AccountManager {

	public static Role CATERING_ROLE = Role.of("CATERING");
	public static Role MANAGER_ROLE = Role.of("MANAGER");

	public final AccountRepository accounts;
	public final UserAccountManager userAccounts;

	AccountManager(AccountRepository accounts, UserAccountManager userAccounts){

		Assert.notNull(accounts, "AccountRepository must not be null");
		Assert.notNull(userAccounts, "UserAccountManager must not be null");

		this.accounts = accounts;
		this.userAccounts = userAccounts;
	}

	public Account createAccount(CreationForm form, Errors result){

		Assert.notNull(form, "Refistration form must not be null!");


	}
}
