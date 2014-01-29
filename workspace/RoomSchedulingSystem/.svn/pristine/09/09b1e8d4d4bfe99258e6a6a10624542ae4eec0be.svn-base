/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Course;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class CourseActionForm extends org.apache.struts.action.ActionForm {
    
   private String action;
   private int courseId;
   private String code;
   private String description;
   private String title;

   public CourseActionForm() {
       super();
       // TODO Auto-generated constructor stub
   }

   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       if ("submit".equalsIgnoreCase(getAction())) {
            if(code == null || code.length() < 1){
                errors.add("code", new ActionMessage("error.course.code"));
            }else{
                if(courseId == 0){
                    Course c = HibernateService.getCourseByCode(code);
                    if(c != null){
                        errors.add("code", new ActionMessage("error.course.code.alreadyExist"));
                    }
                }
            }
            if(title == null || title.length() < 1){
                errors.add("title", new ActionMessage("error.course.title"));
            }
            if(description == null || description.length() < 1){
                errors.add("description", new ActionMessage("error.course.description"));
            }
       } else if ("edit".equalsIgnoreCase(action)) {
            if (courseId != 0) {
                Course course = (Course)HibernateService.getEntityById(Course.class, courseId);
                setCode(course.getCode());
                setDescription(course.getDescription());
                setTitle(course.getTitle());
            }
        }

       return errors;
   }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
