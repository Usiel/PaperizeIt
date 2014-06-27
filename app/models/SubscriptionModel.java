package models;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class SubscriptionModel extends Model {
	public BigDecimal feePerDelivery;
	public Integer deliveries;
	
	public SubscriptionModel(BigDecimal feePerDelivery, Integer deliveries) {
		this.feePerDelivery = feePerDelivery;
		this.deliveries = deliveries;
	}
}
