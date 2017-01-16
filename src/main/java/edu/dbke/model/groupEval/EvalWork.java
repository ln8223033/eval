package edu.dbke.model.groupEval;

import edu.dbke.model.files.Files;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *评测作业
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_work")
public class EvalWork  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "eval_group_id")
    private EvalGroup evalGroup;// 上传作业的组

    @Column(name = "files_id")
    private Files files;// 作业实体

    private Set<EvalStudent> evalStudent = new HashSet<EvalStudent>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvalGroup getEvalGroup() {
        return evalGroup;
    }

    public void setEvalGroup(EvalGroup evalGroup) {
        this.evalGroup = evalGroup;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Set<EvalStudent> getEvalStudent() {
        return evalStudent;
    }

    public void setEvalStudent(Set<EvalStudent> evalStudent) {
        this.evalStudent = evalStudent;
    }
}
