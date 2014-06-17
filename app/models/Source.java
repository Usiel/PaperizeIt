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
		
		Source spiegel = new Source("spiegel.de", "...");
		Source economist = new Source("economist.de", "...");
		Source welt = new Source("welt.de", "...");
		Source bild = new Source("bild.de", "...");
		Source titanic = new Source("titanic-magazin.de", "...");
		Source focus = new Source("focus.de", "...");
		Source faz = new Source("faz.de", "...");
		
		spiegel.save();
		economist.save();
		welt.save();
		bild.save();
		titanic.save();
		focus.save();
		faz.save();
	}
}
