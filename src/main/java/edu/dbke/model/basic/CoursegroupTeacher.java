package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_coursegroup_teacher")
public class CoursegroupTeacher {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CourseGroupId")
    private Integer coursegroupid;

    @Column(name = "TeacherId")
    private Integer teacherid;

    /**
     * 数据写入时间
     */
    @Column(name = "InDate")
    private Date indate;

    @Column(name = "TeacherName")
    private String teachername;

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
     * @return CourseGroupId
     */
    public Integer getCoursegroupid() {
        return coursegroupid;
    }

    /**
     * @param coursegroupid
     */
    public void setCoursegroupid(Integer coursegroupid) {
        this.coursegroupid = coursegroupid;
    }

    /**
     * @return TeacherId
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * @param teacherid
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * 获取数据写入时间
     *
     * @return InDate - 数据写入时间
     */
    public Date getIndate() {
        return indate;
    }

    /**
     * 设置数据写入时间
     *
     * @param indate 数据写入时间
     */
    public void setIndate(Date indate) {
        this.indate = indate;
    }

    /**
     * @return TeacherName
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * @param teachername
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
}