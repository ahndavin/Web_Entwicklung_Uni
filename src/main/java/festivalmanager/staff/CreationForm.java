package festivalmanager.staff;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManager;
import org.h2.api.UserToRolesMapper;
import org.javamoney.moneta.Money;

import javax.validation.constraints.NotEmpty;

public class CreationForm {

		@NotEmpty(message = "{CreationForm.name.NotEmpty}")
		private final String usrName;

		@NotEmpty(message = "{CreationForm.password.NotEmpty}")
		private final String password;

		@NotEmpty(message = "{CreationForm.firstName.NotEmpty}")
		private final String firstName;

		@NotEmpty(message = "{CreationForm.lastName.NotEmpty")
		private final String lastName;

		private Boolean catering;

		private Boolean security;

		private Boolean festivalManager;

		private Boolean ticketSalesman;

		private Float workedHours;

		private Money hourlyWage;

		private Festival festival;

		public CreationForm(String usrName, String password, String firstName, String lastName,
							Boolean catering, Boolean security, Boolean festivalManager, Boolean ticketSalesman, Float workedHours, Money hourlyWage, Festival festival) {
			this.usrName = usrName;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.catering = catering;
			this.security = security;
			this.festivalManager = festivalManager;
			this.ticketSalesman = ticketSalesman;
			this.workedHours = workedHours;
			this.hourlyWage = hourlyWage;
			this.festival = festival;
		}

		public String getUsrName() {
			return usrName;
		}

		public String getPassword() {
			return password;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public Boolean getCatering() {
			return catering;
		}

		public Boolean getSecurity() {
			return security;
		}

		public Boolean getFestivalManager() {
			return festivalManager;
		}

		public Boolean getTicketSalesman() {
			return ticketSalesman;
		}

		public Money getHourlyWage() {return hourlyWage;}

		public Float getWorkedHours() {return workedHours;}

		public Festival getFestival() {return festival;}
}

