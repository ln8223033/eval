package edu.dbke.model.basic;

import javax.persistence.*;

@Table(name = "t_coursegroup_department")
public class CourseGroupDepartment {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "CourseGroupId")
    private CourseGroup coursegroup;//课程组ID

    @Column(name = "DepartmentId")
    private Department department;//课程组所属学院ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseGroup getCoursegroup() {
        return coursegroup;
    }

    public void setCoursegroup(CourseGroup coursegroup) {
        this.coursegroup = coursegroup;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}