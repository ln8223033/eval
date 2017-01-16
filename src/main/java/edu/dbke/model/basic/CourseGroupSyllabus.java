package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * Created by hp on 2017/1/16.
 */
@Table(name = "t_coursegroup_syllabus")
public class CourseGroupSyllabus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CourseGroupId")
    private CourseGroup courseGroup;//课程组ID
    @Column(name = "SyllabusId")
    private Syllabus syllabus;//课程组ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }
}
