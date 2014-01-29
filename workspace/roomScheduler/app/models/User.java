package models;

import javax.persistence.Entity;

@Entity
public class User extends BaseModel{

	public String username;
	
	public String passwordHash;
	
}
