package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 学院
 * Created by hp on 2016/12/15.
 */
@Table(name = "t_department")
public class Department {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;//学院名称

    @Column(name = "SchoolCampusId")
    private SchoolCampus schoolCampus;//校区ID

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

    public SchoolCampus getSchoolCampus() {
        return schoolCampus;
    }

    public void setSchoolCampus(SchoolCampus schoolCampus) {
        this.schoolCampus = schoolCampus;
    }
}
