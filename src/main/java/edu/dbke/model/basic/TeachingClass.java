package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

;

/**
 * 教学班级
 * Created by hp on 2016/12/10.
 */
@Table(name = "t_jx_class")
public class TeachingClass {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;// 教学班级名字

    private Date beginDate;// 开课时间
    private Date endDate;// 结课时间

    private String memo;//备注

    @Column(name = "DeptId")
    private Department department;// 挂靠学院

    @Column(name = "SemesterPlanDetailId")
    private SemesterPlanDetail semesterPlanDetail;// 所属课程


    @Column(name = "TeacherId")
    private Teacher teacher;//任课老师

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public SemesterPlanDetail getSemesterPlanDetail() {
        return semesterPlanDetail;
    }

    public void setSemesterPlanDetail(SemesterPlanDetail semesterPlanDetail) {
        this.semesterPlanDetail = semesterPlanDetail;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
