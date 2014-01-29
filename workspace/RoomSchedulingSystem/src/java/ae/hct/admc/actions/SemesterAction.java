/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.SemesterActionForm;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.DateTimeUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class SemesterAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SemesterActionForm saf = (SemesterActionForm)form;
        if("submit".equalsIgnoreCase(saf.getAction())){
            saveSemester(request,form);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(EDIT);
        
    }

    private void saveSemester(HttpServletRequest request, ActionForm form) {
        SemesterActionForm saf = (SemesterActionForm)form;
        Semester semester = null;
        
        
        if(saf.getSemesterId() != 0){
            semester = (Semester)HibernateService.getEntityById(Semester.class, saf.getSemesterId());
        }else{
            semester = new Semester();
        }
        
        
        semester.setSemesterNo(String.valueOf(saf.getSemesterNo()));
        semester.setDateFrom(DateTimeUtil.parseDateString(saf.getDateFrom()));
        semester.setDateTo(DateTimeUtil.parseDateString(saf.getDateTo()));
        
        HibernateService.saveEntity(semester, (User)request.getSession().getAttribute("userSession"));
    }
}