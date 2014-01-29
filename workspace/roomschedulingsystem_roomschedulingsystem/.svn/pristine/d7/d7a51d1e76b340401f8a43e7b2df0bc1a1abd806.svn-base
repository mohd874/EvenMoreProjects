/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import ae.hct.admc.util.DateTimeUtil;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="room_class_schedule")
public class RoomClassSchedule extends BaseEntity{

    public static final String PENDING_STATUS = "pending";
    public static final String ACCEPTED_STATUS = "accepted";
    
    @JoinColumn(name = "room", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Room room;
    
    @JoinColumn(name = "reserver", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private User reserver;
    
    @JoinColumn(name = "semester", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Semester semester;
    
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private Course course;
    
    @Column(name="day_of_week")
    private int dayOfWeek;
    
    @Column(name="semester_week")
    private int semesterWeek;
    
    @JoinColumn(name = "period", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private Period period;
    
    @Column(name="reservation_status")
    private String reservationStatus;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getReserver() {
        return reserver;
    }

    public void setReserver(User reserver) {
        this.reserver = reserver;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int DayOfWeek) {
        this.dayOfWeek = DayOfWeek;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public int getSemesterWeek() {
        return semesterWeek;
    }

    public void setSemesterWeek(int semesterWeek) {
        this.semesterWeek = semesterWeek;
    }
    
    public String getDayOfWeekName(){
        return DateTimeUtil.getDayOfWeekName(dayOfWeek);
    }
}
