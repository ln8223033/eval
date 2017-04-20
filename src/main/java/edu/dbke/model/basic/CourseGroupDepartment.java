package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_coursegroup_department")
public class CoursegroupDepartment {
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
     * 课程组所属学院ID
     */
    @Column(name = "DepartmentId")
    private Integer departmentid;

    /**
     * 数据写入时间
     */
    @Column(name = "InDate")
    private Date indate;

    @Column(name = "DepartmentName")
    private String departmentname;

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
     * 获取课程组所属学院ID
     *
     * @return DepartmentId - 课程组所属学院ID
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * 设置课程组所属学院ID
     *
     * @param departmentid 课程组所属学院ID
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
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
     * @return DepartmentName
     */
    public String getDepartmentname() {
        return departmentname;
    }

    /**
     * @param departmentname
     */
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
}