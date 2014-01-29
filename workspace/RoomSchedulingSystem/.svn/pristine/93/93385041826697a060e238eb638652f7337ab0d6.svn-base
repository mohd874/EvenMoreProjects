package ae.hct.admc.actions;

import ae.hct.admc.domain.Block;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.RoomSchedulingSystemUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class BlockManagementAction extends org.apache.struts.action.Action {

    private final static String SUCCESS = "success";
    private final static String EDIT = "edit";
    private final static String DELETE = "delete";
    private final static String ADD = "add";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String action = request.getParameter("action");
        if (EDIT.equalsIgnoreCase(action)) {
            request.setAttribute("blockId", request.getParameter("blockId"));
            return mapping.findForward(EDIT);
        } else if (DELETE.equalsIgnoreCase(action)) {
            Block block = (Block) HibernateService.getEntityById(Block.class,
                    Integer.parseInt(request.getParameter("blockId")));

            List<Room> rooms = HibernateService.getRoomsForBlock(block);

            for (Room room : rooms) {
                RoomSchedulingSystemUtil.deleteAllReservationsForRoom(room);
            }
            
            HibernateService.deleteEntity(block);
        } else if (ADD.equalsIgnoreCase(action)) {
            return mapping.findForward(EDIT);
        }

        request.setAttribute("blocks", HibernateService.getAllBlocks());
        return mapping.findForward(SUCCESS);

    }
}
