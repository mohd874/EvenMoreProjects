/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="course")
public class Course extends BaseEntity {
    
    @Column(name="code")
    private String code;
    
    @Column(name="description")
    private String description;
    
    @Column(name="title")
    private String title;

    @ManyToMany(mappedBy="courses")
    private Set<Employee> teacher;
    
//    @OneToMany(mappedBy="course",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    private Set<RoomClassSchedule> roomClassSchedules;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getTeacher() {
        return teacher;
    }

    public void setTeacher(Set<Employee> teacher) {
        this.teacher = teacher;
    }

    public String getFullName(){
        return code+" "+title;
    }

//    public Set<RoomClassSchedule> getRoomClassSchedules() {
//        return roomClassSchedules;
//    }
//
//    public void setRoomClassSchedules(Set<RoomClassSchedule> roomClassSchedules) {
//        this.roomClassSchedules = roomClassSchedules;
//    }
    
}
