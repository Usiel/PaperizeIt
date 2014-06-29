package controllers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import models.Preference;
import models.PreferenceSubscription;
import models.Source;
import models.SourceBias;
import models.SubscriptionModel;
import models.User;
import controllers.Secure.Security;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Http.Cookie;

public class Subscription extends Controller {
    
    public static void index() {
    	if (!Security.isConnected()) {
    		redirect("/login");
    	}
    	
    	User user = User.find("email", Security.connected()).first();
    	List<models.Subscription> subs = models.Subscription.find("user_id", user.id).fetch();
    	render(subs);
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
	    	 models.Subscription newSubscription = user != null ?
	    			 new models.Subscription(user, anonymousUser, null, new Date())
	    	 		: new models.Subscription(anonymousUser, null, new Date());
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

    		response.setCookie("NewSubscription", anonymousUser);
	    		    	   
	    	if (user != null) {
	    		redirect("Subscription.selectModel", new Object[] { });
	    	} else {	    
	    		redirect("Subscription.register", new Object[] { });
	    	}
    	}
    }
    
    public static void selectModel() {
    	render();
    }
    
    public static void selectModelPost(@Required String model) {
    	if (validation.hasErrors()) {
    		render("Subscription/selectModel.html");
    	}
    	
    	SubscriptionModel subModel;
    	
    	if (model.equals("full")) {
    		subModel = SubscriptionModel.find("name", "full").first();
    	} else if (model.equals("trial")) {
    		subModel = SubscriptionModel.find("name", "trial").first();
    	}
    	else {
    		subModel = SubscriptionModel.find("name", "preview").first();
    	}
    	
    	User user = User.find("email", Secure.Security.connected()).first();
    	
    	Cookie subscriptionCookie = request.cookies.get("NewSubscription");
    	response.removeCookie("NewSubscription");
		models.Subscription sub = models.Subscription.find("anonymousUser", subscriptionCookie.value).first();
		
		//sub.user = user not necessary. If user was registered user is set in selectPreferences(). Otherwise it is set in registerPost()
    	sub.subscriptionModel = subModel;
    	sub.save();
    	
    	render("Subscription/showSubscription.html", sub);
    }
    
    public static void showSubscription(@Required long subscriptionId) throws Throwable {
    	models.Subscription sub = models.Subscription.findById(subscriptionId);
    	if (!sub.user.email.equals(Security.connected())) {
    		render("Secure/login.html");
    	}
    	
    	render("Subscription/showSubscription.html", sub);
    }
    
    public static void cancelSubscription(@Required long subscriptionId) {
    	models.Subscription sub = models.Subscription.findById(subscriptionId);
    	if (!sub.user.email.equals(Security.connected())) {
    		render("Secure/login.html");
    	}
    	
    	sub.dateCanceled = new Date();
    	sub.save();
    	
    	render("Subscription/showSubscription.html", sub);
    }
}
