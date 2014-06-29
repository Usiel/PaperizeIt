package notifiers;
 
import play.*;
import play.mvc.*;

import java.util.*;

import models.User;
 
public class Mails extends Mailer {
 
   public static void verifyEmail(User user) {
      setSubject("Verify your paperize Account");
      addRecipient(user.email);
      //setFrom("Paperize <noreply@paperize.it>");
      setFrom("paperizeit@gmail.com");
      send(user);
   }
 
   public static void lostPassword(User user) {   
      //setFrom("Paperize <noreply@paperize.it>");
	  setFrom("paperizeit@gmail.com");
      setSubject("Reset your password for paperize");
      addRecipient(user.email);
      send(user);
   }
}