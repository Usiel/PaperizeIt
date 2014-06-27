package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model {
	
	public String email;
	public String password;
	public String firstName;
	public String lastName;
	public Date dateOfBirth;
	
	public String street;
	public String postalCode;
	public String town;
	public String salutation;
	
	
	public User(String email, String password, String firstName, String lastName, Date dateOfBirth, String street, String postalCode, String town, String salutation) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.street = street;
		this.postalCode = postalCode;
		this.town = town;
		this.salutation = salutation;
	}
}
