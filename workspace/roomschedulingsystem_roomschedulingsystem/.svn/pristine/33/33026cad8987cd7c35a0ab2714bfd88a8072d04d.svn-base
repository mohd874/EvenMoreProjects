/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Event;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class EventActionForm extends org.apache.struts.action.ActionForm {

    private String action;
    private int eventId;
    private String name;
    private String selectedEventType;
    private String numberOfAttendees;
    private String description;

    public EventActionForm() {
        super();
    // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if ("submit".equalsIgnoreCase(getAction())) {
            if (getName() == null || getName().length() < 1) {
                errors.add("name", new ActionMessage("error.event.required"));
            }
            if ("none".equalsIgnoreCase(selectedEventType)) {
                errors.add("type", new ActionMessage("error.event.type"));
            }
            if (numberOfAttendees == null || numberOfAttendees.length() < 1) {
                errors.add("numberOfAttendees", new ActionMessage("error.event.numberOfAttendees"));
            } else {
                try {
                    Integer.parseInt(numberOfAttendees);
                } catch (NumberFormatException ex) {
                    errors.add("numberOfAttendees", new ActionMessage("error.event.numberOfAttendees"));
                }
            }
            if (description == null || description.length() < 1) {
                errors.add("description", new ActionMessage("error.event.description"));
            }
        } else if ("edit".equalsIgnoreCase(action)) {
            if (eventId != 0) {
                Event event = (Event)HibernateService.getEntityById(Event.class, eventId);
                setName(event.getName());
                setNumberOfAttendees(String.valueOf(event.getNumberOfAttendees()));
                setDescription(event.getDescription());
            }
        }
        request.setAttribute("eventTypes", Event.EventType.Exam.getEventTypeList());
        return errors;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedEventType() {
        return selectedEventType;
    }

    public void setSelectedEventType(String selectedEventType) {
        this.selectedEventType = selectedEventType;
    }

    public String getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(String numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
