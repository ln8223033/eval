package edu.dbke.model.basic;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

;

/**
 * 教学班级
 * Created by hp on 2016/12/10.
 */
@Table(name = "t_jx_class")
public class TeachingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static Integer END = 1;
    private String name;// 教学班级名字
    @DateTimeFormat
    private Date beginDate;// 开课时间
    private Date endDate;// 结课时间
    private  Integer status = 0;//是否结课，默认没有结课
    private String memo;//备注
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;// 挂靠学院

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;// 所属课程

    @ManyToOne
    @JoinColumn(name = "teacherId")
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public int getStatus() {
        Date date = Calendar.getInstance().getTime();
        if(endDate.getTime()<date.getTime()){   //已经结课
            return status = END;
        }
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
