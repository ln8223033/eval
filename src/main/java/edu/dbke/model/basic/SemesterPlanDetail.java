package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 课程
 * Created by hp on 2017/1/16.
 */
@Table(name = "t_tts_semester_execution_plan_detail")
public class SemesterPlanDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SyllabusId")
    private Syllabus syllabus;//大纲ID
    @Column(name = "InChargeOfDeptId")
    private Department department;//课程负责学院ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
