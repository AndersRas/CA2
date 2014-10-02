/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "ROLESCHOOL")
@DiscriminatorColumn(name = "DT", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("R")
public class Roleschool implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    private Person owner;
    @Column(name = "role")
    @Expose
    private String role;

    @Column(name = "roleName")
    private String roleName;
    
    public Roleschool(Person owner, String roleName) {
        this.owner = owner;
        this.roleName = roleName;
    }
    
    public Roleschool() {
    }
    
    public Person getOwner() {
        return owner;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
    
}
