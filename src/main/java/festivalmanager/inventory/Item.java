package festivalmanager.inventory;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;

import javax.persistence.Entity;

@Entity
public class Item extends Product {
	private Money cost;
	private Quantity minimalQuantity;

	public Item(String name, Money price, Money cost, Quantity minimalQuantity, String[] categories) {
		super(name, price, Metric.UNIT);

		this.cost = cost;
		this.minimalQuantity = minimalQuantity;

		for(String category : categories) {
			addCategory(category);
		}
	}

	public Money getCost() {
		return cost;
	}

	public void setCost(Money cost) {
		this.cost = cost;
	}

	public Quantity getMinimalQuantity() {
		return minimalQuantity;
	}

	public void setMinimalQuantity(Quantity minimalQuantity) {
		this.minimalQuantity = minimalQuantity;
	}
}
