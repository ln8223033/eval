package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_check_item")
public class EvalCheckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 得分标准
     */
    private String detail;

    /**
     * 该项所占分数
     */
    private Float score;

    /**
     * 父标准
     */
    @Column(name = "parent_item_id")
    private Integer parentItemId;

    /**
     * 评测题目id
     */
    @Column(name = "group_eval_question_id")
    private Integer groupEvalQuestionId;

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
     * 获取得分标准
     *
     * @return detail - 得分标准
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置得分标准
     *
     * @param detail 得分标准
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取该项所占分数
     *
     * @return score - 该项所占分数
     */
    public Float getScore() {
        return score;
    }

    /**
     * 设置该项所占分数
     *
     * @param score 该项所占分数
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取父标准
     *
     * @return parent_item_id - 父标准
     */
    public Integer getParentItemId() {
        return parentItemId;
    }

    /**
     * 设置父标准
     *
     * @param parentItemId 父标准
     */
    public void setParentItemId(Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    /**
     * 获取评测题目id
     *
     * @return group_eval_question_id - 评测题目id
     */
    public Integer getGroupEvalQuestionId() {
        return groupEvalQuestionId;
    }

    /**
     * 设置评测题目id
     *
     * @param groupEvalQuestionId 评测题目id
     */
    public void setGroupEvalQuestionId(Integer groupEvalQuestionId) {
        this.groupEvalQuestionId = groupEvalQuestionId;
    }
}