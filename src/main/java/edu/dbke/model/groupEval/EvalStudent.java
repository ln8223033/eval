package edu.dbke.model.groupEval;



import edu.dbke.model.basic.Student;

import javax.persistence.*;

/**
 * 评测中的学生
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_student")
public class EvalStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Student student;

    @Column(name = "eval_group_id")
    private EvalGroup evalGroup;//所属分组

    @Column(name = "is_leader")
    private boolean isLeader;//组长

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public EvalGroup getEvalGroup() {
        return evalGroup;
    }

    public void setEvalGroup(EvalGroup evalGroup) {
        this.evalGroup = evalGroup;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }
}
