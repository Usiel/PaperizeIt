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
	
	
	public User(String email, String password, String firstName, String lastName, Date dateOfBirth) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
}
