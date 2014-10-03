package facade;

import Entity.Person;
import Entity.Roleschool;
import exceptions.NotFoundException;

/**
 * @author Bettina
 */
public interface FacadeIF {

    public String getPersonsAsJSON();

    public String getPersonAsJSON(Integer id) throws NotFoundException;

    public Person addPersonFromGson(String json);

    public Roleschool addRoleFromGson(String json, Integer id) throws NotFoundException;

    public Person delete(Integer id) throws NotFoundException;
}
