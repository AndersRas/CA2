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
        Person p = new Person("Anders", "Rasmussen");
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        
        List<Person> persons = em.createQuery("select p from Person p").getResultList();
        assertEquals("Anders", persons.get(0).getFirstname());
    }
    
    @Test
    public void testRoleTeacherToPerson() {
        String roleName = "Teacher of fun";
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        Person p = new Person("Anders", "Rasmussen");
        Roleschool r = new Teacher("Goofing around", p, roleName);
        p.addRole(r);
        
        em.getTransaction().begin();
        em.persist(p);
        em.persist(r);
        em.getTransaction().commit();
        
        List<Teacher> persons = em.createQuery("select t from Teacher t").getResultList();
        assertEquals("Teacher of fun", persons.get(0).getRoleName());
    }
    
    @Test
    public void testRoleStudentToPerson() {
        String roleName = "Datamatik student";
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        Person p = new Person("Anders", "Rasmussen");
        Roleschool r = new Student("3rd Semester", p, roleName);
        p.addRole(r);
        
        em.getTransaction().begin();
        em.persist(p);
        em.persist(r);
        em.getTransaction().commit();
        
        List<Student> persons = em.createQuery("select s from Student s").getResultList();
        assertEquals(roleName, persons.get(0).getRoleName());
    }
    
    @Test
    public void testRoleAssistentteacherToPerson() {
        String roleName = "Master assistent";
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        Person p = new Person("Anders", "Rasmussen");
        Roleschool r = new Assistentteacher(p, roleName);
        p.addRole(r);
        
        em.getTransaction().begin();
        em.persist(p);
        em.persist(r);
        em.getTransaction().commit();
        
        List<Assistentteacher> persons = em.createQuery("select a from Assistentteacher a").getResultList();
        assertEquals(roleName, persons.get(0).getRoleName());
    }
}
