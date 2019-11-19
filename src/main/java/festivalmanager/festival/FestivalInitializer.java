package festivalmanager.festival;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class FestivalInitializer implements DataInitializer {
	private FestivalRepository festivals;

	public FestivalInitializer(FestivalRepository festivals) {
		this.festivals = festivals;
	}

	@Override
	public void initialize() {
		if(festivals.findAll().iterator().hasNext()) {
			return;
		}

		festivals.save(new Festival("Test1", "heute", 2, true));
		festivals.save(new Festival("Test2", "morgen", 2, true));
	}
}
