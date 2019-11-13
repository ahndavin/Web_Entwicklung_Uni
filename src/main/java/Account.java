
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.h2.engine.User;
import org.salespointframework.useraccount.UserAccount;


public class Account {

	private @Id @GeneratedValue long id;

	private String fullName;

	@OneToOne
	private UserAccount userAccount;

	private Account() {}

	public Account(UserAccount userAccount, String fullName){
		this.userAccount = userAccount;
		this.fullName = fullName;
	}

	public long getId() {return id;}

	public String getFullName() {return fullName;}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}
}
