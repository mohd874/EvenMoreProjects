package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.EventActionForm;
import ae.hct.admc.domain.Event;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class EventAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EventActionForm eaf = (EventActionForm)form;
        if("submit".equalsIgnoreCase(eaf.getAction())){
            saveEvent(request,form);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(EDIT);
    
    }

    private void saveEvent(HttpServletRequest request, ActionForm form) {
        EventActionForm eaf = (EventActionForm)form;
        Event event = null;
        
        if(eaf.getEventId() != 0){
            event = (Event)HibernateService.getEntityById(Event.class, eaf.getEventId());
        }else{
            event = new Event();
        }
        
        event.setName(eaf.getName());
        event.setNumberOfAttendees(Integer.parseInt(eaf.getNumberOfAttendees()));
        event.setEventType(Event.EventType.Exam.getEventTypeByName(eaf.getSelectedEventType()));
        event.setDescription(eaf.getDescription());
        
        HibernateService.saveEntity(event, (User)request.getSession().getAttribute("userSession"));
    }
}