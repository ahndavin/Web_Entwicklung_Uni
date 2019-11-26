package festivalmanager.staff;

import org.h2.api.UserToRolesMapper;

import javax.validation.constraints.NotEmpty;

class CreationForm {

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

		private Boolean manager;

		private Boolean ticketSalesman;


		public CreationForm(String usrName, String password, String firstName, String lastName, Boolean catering, Boolean security, Boolean manager, Boolean ticketSalesman) {
			this.usrName = usrName;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.catering = catering;
			this.security = security;
			this.manager = manager;
			this.ticketSalesman = ticketSalesman;
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

		public Boolean getManager() {
			return manager;
		}

		public Boolean getTicketSalesman() {
			return ticketSalesman;
		}
}

