/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.google.gson.annotations.Expose;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Christoffer
 */
@Entity
@Table(name = "STUDENT")

public class Student extends Roleschool {
    
    @Column(name = "SEMESTER")
    @Expose
    private String semester;

    public Student() {
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
}
