package edu.dbke.model.groupEval;

import edu.dbke.model.files.Files;


import javax.persistence.*;


/**
 * 评测对应附件表
 * Created by hp on 2016/12/12.
 */

@Table(name = "eval_attachment")
public class EvalAttachment   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "group_eval_question_id")
    private GroupEvalQuestion groupEvalQuestion ;//对应的评测题目

    @OneToOne
    @JoinColumn(name = "files_id")
    private Files files = new Files();//本题所对应的附件

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public GroupEvalQuestion getGroupEvalQuestion() {
        return groupEvalQuestion;
    }

    public void setGroupEvalQuestion(GroupEvalQuestion groupEvalQuestion) {
        this.groupEvalQuestion = groupEvalQuestion;
    }
}



