/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Semester;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.DateTimeUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class SemesterActionForm extends org.apache.struts.action.ActionForm {
    
   private String action;
   private int semesterId;
   private int semesterNo;
   private String dateFrom;
   private String dateTo;
    
   public SemesterActionForm() {
       super();
       // TODO Auto-generated constructor stub
   }

   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       Date fromDate = DateTimeUtil.parseDateString(getDateFrom());
       Date toDate = DateTimeUtil.parseDateString(getDateTo());
       if ("submit".equalsIgnoreCase(getAction())) {
            if (getSemesterNo() == 0) {
                errors.add("name", new ActionMessage("error.semester.semesterNo"));
            }
            if (getDateFrom() == null) {
                errors.add("dateFrom", new ActionMessage("error.semester.dateFrom"));
            }else{
                if(fromDate==null){
                    errors.add("dateFrom", new ActionMessage("error.semester.dateFrom.invalid"));
                }
            }
            if(getDateTo() == null){
                errors.add("dateTo", new ActionMessage("error.semester.dateTo"));
            }else{
                if(toDate==null){
                    errors.add("dateTo", new ActionMessage("error.semester.dateTo.invalid"));
                }
            }
            if(fromDate != null && toDate != null){
                if(fromDate.after(toDate)){
                    errors.add("dateTo", new ActionMessage("error.semester.dateTo.beforeDateTo"));
                }
            }
        } else if ("edit".equalsIgnoreCase(action)) {
            if (semesterId != 0) {
                Semester semester = (Semester)HibernateService.getEntityById(Semester.class, semesterId);
                setSemesterNo(Integer.parseInt(semester.getSemesterNo()));
                setDateFrom(DateTimeUtil.getStringFromDate(semester.getDateFrom()));
                setDateTo(DateTimeUtil.getStringFromDate(semester.getDateTo()));
            }
        }
       
       return errors;
   }

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
