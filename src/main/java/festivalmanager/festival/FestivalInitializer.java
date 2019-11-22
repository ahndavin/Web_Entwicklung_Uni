package festivalmanager.festival;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Order(10)
public class FestivalInitializer implements DataInitializer {
	private FestivalManager festivals;

	public FestivalInitializer(FestivalManager festivals) {
		this.festivals = festivals;
	}

	@Override
	public void initialize() {
		if(festivals.findAll().iterator().hasNext()) {
			return;
		}

		Festival f = new Festival("Test1", "19.12.2019","20.12.2019", 2, true);

		f.editPlan(-1, "blabla");
		f.editPlan(-1, "lalala");

		System.out.println(festivals.save(f));

		System.out.println(festivals.save(new Festival("Test1", "18.12.2019", 2, true)));
	}
}
