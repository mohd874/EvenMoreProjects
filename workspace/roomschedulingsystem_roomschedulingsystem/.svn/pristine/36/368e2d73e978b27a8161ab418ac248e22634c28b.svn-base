package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UserActionForm extends org.apache.struts.action.ActionForm {

    private String action;
    private int userId;
    private String loginName;
    private int selectedRole;
    private String password;
    private String cpassword;

    public UserActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if ("submit".equalsIgnoreCase(getAction())) {
            if(loginName == null || loginName.length() < 1){
                errors.add("loginName", new ActionMessage("error.user.loginName"));
            }else if(userId == 0 && userExist()){
                errors.add("loginName", new ActionMessage("error.user.loginName.AlreadyExist"));
            }
            if(password == null || password.length() < 5){
                errors.add("password", new ActionMessage("error.user.password"));
            }else{
                if(!password.equals(cpassword)){
                    errors.add("cpassword", new ActionMessage("error.user.cpassword"));
                }
            }
            if(selectedRole == 0){
                errors.add("loginName", new ActionMessage("error.user.role"));
            }
        } else if ("edit".equalsIgnoreCase(action)) {
            if(userId != 0){
                User user = (User)HibernateService.getEntityById(User.class, userId);
                setLoginName(user.getLoginName());
                setPassword(user.getPassword());
                setCpassword(user.getPassword());
                setSelectedRole(user.getRole().getId());
            }
        }

        request.setAttribute("roles", HibernateService.getAllRoles());
        return errors;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(int selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    private boolean userExist() {
        List<User> users = HibernateService.getAllUsers();
        for (User user : users) {
            if(user.getLoginName().equalsIgnoreCase(loginName)){
                return true;
            }
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

}
