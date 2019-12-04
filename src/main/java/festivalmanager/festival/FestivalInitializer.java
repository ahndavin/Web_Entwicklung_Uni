

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

		Festival f = new Festival("Beispiel Festival 2019", "Dresden", "19.12.2019","20.12.2019", 100, 50, 20, 40, 2, true);

		f.editPlan().add("blabla");
		f.editPlan().add("lalala");

		festivals.save(f);

		festivals.save(new Festival("Test Festival 2019", "Berlin", "18.12.2019", null, 1000, 400, 50, 100, 2, true));
	}
}