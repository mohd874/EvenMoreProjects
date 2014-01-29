package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Block;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomType;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.RoomSchedulingSystemUtil;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ReportRoomsListActionForm extends org.apache.struts.action.ActionForm {

    private String action;
    //Filter
    private int selectedSemester;
    private String roomName;
    private int selectedBlock;
    private int roomType;
    //Report
    private int roomId;
    private String selectedWeek;
    private boolean showClass;
    private boolean showEvent;
    private boolean showAccepted;
    private boolean showPending;
    private static final String FILTER = "Filter";
    private static final String GENERATE = "Generate";

    public ReportRoomsListActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (action == null) {
            action = FILTER;
        }

        if (FILTER.equalsIgnoreCase(action)) {
            if (roomName == null) {
                roomName = "";
            }

            prepareListItems(request);
        } else if (GENERATE.equalsIgnoreCase(action)) {
            int week = 0;
            try{
                week = Integer.parseInt(selectedWeek);
            }catch(NumberFormatException ex){
                errors.add("selectedWeek", new ActionMessage("error.report.week"));
            }
            if(errors.size() > 0){
                prepareListItems(request);
            }else{
                RoomSchedulingSystemUtil.getScheduleForRoom(
                    (Semester) HibernateService.getEntityById(Semester.class, getSelectedSemester()),
                    (Room) HibernateService.getEntityById(Room.class, getRoomId()),
                    week,
                    request);

                RoomSchedulingSystemUtil.getEventScheduleForRoom(
                    (Semester) HibernateService.getEntityById(Semester.class, getSelectedSemester()), 
                    (Room) HibernateService.getEntityById(Room.class, getRoomId()),
                    week, 
                    request);
            }
        }

        return errors;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(int selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(int selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public boolean isShowClass() {
        return showClass;
    }

    public void setShowClass(boolean showClass) {
        this.showClass = showClass;
    }

    public boolean isShowEvent() {
        return showEvent;
    }

    public void setShowEvent(boolean showEvent) {
        this.showEvent = showEvent;
    }

    public boolean isShowAccepted() {
        return showAccepted;
    }

    public void setShowAccepted(boolean showAccepted) {
        this.showAccepted = showAccepted;
    }

    public boolean isShowPending() {
        return showPending;
    }

    public void setShowPending(boolean showPending) {
        this.showPending = showPending;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getSelectedWeek() {
        return selectedWeek;
    }

    public void setSelectedWeek(String selectedWeek) {
        this.selectedWeek = selectedWeek;
    }

    private void prepareListItems(HttpServletRequest request) {
        request.setAttribute("rooms", HibernateService.getRoomsByParameters(roomName,
                (Block) HibernateService.getEntityById(Block.class, selectedBlock),
                (RoomType) HibernateService.getEntityById(RoomType.class, roomType)));
        request.setAttribute("semesters", HibernateService.getAllSemester());
        request.setAttribute("blocks", HibernateService.getAllBlocks());
        request.setAttribute("roomTypes", HibernateService.getAllRoomTypes());
    }
}
