/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import ae.hct.admc.domain.Event;
import ae.hct.admc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saeed
 */
public class QueryTest {

    public QueryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testQuery(){
        
        Event e = new Event();
        e.setDescription("ddd");
        Session session = HibernateUtil.getAnnotationFactory().getCurrentSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}