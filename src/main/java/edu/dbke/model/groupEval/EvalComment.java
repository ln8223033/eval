package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.Date;

@Table(name = "eval_comment")
public class EvalComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论内容
     */
    private String detail;

    /**
     * 总分
     */
    private Float score;

    /**
     * 评论时间
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 评论的人
     */
    @Column(name = "commenter_id")
    private Integer commenterId;

    /**
     * 评论所属小组
     */
    @Column(name = "eval_group_id")
    private Integer evalGroupId;

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
     * 获取评论内容
     *
     * @return detail - 评论内容
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置评论内容
     *
     * @param detail 评论内容
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取总分
     *
     * @return score - 总分
     */
    public Float getScore() {
        return score;
    }

    /**
     * 设置总分
     *
     * @param score 总分
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取评论时间
     *
     * @return comment_time - 评论时间
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * 设置评论时间
     *
     * @param commentTime 评论时间
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * 获取评论的人
     *
     * @return commenter_id - 评论的人
     */
    public Integer getCommenterId() {
        return commenterId;
    }

    /**
     * 设置评论的人
     *
     * @param commenterId 评论的人
     */
    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    /**
     * 获取评论所属小组
     *
     * @return eval_group_id - 评论所属小组
     */
    public Integer getEvalGroupId() {
        return evalGroupId;
    }

    /**
     * 设置评论所属小组
     *
     * @param evalGroupId 评论所属小组
     */
    public void setEvalGroupId(Integer evalGroupId) {
        this.evalGroupId = evalGroupId;
    }
}