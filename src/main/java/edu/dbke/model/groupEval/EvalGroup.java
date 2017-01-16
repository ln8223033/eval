package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 群体评测的分组
 * Created by hp on 2016/12/12.
 */
@Table(name = "eval_comment_reply")
public class EvalGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static int unchecked = 1;
    public static int checked = 2;
    public static int checking = 3;


    @Column(name = "group_eval_id")
    private GroupEval groupEval;//所属题目
    @Column(name = "group_name")
    private String groupName;//分组名字，也称本组项目名字
    @Column(name = "group_num")
    private Integer groupNum;//组号，从1开始
    private Double score;//得分,所有成员的基础分，个人成员的分在些基础上进行加减
    @Column(name = "teacher_score")
    private Double teacherScore;//教师打的分
    @Column(name = "student_score")
    private Double studentScore;//学生打的分
    @Column(name = "teacher_perscent")
    private Double teacherPerscent = 0.0;//教师打分所占比例，默认为0
    private int status;//状态

    private List<EvalStudent> grouper = new ArrayList<EvalStudent>();//组员

    private List<EvalWork> evalWork = new ArrayList<EvalWork>();//作业

    private List<EvalCheckTask> evalCheckTasks = new ArrayList<EvalCheckTask>();

    private List<EvalComment> comments = new ArrayList<EvalComment>();
    @Transient
    private String leader;//组长
    @Transient
    private String teamer;//组员
    @Transient
    private String statusDTO;//状态
    @Transient
    private String leaderNo ; //组长的学号
    @Transient
    private String statusToString;//以中文显示的状态
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

    public GroupEval getGroupEval() {
        return groupEval;
    }

    public void setGroupEval(GroupEval groupEval) {
        this.groupEval = groupEval;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTeacherScore() {
        return teacherScore;
    }

    public void setTeacherScore(Double teacherScore) {
        this.teacherScore = teacherScore;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }

    public Double getTeacherPerscent() {
        return teacherPerscent;
    }

    public void setTeacherPerscent(Double teacherPerscent) {
        this.teacherPerscent = teacherPerscent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<EvalStudent> getGrouper() {
        return grouper;
    }

    public void setGrouper(List<EvalStudent> grouper) {
        this.grouper = grouper;
    }

    public List<EvalWork> getEvalWork() {
        return evalWork;
    }

    public void setEvalWork(List<EvalWork> evalWork) {
        this.evalWork = evalWork;
    }

    public List<EvalCheckTask> getEvalCheckTasks() {
        return evalCheckTasks;
    }

    public void setEvalCheckTasks(List<EvalCheckTask> evalCheckTasks) {
        this.evalCheckTasks = evalCheckTasks;
    }

    public List<EvalComment> getComments() {
        return comments;
    }

    public void setComments(List<EvalComment> comments) {
        this.comments = comments;
    }

    public String getLeader() {
        for (EvalStudent evalStu : grouper) {
            if (evalStu.isLeader()) {
                return evalStu.getStudent().getName();
            }
        }
        return "";
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTeamer() {
        StringBuffer sb = new StringBuffer();
        for (EvalStudent evalStu : grouper) {
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

    public String getStatusDTO() {
        if (status == checked) {
            return "已评";
        } else if (status == unchecked) {
            return "未评";
        } else if (status == checking) {
            return "评论中";
        }
        return statusDTO;
    }

    public void setStatusDTO(String statusDTO) {
        this.statusDTO = statusDTO;
    }

    public String getLeaderNo() {
        for (EvalStudent evalStu : grouper) {
            if (evalStu.isLeader()) {
                return evalStu.getStudent().getStudentNo();
            }
        }
        return "";
    }

    public void setLeaderNo(String leaderNo) {
        this.leaderNo = leaderNo;
    }

    public String getStatusToString() {
        return statusToString;
    }

    public void setStatusToString(String statusToString) {
        this.statusToString = statusToString;
    }

    public double getGivenScore() {
        return givenScore;
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
