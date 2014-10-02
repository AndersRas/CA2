package facade;

import Entity.Person;
import Entity.Roleschool;
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
    EntityManager em = emf.createEntityManager();

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

        List<Person> persons = em.createQuery("SELECT p FROM person").getResultList();
        return gson.toJson(persons);

    }

    @Override
    public String getPersonAsJSON(long id) throws NotFoundException {
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new NotFoundException("No person exists for the given id!");
        }
        return gson.toJson(person);
    }

    @Override
    public Person addPersonFromGson(String json) {
        Person person = gson.fromJson(json, Person.class);
        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return person;
    }

    @Override
    public Roleschool addRoleFromGson(String json, long id) {
        Roleschool role = gson.fromJson(json, Roleschool.class);
        Person person = em.find(Person.class, id);
        //person.a
    }

    @Override
    public Person delete(long id) throws NotFoundException {
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
        }
        return person;
    }

}
