package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Source extends Model {	
	public String name;
	public String description;
	
	public Source(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public static void GenerateSources() {
		if (!Source.findAll().isEmpty()) {
			return;
		}
		
		Source spiegel = new Source("DER SPIEGEL", "...");
		Source economist = new Source("The Economist", "...");
		Source oglobo = new Source("O Globo", "...");
		
		spiegel.save();
		economist.save();
		oglobo.save();
	}
}
