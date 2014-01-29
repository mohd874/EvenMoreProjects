/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Room;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class RoomActionForm extends org.apache.struts.action.ActionForm {

    Logger LOG = Logger.getLogger(RoomActionForm.class);
    private String action;
    private Integer roomId;
    private String name;
    private Integer selectedType;
    private Integer capacity;
    private Integer selectedBlock;
    private String description;

    /**
     *
     */
    public RoomActionForm() {
        super();
    // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        debugActionForm(request);

        if ("submit".equalsIgnoreCase(getAction())) {
            if (getName() == null || getName().length() < 1) {
                errors.add("name", new ActionMessage("error.room.name"));
            } else {
                if(getRoomId() != null && getRoomId() == 0){
                    Room room = HibernateService.getRoomByName(getName());
                    if (room != null) {
                        errors.add("name", new ActionMessage("error.room.nameAlreadyExist"));
                    }
                }
            }
            if (getCapacity() == null) {
                errors.add("capacity", new ActionMessage("error.room.capacity"));
            } else {
                if (getCapacity() == 0) {
                    errors.add("capacity", new ActionMessage("error.room.zeroCapacity"));
                }
            }
            if (getSelectedType() == null) {
                errors.add("type", new ActionMessage("error.room.type"));
            }
            if (getSelectedBlock() == null) {
                errors.add("block", new ActionMessage("error.name.block"));
            }

        } else if ("edit".equalsIgnoreCase(getAction())) {
            if (getRoomId() != 0) {
                Room room = HibernateService.getRoomById(roomId);
                setCapacity(room.getCapacity());
                setName(room.getName());
                setDescription(room.getDescription());
            }
        }

        request.setAttribute("types", HibernateService.getAllRoomTypes());
        request.setAttribute("blocks", HibernateService.getAllBlocks());
        return errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String string) {
        name = string;
    }

    public Integer getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Integer selectedType) {
        this.selectedType = selectedType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(Integer selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    private void debugActionForm(HttpServletRequest request) {
        LOG.debug("action: " + getAction());
        LOG.debug("roomId: " + getRoomId());
        LOG.debug("name: " + getName());
        LOG.debug("selectedType: " + getSelectedType());
        LOG.debug("selectedBlock: " + getSelectedBlock());
        LOG.debug("description: " + getDescription());
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
