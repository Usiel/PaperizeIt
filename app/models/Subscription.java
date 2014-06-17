package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Subscription extends Model {
	
	@ManyToOne
	public User user;
	
	public Date dateStarted;
	public Integer deliveries;
	
	public Subscription(User user, Date dateStarted, Integer deliveries) {
		this.user = user;
		this.dateStarted = dateStarted;
		this.deliveries = deliveries;
	}
}
