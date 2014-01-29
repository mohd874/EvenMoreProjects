package ae.hct.admc.util;

import ae.hct.admc.domain.*;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final SessionFactory annotationFactory;
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
//    private static final EntityManager entityManager = emf.createEntityManager();
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            annotationFactory = new AnnotationConfiguration().addAnnotatedClass(Block.class).
                    addAnnotatedClass(Course.class).
                    addAnnotatedClass(Event.class).
                    addAnnotatedClass(Period.class).
                    addAnnotatedClass(Permission.class).
                    addAnnotatedClass(Role.class).
                    addAnnotatedClass(Room.class).
                    addAnnotatedClass(RoomClassSchedule.class).
                    addAnnotatedClass(RoomEventSchedule.class).
                    addAnnotatedClass(RoomType.class).
                    addAnnotatedClass(Semester.class).
                    addAnnotatedClass(User.class).
                    addAnnotatedClass(Employee.class).
                    configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactory getAnnotationFactory() {
        return annotationFactory;
    }

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("RoomSchedulingSystemPU").createEntityManager();
    }
    
    public static void closeFactories(){
        sessionFactory.close();
        annotationFactory.close();
    }
}
