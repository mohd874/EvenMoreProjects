package ae.hct.admc.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {

   public void contextInitialized(ServletContextEvent event)
   {
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   public void contextDestroyed(ServletContextEvent event)
   {
      HibernateUtil.closeFactories();
   }
   
}
