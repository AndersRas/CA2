package FacadeT;

import Entity.Person;
import Entity.Roleschool;
import Entity.Student;
import com.google.gson.Gson;
import exceptions.NotFoundException;
import facade.Facade;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bettina
 */
public class FacadeTest {

    Facade facade;
    Gson gson = new Gson();
    EntityManager em;
    EntityManagerFactory emf;

    public FacadeTest() {
    }

    @Before
    public void setUp() {
        facade = Facade.getFacade(true);
        emf = Persistence.createEntityManagerFactory("Ca2_3semesterPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        facade = null;
        emf = null;
        em = null;
    }

    @Test
    public void testAddPersonFromGson() throws NotFoundException {
        Person per = new Person("Bettina", "Løfmark");
//        per.addRole(Student("3 semester", per, "datamatiker student"));
        Person p = facade.addPersonFromGson(gson.toJson(per));
        String expectedJsonString = gson.toJson(p);
        String actual = facade.getPersonAsJSON(p.getId());
        
//        System.out.println(gson.toJson(per));
//        System.out.println(expectedJsonString);
//        System.out.println(actual);
        
        assertEquals(expectedJsonString, actual);
        System.out.println(p.getId());

    }

    @Test
    public void testGetPersonAsJSON() throws Exception {
        testAddPersonFromGson();
    }

    @Test
    public void testGetPersonsAsJSON() {
        Person p1 = new Person("Tanja", "Cederholm");
        Person p2 = new Person("Joachim", "Steffensen");
        Person pers1 = facade.addPersonFromGson(gson.toJson(p1));
        Person pers2 = facade.addPersonFromGson(gson.toJson(p2));

        Map<Integer, Person> test = new HashMap();
        test.put(pers1.getId(), pers1);
        test.put(pers2.getId(), pers2);
        String expected = gson.toJson(test.values());
        String result = facade.getPersonsAsJSON();
        
        System.out.println(expected);
        System.out.println(result);
        
        assertEquals(expected, result);
    }

    @Test
    public void testAddRoleFromGson() throws NotFoundException {
        Person p = facade.addPersonFromGson(gson.toJson(new Person("Bettina", "Løfmark")));
        Roleschool role = new Student("3. Semester", p, "Datamatiker");
        facade.addRoleFromGson(gson.toJson(role), p.getId());
        String personAsJSON = facade.getPersonAsJSON(p.getId());
        Person fromJSON = gson.fromJson(personAsJSON, Person.class);
        Roleschool getRole = fromJSON.getRoles().get(0);
        assertEquals(role.getId(), getRole.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testDelete() throws Exception {
        Person p = facade.addPersonFromGson(gson.toJson(new Person("Mikkel", "Mortensen")));
        facade.delete(p.getId());
        facade.getPersonAsJSON(p.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNonExistingPerson() throws Exception {
        facade.getPersonAsJSON(10);
    }

}
