package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Subscription extends Model {
	
	@ManyToOne
	public User user;
	
	@ManyToOne
	public SubscriptionModel subscriptionModel;
	
	public String anonymousUser;
	
	public Date dateStarted;
	
	public Subscription(User user, SubscriptionModel subscriptionModel, Date dateStarted) {
		this.user = user;
		this.subscriptionModel = subscriptionModel;
		this.dateStarted = dateStarted;
	}
	
	public Subscription(String anonymousUser, SubscriptionModel subscriptionModel, Date dateStarted) {
		this.anonymousUser = anonymousUser;
		this.subscriptionModel = subscriptionModel;
		this.dateStarted = dateStarted;
	}
}
