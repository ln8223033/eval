package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 班级
 * Created by hp on 2016/12/10.
 */
@Table(name="t_class")
public class Classes  {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;//班级名称
    @Column(name = "Grade")
    private int grade;//年级

    @Column(name = "DepartmentId")
    private Department department;//所属学院

    @Column(name = "ProfessionId")
    private Profession profession;//专业

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
