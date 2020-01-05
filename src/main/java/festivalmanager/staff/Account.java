package festivalmanager.staff;

import javax.persistence.*;

import festivalmanager.festival.Festival;
import org.h2.engine.User;
import org.javamoney.moneta.Money;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;

@Entity
public class Account {

	private @Id @GeneratedValue long id;

	private String firstName;
	private String lastName;
	private Float workedHours;
	private Money hourlyWage;

	@ManyToOne
	private Festival festival;

	@OneToOne
	private UserAccount userAccount;

	private Account() {}

	public Account(UserAccount userAccount, String firstName, String lastName, Float workedHours, Money hourlyWage, Festival festival){

		this.userAccount = userAccount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.workedHours = workedHours;
		this.hourlyWage = hourlyWage;
		this.festival = festival;
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

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public Money getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(Money hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public float getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(float workedHours) {
		this.workedHours = workedHours;
	}
}