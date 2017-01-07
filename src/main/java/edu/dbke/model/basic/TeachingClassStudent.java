package edu.dbke.model.basic;

import javax.persistence.*;

/**
 * 教学班级学生
 * Created by hp on 2016/12/14.
 */
@Table(name = "t_jx_class_student")
public class TeachingClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static Integer NORMAL = 1;//正常状态
    public static Integer DELETED = 2;//已删除
    public static Integer LEAVE = 3;//请假


    private Integer status;//学生的状态

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @JoinColumn(name = "teachingClassId")
    private TeachingClass teachingClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }
}
