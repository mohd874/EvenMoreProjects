/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.actionFormBeans;

import ae.hct.admc.domain.Block;
import ae.hct.admc.service.HibernateService;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Saeed
 */
public class BlockActionForm extends org.apache.struts.action.ActionForm {
    
   private String action;
   private Integer blockId;
   private String name;
   private String description;

   /**
    * @return
    */
   public String getName() {
       return name;
   }

   /**
    * @param string
    */
   public void setName(String string) {
       name = string;
   }

   /**
    *
    */
   public BlockActionForm() {
       super();
       // TODO Auto-generated constructor stub
   }

   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       
       if ("submit".equalsIgnoreCase(getAction())) {
            if (getName() == null || getName().length() < 1) {
                errors.add("name", new ActionMessage("error.block.name"));
            } else {
                if(getBlockId() != null && getBlockId() == 0){
                    Block block = HibernateService.getBlockByName(getName());
                    if (block != null) {
                        errors.add("name", new ActionMessage("error.block.nameAlreadyExist"));
                    }
                }
            }
        } else if ("edit".equalsIgnoreCase(getAction())) {
            if (getBlockId() != 0) {
                Block block = (Block)HibernateService.getEntityById(Block.class, blockId);
                setName(block.getName());
                setDescription(block.getDescription());
            }
        }

        return errors;
   }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }
}
