package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 教学班级学生
 * Created by hp on 2016/12/14.
 */
@Table(name = "t_jx_class_student")
public class TeachingClassStudent {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "StudentId")
    private Student student;//学生ID

    @Column(name = "TeachingClassId")
    private TeachingClass teachingClass;//教学班ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }
}