package models;

import java.util.Date;

import play.data.format.Formats.DateTime;
import play.db.ebean.*;

import javax.persistence.*;

@MappedSuperclass
public class BaseModel extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1665030854714569540L;

	@Id
	public Long id;
	
	@DateTime(pattern = "dd-MM-yyyy")
	public Date dateCreated;
	
	@DateTime(pattern = "dd-MM-yyyy")
	public Date dateModified;
	
	
	public static void create(BaseModel model){
		if(model != null && model.id == null)
			model.save();
		else
			update(model);
	}
	
	public static void update(BaseModel model){
		if(model != null && model.id != null)
			model.update();
		else
			create(model);
	}
	
	public static void delete(BaseModel model){
		if(model != null && model.id != null)
			model.delete();
	}
}