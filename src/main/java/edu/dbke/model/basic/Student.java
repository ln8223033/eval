package edu.dbke.model.basic;

import javax.persistence.*;


/**
 * 学生
 * Created by hp on 2016/12/10.
 */


@Table(name = "t_student")
public class Student  {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "StudentNo")
    private String studentNo;//学号
    @Column(name = "Name")
    private String name;//学生姓名
    @Column(name = "CurrentClassId")
    private Classes currentClass;//所在班级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classes getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Classes currentClass) {
        this.currentClass = currentClass;
    }
}


