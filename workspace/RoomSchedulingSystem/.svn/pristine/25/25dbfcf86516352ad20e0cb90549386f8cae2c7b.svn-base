/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.RoomActionForm;
import ae.hct.admc.domain.Block;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomType;
import ae.hct.admc.domain.User;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Saeed
 */
public class RoomAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        RoomActionForm raf = (RoomActionForm)form;
        if("submit".equalsIgnoreCase(raf.getAction())){
            saveRoom(request,form);
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(EDIT);
    }

    private void saveRoom(HttpServletRequest request,ActionForm  form) {
        RoomActionForm raf = (RoomActionForm)form;
        int roomId = 0;
        if(raf.getRoomId() != null){
            roomId = raf.getRoomId();
        }
        
        Room room = null;
        if(roomId != 0){
            room = (Room)HibernateService.getEntityById(Room.class, roomId);
        }else{
            room = new Room();
        }
        
        Block block = (Block)HibernateService.getEntityById(Block.class, raf.getSelectedBlock());
        RoomType roomType = (RoomType)HibernateService.getEntityById(RoomType.class, raf.getSelectedType());
        
        room.setName(raf.getName());
        room.setCapacity(raf.getCapacity());
        room.setDescription(raf.getDescription());
        room.setBlock(block);
        room.setType(roomType);
        
        HibernateService.saveEntity(room, (User)request.getSession().getAttribute("userSession"));
    }
}