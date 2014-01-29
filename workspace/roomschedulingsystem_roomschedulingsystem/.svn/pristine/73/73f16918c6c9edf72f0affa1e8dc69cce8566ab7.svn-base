/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Block;
import ae.hct.admc.domain.RoomType;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.service.HibernateService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class RoomsListForm extends org.apache.struts.action.ActionForm {

    private int semester;
    private int selectedSemester;
    private String roomName;
    private int roomType;
    private int block;

    public RoomsListForm() {
        super();
    // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("rooms", HibernateService.getAllRooms());
            Semester currentSemester = HibernateService.getCurrentSemester();
            if(currentSemester != null){
                semester = currentSemester.getId();
            }
        } else if ("Filter".equalsIgnoreCase(action)) {
            request.setAttribute("rooms", HibernateService.getRoomsByParameters(
                    roomName,
                    (Block) HibernateService.getEntityById(Block.class, block),
                    (RoomType) HibernateService.getEntityById(RoomType.class, roomType)));
            Semester filterSemester = (Semester)HibernateService.getEntityById(Semester.class, selectedSemester);
            if(filterSemester == null){
                semester = HibernateService.getCurrentSemester().getId();
            }else{
                semester = filterSemester.getId();
            }
        }

        request.setAttribute("semesters", HibernateService.getAllSemester());
        request.setAttribute("semester", semester);
        request.setAttribute("blocks", HibernateService.getAllBlocks());
        request.setAttribute("roomTypes", HibernateService.getAllRoomTypes());

        return errors;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(int selectedSemester) {
        this.selectedSemester = selectedSemester;
    }
}
