package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 专业
 * Created by hp on 2016/12/15.
 */
@Table(name = "t_profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;//专业名称
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;// 挂靠学院

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
