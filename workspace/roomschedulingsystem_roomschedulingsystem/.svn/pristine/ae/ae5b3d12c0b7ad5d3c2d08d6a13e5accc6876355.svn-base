package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Event;
import ae.hct.admc.domain.Period;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.DateTimeUtil;
import ae.hct.admc.util.RoomSchedulingSystemUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EventScheduleActionBean extends org.apache.struts.action.ActionForm {

    Logger LOG = Logger.getLogger(RoomScheduleActionBean.class);
    private String reservationWeeks;
//    private int selectedEventType;
    private Integer semesterWeek;
    private Integer numberOfWeeksForSemester;
    private Integer semester;
    private Integer selectedEvent;
    private Integer room;
    private List<String> events;
    private String[] periodDay;
    private Integer[] roomEventSchedule;

    public EventScheduleActionBean() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        String action = request.getParameter("action");

        if ("Reserve".equalsIgnoreCase(action)) {
//            if (reservationWeeks == null) {
//                errors.add("reservationWeeks", new ActionMessage("error.roomSchedule.reservationWeeks.required"));
//            }
            if (getSelectedEvent() == 0) {
                errors.add("event", new ActionMessage("error.roomSchedule.course.required"));
            }
            if (getPeriodDay() == null) {
                if (getRoomEventSchedule() != null) {
                    errors.add("period", new ActionMessage("error.roomSchedule.period.reserved"));
                } else {
                    errors.add("period", new ActionMessage("error.roomSchedule.period.required"));
                }
            } else {
                for (int i = 0; i < periodDay.length; i++) {
                    String slot = periodDay[i];
                    if (!validatePeriodDaySlot(slot)) {
                        errors.add("period", new ActionMessage("error.eventSchedule.period.reserved"));
                    }
                }
            }
            if (errors.size() == 0) {
                addReservation(request);
            }
        } else if ("Next Week >>".equalsIgnoreCase(action)) {
            setSemesterWeek(getSemesterWeek() + 1);
            request.setAttribute("semesterWeek", getSemesterWeek());
        } else if ("<< Prev Week".equalsIgnoreCase(action)) {
            setSemesterWeek(getSemesterWeek() - 1);
            request.setAttribute("semesterWeek", getSemesterWeek());
        } else if ("Accept".equalsIgnoreCase(action)) {
            changeRequestStatus(RoomClassSchedule.ACCEPTED_STATUS);
        } else if ("Decline".equalsIgnoreCase(action)) {
            changeRequestStatus("Decline");
        } else if ("Remove".equalsIgnoreCase(action)) {
            changeRequestStatus("Remove");
        }

        if (getEvent() != null) {
            request.setAttribute("event", getEvent());
        } else {
            request.setAttribute("event", 0);
            setEvent(0);
        }
        Semester currentSemester = null;
        if (getSemester() != null) {
            currentSemester = (Semester) HibernateService.getEntityById(Semester.class, getSemester());
        } else {
            currentSemester = HibernateService.getCurrentSemester();
        }

        request.setAttribute("semester", currentSemester.getId());
        setSemester(currentSemester.getId());

        if (getNumberOfWeeksForSemester() != null) {
            request.setAttribute("numberOfWeeksForSemester", getNumberOfWeeksForSemester());
        } else {
            setNumberOfWeeksForSemester(currentSemester.getNumberOfWeeks());
            request.setAttribute("numberOfWeeksForSemester", currentSemester.getNumberOfWeeks());
        }

        if (getSemesterWeek() != null) {
            request.setAttribute("semesterWeek", getSemesterWeek());
        } else {
            setSemesterWeek(DateTimeUtil.getNumberOfWeeksBetweenDates(currentSemester.getDateFrom(),
                    new Date()));
            if (getSemester() > getNumberOfWeeksForSemester()) {
                setSemester(getNumberOfWeeksForSemester());
            }
            request.setAttribute("semesterWeek", getSemesterWeek());
        }

        request.setAttribute("events", HibernateService.getAllEvents());
//        request.setAttribute("eventType", Event.EventType.Exam.getEventTypeList());
//        request.setAttribute("roomObj", HibernateService.getEntityById(Room.class, room));

        RoomSchedulingSystemUtil.getEventScheduleForRoom(
                (Semester) HibernateService.getEntityById(Semester.class, getSemester()),
                //                (Event) HibernateService.getEntityById(Event.class, getEvent()),
                (Room) HibernateService.getEntityById(Room.class, room),
                getSemesterWeek(),
                request);

        RoomSchedulingSystemUtil.getScheduleForRoom(
                (Semester) HibernateService.getEntityById(Semester.class, getSemester()),
                (Room) HibernateService.getEntityById(Room.class, getRoom()),
                getSemesterWeek(),
                request);

        return errors;
    }

    public String getReservationWeeks() {
        return reservationWeeks;
    }

    public void setReservationWeeks(String reservationWeeks) {
        this.reservationWeeks = reservationWeeks;
    }

