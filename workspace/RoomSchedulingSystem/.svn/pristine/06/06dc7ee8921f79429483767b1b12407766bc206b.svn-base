/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.service;

import ae.hct.admc.domain.BaseEntity;
import ae.hct.admc.domain.Block;
import ae.hct.admc.domain.Course;
import ae.hct.admc.domain.Event;
import ae.hct.admc.domain.Period;
import ae.hct.admc.domain.Role;
import ae.hct.admc.domain.Room;
import ae.hct.admc.domain.RoomClassSchedule;
import ae.hct.admc.domain.RoomEventSchedule;
import ae.hct.admc.domain.RoomType;
import ae.hct.admc.domain.Semester;
import ae.hct.admc.domain.User;
import ae.hct.admc.util.DateTimeUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Saeed
 */
public class HibernateService extends BaseService {

    private static Logger LOG = Logger.getLogger(HibernateService.class);

    
    public static List<User> getAllUsers() {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from User u where u.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
//        Query query = manager.createQuery(queryString);

        List<User> users = query.list();
//        List<User> users = query.getResultList();
        endTransaction();
        return users;
    }
    
    public static List<Room> getAllRooms() {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Room r where r.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);

        List<Room> rooms = query.list();
        endTransaction();
        return rooms;
    }
    
    public static List<Role> getAllRoles() {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Role r where r.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);

        List<Role> roles = query.list();
        endTransaction();
        return roles;
    }

//    public static List<RoomClassSchedule> getRoomClassScheduleForBlock(Block block) {
//        beginTransaction();
//        String queryString = "from RoomClassSchedule rcs where rcs.block = " +
//                ":block and rcs.recordStatus = 'Active'";
//        org.hibernate.Query query = getSession().createQuery(queryString);
//        query.setParameter("block", block);
//        
//        List<RoomClassSchedule> schedule = query.list();
//        endTransaction();
//        return schedule;
//    }

