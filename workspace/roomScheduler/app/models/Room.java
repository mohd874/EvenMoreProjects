package models;

import javax.persistence.Entity;

@Entity
public class Room extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393964556625971886L;

	public String label;
	
	public Integer capacity;
	
	
}
