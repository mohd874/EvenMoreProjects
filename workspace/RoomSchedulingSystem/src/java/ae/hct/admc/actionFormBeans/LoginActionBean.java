/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.HibernateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class LoginActionBean extends org.apache.struts.action.ActionForm {

    Logger LOG = Logger.getLogger(LoginActionBean.class);
    
    private String name;
    private String password;
    private int number;
    private User user;
    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        number = i;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     *
     */
    public LoginActionBean() {
        super();
    // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        System.out.println("user password: "+getPassword());
        String action = request.getParameter("action");
        if("logout".equalsIgnoreCase(action)){
            request.getSession().setAttribute("userSession", null);
        }else{
            if (getName() == null || getName().length() < 1) {
                errors.add("name", new ActionMessage("error.name.required"));
            }
            if (getPassword() == null || getPassword().length() < 1) {
                errors.add("password", new ActionMessage("error.password.required"));
            }
            if(errors.size()==0){
                if(userExist()){
                    request.getSession().setAttribute("userSession",getUser());
                }else{
                    errors.add("userDoesNotExist", new ActionMessage("error.userDoesNotExist"));
                }
            }
        }
        return errors;
    }

    private boolean userExist() {
        List<User> users = HibernateService.getAllUsers();
        String name = getName();
        String pass = getPassword();
        if(name == null || pass == null)return false;
        for (User user : users) {
            if(user.getLoginName().equalsIgnoreCase(getName().trim()) &&
                    user.getPassword().equals(getPassword())){
                this.setUser(user);
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
