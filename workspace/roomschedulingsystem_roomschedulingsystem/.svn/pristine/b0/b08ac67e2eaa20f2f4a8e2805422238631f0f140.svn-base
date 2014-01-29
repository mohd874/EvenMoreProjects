package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.ReportRoomsListActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class ReportRoomsListAction extends org.apache.struts.action.Action {
    
    private static final String FILTER = "Filter";
    private static final String GENERATE = "Generate";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ReportRoomsListActionForm reportForm = (ReportRoomsListActionForm)form;
        String action = reportForm.getAction();
        
        if(action == null)action = FILTER;
        
        return mapping.findForward(action);
    }
}