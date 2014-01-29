package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.CourseActionForm;
import ae.hct.admc.domain.Course;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class CourseAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        CourseActionForm caf = (CourseActionForm)form;
        if("submit".equalsIgnoreCase(caf.getAction())){
            saveCourse(request,form);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(EDIT);
                
    }

    private void saveCourse(HttpServletRequest request, ActionForm form) {
        CourseActionForm caf = (CourseActionForm)form;
        Course course = null;
        
        if(caf.getCourseId() != 0){
            course = (Course)HibernateService.getEntityById(Course.class, caf.getCourseId());
        }else{
            course = new Course();
        }
        
        course.setCode(caf.getCode());
        course.setTitle(caf.getTitle());
        course.setDescription(caf.getDescription());
        
        HibernateService.saveEntity(course, (User)request.getSession().getAttribute("userSession"));
    }
}