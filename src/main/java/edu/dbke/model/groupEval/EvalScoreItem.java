package edu.dbke.model.groupEval;

import javax.persistence.*;

/**
 * 每项的得分
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_score_item")
public class EvalScoreItem  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "eval_check_item_id")
    private EvalCheckItem evalCheckItem;//被打分项

    @ManyToOne
    @JoinColumn(name = "eval_comment_id")
    private EvalComment evalComment;//所属评论

    private Double score;//得分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvalCheckItem getEvalCheckItem() {
        return evalCheckItem;
    }

    public void setEvalCheckItem(EvalCheckItem evalCheckItem) {
        this.evalCheckItem = evalCheckItem;
    }

    public EvalComment getEvalComment() {
        return evalComment;
    }

    public void setEvalComment(EvalComment evalComment) {
        this.evalComment = evalComment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
