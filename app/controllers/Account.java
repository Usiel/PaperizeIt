package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import notifiers.Mails;
import controllers.Secure.Security;
import models.Subscription;
import models.SubscriptionModel;
import models.User;
import play.data.validation.Required;
import play.mvc.*;
import play.mvc.Http.Cookie;


public class Account extends Secure.Security {

    static boolean authenticate(String email, String password) {
        User user = User.find("email", email).first();
        
        if (user.dateEmailVerified == null) {
        	validation.addError("dateEmailVerified", "E-Mail not verified. Please check your inbox or spam folder!");
        }
        
        return user != null && 
        		user.password.equals(User.HashPassword(password, user.salt)) && user.dateEmailVerified != null;
    }

    
    public static void register() {
    	render();
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
    	
    	Mails.verifyEmail(newUser);
    	
    	Cookie subscriptionCookie = request.cookies.get("NewSubscription");
    	Subscription sub = null;
    	if (subscriptionCookie != null) {
    		//Attach subscription to user
    		sub = Subscription.find("anonymousUser", subscriptionCookie.value).first();
    	}
    	if (sub != null) {
    		sub.user = newUser;
    		sub.save();
    		
    		redirect("Subscription.selectModel", new Object[] { });
    	} else {
    		redirect("Subscription.index", new Object[] { });
    	}
    }

    
    public static void lostPassword(@Required String email) {
    	if (validation.hasErrors()) {
    		forbidden();
    	}
    	
    	User user = User.find("email", email).first();
    	
    	if (user == null) {
    		forbidden();
    	}
    	
    	user.resetPasswordToken = User.GetSalt();
    	user.save();
    	
    	Mails.lostPassword(user);
    	
    	ok();
    }
    
    public static void resetPassword(@Required String resetPasswordToken) {
    	User user = User.find("resetPasswordToken", resetPasswordToken).first();

    	if (user == null || Minutes.minutesBetween(new DateTime(), new DateTime(user.passwordLost)).isGreaterThan(Minutes.minutes(60))) {
			forbidden();
    	}
    	
    	render(resetPasswordToken);
    }
    
    public static void resetPasswordPost(@Required String resetPasswordToken, @Required String newPassword, @Required String newPasswordRepeat) {
    	if (!newPassword.equals(newPasswordRepeat)) {
    		validation.addError("newPassword", "Passwords must match.");
    	}
    	
    	if (validation.hasErrors()) {
    		render("Account/resetPassword.html", resetPasswordToken);
    	}
    	
    	User user = User.find("resetPasswordToken", resetPasswordToken).first();

    	if (user == null || Minutes.minutesBetween(new DateTime(), new DateTime(user.passwordLost)).isGreaterThan(Minutes.minutes(60))) {
			forbidden();
    	}
    	
    	user.passwordLost = null;
    	user.resetPasswordToken = null;
    	
    	user.salt = User.GetSalt();
    	user.password = User.HashPassword(newPassword, user.salt);
    	
    	user.save();
    	
    	redirect("Subscription.index", new Object[] { });
    }
    
    public static void verifyEmail(@Required String verifyEmailToken) {
    	User user = User.find("verifyEmailToken", verifyEmailToken).first();

    	if (user == null) {
			forbidden();
    	}
    	
    	user.verifyEmailToken = null;
    	user.dateEmailVerified = new Date();
    	user.save();
    	
    	redirect("Subscription.index", new Object[] { });
    }
}