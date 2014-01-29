/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Where;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="period")
public class Period extends BaseEntity {

    @Column(name="period_segment")
    private String periodSegment;

    @OneToMany(mappedBy = "period", fetch=FetchType.EAGER)
    @Where(clause="record_status = 'Active'")
    private Set<RoomClassSchedule> classSchedules;
    
    @OneToMany(mappedBy = "period", fetch=FetchType.EAGER)
    @Where(clause="record_status = 'Active'")
    private Set<RoomEventSchedule> eventSchedules;
    
    public String getPeriodSegment() {
        return periodSegment;
    }

    public void setPeriodSegment(String periodSegment) {
        this.periodSegment = periodSegment;
    }

    public Set<RoomClassSchedule> getClassSchedules() {
        return classSchedules;
    }

    public void setClassSchedules(Set<RoomClassSchedule> classSchedules) {
        this.classSchedules = classSchedules;
    }

    public Set<RoomEventSchedule> getEventSchedules() {
        return eventSchedules;
    }

    public void setEventSchedules(Set<RoomEventSchedule> eventSchedules) {
        this.eventSchedules = eventSchedules;
    }
    
}
