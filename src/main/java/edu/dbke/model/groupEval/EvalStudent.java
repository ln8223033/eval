package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_student")
public class EvalStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评测中的学生
     */
    @Column(name = "student_id")
    private Integer studentId;

    /**
     * 所属的评测小组
     */
    @Column(name = "eval_group_id")
    private Integer evalGroupId;

    /**
     * 是否是组长
     */
    @Column(name = "is_leader")
    private Boolean isLeader;

    /**
     * @return id
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
     * 获取评测中的学生
     *
     * @return student_id - 评测中的学生
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置评测中的学生
     *
     * @param studentId 评测中的学生
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取所属的评测小组
     *
     * @return eval_group_id - 所属的评测小组
     */
    public Integer getEvalGroupId() {
        return evalGroupId;
    }

    /**
     * 设置所属的评测小组
     *
     * @param evalGroupId 所属的评测小组
     */
    public void setEvalGroupId(Integer evalGroupId) {
        this.evalGroupId = evalGroupId;
    }

    /**
     * 获取是否是组长
     *
     * @return is_leader - 是否是组长
     */
    public Boolean getIsLeader() {
        return isLeader;
    }

    /**
     * 设置是否是组长
     *
     * @param isLeader 是否是组长
     */
    public void setIsLeader(Boolean isLeader) {
        this.isLeader = isLeader;
    }
}