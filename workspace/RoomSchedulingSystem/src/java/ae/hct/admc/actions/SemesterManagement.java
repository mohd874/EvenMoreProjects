package ae.hct.admc.actions;

import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.service.HibernateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class SemesterManagement extends org.apache.struts.action.Action {
    
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    private final static String DELETE = "delete";
    private final static String ADD = "add";
    private final static String ID = "semesterId";
    private final static String LIST = "semesters";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    String action = request.getParameter("action");
        if(EDIT.equalsIgnoreCase(action)){
            request.setAttribute(ID, request.getParameter(ID));
            return mapping.findForward(EDIT);
        }else if(DELETE.equalsIgnoreCase(action)){
            Semester semester = (Semester)HibernateService.getEntityById(Semester.class,
                    Integer.parseInt(request.getParameter(ID)));
            
            List<RoomClassSchedule> classes = HibernateService.getRoomClassScheduleForSemester(semester);
            for(RoomClassSchedule rcs : classes){
                HibernateService.deleteEntity(rcs);
            }
            
            List<RoomEventSchedule> events = HibernateService.getRoomEventScheduleForSemester(semester);
            for(RoomEventSchedule res : events){
                HibernateService.deleteEntity(res);
            }
            
            HibernateService.deleteEntity(semester);
        }else if(ADD.equalsIgnoreCase(action)){
            return mapping.findForward(EDIT);
        }
        
        request.setAttribute(LIST, HibernateService.getAllSemester());
        return mapping.findForward(SUCCESS);
                
    }
}