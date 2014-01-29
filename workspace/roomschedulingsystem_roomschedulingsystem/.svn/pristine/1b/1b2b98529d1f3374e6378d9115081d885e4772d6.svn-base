/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.service;

import ae.hct.admc.domain.BaseEntity;
import ae.hct.admc.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Saeed
 */
class BaseService {

    private static Session session;
    private static Transaction tx;
    
    protected static void saveOrUpdate(BaseEntity entity){
        if(entity.getCreateDate() == null)entity.setCreateDate(new Date());
        session.save(entity);
        endTransaction();
    }
    
    protected static void beginTransaction() {
        session = HibernateUtil.getAnnotationFactory().getCurrentSession();
        tx = session.beginTransaction();
    }

    protected static void endTransaction() {
        if(tx != null && tx.isActive())
            tx.commit();
        if(session != null && session.isOpen())
            session.close();
    }
    
    protected static Session getSession(){
        return session;
    }
    
    protected static Transaction getTransaction(){
        return tx;
    }
}
