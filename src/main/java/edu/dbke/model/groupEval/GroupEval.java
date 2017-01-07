package edu.dbke.model.groupEval;




import edu.dbke.model.basic.Teacher;
import edu.dbke.model.basic.TeachingClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 群体评测
 * Created by hp on 2016/12/11.
 */
@Table(name = "group_eval")
public class GroupEval  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final int DELETED = 1;
    public static final int UNDELETED = 0;
    private static final long serialVersionUID = -791960602557046064L;
    public static Integer assigned = 1;//已分配
    public static Integer unAssigned = 0;//未分配

    private Integer status;//状态: 1:已删除  0:未删除
    @Column(name = "check_task_group_count")
    private Integer checkTaskGroupCount = 3;//评论任务所需评论组数，默认为3组
    @Column(name = "eval_title")
    private String evalTitle;//评测的名字
    @Column(columnDefinition = "CLOB")
    private String evalDetail;//评测的详细内容
    @Column(name = "start_time")
    private Date startTime;//开始时间
    @Column(name = "end_time")
    private Date endTime;//结束时间
    @Column(name = "upload _time")
    private Date uploadTime;//附件(作业)上传结束时间
    @Column(name = "group_time")
    private Date groupTime;//分组结束时间
    @Column(name = "group_size")
    private int groupSize = 1;//每组人数(默认1人)
    @Column(name = "group_method")
    private int groupMethod;//分组方式， 0为还没设置  1为教师指定，2为学生自己分组(默认教师指定)
    @Column(name = "bs_witch")
    private boolean bsWitch;//评论开关
    @Column(name = "auto_publish")
    private boolean autoPublish;//发布开关
    @Column(name = "multi_comment")
    private boolean multiComment;//是否可以多次评论
    @Column(name = "publish_result")
    private boolean publishResult;//是否公布结果
    @Column(name = "anonymous_comment")
    private boolean anonymousComment;//是否是匿名评论
    @Column(name = "assign_state")
    private int assignState;//是否已经分组
    @ManyToOne
    @JoinColumn(name = "group_eval_question_id")
    private GroupEvalQuestion groupEvalQuestion; //该次评测试用的评测题目

    @ManyToOne
    @JoinColumn(name = "teaching_class_id", nullable = false)
    private TeachingClass teachingClass;//教学班级

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Teacher owner;//出题人

    //使用了该题目的分组
    @OneToMany(mappedBy = "eval")
    @OrderBy("groupNum asc")
    private List<EvalGroup> evalGroups = new ArrayList<EvalGroup>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckTaskGroupCount() {
        return checkTaskGroupCount;
    }

    public void setCheckTaskGroupCount(Integer checkTaskGroupCount) {
        this.checkTaskGroupCount = checkTaskGroupCount;
    }

    public String getEvalTitle() {
        return evalTitle;
    }

    public void setEvalTitle(String evalTitle) {
        this.evalTitle = evalTitle;
    }

    public String getEvalDetail() {
        GroupEvalQuestion groupEvalQuestion=new GroupEvalQuestion();
        return groupEvalQuestion.getTitle();
    }

    public void setEvalDetail(String evalDetail) {
        this.evalDetail = evalDetail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Date groupTime) {
        this.groupTime = groupTime;
    }

    public int getGroupSize() {
        if( groupSize == 0)
            groupSize++ ;
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getGroupMethod() {
        return groupMethod;
    }

    public void setGroupMethod(int groupMethod) {
        this.groupMethod = groupMethod;
    }

    public boolean isBsWitch() {
        return bsWitch;
    }

    public void setBsWitch(boolean bsWitch) {
        this.bsWitch = bsWitch;
    }

    public boolean isAutoPublish() {
        return autoPublish;
    }

    public void setAutoPublish(boolean autoPublish) {
        this.autoPublish = autoPublish;
    }

    public boolean isMultiComment() {
        return multiComment;
    }

    public void setMultiComment(boolean multiComment) {
        this.multiComment = multiComment;
    }

    public boolean isPublishResult() {
        return publishResult;
    }

    public void setPublishResult(boolean publishResult) {
        this.publishResult = publishResult;
    }

    public boolean isAnonymousComment() {
        return anonymousComment;
    }

    public void setAnonymousComment(boolean anonymousComment) {
        this.anonymousComment = anonymousComment;
    }

    public int getAssignState() {
        return assignState;
    }

    public void setAssignState(int assignState) {
        this.assignState = assignState;
    }

    public GroupEvalQuestion getGroupEvalQuestion() {
        return groupEvalQuestion;
    }

    public void setGroupEvalQuestion(GroupEvalQuestion groupEvalQuestion) {
        this.groupEvalQuestion = groupEvalQuestion;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }

    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    public List<EvalGroup> getEvalGroups() {
        return evalGroups;
    }

    public void setEvalGroups(List<EvalGroup> evalGroups) {
        this.evalGroups = evalGroups;
    }
    /**
     * 是否发布显示
     */
    public String getPublishDTO(){
        if( autoPublish){
            return "<font color='green'>已发布</font>" ;
        }else{
            return "<font color='blue'>未发布</font>" ;
        }
    }

    /**
     * 获取每组人数
     */
    public String getGroupPeopleDTO(){

        if(getGroupMethod() == 0){           //教师未设置分组方式
            return "<font color='red'>未设置</font>";
        }else if( getGroupMethod() == 1){            //教师指定人数
            return "教师指定(" + "每组<font color='green'>" + groupSize + "</font>人)" ;
        }else if( getGroupMethod() == 2){
            return "自由组合(" + "每组<font color='green'>" + groupSize + "</font>人)" ;
        }else{
            return "" ;
        }
    }

    /**
     * 评测状态提示信息
     */
    public String getEvalStatusDTO(){
        Date date = Calendar.getInstance().getTime();
        if( this.autoPublish){
            if( date.getTime() > endTime.getTime()){   //评论已经开始
                return "<font color='gray'>评测已结束</font>" ;
            }else if( date.getTime() > uploadTime.getTime()){   //作品上传时间已经结束
                return "<font color='green'>上传时间已到,评论已开始</font>" ;
            }else if( groupTime.getTime() < date.getTime()){   //分组时间已经结束
                return "<font color='green'>分组时间已过</font>" ;
            }else if( date.getTime() > startTime.getTime()){   //进行中
                return "<font color='green'>评测开始</font>" ;
            }else if(date.getTime() < startTime.getTime()){        //未开始
                return "<font color='blue'>未开始</font>" ;
            }else{
                return "无" ;
            }
        }else{
            return "未发布" ;
        }
    }

    /**
     * 评分任务状态
     */
    public String getTaskStatusDTO(){
        if( assignState == 0){
            return "<font color='red'>未分配</font>" ;
        }else{
            return "<font color='green'>已分配</font>" ;
        }
    }

    /**
     * 成绩公布状态
     */
    public String getScoreStatusDTO(){
        if( publishResult){
            return "<font color='green'>已公布</font>" ;
        }else{
            return "<font color='blue'>未公布</font>" ;
        }
    }

    /**
     * 活动评测对应问题的题目信息
     */
    public String getEvalTitleDTO(){
        return this.getGroupEvalQuestion().getTitle();
    }
}
