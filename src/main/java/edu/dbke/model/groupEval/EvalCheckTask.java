package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_check_task")
public class EvalCheckTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 被评论组
     */
    @Column(name = "eval_group_id")
    private Integer evalGroupId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 评论信息
     */
    @Column(name = "eval_comment_id")
    private Integer evalCommentId;

    /**
     * 评分人员
     */
    @Column(name = "eval_student")
    private Integer evalStudent;

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
     * 获取被评论组
     *
     * @return eval_group_id - 被评论组
     */
    public Integer getEvalGroupId() {
        return evalGroupId;
    }

    /**
     * 设置被评论组
     *
     * @param evalGroupId 被评论组
     */
    public void setEvalGroupId(Integer evalGroupId) {
        this.evalGroupId = evalGroupId;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取评论信息
     *
     * @return eval_comment_id - 评论信息
     */
    public Integer getEvalCommentId() {
        return evalCommentId;
    }

    /**
     * 设置评论信息
     *
     * @param evalCommentId 评论信息
     */
    public void setEvalCommentId(Integer evalCommentId) {
        this.evalCommentId = evalCommentId;
    }

    /**
     * 获取评分人员
     *
     * @return eval_student - 评分人员
     */
    public Integer getEvalStudent() {
        return evalStudent;
    }

    /**
     * 设置评分人员
     *
     * @param evalStudent 评分人员
     */
    public void setEvalStudent(Integer evalStudent) {
        this.evalStudent = evalStudent;
    }
}