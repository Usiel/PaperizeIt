package models;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class SubscriptionModel extends Model {
	public String name;
	public BigDecimal feePerDelivery;
	public Integer deliveries;
	
	public SubscriptionModel(String name, BigDecimal feePerDelivery, Integer deliveries) {
		this.name = name;
		this.feePerDelivery = feePerDelivery;
		this.deliveries = deliveries;
	}
	
	public static void GenerateSubscriptionModels() {
		if (!SubscriptionModel.findAll().isEmpty()) {
			return;
		}
		
		SubscriptionModel modelPreview = new SubscriptionModel("preview", BigDecimal.ZERO, 0);
		SubscriptionModel modelTrial = new SubscriptionModel("trial", new BigDecimal(3), 12);
		SubscriptionModel modelFull = new SubscriptionModel("full", new BigDecimal(2.50), 52);
		
		modelFull.save();
		modelTrial.save();
		modelPreview.save();
	}
	
}
