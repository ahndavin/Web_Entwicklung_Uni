package festivalmanager.ticket;

import org.javamoney.moneta.Money;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;

import javax.persistence.*;

@Embeddable
public class TicketBuilder {
	@Transient
	private long id;

	@AttributeOverrides({
			@AttributeOverride(name="amount", column= @Column(name="day_quantity_amount")),
			@AttributeOverride(name="metric", column= @Column(name="day_quantity_metric"))
	})
	private Quantity amountDaytickets;

	@AttributeOverrides({
			@AttributeOverride(name="amount", column= @Column(name="camping_quantity_amount")),
			@AttributeOverride(name="metric", column= @Column(name="camping_quantity_metric"))
	})
	private Quantity amountCampingticket;

	private Money priceDayticket;
	private Money priceCampingticket;

	public TicketBuilder(long id, int amountDaytickets , int amountCampingticket, double priceDayticket, double priceCampingticket) {
		this.id = id;
		this.amountDaytickets = Quantity.of(amountDaytickets, Metric.UNIT);
		this.amountCampingticket = Quantity.of(amountCampingticket, Metric.UNIT);
		this.priceDayticket = Money.of(priceDayticket, "EUR");
		this.priceCampingticket = Money.of(priceCampingticket, "EUR");
	}

	public Quantity getAmountDaytickets() {
		return amountDaytickets;
	}

	public void setAmountDaytickets(Quantity dayticketAmount) {
		this.amountDaytickets = dayticketAmount;
	}

	public Money getPriceDayticket() {
		return priceDayticket;
	}

	public Number getFormattedPriceDayticket() {
		return priceDayticket.getNumber();
	}

	public void setPriceDayticket(Money dayticketPrice) {
		this.priceDayticket = dayticketPrice;
	}

	public Quantity getAmountCampingtickets() {
		return amountCampingticket;
	}

	public void setAmountCampingtickets(Quantity campingticketAmount) {
		this.amountCampingticket = campingticketAmount;
	}

	public Money getPriceCampingticket() {
		return priceCampingticket;
	}

	public Number getFormattedPriceCampingticket() {
		return priceCampingticket.getNumber();
	}

	public void setPriceCampingticket(Money campingticketPrice) {
		this.priceCampingticket = campingticketPrice;
	}

	public Campingticket createCampingticket() {
		return new Campingticket(String.valueOf(id), priceCampingticket);
	}

	public Dayticket createDayticket() {
		return new Dayticket(String.valueOf(id), priceDayticket);
	}
}
