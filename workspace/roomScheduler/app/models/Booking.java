package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Booking extends BaseModel{

	enum TYPE {
		Class, Event
	}
	
	public Room room;
	
	public Date timeFrom;
	
	public Date timeTo;
	
	@Enumerated(EnumType.STRING)
	public TYPE type;
	
	public Teacher teacher;
	
	public User reserver;
	
	
}
