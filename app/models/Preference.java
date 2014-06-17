package models;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Entity
public class Preference extends Model {
	
	@ManyToOne
	public Preference parent;
	
	public String name;
	
	public Preference(Preference parent, String name) {
		this.parent = parent;
		this.name = name;
	}
	
	public static void GeneratePreferences() {
		if (!Preference.findAll().isEmpty()) {
			return;
		}
		
		Preference sports = new Preference(null, "Sports");
		Preference basketball = new Preference(sports, "Basketball");
		Preference football = new Preference(sports, "Football");
		Preference diving = new Preference(sports, "Diving");
		
		Preference politics = new Preference(null, "Politics");
		Preference germanPolitics = new Preference(politics, "German Politics");
		Preference internationalPolitics = new Preference(politics, "International");
		Preference law = new Preference(politics, "Law");
		
		Preference science = new Preference(null, "Science");
		Preference biology = new Preference(science, "Biology");
		Preference informatics = new Preference(science, "Informatics");
		Preference physics = new Preference(science, "Physics");
		
		Preference culture = new Preference(null, "Culture");
		Preference opera = new Preference(culture, "Opera");
		Preference cinema = new Preference(culture, "Cinema");
		Preference music = new Preference(culture, "Music");
		
		
		
		sports.save();
		basketball.save();
		football.save();
		diving.save();
		
		politics.save();
		germanPolitics.save();
		internationalPolitics.save();
		law.save();
		
		science.save();
		biology.save();
		informatics.save();
		physics.save();
		
		culture.save();
		opera.save();
		cinema.save();
		music.save();
	}
}
