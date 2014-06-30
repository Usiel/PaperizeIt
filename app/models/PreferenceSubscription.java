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
	
	// For DEMO purposes only
	public static void GeneratePreferenceSubscription() {
		Subscription sub = (Subscription) Subscription.findAll().get(0);
		Preference pref1 = Preference.find("name", "Football").first();
		Preference pref2 = Preference.find("name", "International").first();
		PreferenceSubscription ps1 = new PreferenceSubscription(sub, pref1);
		PreferenceSubscription ps2 = new PreferenceSubscription(sub, pref2);
		
		ps1.save();
		ps2.save();
	}
}
