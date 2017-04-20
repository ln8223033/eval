package edu.dbke.model.groupEval;

import javax.persistence.*;

@Table(name = "eval_work")
public class EvalWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 作业实体
     */
    @Column(name = "files_id")
    private Integer filesId;

    /**
     * 上传作业的组
     */
    @Column(name = "eval_group_id")
    private Integer evalGroupId;

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
     * 获取作业实体
     *
     * @return files_id - 作业实体
     */
    public Integer getFilesId() {
        return filesId;
    }

    /**
     * 设置作业实体
     *
     * @param filesId 作业实体
     */
    public void setFilesId(Integer filesId) {
        this.filesId = filesId;
    }

    /**
     * 获取上传作业的组
     *
     * @return eval_group_id - 上传作业的组
     */
    public Integer getEvalGroupId() {
        return evalGroupId;
    }

    /**
     * 设置上传作业的组
     *
     * @param evalGroupId 上传作业的组
     */
    public void setEvalGroupId(Integer evalGroupId) {
        this.evalGroupId = evalGroupId;
    }
}