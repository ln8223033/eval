package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_attachment")
public class EvalAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 本题对应的附件
     */
    private Integer filesid;

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
     * 获取本题对应的附件
     *
     * @return filesid - 本题对应的附件
     */
    public Integer getFilesid() {
        return filesid;
    }

    /**
     * 设置本题对应的附件
     *
     * @param filesid 本题对应的附件
     */
    public void setFilesid(Integer filesid) {
        this.filesid = filesid;
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