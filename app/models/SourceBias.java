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
	
	// For DEMO purposes only
	public static void GenerateSourceBias() {
		Subscription sub = (Subscription) Subscription.findAll().get(0);
		
		Source src1 = (Source) Source.findAll().get(0);
		Source src2 = (Source) Source.findAll().get(1);
		Source src3 = (Source) Source.findAll().get(2);
		
		SourceBias bias1 = new SourceBias(sub, src1, 2);
		SourceBias bias2 = new SourceBias(sub, src2, 1);
		SourceBias bias3 = new SourceBias(sub, src3, -1);
		
		bias1.save();
		bias2.save();
		bias3.save();
	}
}
