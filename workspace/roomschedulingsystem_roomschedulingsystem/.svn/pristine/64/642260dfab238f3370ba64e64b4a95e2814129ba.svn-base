/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import ae.hct.admc.util.DateTimeUtil;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="semester")
@NamedQueries({
    @NamedQuery(name="Semester.getSemesterById",query="from Semester s where s.id = :id and s.recordStatus = 'Active'"),
    @NamedQuery(name="Semester.getAllSemesters",query="from Semester s where s.recordStatus = 'Active'")})
public class Semester extends BaseEntity{

    @Column(name="semester_no")
    private String semesterNo;
    
    @Temporal(TemporalType.DATE)
    @Column(name="date_from")
    private Date dateFrom;
    
    @Temporal(TemporalType.DATE)
    @Column(name="date_to")
    private Date dateTo;

//    @OneToMany(mappedBy="semester",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    private Set<RoomClassSchedule> roomClassSchedules;
    
    public String getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(String semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getFormatedDateFrom() {
        return DateTimeUtil.getFormatedDate(dateFrom,"dd-MMM-yyyy");
    }

    public String getFormatedDateTo() {
        return DateTimeUtil.getFormatedDate(dateTo,"dd-MMM-yyyy");
    }

    public int getNumberOfWeeks(){
        return DateTimeUtil.getNumberOfWeeksBetweenDates(getDateFrom(), getDateTo());
    }
   
    public String getFullName(){
        String fullName = "Semester "+semesterNo+" ";
        
        fullName += DateTimeUtil.getFormatedDate(dateFrom, "yyyy");
        fullName += " - "+DateTimeUtil.getFormatedDate(dateTo, "yyyy");
        
        return fullName;
    }

//    public Set<RoomClassSchedule> getRoomClassSchedules() {
//        return roomClassSchedules;
//    }
//
//    public void setRoomClassSchedules(Set<RoomClassSchedule> roomClassSchedules) {
//        this.roomClassSchedules = roomClassSchedules;
//    }
}
