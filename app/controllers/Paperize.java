package controllers;

import play.db.jpa.GenericModel;
import play.mvc.*;

import java.util.*;

import models.*;

public class Paperize extends Controller {

    public static void index() {
        render();
    }
    
    public static void subscribe(Boolean error) {
    	if (error != null && error)
    		response.status = 400;
    	
    	Preference.GeneratePreferences();
    	List<Preference> preferences = Preference.findAll();
    	
    	Source.GenerateSources();
    	List<Source> sources = Source.findAll();
    	
    	render(preferences, sources);
    }
    
    public static void selectPreferences() {
    	String[] preferences = params.getAll("preference");
    	
    	List<Integer> preferenceIds = new ArrayList<Integer>();
    	
    	if (preferences == null) {
    		response.status = 400;
    		subscribe(true);
    	} else { 
	    	for (String i : preferences) {
				preferenceIds.add(Integer.parseInt(i));
			}
	    	
	    	SortedMap<Integer, Integer> sourceBias = new TreeMap<Integer, Integer>();
	    	String[] sortedSourceIds = params.getAll("sorted");
	    	
	    	// Put in sorted source bias on a range of sortedIds.length
	    	for (int i = 0; i < sortedSourceIds.length; i++) {
	    		sourceBias.put(Integer.parseInt(sortedSourceIds[i].substring(7)), sortedSourceIds.length - i);
	    	}
	    	
	    	String[] ignoreSourceIds = params.getAll("ignore");
	    	
	    	for (int i = 0; i < ignoreSourceIds.length; i++) {
	    		sourceBias.put(Integer.parseInt(ignoreSourceIds[i].substring(7)), -1);
	    	}
	
	    	Subscription newSubscription = new Subscription(null, new Date(), null);
	    	newSubscription.save();
	    	
	    	for (int prefId : preferenceIds) {
	    		Preference preference = Preference.findById((long)prefId);
	    		PreferenceSubscription prefSub = new PreferenceSubscription(newSubscription, preference);
	    		prefSub.save();
	    	}
	    	
	    	for (Map.Entry<Integer, Integer> entry : sourceBias.entrySet()) {
	    		Source src = Source.findById((long)entry.getKey());
	    		if (src != null) {
		    		SourceBias bias = new SourceBias(newSubscription, src, entry.getValue());
		    		bias.save();
	    		}
	    	}
	    	
	    	redirect("/");
    	}
    }
}