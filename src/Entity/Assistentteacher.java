/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "ASSISTENTTEACHER")
@DiscriminatorValue("AT")
public class Assistentteacher extends Roleschool {

    public Assistentteacher(Person owner, String roleName) {
        super(owner, roleName);
    }
    
    public Assistentteacher() {
    }

    
}
