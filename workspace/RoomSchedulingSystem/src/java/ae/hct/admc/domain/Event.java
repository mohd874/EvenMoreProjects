package ae.hct.admc.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class Event extends BaseEntity{
    
    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    
    @Column(name="number_of_attendees")
    private int numberOfAttendees;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int NumberOfAttendees) {
        this.numberOfAttendees = NumberOfAttendees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public enum EventType{
        Exam,Seminar,Conference;
                
        public List<String> getEventTypeList(){
            List<String> eventType = new ArrayList<String>();
            for(EventType e : values()){
                eventType.add(e.name());
            }
            return eventType;
        }
        
        public String getValue(){
            return this.name();
        }
        
        public String getName(){
            return this.name();
        }
        
        public EventType getEventTypeByName(String name){
            if(name != null){
                for (EventType type : EventType.values()) {
                    if(name.equalsIgnoreCase(type.getName())){
                        return type;
                    }
                }
            }
            return EventType.Exam;
        }
        
    }
}
