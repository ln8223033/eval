package edu.dbke.model.basic;

import javax.persistence.*;

@Table(name = "t_coursegroup_teacher")
public class CourseGroupTeacher {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CourseGroupId")
    private CourseGroup coursegroup;

    @Column(name = "TeacherId")
    private Teacher teacher;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}