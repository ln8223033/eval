package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 专业
 * Created by hp on 2016/12/15.
 */
@Table(name = "t_profession")
public class Profession {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;//中文专业名称
    @ManyToOne
    @Column(name = "DeptId")
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
