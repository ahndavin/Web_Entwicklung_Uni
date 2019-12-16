package festivalmanager.staff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.h2.engine.User;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;

@Entity
public class Account {

	private @Id @GeneratedValue long id;

	private String firstName;
	private String lastName;
	private Boolean catering;

	@OneToOne
	private UserAccount userAccount;

	private Account() {}

	public Account(UserAccount userAccount, String firstName, String lastName){
		this.userAccount = userAccount;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public long getId() {return id;}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getCatering(){
		return this.catering;
	}

	public void setCatering(Boolean catering){
		this.catering = catering;
	}


	public UserAccount getUserAccount() {
		return userAccount;
	}



}