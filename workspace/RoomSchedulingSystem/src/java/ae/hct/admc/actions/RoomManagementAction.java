/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actions;

import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.service.HibernateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Saeed
 */
public class RoomManagementAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    private final static String DELETE = "delete";
    private final static String ADD = "add";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String action = request.getParameter("action");
        if(EDIT.equalsIgnoreCase(action)){
            request.setAttribute("roomId", request.getParameter("roomId"));
            return mapping.findForward(EDIT);
        }else if(DELETE.equalsIgnoreCase(action)){
            Room room = (Room)HibernateService.getEntityById(Room.class,
                    Integer.parseInt(request.getParameter("roomId")));
            
            List<RoomClassSchedule> classes = HibernateService.getRoomClassScheduleForRoom(room);
            for(RoomClassSchedule rcs : classes){
                HibernateService.deleteEntity(rcs);
            }
            
            List<RoomEventSchedule> events = HibernateService.getRoomEventScheduleForRoom(room);
            for(RoomEventSchedule res : events){
                HibernateService.deleteEntity(res);
            }
            
            HibernateService.deleteEntity(room);
        }else if(ADD.equalsIgnoreCase(action)){
            return mapping.findForward(EDIT);
        }
        
        request.setAttribute("rooms", HibernateService.getAllRooms());
        return mapping.findForward(SUCCESS);
        
    }
}