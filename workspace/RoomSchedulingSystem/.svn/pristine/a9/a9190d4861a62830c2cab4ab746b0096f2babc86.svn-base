/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actions;

import ae.hct.admc.actionFormBeans.BlockActionForm;
import ae.hct.admc.domain.Block;
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
public class BlockAction extends org.apache.struts.action.Action {
    
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
        
        BlockActionForm baf = (BlockActionForm)form;
        if("submit".equalsIgnoreCase(baf.getAction())){
            saveBlock(request,form);
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(EDIT);
        
    }

    private void saveBlock(HttpServletRequest request, ActionForm form) {
        BlockActionForm baf = (BlockActionForm)form;
        int blockId = 0;
        if(baf.getBlockId() != null){
            blockId = baf.getBlockId();
        }
        
        Block block = null;
        if(blockId != 0){
            block = (Block)HibernateService.getEntityById(Block.class, blockId);
        }else{
            block = new Block();
        }
        
        block.setName(baf.getName());
        block.setDescription(baf.getDescription());
        
        HibernateService.saveEntity(block, (User)request.getSession().getAttribute("userSession"));
    }
}