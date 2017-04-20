package edu.dbke.model.basic;

import javax.persistence.*;

@Table(name = "t_coursegroup_knowledge")
public class CoursegroupKnowledge {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CourseGroupId")
    private Integer coursegroupid;

    /**
     * 知识点
     */
    private String knowledge;

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
     * 获取知识点
     *
     * @return knowledge - 知识点
     */
    public String getKnowledge() {
        return knowledge;
    }

    /**
     * 设置知识点
     *
     * @param knowledge 知识点
     */
    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
}