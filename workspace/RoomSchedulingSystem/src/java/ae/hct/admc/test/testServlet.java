/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.test;

import ae.hct.admc.domain.*;
import ae.hct.admc.service.HibernateService;
import ae.hct.admc.util.HibernateUtil;
import java.io.*;
import java.net.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import org.hibernate.Session;

/**
 *
 * @author Saeed
 */
public class testServlet extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Create All the Classes
        Block b = new Block();
        b.setDescription("Block Description");
        
        Course c = new Course();
        c.setDescription("Course Description");
        
        Event e = new Event();
        e.setDescription("Event Description");
        
        Period p = new Period();
        p.setPeriodSegment("08:00 - 08:55");
        
        Permission per = new Permission();
        per.setDescription("Create Event");
        
        Role r = new Role();
        r.setDescription("Admin");
        
        Room rom = new Room();
        rom.setDescription("Engineering Room");
        
        RoomType rt = new RoomType();
        rt.setType("Lab");
        
        Semester s = new Semester();
        s.setSemesterNo("1");
        
        User u = new User();
        u.setLoginName("Ali");
        
        Employee emp = new Employee();
        emp.setFirstName("Ahmed");
        
        Employee manager = new Employee();
        emp.setFirstName("Mohamed");
        
        RoomClassSchedule rcs = new RoomClassSchedule();
        
        RoomEventSchedule res = new RoomEventSchedule();
        
        
        //Set Relation
        b.getRooms().add(rom);
        per.getRoles().add(r);
        r.getPermissions().add(per);
        rom.setType(rt);
        u.setRole(r);
        
        rcs.setCourse(c);
        rcs.setPeriod(p);
        rcs.setReserver(u);
        rcs.setRoom(rom);
        rcs.setSemester(s);
        
        res.setEvent(e);
        res.setRoom(rom);
        
        emp.getCourses().add(c);
        emp.setManager(manager);
        
//        Session session = HibernateUtil.getAnnotationFactory().getCurrentSession();
//        session.beginTransaction();
//        
//        session.save(b);
//        session.save(c);
//        session.save(e);
//        session.save(p);
//        session.save(per);
//        session.save(r);
//        session.save(rom);
//        session.save(rt);
//        session.save(s);
//        session.save(u);
//        session.save(rcs);
//        session.save(res);
//        session.save(emp);
//        session.save(manager);
//        
//        session.getTransaction().commit();
//        
//        session = HibernateUtil.getAnnotationFactory().getCurrentSession();
//        session.beginTransaction();
        List<User> users = HibernateService.getAllUsers();
        
        for(int i=0;i< users.size();i++){
            System.out.println("User #"+(i+1)+": "+users.get(i).getLoginName());
        }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
