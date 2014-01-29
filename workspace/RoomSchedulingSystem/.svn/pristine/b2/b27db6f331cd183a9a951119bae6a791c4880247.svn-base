package ae.hct.admc.actions;

import ae.hct.admc.domain.Course;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.service.HibernateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class CourseManagementAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    private final static String DELETE = "delete";
    private final static String ADD = "add";
    private final static String ID = "courseId";
    private final static String LIST = "courses";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String action = request.getParameter("action");
        if(EDIT.equalsIgnoreCase(action)){
            request.setAttribute(ID, request.getParameter(ID));
            return mapping.findForward(EDIT);
        }else if(DELETE.equalsIgnoreCase(action)){
            Course course = (Course)HibernateService.getEntityById(Course.class,
                    Integer.parseInt(request.getParameter(ID)));
            
            List<RoomClassSchedule> classes = HibernateService.getRoomClassScheduleForCourse(course);
            for(RoomClassSchedule rcs : classes){
                HibernateService.deleteEntity(rcs);
            }
            
//            List<RoomEventSchedule> events = HibernateService.getRoomEventScheduleForCourse(course);
//            for(RoomEventSchedule res : events){
//                HibernateService.deleteEntity(res);
//            }
            
            HibernateService.deleteEntity(course);
        }else if(ADD.equalsIgnoreCase(action)){
            return mapping.findForward(EDIT);
        }
        
        request.setAttribute(LIST, HibernateService.getAllCourses());
        return mapping.findForward(SUCCESS);

    }
}