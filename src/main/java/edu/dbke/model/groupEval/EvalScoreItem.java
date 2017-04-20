package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_score_item")
public class EvalScoreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 被打分项
     */
    @Column(name = "eval_check_item_id")
    private Integer evalCheckItemId;

    /**
     * 得分
     */
    private Float score;

    /**
     * 所属评论
     */
    @Column(name = "eval_comment_id")
    private Integer evalCommentId;

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
     * 获取被打分项
     *
     * @return eval_check_item_id - 被打分项
     */
    public Integer getEvalCheckItemId() {
        return evalCheckItemId;
    }

    /**
     * 设置被打分项
     *
     * @param evalCheckItemId 被打分项
     */
    public void setEvalCheckItemId(Integer evalCheckItemId) {
        this.evalCheckItemId = evalCheckItemId;
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
     * 获取所属评论
     *
     * @return eval_comment_id - 所属评论
     */
    public Integer getEvalCommentId() {
        return evalCommentId;
    }

    /**
     * 设置所属评论
     *
     * @param evalCommentId 所属评论
     */
    public void setEvalCommentId(Integer evalCommentId) {
        this.evalCommentId = evalCommentId;
    }
}