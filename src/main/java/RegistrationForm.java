import javax.validation.constraints.NotEmpty;

public class CreationForm {

	@NotEmpty(message = "Name darf nicht leer sein")
	private final String name;

	@NotEmpty(message = "Password darf nicht leer sein")
	private final String password;

	@notEmpty(message = "Rollen dürfen nicht leer sein")
	private final
}
