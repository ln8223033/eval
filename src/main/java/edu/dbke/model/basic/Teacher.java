package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 2016/12/10.
 */


@Table(name = "t_teacher")
public class Teacher {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;//教师姓名


    private List<TeachingClass> teachingClasses = new ArrayList<TeachingClass>();//教学班
    @Transient
    private TeachingClass curTeachingClass;//当前教学班

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeachingClass> getTeachingClasses() {
        return teachingClasses;
    }

    public void setTeachingClasses(List<TeachingClass> teachingClasses) {
        this.teachingClasses = teachingClasses;
    }

    public TeachingClass getCurTeachingClass() {
        return curTeachingClass;
    }

    public void setCurTeachingClass(TeachingClass curTeachingClass) {
        this.curTeachingClass = curTeachingClass;
    }
}