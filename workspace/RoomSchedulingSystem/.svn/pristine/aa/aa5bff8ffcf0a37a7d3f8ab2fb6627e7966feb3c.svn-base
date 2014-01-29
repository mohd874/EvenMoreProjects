/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="permission")
public class Permission extends BaseEntity{

    @Column(name="description")
    private String description;
    
    @ManyToMany(mappedBy="permissions")
    private Set<Role> roles;

    public Permission(){
        setRoles(new HashSet<Role>());
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
