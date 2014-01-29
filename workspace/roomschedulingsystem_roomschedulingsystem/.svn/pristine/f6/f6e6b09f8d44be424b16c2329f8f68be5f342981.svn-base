package ae.hct.admc.actions;

import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class UserManagement extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    private final static String DELETE = "delete";
    private final static String ADD = "add";
    private final static String ID = "userId";
    private final static String LIST = "users";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String action = request.getParameter("action");
        if(EDIT.equalsIgnoreCase(action)){
            request.setAttribute(ID, request.getParameter(ID));
            return mapping.findForward(EDIT);
        }else if(DELETE.equalsIgnoreCase(action)){
            User user = (User)HibernateService.getEntityById(User.class,
                    Integer.parseInt(request.getParameter(ID)));
            
            List<RoomClassSchedule> classes = HibernateService.getRoomClassScheduleForUser(user);
            for(RoomClassSchedule rcs : classes){
                HibernateService.deleteEntity(rcs);
            }
            
            List<RoomEventSchedule> events = HibernateService.getRoomEventScheduleForUser(user);
            for(RoomEventSchedule res : events){
                HibernateService.deleteEntity(res);
            }
            
            HibernateService.deleteEntity(user);
        }else if(ADD.equalsIgnoreCase(action)){
            return mapping.findForward(EDIT);
        }
        
        request.setAttribute(LIST, HibernateService.getAllUsers());
        return mapping.findForward(SUCCESS);
    }
}