/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */

@Entity
@Table(name="system_user")
public class User extends BaseEntity{

    @Column(name="login_name")
    private String loginName;
    
    @Column(name="login_password")
    private String password;
    
    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @OneToOne
    @JoinColumn(name="employee")
    private Employee employee;
    
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public boolean isInRole(String role){
        if(role == null)return false;
        if(this.role.getDescription().equalsIgnoreCase(role)){
            return true;
        }
        return false;
    }
    
    public boolean hasPermission(String permissionString){
        if(permissionString == null)return false;
        for (Permission permission : role.getPermissions()) {
            if(permission.getDescription().equalsIgnoreCase(permissionString)){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasPermission(String... permissionStrings){
        int count = 0;
        if(permissionStrings == null || permissionStrings.length < 1)return false;
        for (String permissionString : permissionStrings) {
            if(hasPermission(permissionString)){
                count++;
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
    
}
