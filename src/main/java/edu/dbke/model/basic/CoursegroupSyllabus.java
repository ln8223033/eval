package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_coursegroup_syllabus")
public class CoursegroupSyllabus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程组ID
     */
    @Column(name = "CourseGroupId")
    private Integer coursegroupid;

    /**
     * 课程大纲ID
     */
    @Column(name = "SyllabusId")
    private Integer syllabusid;

    /**
     * 数据写入时间
     */
    @Column(name = "InDate")
    private Date indate;

    @Column(name = "SyllabusName")
    private String syllabusname;

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
     * 获取课程组ID
     *
     * @return CourseGroupId - 课程组ID
     */
    public Integer getCoursegroupid() {
        return coursegroupid;
    }

    /**
     * 设置课程组ID
     *
     * @param coursegroupid 课程组ID
     */
    public void setCoursegroupid(Integer coursegroupid) {
        this.coursegroupid = coursegroupid;
    }

    /**
     * 获取课程大纲ID
     *
     * @return SyllabusId - 课程大纲ID
     */
    public Integer getSyllabusid() {
        return syllabusid;
    }

    /**
     * 设置课程大纲ID
     *
     * @param syllabusid 课程大纲ID
     */
    public void setSyllabusid(Integer syllabusid) {
        this.syllabusid = syllabusid;
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
     * @return SyllabusName
     */
    public String getSyllabusname() {
        return syllabusname;
    }

    /**
     * @param syllabusname
     */
    public void setSyllabusname(String syllabusname) {
        this.syllabusname = syllabusname;
    }
}