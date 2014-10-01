/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christoffer
 */
public class PersonTest {
    
    public PersonTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreatingPerson() {
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
//        Person p = new Person("Anders", "Rasmussen");
//        em.getTransaction().begin();
//        em.persist(p);
//        em.getTransaction().commit();
        List<Person> persons = em.createQuery("select p from Person p").getResultList();
        for(Person c: persons){
            System.out.println(c.getFirstname());
        }
        assertEquals("Anders", persons.get(0).getFirstname());
        
    }
    
}
