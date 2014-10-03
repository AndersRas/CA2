package facade;

import Entity.Assistentteacher;
import Entity.Person;
import Entity.Roleschool;
import Entity.Student;
import Entity.Teacher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Bettina
 */
public class Facade implements FacadeIF {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ca2_3semesterPU");
//    EntityManager em = emf.createEntityManager();

    
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static Facade instance = new Facade();

    public Facade() {
    }

    public static Facade getFacade(boolean reset) {
        if (true) {
            instance = new Facade();
        }
        return instance;
    }

    @Override
    public String getPersonsAsJSON() {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = em.createQuery("SELECT p FROM Person p").getResultList();
        return gson.toJson(persons);

    }

    @Override
    public String getPersonAsJSON(Integer id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new NotFoundException("No person exists for the given id!");
        }
        return gson.toJson(person);
    }

    @Override
    public Person addPersonFromGson(String json) {
        EntityManager em = emf.createEntityManager();
        Person person = gson.fromJson(json, Person.class);
        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return person;
    }

    @Override
    public Roleschool addRoleFromGson(String json, Integer id) {
        EntityManager em = emf.createEntityManager();
        
        Roleschool role = gson.fromJson(json, Roleschool.class);
        System.out.println(role.getRoleName());

        if(role.getRoleName().equals("Student")){
             role = gson.fromJson(json, Student.class);
        }
        if(role.getRoleName().equals("Teacher")){
            role = gson.fromJson(json, Teacher.class);
        }
        if(role.getRoleName().equals("Assistentteacher")){
            role = gson.fromJson(json, Assistentteacher.class);
        }
      
        Person person = null;
        
        em.getTransaction().begin();
        try {
            person = em.find(Person.class, id);
            person.addRole(role);
            em.persist(role);
            em.persist(person);
           em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return role;
    }

    @Override
    public Person delete(Integer id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new NotFoundException("No person exists for the given id!");
        }
        em.getTransaction().begin();
        try {
            em.remove(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            person = null;
        } //finally {
//            em.close();
//        }

            return person;
        }

    }

