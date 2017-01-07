package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 课程表
 * Created by hp on 2016/12/10.
 */
@Table(name = "t_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static String REQUIRED = "必修";//必修
    public static String OPTIONAL = "选修";//选修
    public static String PUBLIC = "公开";//公开课

    private  String name;//课程名字

    private String courseType;//课程类别，有必修，选修

    private String courseDetail ; // 课程介绍
    @ManyToOne
    private int classHour;//学时

    private double credit;//学分

    private double compose = 1;//平时成绩占总成绩的比例
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;//所属学院

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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCompose() {
        return compose;
    }

    public void setCompose(double compose) {
        this.compose = compose;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

