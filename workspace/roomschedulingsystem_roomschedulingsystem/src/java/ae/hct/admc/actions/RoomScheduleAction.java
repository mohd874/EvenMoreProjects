/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.actions;

import ae.hct.admc.domain.Course;
import ae.hct.admc.domain.Period;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Saeed
 */
public class RoomScheduleAction extends org.apache.struts.action.Action {

    Logger LOG = Logger.getLogger(RoomScheduleAction.class);
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String action = request.getParameter("action");
        pageDebug(request);

        if (action == null) {
//            RoomSchedulingSystemUtil.getCurrentScheduleForRoom(request);
        } else if ("Reserve".equalsIgnoreCase(action)) {
//            addReservation(request);
//            RoomSchedulingSystemUtil.getCurrentScheduleForRoom(request);
        } else {
//            RoomSchedulingSystemUtil.getCurrentScheduleForRoom(request);
        }
//        LOG.debug("request: "+request.getAttribute("periods"));
        return mapping.findForward(SUCCESS);
    }

    
    private void pageDebug(HttpServletRequest request) {
        LOG.debug("Room Id: " + request.getParameter("room"));
        LOG.debug("action: " + request.getParameter("action"));

        LOG.debug("current semester: " + request.getParameter("semester"));
        LOG.debug("selectedCourse: " + request.getParameter("selectedCourse"));
        LOG.debug("reservationWeeks: " + request.getParameter("reservationWeeks"));
        LOG.debug("semester: " + request.getParameter("semester"));
        LOG.debug("room: " + request.getParameter("room"));
        LOG.debug("periodDay: " + request.getParameterValues("periodDay"));
    }
}
