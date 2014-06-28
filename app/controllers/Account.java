package controllers;

import models.User;
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
}