package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.LoginActionBean;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class LoginAction extends org.apache.struts.action.Action {
    
    private final static String ADMIN = "admin";
    private final static String ROOMS = "rooms";
    private final static String LOGIN = "login";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if("logout".equalsIgnoreCase(request.getParameter("action"))){
            return mapping.findForward(LOGIN);
        }
        
        User user = ((LoginActionBean)form).getUser();
        if(user.hasPermission("AccessAdministration")){
            return mapping.findForward(ADMIN);
        }else{
            return mapping.findForward(ROOMS);
        }
    }
}