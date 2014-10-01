package facade;

import Entity.Person;
import Entity.Roleschool;
import exceptions.NotFoundException;

/**
 * @author Bettina
 */
public interface FacadeIF {

    public String getPersonsAsJSON();

    public String getPersonAsJSON(long id) throws NotFoundException;

    public Person addPersonFromGson(String json);

    public Roleschool addRoleFromGson(String json, long id) throws NotFoundException;

    public Person delete(long id) throws NotFoundException;
}
