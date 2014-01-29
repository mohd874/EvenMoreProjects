/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import ae.hct.admc.util.DateTimeUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="room_event_schedule")
public class RoomEventSchedule extends BaseEntity {

    public static final String PENDING_STATUS = "pending";
    public static final String ACCEPTED_STATUS = "accepted";
    
    @JoinColumn(name = "period", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private Period period;
    
    @Column(name="schedule_date")
    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @JoinColumn(name = "event", referencedColumnName = "id")
    @ManyToOne
    private Event event;
    
    @JoinColumn(name = "room", referencedColumnName = "id")
    @ManyToOne
    private Room room;

    @Column(name="day_of_week")
    private int dayOfWeek;
    
    @JoinColumn(name = "semester", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Semester semester;
    
    @Column(name="semester_week")
    private int semesterWeek;
    
    @Column(name="reservation_status")
    private String reservationStatus;

    @JoinColumn(name = "reserver", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private User reserver;
    
    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getSemesterWeek() {
        return semesterWeek;
    }

    public void setSemesterWeek(int semesterWeek) {
        this.semesterWeek = semesterWeek;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public User getReserver() {
        return reserver;
    }

    public void setReserver(User reserver) {
        this.reserver = reserver;
    }
    
    public String getDayOfWeekName(){
        return DateTimeUtil.getDayOfWeekName(dayOfWeek);
    }
}
