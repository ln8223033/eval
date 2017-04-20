package edu.dbke.model.groupEval;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "eval_student_download")
public class EvalStudentDownload {
    /**
     * 评测作业
     */
    @Column(name = "eval_work_id")
    private Integer evalWorkId;

    /**
     * 评测中的学生
     */
    @Column(name = "eval_studnet_id")
    private Integer evalStudnetId;

    /**
     * 获取评测作业
     *
     * @return eval_work_id - 评测作业
     */
    public Integer getEvalWorkId() {
        return evalWorkId;
    }

    /**
     * 设置评测作业
     *
     * @param evalWorkId 评测作业
     */
    public void setEvalWorkId(Integer evalWorkId) {
        this.evalWorkId = evalWorkId;
    }

    /**
     * 获取评测中的学生
     *
     * @return eval_studnet_id - 评测中的学生
     */
    public Integer getEvalStudnetId() {
        return evalStudnetId;
    }

    /**
     * 设置评测中的学生
     *
     * @param evalStudnetId 评测中的学生
     */
    public void setEvalStudnetId(Integer evalStudnetId) {
        this.evalStudnetId = evalStudnetId;
    }
}