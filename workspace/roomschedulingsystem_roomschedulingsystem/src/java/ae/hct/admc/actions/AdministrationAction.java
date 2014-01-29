/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Saeed
 */
public class AdministrationAction extends org.apache.struts.action.Action {
    
    Logger LOG = Logger.getLogger(AdministrationAction.class);
    
    /* forward name="success" path="" */
    private final static String ROOM = "room";
    private final static String BLOCK = "block";
    private final static String USERS = "users";
    private final static String EVENTS = "events";
    private final static String COURSES = "courses";
    private final static String SEMESTERS = "semesters";
    
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
//        debugAction(request);
        String action = request.getParameter("action");
        if(ROOM.equalsIgnoreCase(action)){
            return mapping.findForward(ROOM);
        }else if(BLOCK.equalsIgnoreCase(action)){
            return mapping.findForward(BLOCK);
        }else if(COURSES.equalsIgnoreCase(action)){
            return mapping.findForward(COURSES);
        }else if(EVENTS.equalsIgnoreCase(action)){
            return mapping.findForward(EVENTS);
        }else if(USERS.equalsIgnoreCase(action)){
            return mapping.findForward(USERS);
        }else{
            return mapping.findForward(SEMESTERS);
        }
    }

    private void debugAction(HttpServletRequest request) {
        LOG.debug("action: "+request.getParameter("action"));
        LOG.debug("room: "+request.getParameter("room"));
    }
}