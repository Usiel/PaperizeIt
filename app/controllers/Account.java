package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Subscription;
import models.SubscriptionModel;
import models.User;
import play.data.validation.Required;
import play.mvc.*;
import play.mvc.Http.Cookie;


public class Account extends Secure.Security {

    static boolean authenticate(String email, String password) {
        User user = User.find("email", email).first();
        
        return user != null && 
        		user.password.equals(User.HashPassword(password, user.salt));
    }
    
    public static void register() {
    	render();
    }
    
    public static void registerAndSubscribe() {
    	register();
    }
    
    public static void registerPost(@Required String firstname, @Required String lastname, @Required String salutation, 
    		@Required String dateOfBirth, @Required String street, @Required String postalCode, @Required String town, 
    		@Required String email, @Required String password, @Required String passwordRepeat) {
    	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    	Date birthday = new Date();
		try {
			birthday = df.parse(dateOfBirth);
		} catch (ParseException e) {
			validation.addError("dateOfBirth", "Date is not in a valid format");
		}
		
		if (User.find("email", email).first() != null) {
			validation.addError("EmailInUse", "Email is already in use. Please use a different one or reactivate your account");
		}
		
    	if (validation.hasErrors() || !password.equals(passwordRepeat)) {
    		render("Account/register.html");
    	}
    	    	
    	User newUser = new User(email, password, firstname, lastname, birthday, street, postalCode, town, salutation);
    	newUser.save();
    	
    	Cookie subscriptionCookie = request.cookies.get("NewSubscription");
    	if (subscriptionCookie != null) {
    		//Attach subscription to user
    		Subscription sub = Subscription.find("anonymousUser", subscriptionCookie.value).first();
    		sub.user = newUser;
    		sub.save();
    		
    		render("Account/selectModel.html");
    	} else {
    		redirect("Account.index()", new Object[] { });
    	}
    }
    
    public static void index() {
    	render();
    }
    
    public static void selectModel() {
    	render();
    }
    
    public static void selectModelPost(@Required String model) {
    	if (validation.hasErrors()) {
    		render("Account/selectModel.html");
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
    	
    	Subscription sub = Subscription.find("user_id", user.id).first();
    	
    	sub.subscriptionModel = subModel;
    	sub.save();
    	
    	
    	
    	render("selectModelSuccess", sub);
    }
}