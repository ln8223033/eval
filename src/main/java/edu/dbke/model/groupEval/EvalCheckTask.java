package edu.dbke.model.groupEval;

import javax.persistence.*;

/**
 * 评分任务分组
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_check_task")
public class EvalCheckTask  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static int unchecked = 1;
    public static int checked = 2;


    @Column(name = "eval_student_id")
    private EvalStudent evalStudents = new EvalStudent();//评分人员


    @Column(name = "eval_group_id")
    private EvalGroup evalGroup = new EvalGroup();//被评论组


    @Column(name = "eval_comment_id")
    private EvalComment evalComment;//评论信息

    private int status;//状态
    @Transient
    private String groupNum;//待评论组的编号
    @Transient
    private String leader;//待评论组的组长
    @Transient
    private String teamer;//组员
    @Transient
    private int evalWorks;//作业数
    @Transient
    private String stateToString;//以中文显示的状态
    @Transient
    private double givenScore;//所评的分值
    @Transient
    private String groupId;//隐藏的被评论组的Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvalStudent getEvalStudents() {
        return evalStudents;
    }

    public void setEvalStudents(EvalStudent evalStudents) {
        this.evalStudents = evalStudents;
    }

    public EvalGroup getEvalGroup() {
        return evalGroup;
    }

    public void setEvalGroup(EvalGroup evalGroup) {
        this.evalGroup = evalGroup;
    }

    public EvalComment getEvalComment() {
        return evalComment;
    }

    public void setEvalComment(EvalComment evalComment) {
        this.evalComment = evalComment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTeamer() {
        StringBuffer sb = new StringBuffer();
        for (EvalStudent evalStu : evalGroup.getGrouper()) {
            if (!evalStu.isLeader()) {
                sb.append(evalStu.getStudent().getName() + ",");
            }
        }
        if (-1 != sb.lastIndexOf(",")) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }

    public void setTeamer(String teamer) {
        this.teamer = teamer;
    }

    public int getEvalWorks() {
        return evalWorks;
    }

    public void setEvalWorks(int evalWorks) {
        this.evalWorks = evalWorks;
    }

    public String getStateToString() {
        if (status == unchecked) {
            return "未评";
        } else if (status == checked) {
            return "已评";
        }
        return stateToString;
    }

    public void setStateToString(String stateToString) {
        this.stateToString = stateToString;
    }

    public double getGivenScore() {
        if (null == evalComment) {
            return 0;
        }
        return evalComment.getScore();
    }

    public void setGivenScore(double givenScore) {
        this.givenScore = givenScore;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
