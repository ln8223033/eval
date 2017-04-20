package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.Date;

@Table(name = "eval_comment_reply")
public class EvalCommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对评论进行回复
     */
    @Column(name = "eval_comment_id")
    private Integer evalCommentId;

    /**
     * 回复者
     */
    @Column(name = "replyer_id")
    private Integer replyerId;

    /**
     * 回复详情
     */
    @Column(name = "reply_detail")
    private String replyDetail;

    /**
     * 回复时间
     */
    @Column(name = "reply_time")
    private Date replyTime;

    /**
     * 对那个回复进行回复
     */
    @Column(name = "comment_reply_id")
    private Integer commentReplyId;

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
     * 获取对评论进行回复
     *
     * @return eval_comment_id - 对评论进行回复
     */
    public Integer getEvalCommentId() {
        return evalCommentId;
    }

    /**
     * 设置对评论进行回复
     *
     * @param evalCommentId 对评论进行回复
     */
    public void setEvalCommentId(Integer evalCommentId) {
        this.evalCommentId = evalCommentId;
    }

    /**
     * 获取回复者
     *
     * @return replyer_id - 回复者
     */
    public Integer getReplyerId() {
        return replyerId;
    }

    /**
     * 设置回复者
     *
     * @param replyerId 回复者
     */
    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    /**
     * 获取回复详情
     *
     * @return reply_detail - 回复详情
     */
    public String getReplyDetail() {
        return replyDetail;
    }

    /**
     * 设置回复详情
     *
     * @param replyDetail 回复详情
     */
    public void setReplyDetail(String replyDetail) {
        this.replyDetail = replyDetail;
    }

    /**
     * 获取回复时间
     *
     * @return reply_time - 回复时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置回复时间
     *
     * @param replyTime 回复时间
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取对那个回复进行回复
     *
     * @return comment_reply_id - 对那个回复进行回复
     */
    public Integer getCommentReplyId() {
        return commentReplyId;
    }

    /**
     * 设置对那个回复进行回复
     *
     * @param commentReplyId 对那个回复进行回复
     */
    public void setCommentReplyId(Integer commentReplyId) {
        this.commentReplyId = commentReplyId;
    }
}