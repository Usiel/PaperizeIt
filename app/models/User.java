package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class User extends Model {
	
	public String email;
	
	public String password;
	public String salt;
	public String verifyEmailToken;
	public String resetPasswordToken;
	public Date passwordLost;
	public Date dateEmailVerified;
	
	public String firstName;
	public String lastName;
	public Date dateOfBirth;
	
	public String street;
	public String postalCode;
	public String town;
	public String salutation;
	
	
	
	public User(String email, String password, String firstName, String lastName, Date dateOfBirth, String street, String postalCode, String town, String salutation) {
		this.email = email;
		this.salt = GetSalt();
		this.password = HashPassword(password, this.salt);
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.street = street;
		this.postalCode = postalCode;
		this.town = town;
		this.salutation = salutation;
		this.verifyEmailToken = GetSalt();
	}
	
	public static String GetSalt() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	public static String HashPassword(String password, String salt) {
        String generatedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        return generatedPassword;		
	}
	
	// For DEMO purposes only
	public static void GenerateUsers() {
		User testAccount = new User("test@paperize.it", "test", "John", "Doe", new Date(1980, 10, 12), "Mainstreet 2", "5893", "Randomtown", "Mr.");
		testAccount.dateEmailVerified = new Date();
		testAccount.save();
	}
}
