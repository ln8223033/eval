package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 群体评测题的得分项
 * Created by hp on 2016/12/11.
 */
@Entity
@Table(name = "eval_check_item")
public class EvalCheckItem  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detail;//得分标准详细说明
    private double score;//该项所占分数

    @Column(name = "group_eval_question_id")
    private GroupEvalQuestion groupEvalQuestion ;//项目评测题目


    @Column(name = "parent_item_id")
    private EvalCheckItem parentItem;//父标准

    private List<EvalCheckItem> childItem = new ArrayList<EvalCheckItem>();

    public List<EvalCheckItem> getChildItem() {
        return childItem;
    }

    public void setChildItem(List<EvalCheckItem> childItem) {
        this.childItem = childItem;
    }

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

    public GroupEvalQuestion getGroupEvalQuestion() {
        return groupEvalQuestion;
    }

    public void setGroupEvalQuestion(GroupEvalQuestion groupEvalQuestion) {
        this.groupEvalQuestion = groupEvalQuestion;
    }

    public EvalCheckItem getParentItem() {
        return parentItem;
    }

    public void setParentItem(EvalCheckItem parentItem) {
        this.parentItem = parentItem;
    }
}
