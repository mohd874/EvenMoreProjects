/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.util;

import ae.hct.admc.actionFormBeans.RoomScheduleActionBean;
import ae.hct.admc.domain.Event;
import ae.hct.admc.domain.Period;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.service.HibernateService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Saeed
 */
public class RoomSchedulingSystemUtil {

    public static void getCurrentScheduleForRoom(int semesterWeek, HttpServletRequest request) {
        Semester semester = HibernateService.getCurrentSemester();
        getScheduleForRoom(semester, semesterWeek, request);
    }

    public static void getScheduleForRoom(Semester semester, int semesterWeek, HttpServletRequest request) {

        Room room = HibernateService.getRoomById(Integer.parseInt(request.getParameter("room")));

        List<Period> periods = getPeriodsForSemesterRoom(semester, room, semesterWeek);

        Object[] objs = new Object[6];

//            LOG.debug("periods: "+periods);
//            LOG.debug("objs: "+objs);

        request.setAttribute("periods", periods);
        request.setAttribute("objs", objs);
        request.setAttribute("room", room.getId());
        request.setAttribute("roomObj", room);
        request.setAttribute("semester", semester.getId());
        request.setAttribute("semesterWeek", semesterWeek);
    }

    public static void getScheduleForRoom(Semester semester, Room room, int semesterWeek, HttpServletRequest request) {

        List<Period> periods = getPeriodsForSemesterRoom(semester, room, semesterWeek);

        Object[] objs = new Object[6];

//            LOG.debug("periods: "+periods);
//            LOG.debug("objs: "+objs);

        request.setAttribute("periods", periods);
        request.setAttribute("objs", objs);
        request.setAttribute("roomObj", room);
    }

    public static void getEventScheduleForRoom(Semester semester, Room room, int semesterWeek, HttpServletRequest request) {

        List<Period> periods = getEventPeriodsForSemesterRoom(semester, room, semesterWeek);

        Object[] objs = new Object[6];

//            LOG.debug("periods: "+periods);
//            LOG.debug("objs: "+objs);

        request.setAttribute("periods", periods);
        request.setAttribute("objs", objs);
        request.setAttribute("roomObj", room);
    }

    public static List<Period> getPeriodsForSemesterRoom(Semester semester, Room room, int semesterWeek) {
        List<Period> periods = HibernateService.getAllPeriods();

        for (Period period : periods) {
            period.getClassSchedules().clear();
            period.getClassSchedules().addAll(HibernateService.getClassSchedule(
                    period, semester, semesterWeek, room));
        }
        return periods;
    }

    private static List<Period> getEventPeriodsForSemesterRoom(Semester semester, Room room, int semesterWeek) {
        List<Period> periods = HibernateService.getAllPeriods();

        for (Period period : periods) {
            period.getClassSchedules().clear();
            period.getEventSchedules().addAll(HibernateService.getEventSchedule(
                    period, semester, semesterWeek, room));
        }
        return periods;
    }

    public static void sendNewClassReservationRequestEmail(String toEmail, RoomClassSchedule rcs, int requestId) throws MessagingException {
        String to = toEmail;
        String from = "roomschedulingsystem@admc.hct.ac.ae";
        String subject = "New Request";
        String body = "Your new request will be saved with id # \"" + requestId + "\". Your Request Details is: \n" +
                "Reserver: " + rcs.getReserver().getEmployee().getFirstName() + " " +
                rcs.getReserver().getEmployee().getLastName() + "\n" +
                "Room: " + rcs.getRoom().getName() + "\n" +
                "Semester: " + rcs.getSemester().getFullName() + "\n" +
                "Course: " + rcs.getCourse().getCode() + " " + rcs.getCourse().getTitle() + "\n" +
                "Day Of Week: " + rcs.getDayOfWeekName() + "\n" +
                "Semester Week: " + rcs.getSemesterWeek() + "\n" +
                "Period: " + rcs.getPeriod().getPeriodSegment() + "\n" +
                "Current Status: " + rcs.getReservationStatus();

        MailUtil.sendMail(to, from, subject, body, false);
    }

    public static void sendNewEventReservationRequestEmail(RoomEventSchedule res, int requestId) throws MessagingException {
        String to = res.getReserver().getEmployee().getEmail();
        String from = "roomschedulingsystem@admc.hct.ac.ae";
        String subject = "New Request";
        String body = "Your new request will be saved with id # \"" + requestId + "\". Your Request Details is: \n" +
                "Reserver: " + res.getReserver().getEmployee().getFirstName() + " " +
                res.getReserver().getEmployee().getLastName() + "\n" +
                "Room: " + res.getRoom().getName() + "\n" +
                "Semester: " + res.getSemester().getFullName() + "\n" +
                "Course: " + res.getEvent().getName() + "\n" +
                "Day Of Week: " + res.getDayOfWeekName() + "\n" +
                "Semester Week: " + res.getSemesterWeek() + "\n" +
                "Period: " + res.getPeriod().getPeriodSegment() + "\n" +
                "Current Status: " + res.getReservationStatus();

        MailUtil.sendMail(to, from, subject, body, false);
    }

    public static void sendEventChangeStatusEmail(RoomEventSchedule res, String oldStatus, String newStatus, int requestId) throws MessagingException {
        String to = res.getReserver().getEmployee().getEmail();
        String from = "roomschedulingsystem@admc.hct.ac.ae";
        String subject = "Request Status Change";
        String body = "Your request with id # \"" + requestId + "\" has changed from " +
                "\"" + oldStatus + "\" To \"" + newStatus + "\" ";
        ;

        MailUtil.sendMail(to, from, subject, body, false);
    }

    public static void sendClassChangeStatusEmail(RoomClassSchedule rcs, String oldStatus, String newStatus, int requestId) throws MessagingException {
        String to = rcs.getReserver().getEmployee().getEmail();
        String from = "roomschedulingsystem@admc.hct.ac.ae";
        String subject = "Request Status Change";
        String body = "Your request with id # \"" + requestId + "\" has changed from " +
                "\"" + oldStatus + "\" To \"" + newStatus + "\" ";
        ;

        MailUtil.sendMail(to, from, subject, body, false);
    }

    public static void deleteAllReservationsForRoom(Room room) {
        List<RoomClassSchedule> classes = HibernateService.getRoomClassScheduleForRoom(room);
        for (RoomClassSchedule rcs : classes) {
            HibernateService.deleteEntity(rcs);
        }

        List<RoomEventSchedule> events = HibernateService.getRoomEventScheduleForRoom(room);
        for (RoomEventSchedule res : events) {
            HibernateService.deleteEntity(res);
        }

        HibernateService.deleteEntity(room);
    }
}
