/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "TEACHER")
public class Teacher extends Roleschool {
   
    @Column(name = "DEGREE")
    private String degree;
    
    public Teacher(String degree) {
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