package models;
import models.Source;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class SourceBias extends Model {

	@ManyToOne
	public Subscription subscription;
	
	@ManyToOne
	public Source source;
	
	public int bias;
	
	public SourceBias(Subscription subscription, Source source, int bias) {
		this.subscription = subscription;
		this.source = source;
		this.bias = bias;
	}
}
