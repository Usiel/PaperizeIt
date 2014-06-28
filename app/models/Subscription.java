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
	
	public Integer deliveredIssues;
	
	@OneToMany(mappedBy="subscription")
    public List<SourceBias> sourceBias;
	
	@OneToMany(mappedBy="subscription")
	public List<PreferenceSubscription> preferences;
	
	public Subscription(User user, String anonymousUser, SubscriptionModel subscriptionModel, Date dateStarted) {
		this.user = user;
		this.anonymousUser = anonymousUser;
		this.subscriptionModel = subscriptionModel;
		this.dateStarted = dateStarted;
		this.deliveredIssues = 0;
	}
	
	public Subscription(String anonymousUser, SubscriptionModel subscriptionModel, Date dateStarted) {
		this.anonymousUser = anonymousUser;
		this.subscriptionModel = subscriptionModel;
		this.dateStarted = dateStarted;
		this.deliveredIssues = 0;
	}
}
