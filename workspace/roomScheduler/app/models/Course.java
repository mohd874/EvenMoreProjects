package models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Course extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5763824393646461390L;

	public String label;

	public String description;
	
	@ManyToMany(mappedBy="courses")
    private Set<Teacher> teacher;
}
