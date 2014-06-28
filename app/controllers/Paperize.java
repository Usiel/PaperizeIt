package controllers;

import play.mvc.*;
import play.mvc.Http.Cookie;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;

import com.ning.http.client.Response;

import models.*;

public class Paperize extends Controller {

    public static void index() {
        render();
    }
    
    public static void subscribe(Boolean error) {
    	if (error != null && error)
    		response.status = 400;

    	List<Preference> preferences = Preference.findAll();
    	
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
	    	if (sortedSourceIds != null) {
		    	for (int i = 0; i < sortedSourceIds.length; i++) {
		    		sourceBias.put(Integer.parseInt(sortedSourceIds[i].substring(7)), sortedSourceIds.length - i);
		    	}
	    	}
	    	
	    	String[] ignoreSourceIds = params.getAll("ignore");
	    	
	    	if (ignoreSourceIds != null) {
		    	for (int i = 0; i < ignoreSourceIds.length; i++) {
		    		sourceBias.put(Integer.parseInt(ignoreSourceIds[i].substring(7)), -1);
		    	}
	    	}

	    	//Get user if connected
	    	User user = User.find("email", Secure.Security.connected()).first();
	    	//Create random string
			SecureRandom random = new SecureRandom();
	    	String anonymousUser = new BigInteger(130, random).toString(32);
	    	
	    	//Either create subscription with user or just an identifier to remember the selection for later (identified by cookie with random string)
	    	 Subscription newSubscription = user != null ?
	    			 new Subscription(user, anonymousUser, null, new Date())
	    	 		: new Subscription(anonymousUser, null, new Date());
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
	    		    	   
	    	if (user != null) {
	    		redirect("Account.selectModel()", new Object[] { });
	    	} else {	    
	    		Cookie cookie = new Cookie();
	    		cookie.name = "NewSubscription";
	    		cookie.value = anonymousUser;
	    		response.cookies.put("NewSubscription", cookie);
	    		Account.registerAndSubscribe();
	    	}
    	}
    }
}