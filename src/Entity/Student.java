/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "STUDENT")
@DiscriminatorValue("S")
public class Student extends Roleschool {
    
    @Column(name = "SEMESTER")
    private String semester;

    public Student(String semester, Person owner, String roleName) {
        super(owner, roleName);
        this.semester = semester;
    }
    
    public Student() {
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
}
