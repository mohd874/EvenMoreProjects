package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.UserActionForm;
import ae.hct.admc.domain.Role;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class UserAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        UserActionForm uaf = (UserActionForm)form;
        if("submit".equalsIgnoreCase(uaf.getAction())){
            saveUser(request,form);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(EDIT);
    
    }

    private void saveUser(HttpServletRequest request, ActionForm form) {
        UserActionForm uaf = (UserActionForm)form;
        User user = null;
        
        if(uaf.getUserId() != 0){
            user = (User)HibernateService.getEntityById(User.class, uaf.getUserId());
        }else{
            user = new User();
        }
        
//        user.setName(eaf.getName());
//        user.setNumberOfAttendees(Integer.parseInt(eaf.getNumberOfAttendees()));
//        user.setEventType(Event.EventType.Exam.getEventTypeByName(eaf.getSelectedEventType()));
//        user.setDescription(eaf.getDescription());
        user.setLoginName(uaf.getLoginName());
        user.setPassword(uaf.getPassword());
        user.setRole((Role)HibernateService.getEntityById(Role.class, uaf.getSelectedRole()));
        HibernateService.saveEntity(user, (User)request.getSession().getAttribute("userSession"));
    }
}