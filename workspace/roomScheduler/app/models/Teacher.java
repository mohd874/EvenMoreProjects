package models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Teacher extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1778411737556398113L;
	
	public String name;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses;
}
