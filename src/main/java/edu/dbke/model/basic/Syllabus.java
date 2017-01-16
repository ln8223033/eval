package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 课程大纲表
 * Created by hp on 2016/12/10.
 */
@Table(name = "t_syllabus")
public class Syllabus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "InChargeOfDeptId")
    private Department department;   //所属学院


    @Column(name = "CourseName")
    private String courseName;;//课程名字


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