//    public static List<RoomClassSchedule> getRoomClassScheduleForEvent(Event event) {
//        beginTransaction();
//        String queryString = "from RoomClassSchedule rcs where rcs.event = " +
//                ":event and rcs.recordStatus = 'Active'";
//        org.hibernate.Query query = getSession().createQuery(queryString);
//        query.setParameter("event", event);
//        
//        List<RoomClassSchedule> schedule = query.list();
//        endTransaction();
//        return schedule;
//    }

    public static List<RoomClassSchedule> getRoomClassScheduleForRoom(Room room) {
        beginTransaction();
        String queryString = "from RoomClassSchedule rcs where rcs.room = " +
                ":room and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("room", room);
        
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

    public static List<RoomClassSchedule> getRoomClassScheduleForSemester(Semester semester) {
        beginTransaction();
        String queryString = "from RoomClassSchedule rcs where rcs.course = " +
                ":semester and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("semester", semester);
        
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

    public static List<RoomClassSchedule> getRoomClassScheduleForUser(User user) {
        beginTransaction();
        String queryString = "from RoomEventSchedule rcs where rcs.reserver = " +
                ":user and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("user", user);
        
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

//    public static List<RoomEventSchedule> getRoomEventScheduleForBlock(Block block) {
//        beginTransaction();
//        String queryString = "from RoomEventSchedule rcs where rcs.block = " +
//                ":block and rcs.recordStatus = 'Active'";
//        org.hibernate.Query query = getSession().createQuery(queryString);
//        query.setParameter("block", block);
//        
//        List<RoomEventSchedule> schedule = query.list();
//        endTransaction();
//        return schedule;
//    }

    public static List<RoomEventSchedule> getRoomEventScheduleForEvent(Event event) {
                beginTransaction();
        String queryString = "from RoomEventSchedule rcs where rcs.event = " +
                ":event and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("event", event);
        
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

    public static List<RoomEventSchedule> getRoomEventScheduleForRoom(Room room) {
        beginTransaction();
        String queryString = "from RoomEventSchedule rcs where rcs.room = " +
                ":room and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("room", room);
        
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

    public static List<RoomEventSchedule> getRoomEventScheduleForSemester(Semester semester) {
        beginTransaction();
        String queryString = "from RoomEventSchedule rcs where rcs.semester = " +
                ":semester and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("semester", semester);
        
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

    public static List<RoomEventSchedule> getRoomEventScheduleForUser(User user) {
        beginTransaction();
        String queryString = "from RoomEventSchedule rcs where rcs.reserver = " +
                ":user and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("user", user);
        
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }

//    public static List<Room> getRoomsByParameters(String code, Block block, RoomType type) {
//        beginTransaction();
////        EntityManager manager = HibernateUtil.getEntityManager();
//        if(code == null){
//            code = "";
//        }
//        String queryString = "from Room r";
//        
//        if(code != null || block != null || type != null){
//            queryString += " where ";
//        }
//        
//        if(code != null){
//            queryString += "r.name like '"+code+"%'";
//        }
//        if(block != null){
//            if(code != null){
//                queryString +=" and ";
//            }
//            queryString += " r.block = :block ";
//        }
//        if(type != null){
//            if(code != null || block != null){
//                queryString += " and ";
//            }
//            queryString += "r.type = :type";
//        }
        
    public static List<Room> getRoomsByParameters(String code, Block block, RoomType type) {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        boolean where = true;
        if(code == null){
            code = "";
        }
        String queryString = "from Room r";
        
//        if(code != null || block != null || type != null){
//            queryString += " where ";
//            where = false;
//            and = true;
//        }else{
//            and = false;
//        }
        
        if(code != null){
            if(where)queryString += " where ";
            else queryString += " and ";
            queryString += "r.name like '"+code+"%'";
            where = false;
        }
        
        if(block != null){
            if(where)queryString += " where ";
            else queryString += " and ";
            queryString += " r.block = :block ";
            where = false;
        }
        if(type != null){
            if(where)queryString += " where ";
            else queryString += " and ";
            queryString += "r.type = :type";
            where = false;
        }
        if(where){
            queryString += " where r.recordStatus = 'Active'";
        }else{
            queryString += " and r.recordStatus = 'Active'";
        }
        org.hibernate.Query query = getSession().createQuery(queryString);
        
//        query.setParameter("name", code);
        if(block != null){
            query.setParameter("block", block);
        }
        if(type != null){
            query.setParameter("type", type);
        }
        
        List<Room> rooms = query.list();
        endTransaction();
        return rooms;
    }
    
    public static List<Period> getAllPeriods(){
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Period p where p.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);

        List<Period> rooms = query.list();
        endTransaction();
        return rooms;
    }

    public static List<RoomClassSchedule> getRoomClassScheduleForCourse(Course course) {
        beginTransaction();
        String queryString = "from RoomClassSchedule rcs where rcs.course = " +
                ":course and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("course", course);
        
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        return schedule;
    }
    

    public static List<RoomClassSchedule> getClassSchedule(Period period, 
            Semester semester, int week, Room room){

        beginTransaction();
        String queryString = "from RoomClassSchedule rcs where rcs.semester = " +
                ":semester and rcs.period = :period and rcs.semesterWeek = :week " +
                "and rcs.room = :room and rcs.recordStatus = 'Active' order by rcs.dayOfWeek asc";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("period", period);
        query.setParameter("semester", semester);
        query.setParameter("week", week);
        query.setParameter("room", room);
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        
        return schedule;
    }
    
//    public static List<RoomEventSchedule> getRoomEventScheduleForCourse(Course course) {
//        beginTransaction();
//        String queryString = "from RoomEventSchedule res where res.course = " +
//                ":course and rcs.recordStatus = 'Active'";
//        org.hibernate.Query query = getSession().createQuery(queryString);
//        query.setParameter("course", course);
//        
//        List<RoomEventSchedule> schedule = query.list();
//        endTransaction();
//        return schedule;
//    }
    
    public static List<RoomEventSchedule> getEventSchedule(Period period, 
            Semester semester, int week, Room room){
  
        beginTransaction();
        String queryString = "from RoomEventSchedule res where res.semester = " +
                ":semester and res.period = :period and res.semesterWeek = :week " +
                "and res.room = :room and res.recordStatus = 'Active' order by res.dayOfWeek asc";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("period", period);
        query.setParameter("semester", semester);
        query.setParameter("week", week);
        query.setParameter("room", room);
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        
        return schedule;
    }
    
    public static List<RoomEventSchedule> getEventSchedule(Period period, 
            Semester semester, int week, Room room, int dayOfWeek){
  
        beginTransaction();
        String queryString = "from RoomEventSchedule res where res.semester = " +
                ":semester and res.period = :period and res.semesterWeek = :week " +
                "and res.room = :room and res.dayOfWeek = :dayOfWeek order by res.dayOfWeek asc";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("period", period);
        query.setParameter("semester", semester);
        query.setParameter("week", week);
        query.setParameter("room", room);
        query.setParameter("dayOfWeek", dayOfWeek);
        List<RoomEventSchedule> schedule = query.list();
        endTransaction();
        
        return schedule;
    }
    
    public static List<RoomClassSchedule> getPendingClassSchedule(){
        
        beginTransaction();
        String pendingParam = RoomClassSchedule.PENDING_STATUS;
        String queryString = "from RoomClassSchedule rcs where " +
                "rcs.reservationStatus = :pending and rcs.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("pending", pendingParam);
        List<RoomClassSchedule> schedule = query.list();
        endTransaction();
        
        return schedule;
    }
    
    public static Semester getCurrentSemester(){
        
        Date now = new Date();
        beginTransaction();
        String queryString = "from Semester s where s.dateFrom <= :now " +
                "and s.dateTo >= :now and s.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("now", now);
        List<Semester> semesters = query.list();
        endTransaction();
        if(semesters != null && semesters.size()>0){
            return semesters.get(0);
        }
        return null;
    }
    
    public static Room getRoomById(int roomId){
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Room r where r.id = :id and r.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("id", roomId);
        
        List<Room> rooms = query.list();
        endTransaction();
        if(rooms != null && rooms.size()>0){
            return rooms.get(0);
        }
        return null;
    }
    
    public static Room getRoomByName(String roomName){
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Room r where r.name = :name and r.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("name", roomName);
        
        List<Room> rooms = query.list();
        endTransaction();
        if(rooms != null && rooms.size()>0){
            return rooms.get(0);
        }
        return null;
    }
    
    public static List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<Course>();
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Course c where c.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        
        courses = query.list();
        endTransaction();
        
        return courses;
    }
 
    public static Course getCourseByCode(String code){
        List<Course> courses = new ArrayList<Course>();
        Course course = null;
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Course c where c.code = :code and c.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("code", code);
        courses = query.list();
        if(courses.size() != 0){
            course = courses.get(0);
        }
        endTransaction();
        
        return course;
    }
 
    public static List<RoomType> getAllRoomTypes(){
        List<RoomType> types = new ArrayList<RoomType>();
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from RoomType rt where rt.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        
        types = query.list();
        endTransaction();
        
        return types;
    }
    
    public static List<Block> getAllBlocks(){
        List<Block> blocks = new ArrayList<Block>();
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Block b where b.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        
        blocks = query.list();
        endTransaction();
        
        return blocks;
    }
    
    public static Block getBlockByName(String name){
        List<Block> blocks = new ArrayList<Block>();
        Block block = null;
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        String queryString = "from Block b where b.name = :name and b.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("name", name);
        blocks = query.list();
        if(blocks.size() > 0){
            block = blocks.get(0);
        }
        endTransaction();
        
        return block;
    }
    
    public static List<Semester> getAllSemester() {
        beginTransaction();
        org.hibernate.Query query = getSession().getNamedQuery("Semester.getAllSemesters");
        
        List<Semester> semesters = query.list();
        endTransaction();
        return semesters;
    }

    public static List<Room> getRoomsForBlock(Block block) {
        beginTransaction();
        String queryString = "from Room r where r.block = " +
                ":block and r.recordStatus = 'Active'";
        org.hibernate.Query query = getSession().createQuery(queryString);
        query.setParameter("block", block);
        
        List<Room> rooms = query.list();
        endTransaction();
        return rooms;
    }
    
    public static Semester getSemesterById(int semesterId) {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        org.hibernate.Query query = getSession().getNamedQuery("Semester.getSemesterById");
        query.setParameter("id", semesterId);
        
        List<Semester> list = query.list();
        Semester semester = null;
        if(list != null && list.size() > 0){
            semester = list.get(0);
        }
        endTransaction();
        return semester;
    }
    
    public static List<Event> getAllEvents(){
        beginTransaction();
        org.hibernate.Query query = getSession().createQuery("from Event e where e.recordStatus = 'Active'");
        
        List<Event> list = query.list();
        
        endTransaction();
        return list;
    }
    
    public static BaseEntity getEntityById(Class clazz, int id) {
        
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        BaseEntity entity = (BaseEntity)getSession().get(clazz, id);
        
        if(entity == null){
            return null;
        }
        
        if(entity.getRecordStatus() == BaseEntity.RecordStatus.Deleted){
            entity = null;
        }
        endTransaction();
        return entity;
    }

    public static void saveEntity(BaseEntity entity, User createUser) {
        if(entity.getCreateUser() == null){
            entity.setCreateUser(createUser);
        }
        saveEntity(entity);
    }
    
    public static void saveEntity(BaseEntity entity) {
        beginTransaction();
//        EntityManager manager = HibernateUtil.getEntityManager();
        if(entity.getCreateDate() == null){
            entity.setCreateDate(new Date());
        }
        if(entity.getRecordStatus() == null){
            entity.setRecordStatus(BaseEntity.RecordStatus.Active);
        }
        if(entity.getId() == null){
            getSession().save(entity);
        }else{
            getSession().update(entity);
        }
        
//        getSession().saveOrUpdate(entity);
//        getSession().persist(entity);
        LOG.debug("about to save entity "+getSession().getEntityName(entity));
        getSession().getTransaction().commit();
        LOG.debug("entity was saved with id: "+entity.getId());
        endTransaction();
    }
    
    public static void deleteEntity(BaseEntity entity) {
//        entity.setRecordStatus(BaseEntity.RecordStatus.Deleted);
//        saveEntity(entity);
        beginTransaction();
//        LOG.debug("about to delete entity "+getSession().getEntityName(entity));
        entity.setRecordStatus(BaseEntity.RecordStatus.Deleted);
        getSession().update(entity);
//        getSession().delete(entity);
//        getSession().getTransaction().commit();
        
        endTransaction();
    }
    
}
