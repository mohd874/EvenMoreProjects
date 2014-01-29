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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */

@Entity
@Table(name="employee")
public class Employee extends BaseEntity{

    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="telephone")
    private String telephone;
    
    @Column(name="email")
    private String email;
    
    @JoinColumn(name = "manager", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private Employee manager;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses;

    @OneToOne(mappedBy = "employee")
    private User systemUser;
    
    public Employee(){
        courses = new HashSet<Course>();
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public User getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(User systemUser) {
        this.systemUser = systemUser;
    }
}
