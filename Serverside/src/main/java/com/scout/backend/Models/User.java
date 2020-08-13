package com.scout.backend.Models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import com.scout.backend.Models.Postings;




@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Users")
public class User {
 
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private Integer id;
	
	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lastname;

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phonenumber;
	
	@NotEmpty
	private String password;
	
//	public enum loggedIn{
//		TRUE,
//		FALSE
//	}
//	
//	@NotEmpty
//	private loggedIn LI;
//	
	@OneToMany
	private List<Postings> posting;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstname;
	}
	
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phonenumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
//	public loggedIn getLoggedValue() {
//		return LI;
//	}
//	
//	public void setLoggedIn(loggedIn LI) {
//		if(LI == loggedIn.FALSE || LI == null) {
//			LI = LI.TRUE;
//		}
//		else {
//			LI = LI.FALSE;
//		}
//	}
//	
	public boolean isNew() {
        return this.id == null;
	}

	@Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("telephone", this.phonenumber).toString();
	}

	public List<Postings> getPosting() {
		return posting;
	}

	public void setPosting(List<Postings> posting) {
		this.posting = posting;
	}
}