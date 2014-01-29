/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="role")
public class Role extends BaseEntity{

    @Column(name="description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    private Set<Permission> permissions;

    public Role(){
        setPermissions(new HashSet<Permission>());
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
