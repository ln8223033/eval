package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 校区
 * Created by hp on 2016/12/15.
 */

@Table(name = "t_schoolcampus")
public class SchoolCampus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;//校区名字

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
}
