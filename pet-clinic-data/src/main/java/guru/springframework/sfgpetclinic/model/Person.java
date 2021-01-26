package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //means doesn't create a table in the database for Person just herit
public class Person extends BaseEntity {
	
	@Column( name = "first_name" ) //hibernate by default will name it like that. just to be explicit
	private String firstName;
	
	@Column( name = "last_name" )
	private String lastName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