//    public int getSelectedEventType() {
//        return selectedEventType;
//    }
//
//    public void setSelectedEventType(int selectedEventType) {
//        this.selectedEventType = selectedEventType;
//    }
    public Integer getSemesterWeek() {
        return semesterWeek;
    }

    public void setSemesterWeek(Integer semesterWeek) {
        this.semesterWeek = semesterWeek;
    }

    public Integer getNumberOfWeeksForSemester() {
        return numberOfWeeksForSemester;
    }

    public void setNumberOfWeeksForSemester(Integer numberOfWeeksForSemester) {
        this.numberOfWeeksForSemester = numberOfWeeksForSemester;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getEvent() {
        return getSelectedEvent();
    }

    public void setEvent(Integer event) {
        this.setSelectedEvent(event);
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String[] getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(String[] periodDay) {
        this.periodDay = periodDay;
    }

    public Integer[] getRoomEventSchedule() {
        return roomEventSchedule;
    }

    public void setRoomEventSchedule(Integer[] roomClassSchedule) {
        this.roomEventSchedule = roomClassSchedule;
    }

    private void addReservation(HttpServletRequest request) {
        Semester reserveSemester = HibernateService.getSemesterById(this.semester);
        Room reserveRoom = (Room) HibernateService.getEntityById(Room.class, this.room);
        User user = (User) request.getSession().getAttribute("userSession");

        saveReservation(periodDay, reserveSemester, user, semesterWeek, reserveRoom, request, selectedEvent);
    }

    private void saveReservation(String[] periodDay, Semester semester, User user, int semesterWeek, Room room, HttpServletRequest request, int eventId) {
        RoomEventSchedule res = null;
        StringTokenizer st = null;
        for (String s : periodDay) {
            res = new RoomEventSchedule();
            st = new StringTokenizer(s, "-");
            String periodId = st.nextToken();
            String dayOfWeek = st.nextToken();

            res.setSemester(semester);
            res.setReserver(user);
            res.setDayOfWeek(Integer.parseInt(dayOfWeek));
            res.setPeriod((Period) HibernateService.getEntityById(Period.class, Integer.parseInt(periodId)));
            res.setReservationStatus(res.PENDING_STATUS);
            res.setSemesterWeek(semesterWeek);
            res.setRoom(room);
            res.setEvent((Event) HibernateService.getEntityById(Event.class, eventId));
            HibernateService.saveEntity(res, user);
            try {
                RoomSchedulingSystemUtil.sendNewEventReservationRequestEmail(res, res.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void changeRequestStatus(String status) {
        String oldStatus = null;
        String newStatus = status;

        if (RoomEventSchedule.ACCEPTED_STATUS.equalsIgnoreCase(status)) {
            for (int i = 0; i < roomEventSchedule.length; i++) {
                int id = roomEventSchedule[i];

                RoomEventSchedule rcs =
                        (RoomEventSchedule) HibernateService.getEntityById(RoomEventSchedule.class, id);
                oldStatus = rcs.getReservationStatus();
                rcs.setReservationStatus(RoomEventSchedule.ACCEPTED_STATUS);
                HibernateService.saveEntity(rcs);
                try {
                    RoomSchedulingSystemUtil.sendEventChangeStatusEmail(rcs,
                            oldStatus, newStatus, id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (RoomEventSchedule.PENDING_STATUS.equalsIgnoreCase(status)) {
            for (int i = 0; i < roomEventSchedule.length; i++) {
                int id = roomEventSchedule[i];

                RoomEventSchedule rcs =
                        (RoomEventSchedule) HibernateService.getEntityById(RoomEventSchedule.class, id);
                oldStatus = rcs.getReservationStatus();
                rcs.setReservationStatus(RoomEventSchedule.PENDING_STATUS);
                HibernateService.saveEntity(rcs);
                try {
                    RoomSchedulingSystemUtil.sendEventChangeStatusEmail(rcs,
                            oldStatus, newStatus, id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if ("Decline".equalsIgnoreCase(status)) {
            for (int i = 0; i < roomEventSchedule.length; i++) {
                int id = roomEventSchedule[i];

                RoomEventSchedule rcs =
                        (RoomEventSchedule) HibernateService.getEntityById(RoomEventSchedule.class, id);
                oldStatus = rcs.getReservationStatus();
                if (RoomClassSchedule.PENDING_STATUS.equals(rcs.getReservationStatus())) {
                    HibernateService.deleteEntity(rcs);
                    try {
                        RoomSchedulingSystemUtil.sendEventChangeStatusEmail(rcs,
                                oldStatus, newStatus, id);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if ("Remove".equalsIgnoreCase(status)) {
            for (int i = 0; i < roomEventSchedule.length; i++) {
                int id = roomEventSchedule[i];

                RoomEventSchedule rcs =
                        (RoomEventSchedule) HibernateService.getEntityById(RoomEventSchedule.class, id);
                oldStatus = rcs.getReservationStatus();
                try {
                    RoomSchedulingSystemUtil.sendEventChangeStatusEmail(rcs,
                            oldStatus, newStatus, id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                HibernateService.deleteEntity(rcs);
            }
        }
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Integer selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    private boolean validatePeriodDaySlot(String slot) {
        StringTokenizer st = new StringTokenizer(slot, "-");
        String periodId = st.nextToken();
        String dayOfWeek = st.nextToken();

        Period p = (Period) HibernateService.getEntityById(Period.class, Integer.parseInt(periodId));
        Semester s = (Semester) HibernateService.getEntityById(Semester.class, semester);
        Room r = (Room) HibernateService.getEntityById(Room.class, room);

        List<RoomClassSchedule> rcs = HibernateService.getClassSchedule(p, s, semesterWeek.intValue(), r);

        return (rcs.size() == 0);
    }
}
