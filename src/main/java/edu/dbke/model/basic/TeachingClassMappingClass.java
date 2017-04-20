package edu.dbke.model.basic;

import javax.persistence.*;

@Table(name = "t_jx_class_mapping_teachingclass")
public class TeachingClassMappingClass {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 教学班ID
     */
    @Column(name = "TeachingClassId")
    private Integer teachingclassid;

    /**
     * 自然班级ID（）
     */
    @Column(name = "ClassId")
    private Integer classid;

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
     * 获取教学班ID
     *
     * @return TeachingClassId - 教学班ID
     */
    public Integer getTeachingclassid() {
        return teachingclassid;
    }

    /**
     * 设置教学班ID
     *
     * @param teachingclassid 教学班ID
     */
    public void setTeachingclassid(Integer teachingclassid) {
        this.teachingclassid = teachingclassid;
    }

    /**
     * 获取自然班级ID（）
     *
     * @return ClassId - 自然班级ID（）
     */
    public Integer getClassid() {
        return classid;
    }

    /**
     * 设置自然班级ID（）
     *
     * @param classid 自然班级ID（）
     */
    public void setClassid(Integer classid) {
        this.classid = classid;
    }
}