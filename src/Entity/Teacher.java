/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.google.gson.annotations.Expose;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "TEACHER")
@DiscriminatorValue("T")
public class Teacher extends Roleschool {
   
    @Column(name = "DEGREE")
    @Expose
    private String degree;

    public Teacher(String degree, Person owner, String roleName) {
        super(owner, roleName);
        this.degree = degree;
    }
    
    public Teacher() {
    }
    
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    
}
