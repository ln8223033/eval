package edu.dbke.model.basic;

import javax.persistence.*;

@Table(name = "t_class")
public class Class {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 班级名称
     */
    @Column(name = "Name")
    private String name;

    /**
     * 年级
     */
    @Column(name = "Grade")
    private Integer grade;

    /**
     * 学校ID
     */
    @Column(name = "SchoolId")
    private Integer schoolid;

    /**
     * 所属学院
     */
    @Column(name = "DepartmentId")
    private Integer departmentid;

    /**
     * 所属专业
     */
    @Column(name = "ProfessionId")
    private Integer professionid;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取班级名称
     *
     * @return Name - 班级名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置班级名称
     *
     * @param name 班级名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年级
     *
     * @return Grade - 年级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置年级
     *
     * @param grade 年级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取学校ID
     *
     * @return SchoolId - 学校ID
     */
    public Integer getSchoolid() {
        return schoolid;
    }

    /**
     * 设置学校ID
     *
     * @param schoolid 学校ID
     */
    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    /**
     * 获取所属学院
     *
     * @return DepartmentId - 所属学院
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * 设置所属学院
     *
     * @param departmentid 所属学院
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 获取所属专业
     *
     * @return ProfessionId - 所属专业
     */
    public Integer getProfessionid() {
        return professionid;
    }

    /**
     * 设置所属专业
     *
     * @param professionid 所属专业
     */
    public void setProfessionid(Integer professionid) {
        this.professionid = professionid;
    }


}