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
		
		Source src1 = Source.find("name", "spiegel.de").first();
		Source src2 = Source.find("name", "welt.de").first();
		Source src3 = Source.find("name", "bild.de").first();
		
		SourceBias bias1 = new SourceBias(sub, src1, 2);
		SourceBias bias2 = new SourceBias(sub, src2, 1);
		SourceBias bias3 = new SourceBias(sub, src3, -1);
		
		bias1.save();
		bias2.save();
		bias3.save();
	}
}
