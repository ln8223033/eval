package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 学院
 * Created by hp on 2016/12/15.
 */
@Table(name = "t_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;//学院名称
    @ManyToOne
    @JoinColumn(name = "schoolId")
    private School school;//所属学校

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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
