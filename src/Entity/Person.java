/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;
    
    @OneToMany(mappedBy="owner")
    private List<Roleschool> roles;

    @Column(name = "FIRSTNAME")
    @Expose
    private String firstname;
    
    @Column(name = "LASTNAME")
    @Expose
    private String lastname;
    
    @Column(name = "PHONE")
    @Expose
    private String phone;
    
    @Column(name = "EMAIL")
    @Expose
    private String email;

    public Person(String firstname, String lastname){
//        this();
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Person(){
//        if(roles == null){
//            roles = new ArrayList<>();
//        }
    }
    
    public List<Roleschool> getRoles() {
        return roles;
    }

    public void addRole(Roleschool role) {
        if(roles == null){
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
//    @Override
//    public String toString(){
//        return firstname + lastname;
//    }

}
