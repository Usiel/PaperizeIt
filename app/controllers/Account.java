package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.User;
import play.data.validation.Required;
import play.mvc.*;


public class Account extends Secure.Security {

    static boolean authenticate(String email, String password) {
        User user = User.find("byEmail", email).first();
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
    	Date birthday;
		try {
			birthday = df.parse(dateOfBirth);
		} catch (ParseException e) {
			validation.addError("dateOfBirth", "Date is not in a valid format");
		}
    	if (validation.hasErrors() || !password.equals(passwordRepeat)) {
    		render("Account/register.html");
    	}
    	return;
    }
}