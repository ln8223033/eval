package edu.dbke.model.groupEval;

import edu.dbke.model.org.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 群体评论打分
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_comment")
public class EvalComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detail;//评论内容
    private double score;//总分
    @Column(name = "comment_time")
    private Date commentTime;//评论时间
    @ManyToOne
    @JoinColumn(name = "eval_group_id")
    private EvalGroup evalGroup = new EvalGroup();//被评论组
    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;//评论的人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public EvalGroup getEvalGroup() {
        return evalGroup;
    }

    public void setEvalGroup(EvalGroup evalGroup) {
        this.evalGroup = evalGroup;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
}
