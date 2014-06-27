package models;

import javax.persistence.*;

import play.db.jpa.Model;
import models.Preference;

@Entity
public class PreferenceSubscription extends Model {
	
	@ManyToOne
	public Subscription subscription;
	
	@ManyToOne
	public Preference preference;
	
	public PreferenceSubscription(Subscription subscription, Preference preference) {
		this.subscription = subscription;
		this.preference = preference;
	}
}
