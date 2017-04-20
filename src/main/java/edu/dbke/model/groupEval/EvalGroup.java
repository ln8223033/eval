package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_group")
public class EvalGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 群体评测
     */
    @Column(name = "group_eval_id")
    private Integer groupEvalId;

    /**
     * 小组编号
     */
    @Column(name = "group_num")
    private Integer groupNum;

    /**
     * 得分
     */
    private Float score;

    /**
     * 组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 是否评论的状态
     */
    private Integer status;

    /**
     * 老师打的分
     */
    @Column(name = "teacher_score")
    private Float teacherScore;

    /**
     * 学生打的分
     */
    @Column(name = "student_score")
    private Float studentScore;

    /**
     * 教师打分占得比例
     */
    @Column(name = "teacher_perscent")
    private Float teacherPerscent;

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
     * 获取群体评测
     *
     * @return group_eval_id - 群体评测
     */
    public Integer getGroupEvalId() {
        return groupEvalId;
    }

    /**
     * 设置群体评测
     *
     * @param groupEvalId 群体评测
     */
    public void setGroupEvalId(Integer groupEvalId) {
        this.groupEvalId = groupEvalId;
    }

    /**
     * 获取小组编号
     *
     * @return group_num - 小组编号
     */
    public Integer getGroupNum() {
        return groupNum;
    }

    /**
     * 设置小组编号
     *
     * @param groupNum 小组编号
     */
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * 获取得分
     *
     * @return score - 得分
     */
    public Float getScore() {
        return score;
    }

    /**
     * 设置得分
     *
     * @param score 得分
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取组名
     *
     * @return group_name - 组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置组名
     *
     * @param groupName 组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取是否评论的状态
     *
     * @return status - 是否评论的状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否评论的状态
     *
     * @param status 是否评论的状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取老师打的分
     *
     * @return teacher_score - 老师打的分
     */
    public Float getTeacherScore() {
        return teacherScore;
    }

    /**
     * 设置老师打的分
     *
     * @param teacherScore 老师打的分
     */
    public void setTeacherScore(Float teacherScore) {
        this.teacherScore = teacherScore;
    }

    /**
     * 获取学生打的分
     *
     * @return student_score - 学生打的分
     */
    public Float getStudentScore() {
        return studentScore;
    }

    /**
     * 设置学生打的分
     *
     * @param studentScore 学生打的分
     */
    public void setStudentScore(Float studentScore) {
        this.studentScore = studentScore;
    }

    /**
     * 获取教师打分占得比例
     *
     * @return teacher_perscent - 教师打分占得比例
     */
    public Float getTeacherPerscent() {
        return teacherPerscent;
    }

    /**
     * 设置教师打分占得比例
     *
     * @param teacherPerscent 教师打分占得比例
     */
    public void setTeacherPerscent(Float teacherPerscent) {
        this.teacherPerscent = teacherPerscent;
    }
}