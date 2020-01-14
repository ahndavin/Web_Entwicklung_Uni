package festivalmanager.location;

public class StageForm extends Stage {
	public StageForm(String name, String poster) {
		super(name, poster);
	}

	public Stage toStage() {
		return new Stage(getName(), getPoster());
	}
}
