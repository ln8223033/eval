package edu.dbke.model.groupEval;

import edu.dbke.model.org.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 群体评论回复表
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_comment_reply")
public class EvalCommentReply  implements Comparable<EvalCommentReply>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reply_time")
    private Date replyTime ; //回复的时间

    @ManyToOne
    @JoinColumn(name = "eval_comment_id")
    private EvalComment evalComment;//对那个评论进行回复

    @ManyToOne
    @JoinColumn(name = "comment_reply_id")
    private EvalCommentReply commentReply;//对那个回复进行回复

    @ManyToOne
    @JoinColumn(name = "replyer_id")
    private User replyer;//回复者

    @Column(name = "reply_detail")
    private String replyDetail;//回复详情
    @OneToMany(mappedBy = "commentReply")
    private List<EvalCommentReply> evalCommentReplies = new ArrayList<EvalCommentReply>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public EvalComment getEvalComment() {
        return evalComment;
    }

    public void setEvalComment(EvalComment evalComment) {
        this.evalComment = evalComment;
    }

    public EvalCommentReply getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(EvalCommentReply commentReply) {
        this.commentReply = commentReply;
    }

    public User getReplyer() {
        return replyer;
    }

    public void setReplyer(User replyer) {
        this.replyer = replyer;
    }

    public String getReplyDetail() {
        return replyDetail;
    }

    public void setReplyDetail(String replyDetail) {
        this.replyDetail = replyDetail;
    }

    public List<EvalCommentReply> getEvalCommentReplies() {
        return evalCommentReplies;
    }

    public void setEvalCommentReplies(List<EvalCommentReply> evalCommentReplies) {
        this.evalCommentReplies = evalCommentReplies;
    }
    @Override
    public int compareTo(EvalCommentReply o) {
        return (int)(this.getReplyTime().getTime() - o.getReplyTime().getTime());
    }
}
